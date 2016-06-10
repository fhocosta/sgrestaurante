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
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "disponivel")
    private boolean disponivel;
    @Column(name = "valor")
    private float valor;
    @ManyToMany
    private List<Cardapio> cardapios = new ArrayList<Cardapio>();
    @ManyToMany
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public Produto() {
    }

    public static Finder<Long, Produto> find = new Finder<Long, Produto>(Produto.class);

    public static List<Produto> all() {
        return find.all();
    }

    public static Produto create(Produto produto) {
        produto.save();
        return produto;
    }

    public static Produto update(Long id, Produto p) {
        Produto produto = find.byId(id);

        if (p.getNome() != null) produto.setNome(p.getNome());
        if (p.getDescricao() != null) produto.setDescricao(p.getDescricao());
        if (p.isDisponivel()) produto.setDisponivel(p.isDisponivel());
        if (p.valor != 0) produto.setValor(p.getValor());

        return create(produto);

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public List<Cardapio> getCardapios() {
        return cardapios;
    }

    public void setCardapios(List<Cardapio> cardapios) {
        this.cardapios = cardapios;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
