package controllers;

import javax.inject.Inject;
import models.Cargo;
import models.Funcionario;
import models.Usuario;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
public class CargoController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Cargo> cargos = Cargo.all();
        if (cargos.size() != 0) {
            return ok(views.html.main.render(views.html.Cargo.list.render(Cargo.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Cargos")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Cargo.create.render()));
    }

    @Override
    public Result save() {
        Form<Cargo> form = formFactory.form(Cargo.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Cargo cargo = Cargo.create(form.get());

        return redirect("/cargos/all");
    }

    @Override
    public Result edit(Long id) {
        Cargo cargo = Cargo.find.byId(id);
        if (cargo != null) {
            return ok(views.html.main.render(views.html.Cargo.edit.render(cargo)));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Cargo cargo = Cargo.find.byId(id);
        if (cargo == null) {
            return notFound();
        }

        Form<Cargo> form = formFactory.form(Cargo.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        cargo = Cargo.update(id, form.get());

        return redirect("/cargos/all");
    }

    @Override
    public Result delete(Long id) {
        Cargo cargo = Cargo.find.byId(id);
        if (cargo != null) {
            List<Funcionario> list = Funcionario.find.where().eq("cargo.id",id).findList();
            if(list.size() > 0){
                for(Funcionario funcionario: list){
                    funcionario.setCargo(null);
                    funcionario.save();
                }
            }
            Cargo.delete(id);
            return redirect("/cargos/all");
        }
        return notFound();
    }
}
