package com.raghav.acidproperties.Service;

import com.raghav.acidproperties.Entity.Account;
import com.raghav.acidproperties.Repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BankService {
    private final AccountRepository accountRepository;
    private final AuditService auditService;

    //ATOMICITY ALL transactions or no transactions if not transaction then complete Rollback
    //When @Transactional is used Durability is confirmed
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)//Isolation
    public void transfer(Long fromId, Long toId, BigDecimal amount){
        Account from = accountRepository.findById(fromId)
                .orElseThrow(()->new RuntimeException("Sender Not found"));

        Account to = accountRepository.findById(toId)
                .orElseThrow(()->new RuntimeException("Receiver  Not found"));


        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(from);
        accountRepository.save(to);

        auditService.logTransfer(fromId, toId, amount);

        if (amount.compareTo(new BigDecimal("50000")) > 0) {//consistency
            throw new RuntimeException("Transfer limit exceeded");
        }

    }



}
