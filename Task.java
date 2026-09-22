import java.time.LocalDateTime;

public class Task {
    public static int lastId = 0;

    private int id;
    private String description;
    private Status status;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Task(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    private void updateTask() {
        this.updatedAt = LocalDateTime.now();
    }

    public String toString() {
        return String.format("{\"id\":%d, \"description\":\"%s\", \"status\":\"%s\", \"updatedAt\":\"%s\", \"createdAt\":\"%s\"}",
                                this.id, this.description, this.status, this.updatedAt, this.createdAt);
    }

    //getters
    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.status;
    }

    public LocalDateTime getUpdateTime() {
        return this.updatedAt;
    }

    //setters
    public void setDescription(String description) {
        this.description = description;
        updateTask();
    }

    public void setStatus(Status status) {
        this.status = status;
        updateTask();
    }

    public void setDates(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}