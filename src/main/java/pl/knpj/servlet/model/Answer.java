package pl.knpj.servlet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Answer POJO
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "SELECT_ANSWER_BY_ID", query = "SELECT a FROM Answer a WHERE a.id = ?")
)
public class Answer implements Serializable {

    @Id
    private Long id;

    @Column(length = 500, nullable = false)
    private String text;

    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Question> questions;

    public Answer(){
    }

    public Answer(Long id, String text, List<Question> questions) {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", questions=" + questions +
                '}';
    }
}