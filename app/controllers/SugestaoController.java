package controllers;

import models.Cliente;
import models.Funcionario;
import models.Sugestao;
import models.Usuario;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 07/06/16.
 */
public class SugestaoController extends Controller implements RestMethods {

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Sugestao> sugestoes = Sugestao.all();
        if (sugestoes.size() != 0) {
            return ok(play.libs.Json.toJson(sugestoes));
        }
        return noContent();
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Sugestao.create.render(Usuario.find.all())));
    }

    @Override
    public Result save() {
        Form<Sugestao> form = formFactory.form(Sugestao.class).bindFromRequest();
        //if (form.hasErrors()) {
            //return badRequest(form.errorsAsJson());
        //}

        Sugestao sugestao = Sugestao.create(form.data());

        return ok(play.libs.Json.toJson(sugestao));
    }

    @Override
    public Result edit(Long id) {
        Sugestao sugestao = Sugestao.find.byId(id);
        if (sugestao != null) {
            return ok(play.libs.Json.toJson(sugestao));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Sugestao sugestao = Sugestao.find.byId(id);
        if (sugestao == null) {
            return notFound();
        }

        Form<Sugestao> form = formFactory.form(Sugestao.class).bindFromRequest();

        //if (form.hasErrors()) {
            //return badRequest(form.errorsAsJson());
        //}

        sugestao = Sugestao.update(id, form.data());

        return ok(play.libs.Json.toJson(sugestao));
    }

    @Override
    public Result delete(Long id) {
        Sugestao sugestao = Sugestao.find.byId(id);
        if (sugestao != null) {
            Sugestao.delete(id);
            return ok("Sugestao apagado com Sucesso!");
        }
        return notFound();
    }
}
