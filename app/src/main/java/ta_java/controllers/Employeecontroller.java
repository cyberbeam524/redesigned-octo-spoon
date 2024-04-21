package ta_java.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ta_java.model.Employee;
import ta_java.service.EmployeeService;

@RestController
@RequestMapping(value = "/account")
public class Employeecontroller {

    private final EmployeeService accountQueryService;

    public Employeecontroller(EmployeeService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    // @GetMapping("/{accountId}")
    // public ResponseEntity<Employee> getAccountById(@PathVariable(value = "accountId") Long accountId) {
    //     Optional<Account> accountOpt = accountQueryService.getAccountById(accountId);
    //     return accountOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    @GetMapping("/list")
    public List<Employee> getAccounts() {
        // System.out.println("returning getAccounts" + accountQueryService.getAccounts());
        return accountQueryService.getAccounts();
    }

}