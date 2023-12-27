package comp.company.controller;

import comp.company.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/*
 * 🦋 How to Retrieve Object Properties?
 * · When the form is loaded, Spring MVC will call getter methods.
 * · When the form is submitted, Spring MVC will call setter methods.
 *
 * 🦋 @ModelAttribute
 * · @ModelAttribute binds a method parameter or method return value to a named model attribute, which is exposed to web views.
 * · It can be used either as a method parameter or at method level.
 *
 * ❗️When the "mentor/register" endpoint loads, we see the empty form in the view. MVC will call all the getter methods. Ex: mentor.getFirstName, etc.
 * ❗️When the "mentor/confirm" endpoint loads, it sets the inputted values. MVC will call all the setter methods. Ex: mentor.setFirstName="Mike," etc.
 *
 */

@Controller
@RequestMapping("/mentor")
public class MentorController {


    @GetMapping("/register") // When this end point is executed, we will see an empty registration form in the view.
    public String showForm(Model model){
        /*
         * ❗️model.addAttribute("mentor", new Mentor());
         * · A new empty Mentor object to capture inputted data from the mentor-register.html view (browser).
         * · This object is used to model a form’s fields and provide getter and setter methods that will be used by the
         *   framework for establishing and obtaining the values input by the user at the browser side.
         */
        model.addAttribute("mentor", new Mentor());

        List<String> batchList = Arrays.asList("B1", "B2", "B3", "B4", "B5", "B6", "B7"); // DropDown List
        model.addAttribute("batchList", batchList);

        return "mentor/mentor-register";
    }

    /*
     * · @ModelAttribute("mentor") is exposed to web view, so we use it to display data on the view "mentor/mentor-confirmation".
     * · return "redirect:/mentor/register"; Data will be posted, but it will come back in an empty form. We redirect from one endpoint to another.
     */
    @PostMapping("/confirm")
    public String showForm2( @ModelAttribute("mentor") Mentor mentor){

//        System.out.println(mentor.toString());
        return "mentor/mentor-confirmation";

//        return "redirect:/mentor/register"; // After submitting the form, it will redirect the user to the same view (the empty form).


    }




}
