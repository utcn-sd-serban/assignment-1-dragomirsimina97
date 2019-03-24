package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.repository.QtagRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryQtagRepository implements QtagRepository {
    private int currentId=1;
    private final Map<Integer,QTag> data=new HashMap<>();
    @Override
    public synchronized QTag save (QTag qtag){
        if(qtag.getId()!=0){
            data.put(qtag.getId(),qtag);
        } else {

            qtag.setId(currentId++);
            data.put(qtag.getId(),qtag);
        }

        return qtag;
    }

    @Override
    public Optional <QTag> findById(int id){

        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<QTag> findByQId(int id_q) {
        return data.values().stream().filter((QTag qtag)->qtag.getId_q()==id_q).collect(Collectors.toList());
    }

    @Override
    public List <QTag> findByTId(int id_t) {
        return data.values().stream().filter((QTag qtag)->qtag.getId_t()==id_t).collect(Collectors.toList());
    }

    @Override
    public synchronized void remove (QTag qtag){
        data.remove(qtag.getId());
    }

    @Override
    public List<QTag> findAll(){
        return new ArrayList<>(data.values());
    }
    @Override
    public Optional<QTag> findByTAGSIQd(int id_t, int id_q) {
        return   findAll().stream().filter((QTag qtags)->qtags.getId_t() == id_t && qtags.getId_q()==id_q).findFirst();

    }
}
