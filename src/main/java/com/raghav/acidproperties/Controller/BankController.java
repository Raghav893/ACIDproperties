package com.raghav.acidproperties.Controller;

import com.raghav.acidproperties.Service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Long from,
            @RequestParam Long to,
            @RequestParam BigDecimal amount
    ) {
        bankService.transfer(from, to, amount);
        return ResponseEntity.ok("Transfer successful");
    }
}

