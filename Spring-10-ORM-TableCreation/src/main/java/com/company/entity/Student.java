package com.company.entity;
import com.company.enums.Gender;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * 🦋 Table Creation/@Entity
 * · Entities in JPA (Java Persistence API) are nothing but POJOs representing data that can be persisted to the database.
 * · An entity represents a table stored in a database.
 * · Every instance of an entity represents a row in the table.
 * · Entity classes must not be declared final (It should not be able to be extended).
 * · @Entity is a JPA annotation
 *
 * 🦋 Table Creation/@Id
 * · Each JPA entity must have a primary key which uniquely identifies it @Id annotation defines the primary key.
 * · We can generate the identifiers in different ways which are specified by the @GeneratedValue annotation.
 *
 * 🦋 Table Creation/@Enumerated
 * · @Enumerated annotation is used to persist Java Enum type.
 * · If we are going to persist the Gender by the Enum’s ordinal(0 or 1), we do not have to specify the @Enumerated annotation.
 *   However, to persist the Gender by Enum name, we must configure the annotation with EnumType.STRING
 *
 * 🦋 Table Creation / @MappedSuperClass
 * · @MappedSuperclass annotation is used to allow an entity to inherit properties from a base class
 * · @MappedSuperclass inheritance model is invisible since all the base class properties are simply copied to the database table mapped by the actual entity class.
 *
 * 🖍️...
 * · Class Name = Table Name  · Fields Names = Column Name  · Object = Row
 * · @Entity: Spring will create a table.
 * · @Id: Define the field as a primary key.
 * · @GeneratedValue(strategy = GenerationType.IDENTITY): It will instruct hibernate to automatically generate a unique
 *   value for that field during the process of persisting the entity into the database.
 * · @Table(name = ""): Modifies the table name.
 * · @Column(name = ""): Is used to mention the details of a column in the table. Modifies the column name etc.
 * · @Transient: It is used to make a field non-persistent. Fields will be excluded from the table.
 * · @Column(columnDefinition = "DATE"):
 * · @Column(columnDefinition = "TIME"):
 * · @Column(columnDefinition = "TIMESTAMP"):
 * · @Enumerated(EnumType.STRING): It will modify the enum field and convert it to varchar. (Default enum field will be integer 0,1...)
 * · @MappedSuperclass: A table will not be created from this class. @Entity annotated classes will be extended from the @MappedSuperclass annotated class.
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "studentFirstName")
    private String firstName;
    @Column(name = "studentLastName")
    private String lastName;
    private String email;
    @Transient
    private String city;
    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;
    @Column(columnDefinition = "TIME")
    private LocalTime birthTime;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate birthDateTime;
    @Enumerated(EnumType.STRING)
    private Gender gender;




}
