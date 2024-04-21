package ta_java.dao;

import ta_java.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


// ListCrudRepository<Employee, Long>,
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}