package com.company;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * 🦋 Spring Template
 * · Your app can use a data source to obtain connections to the database server efficiently. But how easily can you write code to work with the data?
 * · Directly using JDBC classes provided by the JDK has proved not to be a comfortable way to work with the persisted data.
 *   You have to write verbose blocks of code, even for the simplest operations.
 * · App Logic (uses ->) Spring JDBC Template (implements Data Source) -> JDBC Driver -> DBMS.
 * · JDBC is not the only approach you can use to connect to a relational database.
 * · Another common way to implement data persistence is using an Object Relational Mapping(ORM) Framework.
 *
 * 🦋 ORM (Object Relational Mapping)
 * · ORM is a programming technique for converting data between relational database and object-oriented programming languages.
 * · Java Class (Entity) ←→ ORM ←→  Database Table.
 * · ORM Frameworks: Hibernate, iBatis/MyBatis, TopLink
 * · Hibernate is an open source framework for saving Java Objects in a database.
 * · Spring APP can use JDBC directly, or an ORM framework such as Hibernate.
 * · Hibernate is an ORM persistence framework that relies on JDBC and simplifies some aspects of working with persisted data.
 * · When you use Hibernate, all database interactions still occur with the JDBC APIs. Hibernate and JPA are actually built on top of the JDBC API
 *
 * 🦋 JPA(Java Persistence API)
 * · JPA is not a tool or framework.
 * · It defines a set of concepts that can be implemented by any tool or framework.
 * · JPA’s object relational mapping model is originally based on Hibernate.
 * · As JPA is just a specification, it does not perform any operation by itself. It requires an implementation. So, ORM tools
 *   like Hibernate, TopLink, and iBatis implements JPA specifications for data persistence.
 * · Application Architecture Flow: Client -> Service Layer -> Repository Level (CRUD,JPA←→Hibernate) -> Database.
 *
 * 🦋 Spring Data
 * · Spring Data is a Spring ecosystem project that simplifies the persistence layer’s (DB related code) development by providing
 *   implementations according to the persistence technology we use.
 * · Spring data lets you access data from a variety of data sources (Relational and non-relational databases).
 * · It is an umbrella project under the Spring Framework that contains several subprojects each of which targets a specific database.
 * · Spring Data provides a repository abstraction layer across the supported databases as a common programming model. The abstraction is contained in
 *   the Spring Data Commons module, and it provides several useful interfaces that let you perform the standard CRUD operations as well as execute queries.
 * 🔺 Spring Data Modules:
 * · Spring Data Commons: It contains the foundational components used in all Spring Data projects
 * · Spring Data JDBC: This module provides repository support for JDBC
 * · Spring Data JPA: It provides repository support for JPA. We use Spring Data JPA to develop the repository layer.
 * · Spring Data MongoDB: It provides support for document-based MongoDB database
 * · Spring Data REST: It lets you access Spring data repositories at REST resources
 * · Spring Data for Apache Cassandra: This module provides the necessary support for ApacheCassandra
 *
 * 🖍️... Spring Data JPA (Java Persistence API)
 * · Reduce the amount of boilerplate code required to implement data access object (Data Access Object, DAO pattern) layer.
 * · Spring Data JPA is not a JPA provider. It simply "hides" the Java Persistence API (and the JPA provider) behind its repository abstraction.
 * · Spring Data JPA uses Hibernate as a default JPA provider.
 *
 * ⬇️ pom.xml dependencies:
 * · Spring Data JPA ->  Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
 * · PostgreSQL Driver ->  A JDBC and R2DBC driver that allows Java programs to connect to a PostgreSQL database using standard, database independent Java code
 *
 * 🔺 Configuring a Database
 * · To configure a relational database with Spring Boot, we can add spring-boot-starter-data-jpa and the relational database driver
 *   dependency in the pom.xml of the spring boot application.
 * · Configuration/Setting Properties: Spring Boot application contains an application.properties file that let us configure database properties.
 *
 */
@SpringBootApplication
public class CompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}

}
