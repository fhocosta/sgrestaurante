package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_pedido")
public class Pedido extends Model {

    @Id
    private long id;
    @OneToOne //TODO OnToMany ????
    private Status status;
    @OneToMany //TODO ManyToMany ???
    private List<Produto> produtos = new ArrayList<Produto>();

    public Pedido() {
    }

    public static Finder<Long, Pedido> find = new Finder<Long, Pedido>(Pedido.class);
}
