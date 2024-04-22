package ta_java.listeners;

import ta_java.App;
import ta_java.events.stockManagement.UpdateStockEvent;
import ta_java.events.stockManagement.PrintStockEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory; 
import org.slf4j.Logger; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import ta_java.model.Stock;


  /**
 * StockEventsListener class listens to events related to Stocks such as PrintStockEvent and UpdateStockEvent
 */
@Slf4j
@Component
public class StockEventsListener implements ApplicationEventPublisherAware {


    // for republishing events after being processed once and calculations are done:
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
      this.applicationEventPublisher = applicationEventPublisher;
    }

    Logger logger = LoggerFactory.getLogger(StockEventsListener.class); 


  String round(Double value, int roundingNum){
    return String.format("%." + Integer.toString(roundingNum) + "f", value);
  }

  /**Method listens for all events of type UpdateStockEvent and processes data for printing later before triggering PrintStockEvent event
 * @param event Event of type UpdateStockEvent
 */
  @EventListener
  void handleUpdateStock(UpdateStockEvent event) {
    // System.out.println("New price added and handled!...");
    // String toPrint = "symbol\t\t\tprice\tqty\t\t\tvalue\n";
    String fStr = "%20.20s";
    String toPrint = String.format("%-20.20s" + "\t" + fStr + "\t" + "%10.10s" + "\t"+ fStr +"\n", 
    "symbol", "price", "qty", "total");
    Double totalPortfolioValue = 0.0;
    for (Stock s: event.getStock()){
      String symbol = s.getName();
    double qty = s.getQty();
    double price = s.getPrice();
    double total = price * qty;
    toPrint += String.format("%-20.20s" +"\t" + fStr + "\t" + "%10.10s" + "\t"+ fStr +"\n", 
    symbol, round(price, 2), round(qty, 2), round(total, 2));
    totalPortfolioValue += total;
    }

    toPrint += String.format("%-20.20s" +"\t" + fStr + "\t" + "%10.10s" + "\t"+ fStr +"\n", 
    "#Total Portfolio", "", "", round(totalPortfolioValue, 2));
    // String toPrint = System.out.format("%32s%20s%20s%20s", symbol, Double.toString(price), Double.toString(qty), Double.toString(total)).toString();
    applicationEventPublisher.publishEvent(new PrintStockEvent(toPrint));   
  }

  /**Method listens for all events of type PrintStockEvent and prints market data update in the right format in console
 * @param event Event of type PrintStockEvent
 */
  @EventListener
  void handlePrintEventEvent(PrintStockEvent event) {
    // logger.info("New stock update printed!..."); 
    
    System.out.println(event.getSource());

  }
}