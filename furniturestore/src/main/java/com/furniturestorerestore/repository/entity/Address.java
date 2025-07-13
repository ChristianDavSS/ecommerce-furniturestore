package com.furniturestorerestore.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String houseNumber;

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
