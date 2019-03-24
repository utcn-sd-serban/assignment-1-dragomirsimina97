package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.exception.UserssNotFoundException;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.UserssRepository;

import java.util.List;
import java.util.Optional;

// The @Service is a specialized @Component (https://www.baeldung.com/spring-component-repository-service)
@Service
@RequiredArgsConstructor
public class UserssManagementService {
	private final RepositoryFactory repositoryFactory;

	// Transactional methods ensure that the code contained inside is run in a transaction, which is committed
	// when the methods returns and is rolled-back when an exception is thrown
	// http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
	@Transactional
	public List<Userss> listUserss() {
		return repositoryFactory.createUserssRepository().findAll();
	}

	@Transactional
	public Userss addUserss(String name, String password) {
		return repositoryFactory.createUserssRepository().save(new Userss(name, password));
	}

	@Transactional
	public Optional<Userss> findByName(String name, String password) {
		return repositoryFactory.createUserssRepository().findAll().stream().filter(
				(Userss s) -> s.getName().equals(name) && s.getPassword().equals(password)
		).findFirst();
	}

	@Transactional
	public void removeUserss(int id) {
		UserssRepository repository = repositoryFactory.createUserssRepository();
		Userss userss= repository.findById(id).orElseThrow(UserssNotFoundException::new);
		repository.remove(userss);
	}
}
