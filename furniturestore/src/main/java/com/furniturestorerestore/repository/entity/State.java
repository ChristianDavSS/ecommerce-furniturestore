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
@Builder
@NoArgsConstructor
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
