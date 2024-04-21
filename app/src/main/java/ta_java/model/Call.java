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


/**
 * Call class that has a price associated with it
 */
// @Entity
// @Data
// @NoArgsConstructor
@Entity
@Table(name = "call")
@Data
// @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Call {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  double price;

  public Call() {
    this.price = 0.0;
  }

  public Call(double price) {
    this.price = price;
  }

  public double getPrice(){
    return this.price;
  }
}