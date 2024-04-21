package ta_java.service;

import ta_java.dao.OptionRepository;
import ta_java.model.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import ta_java.events.stockManagement.UpdateStockEvent;
import ta_java.events.stockManagement.PrintStockEvent;
import ta_java.exception.DatabaseException;

import java.util.List;
import java.util.ArrayList;


  /**
 * This Service allows saving and updating of Option records in database
 */
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

  /**
 * This method all the option records from database
 */
  public List<Option> getAllOptions() {
    return repository.findAll();
}

  /**
 * @param option Option instance to be saved in database
 * @return saved Option result in database
 * @throws DatabaseException
 */
  public Option create(Option option) throws DatabaseException {
    Option newOption = repository.save(option);
    if (newOption != null) {
      // applicationEventPublisher.publishEvent(new AddEmployeeEvent(newEmployee));
      return newOption;
    }
    throw new DatabaseException("Option could not be saved");
  }
}