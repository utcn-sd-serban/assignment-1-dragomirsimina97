package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagManagementService {
    private final RepositoryFactory repositoryFactory;
    @Transactional
    public List<Tag> TagList() {
        List<Tag> myQuestions = repositoryFactory.createTagRepository().findAll();
        Collections.reverse(myQuestions);

        return myQuestions;

    }



    @Transactional
    public Tag insert ( String name) {
        return repositoryFactory.createTagRepository().save(new Tag(name));
    }

    @Transactional
    public Optional <Tag> findidtq(String name) {
        Optional<Tag> myQuestions = repositoryFactory.createTagRepository().findByName(name);
        return myQuestions;

    }
}
