package ta_java.model;


import java.util.HashMap;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.stream.*; 

import org.apache.commons.math3.distribution.NormalDistribution;


/**
 * Option class that has a many to one relationship with each Stock
 */
// @Entity
// @Data
// @NoArgsConstructor
@Entity
@Table(name = "option")
@Data
// @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Option{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    Double timeToMaturity;
    Double fixedStrikePrice;
    Boolean callOption;


    @ManyToOne
    @JoinColumn(name = "stock_id", nullable=false)
    private Stock stock;


  public Option() {
    this.price = 0.0;
    this.timeToMaturity = 0.0;
    this.fixedStrikePrice = 0.0;
    this.callOption = false;
    this.stock = null;
  }

  public Option(double price, double timeToMaturity, double fixedStrikePrice, Stock stock, boolean callOption) {
    this.price = price;
    this.timeToMaturity = timeToMaturity;
    this.fixedStrikePrice = fixedStrikePrice;
    this.callOption = callOption;
    this.stock = stock;
  }

  public Double getPrice(){
    return this.price;
  }

  public Double getTimeToMaturity(){
    return this.timeToMaturity;
  }

  public Double getFixedStrikePrice(){
    return this.fixedStrikePrice;
  }

  public Double getRealTimeValue(){
    double S = this.stock.getPrice();
    double K = this.fixedStrikePrice;
    double r = 0.02;
    double sigma_volatility = this.stock.getVolatility();
    double t = this.timeToMaturity;
    NormalDistribution normdist = new NormalDistribution();
    double d1 = (java.lang.Math.log(S/K) + (r + (sigma_volatility * sigma_volatility / 2)) * t) / (sigma_volatility * Math.sqrt(t));
    double d2 = d1 - (sigma_volatility * Math.sqrt(t));
    Double value = this.callOption ? S * normdist.cumulativeProbability(d1) - K * Math.exp(-r * t) *normdist.cumulativeProbability(d2) : 
    K * Math.exp(-r * t) * normdist.cumulativeProbability(-d2) - S * normdist.cumulativeProbability(-d1);
    return value;
  }

  public String toString(){
    return String.format("[stock:%s]%s - $%s", this.stock.getName(), this.callOption ? "Call" : "Put", this.price);
   }



}