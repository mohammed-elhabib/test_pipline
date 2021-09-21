package dz.itinfinity.orderMS.IDComposites;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class OrderLineID  implements Serializable {
    private int idOrder;
    private int idProduct;

    public OrderLineID(){}

    public OrderLineID(int idOrder, int idProduct) {
        this.idOrder =idOrder;
        this.idProduct =idProduct;
    }

    @Override
    public boolean equals(Object ol) {
        if (this == ol) return true;
        if (ol == null || getClass() != ol.getClass()) return false;
        OrderLineID orderLineID = (OrderLineID) ol;
        return (idOrder == orderLineID.idOrder) && (idProduct == orderLineID.idProduct);
    }
}
