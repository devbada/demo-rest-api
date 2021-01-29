package me.minam.demorestapi.events;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class EventController {

    @PostMapping("events")
    public String createEvent(@RequestParam String id) {
        return "SUCCESS";
    }
}
