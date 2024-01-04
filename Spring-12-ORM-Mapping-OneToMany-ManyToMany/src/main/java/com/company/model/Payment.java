package com.company.model;
import com.company.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
/*
 * 🖍️...
 * · Many-to-One indicates that many entities of one type are associated with one entity of the other type.
 * · @ManyToOne: Many payments belong to one merchant.
 * · Cascading will not work with one-to-many relationship. It works only with one-to-one relationship.
 */
@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private LocalDate createdDate;

    private BigDecimal amount;
    private Status paymentStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private PaymentDetail paymentDetail;

    @ManyToOne // Many payments belong to one merchant.
    private Merchant merchant;

    @ManyToOne // Many payments belong to one customer.
    private Customer customer;

    public Payment(LocalDate createdDate, BigDecimal amount, Status paymentStatus) {
        this.createdDate = createdDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }


}