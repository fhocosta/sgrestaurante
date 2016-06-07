package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
@Entity
@Table(name = "tbl_cargo")
public class Cargo extends Model {

    @Id
    private long id;
    @Column(name="nome")
    private String nome;
    @OneToMany
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

    public Cargo() {
    }

    public static Finder<Long, Cargo> find = new Finder<Long, Cargo>(Cargo.class);

    public static List<Cargo> all(){
        return find.all();
    }

    public static Cargo create(Cargo cargo){
        cargo.save();
        return cargo;
    }

    public static Cargo update(Long id, Cargo c){
        Cargo cargo = find.byId(id);

        if(c.getNome() != null) cargo.setNome(c.getNome());

        return create(cargo);
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

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
