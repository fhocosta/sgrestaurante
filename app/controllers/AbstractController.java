package controllers;

import com.google.inject.Inject;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by fhocosta on 31/05/16.
 */
interface RestMethods {
    public Result list();

    public Result create();

    public Result save();

    public Result edit(Long id);

    public Result update(Long id);

    public Result delete(Long id);
}
