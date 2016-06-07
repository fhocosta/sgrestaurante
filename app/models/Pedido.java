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
    @ManyToOne
    private Status status;
    @ManyToMany
    private List<Produto> produtos = new ArrayList<Produto>();
    @ManyToOne
    private Comanda comanda;

    public Pedido() {
    }

    public static Finder<Long, Pedido> find = new Finder<Long, Pedido>(Pedido.class);

    public static List<Pedido> all() {
        return find.all();
    }

    public static Pedido create(Pedido pedido) {
        pedido.save();
        return pedido;
    }

    public static Pedido update(Long id, Pedido p) {
        Pedido pedido = find.byId(id);

        if (p.status != null) pedido.setStatus(p.getStatus());
        if (p.getComanda() != null) pedido.setComanda(p.getComanda());

        return create(pedido);
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
}
