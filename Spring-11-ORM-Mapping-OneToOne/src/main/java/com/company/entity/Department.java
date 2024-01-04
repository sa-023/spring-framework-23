package com.company.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/*
 * 🦋 Bidirectional Relationship
 * · When we have a bidirectional relationship between objects, it means that we are able to access Object A from Object B, and Object B from Object A.
 * · A relationship between two tables where both tables have a foreign key that references the primary key of the other table.
 * · There are 4 different types of Bidirectional association which are as follows:
 * 1. One-to-One Bidirectional Association
 * 2. One-to-Many Bidirectional Association
 * 3. Many-to-One Bidirectional Association
 * 4. Many-to-Many Bidirectional Association
 *
 * 🌀 mappedBy: @OneToOne(mappedBy = "department")
 * · The mappedBy attribute characterizes a bidirectional association and must be set on the parent side.
 * · It signals hibernate that key for the relationship is on the other side.
 * 🖍️...
 * · We would like to access the employee details from the department table. The Department Table is not the owner in this relationship;
 *   the owner is the Employee Table. When we create an employee field in the department class and annotate it with @OneToOne,
 *   it will create an employee foreign key column in the department table, which we would like to avoid. To prevent that,
 *   we pass the mappedBy = "department" parameter to the @OneToOne() annotation. In this case, the foreign key column will not be
 *   created in the department table, but we are still able to access employee details from the department.
 * · (mappedBy = "department") will map the employee field to the department reference that we have in the Employee class.
 *   In this way, the employee column will NOT be created on the department table, and we will still be able to reach the
 *   employee fields when we call it with employee.getDepartment().
 *
 * · Ex: @OneToOne(mappedBy = "department")
 *       private Employee employee;
 *   ❗ The department name should match exactly the department field we provided in the employee class.
 */
@Entity
@Table(name = "departments")
@NoArgsConstructor
@Data
public class Department extends BaseEntity {

    private String department;
    private String division;
    @OneToOne(mappedBy = "department") // The department name should match exactly the department field we provided in the employee class.
    private Employee employee;


    public Department(String department, String division) {
        this.department = department;
        this.division = division;

    }


}
