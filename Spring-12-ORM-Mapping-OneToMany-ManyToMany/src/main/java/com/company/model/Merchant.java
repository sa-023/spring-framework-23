package com.company.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
/*
 * 🖍️...
 * · @OneToMany(mappedBy = "merchant"): In a One-To-Many relationship, ownership belongs to Many side.
 * · When we just provide the @OneToMany annotation with the "paymentList" field, Hibernate will try to create a foreign
 *   key column in the merchant table, but because "paymentList" is a list, it creates an additional table in DB named merchants_payment_list.
 *   So we have to annotate paymentList with @OneToMany(mappedBy = "merchant") to prevent that.
 * · Ex: @OneToMany(mappedBy = "merchant")
 *       private List<Payment> paymentList;
 */
@Entity
@Table(name = "merchants")
@Data
@NoArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private BigDecimal transactionFee;
    private BigDecimal commissionRate;
    private Integer payoutDelayCount;

    @OneToMany(mappedBy = "merchant") // In a One-To-Many relationship, ownership belongs to Many side.
    private List<Payment> paymentList; // One merchant can have many payments.

    public Merchant(String name, String code, BigDecimal transactionFee, BigDecimal commissionRate, Integer payoutDelayCount) {
        this.name = name;
        this.code = code;
        this.transactionFee = transactionFee;
        this.commissionRate = commissionRate;
        this.payoutDelayCount = payoutDelayCount;
    }



}
