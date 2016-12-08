package pl.knpj.servlet.model;

import java.util.*;

/**
 * Question POJO
 */
public class Question {

    private Long id;
    private String title;
    private String description;
    private Collection<Answer> allAnswers;
    private Collection<Answer> correctAnswers;

    public Question () {
    }

    public Question(Long id, String title, String description, Collection<Answer> allAnswers, Collection<Answer> correctAnswers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.allAnswers = allAnswers;
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

    public Collection<Answer> getAllAnswers() {
        return allAnswers;
    }

    public Collection<Answer> getCorrectAnswers() {
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

    public void setAllAnswers(Collection<Answer> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public void setCorrectAnswers(Collection<Answer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {

        StringBuilder buildAllAnswers = new StringBuilder("\'Is empty\'");
        if(allAnswers != null &&
                ! allAnswers.isEmpty())
        {
            buildAllAnswers.delete(0, buildAllAnswers.length());
            Iterator allAnswersIterator = allAnswers.iterator();
            if (allAnswersIterator.hasNext()){
                buildAllAnswers.append(allAnswersIterator.next().toString());
            }
            while (allAnswersIterator.hasNext()) {
                buildAllAnswers.append(", ");
                buildAllAnswers.append(allAnswersIterator.next().toString());
            }
        }

        StringBuilder buildCorrectAnswers = new StringBuilder("\'Is empty\'");
        if(correctAnswers != null &&
                ! correctAnswers.isEmpty())
        {
            buildCorrectAnswers.delete(0, buildCorrectAnswers.length());
            Iterator correctAnswersIterator = correctAnswers.iterator();
            if (correctAnswersIterator.hasNext()){
                buildCorrectAnswers.append(correctAnswersIterator.next().toString());
            }
            while (correctAnswersIterator.hasNext()) {
                buildCorrectAnswers.append(", ");
                buildCorrectAnswers.append(correctAnswersIterator.next().toString());
            }
        }

        return "Question{" +
                "id=" + id +
                ", title = '" + title + '\'' +
                ", description = '" + description + '\'' +
                ", allAnswers : " + buildAllAnswers.toString() +
                ", correctAnswers : " + buildCorrectAnswers.toString() +
                '}';
    }
}