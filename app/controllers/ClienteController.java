package controllers;

import models.Cliente;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by fhocosta on 31/05/16.
 */

public class ClienteController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Cliente> clientes = Cliente.all();
        if (clientes.size() != 0) {
            return ok(play.libs.Json.toJson(clientes));
        }
        return noContent();
    }

    @Override
    public Result create() {
        return ok(views.html.Clientes.create.render(formFactory.form(Cliente.class)));
    }

    @Override
    public Result save() {
        Form<Cliente> form = formFactory.form(Cliente.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Cliente cliente = Cliente.create(form.get());

        return ok(play.libs.Json.toJson(cliente));
    }
    @Override
    public Result edit(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if (cliente != null) {
            return ok(play.libs.Json.toJson(cliente));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if (cliente == null) {
            return notFound();
        }

        Form<Cliente> form = formFactory.form(Cliente.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        cliente = Cliente.update(id, form.get());

        return ok(play.libs.Json.toJson(cliente));
    }

    @Override
    public Result delete(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if (cliente != null) {
            Cliente.delete(id);
            return ok("Cliente apagado com Sucesso!");
        }
        return notFound();

    }


}
