package dz.itinfinity.orderMS.IDComposites;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class EnterpriseID  implements Serializable {

    private String enterpriseName;
    private String town;

    public EnterpriseID(){}

    public EnterpriseID(String enterpriseName, String town){
        this.enterpriseName =enterpriseName;
        this.town = town;
    }

    @Override
    public boolean equals(Object e) {
        if (this == e) return true;
        if (e == null || getClass() != e.getClass()) return false;
        EnterpriseID enterpriseID = (EnterpriseID) e;
        return (enterpriseName == enterpriseID.enterpriseName) && (town == enterpriseID.town);
    }
}
