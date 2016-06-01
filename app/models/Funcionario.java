package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by fhocosta on 31/05/16.
 */
@Entity
@Table(name = "tbl_funcionario")
public class Funcionario extends Model {

    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "endereco")
    private String endereco;
    @OneToOne
    private Cargo cargo;
    @Column(name = "email")
    private String email;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, String endereco, Cargo cargo, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cargo = cargo;
        this.email = email;
    }

    public static Finder<Long, Funcionario> find = new Finder<Long, Funcionario>(Funcionario.class);

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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
