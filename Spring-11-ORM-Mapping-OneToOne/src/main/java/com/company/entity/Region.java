package com.company.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
@NoArgsConstructor
@Data
public class Region extends BaseEntity{

    private String region;
    private String country;

    @OneToOne(mappedBy = "region")//Means we don't create an employee foreign key column in the Region Table (foreign key); we create it in the Employee Table.
    private Employee employee;

    public Region(String region, String country) {
        this.region = region;
        this.country = country;
    }



}
