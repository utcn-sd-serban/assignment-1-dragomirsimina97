package ro.utcn.spet.example.a1.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.spet.example.a1.entity.Question;

import ro.utcn.spet.example.a1.repository.QuestionRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class HibernateQuestionRepository implements QuestionRepository{
    private final EntityManager entityManager;
    @Override
    public Question save(Question question) {
        if (question.getId() == 0) {
            entityManager.persist(question);
            return question;
        } else {
            return entityManager.merge(question);
        }
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(entityManager.find(Question.class, id));
    }
    @Override
    public void remove(Question question) {
        entityManager.remove(question);
    }

    @Override

    public List<Question> findAll() {
        // the criteria builder is used to create a type-safe query; an alternative would have been
        // to write a JPQL query instead ("SELECT s FROM Student s") or to use named queries
        // https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        query.select(query.from(Question.class));
        return entityManager.createQuery(query).getResultList();
    }

}
