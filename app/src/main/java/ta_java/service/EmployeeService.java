package ta_java.service;

import ta_java.dao.EmployeeRepository;
import ta_java.events.employeeMgmt.AddEmployeeEvent;
import ta_java.exception.ApplicationException;
import ta_java.model.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements ApplicationEventPublisherAware {

  @Autowired
  EmployeeRepository repository;
  ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public EmployeeService(EmployeeRepository repository) {
    this.repository = repository;
  }

  public List<Employee> getAccounts() {
    return repository.findAll();
}

  public Employee create(Employee employee) throws ApplicationException {
    Employee newEmployee = repository.save(employee);
    if (newEmployee != null) {
      applicationEventPublisher.publishEvent(new AddEmployeeEvent(newEmployee));
      return newEmployee;
    }
    throw new ApplicationException("Employee could not be saved");
  }
}