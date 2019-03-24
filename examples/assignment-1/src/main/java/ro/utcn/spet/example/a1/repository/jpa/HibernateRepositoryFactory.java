package ro.utcn.spet.example.a1.repository.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.repository.*;
import ro.utcn.spet.example.a1.repository.memory.InMemoryQuestionRepository;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JPA")
public class HibernateRepositoryFactory implements RepositoryFactory {
	private final EntityManager entityManager;
	private final EntityManager entityManager2;


	@Override
	public UserssRepository createUserssRepository() {
		return new HibernateUserssRepository(entityManager);
	}

	@Override
	public QuestionRepository createQuestionRepository(){return new HibernateQuestionRepository(entityManager2);
	}

	@Override
	public TagRepository createTagRepository() {
		return null;
	}

	@Override
	public QtagRepository createQtagRepository() {
		return null;
	}
}
