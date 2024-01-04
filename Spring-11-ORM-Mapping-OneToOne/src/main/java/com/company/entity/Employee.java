package com.company.entity;
import com.company.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
/*
 * 🦋 Object Relationship (Mapping)
 * · One-to-One · One-to-Many · Many-to-One · Many-to-Many
 *
 * 🌀 One-to-One: @OneToOne
 * · It indicates that one entity is associated with exactly one entity of the other type.
 * · For example, let’s say we have two entities called Course and CourseDetails. And CourseDetails entity captures the additional details about a Course.
 *   Thus, we can say that the Course and CourseDetails entities have a One-to-One relationship as a Course can have only one CourseDetails.
 * · One-to-One association involves a parent side and a child side that are linked via unique foreign key.
 * · A course(child) can not exist without a student(parent)
 * * 🖍️...
 * · Hibernate will join two tables by creating a column for the @OneToOne annotated field and defining it as a foreign key column.
 * · A department column will be created in the employees' table and will be defined as a foreign key Column.
 * · Ex:  @OneToOne
 *        private Department department;
 *
 * 🌀 @JoinColumn
 * · It is used to specify the foreign key column in the OWNER of the relationship. The inverse-side of the relationship
 *   sets the @OneToOne’s mappedBy parameter to indicate that relationship is mapped by the other entity.
 * · @JoinColumn(name = ""): Annotation modifies joined column (foreign key column) name.
 * · Ex: @OneToOne
 *       @JoinColumn(name = "department_id")
 *       private Department department;
 *
 * 🌀 Cascading: @OneToOne(cascade = CascadeType.ALL)
 * · Entity relationships often depend on the existence of another entity.
 * · When we perform some action on the target entity, the same action will be applied to the associated entity.
 * · Different JPA Cascade Types: ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH.
 *
 */
@Entity
@Table(name = "employees")
@NoArgsConstructor
@Data
public class Employee extends BaseEntity{

    private String firstName;
    private String lastName;
    private String email;
    @Column(columnDefinition = "DATE")
    private LocalDate hireDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int salary;


//    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")

    private Department department;
    @OneToOne(cascade = CascadeType.ALL)
    private Region region;

    public Employee(String firstName, String lastName, String email, LocalDate hireDate, int salary, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
        this.gender = gender;
    }



}
