import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

public class TaskManager {
    private List<Task> tasks = RepositoryManager.readJSON();

    public List<Task> getList() {
        return tasks;
    }

    public void setList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void createTask(String description) {
        Task task = new Task(description.replace(" ", "-"));
        tasks.add(task);
        
        reloadList();
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        
        reloadList();
    }

    public void updateTask(int id, String description) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                reloadList();
                return;
            }
        }
        
        throw new NoSuchElementException();
    }

    public void updateTask(int id, Status status) {
        if (status == null) {
            throw new IllegalArgumentException();
        }
        
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(status);
                reloadList();
                return;
            }
        }

        throw new NoSuchElementException();
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

    private void reloadList() {
        RepositoryManager.writeJSON(tasks);
        tasks = RepositoryManager.readJSON();
    }
}