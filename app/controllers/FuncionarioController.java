package controllers;

import com.google.inject.Inject;
import models.Cargo;
import models.Funcionario;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
public class FuncionarioController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Funcionario> funcionarios = Funcionario.find.all();
        if (funcionarios.size() != 0) {
            return ok(play.libs.Json.toJson(funcionarios));
        }
        return noContent();
    }

    @Override
    public Result create() {
        //TODO: retornar formulario para novo funcionario
        return null;
    }

    @Override
    public Result save() {
        Form<Funcionario> funcionario = formFactory.form(Funcionario.class).bindFromRequest();
        if (funcionario.hasErrors()) {
            return badRequest(funcionario.errorsAsJson());
        }
        Funcionario novoFuncionario = funcionario.get();
        novoFuncionario.save();
        return ok(play.libs.Json.toJson(novoFuncionario));
    }

    @Override
    public Result edit(Long id) {
        Funcionario funcionario = Funcionario.find.byId(id);
        if (funcionario != null) {
            return ok(play.libs.Json.toJson(funcionario));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {

        Funcionario funcionario = Funcionario.find.byId(id);
        if (funcionario == null) {
            return notFound();
        }

        Form<Funcionario> atualizarFuncionario = formFactory.form(Funcionario.class).bindFromRequest();

        if (atualizarFuncionario.hasErrors()) {
            return badRequest(atualizarFuncionario.errorsAsJson());
        }

        funcionario.setNome(atualizarFuncionario.get().getNome());
        funcionario.setCpf(atualizarFuncionario.get().getCpf());
        funcionario.setEndereco(atualizarFuncionario.get().getEndereco());

        Cargo cargo = Cargo.find.byId(atualizarFuncionario.get().getCargo().getId());
        funcionario.setCargo(cargo);

        funcionario.setEmail(atualizarFuncionario.get().getEmail());
        funcionario.save();

        return ok(play.libs.Json.toJson(funcionario));
    }

    @Override
    public Result delete(Long id) {
        Funcionario funcionario = Funcionario.find.byId(id);
        if (funcionario != null) {
            funcionario.delete();
            return ok("Funcionario apagado com Sucesso!");
        }
        return notFound();
    }
}
