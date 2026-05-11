package wb.tasks_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wb.tasks_backend.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByDescription(String description);

}
