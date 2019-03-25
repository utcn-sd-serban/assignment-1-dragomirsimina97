package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Tag save(Tag tag);

    Optional <Tag> findById(int id);
    Optional <Tag> findByName(String name);
    void remove(Tag tag);

    List<Tag> findAll();
}
