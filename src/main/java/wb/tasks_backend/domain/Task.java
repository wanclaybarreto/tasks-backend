package wb.tasks_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;
    private LocalDate whenToDo;
    private Boolean done = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
