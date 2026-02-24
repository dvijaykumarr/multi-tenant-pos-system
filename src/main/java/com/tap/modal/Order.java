package com.tap.modal;


import com.tap.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private User cashier;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<OrderItem> items;

    private PaymentType paymentType;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }



}