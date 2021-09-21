package dz.itinfinity.orderMS.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {

    private int idProduct;
    private String productRef;
    private String productName;
    private float price;
    private String unit;
    private String category;
    private String productImage;
    private String productDescription;

    private int quantity;
    private float discount;
    private float total;
}
