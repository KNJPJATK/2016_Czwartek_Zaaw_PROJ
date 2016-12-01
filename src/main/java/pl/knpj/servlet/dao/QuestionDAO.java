package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.Answer;
import pl.knpj.servlet.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by vadikms on 29.11.16.
 */
public class QuestionDAO extends BaseDAO{

    private static final String GET_QUESTION_SQL =
            "SELECT id, title, description " +
                    "FROM \"questions\" WHERE title = ?";
    private static final String GET_ANSWERS_SQL =
            "SELECT id, text FROM \"answers\" WHERE forKey = ?";
    private static final String CREATE_QUESTION_SQL =
            "INSERT INTO \"questions\" (id, title, description)" +
            "values(?,?,?)";
    private static final String CREATE_ANSWER_SQL =
            "INSERT INTO \"answers\" (id, text, forKey) values (?,?,?)";
    private static final String UPDATE_QUESTION_SQL =
            "UPDATE \"questions\" SET title = ?, description = ? WHERE id = ?";

    private Question getQuestionFromRS (ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        } else {

            Long id = rs.getLong(1);
            String title = rs.getString(2);
            String description = rs.getString(3);
            Collection<Answer> correctAnswers = new HashSet<>();
            ResultSet resultSet = executeStatement(GET_ANSWERS_SQL, id);
            while (resultSet.next()) {
                Long idAnswer = rs.getLong(1);
                String text = rs.getString(2);
                correctAnswers.add(new Answer(idAnswer, text));
            }
            return new Question(id, title, description, correctAnswers);
        }
    }

    public Question getQuestionByTitle(String title) throws SQLException{
        ResultSet rs = executeStatement(GET_QUESTION_SQL, title);
        try {
            return getQuestionFromRS(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
        return null;
    }

    public boolean checkIfAlready(String title) throws SQLException{
        Question question = getQuestionByTitle(title);
        if(question == null){
            return false;
        } else
            return true;
    }

    public void createQuestion (Question question) throws SQLException{
        executeUpdate(CREATE_QUESTION_SQL, question.getId(), question.getTitle(), question.getDescription());
        Iterator iterator = question.getCorrectAnswer().iterator();
        while (iterator.hasNext()) {
            Answer answer = (Answer) iterator.next();
            executeUpdate(CREATE_ANSWER_SQL, answer.getId(), answer.getText(), question.getId());
        }
    }

    public void updateQuestion(String title, String description, Long id, Collection<Answer> answers) throws SQLException{
        executeUpdate(UPDATE_QUESTION_SQL, title, description, id);
        executeUpdate("DELETE FROM \"answers\" WHERE forKey = " + id);
        Iterator iterator = answers.iterator();
        while (iterator.hasNext()){
            Answer answer = (Answer) iterator.next();
            executeUpdate(CREATE_ANSWER_SQL, answer.getId(), answer.getText(), id);
        }
    }

    public void deleteFromTable(String name) throws SQLException{
        executeUpdate("DELETE FROM " + name);
    }
}
