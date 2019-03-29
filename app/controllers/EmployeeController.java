package controllers;

import com.google.common.io.Files;
import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeController extends Controller
{
    private JPAApi db;
    private FormFactory formFactory;
    private EmployeeRepository employeeRepository;
    private StateRepository stateRepository;

    @Inject
    public EmployeeController(JPAApi db, FormFactory formFactory, EmployeeRepository employeeRepository, StateRepository stateRepository)
    //public EmployeeController(FormFactory formFactory, EmployeeRepository employeeRepository, StateRepository stateRepository)
    {
        this.db = db;
        this.formFactory = formFactory;
        this.employeeRepository = employeeRepository;
        this.stateRepository = stateRepository;
    }

    @Transactional(readOnly = true)
    public Result getEmployee(int employeeId)
    {
        /*TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();*/
        Employee employee = employeeRepository.getEmployee(employeeId);

        Employee reportsToEmployee = null;

        if (employee.getReportsToEmployeeId() != null)
        {
            /*query.setParameter("employeeId", employee.getReportsToEmployeeId());
            reportsToEmployee = query.getSingleResult();*/
            reportsToEmployee = employeeRepository.getEmployee(employee.getReportsToEmployeeId());
        }

        /*TypedQuery<State> statesQuery = db.em().createQuery("SELECT s FROM State s WHERE stateId = :stateId", State.class);
        statesQuery.setParameter("stateId", employee.getStateId());
        State state = statesQuery.getSingleResult();*/
        State state = stateRepository.getState(employee.getStateId());

        /*TypedQuery<Employee> reportsQuery = db.em().createQuery("SELECT e FROM Employee e WHERE reportsToEmployeeId = :employeeId", Employee.class);
        reportsQuery.setParameter("employeeId", employeeId);
        List<Employee> reports = reportsQuery.getResultList();*/
        List<Employee> reports = employeeRepository.getReports(employeeId);

        return ok(views.html.employee.render(employee, reports, reportsToEmployee, state));
    }

    @Transactional(readOnly = true)
    public Result getPicture(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        return ok(employee.getPicture()).as("image/jpeg");
    }

    @Transactional(readOnly = true)
    public Result getEmployeeEdit(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        TypedQuery<Title> titleQuery = db.em().createQuery("SELECT t FROM Title t ORDER BY titleName", Title.class);
        List<Title> titles = titleQuery.getResultList();

        TypedQuery<Hobby> hobbyQuery = db.em().createQuery("SELECT h FROM Hobby h ORDER BY hobbyName", Hobby.class);
        List<Hobby> hobbies = hobbyQuery.getResultList();

        return ok(views.html.employeeedit.render(employee, titles, hobbies));
    }

    @Transactional
    public Result postEmployeeEdit(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String firstName = form.get("firstName");
        String lastName = form.get("lastName");

        LocalDate birthdate = LocalDate.parse(form.get("birthdate"));

        int titleId = Integer.parseInt(form.get("titleId"));

        String hobby = form.get("hobbyId");
        Integer hobbyId;
        String newHobbyName = form.get("newHobby");

        if (hobby != null && hobby.length() > 0)
        {
            hobbyId = Integer.parseInt(hobby);
        }
        else if (!newHobbyName.isEmpty())
        {
            Hobby newHobby = new Hobby();
            newHobby.setHobbyName(newHobbyName);
            db.em().persist(newHobby);
            hobbyId = newHobby.getHobbyId();
        }
        else
        {
            hobbyId = null;
        }

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setBirthdate(birthdate);
        employee.setTitleId(titleId);
        employee.setHobbyId(hobbyId);

        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
        File file = filePart.getFile();

        byte[] picture;

        try
        {
            picture = Files.toByteArray(file);

            if (picture != null && picture.length > 0)
            {
                employee.setPicture(picture);
            }
        } catch (Exception e)
        {
            picture = null;
        }

        db.em().persist(employee);

        return ok("saved");
    }

    @Transactional
    public Result postEmployeeDelete(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();
        db.em().remove(employee);

        return ok("DELETED");
    }


        public Result getEmployeeAdd()
    {
        return ok(views.html.employeeadd.render());
    }

    @Transactional
    public Result postEmployeeAdd()
    {
        Employee employee = new Employee();

        DynamicForm form = formFactory.form().bindFromRequest();
        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        Logger.debug(firstName);

        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate birthday = LocalDate.parse("2001-12-20", dateTimeFormatter);
        LocalDate birthday = LocalDate.parse("2001-02-09");
        Logger.debug(birthday.toString());


        db.em().persist(employee);

        return ok("saved");
    }

    @Transactional(readOnly=true)
    public Result getEmployees()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String sortColumn = form.get("sortColumn");
        if (sortColumn == null)
        {
            sortColumn = "";
        }

        String sortOrder = "";

        switch (sortColumn)
        {
            case "firstNameASC":
                sortOrder = "firstName ASC, lastName, employeeId";
                break;
            case "firstNameDESC":
                sortOrder = "firstName DESC, lastName, employeeId";
                break;
            default:
                sortOrder = "lastName, firstName, employeeId";
        }

        String sql = "SELECT e FROM Employee e ORDER BY " + sortOrder;
        TypedQuery<Employee> query = db.em().createQuery(sql, Employee.class);
        List<Employee> employees = query.getResultList();

        return ok(views.html.employees.render(employees));
    }

    @Transactional(readOnly=true)
    public Result getEmployeeSearch()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String name = form.get("name");

        if (name == null)
        {
            name = "";
        }

        name = "%" + name + "%";
        Logger.debug("name:" + name);

        TypedQuery<EmployeeDetail> query =
                db.em().createQuery("SELECT NEW EmployeeDetail(e.employeeId, e.firstName, e.lastName, t.titleName) " +
                                    "FROM Employee e JOIN Title t ON e.titleId = t.titleId " +
                                    "WHERE lastName LIKE :name OR firstName LIKE :name " +
                                    "ORDER BY lastName, firstName, employeeId", EmployeeDetail.class);
        query.setParameter("name", name);
        List<EmployeeDetail> employees = query.getResultList();

        return ok(views.html.employeesearch.render(employees));
    }
}
