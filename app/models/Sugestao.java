package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_sugestao")
public class Sugestao extends Model {

    @Id
    private long id;
    @Column(name = "descricao")
    private String descricao;
    @OneToOne //TODO ManyToOne ???
    private Cliente cliente;
    @OneToOne //TODO ManyToOne ???
    private Funcionario funcionario;

    public Sugestao() {
    }

    public static Finder<Long, Sugestao> find = new Finder<Long, Sugestao>(Sugestao.class);
}
