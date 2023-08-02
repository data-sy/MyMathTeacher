package com.mmt.diagnosis.repository.testItem;

import com.mmt.diagnosis.domain.TestItems;
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
    public List<TestItems> findTestItems(Long testId){
        String sql = "SELECT ti.test_item_number, i.item_image_path, i.item_answer FROM tests_items ti JOIN items i ON ti.item_id = i.item_id WHERE ti.test_id = ?";

        return jdbcTemplate.query(sql, testItemsRowMapper(), testId);
    }

    private RowMapper<TestItems> testItemsRowMapper() {
        return (rs, rowNum) -> {
            TestItems testItems = new TestItems();
            testItems.setTestItemNumber(rs.getInt("test_item_number"));
            testItems.setItemImagePath(rs.getString("item_image_path"));
            testItems.setItemAnswer(rs.getString("item_answer"));
            return testItems;
        };
    }

}
