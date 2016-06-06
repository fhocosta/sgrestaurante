package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by fhocosta on 31/05/16.
 */
@Entity
@Table(name = "tbl_funcionario")
public class Funcionario extends Usuario {

    @Id
    private long id;
    @OneToOne
    private Cargo cargo;
    @OneToOne
    private Usuario usuario;

    public Funcionario() {
    }


    public static Finder<Long, Funcionario> find = new Finder<Long, Funcionario>(Funcionario.class);
}
