package ta_java.service;

import ta_java.dao.EmployeeRepository;
import ta_java.dao.OptionRepository;
import ta_java.events.employeeMgmt.AddEmployeeEvent;
import ta_java.exception.ApplicationException;
import ta_java.model.Employee;
import ta_java.model.Option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class OptionService implements ApplicationEventPublisherAware {

  @Autowired
  OptionRepository repository;
  ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public OptionService(OptionRepository repository) {
    this.repository = repository;
  }

  public List<Option> getAllOptions() {
    return repository.findAll();
}

  public Option create(Option option) throws ApplicationException {
    Option newOption = repository.save(option);
    if (newOption != null) {
      // applicationEventPublisher.publishEvent(new AddEmployeeEvent(newEmployee));
      return newOption;
    }
    throw new ApplicationException("Option could not be saved");
  }
}