package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.smartcardio.Card;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_cardapio")
public class Cardapio extends Model {

    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "disponivel")
    private boolean disponivel;
    @Column(name = "data")
    private Date data;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Produto> produtos = new ArrayList<Produto>();

    public Cardapio() {
    }

    public static Finder<Long, Cardapio> find = new Finder<Long, Cardapio>(Cardapio.class);

    public static List<Cardapio> all() {
        return find.all();
    }

    public static Cardapio create(Map<String, String> form) {
        Cardapio cardapio = new Cardapio();

        if(form.get("nome") != null){
            cardapio.setNome(form.get("nome"));
        }

        if(form.get("descricao") != null){
            cardapio.setDescricao(form.get("descricao"));
        }

        if(form.get("disponivel") != null){
            cardapio.setDisponivel(Boolean.parseBoolean(form.get("disponivel")));
        }

        if(form.get("data") != null){
            String string = form.get("data");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) cardapio.setData(date);
        }

        if(form.get("produto") != null){
            Long id = Long.parseLong(form.get("produto"));
            Produto produto = Produto.find.byId(id);
            if (produto != null){
                List<Produto> produtos = new ArrayList<Produto>();
                produtos.add(produto);
                cardapio.setProdutos(produtos);
            }
        }

        return create(cardapio);
    }

    public static Cardapio create(Cardapio cardapio) {
        cardapio.save();
        return cardapio;
    }

    public static Cardapio update(Long id, Cardapio c) {
        Cardapio cardapio = find.byId(id);

        if (c.getNome() != null) cardapio.setNome(c.getNome());
        if (c.getDescricao() != null) cardapio.setDescricao(c.getDescricao());
        if (c.isDisponivel()) cardapio.setDisponivel(c.isDisponivel());
        if (c.getData() != null) cardapio.setData(c.getData());

        return create(cardapio);
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


}
