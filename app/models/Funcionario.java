package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
@Entity
@DiscriminatorValue("F")
public class Funcionario extends Usuario {

//    @Id
//    private long id;
    @ManyToOne
    private Cargo cargo;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
    @OneToMany
    private List<Comanda> comandas = new ArrayList<Comanda>();
    @OneToMany
    private List<Reserva> reservas = new ArrayList<Reserva>();

    public Funcionario() {
    }

    public static Finder<Long, Funcionario> find = new Finder<Long, Funcionario>(Funcionario.class);

    public static List<Funcionario> all() {
        return find.all();
    }

    public static Funcionario create(Funcionario funcionario) {
        funcionario.save();
        return funcionario;
    }

    public static Funcionario update(Long id, Funcionario f) {
        Usuario.update(f.getUsuario().getId(), f);
        Funcionario funcionario = find.byId(f.getId());
        if (f.getCargo() != null) {
            Cargo cargo = Cargo.find.byId(f.getCargo().getId());
            funcionario.setCargo(cargo);
        }
        return create(funcionario);
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
