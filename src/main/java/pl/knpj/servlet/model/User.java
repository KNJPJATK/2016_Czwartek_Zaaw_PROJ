package pl.knpj.servlet.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rpi on 24.11.16.
 */
@Entity
@Table(name = "vuser")
@NamedQueries(
        @NamedQuery(name = "GET_USER_BY_USERNAME", query = "SELECT u FROM User u WHERE u.username = ?")
)
public class User implements Serializable {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
