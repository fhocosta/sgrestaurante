package controllers;

import com.google.inject.Inject;
import models.Cargo;
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
        List<Cargo> cargos = Cargo.find.all();
        if (cargos.size() != 0) {
            return ok(play.libs.Json.toJson(cargos))
        }
        return noContent();
    }

    @Override
    public Result create() {
        //TODO: retornar formulario de criacao de Cargo
        return null;
    }

    @Override
    public Result save() {
        Form<Cargo> cargo = formFactory.form(Cargo.class).bindFromRequest();
        if (cargo.hasErrors()) {
            return badRequest(cargo.errorsAsJson());
        }
        Cargo novoCargo = cargo.get();
        novoCargo.save();
        return ok(play.libs.Json.toJson(novoCargo));
    }

    @Override
    public Result edit(Long id) {
        Cargo cargo = Cargo.find.byId(id);
        if (cargo != null) {
            return ok(play.libs.Json.toJson(cargo));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Cargo cargo = Cargo.find.byId(id);
        if (cargo == null) {
            return notFound();
        }
        Form<Cargo> atualizarCargo = formFactory.form(Cargo.class).bindFromRequest();

        if (atualizarCargo.hasErrors()) {
            return badRequest(atualizarCargo.errorsAsJson());
        }

        cargo.setNome(atualizarCargo.get().getNome());
        cargo.save();

        return ok(play.libs.Json.toJson(cargo));
    }

    @Override
    public Result delete(Long id) {
        Cargo cargo = Cargo.find.byId(id);
        if (cargo != null) {
            cargo.delete();
            return ok("Cargo apagado com Sucesso!");
        }
        return notFound();
    }
}
