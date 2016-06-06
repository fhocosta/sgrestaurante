package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fhocosta on 06/06/16.
 */

@Entity
@Table(name = "tbl_cortesia")
public class Cortesia extends Model{

    @Id
    private long id;
    @Column(name = "quantidade")
    private int quantidade;
    @Column(name = "disponibilidade")
    private Date disponibilidade;
    @OneToOne
    private Produto produto;

    public Cortesia() {
    }

    public static Finder<Long, Cortesia> find = new Finder<Long, Cortesia>(Cortesia.class);
}
