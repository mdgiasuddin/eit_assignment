package com.example.EitAssignment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QAService {

    private final String API_KEY = "18e544032fa91c237aea167299f2afb9";

    public String greetings() {
        return "Hello";
    }

    ResponseEntity<?> getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> response = restTemplate
//                .getForEntity("https://api.openweathermap.org/data/2.5/weather?lat=33.441792&lon=-94.037689&APPID=" + API_KEY + "&units=metric", Object.class);
//
        ResponseEntity<Object> response = restTemplate
                .getForEntity("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "", Object.class);
        return response;
    }
}
