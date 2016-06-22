package controllers;

import models.Cortesia;
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
public class CortesiaController extends Controller implements RestMethods{

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Cortesia> comandas = Cortesia.all();
        if (comandas.size() != 0) {
            return ok(views.html.main.render(views.html.Cortesia.list.render(Cortesia.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Cortesias")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Cortesia.create.render(Produto.all())));
    }

    @Override
    public Result save() {
        Form<Cortesia> form = formFactory.form(Cortesia.class).bindFromRequest();
        //if (form.hasErrors()) {
            //return badRequest(form.errorsAsJson());
        //}

        Cortesia cortesia = Cortesia.create(form.data());

        return redirect("/cortesias/all");
    }

    @Override
    public Result edit(Long id) {
        Cortesia cortesia = Cortesia.find.byId(id);
        if (cortesia != null) {
            return ok(play.libs.Json.toJson(cortesia));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Cortesia cortesia = Cortesia.find.byId(id);
        if (cortesia == null) {
            return notFound();
        }

        Form<Cortesia> form = formFactory.form(Cortesia.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        cortesia = Cortesia.update(id, form.get());

        return ok(play.libs.Json.toJson(cortesia));
    }

    @Override
    public Result delete(Long id) {
        Cortesia cortesia = Cortesia.find.byId(id);
        if (cortesia != null) {
            cortesia.setProduto(null);
            cortesia.save();
            Cortesia.delete(id);
            return redirect("/cortesias/all");
        }
        return notFound();
    }
}
