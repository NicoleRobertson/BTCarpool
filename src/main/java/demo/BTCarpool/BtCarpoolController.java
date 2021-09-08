package demo.BTCarpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@EnableAutoConfiguration

public class BtCarpoolController {

    @Autowired
    CarRideRepository repository;


    @GetMapping ("/")
    public String startpage(Model model) {
        List<StartpageCarRides> list = repository.publishedCarRides();
        List<CarRide> carRides = repository.getCarRides();
        List<Booking> bookings = repository.getBookings();
        model.addAttribute("publishedCarRides", list);
        model.addAttribute("image", "'/panoramanature.jpg'");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.addAttribute("userName", currentUserName);
            Employee employee = repository.getEmployee(currentUserName);
            if (employee != null) {
                model.addAttribute("employeeId", employee.getId());
            }
            // return currentUserName;
            for (StartpageCarRides c : list) {
                for (Booking b : bookings) {
                    if (c.getId() == b.getCarrideId() && employee.getId() == b.getEmployeeId()) {
                        c.setAlreadyBooked(true);
                    }
                }
            }

            model.addAttribute("bookings", repository.getBookings());

        }

        return "startpage";

    }

    @GetMapping ("/join/{id}")
    public String join(Model model, @PathVariable int id) {
        //Find username based on authetication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        System.out.println(currentUserName);

        //Find employeeId based on UserName
        Employee employee = repository.getEmployee(currentUserName);
        //Create a row in table named Booking
        int bookingId = repository.saveBooking(id, employee.getId());
        //Find carride Id in repository
        repository.getCarRide(id);

        //Update list of published cars (availableseats-1)
        List<StartpageCarRides> list = repository.publishedCarRides();
        model.addAttribute("publishedCarRides", list);

        return "redirect:/";

    }

   @GetMapping("/carrides/{id}")
    public String details(Model model, @PathVariable int id) {
        Vehicle details = repository.getVehicle(id);
        /*Employee employee = repository.getEmployee(id);*/
        model.addAttribute("details", details);
        model.addAttribute("image", "'/forest2.jpg'");
        /*model.addAttribute("employee", employee);*/
        return "carridedetails";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("image", "'/ocean.jpg'");
        return "login";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("address", new Address());
        model.addAttribute("employee", new Employee());
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("ride", new CarRide());
        model.addAttribute("image", "'/flowers.jpg'");
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

    @GetMapping ("/myrides/{id}")
    public String myrides(Model model, @PathVariable int id) {
        List<StartpageCarRides> list = repository.publishedCarRides();
        List<CarRide> carRides = repository.getCarRides();
        List<Booking> bookings = repository.getBookings();
        List<StartpageCarRides> myrides = new ArrayList<>();
        List<Booking> mybookings = new ArrayList<>();
        List<Booking> cancelledbookings = new ArrayList<>();
        List<StartpageCarRides> cancelledrides = new ArrayList<>();

        model.addAttribute("publishedCarRides", list);
        model.addAttribute("image", "'/panoramanature.jpg'");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.addAttribute("userName", currentUserName);
            Employee employee = repository.getEmployee(currentUserName);
            model.addAttribute("employee", employee);
            if (employee != null) {
                model.addAttribute("employeeId", employee.getId());
            }
            // return currentUserName;
            // Create list to see my active bookings
            for (Booking b : bookings) {
                if (b.getEmployeeId() == employee.getId() && b.getActive()){
                    mybookings.add(b);
                }
            }

            for (StartpageCarRides c : list) {
                for (Booking b : mybookings) {
                    if (c.getId() == b.getCarrideId()) {
                       myrides.add(c);
                    }
                }
            }
            // Create list to see my cancelled bookings

            for (Booking b : bookings) {
                if (b.getEmployeeId() == employee.getId() && !b.getActive()){
                    cancelledbookings.add(b);
                }
            }

            for (StartpageCarRides c : list) {
                for (Booking b : cancelledbookings) {
                    if (c.getId() == b.getCarrideId()) {
                        cancelledrides.add(c);
                    }
                }
            }

            /*CarRideId.Booking == CarrideId.Carride (alla bokningar för specifik carride - kan vara fler)
             * (EmployeeId.Booking == EmployeeId.Carride) && (Username.Login == Username.Employee) */

            model.addAttribute("myrides", myrides);
            model.addAttribute("cancelledrides", cancelledrides);

        }

        return "myRides";

    }
    @GetMapping ("/cancel/{id}")
    public String cancel(Model model, @PathVariable int id) {
        //Find username based on authetication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        System.out.println(currentUserName);

        //Find employeeId based on UserName
        Employee employee = repository.getEmployee(currentUserName);

        repository.cancelBooking(id, employee.getId());


        return "redirect:/myrides/{id}";

    }

}
