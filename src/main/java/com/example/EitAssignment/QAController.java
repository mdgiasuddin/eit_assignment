package com.example.EitAssignment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/qa")
public class QAController {

    private final QAService qaService;


    @GetMapping("/greetings")
    public Object greetings(@RequestParam("q") String question) {
        return qaService.greetings(question);
    }

    @GetMapping("/world/affairs")
    public Object basicWorldAffairs(@RequestParam("q") String question) {
        return qaService.basicWorldAffairs(question);
    }

    @GetMapping("/world/affairs")
    public Object basicWoAffairs(@RequestParam("q") String question) {
        return qaService.basicWorldAffairs(question);
    }

    @GetMapping("/weather")
    public Object getWeather(@RequestParam("q") String question) {
        return qaService.getWeather(question);
    }

}
