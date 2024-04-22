package ta_java.service;

import ta_java.dao.StockRepository;
import ta_java.events.stockManagement.UpdateStockEvent;
import ta_java.events.stockManagement.PrintStockEvent;
import ta_java.exception.ApplicationException;
import ta_java.exception.DatabaseException;
import ta_java.model.Stock;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

  /**
 * This Service allows saving and updating of Stock records in database
 */
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

  /**
 * This method all the stock records from database
 */
  public List<Stock> getAccounts() {
    return repository.findAll();
}

/**
 * @param stock Stock instance to be created in database
 * @return saved Stock result in database
 * @throws DatabaseException
 */
  public Stock create(Stock stock) throws DatabaseException {
    Stock newStock = repository.save(stock);
    if (newStock != null) {
      return newStock;
    }
    throw new DatabaseException("newStock could not be saved");
  }

    public Stock findByName(String stockName) throws DatabaseException {
    Stock newStock = repository.findByName(stockName);
    // if (newStock != null) {
    //   return newStock;
    // }
    // throw new DatabaseException("Stock could not be found");
    return newStock;
  }

  // todo: stock service on update publish message for update stock event
  /**
 * @param stock Stock instance to be updated in database
 * @return saved Stock result in database
 * @throws DatabaseException
 */
  public Stock update(Stock stock) throws DatabaseException {
    Stock newStockRecord = repository.save(stock);
    if (newStockRecord != null) {
      return newStockRecord;
    }
    throw new DatabaseException("stock could not be updated");
  }

    // todo: stock service on update publish message for update stock event
  /**
 * @param stocks Stock instances to be updated in database and published UpdateStockEvent that triggers recalculation of portfolio.
 * @return saved Stock results in database
 * @throws DatabaseException
 */
  public List<Stock> updateMultiple(List<Stock> stocks) throws DatabaseException {
    List<Stock> stocksUpdated = new ArrayList<>();
    for (Stock s: stocks){
      Stock newStockRecord = repository.save(s);
      if (newStockRecord == null) throw new DatabaseException("stocks could not be updated");
      else stocksUpdated.add(newStockRecord);
    }
    applicationEventPublisher.publishEvent(new UpdateStockEvent(stocksUpdated));
    return stocksUpdated;
  }

}