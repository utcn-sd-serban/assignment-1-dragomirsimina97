package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface QtagRepository {
    QTag save(QTag qtag);

    Optional<QTag> findById(int id);
    List <QTag> findByQId(int id_q);
    List <QTag> findByTId(int id_t);
    Optional <QTag> findByTAGSIQd(int id_t,int id_q);
    void remove(QTag qtag);

    List<QTag> findAll();
}
