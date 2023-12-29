package pl.kenez.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kenez.communication.schedule.ScheduleDto;
import pl.kenez.service.ScheduleService;

@Controller
@RequestMapping("/api/schedule")
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

    @GetMapping
    public String index() {
        return "api/schedule";
    }
}
