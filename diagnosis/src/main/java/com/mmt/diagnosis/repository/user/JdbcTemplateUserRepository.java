package com.mmt.diagnosis.repository.user;

import com.mmt.diagnosis.domain.User;
import com.mmt.diagnosis.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveUser(String id, String password, String name, String phone) {
        String sql = "INSERT INTO users(user_id, user_password, user_name, user_phone) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, password, name, phone);
    }

    @Override
    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> result = jdbcTemplate.query(sql, userRowMapper(), id);
        return Optional.empty();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setUserPassword(rs.getString("user_password"));
            user.setUserName(rs.getString("user_name"));
            user.setUserPhone(rs.getString("user_phone"));
            return user;
        };
    }
}