package dz.itinfinity.orderMS.Entities.TablesEnt;

import dz.itinfinity.orderMS.IDComposites.BuyerID;
import dz.itinfinity.orderMS.Entities.Enums.BuyerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(BuyerID.class)
@Table(name = "buyer")
public class Buyer {
//    @Id
    @Column(name = "idBuyer", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idBuyer;

    @Id
    @Column(name = "firstNameB",nullable = false)
    private String firstNameB;

    @Id
    @Column(name = "lastNameB",nullable = false)
    private String lastNameB ;

    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthDayB", nullable = false )
    private Date birthDayB;

    @Column(name = "emailBuyer", nullable = false )
    private String emailBuyer;

    @Column(name = "phoneBuyer", nullable = false )
    private String phoneBuyer;

    @Enumerated(EnumType.STRING)
    @Column(name = "buyerStatus", columnDefinition="enum('person','enterprise')", nullable = false )
    private BuyerStatus buyerStatus;

    @Column(name = "registerCommercialNumberB", nullable = false )
    private String registerCommercialNumberB;

    @Column(name = "taxIdentificationNumberB", nullable = false )
    private String taxIdentificationNumberB;

    @Column(name = "webSiteB", nullable = false )
    private String webSiteB;

    @Column(name = "articleBuyer", nullable = false )
    private String articleBuyer;

    @Column(name = "enterpriseNameBuyer", nullable = false )
    private String enterpriseNameBuyer;

    @Column(name = "Address_idAddress", updatable = true)
    private int idAddress;

}
