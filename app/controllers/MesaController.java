package controllers;

import models.Mesa;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 07/06/16.
 */
public class MesaController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Mesa> mesas = Mesa.all();
        if (mesas.size() != 0) {
            return ok(play.libs.Json.toJson(mesas));
        }
        return noContent();
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Mesa.create.render()));
    }

    @Override
    public Result save() {
        Form<Mesa> form = formFactory.form(Mesa.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Mesa mesa = Mesa.create(form.get());

        return ok(play.libs.Json.toJson(mesa));
    }

    @Override
    public Result edit(Long id) {
        Mesa mesa = Mesa.find.byId(id);
        if (mesa != null) {
            return ok(play.libs.Json.toJson(mesa));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Mesa mesa = Mesa.find.byId(id);
        if (mesa == null) {
            return notFound();
        }

        Form<Mesa> form = formFactory.form(Mesa.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        mesa = Mesa.update(id, form.get());

        return ok(play.libs.Json.toJson(mesa));
    }

    @Override
    public Result delete(Long id) {
        Mesa mesa = Mesa.find.byId(id);
        if (mesa != null) {
            Mesa.delete(id);
            return ok("Mesa apagado com Sucesso!");
        }
        return notFound();
    }
}
