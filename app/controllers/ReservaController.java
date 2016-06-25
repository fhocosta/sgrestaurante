package controllers;

import models.Cliente;
import models.Funcionario;
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
public class ReservaController extends Controller implements RestMethods{

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Reserva> reservas = Reserva.all();
        if (reservas.size() != 0) {
            return ok(views.html.main.render(views.html.Reserva.list.render(Reserva.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Reservas")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Reserva.create.render(Cliente.all(), Funcionario.all(), Mesa.all())));
    }

    @Override
    public Result save() {
        Form<Reserva> form = formFactory.form(Reserva.class).bindFromRequest();
        //if (form.hasErrors()) {
            //return badRequest(form.errorsAsJson());
        //}

        Reserva reserva = Reserva.create(form.data());

        return redirect("/reservas/all");
    }

    @Override
    public Result edit(Long id) {
        Reserva reserva = Reserva.find.byId(id);
        if (reserva != null) {
            return ok(views.html.main.render(views.html.Reserva.edit.render(reserva,Cliente.all(), Funcionario.all(), Mesa.all())));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Reserva reserva = Reserva.find.byId(id);
        if (reserva == null) {
            return notFound();
        }

        Form<Reserva> form = formFactory.form(Reserva.class).bindFromRequest();

        //if (form.hasErrors()) {
            //return badRequest(form.errorsAsJson());
        //}

        reserva = Reserva.update(id, form.data());

        return redirect("/reservas/all");
    }

    @Override
    public Result delete(Long id) {
        Reserva reserva = Reserva.find.byId(id);
        if (reserva != null) {

            reserva.setCliente(null);
            reserva.setMesa(null);
            reserva.save();

            Reserva.delete(id);
            return redirect("/reservas/all");
        }
        return notFound();
    }
}
