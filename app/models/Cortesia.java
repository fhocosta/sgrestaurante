package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */

@Entity
@Table(name = "tbl_cortesia")
public class Cortesia extends Model {

    @Id
    private long id;
    @Column(name = "quantidade")
    private int quantidade;
    @Column(name = "disponibilidade")
    private Date disponibilidade;
    @ManyToOne
    private Produto produto;
    @OneToMany
    private List<Comanda> comandas = new ArrayList<Comanda>();

    public Cortesia() {
    }

    public static Finder<Long, Cortesia> find = new Finder<Long, Cortesia>(Cortesia.class);

    public static List<Cortesia> all() {
        return find.all();
    }

    public static Cortesia create(Cortesia cortesia) {
        cortesia.save();
        return cortesia;
    }

    public static Cortesia update(Long id, Cortesia c) {
        Cortesia cortesia = find.byId(id);

        if (c.getQuantidade() != 0) cortesia.setQuantidade(c.getQuantidade());
        if (c.getQuantidade() != 0) cortesia.setDisponibilidade(c.getDisponibilidade());
        if (c.getProduto() != null) cortesia.setProduto(c.getProduto());

        return create(cortesia);
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Date disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
