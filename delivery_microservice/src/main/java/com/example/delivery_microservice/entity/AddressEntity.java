package com.example.delivery_microservice.entity;

import com.example.delivery_microservice.composite_Id.AddressId;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(AddressId.class)
@Table(name = "address")
public class AddressEntity{
    @Column(name = "idAddress",unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAddress;


    @Column(name = "homeNumber")

    private String  homeNumber;
    @Id
    @Column(name = "floor")

    private  String floor;
    @Id
    @Column(name = "street")
    @NotNull
    private String street;

    @Id
    @Column(name = "city")

    private String city;
    @Column(name = "codeZip")

    private String codeZip;

    @Id
    @Column(name = "Commune_idCommune")
    private int idCommune;

    @ManyToOne
    @JoinColumn(name = "Commune_idCommune",referencedColumnName = "idCommune",insertable = false, updatable = false)
    private CommuneEntity commune;





}
