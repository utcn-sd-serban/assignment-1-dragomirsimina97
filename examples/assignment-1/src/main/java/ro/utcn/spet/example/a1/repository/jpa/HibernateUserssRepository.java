package ro.utcn.spet.example.a1.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.repository.UserssRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateUserssRepository implements UserssRepository {
	private final EntityManager entityManager;

	@Override
	public List<Userss> findAll() {
		// the criteria builder is used to create a type-safe query; an alternative would have been
		// to write a JPQL query instead ("SELECT s FROM Student s") or to use named queries
		// https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Userss> query = builder.createQuery(Userss.class);
		query.select(query.from(Userss.class));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public Userss save(Userss userss) {
		if (userss.getId() == null) {
			entityManager.persist(userss);
			return userss;
		} else {
			return entityManager.merge(userss);
		}
	}

	@Override
	public void remove(Userss userss) {
		entityManager.remove(userss);
	}

	@Override
	public Optional<Userss> findById(int id) {
		return Optional.ofNullable(entityManager.find(Userss.class, id));
	}
}
