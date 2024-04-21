package ta_java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
@Entity
@Table(name = "account")
@Data
// @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  double price;

  public Employee() {
    this.price = 0.0;
  }

  public Employee(double price) {
    this.price = price;
  }

  public double getName(){
    return this.price;
  }
}