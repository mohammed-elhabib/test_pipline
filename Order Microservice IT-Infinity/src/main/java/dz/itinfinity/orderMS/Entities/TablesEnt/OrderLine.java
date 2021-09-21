package dz.itinfinity.orderMS.Entities.TablesEnt;

import dz.itinfinity.orderMS.IDComposites.OrderLineID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(OrderLineID.class)
@Table(name = "orderline")
public class OrderLine {

    @Column(name = "idOrderLine", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idOrderLine;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "discount")
    private float discount;

    @Column(name = "total")
    private float total;

    @Id
    @Column(name = "Order_idOrder")
    private int idOrder;

    @Id
    @Column(name = "Product_idProduct", nullable = false, updatable = false)
    private int idProduct;

}
