package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Entity
public class CategorySummary
{
    @Id
    private int categoryId;

    private String categoryName;
    private long distinctOrders;
    private long totalSold;
    private BigDecimal totalSales;

    public CategorySummary(int categoryId, String categoryName, long distinctOrders, long totalSold, BigDecimal totalSales)
    {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.distinctOrders = distinctOrders;
        this.totalSold = totalSold;
        this.totalSales = totalSales;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public long getDistinctOrders()
    {
        return distinctOrders;
    }

    public String getFormattedDistinctOrders()
    {
        return NumberFormat.getIntegerInstance().format(distinctOrders);
    }

    public long getTotalSold()
    {
        return totalSold;
    }

    public String getFormattedTotalSold()
    {
        return NumberFormat.getIntegerInstance().format(totalSold);
    }

    public BigDecimal getTotalSales()
    {
        return totalSales;
    }

    public String getFormattedTotalSales()
    {
        return NumberFormat.getCurrencyInstance().format(totalSales);
    }

}
