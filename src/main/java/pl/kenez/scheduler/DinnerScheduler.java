package pl.kenez.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.kenez.model.MailModel;
import pl.kenez.service.MailService;
import pl.kenez.service.MessageService;

@Component
public class DinnerScheduler {
    private final MessageService messageService;
    private final MailService mailService;

    @Autowired
    public DinnerScheduler(MessageService messageService, MailService mailService) {
        this.messageService = messageService;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 0 12 ? * 6/1 *")
//    @Scheduled(fixedDelay = 100)
    public void scheduleDinner() {
        mailService.send(new MailModel("kenez92@gmail.com", "Dinner", messageService.prepareMessage()));
    }
}

