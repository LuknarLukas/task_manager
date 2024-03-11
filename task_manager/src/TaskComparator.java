import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task uloha1, Task uloha2) {
        boolean jeDolezita1 = uloha1.isImportant();
        boolean jeDolezita2 = uloha2.isImportant();

        if (jeDolezita1 != jeDolezita2) {
            return jeDolezita1 ? -1 : 1;
        }

        return uloha1.getDate().compareTo(uloha2.getDate());
    }

}
