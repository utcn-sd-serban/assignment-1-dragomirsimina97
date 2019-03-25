package ro.utcn.spet.example.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.spet.example.a1.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("text"));
}}
