package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.Question;

import java.util.List;

/**
 * DAO class for retrieving data from question table
 */
public class QuestionDAO extends BaseDAO {

    public Question getQuestionByTitle(String title) {
        List<Question> questions = executeNamedQuery("SELECT_QUESTION_BY_TITLE", Question.class, title);
        if (questions.size() != 1) {
            return null;
        }
        return questions.get(0);
    }

}
