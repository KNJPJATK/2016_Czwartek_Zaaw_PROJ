package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.Question;
import pl.knpj.servlet.model.QuestionAnswer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * DAO class for retrieving data from question table
 */
public class QuestionDAO extends BaseDAO {

    private static final String ID_COLUMN = "id";
    private static final String TITLE_COLUMN = "title";
    private static final String DESCRIPTION_COLUMN = "description";

    private static final String GET_QUESTION_SQL =
            "SELECT id, title, description " +
                    "FROM \"question\" WHERE title = ?";
    private static final String CREATE_QUESTION_SQL =
            "INSERT INTO \"question\" (id, title, description)" +
            "values(?,?,?)";
    private static final String CREATE_QUESTION_ANSWER_SQL =
            "INSERT INTO \"question_answer\" (id, question_id, answer_id, is_correct) values (?,?,?,?)";
    private static final String UPDATE_QUESTION_SQL =
            "UPDATE \"question\" SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_QUESTION_ANSWER_SQL =
            "DELETE FROM \"question_answer\" WHERE question_id = ?";


    /**
     * {@inheritDoc}
     */
    protected Object parseResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (!rs.next()) {
            return null;
        }

        long id = rs.getLong(ID_COLUMN);

        String title = rs.getString(TITLE_COLUMN);

        String description = rs.getString(DESCRIPTION_COLUMN);

        QuestionAnswerDAO questionAnswerDAO = new QuestionAnswerDAO();

        Collection<QuestionAnswer> collection;

        collection = questionAnswerDAO.getQuestionAnswersByQuestionId(id);

        return new Question(id, title, description, collection);

    }

    /**
     * Gets question from db by title.
     *
     * @param title
     * @return question from database, or null if not found
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    public Question getQuestionByTitle(String title) throws SQLException, ClassNotFoundException {

        ResultSet rs = executeQuery(GET_QUESTION_SQL, title);
        return (Question) parseResultSet(rs);

    }

    /**
     * This method check if the question stored already in db by title.
     *
     * @param title
     * @return true if question with this title is already stored in db, false otherwise
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    public boolean checkIfQuestionWithTitleExists (String title) throws SQLException, ClassNotFoundException{
        Question question = getQuestionByTitle(title);
        if(question == null){
            return false;
        } else
            return true;
    }

    /**
     * This method adding question in db.
     *
     * @param question to add in db
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    public void createQuestion (Question question) throws SQLException, ClassNotFoundException{
        executeUpdate(CREATE_QUESTION_SQL, question.getId(), question.getTitle(), question.getDescription());

        for(QuestionAnswer answer : question.getAnswers()){
            executeUpdate(CREATE_QUESTION_ANSWER_SQL, answer.getId(), answer.getQuestionId(), answer.getAnswerId(), answer.isCorrect());
        }

    }

    /**
     * This method updating question in db.
     *
     * @param title
     * @param description
     * @param id
     * @param answers
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    public void updateQuestion (String title, String description, Long id, Collection<QuestionAnswer> answers) throws SQLException, ClassNotFoundException{

        executeUpdate(UPDATE_QUESTION_SQL, title, description, id);

        executeUpdate(DELETE_QUESTION_ANSWER_SQL, id);

        for (QuestionAnswer answer : answers){

            executeUpdate(CREATE_QUESTION_ANSWER_SQL, answer.getId(), answer.getQuestionId(), answer.getAnswerId(), answer.isCorrect());

        }

    }
}
