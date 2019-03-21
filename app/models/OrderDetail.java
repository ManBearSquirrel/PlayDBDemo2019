package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class OrderDetail
{
    @Id
    private int orderDetailId;

    private BigDecimal unitPrice;
    private int quantity;
    private int productId;
}
