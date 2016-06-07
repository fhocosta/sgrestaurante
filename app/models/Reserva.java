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
@Table(name = "tbl_reserva")
public class Reserva extends Model {

    @Id
    private long id;
    @OneToOne
    private Cliente cliente;
    @ManyToOne
    private Funcionario atendente;
    @Column(name = "data")
    private Date data;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "quantidade_convidados")
    private int quantidadeConvidados;
    @OneToMany
    private List<Mesa> mesas = new ArrayList<Mesa>();

    public Reserva() {
    }

    public static Finder<Long, Reserva> find = new Finder<Long, Reserva>(Reserva.class);

    public static List<Reserva> all() {
        return find.all();
    }

    public static Reserva create(Reserva reserva) {
        reserva.save();
        return reserva;
    }

    public static Reserva update(Long id, Reserva r) {
        Reserva reserva = find.byId(id);

        if (r.getCliente() != null) reserva.setCliente(r.getCliente());
        if (r.getAtendente() != null) reserva.setAtendente(r.getAtendente());
        if (r.getData() != null) reserva.setData(r.getData());
        if (r.getObservacao() != null) reserva.setObservacao(r.getObservacao());
        if (r.getQuantidadeConvidados() != 0) reserva.setQuantidadeConvidados(r.getQuantidadeConvidados());

        return create(reserva);

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getQuantidadeConvidados() {
        return quantidadeConvidados;
    }

    public void setQuantidadeConvidados(int quantidadeConvidados) {
        this.quantidadeConvidados = quantidadeConvidados;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
}
