package ta_java.service;

import ta_java.dao.EmployeeRepository;
import ta_java.dao.StockRepository;
import ta_java.events.employeeMgmt.AddEmployeeEvent;
import ta_java.exception.ApplicationException;
import ta_java.model.Employee;
import ta_java.model.Stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class StockService implements ApplicationEventPublisherAware {

  @Autowired
  StockRepository repository;
  ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public StockService(StockRepository repository) {
    this.repository = repository;
  }

  public List<Stock> getAccounts() {
    return repository.findAll();
}

  public Stock create(Stock stock) throws ApplicationException {
    Stock newStock = repository.save(stock);
    if (newStock != null) {
      // applicationEventPublisher.publishEvent(new AddEmployeeEvent(newEmployee));
      return newStock;
    }
    throw new ApplicationException("newStock could not be saved");
  }
}