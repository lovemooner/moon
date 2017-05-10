package love.moon.spring.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nadong on 2017/4/20.
 */
@Entity
@Table(name="user")
public class User implements Serializable {


    private int id;
    private String username;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}