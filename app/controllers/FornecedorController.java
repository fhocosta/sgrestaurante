package controllers;

import models.Fornecedor;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
public class FornecedorController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Fornecedor> fornecedores = Fornecedor.all();
        if (fornecedores.size() != 0) {
            return ok(play.libs.Json.toJson(fornecedores));
        }
        return noContent();
    }

    @Override
    public Result create() {
        return ok(views.html.main.render("SG Restaurante - Funcionários", views.html.Fornecedor.create.render(formFactory.form(Fornecedor.class))));
    }

    @Override
    public Result save() {
        Form<Fornecedor> form = formFactory.form(Fornecedor.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Fornecedor fornecedor = Fornecedor.create(form.get());

        return ok(play.libs.Json.toJson(fornecedor));
    }

    @Override
    public Result edit(Long id) {
        Fornecedor fornecedor = Fornecedor.find.byId(id);
        if (fornecedor != null) {
            return ok(play.libs.Json.toJson(fornecedor));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Fornecedor fornecedor = Fornecedor.find.byId(id);
        if (fornecedor == null) {
            return notFound();
        }

        Form<Fornecedor> form = formFactory.form(Fornecedor.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        fornecedor = Fornecedor.update(id, form.get());

        return ok(play.libs.Json.toJson(fornecedor));
    }

    @Override
    public Result delete(Long id) {
        Fornecedor fornecedor = Fornecedor.find.byId(id);
        if (fornecedor != null) {
            Fornecedor.delete(id);
            return ok("Fornecedor apagado com Sucesso!");
        }
        return notFound();
    }
}
