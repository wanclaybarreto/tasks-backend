package wb.tasks_backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "A descrição da tarefa é obrigatória")
    @Length(min = 3, max = 50, message = "A descrição da tarefa precisa ter de 3 a 50 caracteres")
    private String description;

    @NotNull(message = "A data da tarefa é obrigatória")
    @FutureOrPresent(message = "Data inválida")
    private LocalDate whenToDo;

    private Boolean done = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "O usuário da tarefa é obrigatório")
    private User user;

    public Task() {}

    public Task(String description, LocalDate whenToDo, Boolean done) {
        this.description = description;
        this.whenToDo = whenToDo;
        this.done = done;
    }

    public Task(String description, LocalDate whenToDo, Boolean done, User user) {
        this.description = description;
        this.whenToDo = whenToDo;
        this.done = done;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getWhenToDo() {
        return whenToDo;
    }

    public void setWhenToDo(LocalDate whenToDo) {
        this.whenToDo = whenToDo;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
