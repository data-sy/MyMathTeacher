package com.mmt.diagnosis.repository.scenario;

import com.mmt.diagnosis.domain.Scenario;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository
public class JdbcTemplateScenarioRepository implements ScenarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScenarioRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(List<Scenario> scenarioList) {
        String sql =  "INSERT INTO scenario (scenario_case, mom_id, skill_id, pro_list) VALUES (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Scenario scenario = scenarioList.get(i);
                ps.setInt(1, scenario.getScenarioCase());
                ps.setInt(2, scenario.getMomId());
                ps.setInt(3, scenario.getSkillId());
                ps.setString(4, scenario.getProList());
            }
            @Override
            public int getBatchSize() {
                return scenarioList.size();
            }
        });
    }

}
