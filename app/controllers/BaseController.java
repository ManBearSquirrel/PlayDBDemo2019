package controllers;

import models.Employee;
import play.Logger;
import play.mvc.Controller;

public class BaseController extends Controller
{
    private final String LOGGED_IN_EMPLOYEE = "LoggedInEmployeeId";

    public void login(Employee employee)
    {
        session().put(LOGGED_IN_EMPLOYEE, "" + employee.getEmployeeId());
    }

    public void logout()
    {
        session().remove(LOGGED_IN_EMPLOYEE);
    }

    public boolean isLoggedIn()
    {
        return session().containsKey(LOGGED_IN_EMPLOYEE);
    }

    public Integer getLoggedInEmployeeId()
    {
        String employeeIdText = session().get(LOGGED_IN_EMPLOYEE);

        Integer employeeId = null;

        if (employeeIdText != null)
        {
            employeeId = Integer.parseInt(employeeIdText);
        }

        return employeeId;
    }
}
