package dz.itinfinity.orderMS.Requests;

import dz.itinfinity.orderMS.Entities.Enums.BuyerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyerRequest {

    private int idBuyer;
    private String firstNameB;
    private String lastNameB ;
    private Date birthDayB ;
    private String emailBuyer;
    private String phoneBuyer;
    private BuyerStatus buyerStatus;
    private String registerCommercialNumberB;
    private String taxIdentificationNumberB;

    private String articleBuyer;
    private String enterpriseNameBuyer;
    private String webSiteB;

    private int address;

}
