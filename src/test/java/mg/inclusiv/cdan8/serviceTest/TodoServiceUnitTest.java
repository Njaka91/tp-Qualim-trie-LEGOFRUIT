package mg.inclusiv.cdan8.serviceTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mg.inclusiv.cdan8.entity.Todo;
import mg.inclusiv.cdan8.repository.TodoRepository;
import mg.inclusiv.cdan8.services.TodoService;

public class TodoServiceUnitTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        Long userId = 1L;
        List<Todo> todoList = new ArrayList<>();
        when(todoRepository.findAllByUserId(userId)).thenReturn(todoList);

        List<Todo> result = todoService.getAll(userId);

        assertEquals(todoList, result);
    }


}

