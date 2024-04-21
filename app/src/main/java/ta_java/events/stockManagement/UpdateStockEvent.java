package ta_java.events.stockManagement;

import ta_java.model.Stock;
import java.util.List;

public class UpdateStockEvent {

  private List<Stock> stocks;

  public UpdateStockEvent(List<Stock> stocks) {
    this.stocks = stocks;
  }

  public List<Stock> getStock() {
    return stocks;
  }

  @Override
  public String toString() {
    return "ApplicationEvent: Stock updated :: " + this.stocks;
  }
}