package controllers;

import models.CategorySummary;
import models.ChartValues;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryController extends Controller
{
    private JPAApi db;

    @Inject
    public CategoryController(JPAApi db)
    {
        this.db = db;
    }

        @Transactional(readOnly = true)
    public Result getCategorySummary()
    {
        String sql = "SELECT NEW CategorySummary(c.categoryId, c.categoryName, COUNT(*), SUM(od.quantity), SUM(od.unitPrice * od.quantity)) " +
                "FROM OrderDetail od " +
                "JOIN Product p ON od.productId = p.productId " +
                "JOIN Category c ON p.categoryId = c.categoryId " +
                "GROUP BY c.categoryId, c.categoryName ";

        TypedQuery<CategorySummary> query = db.em().createQuery(sql, CategorySummary.class);
        List<CategorySummary> categorySummaries = query.getResultList();

        String data = categorySummaries.stream()
                .map(categorySummary -> Long.toString(categorySummary.getTotalSold()))
                .collect(Collectors.joining("|"));

        String labels = categorySummaries.stream()
                .map(categorySummary -> categorySummary.getCategoryName())
                .collect(Collectors.joining("|"));

        ChartValues chartValues = new ChartValues();
        chartValues.setDataValues(data);
        chartValues.setLabelValues(labels);
        chartValues.setColorValues("rgb(255, 0, 0)|rgb(0, 255, 0)|rgb(0, 0, 255)|rgb(255, 255, 0)|rgb(0, 255, 255)|rgb(128, 0, 255)");

        return ok(views.html.categorysummary.render(categorySummaries, chartValues));
    }
}
