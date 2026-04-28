package wb.tasks_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import wb.tasks_backend.domain.Task;
import wb.tasks_backend.domain.User;

@SpringBootApplication
public class TasksBackendApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TasksBackendApplication.class, args);
	}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Task.class);
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
    }
}
