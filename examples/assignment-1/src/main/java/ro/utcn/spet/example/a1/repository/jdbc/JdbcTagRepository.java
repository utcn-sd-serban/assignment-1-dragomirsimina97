package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.Tag;

import ro.utcn.spet.example.a1.repository.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {
    private final JdbcTemplate template;
    @Override
    public Tag save(Tag tag) {
        if(tag.getId() !=0){
            update(tag);
        } else{
            tag.setId(insert (tag));

        }

        return tag ;
    }

    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> tags=template.query("SELECT * FROM tagi WHERE id=?",
                new Object[]{id},(resultSet,i)->new Tag(resultSet.getInt("id"),resultSet.getString("name")));

        return tags.isEmpty()? Optional.empty():Optional.of(tags.get(0));
    }

    @Override
    public Optional<Tag> findByName(String name) {

            List<Tag> tags=template.query("SELECT * FROM tagi WHERE name=?",
                    new Object[]{name},(resultSet,i)->new Tag(resultSet.getInt("id"),resultSet.getString("name")));

            return tags.isEmpty()? Optional.empty():Optional.of(tags.get(0));
    }

    @Override
    public void remove(Tag tag){
        template.update("DELETE FROM tagi WHERE id=?",tag.getId());
    }
    @Override
    public List<Tag> findAll() {
            return template.query("SELECT * FROM tagi",(resultSet,i)->new Tag(resultSet.getInt("id"),resultSet.getString("name")));
    }
    private int insert(Tag tag) {
        // we use the SimpleJdbcInsert to easily retrieve the generated ID
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tagi");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("name", tag.getName());


        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Tag tag) {
        template.update("UPDATE tagi SET name=? WHERE id = ?",
                tag.getName(), tag.getId());
    }
}
