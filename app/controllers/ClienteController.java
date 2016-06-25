package controllers;

import models.Cliente;
import models.Reserva;
import models.Sugestao;
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
            return ok(views.html.main.render(views.html.Cliente.list.render(Cliente.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Clientes")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Cliente.create.render()));
    }

    @Override
    public Result save() {
        Form<Cliente> form = formFactory.form(Cliente.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Cliente cliente = Cliente.create(form.get());

        return redirect("/clientes/all");
    }
    @Override
    public Result edit(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if (cliente != null) {
            return ok(views.html.main.render(views.html.Cliente.edit.render(cliente)));
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

        return redirect("/clientes/all");
    }

    @Override
    public Result delete(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if (cliente != null) {
            List<Sugestao> list = Sugestao.find.where().eq("usuario.id",id).findList();
            if(list.size() > 0){
                for(Sugestao pedido: list){
                    pedido.setUsuario(null);
                    pedido.save();
                }
            }
            List<Reserva> list2 = Reserva.find.where().eq("cliente.id",id).findList();
            if(list2.size() > 0){
                for(Reserva reserva: list2){
                    reserva.setCliente(null);
                    reserva.save();
                }
            }
            Cliente.delete(id);
            return redirect("/clientes/all");
        }
        return notFound();

    }


}
