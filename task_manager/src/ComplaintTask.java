import java.util.PriorityQueue;

public class ComplaintTask extends Task{

    public ComplaintTask(String type, String description, String date, boolean important) {
        super(type, description, date, important);
    }


    public void zrusReklamaciu(Task task, PriorityQueue<Task> taskPriorityQueue) {
        System.out.println("Úloha vyriešená: " + task.getDescription());
        taskPriorityQueue.remove(task);
    }

    @Override
    public String toString() {
        return "\n" + "ComplaintTask:  \n" +
                "Popis: " + getDescription() + "\n" +
                "Date:  " + getDate() + "\n" +
                "Important: " + isImportant() + "\n";
    }

}
