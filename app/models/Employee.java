package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Employee
{
    @Id
    private int employeeId;

    private String firstName;
    private String lastName;
    private int titleId;
    private int titleOfCourtesyId;
    private Integer hobbyId;
    private LocalDate birthdate;
    private LocalDate hireDate;
    private String address;
    private String city;
    private String stateId;
    private String zipCode;
    private String personalPhone;
    private String extension;
    private byte[] picture;
    private String notes;
    private Integer reportsToEmployeeId;
    private BigDecimal salary;

    public int getEmployeeId()
    {
        return employeeId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getTitleId()
    {
        return titleId;
    }

    public int getTitleOfCourtesyId()
    {
        return titleOfCourtesyId;
    }

    public Integer getHobbyId()
    {
        return hobbyId;
    }

    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    public LocalDate getHireDate()
    {
        return hireDate;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getStateId()
    {
        return stateId;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public String getPersonalPhone()
    {
        return personalPhone;
    }

    public String getExtension()
    {
        return extension;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public String getNotes()
    {
        return notes;
    }

    public Integer getReportsToEmployeeId()
    {
        return reportsToEmployeeId;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }
}
