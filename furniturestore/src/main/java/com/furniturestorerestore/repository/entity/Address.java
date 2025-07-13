package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    // Relationships
    @OneToMany(mappedBy = "address")
    List<MyUser> myUsers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "stateId")
    private State state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "municipalityId")
    private Municipality municipality;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "zipCodeId")
    private ZipCode zipCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "neighborhoodId")
    private Neighborhood neighborhood;
}
