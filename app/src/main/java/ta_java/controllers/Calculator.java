package ta_java.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Collections;
// import java.util.Pair;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.*; 
import java.util.stream.*; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import ta_java.model.Stock;
import ta_java.service.StockService;
import ta_java.model.Option;
import ta_java.service.OptionService;
import ta_java.exception.ApplicationException;
import ta_java.exception.DatabaseException;



  /**
 * Calculator class for performing Call and Put price calculations
 */
@RestController
@RequestMapping(value = "/calculate")
public class Calculator {   

    public Calculator() { }

    @Autowired
    StockService stockService;

  /**Method that fetches all associated options with underlying stock with ticker and their real time prices based on European Option Pricing Formula.
 * @param ticker Ticker of stock by which options are filtered
 */
    @GetMapping("/list/{ticker}")
    public ResponseEntity<?> getAccounts(@PathVariable String ticker) throws DatabaseException, ResponseStatusException {
        Stock stock = stockService.findByName(ticker);
        if (stock == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock provided is not registered");
        
        List<ImmutablePair<String, Double>> optionsPricings = stock.getOptions().stream().map((x) -> 
            {return new ImmutablePair<>(x.toString(), x.getRealTimeValue());}).collect(Collectors.toList());
        HashMap<String, Object> responseFirstOption = new HashMap<>();
        responseFirstOption.put("Underlying Price", stock.getPrice());
        responseFirstOption.put("Options", optionsPricings);
        return new ResponseEntity<>(responseFirstOption, HttpStatus.OK);
    }

}