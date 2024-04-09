package mg.inclusiv.cdan8.repository;

import mg.inclusiv.cdan8.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    public List<Todo> findAllByUserId(Long userId);
}
