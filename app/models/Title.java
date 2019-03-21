package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Title
{
    @Id
    private int titleId;

    private String titleName;

    public int getTitleId()
    {
        return titleId;
    }

    public String getTitleName()
    {
        return titleName;
    }
}
