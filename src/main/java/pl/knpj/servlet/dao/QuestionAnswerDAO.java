package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.QuestionAnswer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DAO class for retrieving data from question_answer table
 */
public class QuestionAnswerDAO extends BaseDAO{

    private final String ID_COLUMN = "id";
    private final String QUESTION_ID_COLUMN = "question_id";
    private final String ANSWER_ID_COLUMN = "answer_id";
    private final String IS_CORRECT_COLUMN = "is_correct";
    private static final String GET_BY_QUESTION_ID_SQL =
            "SELECT id, question_id, answer_id, is_correct " +
                    "FROM \"question_answer\" WHERE question_id = ?";
    private static final String GET_BY_ANSWER_ID_SQL =
            "SELECT id, question_id, answer_id, is_correct " +
                    "FROM \"question_answer\" WHERE answer_id = ?";

    /**
     * Gets QuestionAnswer from db by question_id.
     *
     * @param questionId
     * @return Collection of QuestionAnswer from database, or empty Collection if not found
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    protected Collection<QuestionAnswer> getQuestionAnswersByQuestionId (Long questionId) throws SQLException, ClassNotFoundException {

        return getList(GET_BY_QUESTION_ID_SQL, questionId);

    }

    /**
     * Gets QuestionAnswer from db by answer_id.
     *
     * @param answerId
     * @return Collection of QuestionAnswer from database, or empty Collection if not found
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    protected Collection<QuestionAnswer> getQuestionAnswersByAnswerId (Long answerId) throws SQLException, ClassNotFoundException{

        return getList(GET_BY_ANSWER_ID_SQL, answerId);

    }

    /**
     * {@inheritDoc}
     */
    protected Object parseResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {

        long id = rs.getLong(ID_COLUMN);
        long questionId = rs.getLong(QUESTION_ID_COLUMN);
        long answerId = rs.getLong(ANSWER_ID_COLUMN);
        boolean isCorrect = rs.getBoolean(IS_CORRECT_COLUMN);

        QuestionAnswer questionAnswer = new QuestionAnswer (id, questionId, answerId, isCorrect);

        return questionAnswer;
    }

    private Collection<QuestionAnswer> parseResultSetToList (ResultSet rs) throws SQLException, ClassNotFoundException {

        List<QuestionAnswer> list = new ArrayList<QuestionAnswer>();

        if(!rs.next()){
            return null;
        }

        while (rs.next()) {
            list.add((QuestionAnswer) parseResultSet(rs));
        }

        return list;
    }

    private Collection<QuestionAnswer> getList (String sql, Object... params) throws SQLException, ClassNotFoundException{

        ResultSet rs = executeQuery(sql, params);
        return parseResultSetToList(rs);

    }
}