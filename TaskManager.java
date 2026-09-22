import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void createTask(String description) {
        Task task = new Task(description);
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
}