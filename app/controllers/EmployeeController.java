package controllers;

import models.Employee;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;


public class EmployeeController extends Controller
{
    private JPAApi db;

    @Inject
    public EmployeeController(JPAApi db)
    {
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getEmployee(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        TypedQuery<Employee> reportsQuery = db.em().createQuery("SELECT e FROM Employee e WHERE reportsToEmployeeId = :employeeId", Employee.class);
        reportsQuery.setParameter("employeeId", employeeId);
        List<Employee> reports = reportsQuery.getResultList();

        return ok(views.html.employee.render(employee, reports));
    }

    @Transactional(readOnly = true)
    public Result getPicture(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        return ok(employee.getPicture());
    }
}
