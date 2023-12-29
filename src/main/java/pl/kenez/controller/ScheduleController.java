package pl.kenez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kenez.communication.schedule.ScheduleDto;
import pl.kenez.service.ScheduleService;

@Controller("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    void scheduleDinner(@ModelAttribute("scheduleDto") final ScheduleDto scheduleDto) {
        scheduleService.scheduleDinner(scheduleDto);
    }
}
