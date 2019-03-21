package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product
{
    @Id
    int productId;

    private String productName;
    private int categoryId;

    public int getProductId()
    {
        return productId;
    }

    public String getProductName()
    {
        return productName;
    }
}
