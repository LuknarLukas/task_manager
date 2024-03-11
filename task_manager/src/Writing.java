import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;


public class Writing {

    public static void writeAdministrationTasksToCSV(AdministrationTask task, String filePath, String result) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String taskData = task.getType() + "," + task.getDescription() + "," + task.getDate() + "," + task.isImportant()  +
                    "," + result + "\n";
            writer.write(taskData);
            writer.close();
            System.out.println("AdministrationTask appended to CSV successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTaskComplaintToCSV(ComplaintTask task, String filePath, String result) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String taskData = task.getType() + "," + task.getDescription() + "," + task.getDate() + "," + task.isImportant() +
                    "," + result + "\n";
            writer.write(taskData);
            writer.close();
            System.out.println("ComplainTask appended to CSV successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAllTaskToCSV(PriorityQueue<Task> taskQueue, String filePath){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                while (!taskQueue.isEmpty()) {
                    Task task = taskQueue.poll();
                    String taskData = task.getType() + "," + task.getDescription() + "," + task.getDate() + "," +
                            task.isImportant() + "\n";

                    writer.write(taskData);
                }

                writer.close();
                System.out.println("All tasks appended to CSV successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


}
