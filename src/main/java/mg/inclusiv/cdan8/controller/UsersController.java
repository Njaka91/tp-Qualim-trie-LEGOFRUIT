package mg.inclusiv.cdan8.controller;

import jakarta.servlet.http.HttpSession;
import mg.inclusiv.cdan8.entity.Users;
import mg.inclusiv.cdan8.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping("login")
    public String login(@ModelAttribute Users user, HttpSession session) {
        Users userlogged = usersService.login(user);
        if ( userlogged!= null){
            session.setAttribute("userid",userlogged.getId());
            return "redirect:/todo";
        }
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userid");
        return "redirect:/";
    }

    @PostMapping("add")
    public String signIn(@ModelAttribute Users user){
        usersService.creat(user);
        return "redirect:/";
    }

}
