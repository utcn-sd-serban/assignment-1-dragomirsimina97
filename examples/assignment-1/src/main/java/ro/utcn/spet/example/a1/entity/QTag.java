package ro.utcn.spet.example.a1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="qtag")
public class QTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_q;
    private int id_t;


    public QTag(int id_q,int id_t) {
        this.id_q = id_q;
        this.id_t = id_t;

    }
}
