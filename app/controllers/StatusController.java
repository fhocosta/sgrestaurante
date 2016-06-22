package controllers;

import models.Status;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;
import java.util.List;

/**
 * Created by fhocosta on 07/06/16.
 */
public class StatusController extends Controller implements RestMethods{

    @Inject
    FormFactory formFactory;

    @Override
    public Result list() {
        List<Status> statuses = Status.all();
        if (statuses.size() != 0) {
            return ok(views.html.main.render(views.html.Status.list.render(Status.all())));
        }
        return ok(views.html.main.render(views.html.noContent.render("Status")));
    }

    @Override
    public Result create() {
        return ok(views.html.main.render(views.html.Status.create.render()));
    }

    @Override
    public Result save() {
        Form<Status> form = formFactory.form(Status.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Status status = Status.create(form.get());

        return redirect("/status/all");
    }

    @Override
    public Result edit(Long id) {
        Status status = Status.find.byId(id);
        if (status != null) {
            return ok(play.libs.Json.toJson(status));
        }
        return notFound();
    }

    @Override
    public Result update(Long id) {
        Status status = Status.find.byId(id);
        if (status == null) {
            return notFound();
        }

        Form<Status> form = formFactory.form(Status.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        status = Status.update(id, form.get());

        return ok(play.libs.Json.toJson(status));
    }

    @Override
    public Result delete(Long id) {
        Status status = Status.find.byId(id);
        if (status != null) {
            Status.delete(id);
            return redirect("/status/all");
        }
        return notFound();
    }
}
