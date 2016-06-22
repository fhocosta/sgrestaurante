package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_pedido")
public class Pedido extends Model {

    @Id
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Status status;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<Produto>();
    @ManyToOne(cascade = CascadeType.ALL)
    private Comanda comanda;

    public Pedido() {
    }

    public static Finder<Long, Pedido> find = new Finder<Long, Pedido>(Pedido.class);

    public static List<Pedido> all() {
        return find.all();
    }

    public static Pedido create(Map<String, String> form) {

        Pedido pedido = new Pedido();

        if(form.get("status") != null){
            Long id = Long.parseLong(form.get("status"));
            Status status = Status.find.byId(id);
            if(status != null){
                pedido.setStatus(status);
            }
        }

        List<Produto> produtos = new ArrayList<Produto>();

        if(form.get("produto1") != null){
            Long id = Long.parseLong(form.get("produto1"));
            Produto produto = Produto.find.byId(id);
            if(produto != null){
                produtos.add(produto);
            }
        }

        if(form.get("produto2") != null && !form.get("produto2").isEmpty()){
            Long id = Long.parseLong(form.get("produto2"));
            Produto produto = Produto.find.byId(id);
            if(produto != null){
                produtos.add(produto);
            }
        }

        pedido.setProdutos(produtos);

        if(form.get("comanda") != null){
            Long id = Long.parseLong(form.get("comanda"));
            Comanda comanda = Comanda.find.byId(id);
            if(comanda != null){
                pedido.setComanda(comanda);
            }
        }

        return create(pedido);
    }

    public static Pedido create(Pedido pedido) {
        pedido.save();
        return pedido;
    }

    public static Pedido update(Long id, Map<String, String> form) {
        Pedido pedido = find.byId(id);

        if(form.get("status") != null){
            Long idStatus = Long.parseLong(form.get("idStatus"));
            Status status = Status.find.byId(id);
            if(status != null){
                pedido.setStatus(status);
            }
        }

        List<Produto> produtos = new ArrayList<Produto>();

        if(form.get("produto1") != null){
            Long idProduto1 = Long.parseLong(form.get("produto1"));
            Produto produto = Produto.find.byId(idProduto1);
            if(produto != null){
                produtos.add(produto);
            }
        }

        if(form.get("produto2") != null){
            Long idProduto2 = Long.parseLong(form.get("produto2"));
            Produto produto = Produto.find.byId(idProduto2);
            if(produto != null){
                produtos.add(produto);
            }
        }

        pedido.setProdutos(produtos);

        if(form.get("comanda") != null){
            Long idComanda = Long.parseLong(form.get("comanda"));
            Comanda comanda = Comanda.find.byId(idComanda);
            if(comanda != null){
                pedido.setComanda(comanda);
            }
        }

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
    public String produtosToString(){
        String string = "";
        for (Produto produto: produtos){
            if(!string.isEmpty()){
                string += ",";
            }
            string += produto.getNome() + " ";
        }
        return string;
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
