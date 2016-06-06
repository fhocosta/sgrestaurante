package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.text.StringFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name="tbl_usuario")
public class Usuario extends Model {

    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "senha")
    private String senha;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "telefone")
    private String telefone;

    public Usuario() {
    }

    public static Finder<Long, Usuario> find = new Finder<Long, Usuario>(Usuario.class);

}