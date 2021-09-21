package com.example.delivery_microservice.entity;

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
@Table(name = "wilaya")
public class WilayaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idWilaya;
    @Column(name = "wilayaName")

    private String wilayaName;

}
