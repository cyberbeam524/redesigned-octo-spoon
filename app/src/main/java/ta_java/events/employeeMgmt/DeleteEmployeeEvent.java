package ta_java.events.employeeMgmt;

import ta_java.model.Employee;
import org.springframework.context.ApplicationEvent;

public class DeleteEmployeeEvent extends ApplicationEvent {

  public DeleteEmployeeEvent(String toPrint) {
    super(toPrint);
  }

  @Override
  public String toString() {
    return "ApplicationEvent: Employee Deleted :: " + this.getSource();
  }
}