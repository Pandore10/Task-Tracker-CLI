import java.time.LocalDateTime;

public class Task {
    private static int lastId = 0;

    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
        
    public Task(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = "todo";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override 
    public String toString() {
        return String.format("Task ID: %d\n" + 
                             "Task Description: %s\n" +
                             "Task Status: %s\n" +
                             "Last updated: %s\n" +
                             "Created: %s\n", this.id, this.description, this.status, this.updatedAt, this.createdAt);
    }

    private void updateTask() {
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String status() {
        return this.status;
    }

    public LocalDateTime getLastUpdateTime() {
        return this.updatedAt;
    }

    public LocalDateTime getTaskCreatedTime() {
        return this.createdAt;
    }

    public void updateDescription(String description) {
        this.description = description;
        updateTask();
    }

    public void setStatusTodo() {
        if (this.status.equals("todo")) return;
        this.status = "todo";
        updateTask();
    }

    public void setStatusInProgress() {
        if (this.status.equals("in-progress")) return;
        this.status = "in-progress";
        updateTask();
    }

    public void setStatusDone() {
        if (this.status.equals("done")) return;
        this.status = "done";
        updateTask();
    }
}
