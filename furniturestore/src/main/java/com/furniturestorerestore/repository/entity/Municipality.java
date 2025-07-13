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
