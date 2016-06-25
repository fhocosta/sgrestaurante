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
            return ok(views.html.main.render(views.html.Comanda.list.render(Comanda.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Comandas")));
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

        return redirect("/comandas/all");
    }

    @Override
    public Result edit(Long id) {
        Comanda comanda = Comanda.find.byId(id);
        if (comanda != null) {
            return ok(views.html.main.render(views.html.Comanda.edit.render(comanda, Funcionario.all(), Cliente.all(), Mesa.all(), Cortesia.all())));
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

        return redirect("/comandas/all");
    }

    @Override
    public Result delete(Long id) {
        Comanda comanda = Comanda.find.byId(id);
        if (comanda != null) {
            List<Pedido> list = Pedido.find.where().eq("comanda.id",id).findList();
            if(list.size() > 0){
                for(Pedido pedido: list){
                    pedido.delete();
                }
            }
            comanda.setAtendente(null);
            comanda.setCliente(null);
            comanda.setMesa(null);
            comanda.setCortesia(null);
            comanda.save();

            Comanda.delete(id);
            return redirect("/comandas/all");
        }
        return notFound();
    }
}
