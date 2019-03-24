package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.repository.QtagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
public class JdbcQTagRepository implements QtagRepository {
    private final JdbcTemplate template;



    @Override
    public QTag save(QTag qtag) {
        if(qtag.getId() !=0){
            update(qtag);
        } else{
            qtag.setId(insert (qtag));

        }

        return qtag ;

    }

    @Override
    public Optional<QTag> findById(int id) {
        List<QTag> qtags=template.query("SELECT * FROM qtag WHERE id=?",
                new Object[]{id},(resultSet,i)->new QTag(resultSet.getInt("id"),resultSet.getInt("id_q"),resultSet.getInt("id_t")));

        return qtags.isEmpty()? Optional.empty():Optional.of(qtags.get(0));
    }

    @Override
    public List<QTag> findByQId(int id_q) {

            List<QTag> qtags=template.query("SELECT * FROM qtag WHERE id_q=?",
                    new Object[]{id_q},(resultSet,i)->new QTag(resultSet.getInt("id"),resultSet.getInt("id_q"),resultSet.getInt("id_t")));

            return qtags;
    }

    @Override
    public List<QTag> findByTId(int id_t) {
        List<QTag> qtags=template.query("SELECT * FROM qtag WHERE id_t=?",
                new Object[]{id_t},(resultSet,i)->new QTag(resultSet.getInt("id"),resultSet.getInt("id_q"),resultSet.getInt("id_t")));

        return qtags;
    }
    @Override
    public List <QTag> findAll() {
        return template.query("SELECT * FROM qtag",(resultSet,i)->new QTag(resultSet.getInt("id"),resultSet.getInt("id_q"),resultSet.getInt("id_t")));
    }
    @Override
    public Optional<QTag> findByTAGSIQd(int id_t, int id_q) {
     return   findAll().stream().filter((QTag qtags)->qtags.getId_t() == id_t && qtags.getId_q()==id_q).findFirst();

    }

    @Override
    public void remove(QTag qtag){
        template.update("DELETE FROM qtag WHERE id=?",qtag.getId());
    }

    private int insert(QTag qtag) {
        // we use the SimpleJdbcInsert to easily retrieve the generated ID
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tagi");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("id_q", qtag.getId_q());
        map.put("id_t", qtag.getId_t());


        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(QTag qtag) {
        template.update("UPDATE qtag SET id_q=?, id_t=? WHERE id = ?",
                qtag.getId_q(),qtag.getId_t(), qtag.getId());
    }
}
