package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, nullable=false)
    private String name;

    @OneToMany(mappedBy = "state")
    List<Address> addresses;

    @OneToMany(mappedBy = "state")
    List<Municipality> municipalities;
}
