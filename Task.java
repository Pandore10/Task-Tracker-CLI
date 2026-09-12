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
                                 "Created: %s", this.id, this.description, this.status, this.updatedAt, this.createdAt);
        }
}
