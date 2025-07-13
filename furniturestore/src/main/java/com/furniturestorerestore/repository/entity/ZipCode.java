package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 5)
    private String zipCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "municipalityId")
    private Municipality municipality;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipCode")
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipCode")
    private List<Neighborhood> neighborhoods;
}
