package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hobby
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hobbyId;

    private String hobbyName;

    public int getHobbyId()
    {
        return hobbyId;
    }

    public String getHobbyName()
    {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName)
    {
        this.hobbyName = hobbyName;
    }
}
