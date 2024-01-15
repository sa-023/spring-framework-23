package com.company.controller;
import com.company.dto.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
/*
 * 🔺 Rest Template
 * · First, we direct Spring to create a bean from RestTemplate in the main (configuration) class.
 *   Ex: @Bean
 *	     public RestTemplate restTemplate(){ return new RestTemplate();}
 * · Second, we inject the RestTemplate dependency into our controller class to be able to use methods to consume the 3rd-party APIs.
 * 🌀 Methods:
 * · getForEntity(url, responseType): It retrieves an entity by using GET method for the given URL. It returns ResponseEntity.
 * · getForObject(url, classType): It retrieves an entity using GET method on the given URL. It is similar to getForEntity(), but returns the resource directly.
 * · exchange(requestEntity, responseType): Execute the specified RequestEntity and return the response as ResponseEntity.
 *   Executes a specified HTTP method, such as GET, POST, PUT, etc., and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
 * 🖍️...
 * · RestTemplate is currently in maintenance mode, which means it won’t be developed further. Sometime soon, it is going to be deprecated.
 */
@RestController
@RequestMapping("/users")
public class Consume_RestTemplate {
    private final RestTemplate restTemplate;
    public Consume_RestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private final String URI = "https://jsonplaceholder.typicode.com/users"; // 3rd party API.



    @GetMapping
    public User[] readAllUsers() {
        // getForEntity() method converts third-party APIs to DTOs. It will get the JSON output and map it to out dto.
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URI, User[].class);
        return responseEntity.getBody();
    }

    @GetMapping("{id}")
    public Object readUser(@PathVariable("id") Integer id){
        String URL = URI + "/{id}";
        return restTemplate.getForObject(URL, Object.class, id); // getForObject() method directly gets the response and passes it to our API.
    }

    @GetMapping("/test")
    public ResponseEntity<Object> consumeFromDummyApi(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));// Accepts = APPLICATION_JSON
        headers.set("app-id", "6298ebfecd0551211fce37a6");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10", HttpMethod.GET, entity, Object.class);
        return response;
        /*
         * 🖍️...Steps:
         * · Define the HTTP headers by creating and configuring an HttpHeaders instance.
         * · Create an HttpEntity instance that represents the request data(headers + body)
         * · Send the HTTP call using the exchange() method and get the HTTP response.
         */
    }






}