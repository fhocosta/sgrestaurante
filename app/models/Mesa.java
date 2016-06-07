package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_mesa")
public class Mesa extends Model {

    @Id
    private long id;
    @Column(name = "numero")
    private int numero;
    @Column(name = "lugares")
    private int lugares;
    @OneToMany
    private List<Comanda> comandas = new ArrayList<Comanda>();
    @ManyToOne
    private Reserva reserva;

    public Mesa() {
    }

    public static Finder<Long, Mesa> find = new Finder<Long, Mesa>(Mesa.class);

    public static List<Mesa> all() {
        return find.all();
    }

    public static Mesa create(Mesa mesa) {
        mesa.save();
        return mesa;
    }

    public static Mesa update(Long id, Mesa m) {
        Mesa mesa = find.byId(id);

        if (m.getNumero() != 0) mesa.setNumero(m.getNumero());
        if (m.getLugares() != 0) mesa.setLugares(m.getLugares());
        if (m.getReserva() != null) mesa.setReserva(m.getReserva());

        return create(mesa);

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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
