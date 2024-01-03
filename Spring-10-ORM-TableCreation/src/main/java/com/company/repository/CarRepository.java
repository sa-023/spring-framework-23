package com.company.repository;
import com.company.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * 🦋 Repository
 * · The Repository is a marker interface and is primarily used to capture the domain class and its ID type information.
 * · A marker interface has no methods or constants and provides run-time type information about objects.
 * · Interface Repository<T,ID> → CrudRepository → PagingAndSortingRepository → JPARepository
 * · T is the type of class. Class of the entity that needs to be created, read, updated, or deleted in a database.
 * · ID is the type of field of the entity. Primary Key Data Type.
 *
 * ❗@Repository: Sprint Stereotype annotation we use at repository level.
 *
 */
@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

}