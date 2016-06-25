package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private Produto produto;

    public Cortesia() {
    }

    public static Finder<Long, Cortesia> find = new Finder<Long, Cortesia>(Cortesia.class);

    public static List<Cortesia> all() {
        return find.all();
    }

    public static Cortesia create(Map<String, String> form) {
        Cortesia cortesia = new Cortesia();

        if(form.get("produto") != null){
            Long id = Long.parseLong(form.get("produto"));
            Produto produto = Produto.find.byId(id);
            if(produto != null){
                cortesia.setProduto(produto);
            }
        }

        if(form.get("quantidade") != null){
            cortesia.setQuantidade(Integer.parseInt(form.get("quantidade")));
        }

        if(form.get("disponibilidade") != null){
            String string = form.get("disponibilidade");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) cortesia.setDisponibilidade(date);
        }

        return create(cortesia);
    }

    public static Cortesia create(Cortesia cortesia) {
        cortesia.save();
        return cortesia;
    }

    public static Cortesia update(Long id, Map<String, String> form) {
        Cortesia cortesia = find.byId(id);

        if(form.get("produto") != null){
            Long idProduto = Long.parseLong(form.get("produto"));
            Produto produto = Produto.find.byId(idProduto);
            if(produto != null){
                cortesia.setProduto(produto);
            }
        }

        if(form.get("quantidade") != null){
            cortesia.setQuantidade(Integer.parseInt(form.get("quantidade")));
        }


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
}
