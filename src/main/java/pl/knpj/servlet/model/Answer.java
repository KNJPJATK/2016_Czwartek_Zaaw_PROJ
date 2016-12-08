package pl.knpj.servlet.model;

/**
 * Answer POJO
 */
public class Answer {

    private Long id;
    private String text;

    public Answer(){
    }

    public Answer (Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}