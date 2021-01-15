package com.example.EitAssignment;

import com.hp.hpl.jena.query.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QAService {

    private final String API_KEY = "18e544032fa91c237aea167299f2afb9";

    private String getDetailsFromUrl(String url) {
        String content = "";
        try {
            content = Jsoup.connect(url).get().text();
            int index = content.indexOf("Property Value");

            if (index != -1)
                content = content.substring(0, index);
            index = content.lastIndexOf("dbpedia.org");

            if (index != -1)
                content = content.substring(index + 12);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

    public Object greetings(String question) {
        Map<String, String> map = new HashMap<>();
        StringBuilder answer = new StringBuilder("Hello Corona!");

        String[] questionWords = question.toUpperCase().split("[ !,?.]+");
        List<String> wordList = Arrays.asList(questionWords);
        if (wordList.contains("GOOD")) {
            answer.append(" Good ");
            answer.append(wordList.get(wordList.indexOf("GOOD") + 1).toLowerCase() + "!");
        }
        if (wordList.contains("HOW")) {
            answer.append(" I am fine. And you?");
        }
        if (wordList.contains("NAME")) {
            answer.append(" My name is Giash Uddin.");
        }

        map.put("answer", answer.toString());
        return map;
    }

    public Object basicWorldAffairs(String question) {
        String[] questionWords = question.split("[ !,?.]+");
        String subject = questionWords[questionWords.length - 1];

        Map<String, Object> answer = new HashMap<>();
        try {
            String queryString = "PREFIX pr:<http://xmlns.com/foaf/0.1/>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "SELECT DISTINCT ?s ?label WHERE {" + "?s rdfs:label ?label . " +
                    "?s a pr:Person . " +
                    "FILTER (lang(?label) = 'en') . " +
                    "?label <bif:contains> '" + subject + "' ." +
                    "}";
            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
            List<Map> mapList = new ArrayList<>();

            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                Object name = soln.get("label");
                Object url = soln.getResource("s");
                Map<String, Object> map = new HashMap<>();
                map.put("name", String.valueOf(name));
                map.put("url", String.valueOf(url));
                map.put("content", getDetailsFromUrl(String.valueOf(url)));

                mapList.add(map);
            }

            answer.put("answer", mapList);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
        }

        answer.put("answer", "Your majesty! Jon Snow knows nothing! So do I!");
        return answer;

    }

    public Object getWeather(String question) {
        String[] questionWords = question.toUpperCase().split("[ !,?.]");
        String city = questionWords[questionWords.length - 1];
        List<String> words = Arrays.asList(questionWords);

        Map<String, Object> answer = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> response = restTemplate
                    .getForEntity("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "", Object.class);

            JSONObject object = new JSONObject(response);

            Map<String, Object> map = new HashMap<>();

            JSONObject mainObj = object.getJSONObject("body").getJSONObject("main");
            JSONObject cloudObj = object.getJSONObject("body").getJSONObject("clouds");
            if (words.contains("TEMPERATURE")) {
                map.put("temperature", mainObj.get("temp"));
            }
            if (words.contains("HUMIDITY")) {
                map.put("humidity", mainObj.get("humidity"));
            }
            if (words.contains("CLOUD") || words.contains("CLOUDY")) {
                map.put("cloud", cloudObj.get("all"));
            }

            answer.put("answer", map);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
        }

        answer.put("answer", "Your majesty! Jon Snow knows nothing! So do I!");
        return answer;
    }
}
