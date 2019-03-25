package ro.utcn.spet.example.a1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.repository.UserssRepository;

// Spring Data JPA automatically implements this interface (because it extends the Repository interface)
// and generates the queries based on the method names
// check out https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts
public interface DataUserssRepository extends Repository<Userss, Integer>, UserssRepository {

	void delete(Userss userss);

	// needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).
	@Override
	default void remove(Userss userss) {
		delete(userss);
	}
}
