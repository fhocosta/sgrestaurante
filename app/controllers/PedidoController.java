package controllers;

import models.Comanda;
import models.Pedido;
import models.Produto;
import models.Status;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;
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
        return ok(views.html.main.render(views.html.Pedido.create.render(Status.all(), Produto.all(), Comanda.all())));
    }

    @Override
    public Result save() {
        Form<Pedido> form = formFactory.form(Pedido.class).bindFromRequest();
        //if (form.hasErrors()) {
            //return badRequest(form.errorsAsJson());
        //}

        Pedido pedido = Pedido.create(form.data());

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

        pedido = Pedido.update(id, form.data());

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
