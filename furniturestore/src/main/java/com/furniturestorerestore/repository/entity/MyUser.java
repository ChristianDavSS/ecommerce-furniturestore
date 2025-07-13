package com.furniturestorerestore.repository.entity;

import com.furniturestorerestore.repository.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
    @JoinColumn(nullable = true, name = "addressId")
    private Address address;
}
