package dz.itinfinity.orderMS.IDComposites;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
@Getter
@Setter
public class BuyerID  implements Serializable {

    private String firstNameB;
    private String lastNameB ;
    private Date birthDayB;

    public BuyerID(){}
    public BuyerID(String firstNameB, String lastNameB, Date birthDayB) {
        this.firstNameB = firstNameB;
        this.lastNameB =lastNameB;
        this.birthDayB = birthDayB;
    }

    @Override
    public boolean equals(Object b) {
        if (this == b) return true;
        if (b == null || getClass() != b.getClass()) return false;
        BuyerID buyerID = (BuyerID) b;
        return (firstNameB == buyerID.firstNameB) && (lastNameB == buyerID.lastNameB)&& (birthDayB == buyerID.birthDayB);
    }
}
