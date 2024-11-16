import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Watch deep learning course", "Lecture 03", "15.11.2024", "20.11.2024", 1);
        taskManager.createTask("Watch robotics course", "Lecture 04", "12.11.2024", "19.11.2024", 1);

        System.out.println(taskManager);

        System.out.println("\nRemoving the first task\n");
        taskManager.removeTask("Watch deep learning course");

        System.out.println(taskManager);

        System.out.println("\nModifying description\n");
        taskManager.modifyDescription("Watch robotics course", "Lecture + Exercice 04");

        System.out.println(taskManager);

        System.out.println("\nMaking the Task done\n");
        taskManager.setTaskDone("Watch robotics course");

        System.out.println(taskManager);

        System.out.println("\nChanging Priority\n");
        taskManager.modifyPriority("Watch robotics course", 12);

        System.out.println(taskManager);
    }
}
