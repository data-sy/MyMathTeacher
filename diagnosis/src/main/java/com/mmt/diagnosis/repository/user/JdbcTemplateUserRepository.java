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
    public void save(String id, String password, String name, String phone) {
        String sql = "INSERT INTO users(user_id, user_password, user_name, user_phone) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, password, name, phone);
    }


}
