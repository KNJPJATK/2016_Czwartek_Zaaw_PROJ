package pl.knpj.servlet.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Answer POJO
 */
public class Answer implements Serializable {

    private Long id;
    private String text;
    private Collection<QuestionAnswer> questions;

    public Answer(){
    }

    public Answer(Long id, String text, Collection<QuestionAnswer> questions) {
        this.id = id;
        this.text = text;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Collection<QuestionAnswer> getQuestions() {
        return questions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setQuestions(Collection<QuestionAnswer> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {

        StringBuilder buildQuestions = new StringBuilder("\'Is empty\'");
        if(questions != null &&
                ! questions.isEmpty())
        {
            buildQuestions.delete(0, buildQuestions.length());

            for(QuestionAnswer questionAnswer : questions){
                buildQuestions.append(questionAnswer.toString());
                buildQuestions.append(", ");
            }

            buildQuestions.delete(buildQuestions.length() - 2, buildQuestions.length());
        }

        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                " questions: " + buildQuestions.toString() +
                '}';
    }
}