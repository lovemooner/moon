package love.moon.hibernate;

import javax.persistence.*;

/**
 * Author: lovemooner
 * Date: 2018/7/15 1:45
 */
@Entity
@Table(name = "CA_USER")
public class CaUser {

    private Long id;
    private String userName;
    private Long version;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}