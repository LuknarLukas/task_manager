
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Task> listOfTask;
        System.out.println("Zadajte nazov suboru");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        listOfTask = Reading.loadTaskFile(filePath);
        LocalDate currentDate = LocalDate.now();
        boolean run = true;


        while (run) {
            menu();
            String choice = scanner.next();
            switch (choice) {
                case "1" -> {
                    Task task = listOfTask.peek();
                    if (task == null) {
                        System.out.println("No task available.");
                        break;
                    }
                    if (task.getType().equals("administracia")) {
                        AdministrationTask adTask = (AdministrationTask) task;
                        System.out.println(adTask);
                        boolean run1 = true;
                        while (run1) {
                            System.out.println("Chcete si prevziat Ulohu? y/n");
                            String option = scanner.next();
                            switch (option) {
                                case "y" -> {
                                    System.out.println("Administrativnu ulohu mozete Vyriesit alebo Odlozit v/o");
                                    while (run1) {
                                        String option1 = scanner.next();
                                        switch (option1) {
                                            case "v" -> {
                                                Writing.writeAdministrationTasksToCSV(adTask, "output_tasks.csv", "Vyriesena");
                                                adTask.vyriesitUlohu(adTask, listOfTask);
                                                run1 = false;
                                            }
                                            case "o" -> {
                                                adTask.odlozUlohu(currentDate);
                                                run1 = false;
                                            }
                                            default -> System.out.println("Nespravny znak");

                                        }
                                    }
                                }
                                case "n" -> run1 = false;
                                default -> System.out.println("Nespravny znak");
                            }
                        }
                    } else if (task.getType().equals("reklamacia")) {
                        ComplaintTask coTask = (ComplaintTask) task;
                        System.out.println(coTask.toFullString());
                        boolean run1 = true;
                        while (run1) {
                            System.out.println("Chcete si prevziat Ulohu? y/n");
                            String option = scanner.next();
                            switch (option) {
                                case "y" -> {
                                    System.out.println("Reklamaciu mozete Vyriesit alebo Zrusit v/z");
                                    while (run1) {
                                        String option1 = scanner.next();
                                        switch (option1) {
                                            case "v" -> {
                                                Writing.writeTaskComplaintToCSV(coTask, "output_tasks.csv", "Vyriesena");
                                                coTask.vyriesitUlohu(coTask, listOfTask);
                                                run1 = false;
                                            }
                                            case "z" -> {
                                                Writing.writeTaskComplaintToCSV(coTask, "output_tasks.csv", "Zrusena");
                                                coTask.zrusReklamaciu(coTask, listOfTask);
                                                run1 = false;
                                            }
                                            default -> System.out.println("Nespravny znak");

                                        }
                                    }
                                }
                                case "n" -> run1 = false;
                                default -> System.out.println("Nespravny znak");
                            }
                        }
                    } else {
                        System.out.println("Neznama uloha");
                    }
                }
                case "2" -> {
                    String nextFile = scanner.next();
                    Reading.readingNewFile(listOfTask, nextFile);
                }
                case "3" -> {
                    Writing.writeAllTaskToCSV(listOfTask, "undone_tasks.csv");
                    run = false;
                }

                case "4" -> System.out.println(listOfTask);


                default -> System.out.println("Neplatná voľba. Skúste znova.");
            }
        }
        scanner.close();
    }


    public static void menu(){
        System.out.println();
        System.out.println("=== Task Manager ===");
        System.out.println("1. Zobraziť úlohu v poradí");
        System.out.println("2. Pridať úlohy");
        System.out.println("3. Ukončiť aplikáciu");
        System.out.println("Vyberte akciu: ");
    }


}

