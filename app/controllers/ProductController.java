package controllers;

import models.Product;
import models.ProductDetail;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductController extends Controller
{
    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public ProductController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getProducts()
    {
        TypedQuery<ProductDetail> query = db.em().createQuery(
                "SELECT NEW ProductDetail(p.productId, p.productName, c.categoryName) " +
                        "FROM Product p " +
                        "JOIN Category c ON p.categoryId = c.categoryId", ProductDetail.class);
        List<ProductDetail> products = query.getResultList();

        return ok(views.html.products.render(products));
    }
}
