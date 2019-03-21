package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeDetail
{
    @Id
    private int employeeId;

    private String firstName;
    private String lastName;
    private String titleName;

    public EmployeeDetail(int employeeId, String firstName, String lastName, String titleName)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleName = titleName;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getTitleName()
    {
        return titleName;
    }
}
