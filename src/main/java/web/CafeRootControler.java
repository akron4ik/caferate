package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repository.datajpa.DataJpaCafeRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CafeRootControler {
    @Autowired
    DataJpaCafeRepository repository;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/cafes")
    public String users(Model model) {
        model.addAttribute("cafes", repository.getAll());
        return "cafes";
    }

   /* @PostMapping("/cafes")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:cafes";
    }*/
}
