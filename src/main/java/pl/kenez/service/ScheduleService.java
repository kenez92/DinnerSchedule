package pl.kenez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kenez.communication.schedule.ScheduleDto;
import pl.kenez.model.MailModel;
import pl.kenez.service.dao.RecipeService;

@Service
public class ScheduleService {
    private static final String MAIL_SUBJECT = "Dinner";
    private final MailService mailService;
    private final MessageService messageService;
    private final RecipeService recipeService;

    @Autowired
    public ScheduleService(final MailService mailService, final MessageService messageService,
                           final RecipeService recipeService) {
        this.mailService = mailService;
        this.messageService = messageService;
        this.recipeService = recipeService;
    }

    public void scheduleDinner(final ScheduleDto scheduleDto) {
        mailService.send(new MailModel(
                scheduleDto.getMailTo(),
                MAIL_SUBJECT,
                messageService.prepareMessage(recipeService.findRandomRecipes(scheduleDto.getDinnerQuantity()))));
    }
}
