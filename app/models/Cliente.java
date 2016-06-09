package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */

@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario {

//    @Id
//    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
    @Column(name = "receber_marketing")
    private boolean receberMarketing;
    @OneToMany
    private List<Reserva> reservas = new ArrayList<Reserva>();
    @OneToMany
    private List<Comanda> comandas = new ArrayList<Comanda>();

    public Cliente() {
    }

    public static Finder<Long, Cliente> find = new Finder<Long, Cliente>(Cliente.class);

    public static List<Cliente> all() {
        return find.all();
    }

    public static Cliente create(Cliente cliente) {
        cliente.save();
        return cliente;
    }

    public static Cliente update(Long id, Cliente c) {
        Usuario.update(c.getUsuario().getId(), c);
        Cliente cliente = find.byId(id);
        if (c.isReceberMarketing()) cliente.setReceberMarketing(c.isReceberMarketing());
        return create(cliente);
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public boolean isReceberMarketing() {
        return receberMarketing;
    }

    public void setReceberMarketing(boolean receberMarketing) {
        this.receberMarketing = receberMarketing;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
