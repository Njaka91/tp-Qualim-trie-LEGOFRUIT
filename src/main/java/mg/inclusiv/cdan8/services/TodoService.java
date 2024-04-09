package mg.inclusiv.cdan8.services;

import mg.inclusiv.cdan8.entity.Todo;
import mg.inclusiv.cdan8.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAll(Long userId)
    {
        return  todoRepository.findAllByUserId(userId);
    }
    public void create(Todo todo)
    {
        todoRepository.save(todo);
    }

    public void toggleState(Long todoId)
    {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
    }

    public void delete (Long todoId)
    {
        todoRepository.deleteById(todoId);
    }
}
