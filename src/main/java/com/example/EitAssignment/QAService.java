package com.example.EitAssignment;

import com.hp.hpl.jena.query.*;
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

    public void basicWorldAffairs(String subject) {
        String str = "Obama";
        String queryString = "PREFIX pr:<http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT DISTINCT ?s ?label WHERE {" + "?s rdfs:label ?label . " +
                "?s a pr:Person . " +
                "FILTER (lang(?label) = 'en') . " +
                "?label bif:contains \"" + str + "\" ." +
                "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
        try {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                //Literal name = soln.getLiteral("x");
                System.out.println(soln);
            }
        } finally {
            qexec.close();
        }

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
