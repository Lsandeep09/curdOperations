package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nt.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
