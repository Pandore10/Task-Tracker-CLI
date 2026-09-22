import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getList() {
        return tasks;
    }

    public void setList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void createTask(String description) {
        Task task = new Task(description.replace(" ", "-"));
        tasks.add(task);
    }

    public void deleteTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) tasks.remove(task);
        }
    }

    public void updateTask(int id, String description) {
        for (Task task : tasks) {
            if (task.getId() == id) task.setDescription(description);
        }
    }

    public void updateTask(int id, Status status) {
        for (Task task : tasks) {
            if (task.getId() == id) task.setStatus(status);
        }
    }

    public void listTasks() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY - HH:mm:ss");

        for (Task task : tasks) {
            System.out.printf("Task '%s' (ID %d)\nStatus: %s\nLast update: %s\n\n", 
                                task.getDescription().replace("-", " "), task.getId(), task.getStatus().getStatus(), task.getUpdateTime().format(formatter));
        }
    }

    public void listTasks(Status status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY - HH:mm:ss");

        for (Task task : tasks) {
            if (task.getStatus().equals(status)) {
                System.out.printf("Task '%s' (ID %d)\nStatus: %s\nLast update: %s\n\n", 
                                    task.getDescription().replace("-", " "), task.getId(), task.getStatus().getStatus(), task.getUpdateTime().format(formatter));
            }
        }
    }
}