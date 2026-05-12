package wb.tasks_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import wb.tasks_backend.domain.Task;
import wb.tasks_backend.domain.User;

@SpringBootApplication
public class TasksBackendApplication implements RepositoryRestConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TasksBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TasksBackendApplication.class, args);
        LOGGER.info("Tasks in action.");
	}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Task.class, User.class);
        cors.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");

        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        LOGGER.info("The configuration of the CORS Repository and the Ids Exposure were completed successfully.");
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        Validator validator = validator();
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave", validator);

        RepositoryRestConfigurer.super.configureValidatingRepositoryEventListener(validatingListener);

        LOGGER.info("Validator successfully configured!");
    }
}
