import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TaskManager {

    private List<Task> listOfTasks;
    public int capacity;

    public TaskManager() {
        this.listOfTasks = new LinkedList<Task>();
    }

    public Task getTaskbyName(String title) {
        return listOfTasks.get(getTaskIndex(title));
    }

    public void createTask(String title, String description, LocalDate dateOfCreation, LocalDate deadline, int priority) {
        //TODO: check that dateOfCreation is before deadline
        Task task = new Task(title, description, dateOfCreation, deadline, priority);
        for (Task oldTask : listOfTasks) {
            if (task.getTitle().equals(oldTask.getTitle())) {
                System.out.println("This task already exists");
                return;
            }
        }
        listOfTasks.add(task);
    }

    public void removeTask(String title) {
        for (Task oldTask : listOfTasks) {
            if (title.equals(oldTask.getTitle())) {
                int index = getTaskIndex(title);
                if (index >= 0) {
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
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    public void modifyDateOfCreation(String title, LocalDate dateOfCreation) {
        Task task = getTaskbyName(title);
        task.setDateOfCreation(dateOfCreation);
    }

    public void modifyDeadline(String title, LocalDate deadline) {
        Task task = getTaskbyName(title);
        task.setDeadline(deadline);
    }

    public void modifyDescription(String title, String description) {
        Task task = getTaskbyName(title);
        task.setDescription(description);
    }

    public boolean modifyPriority(String title, int priority) {
        if (priority > 5 || priority < 0) {
            System.out.println("The level of priority should be between 1 and 5");
            return false;
        }
        Task task = getTaskbyName(title);
        task.setPriority(priority);
        return true;
    }

    public void setTaskDone(String title) {
        Task task = getTaskbyName(title);
        task.setDone(true);
    }

    public String toString() {
        String string = "Number of Tasks: " + listOfTasks.size() + "\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            string += "Task N:" + (i + 1) + " " + listOfTasks.get(i).toString() + "\n";
        }
        return string;
    }

    public void viewTasks(boolean onlyDone) {
        boolean isEmpty = true;
        if(onlyDone) {
            for (Task task : listOfTasks) {
                if (task.getDone()) {
                    isEmpty = false;
                    System.out.println(task);
                }
            }
        } else {
            for (Task task : listOfTasks) {
                isEmpty = false;
                System.out.println(task);
            }
        }
        if(isEmpty) {
            System.out.println("There are no tasks here");
        }
    }
}
