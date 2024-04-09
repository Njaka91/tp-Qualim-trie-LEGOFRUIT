package mg.inclusiv.cdan8.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import mg.inclusiv.cdan8.controller.TodoController;
import mg.inclusiv.cdan8.entity.Todo;
import mg.inclusiv.cdan8.entity.Users;
import mg.inclusiv.cdan8.services.TodoService;
import mg.inclusiv.cdan8.services.UsersService;

public class TodoControllerUnitTest {

    @Mock
    TodoService todoService;

    @Mock
    UsersService usersService;

    @Mock
    HttpSession session;

    @Mock
    Model model;

    @InjectMocks
    TodoController todoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTodo() {
        Long userId = 1L;
        Users user = new Users();
        user.setId(userId);
        when(session.getAttribute("userid")).thenReturn(userId);
        when(usersService.getInfo(userId)).thenReturn(user);
        List<Todo> todoList = new ArrayList<>();
        when(todoService.getAll(userId)).thenReturn(todoList);

        String result = todoController.todo(session, model);

        assertEquals("todo", result);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("listTodo", todoList);
    }



    @Test
    public void testCreateTodo() {
        Todo todo = new Todo();
        Long userId = 1L;
        when(session.getAttribute("userid")).thenReturn(userId);
        when(usersService.getInfo(userId)).thenReturn(new Users());

        String result = todoController.addTask(todo, session);

        assertEquals("redirect:/todo", result);
        assertNotNull(todo.getCreatedAt());
        assertFalse(todo.isCompleted());
        assertEquals(userId, todo.getUserId());
        verify(todoService).create(todo);
    }

}


