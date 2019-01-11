package love.moon.spring.template;

import love.moon.spring.model.User;
import love.moon.spring.template.service.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class AppMain2 {



    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://slc11fsp.us.oracle.com:3306/bigdata");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql="select * from ca_user where id=?";
        User user= jdbcTemplate.queryForObject(sql, new UserRowMapper(), 1000);
        System.out.println(user.getUserName());
    }

}
