package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_produto")
public class Produto extends Model {

    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name ="descricao")
    private String descricao;
    @Column(name = "disponivel")
    private boolean disponivel;
    @Column(name = "valor")
    private float valor;
    @ManyToMany
    private List<Cardapio> cardapios = new ArrayList<Cardapio>();

    public Produto() {
    }

    public static Finder<Long, Produto> find = new Finder<Long, Produto>(Produto.class);
}
