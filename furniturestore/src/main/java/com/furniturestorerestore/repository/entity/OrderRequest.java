package com.furniturestorerestore.repository.entity;

import com.furniturestorerestore.repository.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class OrderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "userId")
    private MyUser user;
}
