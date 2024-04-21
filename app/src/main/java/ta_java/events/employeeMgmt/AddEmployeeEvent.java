package ta_java.events.employeeMgmt;

import ta_java.model.Employee;

public class AddEmployeeEvent {

  private Employee employee;

  public AddEmployeeEvent(Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee() {
    return employee;
  }

  @Override
  public String toString() {
    return "ApplicationEvent: New Employee Saved :: " + this.employee;
  }
}