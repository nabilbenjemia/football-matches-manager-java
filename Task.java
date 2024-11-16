public class Task {
    private String dateOfCreation;
    private String deadline;
    private String description;
    private String title;
    private int priority;

    private boolean isDone;

    //Constructor
    public Task(String title, String description, String dateOfCreation, String deadline, int priority) {
        this.title = title;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
        this.deadline = deadline;
        this.priority = priority;
        this.isDone = false;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public boolean getDone() {
        return isDone;
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

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString() {
        String done = isDone? ", done" : ", not done";
        return "{" +
                "dateOfCreation='" + dateOfCreation + '\'' +
                ", deadline='" + deadline + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                 done+
                '}';
    }
}
