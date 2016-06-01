package controllers;

import play.mvc.Result;

/**
 * Created by fhocosta on 01/06/16.
 */
public interface RestMethods {

    public Result list();

    public Result create();

    public Result save();

    public Result edit(Long id);

    public Result update(Long id);

    public Result delete(Long id);

}
