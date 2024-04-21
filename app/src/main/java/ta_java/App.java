// /*
//  * This Java source file was generated by the Gradle 'init' task.
//  */
package ta_java;

import ta_java.controllers.Employeecontroller;
import ta_java.listeners.EmployeeEventsListener;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// public class App {
//     public String getGreeting() {
//         return "Hello World!";
//     }

    // public List<List<String>> readCsvFile(File file) throws IOException {
    //     List<List<String>> records = new ArrayList<>();
    //     try (BufferedReader br = new BufferedReader(new FileReader( file))) {
    //         String line;
    //         while ((line = br.readLine()) != null) {
    //             String[] values = line.split(",");
    //             records.add(Arrays.asList(values));
    //         }
    //     }

    //     System.out.println(records);
    //     return records;
    // }

//     public static void main(String[] args) throws IOException {
//         System.out.println(new App().getGreeting());
//         System.out.println("Working Directory = " + System.getProperty("user.dir"));
//         Path currentRelativePath = Paths.get("");
// String s = currentRelativePath.toAbsolutePath().toString();
// System.out.println("Current absolute path is: " + s);


// File folder = new File("../assets");
// File[] listOfFiles = folder.listFiles();

// for (int i = 0; i < listOfFiles.length; i++) {
//   if (listOfFiles[i].isFile()) {
//     System.out.println("File " + listOfFiles[i].getName());
//     List<List<String>> records = new App().readCsvFile(listOfFiles[i]);
//   } else if (listOfFiles[i].isDirectory()) {
//     System.out.println("Directory " + listOfFiles[i].getName());
//   }
// }
//         // new App().readCsvFile();
        
//     }

// }



// package com.howtodoinjava.demo;

import ta_java.model.Employee;
import ta_java.model.Option;
import ta_java.model.Stock;
import ta_java.service.EmployeeService;
import ta_java.service.OptionService;
import ta_java.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController; 

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import static java.lang.Math.sqrt;
import static org.mockito.ArgumentMatchers.booleanThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.exp;

import org.apache.commons.math3.distribution.NormalDistribution;

