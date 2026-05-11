package wb.tasks_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import wb.tasks_backend.domain.Task;
import wb.tasks_backend.exception.DuplicatedTaskException;

@Component
@RepositoryEventHandler(Task.class)
public class TaskRepositoryEventHandler {

    private TaskRepository taskRepository;

    @Autowired
    public TaskRepositoryEventHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handle(Task task) throws DuplicatedTaskException {
        Task taskDB = taskRepository.findByDescription(task.getDescription());

        boolean duplicatedTask = false;

        if (taskDB != null) {
            if ((task.getId() == null || task.getId() == 0) && task.getDescription().equals(taskDB.getDescription())) {
                duplicatedTask = true;
            }

            if (task.getId() != null && task.getId() > 0 && !task.getId().equals(taskDB.getId())) {
                duplicatedTask = true;
            }
        }

        if (duplicatedTask) throw new DuplicatedTaskException("Já existe uma tarefa com a mesma descrição");
    }

}
