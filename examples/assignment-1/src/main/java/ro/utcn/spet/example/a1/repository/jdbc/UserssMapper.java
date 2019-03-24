package ro.utcn.spet.example.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.spet.example.a1.entity.Userss;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserssMapper implements RowMapper<Userss> {

	@Override
	public Userss mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Userss(rs.getInt("id"),
				rs.getString("name"),
				rs.getString("password"));
	}

}
