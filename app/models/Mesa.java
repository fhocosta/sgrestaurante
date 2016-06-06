package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_mesa")
public class Mesa extends Model{

    @Id
    private long id;
    @Column(name = "numero")
    private int numero;
    @Column(name = "lugares")
    private int lugares;
    @OneToMany
    private List<Comanda> comandas = new ArrayList<Comanda>();

    public Mesa() {
    }

    public static Finder<Long, Mesa> find = new Finder<Long, Mesa>(Mesa.class);
}
