package dz.itinfinity.orderMS.Requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderLineRequest {

    private int idOrderLine;
    private ProductRequest product;
    private int quantity;
    private float discount;
    private float total;

}
