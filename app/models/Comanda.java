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
@Table(name = "tbl_comanda")
public class Comanda extends Model {

    @Id
    private long id;
    @Column(name = "data_abertura")
    private Date dataAbertura;
    @Column(name = "data_fechamento")
    private Date dataFechamento;
    @Column(name = "total")
    private float total;
    @Column(name = "aberta")
    private boolean aberta;
    @ManyToOne
    private Funcionario atendente;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Mesa mesa;
    @ManyToOne
    private Cortesia cortesia;
    @OneToMany
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public static Finder<Long, Comanda> find = new Finder<Long, Comanda>(Comanda.class);

    public static List<Comanda> all() {
        return find.all();
    }

    public static Comanda create(Comanda comanda) {
        comanda.save();
        return comanda;
    }

    public static Comanda update(Long id, Comanda c) {
        Comanda comanda = find.byId(id);

        if (c.getDataAbertura() != null) comanda.setDataAbertura(c.getDataAbertura());
        if (c.getDataFechamento() != null) comanda.setDataFechamento(c.getDataFechamento());
        if (c.isAberta()) comanda.setAberta(c.isAberta());
        if (c.getAtendente() != null) comanda.setAtendente(c.getAtendente());
        if (c.getCliente() != null) comanda.setCliente(c.getCliente());
        if (c.getMesa() != null) comanda.setMesa(c.getMesa());
        if (c.getCortesia() != null) comanda.setCortesia(c.getCortesia());

        return create(comanda);
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    //TODO A ENTIDADE QUE RECEBE UM ID ESTRANGEIRO DEVE MAPEA-LO, POR PRECAUÇÃO


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cortesia getCortesia() {
        return cortesia;
    }

    public void setCortesia(Cortesia cortesia) {
        this.cortesia = cortesia;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
