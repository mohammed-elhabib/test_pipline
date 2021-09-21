package dz.itinfinity.orderMS.IDComposites;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ProductID  implements Serializable {

    private String productRef;
    private String productName;
    private int idEnterprise;

    public ProductID(){}
    public ProductID(String productRef, String productName, int idEnterprise) {

        this.productRef = productRef;
        this.productName = productName;
        this.idEnterprise =idEnterprise;
    }

    @Override
    public boolean equals(Object p) {
        if (this == p) return true;
        if (p == null || getClass() != p.getClass()) return false;
        ProductID productID = (ProductID) p;
        return (productRef == productID.productRef) && (productName == productID.productName)&& (idEnterprise == productID.idEnterprise);
    }

}
