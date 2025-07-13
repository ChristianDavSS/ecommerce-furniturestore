package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Neighborhood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "neighborhood")
    List<Address> addresses;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "zipCodeId")
    private ZipCode zipCode;
}
