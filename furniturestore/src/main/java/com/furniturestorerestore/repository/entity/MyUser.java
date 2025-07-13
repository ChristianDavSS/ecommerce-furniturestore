package com.furniturestorerestore.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.furniturestorerestore.repository.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String paternalSurname;
    @Column(nullable = false)
    private String maternalSurname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Relationships
    @OneToMany(mappedBy = "user")
    List<PhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = "user")
    List<OrderRequest> orderRequests;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "addressId")
    private Address address;
}
