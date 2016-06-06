package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_cardapio")
public class Cardapio extends Model{

    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @ManyToMany
    private List<Produto> produtos = new ArrayList<Produto>();

    public Cardapio() {
    }

    public static Finder<Long, Cardapio> find = new Finder<Long, Cardapio>(Cardapio.class);
}
