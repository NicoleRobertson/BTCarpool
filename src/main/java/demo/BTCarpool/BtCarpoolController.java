package demo.BTCarpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BtCarpoolController {

    @Autowired
    CarRideRepository repository;

    @GetMapping ("/")
    public String startpage(Model model) {
        List<StartpageCarRides> list = repository.publishedCarRides();
        model.addAttribute("publishedCarRides", list);

        return "startpage";
    }

   /* @GetMapping("/{id}")
    public String recipe(Model model, @PathVariable int id) {
        Recipe recipe = repository.getRecipe(id);
        model.addAttribute("recipe", recipe);

        return "Recipe";
    } */

}
