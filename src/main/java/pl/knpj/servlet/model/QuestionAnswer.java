package pl.knpj.servlet.model;

import java.io.Serializable;

/**
 * Associative class between Question and Answer.
 */
public class QuestionAnswer implements Serializable {

    private Long id;
    private Long questionId;
    private Long answerId;
    private boolean isCorrect;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Long id, Long questionId, Long answerId, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
