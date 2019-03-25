package ro.utcn.spet.example.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.repository.*;

@Component
@RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserssSeed implements CommandLineRunner {
	private final RepositoryFactory factory;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		UserssRepository repository = factory.createUserssRepository();
		if (repository.findAll().isEmpty()) {
			repository.save(new Userss( "B", "B"));
			repository.save(new Userss( "D", "D"));
			repository.save(new Userss( "F", "F"));
		}

		QuestionRepository questionRepository = factory.createQuestionRepository();
		if (questionRepository.findAll().isEmpty()) {
			questionRepository.save(new Question("Fixing database conection", "you can fix it by yourself"));
			questionRepository.save(new Question( "Fixing intelij error", "C.D@example.com"));
			questionRepository.save(new Question( "Fixing basic errors", "E.F@example.com"));
		}
		TagRepository tagRepository = factory.createTagRepository();
		if (tagRepository.findAll().isEmpty()) {
			tagRepository.save(new Tag("dap"));
			tagRepository.save(new Tag( "nope"));
			tagRepository.save(new Tag( "silvia"));
		}
		QtagRepository QtagRepository = factory.createQtagRepository();
		if (QtagRepository.findAll().isEmpty()) {
			QtagRepository.save(new QTag(1,2));
			QtagRepository.save(new QTag( 2,3));
			QtagRepository.save(new QTag( 3,4));
		}
	}
}
