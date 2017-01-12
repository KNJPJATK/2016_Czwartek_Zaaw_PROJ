package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.Answer;
import pl.knpj.servlet.model.QuestionAnswer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * DAO class for retrieving data from answer table
 */
public class AnswerDAO extends BaseDAO {

    private static final String ID_COLUMN = "id";
    private static final String TEXT_COLUMN = "text";

    private static final String CREATE_ANSWER_SQL =
            "INSERT INTO \"answer\" (id, text)" +
                    "values(?,?)";
    private static final String CREATE_QUESTION_ANSWER_SQL =
            "INSERT INTO \"question_answer\" (id, question_id, answer_id, is_correct) values (?,?,?,?)";
    private static final String UPDATE_ANSWER_SQL =
            "UPDATE \"answer\" SET text = ?, WHERE id = ?";
    private static final String DELETE_QUESTION_ANSWER_SQL =
            "DELETE FROM \"question_answer\" WHERE answer_id = ?";

    /**
     * {@inheritDoc}
     */
    protected Object parseResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (!rs.next()) {
            return null;
        }

        long id = rs.getLong(ID_COLUMN);
        String text = rs.getString(TEXT_COLUMN);

        QuestionAnswerDAO questionAnswerDAO = new QuestionAnswerDAO();
        Collection<QuestionAnswer> collection;
        collection = questionAnswerDAO.getQuestionAnswersByAnswerId(id);

        return new Answer(id, text, collection);
    }

    /**
     * This method adding answer in db.
     *
     * @param answer to add in db
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    public void createAnswer (Answer answer) throws SQLException, ClassNotFoundException{
        executeUpdate(CREATE_ANSWER_SQL, answer.getId(), answer.getText());

        for(QuestionAnswer question : answer.getQuestions()){

            executeUpdate(CREATE_QUESTION_ANSWER_SQL, question.getId(), question.getQuestionId(), question.getAnswerId(), question.isCorrect());

        }
    }

    /**
     * This method updating answer in db.
     *
     * @param text
     * @param id
     * @param questions
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    public void updateAnswer (String text, Long id, Collection<QuestionAnswer> questions) throws SQLException, ClassNotFoundException {

        executeUpdate(UPDATE_ANSWER_SQL, text, id);

        executeUpdate(DELETE_QUESTION_ANSWER_SQL, id);

        for (QuestionAnswer question : questions){

            executeUpdate(CREATE_QUESTION_ANSWER_SQL, question.getId(), question.getQuestionId(), question.getAnswerId(), question.isCorrect());

        }
    }
}
