package pl.knpj.servlet.model;

import java.util.*;

/**
 * Created by vadikms on 29.11.16.
 */
public class Question {
    private Long id;
    private String title;
    private String description;
    private Collection<Answer> correctAnswers;

    public Question(Long id, String title, String description, Collection<Answer> correctAnswers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.correctAnswers = correctAnswers;
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

    public Collection<Answer> getCorrectAnswer() {
        return correctAnswers;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Iterator iterator = correctAnswers.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString()).append(" ");
        }
        return "Question{" +
                "id=" + id +
                ", title = '" + title + '\'' +
                ", description = '" + description + '\'' +
                ", correctAnswers = " + builder.toString() +
                '}';
    }
}