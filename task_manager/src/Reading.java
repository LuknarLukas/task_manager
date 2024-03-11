import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Reading {

    public static PriorityQueue<Task> loadTaskFile(String filePath) {
        PriorityQueue<Task> taskPriorityQueue = new PriorityQueue<>(new TaskComparator());

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 3) {
                    String type = data[0];
                    String description = data[1];
                    String date = data[2];
                    boolean important = data.length == 4 && data[3].equals("important");

                    if (type.equals("administracia")) {
                        AdministrationTask administrationTask = new AdministrationTask(type, description, date, important);
                        taskPriorityQueue.add(administrationTask);
                    } else if (type.equals("reklamacia")) {
                        LocalDate currentDate = LocalDate.now();
                        LocalDate givenDate = convertStringToLocalDate(data[2]);
                        if (!important){
                            boolean isOlderThan15Days = isOlderThan15Days(givenDate, currentDate);
                            if (isOlderThan15Days){
                                important = true;
                            }
                        }
                        ComplaintTask complaintTask = new ComplaintTask(type, description, date, important);
                        taskPriorityQueue.add(complaintTask);
                    }
                }
                taskComparator(taskPriorityQueue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return taskPriorityQueue;
    }

    public static void readingNewFile(PriorityQueue<Task> taskPriorityQueue, String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 3) {
                    String type = data[0];
                    String description = data[1];
                    String date = data[2];
                    boolean important = data.length == 4 && data[3].equals("important");

                    if (type.equals("administracia")) {
                        AdministrationTask administrationTask = new AdministrationTask(type, description, date, important);
                        taskPriorityQueue.add(administrationTask);
                    } else if (type.equals("reklamacia")) {
                        ComplaintTask complaintTask = new ComplaintTask(type, description, date, important);
                        taskPriorityQueue.add(complaintTask);
                    }
                }

                taskComparator(taskPriorityQueue);
            }
            System.out.println("All tasks appended to CSV successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void taskComparator(PriorityQueue<Task> taskPriorityQueue) {
        TaskComparator taskComparator = new TaskComparator();
        List<Task> sortedTasks = new ArrayList<>();
        while (!taskPriorityQueue.isEmpty()) {
            sortedTasks.add(taskPriorityQueue.poll());
        }
        sortedTasks.sort(taskComparator);
        taskPriorityQueue.addAll(sortedTasks);
    }

    @org.jetbrains.annotations.Nullable
    static LocalDate convertStringToLocalDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    static boolean isOlderThan15Days(LocalDate date1, LocalDate date2) {
        long differenceInDays = ChronoUnit.DAYS.between(date1, date2);
        return differenceInDays > 15;
    }
}
