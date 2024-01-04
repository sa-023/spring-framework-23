package com.company.model;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
/*
 * 🖍️...
 * · JoinTable: The table that will be created is a result of many-to-many relationships.
 * · @JoinTable(name = "cart_item_rel"): To change the join table name.
 * · @JoinColumn(name = "c_id"): To change the join column name.
 * · inverseJoinColumns = @JoinColumn(name = "i_id"): To change the other join column name.
 * · Ex: @ManyToMany
 *       @JoinTable(name = "cart_item_rel", joinColumns = @JoinColumn(name = "c_id"), inverseJoinColumns = @JoinColumn(name = "i_id"))
 *       private List<Item> itemList;
 */
@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "cart_item_rel", joinColumns = @JoinColumn(name = "c_id"), inverseJoinColumns = @JoinColumn(name = "i_id"))
    private List<Item> itemList;



}
