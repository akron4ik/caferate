package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.datajpa.DataJpaMealRepository;
import repository.datajpa.DataJpaRestaurantRepository;
import repository.datajpa.DataJpaUserRepository;
import repository.datajpa.DataJpaVoiceRepository;


@Controller
public class RestaurantRootControler {
    @Autowired
    DataJpaRestaurantRepository restaurantRepository;
    @Autowired
    DataJpaMealRepository mealRepository;
    @Autowired
    DataJpaUserRepository userRepository;
    @Autowired
    DataJpaVoiceRepository voiceRepository;


    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/restaurants")
    public String restaurants(Model model) {
        model.addAttribute("restaurants", restaurantRepository.getAllRestaurantsWithMeals());
        return "restaurants";
    }

    @GetMapping("/meals")
    public String meals(Model model){
        model.addAttribute("meals", mealRepository.getAll());
        return "meals";
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userRepository.getAll());
        return "/users";
    }

    @GetMapping("/voices")
    public String voices(Model model){
        model.addAttribute("voices", voiceRepository.getAll());
        return "/voices";
    }

    /*@PostMapping("/cafes")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        if (userId == 100000) {
            return "redirect:cafeForm";
        }
        else{
            return "redirect:cafeMenu";
        }
    }*/
}
