package dz.itinfinity.orderMS.Requests;

import dz.itinfinity.orderMS.Entities.Enums.EnterpriseStatus;
import dz.itinfinity.orderMS.Entities.Enums.PublicPrivate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnterpriseRequest {

    private int idEnterprise;
    private String enterpriseName;
    private String town;
    private String webSite;
    private String taxIdentificationNumberS;
    private String registerCommercialNumberS;
    private String accountNumber;
    private String accountType;
    private String logoImage;
    private String articleSeller;
    private float transportCosts;
    private int deliveryTime;
    private int reservationTime;
    private int acceptationTime;
    private int validationTime;
    private int orderRecoveryTime;

    private PublicPrivate publicPrivate;
    private EnterpriseStatus enterpriseStatus;
    private String[] paymentMethod;

    private List<Integer> deliveryPoints;

    private int address;
    private int contact;
}
