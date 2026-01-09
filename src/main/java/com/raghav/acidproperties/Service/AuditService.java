package com.raghav.acidproperties.Service;

import com.raghav.acidproperties.Entity.AuditLog;
import com.raghav.acidproperties.Repo.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AuditService {

    private final AuditLogRepository auditRepo;



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransfer(Long from, Long to, BigDecimal amount) {
        auditRepo.save(
                new AuditLog("Transfer " + amount + " from " + from + " to " + to)
        );
    }
}
