package controllers;

import com.avaje.ebeaninternal.server.lib.util.Str;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Cargo;
import models.Funcionario;
import models.Reserva;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

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
            return ok(views.html.main.render(views.html.Funcionario.list.render(Funcionario.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Funcionários")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Funcionario.create.render(Cargo.all())));
    }

    @Override
    public Result save() {
        Form<Funcionario> form = formFactory.form(Funcionario.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Funcionario funcionario = Funcionario.create(form.data());

        return redirect("/funcionarios/all");
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
            funcionario.setReservas(null);
            funcionario.save();
            Funcionario.delete(id);

            return redirect("/funcionarios/all");
        }
        return notFound();
    }
}
