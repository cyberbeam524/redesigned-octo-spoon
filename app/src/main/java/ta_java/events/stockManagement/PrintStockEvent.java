package ta_java.events.stockManagement;

import org.springframework.context.ApplicationEvent;

public class PrintStockEvent extends ApplicationEvent {

  public PrintStockEvent(String toPrint) {
    super(toPrint);
  }

  @Override
  public String toString() {
    return "ApplicationEvent: Stocks printing :: " + this.getSource();
  }
}