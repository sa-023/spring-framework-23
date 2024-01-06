package com.company.repository;
import com.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //Display all employees with email address ' '
    List<Employee> findByEmail(String email);

    //Display all employees with first name ' ' and last name ' ' , also show all employees with an email address
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName,String lastName,String email);

    //Display all employees that first name is not ' '
    List<Employee> findByFirstNameIsNot(String firstName);

    //Display all employees where last name starts with ' '
    List<Employee> findByLastNameStartsWith(String pattern);

    //Display all employees with salaries higher than ' '
    List<Employee> findBySalaryGreaterThan(Integer salary);

    //Display all employees with salaries less than ' '
    List<Employee> findBySalaryLessThanEqual(Integer salary);

    //Display all employees that has been hired between '' and  ''
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //Display all employees where salaries greater and equal to '' in order
    List<Employee> findBySalaryIsGreaterThanEqualOrderBySalaryDesc(Integer salary);

    //Display top unique 3 employees that is making less than ''
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do not have email address
    List<Employee> findByEmailIsNull();



    // JPQL Query
    @Query("SELECT e FROM Employee e WHERE e.email = 'sdubber7@t-online.de'") // Employee is a class name
    Employee getEmployeeDetail();
    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'sdubber7@t-online.de'")
    Integer getEmployeeSalary();
    @Query("SELECT e FROM Employee e WHERE e.email=?1") // Positional Parameters (?1, ?2, etc).
    Optional<Employee> getEmployeeDetail(String email);
    @Query("SELECT e FROM Employee e WHERE e.email=?1 AND e.salary=?2")
    Employee getEmployeeDetail(String email,int salary);
    @Query("SELECT e FROM Employee e WHERE e.salary <> ?1 ") // <> Not equal sign
    List<Employee> getEmployeeSalaryNotEqual(int salary);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE ?1") // like/contains/startsWith/endsWith
    List<Employee> getEmployeeFirstNameLike(String pattern);

    @Query("SELECT e FROM Employee e WHERE e.salary < ?1") // less than
    List<Employee> getEmployeeSalaryLessThan(int salary);

    @Query("SELECT e FROM Employee e WHERE e.salary > ?1") // greater than
    List<Employee> getEmployeeSalaryGreaterThan(int salary);

    @Query("SELECT e FROM Employee e WHERE e.hireDate > ?1") // Before
    List<Employee> getEmployeeHireDateBefore(LocalDate date);

    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2")  // Between
    List<Employee> getEmployeeSalaryBetween(int salary1,int salary2);

    @Query("SELECT e FROM Employee e WHERE e.email IS NULL") // Null
    List<Employee> getEmployeeEmailIsNull();

    @Query("SELECT e FROM Employee e WHERE e.email IS NOT NULL") // Not Null
    List<Employee> getEmployeeEmailIsNotNull();

    @Query("SELECT e FROM Employee e ORDER BY e.salary") // Sorting in ascending order
    List<Employee> getEmployeeSalaryOrderAsc();

    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC ") // Sorting in descending order
    List<Employee> getEmployeeSalaryOrderDesc();

    @Query("select e from Employee e where e.salary = :salary") // Named Parameters
    List<Employee> getEmployeeSalary(@Param("salary") int salary);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.email = 'admin@email.com' WHERE e.id=:id") // Named Parameters
    void updateEmployeeJPQL(@Param("id") int id);



    // SQL Native Query
    @Query(value = "SELECT * FROM employees WHERE salary ?1",nativeQuery = true) // Native query with Positional Parameters
    List<Employee> readEmployeeDetailBySalary(int salary);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET email = 'admin@email.com' WHERE id=:id",nativeQuery = true) // Native query with Named Parameters
    void updateEmployeeNativeQuery(@Param("id") int id);



}
