package com.example.EitAssignment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/qa")
public class QAController {

    private final QAService qaService;


    @GetMapping("/greetings")
    public String greetings() {
        return qaService.greetings();
    }

    @GetMapping("/world/{subject}")
    public Object basicWorldAffairs(@PathVariable("subject") String subject) {
        return qaService.basicWorldAffairs(subject);
    }

    @GetMapping("/weather/{city}")
    public Object getWeather(@PathVariable("city") String city) {
        return qaService.getWeather(city);
    }

}
