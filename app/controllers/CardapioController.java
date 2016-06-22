package controllers;

import com.google.inject.Inject;
import models.Cardapio;
import models.Produto;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;


import play.mvc.Result;

import java.util.List;

/**
 * Created by fhocosta on 07/06/16.
 */
public class CardapioController extends Controller implements RestMethods{
    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Cardapio> cardapios = Cardapio.all();
        if (cardapios.size() != 0) {
            return ok(views.html.main.render(views.html.Cardapio.list.render(Cardapio.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Cardápios")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Cardapio.create.render(Produto.all())));
    }

    @Override
    public Result save() {

        Form<Cardapio> form = formFactory.form(Cardapio.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Cardapio cardapio = Cardapio.create(form.data());

        return redirect("/cardapios/all");
    }

    @Override
    public Result edit(Long id) {

        Cardapio cardapio = Cardapio.find.byId(id);
        if (cardapio != null) {
            return ok(play.libs.Json.toJson(cardapio));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {

        Cardapio cardapio = Cardapio.find.byId(id);
        if (cardapio == null) {
            return notFound();
        }

        Form<Cardapio> form = formFactory.form(Cardapio.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        cardapio = Cardapio.update(id, form.get());

        return ok(play.libs.Json.toJson(cardapio));
    }

    @Override
    public Result delete(Long id) {

        Cardapio cardapio = Cardapio.find.byId(id);
        if (cardapio != null) {
            Cardapio.find.ref(id).delete();
            return redirect("/cardapios/all");
        }
        return notFound();
    }
}
