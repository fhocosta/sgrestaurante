package controllers;

import javax.inject.Inject;
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
        List<Funcionario> funcionarios = Funcionario.all();
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
        Form<Funcionario> form = formFactory.form(Funcionario.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Funcionario funcionario = Funcionario.create(form.get());

        return ok(play.libs.Json.toJson(funcionario));
    }

    @Override
    public Result edit(Long id) {
        Funcionario funcionario= Funcionario.find.byId(id);
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

        Form<Funcionario> form = formFactory.form(Funcionario.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        funcionario = Funcionario.update(id, form.get());

        return ok(play.libs.Json.toJson(funcionario));
    }

    @Override
    public Result delete(Long id) {
        Funcionario funcionario = Funcionario.find.byId(id);
        if (funcionario != null) {
            Funcionario.delete(id);
            return ok("Funcionario apagado com Sucesso!");
        }
        return notFound();
    }
}
