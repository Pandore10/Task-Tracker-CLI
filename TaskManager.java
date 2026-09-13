import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    List<Task> tasks = new ArrayList<>();

    public void createTask(String description) {
        Task task = new Task(description);
        tasks.add(task);

        System.out.println("Task '" + description + "' (" + task.getId() + ") adicionada a lista de tasks.");
    }

    public void deleteTask(int id) {
        for(Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                System.out.println("Removida task '" + task.getDescription() + "' da lista de tasks.");
                break;
            }
        }
    }

    public void listTasks() {
        System.out.println("Listando tasks\n");
        
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    public void listTasks(String status) {
        System.out.println("Listando tasks com o status '" + status + "'.");

        for (Task task : tasks) {
            if (task.getStatus().equals(status)) System.out.println(task.toString());
        }
    }

    public void editStatus(int id, String status) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                switch (status) {
                    case "todo":
                        task.setStatusTodo();
                        System.out.println("Status da task '" + task.getDescription() + "' mudou para 'todo'.");
                        break;
                    
                    case "in-progress":
                        task.setStatusInProgress();
                        System.out.println("Status da task '" + task.getDescription() + "' mudou para 'in-progress'.");
                        break;

                    case "done":
                        task.setStatusDone();
                        System.out.println("Status da task '" + task.getDescription() + "' mudou para 'done'.");
                        break;
                }
            }
        }
    }
}
