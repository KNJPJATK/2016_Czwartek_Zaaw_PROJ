package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.Answer;

import java.util.List;

/**
 * DAO class for retrieving data from answer table
 */
public class AnswerDAO extends BaseDAO {

    public Answer getAnswerById(Long id) {
        List<Answer> answers = executeNamedQuery("SELECT_ANSWER_BY_ID", Answer.class, id);
        if (answers.size() != 1) {
            return null;
        }
        return answers.get(0);
    }

}
