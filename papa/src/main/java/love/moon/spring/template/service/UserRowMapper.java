package love.moon.spring.template.service;

import love.moon.spring.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
// rs 已经指向每一条数据，不需要自己调用 next，将rs指向数据 转换 User对象
        User user = new User();
        user.setUserName(rs.getString("user_name"));
        return user;
    }
}