@EnableScheduling
@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class App implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(App.class);
    // SpringApplication springApplication = 
    //     new SpringApplication(App.class);
    // springApplication.addListeners(new EmployeeEventsListener());
    // springApplication.run(args);
  }

  @Autowired
  EmployeeService employeeService;

  @Autowired
  StockService stockService;

  @Autowired
  OptionService optionService;

  // @Autowired
  // Employeecontroller controller;

  // creating a logger 
  Logger logger = LoggerFactory.getLogger(App.class); 
  // logger

  public List<List<String>> readCsvFile(File file) throws IOException {
    List<List<String>> records = new ArrayList<>();
    boolean first = true;
    try (BufferedReader br = new BufferedReader(new FileReader( file))) {
      
        String line;
        while ((line = br.readLine()) != null) {
          if (first) {
            first = false;
            continue;}
            String[] values = line.split(",");
            records.add(Arrays.asList(values));
        }
    }

    // System.out.println(records);
    return records;
}

  @Override
  public void run(String... args) throws Exception {


//     File folder = new File("../assets");
// File[] listOfFiles = folder.listFiles();

// for (int i = 0; i < listOfFiles.length; i++) {
//   if (listOfFiles[i].isFile()) {
//     System.out.println("File " + listOfFiles[i].getName());
//     List<List<String>> records = new App().readCsvFile(listOfFiles[i]);



//   } else if (listOfFiles[i].isDirectory()) {
//     System.out.println("Directory " + listOfFiles[i].getName());
//   }
// }

File file = new File("../assets/books.csv");
System.out.println("File " + file.getName());
List<List<String>> records = new App().readCsvFile(file);
System.out.println("records:  " + file.getName());
for (List<String> record: records){
  // System.out.println(record);
  Stock s1 = stockService.create(new Stock(record.get(0), Double.parseDouble(record.get(2)), Double.parseDouble(record.get(1)), 0.02));
  System.out.println(s1);
}
    // readfile and create objects:
    Stock s = stockService.create(new Stock("AAPL", 200, 200, 0.02));
    // stockService.getAccounts()
    System.out.println(s);
    Option callOption1 = optionService.create(new Option(50, 5, 30, s, true));
    Option putOption1 = optionService.create(new Option(50, 5, 30, s, false));

    
    // https://stackoverflow.com/questions/10675768/executing-script-file-in-h2-database

    // System.out.println("For stock:" + s);
    // System.out.println("For call option:" + callOption1);
    // System.out.println("Real time value of call option: " + Double.toString(callOption1.getRealTimeValue()));
    // System.out.println("For put option:" + putOption1);
    // System.out.println("Real time value of put option: " + Double.toString(putOption1.getRealTimeValue()));

    // precalculate all the prices for that day here orrrr: fix the start price and calculate all other future prices with diffenet time on scheduled message
  }

  // https://www.javacodegeeks.com/2015/12/geometric-brownian-motion-java.html
  // https://github.com/ylyhlh/Financial-Computing-Homeworks/blob/master/hw2_hl1283/StockPath/BrownianStockPath.java
  // https://www.bezkoder.com/spring-boot-jpa-h2-example/#google_vignette
  // runs every 3 seconds:

  public List<double[]> getLatestBrownianPrice(double mu, double sigma, int years, int initialValue,
  int monthlyValue, double[] breaks){
      double periodizedMu = mu / 12;
      double periodizedSigma = sigma / Math.sqrt(12);
      int periods = years * 12;

      List<double[]> result = new ArrayList<double[]>();
 
      for (int i = 0; i < periods; i++) {
          double value = initialValue + (monthlyValue * i);
          NormalDistribution normalDistribution = new NormalDistribution(periodizedMu * (i + 1),
                  periodizedSigma * sqrt(i + 1));
          double bounds[] = new double[breaks.length];
          for (int j = 0; j < breaks.length; j++) {
              double normInv = normalDistribution.inverseCumulativeProbability(breaks[j]);
              bounds[j] = value * exp(normInv);
          }

          result.add(bounds);
      }
      return result;
  }

  double timeDiffSec = 2;
  double mu = 0.1;
  double sigma = 0.1;
  double randInt = 0.0;
  Random one = new Random();
  double initialPriceS = 2000;

  double getLatestBrownianPrice2(){
      LocalTime myObj = LocalTime.now();
      System.out.println(myObj);
      randInt = one.nextGaussian();
      double newPrice = initialPriceS * (1 + (mu * (timeDiffSec/7257600) + sigma * randInt * Math.sqrt(timeDiffSec/7257600)));
      System.out.println(newPrice);
      initialPriceS = newPrice;
      return initialPriceS;
  }


  @Scheduled(fixedRate = 2000L)
  public void sendMessage() throws Exception{
    System.out.println("Market Update...");
    double price = getLatestBrownianPrice2();
    // employeeService.create(new Employee(price));
    
  }

  // @GetMapping("/2")
  // public String getRealTimeOptionPrice() {

  //   double S = 2000;
  //   double K = 2000;
  //   double r = 0.02;
  //   double sigma_volatility = 0.02;
  //   double t = 2;
  //   NormalDistribution normdist = new NormalDistribution();
  //   double N = normdist.cumulativeProbability(0.02);
  //   double d1 = (java.lang.Math.log(S/K) + (r + (sigma_volatility * sigma_volatility / 2)) * t) / (sigma_volatility * Math.sqrt(t));
  //   double d2 = d1 - (sigma * Math.sqrt(t));
  //   double c = S * normdist.cumulativeProbability(d1) - K * Math.exp(-r * t) *normdist.cumulativeProbability(d2);
  //   double p = K * Math.exp(-r * t) * normdist.cumulativeProbability(-d2) - S * normdist.cumulativeProbability(-d1);
  //   return "Yay";
  // }
}