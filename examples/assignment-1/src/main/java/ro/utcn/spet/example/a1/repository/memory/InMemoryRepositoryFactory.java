package ro.utcn.spet.example.a1.repository.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.repository.*;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {
	private final InMemoryUserssRepository repository = new InMemoryUserssRepository();
	private final InMemoryQuestionRepository repository1= new InMemoryQuestionRepository();
	private final InMemoryQtagRepository repository3= new InMemoryQtagRepository();
	private final InMemoryTagRepository repository4= new InMemoryTagRepository();
	@Override
	public UserssRepository createUserssRepository() {
		return repository;
	}
	@Override
	public QuestionRepository createQuestionRepository(){return repository1;}

	@Override
	public TagRepository createTagRepository() {
		return repository4;
	}

	@Override
	public QtagRepository createQtagRepository() {
		return repository3;
	}


}
