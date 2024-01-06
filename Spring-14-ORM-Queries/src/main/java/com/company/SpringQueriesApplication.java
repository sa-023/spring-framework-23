package com.company;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 🦋 Queries
 * · Spring Data JPA allows you to execute different kinds of queries to retrieve data from database. You can either use
 *   the method name to derive a query directly, or manually define your own JPQL/Native query using the @Query annotation.
 * · Define the query method in a repository interface that extends one of the Spring Data’s repositories. Spring Data JPA
 *   will create queries automatically by parsing these methods names.
 *
 * 🔺 DERIVED QUERIES
 * · Spring Data comes with the Query Builder mechanism for JPA, and it’s capable of interpreting a query method
 *   name(derived query: the query derived from the method name) and converting it into an SQL statement.
 * · This is possible as long as you follow the naming conventions of this mechanism.
 * 🔎 Derived Query Methods Structure
 * · Introducer clause like find, read, query, count, or get which tells JPA what you want to do with the method. This
 *   clause can contain further expressions, such as Distinct to set a distinct flag on the query to be created.
 * · Criteria clause that starts after the first By keyword. The first By acts as a delimiter to indicate the start of the actual
 *   query criteria. The criteria clause is where you define conditions on entity properties and concatenate them with And, and Or keywords.
 * · Ex: Simple Derived Queries:
 *       List<String> findByLastName(String lastName); -> Introducer(find) · Delimiter(By) · Criteria(LastName)
 *
 * 🔺 CUSTOM QUERIES
 * · Derived queries are good as long as they are not complex. As the number of query parameters go beyond 3-4, you need a more flexible strategy.
 * · Adding sorting, filtering, comparison, and result size limiting keywords to derived queries, it would have become practically impossible to read and maintain these queries.
 * 🔎 Custom Queries using @Query Annotation
 * · For such complicated situations, we should rather use the Spring Data JPA’s @Query annotation to define a custom JPQL or native SQL query.
 * · The @Query annotation defines queries directly on repository methods. This gives us full flexibility to run any query without following the method naming conventions.
 * 🌀 @Query() with JPQL
 * · The Java Persistence Query Language (JPQL) is a platform-independent object-oriented query language defined as part of the JPA specification.
 *   JPQL is used to make queries against entities stored in a relational database. It is heavily inspired by SQL, and its queries resemble SQL
 *   queries in syntax, but operate against JPA entity objects rather than directly with database tables.
 * · JPQL is just an object-oriented way of defining queries based on entity attributes. Spring Data JPA supports both JPQL and native SQL queries.
 *   The JPA implementation you use, which is hibernate by default, will then execute the query and return the result.
 *   The only downside of using JPQL is that it supports a subset of the SQL standard. So, it may not be a great choice for complex queries.
 * · Ex: @Query("SELECT e FROM Employee e WHERE e.email='amcnee1@google.es'")
 *       Employee getEmployeeDetail();
 *       @Query("SELECT e.salary FROM Employee e WHERE e.email='amcnee1@google.es'")
 *       Employee getEmployeeSalary();
 *
 * 🖍️...
 * · Spring Data JPA supports both JPQL and native SQL queries.
 * · When we use a @Query annotation, the method name doesn't matter.
 * · We can use the @Query() annotation with JPQL or native SQL queries.
 * ■ JPQL will perform the actions on the entities (classes and objects).
 * · Ex: @Query("SELECT e FROM Employee e WHERE e.email = 'sdubber7@t-online.de'")
 *   In the above example, "Employee" is a class name, not a table name. And "e" is an object created from the Employee class.
 * ■ Native SQL queries directly perform the action in the database.
 *   Ex: @Query(value = "SELECT * FROM employees WHERE salary ?1",nativeQuery = true)
 *   In the above example, we should add nativeQuery = true at the end of the native query.
 *
 * 🦋 @Query - Positional vs Named Bind Parameters
 * · Bind parameters act as a placeholder in a custom query that must be replaced with actual values before the query gets executed.
 *   There are two ways to bind parameters in Spring Data JPA.
 * · We can either use positional (also called indexed) or named bind parameters. Spring Data JPA treats both JPQL and native SQL queries bind parameters in the same way.
 * ■ Positional Parameters:
 * · A positional bind parameter is referenced by its position in the query. They are defined with ? followed by a number
 *   that specifies the position (?1, ?2, etc). Spring Data JPA will automatically set the bind parameter values.
 *   It replaces the value of each method parameter with the value of a bind parameter in the same position.
 * · Ex: @Query("SELECT e FROM Employee e WHERE e.email = ?1")
 *       Employee getEmployeeByEmail(String email);
 *       @Query("SELECT e FROM Employee e WHERE e.email = ?1 AND e.salary = ?2")
 *       Employee getEmployeeByEmailAndSalary(String email, int salary);
 * ■ Named Parameters:
 * · Named bind parameters are another way of passing method parameter values to the query bind parameters. A named bind
 *   parameter starts with : followed by the name of the parameter.
 * · We can pass method parameters in any order without worrying about their position.
 * · You can use the @Param annotation to specify the name of the bind parameter in the method definition. Each method
 *   parameter annotated with @Param must have a corresponding bind parameter in the JPQL or SQL query.
 * · Ex: @Query("SELECT e FROM Employee e WHERE e.salary = :salary")
 *       List<Employee> getEmployeeSalary(@Param("salary") int salary);
 *       @Query("SELECT e FROM Employee e WHERE e.firstName = :name OR e.salary = :salary")
 *       List<Employee> getEmployeeFirstNameOrSalary(@Param("name") String firstName, @Param("salary") int salary);
 *
 * 🦋 @Modifying
 * · It is used to enhance the @Query annotation to execute not only SELECT queries but also INSERT, UPDATE, DELETE queries.
 * · Ex: @Modifying
 *       @Transactional
 *       @Query("UPDATE Employee e SET e.email = 'admin@email.com' WHERE e.id = :id")
 *       void updateEmployeeJPQL(@Param("id") Integer id);
 * · Ex: @Modifying
 *       @Transactional
 *       @Query(value = "UPDATE employees SET email = 'admin@email.com' WHERE id = :id",nativeQuery = true)
 *       void updateEmployeeNativeQuery(@Param("id") Integer id);
 *
 */

@SpringBootApplication
public class SpringQueriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringQueriesApplication.class, args);
    }





}
