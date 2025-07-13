package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipality")
    List<Address> addresses;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "stateId")
    private State state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipality")
    List<ZipCode> zipCodes;
}
