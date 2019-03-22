package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class ChartTestController extends Controller
{
    public Result getPieChart()
    {
        return ok(views.html.piechart.render());
    }
}
