package love.moon.spring.template.service;

import love.moon.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void testExecute() {
        jdbcTemplate.execute("select * from ld_cart");
    }

    public User getUser(Long id){
        String sql="select * from ca_user where id=?";
               return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

}
