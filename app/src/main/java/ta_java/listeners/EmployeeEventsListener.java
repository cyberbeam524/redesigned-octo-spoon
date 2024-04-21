package ta_java.listeners;

import ta_java.App;
import ta_java.events.employeeMgmt.AddEmployeeEvent;
import ta_java.events.employeeMgmt.DeleteEmployeeEvent;
import ta_java.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory; 
import org.slf4j.Logger; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
// import org.springframework.stereotype.Service;

@Slf4j
@Component
public class EmployeeEventsListener implements ApplicationEventPublisherAware {


    // for republishing events after being processed once and calculations are done:
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
      this.applicationEventPublisher = applicationEventPublisher;
    }

    Logger logger = LoggerFactory.getLogger(EmployeeEventsListener.class); 


  @EventListener
  void handleAddEmployeeEvent(AddEmployeeEvent event) {
    // log.info(event.toString());
    // logger.info("handleAddEmployeeEvent happening!"); 
    // process information here and republish the events to new one 
    // where it will be published and streamed into console using pretty print
    System.out.println("New price added and handled!...");
    // getquantity and symbol from db:
  
    String symbol = "AAPL";
    double qty = 1000.0;
    double price = event.getEmployee().getName();
    double total = price * qty;
    String toPrint = String.format("symbol\t\t\tprice\t\t\tqty\t\t\tvalue\n%s\t\t\t%s\t\t\t%s\t\t\t%s", 
    symbol, Double.toString(price), Double.toString(qty), Double.toString(total));

    // String toPrint = System.out.format("%32s%20s%20s%20s", symbol, Double.toString(price), Double.toString(qty), Double.toString(total)).toString();

    applicationEventPublisher.publishEvent(new DeleteEmployeeEvent(toPrint));
    // logger.warning("importantttttttttt");
    
  }

  @EventListener
  void handleDeleteEmployeeEvent(DeleteEmployeeEvent event) {
    // log.info(event.toString());
    // print here very pretty but in a nicer console!:
    // logger.info("New Employee Deleted!..."); 
    System.out.println("Pretty printing results as Market update");
    System.out.println(event.getSource());

  }
}