package models;

import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepository
{
    private JPAApi db;

    @Inject
    public EmployeeRepository(JPAApi db)
    {
        this.db = db;
    }

    public Employee getEmployee(int employeeId)
    {
        TypedQuery<Employee> query = db.em().createQuery("SELECT e FROM Employee e WHERE employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        Employee employee = query.getSingleResult();

        return employee;
    }

    public void updateEmployee(Employee employee)
    {
        db.em().persist(employee);
    }

    public List<Employee> getReports(int employeeId)
    {
        TypedQuery<Employee> reportsQuery = db.em().createQuery("SELECT e FROM Employee e WHERE reportsToEmployeeId = :employeeId", Employee.class);
        reportsQuery.setParameter("employeeId", employeeId);
        List<Employee> reports = reportsQuery.getResultList();

        return reports;
    }

}
