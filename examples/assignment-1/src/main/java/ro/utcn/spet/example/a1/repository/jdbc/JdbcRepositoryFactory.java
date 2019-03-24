package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {
	private final JdbcTemplate template;

	@Override
	public UserssRepository createUserssRepository() {
		return new JdbcUserssRepository(template);
	}

	public QuestionRepository createQuestionRepository() {return new JdbcQuestionRepository(template);}
	public TagRepository createTagRepository() {return new JdbcTagRepository(template);}
	public QtagRepository createQtagRepository() {return new JdbcQTagRepository(template);}
}
