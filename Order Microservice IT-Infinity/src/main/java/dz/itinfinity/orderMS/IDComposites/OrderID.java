package dz.itinfinity.orderMS.IDComposites;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter

public class OrderID  implements Serializable {

    private int idOrder;
    private int idEnterprise;

    public OrderID(){}
    public OrderID(int idOrder, int idEnterprise) {
        this.idOrder =idOrder;
        this.idEnterprise =idEnterprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderID orderID = (OrderID) o;
        return (idOrder == orderID.idOrder) && (idEnterprise == orderID.idEnterprise);
    }
}
