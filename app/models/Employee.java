package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Employee
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
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

    public void setTitleId(int titleId)
    {
        this.titleId = titleId;
    }

    public void setHobbyId(Integer hobbyId)
    {
        this.hobbyId = hobbyId;
    }

    public void setBirthdate(LocalDate birthdate)
    {
        this.birthdate = birthdate;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }
}
