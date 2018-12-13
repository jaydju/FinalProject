import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private String name;
    private String classTerm;
    private ArrayList<Classes> classes = new ArrayList<>();

    public void addClass(Classes y){
        classes.add(y);
    }

    public ArrayList<Classes> getClasses(){
        return classes;
    }

    public void setClassTerm(String classTerm){
        this.classTerm = classTerm;
    }
    //Static Method to Find the Number of Classes
    public static String findName(Scanner y){
        String name = "";
        while (y.hasNextLine()) {
            String thisLine = y.next();
            if (thisLine.equals("Detail")) {
                y.nextLine();
                y.nextLine();
                name = y.nextLine();
                break;
            }
        }
        if (name.equals("")){
            System.out.println("Couldn't find name; please copy everything correctly.");
        }
       return name.substring(10);
    }


}
