package pl.knpj.servlet.model;

import java.util.*;

/**
 * Question POJO
 */
public class Question {

    private Long id;
    private String title;
    private String description;
    private Collection<Answer> answers;

    public Question () {
    }

    public Question(Long id, String title, String description, Collection<Answer> answers) {
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

    public Collection<Answer> getAnswers() {
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

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\'Is empty\'");
        if(answers != null){
            builder.delete(0, builder.length());
            Iterator iterator = answers.iterator();
            if (iterator.hasNext()){
                builder.append(iterator.next().toString());
            }
            while (iterator.hasNext()) {
                builder.append(", ");
                builder.append(iterator.next().toString());
            }

        }
        return "Question{" +
                "id=" + id +
                ", title = '" + title + '\'' +
                ", description = '" + description + '\'' +
                ", answers = " + builder.toString() +
                '}';
    }
}