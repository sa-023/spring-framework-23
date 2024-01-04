package com.company.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;
/*
 * 🖍️...
 * · In Many-To-Many relationship, an additional table will be created, and we should choose which class will have ownership.
 * · So in below example, ownership belongs to the cart class.
 * · Ex: @ManyToMany(mappedBy = "itemList")
 *       private List<Cart> cart;
 */

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToMany(mappedBy = "itemList") // The ownership will belong to the cart class.
    private List<Cart> cart;

    public Item(String name, String code) {
        this.name = name;
        this.code = code;
    }


}
