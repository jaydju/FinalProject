import java.util.ArrayList;

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


}
