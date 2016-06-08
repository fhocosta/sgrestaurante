package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by fhocosta on 08/06/16.
 */
public class HomeController extends Controller {

    public Result index(){
        return ok(views.html.main.render("SG Restaurante"));
    }
}
