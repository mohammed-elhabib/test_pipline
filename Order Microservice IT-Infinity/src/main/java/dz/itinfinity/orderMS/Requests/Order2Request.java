package dz.itinfinity.orderMS.Requests;

import dz.itinfinity.orderMS.Entities.Enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order2Request {

    private int idOrder;
    private String orderNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp dateOrder;
 //   private Date dateOrder;
    private float orderTotal;
    private float totalOutsideTax;
    private float tva;
    private String cancellationReason;
    private OrderStatus orderStatus;

    private Date acceptationDate;
    private Date reservationDate;

    private ProductRequest [] orderLineProducts;

    private BuyerRequest buyer;
    private EnterpriseRequest enterprise;
}
