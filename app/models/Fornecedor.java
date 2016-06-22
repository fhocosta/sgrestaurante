package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
@Entity
@Table(name = "tbl_fornecedor")
public class Fornecedor extends Model {

    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "telefone")
    private String telefone;

    public Fornecedor() {
    }

    public Fornecedor(String nome, String cnpj, String endereco, String telefone) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public static Finder<Long, Fornecedor> find = new Finder<Long, Fornecedor>(Fornecedor.class);

    public static List<Fornecedor> all() {
        return find.all();
    }

    public static Fornecedor create(Fornecedor fornecedor) {
        fornecedor.save();
        return fornecedor;
    }

    public static Fornecedor update(Long id, Fornecedor f) {
        Fornecedor fornecedor = find.byId(id);

        if (f.getNome() != null) fornecedor.setNome(f.getNome());
        if (f.getCnpj() != null) fornecedor.setCnpj(f.getCnpj());
        if (f.getEndereco() != null) fornecedor.setEndereco(f.getEndereco());
        if (f.getTelefone() != null) fornecedor.setTelefone(f.getTelefone());

        return create(fornecedor);
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
