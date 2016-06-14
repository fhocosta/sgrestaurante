package controllers;

import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 07/06/16.
 */
public class ComandaController extends Controller implements RestMethods {
    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Comanda> comandas = Comanda.all();
        if (comandas.size() != 0) {
            return ok(play.libs.Json.toJson(comandas));
        }
        return noContent();
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Comanda.create.render(Funcionario.all(), Cliente.all(), Mesa.all(), Cortesia.all())));
    }

    @Override
    public Result save() {
        Form<Comanda> form = formFactory.form(Comanda.class).bindFromRequest();
        //if (form.hasErrors()) {
            //return badRequest(form.errorsAsJson());
        //}

        Comanda comanda = Comanda.create(form.data());

        return ok(play.libs.Json.toJson(comanda));
    }

    @Override
    public Result edit(Long id) {
        Comanda comanda = Comanda.find.byId(id);
        if (comanda != null) {
            return ok(play.libs.Json.toJson(comanda));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Comanda comanda = Comanda.find.byId(id);
        if (comanda == null) {
            return notFound();
        }

        Form<Comanda> form = formFactory.form(Comanda.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        comanda = Comanda.update(id, form.get());

        return ok(play.libs.Json.toJson(comanda));
    }

    @Override
    public Result delete(Long id) {
        Comanda comanda = Comanda.find.byId(id);
        if (comanda != null) {
            Comanda.delete(id);
            return ok("Comanda apagado com Sucesso!");
        }
        return notFound();
    }
}
