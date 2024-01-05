package com.company.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;
/*
 * 🦋 Fetch Types:
 * · LAZY: Fetch when needed. Ex: @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
 *   Lazy initialization improves performance by avoiding unnecessary computation and reduce memory requirements.
 * · EAGER: Fetch immediately Ex: @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
 *   Eager initialization takes more memory consumption and processing speed is slow.
 * · Default Fetch Types:
 * 1. @OneToOne: FetchType.EAGER
 * 2. @OneToMany: FetchType.LAZY
 * 3. @ManyToOne: FetchType.EAGER
 * 4. @ManyToMany: FetchType.LAZY
 * 🖍️...
 * · For example, we only need payment-related information, but the payment class has a merchant object as well. So, when the application starts,
 *   Hibernate will load all the fields and related data in the payment class, which can cause potential performance issues. To prevent that,
 *   we pass "fetch = FetchType.LAZY" into @OneToMany as a parameter. With the lazy initialization approach, merchant will get initialized only when we explicitly call it.
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String name;
    private String surName;
    private String email;
    private String address;


    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY) // In a One-To-Many relationship, ownership belongs to Many side.
    private List<Payment> paymentList; // One customer can have many payments.

    public Customer(String userName, String name, String surName, String email, String address) {
        this.userName = userName;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.address = address;
    }


}
