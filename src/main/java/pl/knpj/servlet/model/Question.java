package pl.knpj.servlet.model;

import java.io.Serializable;
import java.util.*;

/**
 * Question POJO
 */
public class Question implements Serializable {

    private Long id;
    private String title;
    private String description;
    private Collection<QuestionAnswer> answers;

    public Question () {
    }

    public Question(Long id, String title, String description, Collection<QuestionAnswer> answers) {
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

    public Collection<QuestionAnswer> getAnswers() {
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

    public void setAnswers(Collection<QuestionAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {

        StringBuilder buildAnswers = new StringBuilder("\'Is empty\'");
        if(answers != null &&
                ! answers.isEmpty())
        {
            buildAnswers.delete(0, buildAnswers.length());

            for (QuestionAnswer questionAnswer : answers) {
                buildAnswers.append(questionAnswer.toString());
                buildAnswers.append(", ");
            }

            buildAnswers.delete(buildAnswers.length() - 2, buildAnswers.length());
        }

        return "Question{" +
                "id=" + id +
                ", title = '" + title + '\'' +
                ", description = '" + description + '\'' +
                ", answers : " + buildAnswers.toString() +
                '}';
    }
}