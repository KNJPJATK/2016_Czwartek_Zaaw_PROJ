package pl.knpj.servlet.model;

/**
 * Associative class between Question and Answer.
 */
public class QuestionAnswer {

    private long questionId;
    private long answerId;
    private boolean isCorrect;

    public QuestionAnswer() {
    }

    public QuestionAnswer(long questionId, long answerId, boolean isCorrect) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrect = isCorrect;
    }

    public long getQuestionId() {
        return questionId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "questionId=" + questionId +
                ", answerId=" + answerId +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
