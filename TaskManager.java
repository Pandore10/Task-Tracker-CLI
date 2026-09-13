import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    List<Task> tasks = new ArrayList<>();

    public void createTask(String description) {
        Task task = new Task(description);
        tasks.add(task);

        System.out.println("Task '" + description + "' adicionada a lista de tasks.");
    }

    public void deleteTask(int id) {

    }
}
