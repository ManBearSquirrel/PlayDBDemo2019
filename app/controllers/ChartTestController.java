package controllers;

import models.ChartValues;
import play.mvc.Controller;
import play.mvc.Result;

public class ChartTestController extends Controller
{
    public Result getPieChart()
    {
        ChartValues chartValues = new ChartValues();
        chartValues.setDataValues("56|13|67|23|42|100");
        chartValues.setLabelValues("Freaks|Weirdos|Mutants|Josh|Dave|LCD");
        chartValues.setColorValues("rgb(255, 0, 0)|rgb(0, 255, 0)|rgb(0, 0, 255)|rgb(255, 255, 0)|rgb(0, 255, 255)|rgb(128, 0, 255)");

        return ok(views.html.piechart.render(chartValues));
    }
}
