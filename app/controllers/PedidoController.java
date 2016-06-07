package controllers;

import models.Pedido;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 07/06/16.
 */
public class PedidoController extends Controller implements RestMethods{

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Pedido> pedidos = Pedido.all();
        if (pedidos.size() != 0) {
            return ok(play.libs.Json.toJson(pedidos));
        }
        return noContent();
    }

    @Override
    public Result create() {
        //TODO: retornar formulario de criacao de Pedido
        return null;
    }

    @Override
    public Result save() {
        Form<Pedido> form = formFactory.form(Pedido.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Pedido pedido = Pedido.create(form.get());

        return ok(play.libs.Json.toJson(pedido));
    }

    @Override
    public Result edit(Long id) {
        Pedido pedido = Pedido.find.byId(id);
        if (pedido != null) {
            return ok(play.libs.Json.toJson(pedido));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Pedido pedido = Pedido.find.byId(id);
        if (pedido == null) {
            return notFound();
        }

        Form<Pedido> form = formFactory.form(Pedido.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        pedido = Pedido.update(id, form.get());

        return ok(play.libs.Json.toJson(pedido));
    }

    @Override
    public Result delete(Long id) {
        Pedido pedido = Pedido.find.byId(id);
        if (pedido != null) {
            Pedido.delete(id);
            return ok("Pedido apagado com Sucesso!");
        }
        return notFound();
    }
}
