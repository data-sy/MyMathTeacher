//package com.mmt.diagnosis.repository.answer;
//
//import com.mmt.diagnosis.domain.Answer;
//import com.mmt.diagnosis.domain.Student;
//import com.mmt.diagnosis.repository.AnswerRepository;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Repository
//@Primary
//public class JdbcTemplateAnswerRepository implements AnswerRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public JdbcTemplateAnswerRepository(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public void save(Long studentId, Long testId) {
//        String sql =  "INSERT INTO answers (student_id, test_id, item_id) SELECT ? AS student_id, test_id, item_id FROM tests_items WHERE test_id=?";
//        jdbcTemplate.update(sql, studentId, testId);
//        // Q. 한 번 실행 시킬 때 마다 answer_id 11개 날라감. why?????
//    }
//
//    @Override
//    public List<Answer> findByStudentId(Long studentId) {
//        // tests 테이블과 tests_items 테이블 둘 모두를 사용해야 하므로 각각 조인과 서브쿼리 사용함
//        // GROUP BY test_id HAVING min(item_id)는 재시험을 분류할 수 없으므로 안 됨
//        // 리팩토링 : 성능 더 좋은 다른 쿼리 있을지 고민해보자.
//        String subQuery = "SELECT ti.test_id, ti.item_id FROM tests_items ti WHERE ti.test_item_number=1";
//        String sql = String.format("SELECT a.answer_id, a.test_id, t.test_name, a.answer_code FROM answers a JOIN tests t ON t.test_id = a.test_id WHERE a.student_id=? AND (a.test_id, a.item_id) IN (%s)", subQuery);
//        return jdbcTemplate.query(sql, answerRowMapper(), studentId);
//    }
//
//    private RowMapper<Answer> answerRowMapper() {
//        return (rs, rowNum) -> {
//            Answer answer = new Answer();
//            answer.setAnswerId(rs.getLong("answer_id"));
//            answer.setTestId(rs.getLong("test_id"));
//            answer.setTestName(rs.getString("test_name"));
//            answer.setAnswerCode(rs.getObject("answer_code", Integer.class));
//            return answer;
//        };
//    }
//}
