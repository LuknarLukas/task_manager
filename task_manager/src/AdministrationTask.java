

public class AdministrationTask extends Task{

    public AdministrationTask(String type, String description, String date, boolean important) {
        super(type, description, date, important);
    }



    @Override
    public String toString() {
        return "\n" + "AdministrationTask:  \n" +
                "Popis: " + getDescription() + "\n" +
                "Date:  " + getDate() + "\n" +
                "Important: " + isImportant() + "\n";
    }


}
