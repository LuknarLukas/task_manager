
import java.time.LocalDate;
import java.util.PriorityQueue;

public class Task {

    private final String type;
    private final String description;
    private String date;
    private boolean important;



    public Task(String type, String description, String date, boolean important) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.important = important;
    }



    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public boolean isImportant() {
        return important;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void prevziatUlohu(Task task, PriorityQueue<Task> taskPriorityQueue) {
        System.out.println(task.type + task.date + task.description);


    }

    public void vyriesitUlohu(Task task, PriorityQueue<Task> taskPriorityQueue) {
        System.out.println("Úloha vyriešená: " + this.description);
        taskPriorityQueue.remove(task);
    }

    public void odlozUlohu(LocalDate date) {
        setDate(String.valueOf(date.plusDays(1)));
        System.out.println("Úloha odložená na ďalší deň: " + this.description);
    }

    public String toFullString(){
        return "\n" + "Task:  " +  "Date: " + getDate() + "   "  + isImportant() + "\n" +
                "Popis: " + getDescription();
    }

}
