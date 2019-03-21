package controllers;

import models.CategorySummary;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

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

        return ok(views.html.categorysummary.render(categorySummaries));
    }
}
