package controllers;

import models.Employee;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class SessionController extends BaseController
{
    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public SessionController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    public Result getLogin()
    {
        return ok(views.html.login.render(""));
    }

    @Transactional(readOnly = true)
    public Result postLogin()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String username = form.get("username");

        String sql = "SELECT e FROM Employee e WHERE lastName = :username";

        Logger.debug(sql);

        TypedQuery<Employee> query = db.em().createQuery(sql, Employee.class);
        query.setParameter("username", username);

        List<Employee> employees = query.getResultList();

        Result result;

        if (employees.size() == 1)
        {
            Employee employee = employees.get(0);
            result = redirect("/employees");
            login(employee);
        }
        else
        {
            logout();
            String message = "Incorrect username or password. Please try again.";
            result = ok(views.html.login.render(message));
        }

        return result;
    }

}
