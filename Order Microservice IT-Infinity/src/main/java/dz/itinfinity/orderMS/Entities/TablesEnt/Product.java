package dz.itinfinity.orderMS.Entities.TablesEnt;

import dz.itinfinity.orderMS.IDComposites.ProductID;
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
@IdClass(ProductID.class)
@Table(name = "product")
public class Product  {

    @Column(name = "idProduct", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idProduct;

    @Id
    @Column(name = "productRef", nullable = false)
    private String productRef;

    @Id
    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "quantityStock", nullable = false)
    private int quantityStock;

    @Column(name = "productImage", nullable = false)
    private String productImage;

    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @Id
    @Column(name = "Enterprise_idEnterprise", nullable = false, updatable = false)
    private int idEnterprise;

}
