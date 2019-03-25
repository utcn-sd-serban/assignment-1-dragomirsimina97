package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QtagManagementService {
    private final RepositoryFactory repositoryFactory;

    // Transactional methods ensure that the code contained inside is run in a transaction, which is committed
    // when the methods returns and is rolled-back when an exception is thrown
    // http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
    @Transactional
    public List<QTag> qTagList() {
        List<QTag> myQuestions = repositoryFactory.createQtagRepository().findAll();
        Collections.reverse(myQuestions);

        return myQuestions;

    }



    @Transactional
    public QTag insert ( int id_q,int id_t) {
        return repositoryFactory.createQtagRepository().save(new QTag(id_q,id_t));
    }

    @Transactional
    public List<QTag> findidq(int id_t) {
        List<QTag> myQuestions = repositoryFactory.createQtagRepository().findByTId(id_t);
        return myQuestions;

    }

}
