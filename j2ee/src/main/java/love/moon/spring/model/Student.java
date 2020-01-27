package love.moon.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: lovemooner
 * Date: 2017/5/23 17:32
 */
@Entity
@Table(name = "NAN_STUDENT")
public class Student {

    @Id
    private int id;
    private String name;

    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
