package com.example.delivery_microservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commune")
public class CommuneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int idCommune;
    @Column(name = "communeName")

    private String communeName;
    @Column(name = "Wilaya_idWilaya")

    private int idWilaya;
    @JsonIgnore
    @OneToMany(mappedBy="commune")
    private Set<AddressEntity> address;


}
