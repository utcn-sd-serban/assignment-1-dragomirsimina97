package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.repository.TagRepository;

import java.util.*;

public class InMemoryTagRepository implements TagRepository {

    private int currentId=1;
    private final Map<Integer,Tag> data=new HashMap<>();
    @Override
    public Tag save(Tag tag) {
        if(tag.getId()!=0){
            data.put(tag.getId(),tag);
        } else {

            tag.setId(currentId++);
            data.put(tag.getId(),tag);
        }

        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return Optional.ofNullable(data.get(name));
    }

    @Override
    public void remove(Tag tag) {
        data.remove(tag.getId());
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(data.values());
    }
}
