package demo.BTCarpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import java.util.List;


@Controller
@EnableAutoConfiguration

public class BtCarpoolController {

    @Autowired
    CarRideRepository repository;


    @GetMapping ("/")
    public String startpage(Model model) {
        List<StartpageCarRides> list = repository.publishedCarRides();
        model.addAttribute("publishedCarRides", list);
        model.addAttribute("image", "'/panoramanature.jpg'");

        return "startpage";
    }

   @GetMapping("/carrides/{id}")
    public String details(Model model, @PathVariable int id) {
        Vehicle details = repository.getVehicle(id);
        /*Employee employee = repository.getEmployee(id);*/
        model.addAttribute("details", details);
        /*model.addAttribute("employee", employee);*/
        return "carridedetails";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }
}
