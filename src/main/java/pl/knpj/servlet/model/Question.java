package pl.knpj.servlet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Question POJO
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "SELECT_QUESTION_BY_TITLE", query = "SELECT q FROM Question q WHERE q.title = ?")
)
public class Question implements Serializable {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Lob
    private String description;

    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Answer> answers;

    public Question () {
    }

    public Question(Long id, String title, String description, List<Answer> answers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", answers=" + answers +
                '}';
    }
}