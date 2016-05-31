package controllers;

import models.Cliente;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by fhocosta on 31/05/16.
 */

public class ClienteController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result list() {
        List<Cliente> clientes = Cliente.find.all();
        if (clientes.size() != 0) {
            return ok(play.libs.Json.toJson(clientes));
        } else {
            return noContent();
        }
    }

    public Result create() {
        return ok(views.html.Clientes.create.render(formFactory.form(Cliente.class)));
    }

    @Transactional
    public Result save() {
        Form<Cliente> cliente = formFactory.form(Cliente.class).bindFromRequest();
        if (cliente.hasErrors()) {
            return badRequest(cliente.errorsAsJson());
        }
        Cliente novoCliente = cliente.get();
        novoCliente.save();
        return ok(play.libs.Json.toJson(novoCliente));
    }

    public Result edit(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if (cliente != null) {
            return ok(play.libs.Json.toJson(cliente));
        } else {
            return notFound();
        }
    }

    @Transactional
    public Result update(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if (cliente == null) {
            return notFound();
        }
        Form<Cliente> atualizarCliente = formFactory.form(Cliente.class).bindFromRequest();

        if (atualizarCliente.hasErrors()) {
            return badRequest(atualizarCliente.errorsAsJson());
        }

        cliente.setNome(atualizarCliente.get().getNome());
        cliente.setEmail(atualizarCliente.get().getEmail());
        cliente.setTelefone(atualizarCliente.get().getTelefone());
        cliente.save();

        return ok(play.libs.Json.toJson(cliente));
    }

    public Result delete(Long id) {
        Cliente cliente = Cliente.find.byId(id);
        if(cliente != null){
            cliente.delete();
            return ok("Cliente apagado com Sucesso!");
        }else{
            return notFound();
        }

    }


}
