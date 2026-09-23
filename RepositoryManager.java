import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RepositoryManager {

    private static void writeJSON(List<Task> tasks, String filename) {
        
        String content = "";

        for (int i = 0; i < tasks.size(); i++) {
            
            content = content.concat(tasks.get(i).toString());

            if (i != tasks.size() - 1) {
                content = content.concat(",");
            }
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(String.format("[%s]", content));
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private static List<Task> readJSON(String filename) {

        File file = new File(filename);

        if (!file.exists()) return null;

        try (Scanner scanner = new Scanner(file)) {

            List<Task> tasks = new ArrayList<>();
            String description = "";
            String statusString = "";
            String updatedAtString = "";
            String createdAtString = "";
            
            while (scanner.hasNext()) {
                String line = scanner.next();

                if (line.contains("\"description\":")) {
                    description = line.substring(line.indexOf(":") + 2, line.indexOf(",") - 1);
                }

                if (line.contains("\"status\":")) {
                    statusString = line.substring(line.indexOf(":") + 2, line.indexOf(",") - 1);
                }

                if (line.contains("\"updatedAt\":")) {
                    updatedAtString = line.substring(line.indexOf(":") + 2, line.indexOf(",") - 1);
                }

                if (line.contains("\"createdAt\":")) {
                    createdAtString = line.substring(line.indexOf(":") + 2, line.indexOf("}") - 1);
                }

                if (!description.isEmpty() && !statusString.isEmpty() && !updatedAtString.isEmpty() && !createdAtString.isEmpty()) {
                    
                    Status taskStatus = Status.TODO;
                    LocalDateTime updatedAt = LocalDateTime.parse(updatedAtString);
                    LocalDateTime createdAt = LocalDateTime.parse(createdAtString);

                    switch (statusString) {
                        case "TODO":
                            taskStatus = Status.TODO;
                            break;
                    
                        case "IN_PROGRESS":
                            taskStatus = Status.IN_PROGRESS;
                            break;

                        case "DONE":
                            taskStatus = Status.DONE;
                            break;
                    }

                    Task task = new Task(description);
                    task.setStatus(taskStatus);
                    task.setDates(createdAt, updatedAt);

                    tasks.add(task);

                    description = "";
                    statusString = "";
                    updatedAtString = "";
                    createdAtString = "";
                }   
            }

            return tasks;

        } catch (NoSuchElementException e) {
            System.err.println("NoSuchElementException: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }

        return null;
    }

    public static void writeJSON(List<Task> tasks) {
        writeJSON(tasks, "tasks.json");
    }

    public static List<Task> readJSON() {
        return readJSON("tasks.json");
    }
}