package ta_java.events.stockManagement;

import ta_java.model.Stock;
import java.util.List;

  /**
 * Event class to be published by event publishers and consumed by subscribers listening to this event. 
 * This event is for messages that need to be processed for printing as market update as they have updates in 2 second window.
 * Further decoupling may be needed for more granular updates for each stock that may be missed within the 2 second window.
 */
public class UpdateStockEvent {

  private List<Stock> stocks;

  public UpdateStockEvent(List<Stock> stocks) {
    this.stocks = stocks;
  }

  /**
 * Method returns unique stocks in portfolio and their updated prices
 */
  public List<Stock> getStock() {
    return stocks;
  }

  @Override
  public String toString() {
    return "ApplicationEvent: Stock updated :: " + this.stocks;
  }
}