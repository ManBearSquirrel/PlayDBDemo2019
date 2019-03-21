package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductDetail
{
    @Id
    private int productId;

    private String productName;
    private String categoryName;

    public ProductDetail(int productId, String productName, String categoryName)
    {
        this.productId = productId;
        this.productName = productName;
        this.categoryName = categoryName;
    }

    public int getProductId()
    {
        return productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
}
