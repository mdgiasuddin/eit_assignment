package com.example.EitAssignment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/qa")
public class QAController {

    private final QAService qaService;


    @GetMapping("/greetings")
    public Object greetings(@RequestParam("q") String question) {
        return qaService.greetings(question);
    }

    @GetMapping("/world/{subject}")
    public Object basicWorldAffairs(@PathVariable("subject") String subject) {
        return qaService.basicWorldAffairs(subject);
    }

    @GetMapping("/weather")
    public Object getWeather(@RequestParam("q") String question) {
        return qaService.getWeather(question);
    }

}
