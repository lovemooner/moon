package love.moon.spring.model;


import love.moon.spring.model.base.LongIdentifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by nadong on 2017/4/20.
 */
@Entity
@Table(name = "CA_USER")
public class User extends LongIdentifier{


    private String userName;

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}