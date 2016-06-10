package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.text.StringFormatter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario extends Model {

    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
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

    public static void create(Usuario usuario) {
        usuario.save();
    }

    public static void update(Long id, Usuario usuario) {
        Usuario user = find.byId(id);

        if (usuario.getNome() != null) user.setNome(usuario.getNome());
        if (usuario.getEmail() != null) user.setEmail(usuario.getEmail());
        if (usuario.getUsername() != null) user.setUsername(usuario.getUsername());
        if (usuario.getSenha() != null) user.setSenha(usuario.getSenha());
        if (usuario.getCpf() != null) user.setCpf(usuario.getCpf());
        if (usuario.getEndereco() != null) user.setEndereco(usuario.getEndereco());
        if (usuario.getTelefone() != null) user.setTelefone(usuario.getTelefone());

        user.save();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}