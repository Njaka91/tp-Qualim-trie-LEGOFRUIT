package mg.inclusiv.cdan8.controller;

import jakarta.servlet.http.HttpSession;
import mg.inclusiv.cdan8.entity.Todo;
import mg.inclusiv.cdan8.entity.Users;
import mg.inclusiv.cdan8.services.TodoService;
import mg.inclusiv.cdan8.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService todoService;
    @Autowired
    UsersService usersService;

    @GetMapping
    private String todo(HttpSession session, Model model){
        Long userid = (Long) session.getAttribute("userid");
        if (userid != null){
            Users user = usersService.getInfo(userid);
            if (user != null){
                model.addAttribute("user", user);

                List<Todo> listTodo = todoService.getAll(userid);
                model.addAttribute("listTodo", listTodo);
                return "todo";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/list")
    public List<Todo> getAll(HttpSession session)
    {
        Long userid = (Long) session.getAttribute("userid");
        if (userid != null){
            return todoService.getAll(userid);
        }

        return null;
    }

    @PostMapping("/add")
    public String addTask (@ModelAttribute Todo todo, HttpSession session)
    {
        Long userid = (Long) session.getAttribute("userid");
        if (userid != null){
            Users user = usersService.getInfo(userid);
            if (user != null){
                todo.setCompleted(false);
                todo.setUserId(userid);
                todo.setCreatedAt(LocalDateTime.now());
                todoService.create(todo);

                return "redirect:/todo";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/togglestate/{todoId}")
    public String toggleState (@PathVariable Long todoId)
    {
        todoService.toggleState(todoId);
        return "redirect:/todo";
    }

    @GetMapping("/delete/{todoId}")
    public String deleteTask (@PathVariable Long todoId, HttpSession session)
    {
        Long userid = (Long) session.getAttribute("userid");
        if (userid != null){
            Users user = usersService.getInfo(userid);
            if (user != null){
                todoService.delete(todoId);
                return "redirect:/todo";
            }
        }
        return "redirect:/";
    }

}
