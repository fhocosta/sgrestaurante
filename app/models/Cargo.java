package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
@Entity
@Table(name = "tbl_cargo")
public class Cargo extends Model {

    @Id
    private Long id;
    @Column(name="nome")
    private String nome;

    public Cargo() {
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

    public static Finder<Long, Cargo> find = new Finder<Long, Cargo>(Cargo.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
