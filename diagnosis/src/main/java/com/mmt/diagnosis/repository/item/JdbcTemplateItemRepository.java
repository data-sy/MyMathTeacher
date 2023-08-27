package com.mmt.diagnosis.repository.item;

import com.mmt.diagnosis.domain.TestItems;
import com.mmt.diagnosis.repository.ItemRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Primary
public class JdbcTemplateItemRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<TestItems> findItems(List<Long> itemIdList) {
        String inSql = String.join(",", Collections.nCopies(itemIdList.size(), "?"));
        String sql = String.format("SELECT item_id, item_answer, item_image_path FROM items WHERE item_id IN (%s)", inSql);
        return jdbcTemplate.query(sql, itemsRowMapper(), itemIdList.toArray());
    }

    private RowMapper<TestItems> itemsRowMapper() {
        return (rs, rowNum) -> {
            TestItems testItems = new TestItems();
            testItems.setItemId(rs.getLong("item_id"));
            testItems.setItemAnswer(rs.getString("item_answer"));
            testItems.setItemImagePath(rs.getString("item_image_path"));
            return testItems;
        };
    }

}
