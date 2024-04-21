package ta_java.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.math3.distribution.NormalDistribution;

import ta_java.model.Stock;
import ta_java.service.StockService;

@RestController
@RequestMapping(value = "/calculate")
public class Calculator {

    public Calculator() { }

    @GetMapping("/list")
    public HashMap<String, Double> getAccounts() {
    double S = 2000;
    double K = 2000;
    double r = 0.02;
    double sigma_volatility = 0.02;
    double t = 2;
    NormalDistribution normdist = new NormalDistribution();
    double N = normdist.cumulativeProbability(0.02);
    double d1 = (java.lang.Math.log(S/K) + (r + (sigma_volatility * sigma_volatility / 2)) * t) / (sigma_volatility * Math.sqrt(t));
    double d2 = d1 - (sigma_volatility * Math.sqrt(t));
    Double c = S * normdist.cumulativeProbability(d1) - K * Math.exp(-r * t) *normdist.cumulativeProbability(d2);
    Double p = K * Math.exp(-r * t) * normdist.cumulativeProbability(-d2) - S * normdist.cumulativeProbability(-d1);
    // List<Double> list = new ArrayList<Double> (); 
    // list.add(c);
    // list.add(p);
    HashMap<String, Double> response = new HashMap<>();
    response.put("Call", c);
    response.put("Put", p);
    response.put("Underlying Price", S);
    // return p;
    return response;
    }

}