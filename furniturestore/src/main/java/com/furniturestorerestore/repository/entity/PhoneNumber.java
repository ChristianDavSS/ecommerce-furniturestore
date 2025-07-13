package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "userId")
    private MyUser user;
}
