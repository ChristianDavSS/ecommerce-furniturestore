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
