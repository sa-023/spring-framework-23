package com.company.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * 🦋 Getting Data on the HTTP Request
 * · HTTP request parameter: Represents a simple way to send values from client to server in a key-value pair format. They are also called query parameters.
 * · Path variable: Sending data through the request path itself.
 * · HTTP request header: Similarly to the request parameters, the request headers are sent through the HTTP header.
 * · The HTTP request body:
 *
 * 🔺 @RequestParam
 * · @RequestParam annotation tells Spring it needs to get the value from the HTTP request parameter with the same name as the method’s parameter name.
 * · A request parameter is mandatory by default. If the client does not provide a value for the request parameter, the server sends
 *   back a response with the status HTTP 400 Bad Request.
 * · If you wish the value to be optional, you need to explicitly specify this on the annotation using the required attribute: @RequestParam(required = false)
 * · We use request parameters when: The quantity of data you send is not large, You need to send optional data, It is mostly
 *   used defining some search and filtering criteria.
 *
 * 🔺 @PathVariable
 * · @PathVariable annotation is used to mark the controller’s action parameter to get the path variable’s value.
 * · The path variable cannot be optional because we pass it in the @RequestMapping() following the endpoint.
 * · Instead of using HTTP request parameters, we can directly set variable values in the path.
 *   Ex: @RequestMapping("/info/{make}/{year}") -> make and year are path variables
 *
 * 🖍️...
 * · A controller’s action gets the details sent by the client in parameters annotated with @RequestParam or @PathVariable.
 * · To be able to cache the value that comes from the client (browser), we pass @RequestParam or @PathVariable with the instance reference into the method parameter.
 * · Ex: public String methodName(@RequestParam String make){}
 *   Ex: public String methodName(@PathVariable String make){}
 * · The request parameter (Query Parameters) makes the match based on the key, and the path parameter makes the match based on the position.
 *
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @RequestMapping("/info") // http://localhost:8080/car/info?make=Honda&year=2015
    public String carInfo(@RequestParam String make, @RequestParam Integer year ,Model model){

        // We use Model to capture the values of "make" and "year" which come from the browser, and we pass them into the view.
        model.addAttribute("make", make);
        model.addAttribute("year", year);
        System.out.println("Test 1: " + make + " " +year);
        return "/car/car-info";
    }


    /*
     * ❗️Query parameters can be optional:
     * · Ex1: car/info2 -> @RequestParam(value = "make",required = false): If no value is provided, it will return nothing.
     * · Ex2: car/info2 ->  @RequestParam(value = "make",required = false, defaultValue = "Tesla"): If no value is provided, it will return the default value ("Tesla").
     * · Ex3: car/info2?make=Nissan -> @RequestParam(value = "make",required = false,defaultValue = "Tesla"): If a value is provided, it will return the provided value (Nissan).
     */
    @RequestMapping("/info2") // http://localhost:8080/car/info2
    public String carInfo2(@RequestParam (value = "make", required = false, defaultValue = "Tesla") String make, Model model){

        model.addAttribute("make", make);
        System.out.println(make);
        return "/car/car-info";
    }


    /*
     * ❗Path variable cannot be optional because we pass it in the @RequestMapping() following the endpoint.
     * · Ex1: car/info/Toyota -> @RequestMapping("/info/{make}")
     * · Ex2: car/info/Toyota/2015 -> @@RequestMapping("/info/{make}/{year}")
     */
    @RequestMapping("/info/{make}/{year}") // http://localhost:8080/car/info/honda/2012
    public String getCarInfo(@PathVariable String make, @PathVariable Integer year,  Model model){

        model.addAttribute("make", make);
        System.out.println(make + " " + year);
        return "/car/car-info";
    }





}
