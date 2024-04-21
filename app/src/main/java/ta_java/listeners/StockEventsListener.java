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


  @EventListener
  void handleUpdateStock(UpdateStockEvent event) {
    // log.info(event.toString());
    // logger.info("handleUpdateStock happening!"); 
    // process information here and republish the events to new one 
    // where it will be published and streamed into console using pretty print
    System.out.println("New price added and handled!...");
    // getquantity and symbol from db:
  
    String toPrint = "";
    for (Stock s: event.getStock()){
      String symbol = "AAPL";
    double qty = 1000.0;
    double price = s.getPrice();
    double total = price * qty;
    toPrint += String.format("symbol\t\t\tprice\t\t\tqty\t\t\tvalue\n%s\t\t\t%s\t\t\t%s\t\t\t%s", 
    symbol, Double.toString(price), Double.toString(qty), Double.toString(total));
    }

    // String toPrint = System.out.format("%32s%20s%20s%20s", symbol, Double.toString(price), Double.toString(qty), Double.toString(total)).toString();

    applicationEventPublisher.publishEvent(new PrintStockEvent(toPrint));
    // logger.warning("importantttttttttt");
    
  }

  @EventListener
  void handlePrintEventEvent(PrintStockEvent event) {
    // log.info(event.toString());
    // print here very pretty but in a nicer console!:
    // logger.info("New stock update printed!..."); 
    System.out.println("Pretty printing results as Market update");
    System.out.println(event.getSource());

  }
}