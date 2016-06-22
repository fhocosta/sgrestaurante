package controllers;

import models.Produto;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 07/06/16.
 */
public class ProdutoController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Produto> produtos = Produto.all();
        if (produtos.size() != 0) {
            return ok(views.html.main.render(views.html.Produto.list.render(Produto.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Produtos")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Produto.create.render()));
    }

    @Override
    public Result save() {
        Form<Produto> form = formFactory.form(Produto.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Produto produto = Produto.create(form.get());

        return redirect("/produtos/all");
    }

    @Override
    public Result edit(Long id) {
        Produto produto = Produto.find.byId(id);
        if (produto != null) {
            return ok(play.libs.Json.toJson(produto));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Produto produto = Produto.find.byId(id);
        if (produto == null) {
            return notFound();
        }

        Form<Produto> form = formFactory.form(Produto.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        produto = Produto.update(id, form.get());

        return ok(play.libs.Json.toJson(produto));
    }

    @Override
    public Result delete(Long id) {
        Produto produto = Produto.find.byId(id);
        if (produto != null) {
            Produto.delete(id);
            return redirect("/produtos/all");
        }
        return notFound();
    }
}
