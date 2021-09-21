package dz.itinfinity.orderMS.Requests;

import dz.itinfinity.orderMS.Entities.Enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    private int idOrder;
    private String orderNumber;
    private java.sql.Timestamp dateOrder;
 //   private Date dateOrder;
    private float orderTotal;
    private float totalOutsideTax;
    private float tva;
    private String cancellationReason;
    private OrderStatus orderStatus;

    private List<ProductRequest> orderLineProducts;

    private BuyerRequest buyer;
    private EnterpriseRequest enterprise;
    private int idEnterprise;
    private int idBuyer;
}
