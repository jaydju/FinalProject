import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Classes> classes;

    public String getName() {
        return name;
    }

    public int getClassSize() {
        return classes.size();
    }
//
//    public Classes getClassList() {
//        for (int i = 0; i < classes.size(); i++) {
//            return classes.get(i);
//        }
//    }
}
