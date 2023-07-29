package com.mmt.diagnosis.repository.testItem;

import com.mmt.diagnosis.dto.testitem.TestItemData;
import com.mmt.diagnosis.dto.testitem.TestItemResponse;
import com.mmt.diagnosis.repository.TestItemRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
public class JdbcTemplateTestItemRepository implements TestItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTestItemRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TestItemResponse> findByTestId(Long testId){
        String sql = "SELECT ti.test_item_number, i.item_image_path FROM tests_items ti JOIN items i ON ti.item_id = i.item_id WHERE ti.test_id = ?";
        return jdbcTemplate.query(sql, testitemRowMapper(), testId);
    }

    @Override
    public List<TestItemData> findDataList(Long testId) {
        String sql = "SELECT ti.test_item_number, i.item_answer, i.item_image_path FROM tests_items ti JOIN items i ON ti.item_id = i.item_id WHERE ti.test_id = ?";
        return jdbcTemplate.query(sql, dataRowMapper(), testId);
    }

    private RowMapper<TestItemResponse> testitemRowMapper() {
        return (rs, rowNum) -> {
            TestItemResponse testItemResponse = new TestItemResponse();
            testItemResponse.setTestItemNumber(rs.getInt("test_item_number"));
            testItemResponse.setItemImagePath(rs.getString("item_image_path"));
            return testItemResponse;
        };
    }
    private RowMapper<TestItemData> dataRowMapper() {
        return (rs, rowNum) -> {
            TestItemData testItemData = new TestItemData();
            testItemData.setTestItemNumber(rs.getInt("test_item_number"));
            testItemData.setItemAnswer(rs.getString("item_answer"));
            testItemData.setItemImagePath(rs.getString("item_image_path"));
            return testItemData;
        };
    }
}
