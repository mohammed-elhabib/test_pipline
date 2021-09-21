package dz.itinfinity.orderMS.Entities.TablesEnt;

import dz.itinfinity.orderMS.Entities.Enums.EnterpriseStatus;
import dz.itinfinity.orderMS.IDComposites.EnterpriseID;
import dz.itinfinity.orderMS.Entities.Enums.PublicPrivate;
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
@IdClass(EnterpriseID.class)
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @Column(name = "idEnterprise", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idEnterprise;

    @Id
    @Column(name = "enterpriseName", nullable = false)
    private String enterpriseName;

    @Id
    @Column(name = "town", nullable = false)
    private String town;

    @Column(name = "webSite", nullable = false)
    private String webSite;

    @Column(name = "taxIdentificationNumberS", nullable = false)
    private String taxIdentificationNumberS;

    @Column(name = "registerCommercialNumberS", nullable = false)
    private String registerCommercialNumberS;

    @Column(name = "accountNumber", nullable = false)
    private String accountNumber;

    @Column(name = "accountType", nullable = false)
    private String accountType;

    @Column(name = "logoImage", nullable = false)
    private String logoImage;

    @Column(name = "transportCosts", nullable = false)
    private float transportCosts;

    @Column(name = "deliveryTime", nullable = false)
    private int deliveryTime;

    @Column(name = "reservationTime", nullable = false)
    private int reservationTime;

    @Column(name = "acceptationTime", nullable = false)
    private int acceptationTime;

    @Column(name = "validationTime", nullable = false)
    private int validationTime;

    @Column(name = "orderRecoveryTime", nullable = false)
    private int orderRecoveryTime;

    @Column(name = "articleSeller", nullable = false)
    private String articleSeller;

    @Enumerated(EnumType.STRING)
    @Column(name = "publicPrivate", columnDefinition="enum('Public','Private')", nullable = false )
    private PublicPrivate publicPrivate;

    @Enumerated(EnumType.STRING)
    @Column(name = "enterpriseStatus", columnDefinition="enum('active','notActive')", nullable = false )
    private EnterpriseStatus enterpriseStatus;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "Address_idAddress", nullable = false, updatable = false)
    private int idAddress;

    @Column(name = "Contact_idContact", nullable = false, updatable = false)
    private int idContact;
}
