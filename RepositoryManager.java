import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RepositoryManager {

    public void writeJSON(List<Task> tasks, String filename) {
        
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
}