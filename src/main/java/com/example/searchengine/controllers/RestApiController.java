package com.example.searchengine.controllers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;


@CrossOrigin
@RestController
@RequestMapping(value = "/search",headers = "Accept=application/json")
public class RestApiController {
    @Value("${API_KEY}")
    private String apiKey;
    @GetMapping("/searchForm")
    public String search(@RequestParam(name = "search")String search, Model model) throws IOException, InterruptedException {

        String encodedSearch = URLEncoder.encode(search, StandardCharsets.UTF_8);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google.serper.dev/search?q=" + encodedSearch))
                .header("X-API-KEY", apiKey )
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        model.addAttribute("response", response.body());

          return response.body();
    }



}



