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

        public static void fincClassDetails(Scanner y, ArrayList<String> one, ArrayList<String> two){

            while (y.hasNextLine()) {
                String line = y.next();
                if (line.equals("Type")) {
                    y.nextLine();
                    String firstDetails = y.nextLine();
                    one.add(firstDetails);
                    String secondDetails = y.nextLine();
                    two.add(secondDetails);
                }
            }

        }

        public static void removeInstanceTBA(ArrayList<String> incompleteClasses, ArrayList<String> firstLine, ArrayList<String> secondLine, ArrayList<String> classNames){
            for (int j = 0; j < firstLine.size(); j++) {
                String thisClassDetail = firstLine.get(j);
                if (thisClassDetail.toLowerCase().contains("TBA".toLowerCase())) {
                    incompleteClasses.add(classNames.get(j) + firstLine.get(j));
                    firstLine.remove(j);
                    secondLine.remove(j);
                    classNames.remove(j);
                }
            }
        }

        public static void seminarAndLaboratoryHandler(ArrayList<String> varyingClassDetails, ArrayList<String> firstLine, ArrayList<String> secondLine, ArrayList<String> classNames){
            for (int j = 0; j < firstLine.size(); j++) {
                ArrayList<String> seminarTokens1 = new ArrayList<>();
                ArrayList<String> seminarTokens2 = new ArrayList<>();
                String thisClassDetail = firstLine.get(j);
                if (thisClassDetail.toLowerCase().contains("Seminar".toLowerCase()) || thisClassDetail.toLowerCase().contains("Laboratory".toLowerCase())) {
                    String[] words = thisClassDetail.split("\\s+");
                    for (String word : words) {
                        seminarTokens1.add(word);
                    }
                    String[] words2 = secondLine.get(j).split("\\s+");
                    for (String word : words2) {
                        seminarTokens2.add(word);
                    }
                    //********** NEED TO RECODE THIS TO MORE GENERAL/ROBUST CASE. IF THE DAYS ARE THE SAME AND THE TIME OVERLAPS, THEN REMOVE IT.
                    String day1 = seminarTokens1.get(6);
                    String day2 = seminarTokens2.get(6);
                    String time1 = seminarTokens1.get(1) + seminarTokens1.get(2) + seminarTokens1.get(3) + seminarTokens1.get(4) + seminarTokens1.get(5);
                    String time2 = seminarTokens2.get(1) + seminarTokens2.get(2) + seminarTokens2.get(3) + seminarTokens2.get(4) + seminarTokens2.get(5);
                    if (time1.equals(time2) || day1.equals(day2)) {
                        varyingClassDetails.add(classNames.get(j) + secondLine.get(j));
                        secondLine.set(j, " ");
                    }
                }
            }
        }

        public static void setFirstLineClassData(ArrayList<String> firstLine, Student s1){
            for (int i = 0; i < firstLine.size(); i++) {
                //We create another Array List to Save the Tokens
                ArrayList<String> classDetailTokens1 = new ArrayList<>();
                String firstClassDetail = firstLine.get(i);
                String[] words = firstClassDetail.split("\\s+");
                //We Add the Tokenized Details of Each Class Detail Line into the Token Array list
                for (String word : words) {
                    classDetailTokens1.add(word);
                }
                //The Days Information is Always Going to Have an Index Position of 6 Assuming the Schedule Information is Complete
                String days = classDetailTokens1.get(6);
                String time = classDetailTokens1.get(1) + classDetailTokens1.get(2) + classDetailTokens1.get(3) + classDetailTokens1.get(4) + classDetailTokens1.get(5);
                Classes c1 = new Classes();
                c1.setClassName(firstLine.get(i));
                c1.setClassDays(days);
                c1.setClassTime(time);

                //Class Location
                String location = "";
                location = classDetailTokens1.get(7);
                for (int j = 8; j < classDetailTokens1.size(); j++) {
                    //We check to see if the Token refers to the Date which is identified by "/"
                    if (classDetailTokens1.get(j).contains("/")) {
                        break;
                    } else {
                        location += " " + classDetailTokens1.get(j);
                    }


                }
                c1.setClassLoc(location);


                //Class Professor
                for (int j = 0; j < classDetailTokens1.size(); j++) {
                    String token = classDetailTokens1.get(j);
                    //We check if the Tokens equals Lecture/Seminar/Laboratory etc; we know the Token After This is the Start of the Prof Name
                    if (token.equals("Lecture") || token.equals("Seminar") || token.equals("Laboratory")) {
                        String professorName = "";
                        for (int h = j + 1; h < classDetailTokens1.size(); h++) {
                            if (classDetailTokens1.get(h).equals(("(P)E-mail")) || classDetailTokens1.get(h).equals("E-mail")) {
                                break;
                            } else {
                                professorName = professorName + " " + classDetailTokens1.get(h);
                            }
                        }
                        professorName = professorName.substring(1);
                        c1.setClassProf(professorName);
                    }

                }

                s1.addClass(c1);
            }
        }


    }

