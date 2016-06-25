package controllers;

import models.Mesa;
import models.Reserva;
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
            return ok(views.html.main.render(views.html.Mesa.list.render(Mesa.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Mesas")));
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

        return redirect("/mesas/all");
    }

    @Override
    public Result edit(Long id) {
        Mesa mesa = Mesa.find.byId(id);
        if (mesa != null) {
            return ok(views.html.main.render(views.html.Mesa.edit.render(mesa)));
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

        return redirect("/mesas/all");
    }

    @Override
    public Result delete(Long id) {
        Mesa mesa = Mesa.find.byId(id);
        if (mesa != null) {
            List<Reserva> list = Reserva.find.where().eq("mesa.id",id).findList();
            if(list.size() > 0){
                for(Reserva reserva: list){
                    reserva.setMesa(null);
                    reserva.save();
                }
            }
            Mesa.delete(id);
            return redirect("/mesas/all");
        }
        return notFound();
    }
}
