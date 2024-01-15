package com.company.client;
import com.company.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
/*
 * 🖍️...
 * · @FeignClient: We use this annotation to provide the API that we will consume.
 * · name = "USER-CLIENT" is the identifier for the url.
 */
@FeignClient(url  = "https://jsonplaceholder.typicode.com/",name = "USER-CLIENT") // 3rd party API we will consume.
public interface UserClient {

     @GetMapping("/users") // 3rd party API endpoint.
     List<User> getUsers();
    /*
     * 🖍️...
     * · @GetMapping("/users") is a 3rd party endpoint which will be executed when we call getUsers() method and data will be assigned to the List<User>.
     * · When we call the getUsers() method, the @FeignClient URL will be triggered and @GetMapping("/users") will be executed.
     */




}
