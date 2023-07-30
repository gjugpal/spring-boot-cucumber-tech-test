package controller;

import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class UniversityApiController {

    @GetMapping("/searchByName")
    public String searchByName(Model model, @RequestParam String name) throws UnirestException, UnsupportedEncodingException {

        String url = "http://universities.hipolabs.com/search?name=" + URLEncoder.encode(name);

        HttpResponse response = new HttpRequest(HttpMethod.GET, url).asJson();
        model.addAttribute("json", response.getBody().toString());
        return "responses";
    }

    @GetMapping("/searchByCountry")
    public String searchByCountry(Model model, @RequestParam String country) throws UnirestException {

        String url = "http://universities.hipolabs.com/search?country=" + URLEncoder.encode(country);

        HttpResponse response = new HttpRequest(HttpMethod.GET, url).asJson();
        model.addAttribute("json", response.getBody().toString());
        return "responses";
    }

    @GetMapping("/searchByNameAndCountry")
    public String searchByNameAndCountry(Model model, @RequestParam String country, @RequestParam String name) throws UnirestException {

        String url = String.format("http://universities.hipolabs.com/search?country=%s&name=%s", URLEncoder.encode(country), URLEncoder.encode(name));

        HttpResponse response = new HttpRequest(HttpMethod.GET, url).asJson();
        model.addAttribute("json", response.getBody().toString());
        return "responses";
    }
}
