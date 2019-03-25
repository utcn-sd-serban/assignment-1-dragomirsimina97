package ro.utcn.spet.example.a1.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "DATA")
public class DataRepositoryFactory implements RepositoryFactory {
	private final DataUserssRepository userssRepository;
	private final DataQuestionRepository questionRepository;


	@Override
	public UserssRepository createUserssRepository() {
		return userssRepository;
	}
	@Override
	public QuestionRepository createQuestionRepository() {
		return questionRepository;
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
