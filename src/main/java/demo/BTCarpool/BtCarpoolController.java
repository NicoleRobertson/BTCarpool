package demo.BTCarpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;



import javax.validation.Valid;
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

        return "startpage";
    }

   @GetMapping("/{id}")
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

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("address", new Address());
        model.addAttribute("employee", new Employee());
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("ride", new CarRide());
        return "createRide";
    }

    @PostMapping("/save")
    public String set(@Valid Employee employee, @Valid Address address,@Valid Vehicle vehicle,@Valid CarRide carride, BindingResult result) {
        if (result.hasErrors()) {
            return "createRide";
        }
        int addressId = repository.saveAddress(address);
        int employeeId = repository.saveEmployeeCreateRide(employee, addressId);
        int vehicleId = repository.saveVehicle(vehicle, employeeId);
        repository.saveRide(carride, vehicleId, employeeId);
        return "redirect:/create";
    }
}
