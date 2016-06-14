package models;

import com.avaje.ebean.Model;
import play.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fhocosta on 31/05/16.
 */
@Entity
@DiscriminatorValue("F")
public class Funcionario extends Usuario {

    @Column(nullable = false)
    @ManyToOne
    private Cargo cargo;
    @OneToMany
    private List<Reserva> reservas = new ArrayList<Reserva>();

    public Funcionario() {
    }

    public static Finder<Long, Funcionario> find = new Finder<Long, Funcionario>(Funcionario.class);

    public static List<Funcionario> all() {
        return find.all();
    }

    public static Funcionario create(Map<String, String> form){
        Funcionario funcionario = new Funcionario();

        if(form.get("nome") != null){
            funcionario.setNome(form.get("nome"));
        }
        if(form.get("email") != null){
            funcionario.setEmail(form.get("email"));
        }
        if(form.get("username") != null){
            funcionario.setUsername(form.get("username"));
        }
        if(form.get("senha") != null && !form.get("senha").isEmpty()){
            funcionario.setSenha(form.get("senha"));
        }
        if(form.get("cpf") != null){
            funcionario.setCpf(form.get("cpf"));
        }
        if(form.get("endereco") != null){
            funcionario.setEndereco(form.get("endereco"));
        }
        if(form.get("telefone") != null){
            funcionario.setTelefone(form.get("telefone"));
        }
        if(form.get("cargo") != null){
            Long id = Long.parseLong(form.get("cargo"));
            Cargo cargo = Cargo.find.byId(id);
            if (cargo != null){
                funcionario.setCargo(cargo);
            }
        }
        return create(funcionario);
    }

    public static Funcionario create(Funcionario funcionario) {
        Logger.debug("funcionario", funcionario);
        funcionario.save();
        return funcionario;
    }

    public static Funcionario update(Long id, Funcionario f) {
        Funcionario funcionario = find.byId(f.getId());
        Usuario.update(funcionario.getId(), f);
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

}
