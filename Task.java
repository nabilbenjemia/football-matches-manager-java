public class Task {
    private String dateOfCreation;
    private String deadline;
    private String description;
    private String title;
    private int priority;

    //Constructor
    public Task(String title, String description, String dateOfCreation, String deadline, int priority) {
        this.title = title;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
        this.deadline = deadline;
        this.priority = priority;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


    public String toString() {
        return "{" +
                "dateOfCreation='" + dateOfCreation + '\'' +
                ", deadline='" + deadline + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                '}';
    }
}
