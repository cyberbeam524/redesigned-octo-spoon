package ta_java.events.stockManagement;

import org.springframework.context.ApplicationEvent;


  /**
 * Event class to be published by event publishers and consumed by subscribers listening to this event. 
 * This event is for messages that need to be printed in the right format.
 */
public class PrintStockEvent extends ApplicationEvent {

  public PrintStockEvent(String toPrint) {
    super(toPrint);
  }

  @Override
  public String toString() {
    return "ApplicationEvent: Stocks printing :: " + this.getSource();
  }
}