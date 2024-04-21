package ta_java.service;

import ta_java.dao.StockRepository;
import ta_java.events.stockManagement.UpdateStockEvent;
import ta_java.events.stockManagement.PrintStockEvent;
import ta_java.exception.ApplicationException;
import ta_java.model.Stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
      return newStock;
    }
    throw new ApplicationException("newStock could not be saved");
  }

  // todo: stock service on update publish message for update stock event
  public Stock update(Stock stock) throws ApplicationException {
    Stock newStockRecord = repository.save(stock);
    if (newStockRecord != null) {
      return newStockRecord;
    }
    throw new ApplicationException("stock could not be updated");
  }

    // todo: stock service on update publish message for update stock event
  public List<Stock> updateMultiple(List<Stock> stocks) throws ApplicationException {
    List<Stock> stocksUpdated = new ArrayList<>();
    for (Stock s: stocks){
      Stock newStockRecord = repository.save(s);
      if (newStockRecord == null) throw new ApplicationException("stocks could not be updated");
      else stocksUpdated.add(newStockRecord);
    }
    applicationEventPublisher.publishEvent(new UpdateStockEvent(stocksUpdated));
    return stocksUpdated;
  }

}