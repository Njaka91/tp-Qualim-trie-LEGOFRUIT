package mg.inclusiv.cdan8.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(HttpSession session) {
       Long userid = (Long) session.getAttribute("userid");
        if (userid != null){
            return "redirect:/todo";
        }

        return "index";
    }
}