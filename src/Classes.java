import java.util.Scanner;
import java.util.ArrayList;

public class Classes {
    private String classTerm;
    private String className;
    private String classProf;
    private String classType;
    private String classLoc;
    private String classTime;
    private String classDays;

    public String getClassTerm() {
        return classTerm;
    }

    public String getClassName() {
        return className;
    }

    public String getClassProf() {
        return classProf;
    }

    public String getClassType() {
        return classType;
    }

    public String getClassLoc() {
        return classLoc;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getClassDays() {
        return classDays;
    }

    public void setClassTerm(String classTerm) {
        this.classTerm = classTerm;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassProf(String classProf) {
        this.classProf = classProf;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setClassLoc(String classLoc) {
        this.classLoc = classLoc;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public void setClassDays(String classDays) {
        this.classDays = classDays;
    }


    //Static Method to Find the Term of the Class
    public static String findTerm(Scanner y) {
        String classTerm = "";
        while (y.hasNext()) {
            String word = y.next();
            if (word.equals("Autumn")) {
                classTerm = "Autumn ";
                y.next();
                String year = y.next();
                classTerm = classTerm + year;
                break;
            } else if (word.equals("Spring")) {
                classTerm = "Spring ";
                y.next();
                String year = y.next();
                classTerm = classTerm + year;
                break;
            } else if (word.equals("Winter")) {
                classTerm = "Winter ";
                y.next();
                String year = y.next();
                classTerm = classTerm + year;
                break;
            } else if (word.equals("Summer")) {
                classTerm = "Summer ";
                y.next();
                String year = y.next();
                classTerm = classTerm + year;
                break;
            }
        }
        if (classTerm.equals("")){
            System.out.println("Please copy your schedule correctly.");
        }
            return classTerm;
    }

    //Static Method to Find All Class Names
    public static ArrayList<String> findAllClassNames(Scanner y){
        ArrayList<String> classes = new ArrayList<>();
        while (y.hasNextLine()) {
            String previousLine = y.nextLine();
            try {
                String word = y.next();
                if (word.equals("Associated")) {
                    classes.add(previousLine);
                }
            } catch (Exception e) {
                break;
            }
        }
        ArrayList<String> parsedClasses = new ArrayList<>();
        for (int i = 0; i < classes.size(); i++) {
            String className = classes.get(i);
            for (int j = 0; j < className.length(); j++) {
                String letter = className.substring(j, j + 1);
                if (letter.equals("-")) {
                    String classNameParse = className.substring(j + 2, className.length() - 6);
                    parsedClasses.add(classNameParse);
                    break;

                }
            }
        }
        return parsedClasses;
    }


    //Static Method to Find the Number of Classes
    public static int findNumberOfClasses(Scanner y){
            int count = 0;
            while (y.hasNextLine()) {
                String thisLine = y.next();
                if (thisLine.equals("Associated")) {
                    count++;
                }
            }
            return count;
        }


    }

