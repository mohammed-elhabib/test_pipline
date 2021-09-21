package dz.itinfinity.orderMS.Entities.TablesEnt;

import dz.itinfinity.orderMS.IDComposites.OrderID;
import dz.itinfinity.orderMS.Entities.Enums.OrderStatus;
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
@IdClass(OrderID.class)
@Table(name = "orderPay")
public class OrderPay {
//    strategy = GenerationType.AUTO
    @Id
    @Column(name = "idOrder", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idOrder;

    @Id
    @Column(name = "Enterprise_idEnterprise", nullable = false, updatable = false)
    private int idEnterprise;

    @Column(name = "orderNumber", nullable = false, updatable = false)
    private String orderNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "acceptationDate" , updatable = false)
    private java.sql.Timestamp acceptationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "reservationDate" , updatable = false)
    private java.sql.Timestamp reservationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "dateOrder", nullable = false, updatable = false)
    private java.sql.Timestamp dateOrder;

    @Column(name = "orderTotal", nullable = false)
    private float orderTotal;

    @Column(name = "totalOutsideTax", nullable = false)
    private float totalOutsideTax;

    @Column(name = "tva", nullable = false)
    private float tva;

    @Column(name = "cancellationReason", nullable = false)
    private String cancellationReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "orderStatus", columnDefinition="enum('reserved','notConfirm','cancel','reservationRequest','paid','initial')")
    private OrderStatus orderStatus;

    @Column(name = "Buyer_idBuyer")
    private int idBuyer;

}
