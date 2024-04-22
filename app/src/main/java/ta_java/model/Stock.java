package ta_java.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.stream.*; 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.stream.*; 

import java.util.*; 



/**
 * Stock class with price, quantity and volatility attributes for its associated ticker.
 */
// @Entity
// @Data
// @NoArgsConstructor
@Entity
@Table(name = "stocks")
@Data
// @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String name;
  Double price;
  Double qty;
  Double volatility;

  @OneToMany(mappedBy = "stock", cascade = CascadeType.REMOVE)
  private List<Option> options;

  public Stock() {
    this.name = "null";
    this.price = 0.0;
    this.qty = 0.0;
    this.volatility = 0.0;
  }


  public Stock(String name, double price, double qty, double volatility) {
    this.name = name;
    this.price = price;
    this.qty = qty;
    this.volatility = volatility;

  }

  public String getName(){
    return this.name;
  }

  public Double getPrice(){
    return this.price;
  }

  public void setPrice(double price){

    this.price = price;
  }

  public Double getQty(){
    return this.qty;
  }

  public Double getValue(){
    return this.price * this.qty;
  }
   public Double getVolatility(){
    return this.volatility;
   }

  public List<Option> getOptions(){
    return options;
    }

   public Stream<Option> getOptionsStream(){
    Stream.Builder<Option> optStreamBuilder = Stream.builder();
    for (Option i: options) optStreamBuilder.accept(i);
    return optStreamBuilder.build();
   }

   public String toString(){
    return String.format("%s:%s", this.name, Double.toString(this.price));
   }

  

}