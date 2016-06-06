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
    @OneToOne
    private Funcionario atendente;
    @Column(name = "data")
    private Date data;
    @Column(name = "observacao")
    private String observacao;
    @OneToMany
    private List<Mesa> mesas = new ArrayList<Mesa>();

    public Reserva() {
    }

    public static Finder<Long, Reserva> find = new Finder<Long, Reserva>(Reserva.class);
}
