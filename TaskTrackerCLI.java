import java.util.NoSuchElementException;

public class TaskTrackerCLI {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: TaskTrackerCLI -h | -a | -r | -l | -u");
            return;
        }

        TaskManager manager = new TaskManager();

        switch (args[0]) {
            case "-h":
                System.out.println("Usage: TaskTrackerCLI [OPTIONS]");
                System.out.println("Options:");
                System.out.println("  -h                                Show this list.");
                System.out.println("  -a <task description>             Add a new task to the list.");
                System.out.println("  -r <task id>                      Remove a task from the list.");
                System.out.println("  -u <task id> [OPTIONS] <value>     Update description or status of specified task.");
                System.out.println("    Options:");
                System.out.println("      --description                 Update description of task.");              
                System.out.println("      --status                      Update status of task.");
                System.out.println("  -l <todo|in-progress|done>        List tasks, filter by status using 'todo', 'in-progress' or 'done'.");
                break;

            case "-a":
                if (args.length < 2) {
                    System.err.println("Not enough arguments.");
                    System.err.println("Usage: TaskTrackerCLI -a <task description>");
                    return;
                }

                manager.createTask(args[1]);
                break;

            case "-r":
                if (args.length < 2) {
                    System.err.println("Not enough arguments.");
                    System.err.println("Usage: TaskTrackerCLI -r <task id>");
                    return;
                }

                try {
                    manager.deleteTask(Integer.parseInt(args[1]));
                } catch (NumberFormatException e) {
                    System.err.println("Usage: TaskTrackerCLI -r <task id>");
                    return;
                }

                break;

            case "-l":
                if (args.length > 1) {
                    String statusString = args[1].toLowerCase();

                    switch (statusString) {
                        case "todo":
                            manager.listTasks(Status.TODO);
                            break;
                        
                        case "in-progress":
                            manager.listTasks(Status.IN_PROGRESS);
                            break;
                        
                        case "done":
                            manager.listTasks(Status.DONE);
                            break;
                    
                        default:
                            System.err.println("Usage: TaskTrackerCLI -r <status>");
                            return;
                    }
                } else {
                    manager.listTasks();
                }
                break;
            
            case "-u":
                if (args.length < 4) {
                    System.err.println("Not enough arguments.");
                    System.err.println("Usage: TaskTrackerCLI -u <task id> <option> <value>");
                    return;
                }

                switch(args[2].toLowerCase()) {
                    case "--description":
                        try {
                            manager.updateTask(Integer.parseInt(args[1]), args[3]);

                        } catch (NumberFormatException e) {
                            System.err.println("Invalid ID.");
                            System.err.println("Usage: TaskTrackerCLI -u <task id> <option> <value>");
                            return;

                        } catch (NoSuchElementException e) {
                            System.err.println("ID not found.");
                            System.err.println("Usage: TaskTrackerCLI -u <task id> <option> <value>");
                            return;
                        }
                        break;

                    case "--status":
                        try {
                            switch (args[3].toLowerCase()) {
                                case "todo":
                                    manager.updateTask(Integer.parseInt(args[1]), Status.TODO);
                                    break;
                            
                                case "in-progress":
                                    manager.updateTask(Integer.parseInt(args[1]), Status.IN_PROGRESS);
                                    break;

                                case "done":
                                    manager.updateTask(Integer.parseInt(args[1]), Status.DONE);
                                    break;

                                default:
                                    System.err.println("Invalid status.");
                                    System.err.println("Usage: TaskTrackerCLI -u <task id> <option> <value>");
                                    return;
                            }

                        } catch (NumberFormatException e) {
                            System.err.println("Invalid ID.");
                            System.err.println("Usage: TaskTrackerCLI -u <task id> <option> <value>");
                            return;

                        } catch (NoSuchElementException e) {
                            System.err.println("ID not found.");
                            System.err.println("Usage: TaskTrackerCLI -u <task id> <option> <value>");
                            return;
                        }
                }
                break;

            default:
                System.err.println("Usage: TaskTrackerCLI -h | -a | -r | -l | -u");
                return;
        }
    }
}