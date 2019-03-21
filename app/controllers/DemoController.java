package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class DemoController extends Controller
{
    public Result getChart()
    {
        return ok(views.html.chartfun.render());
    }

    public Result getChartJSON()
    {
        List<Integer> values = new ArrayList<>();
        values.add(10);
        values.add(20);
        values.add(30);
        values.add(40);
        values.add(50);

        return ok(views.html.chartfunjson.render("10,20,30,40,50"));
    }
}
