import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the name of the Task: ");
        String title = scanner.nextLine();
        TaskManager taskManager = new TaskManager();
        System.out.println("Please provide a small description");
        String description = scanner.nextLine();
        System.out.println("Please provide the deadline in the form yyyy-mm-dd");
        String deadline = scanner.nextLine();
        taskManager.createTask(title, description, LocalDate.now(), LocalDate.parse(deadline), 1);
        //taskManager.createTask("Watch deep learning course", "Lecture 03", LocalDate.parse("2024-11-15"), LocalDate.parse("2024-11-20"), 1);
        //taskManager.createTask("Watch robotics course", "Lecture 04", LocalDate.parse("2024-11-20"), LocalDate.parse("2024-11-19"), 1);
        /*
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

        taskManager.createTask("Go Gym", "at 16h", LocalDate.parse("2024-11-18"), LocalDate.parse("2024-11-22"), 1);
        */
        System.out.println("Printing done tasks only:");
        taskManager.viewTasks(true);

        System.out.println("\nPrinting all tasks");
        taskManager.viewTasks(false);
    }
}
