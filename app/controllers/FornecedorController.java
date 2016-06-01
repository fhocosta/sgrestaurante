package controllers;

import models.Fornecedor;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */
public class FornecedorController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    public Result list() {
        List<Fornecedor> fornecedores = Fornecedor.find.all();
        if (fornecedores.size() != 0) {
            return ok(play.libs.Json.toJson(fornecedores));
        }
        return noContent();

    }

    public Result create() {
        return ok(views.html.Fornecedor.create.render(formFactory.form(Fornecedor.class)));
    }

    @Transactional
    public Result save() {
        Form<Fornecedor> fornecedor = formFactory.form(Fornecedor.class).bindFromRequest();
        if (fornecedor.hasErrors()) {
            return badRequest(fornecedor.errorsAsJson());
        }
        Fornecedor novoFornecedor = fornecedor.get();
        novoFornecedor.save();
        return ok(play.libs.Json.toJson(novoFornecedor));
    }

    public Result edit(Long id) {
        Fornecedor fornecedor = Fornecedor.find.byId(id);
        if (fornecedor != null) {
            return ok(play.libs.Json.toJson(fornecedor));
        }
        return notFound();
    }

    @Transactional
    public Result update(Long id) {
        Fornecedor fornecedor = Fornecedor.find.byId(id);
        if (fornecedor == null) {
            return notFound();
        }
        Form<Fornecedor> atualizarFornecedor = formFactory.form(Fornecedor.class).bindFromRequest();

        if (atualizarFornecedor.hasErrors()) {
            return badRequest(atualizarFornecedor.errorsAsJson());
        }

        fornecedor.setNome(atualizarFornecedor.get().getNome());
        fornecedor.setCnpj(atualizarFornecedor.get().getCnpj());
        fornecedor.setEndereco(atualizarFornecedor.get().getEndereco());
        fornecedor.setTelefone(atualizarFornecedor.get().getTelefone());
        fornecedor.save();

        return ok(play.libs.Json.toJson(fornecedor));
    }

    @Transactional
    public Result delete(Long id) {
        Fornecedor fornecedor = Fornecedor.find.byId(id);
        if (fornecedor != null) {
            fornecedor.delete();
            return ok("Fornecedor apagado com Sucesso!");
        }
        return notFound();
    }
}
