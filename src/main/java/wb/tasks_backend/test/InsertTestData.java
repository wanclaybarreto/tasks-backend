package wb.tasks_backend.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import wb.tasks_backend.domain.Task;
import wb.tasks_backend.repository.TaskRepository;
import wb.tasks_backend.domain.User;
import wb.tasks_backend.repository.UserRepository;

import java.time.LocalDate;

@Component //Para que o Spring possa gerenciá-la e, desse modo, enxergar a instância dessa classe e chamar no momento certo
public class InsertTestData {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public InsertTestData(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @EventListener
    public void loadDatabase(ContextRefreshedEvent event) {

        //TODO: Criptografar senha.
        User user = new User("Fulano de Tal", "fulano", "abc123");
        userRepository.save(user);

        LocalDate baseDate = LocalDate.parse("2026-11-01");

        for (int i = 1; i <= 10; i++) {
            taskRepository.save(
                    new Task(
                            "Tarefa #" + i,
                            baseDate.plusDays(i),
                            false,
                            user
                    )
            );
        }

    }

}
