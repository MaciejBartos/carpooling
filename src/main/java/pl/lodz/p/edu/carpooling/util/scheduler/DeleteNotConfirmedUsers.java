package pl.lodz.p.edu.carpooling.util.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lodz.p.edu.carpooling.persistence.repository.AccountRepository;

import javax.transaction.Transactional;

@Component
@Log
@RequiredArgsConstructor
public class DeleteNotConfirmedUsers {

    private final AccountRepository accountRepository;

    @Scheduled(cron = "0 0 2 * * *")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteAllNotConfirmedAccounts() {
        log.info("deleteAllNotConfirmedAccounts scheduler start");
        accountRepository.deleteAllNotConfirmedAccounts();
        log.info("deleteAllNotConfirmedAccounts scheduler end");
    }
}
