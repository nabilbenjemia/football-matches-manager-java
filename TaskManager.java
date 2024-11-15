import java.util.LinkedList;
import java.util.List;

public class TaskManager {

    private List<Task> listOfTasks;
    public int capacity;

    public TaskManager() {
        this.listOfTasks = new LinkedList<Task>();
    }

    public void createTask(String title, String description, String dateOfCreation, String deadline, int priority) {
        Task task = new Task(title, description, dateOfCreation, deadline, priority);
        for(Task oldTask: listOfTasks) {
            if(task.getTitle().equals(oldTask.getTitle())) {
                System.out.println("This task already exists");
                return;
            }
        }
        listOfTasks.add(task);
    }

    public void removeTask(String title) {
        for(Task oldTask: listOfTasks) {
            if(title.equals(oldTask.getTitle())) {
                int index = getTaskIndex(title);
                if(index >= 0) {
                    listOfTasks.remove(0);
                } else {
                    System.out.println("This task was not found");
                }
            } else {
                System.out.println("This task is not found and can't be removed");
            }
        }
    }

    private int getTaskIndex(String title) {
        for(int i=0; i<listOfTasks.size(); i++) {
            if(listOfTasks.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        String string = "Number of Tasks: " + listOfTasks.size() +"\n";
        for(int i=0; i<listOfTasks.size(); i++) {
            string += "Task N:"+ (i+1) + " " + listOfTasks.get(i).toString() + "\n";
        }
        return string;
    }
}
