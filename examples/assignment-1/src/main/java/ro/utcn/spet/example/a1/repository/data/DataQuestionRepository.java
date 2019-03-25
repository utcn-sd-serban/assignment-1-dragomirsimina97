package ro.utcn.spet.example.a1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.repository.QuestionRepository;

public interface DataQuestionRepository extends Repository<Question, Integer>, QuestionRepository{
    void delete(Question question);

    // needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).
    @Override
    default void remove(Question question) {
        delete(question);
    }
}
