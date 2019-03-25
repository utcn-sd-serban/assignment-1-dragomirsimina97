package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.repository.UserssRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserssRepository implements UserssRepository {
	// The Jdbc template is a helper class for doing JDBC operations (usually "templates" simplify common tasks)
	// check out https://spring.io/guides/gs/relational-data-access/
	private final JdbcTemplate template;

	@Override
	public List<Userss> findAll() {
		return template.query("SELECT * FROM userss", new UserssMapper());
	}

	@Override
	public Userss save(Userss userss) {
		if (userss.getId() == null) {
			userss.setId(insert(userss));
		} else {
			update(userss);
		}
		return userss;
	}

	@Override
	public void remove(Userss userss) {
		template.update("DELETE FROM userss WHERE id = ?", userss.getId());
	}

	@Override
	public Optional<Userss> findById(int id) {
		List<Userss> usersss = template.query("SELECT * FROM userss WHERE id = ?", new UserssMapper(), id);
		return usersss.isEmpty() ? Optional.empty() : Optional.of(usersss.get(0));
	}

	private int insert(Userss userss) {
		// we use the SimpleJdbcInsert to easily retrieve the generated ID
		SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
		insert.setTableName("userss");
		insert.usingGeneratedKeyColumns("id");
		Map<String, Object> map = new HashMap<>();
		map.put("name", userss.getName());
		map.put("password", userss.getPassword());

		return insert.executeAndReturnKey(map).intValue();
	}

	private void update(Userss userss) {
		template.update("UPDATE userss SET name=?, password = ? WHERE id = ?",
				userss.getName(), userss.getPassword(), userss.getId());
	}
}
