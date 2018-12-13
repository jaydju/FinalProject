import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

//Integers are faster to traverse
//The program breaks down if you paste it from TC to a word doc first then to a text file. Word Doc processes it with some binary formatting.
public class ScheduleMain {
    public static void main(String[] args) throws java.io.FileNotFoundException {
        //We Initialize the File Name, remember to ask for user input and print line by line to a text file.
        String fileName = "scheduleTC.txt";
        Scanner in = new Scanner(new FileReader(fileName));

        //Initialize the student class which holds an array of list of Classes and their Information
        Student s1 = new Student();

        //Method to Find Class Term
        String classTerm = Classes.findTerm(in);
        s1.setClassTerm(classTerm);
        System.out.println(Classes.findNumberOfClasses(in));

        //Method to Find All Class Names
        Scanner it = new Scanner(new FileReader(fileName));
        ArrayList<String> allClassNames;
        allClassNames = Classes.findAllClassNames(it);

        for (int i = 0; i < allClassNames.size(); i ++){
            System.out.println(allClassNames.get(i));
        }

        //Finds Class Details but only UP To Two Lines
        Scanner s = new Scanner(new FileReader(fileName));
        ArrayList<String> classDetailFirst = new ArrayList<>();
        ArrayList<String> classDetailSecond = new ArrayList<>();

        while(s.hasNextLine()){
            String line = s.next();
            if (line.equals("Type")){
                s.nextLine();
                String firstDetails = s.nextLine();
                classDetailFirst.add(firstDetails);
                String secondDetails = s.nextLine();
                classDetailSecond.add(secondDetails);
                }
            }


        System.out.println(Arrays.toString(classDetailFirst.toArray()));
        System.out.println(Arrays.toString(classDetailSecond.toArray()));

        //Checking if Class Details is Complete (Conditioned On Only First Line Containing TBA
        // If TBA is in the First Line, it is removed from the Array. The Second Line index of that same class is also removed.
        ArrayList<String> incompleteClass = new ArrayList<>(); //The incompleteClass Array List saves the incomplete line information to be printed out to the user later.
        for (int j = 0; j < classDetailFirst.size(); j++){
            System.out.println(classDetailFirst.get(j));
            String thisClassDetail = classDetailFirst.get(j);
            if (thisClassDetail.toLowerCase().contains("TBA".toLowerCase())) {
                incompleteClass.add(allClassNames.get(j) + classDetailFirst.get(j));
                classDetailFirst.remove(j);
                classDetailSecond.remove(j);
                allClassNames.remove(j);
            }
        }

        for (int i = 0; i < classDetailFirst.size(); i++){
            System.out.println(classDetailFirst.get(i));
        }

        //Checking if Class Size is Correct
        System.out.println(classDetailFirst.size());
        System.out.println(classDetailSecond.size());

        for (int i = 0; i < classDetailSecond.size(); i++){
            System.out.println(classDetailSecond.get(i));
        }

        System.out.println(Arrays.toString(classDetailFirst.toArray()));
        System.out.println(Arrays.toString(classDetailSecond.toArray()));

        //Handling Instances of Seminars if the Time for Both Seminars is the Same Replace
        ArrayList<String> varyingClassDetails = new ArrayList<>();
        for (int j = 0; j < classDetailFirst.size(); j++){
            ArrayList<String> seminarTokens1 = new ArrayList<>();
            ArrayList<String> seminarTokens2 = new ArrayList<>();
            System.out.println(classDetailFirst.get(j));
            String thisClassDetail = classDetailFirst.get(j);
            if (thisClassDetail.toLowerCase().contains("Seminar".toLowerCase()) || thisClassDetail.toLowerCase().contains("Laboratory".toLowerCase())) {
                String[] words = thisClassDetail.split("\\s+");
                for (String word : words){
                    seminarTokens1.add(word);
                }
                String[] words2 = classDetailSecond.get(j).split("\\s+");
                for (String word : words2){
                    seminarTokens2.add(word);
                }
                //********** NEED TO RECODE THIS TO MORE GENERAL/ROBUST CASE. IF THE DAYS ARE THE SAME AND THE TIME OVERLAPS, THEN REMOVE IT.
                String day1 = seminarTokens1.get(6);
                String day2 = seminarTokens2.get(6);
                String time1 = seminarTokens1.get(1) + seminarTokens1.get(2) + seminarTokens1.get(3) + seminarTokens1.get(4) + seminarTokens1.get(5);
                String time2 = seminarTokens2.get(1) + seminarTokens2.get(2) + seminarTokens2.get(3) + seminarTokens2.get(4) + seminarTokens2.get(5);
                System.out.println(time1);
                System.out.println(time2);
                if(time1.equals(time2)|| day1.equals(day2)){
                    varyingClassDetails.add(allClassNames.get(j) + classDetailSecond.get(j));
                    classDetailSecond.set(j, " ");
                }
            }
        }
        for (int i = 0; i < classDetailFirst.size(); i++){
            System.out.println(classDetailFirst.get(i));
        }
        for (int j = 0; j < classDetailSecond.size(); j++){
            System.out.println(classDetailSecond.get(j));
        }

        System.out.println(Arrays.toString(classDetailFirst.toArray()));
        System.out.println(Arrays.toString(classDetailSecond.toArray()));

        for (int i = 0; i < classDetailFirst.size(); i++){

        }
        System.out.println(classDetailFirst.get(3));
        System.out.println(classDetailSecond.get(3));

        //We iterate through the Array Containing the Lines of Class Details for the FIRST ARRAY
        for (int i = 0; i < classDetailFirst.size(); i++){
            //We create another Array List to Save the Tokens
            ArrayList<String> classDetailTokens1 = new ArrayList<>();
            String firstClassDetail = classDetailFirst.get(i);
            String[] words = firstClassDetail.split("\\s+");
            //We Add the Tokenized Details of Each Class Detail Line into the Token Array list
            for (String word: words){
                classDetailTokens1.add(word);
            }
            //The Days Information is Always Going to Have an Index Position of 6 Assuming the Schedule Information is Complete
            String days = classDetailTokens1.get(6);
            String time = classDetailTokens1.get(1) + classDetailTokens1.get(2) + classDetailTokens1.get(3) + classDetailTokens1.get(4) + classDetailTokens1.get(5);
            Classes c1 = new Classes();
            c1.setClassName(allClassNames.get(i));
            c1.setClassDays(days);
            c1.setClassTime(time);

            //Class Location
            String location = "";
            location = classDetailTokens1.get(7);
            for (int j = 8; j < classDetailTokens1.size(); j++){
                //We check to see if the Token refers to the Date which is identified by "/"
                if (classDetailTokens1.get(j).contains("/")){
                    break;
                }
                else {
                    location += " " + classDetailTokens1.get(j);
                }


            }
            c1.setClassLoc(location);
            System.out.println(location);


            //Class Professor
            System.out.println(Arrays.toString(classDetailTokens1.toArray()));
            for (int j = 0; j < classDetailTokens1.size(); j++){
                String token = classDetailTokens1.get(j);
                //We check if the Tokens equals Lecture/Seminar/Laboratory etc; we know the Token After This is the Start of the Prof Name
                if (token.equals("Lecture") || token.equals("Seminar") || token.equals("Laboratory")){
                    String professorName = "";
                    for(int h = j + 1; h < classDetailTokens1.size(); h++ ){
                        if (classDetailTokens1.get(h).equals(("(P)E-mail")) || classDetailTokens1.get(h).equals("E-mail")){
                            break;
                        }
                        else {
                            professorName = professorName + " " + classDetailTokens1.get(h);
                        }
                        }
                    professorName = professorName.substring(1);
                    System.out.println(professorName);
                    c1.setClassProf(professorName);
                    }

                    }



            System.out.println(days);
            System.out.println(time);
            s1.addClass(c1);
        }

        //We iterate through the Array Containing the Lines of Class Details for the SECOND ARRAY
        System.out.println(classDetailSecond);
        for (int i = 0; i < classDetailSecond.size(); i++){
            //We create another Array List to Save the Tokens
            ArrayList<String> classDetailTokens2 = new ArrayList<>();
            String secondClassDetail = classDetailSecond.get(i);
            if (!(secondClassDetail.equals(" ") || secondClassDetail.equals(""))){
                String validClassDetails = classDetailSecond.get(i);
                String[] words = validClassDetails.split("\\s+");
                for (String word: words){
                    classDetailTokens2.add(word);
                }
                //The Days Information is Always Going to Have an Index Position of 6 Assuming the Schedule Information is Complete
                String days = classDetailTokens2.get(6);
                String time = classDetailTokens2.get(1) + classDetailTokens2.get(2) + classDetailTokens2.get(3) + classDetailTokens2.get(4) + classDetailTokens2.get(5);
                Classes c1 = new Classes();
                c1.setClassName(allClassNames.get(i));
                c1.setClassDays(days);
                c1.setClassTime(time);

                //Need to Add Class Location Here
                String location = "";
                location = classDetailTokens2.get(7);
                for (int j = 8; j < classDetailTokens2.size(); j++){
                    //We check to see if the Token refers to the Date which is identified by "/"
                    if (classDetailTokens2.get(j).contains("/")){
                        break;
                    }
                    else {
                        location += " " + classDetailTokens2.get(j);
                    }


                }
                c1.setClassLoc(location);
                System.out.println(location);

                //Class Professor
                System.out.println(Arrays.toString(classDetailTokens2.toArray()));
                for (int j = 0; j < classDetailTokens2.size(); j++){
                    String token = classDetailTokens2.get(j);
                    //We check if the Tokens equals Lecture/Seminar/Laboratory etc; we know the Token After This is the Start of the Prof Name
                    if (token.equals("Lecture") || token.equals("Seminar") || token.equals("Laboratory")){
                        String professorName = "";
                        for(int h = j + 1; h < classDetailTokens2.size(); h++ ){
                            if (classDetailTokens2.get(h).equals(("(P)E-mail")) || classDetailTokens2.get(h).equals("E-mail")){
                                break;
                            }
                            else {
                                professorName = professorName + " " + classDetailTokens2.get(h);
                            }
                        }
                        professorName = professorName.substring(1);
                        System.out.println(professorName);
                        c1.setClassProf(professorName);
                    }
                }


                System.out.println(days);
                System.out.println(time);
                s1.addClass(c1);
            }
            }


        System.out.println("THIS IS A LIMITER");

        //Iterating through the Students Classes:
        int boxX = 0;
        int boxY = 0;
        int boxHeight = 0;
        for (int i = 0; i < s1.getClasses().size(); i++) {
            Classes c1 = s1.getClasses().get(i);
            int classSize = c1.getClassDays().length();

            for (int j = 0; j < classSize; j++){
                String firstClassDay = c1.getClassDays().substring(j,j+1);
                System.out.println(firstClassDay);
                if (firstClassDay.equals("M")){
                    boxX = 80;
                }
                else if (firstClassDay.equals("T")){
                    boxX = 320;
                }
                else if (firstClassDay.equals("W")){
                    boxX = 560;
                }
                else if (firstClassDay.equals("R")){
                    boxX = 800;
                }
                else {
                    boxX = 0;
                    System.out.println("Can't find class day");
                }

                //Excess String Traversals

                ArrayList<String> tokenTime = new ArrayList<>();
                String[] classTime = c1.getClassTime().split("[\\s-:]+");
                for (String words: classTime){
                    tokenTime.add(words);
                }
                int firstTime = Integer.parseInt(tokenTime.get(0));
                String secondDetail = tokenTime.get(1);
                if (secondDetail.toLowerCase().contains("pm")){
                    firstTime += 12;
                }
//                boxY = firstTime; //Algorithm To Relate the Coordinates to the Time
                //Consider Switch Cases:
                if (firstTime == 9){boxY = 90;}
                else if (firstTime == 10){boxY = 150;} else if (firstTime == 11){boxY = 210;} else if (firstTime == 12){boxY = 270;}
                else if (firstTime == 13){boxY = 330;} else if (firstTime == 14){boxY = 390;} else if (firstTime == 15){boxY = 450;}
                else if (firstTime == 16){boxY = 510;} else if (firstTime == 17){boxY = 570;} else if (firstTime == 18){boxY = 630;}
                else if (firstTime == 19){boxY = 690;} else if (firstTime == 20){boxY = 750;} else {boxY = 810;}

                //difference between hour is 60 units
                int minutes = Integer.parseInt(secondDetail.substring(0, secondDetail.length() - 2));
                boxY += minutes;

                //Find Height here to determine size of the box
                int secondTime = Integer.parseInt(tokenTime.get(2));
                String fourthDetail = tokenTime.get(3);
                if (fourthDetail.toLowerCase().contains("pm")){
                    secondTime += 12;
                }

                if (secondTime == 9){boxHeight = 90;}
                else if (secondTime == 10){boxHeight = 150;} else if (secondTime == 11){boxHeight = 210;} else if (secondTime == 12){boxHeight = 270;}
                else if (secondTime == 13){boxHeight = 330;} else if (secondTime == 14){boxHeight = 390;} else if (secondTime == 15){boxHeight = 450;}
                else if (secondTime == 16){boxHeight = 510;} else if (secondTime == 17){boxHeight = 570;} else if (secondTime == 18){boxHeight = 630;}
                else if (secondTime == 19){boxHeight = 690;} else if (secondTime == 20){boxHeight = 750;} else {boxHeight = 810;}

                //Add Second Minutes
                int minutes2 = Integer.parseInt(fourthDetail.substring(0, fourthDetail.length() - 2));
                boxHeight += minutes2;

                boxHeight = boxHeight - boxY;


                System.out.println(firstTime);
                System.out.println(Arrays.toString(tokenTime.toArray()));

                //Need to adjust the positive Y here.
                //THe length of each vertical segment is 840 long we need to parse by time between 9am - 920pm.

            }
            System.out.println(c1.getClassName());
            System.out.println(c1.getClassProf());
            System.out.println(c1.getClassTime());

            Rectangle classBox = new Rectangle(boxX, boxY, 240, boxHeight);
            classBox.draw();
            classBox.setColor(Color.BLUE);
            classBox.fill();
        }


        //Create Static Method (in Classes) to convert
        //Convert time to width and height


//        //         CREATING LINE SEGMENTS ACTUAL DRAWING HERE
        Line horizontalTop = new Line(10, 10, 1280, 10);
        horizontalTop.draw();
        Line verticalLeft = new Line(10, 10, 10, 850);
        verticalLeft.draw();
        Line horizontalBot = new Line(10, 810, 1280, 810);
        horizontalBot.draw();
        Line verticalRight = new Line(1280, 850, 1280, 10);
        verticalRight.draw();
        Line verticalTime = new Line (80, 10, 80, 850);
        verticalTime.draw();
//        Line header = new Line(10, 100, 1210, 100);
//        header.draw();
//        Line subHeader = new Line(10, 150, 1210, 150);
//        subHeader.draw();

        int hour = 9;
        int minute = 0;
        String meridian = "AM";
        for(int i = 90; i <= 850; i += 20){
            Line segmented = new Line(10, i,1280, i );
            segmented.draw();
            String displayTime = hour + ":" + minute + "0 " + meridian;
            Text time = new Text(15, i, displayTime);
            if (i < 850){
                time.draw();
            }
            else{
                continue;
            }
            time.draw();
            minute += 2;
            if (minute >= 6){
                minute = 0;
                hour++;
            }
            if (hour > 12)
            {
                hour = hour - 12;
                meridian = "PM";
            }
        }

        //Days of the Week Drawing
        ArrayList<String> daysOfWeek = new ArrayList<>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");

        int indexOfDay = 0;
        for (int i = 80; i <= 1280; i+= 240 ){
            Line verticalSegment = new Line (i, 10, i, 850);
            verticalSegment.draw();

            if (i < 1280) {
                String curDay = daysOfWeek.get(indexOfDay);
                Text days = new Text(i + 95, 40, curDay);
                days.draw();
                days.grow(35, 15);
                indexOfDay++;
            }
        }




//        ArrayList<String> classDetails = new ArrayList<>();
//        String details = "";
//        String noTime1 = "";
//        String noTime2 = "";
//        String date = "";
//        String date2 = "";
//        while (s.hasNext()) {
//            String finder = s.next();
//            if (finder.equals("Scheduled")) {
//                s.nextLine();
//                s.nextLine();
//                details = s.nextLine();
//                for (int i = 0; i < details.length(); i++) {
//                    String letters = details.substring(i, i + 1);
//                    if (letters.equals("/")) {
//                        details = details.substring(6, i - 3);
//                        classDetails.add(details);
//                    }
//                }
//                for (int i = 0; i < details.length(); i++) {
//                    String letter = details.substring(i, i + 1);
//                    if (letter.equals("m")) {
//                        noTime1 = details.substring(i + 4);
//                        break;
//                    }
//                }
//                for (int h = 0; h < noTime1.length(); h++) {
//                    String lettering = noTime1.substring(h, h + 1);
//                    if (lettering.equals("m")) {
//                        noTime2 = noTime1.substring(h + 2);
//                        for (int k = 0; k < noTime2.length(); k++) {
//                            String spaceFinder = noTime2.substring(k, k + 1);
//                            if (spaceFinder.equals("\t")) {
//                                date = noTime2.substring(0, k);
//                                System.out.println(date);
//                                break;
//                            }
//                        }
//
//                    }
//                }
//                String nextDetail = s.nextLine();
//                String nextDetailNoDate = "";
//                String nextDetailNoTime = "";
//                String nextDetailNoTime2;
//                if (!nextDetail.equals("")) {
//                    for (int i = 0; i < nextDetail.length(); i++) {
//                        String letters = nextDetail.substring(i, i + 1);
//                        if (letters.equals("/")) {
//                            nextDetailNoDate = nextDetail.substring(6, i - 3);
//                            break;
//                        }
//                    }
//
//                    for (int i = 0; i < nextDetailNoDate.length(); i++) {
//                        String letter = nextDetailNoDate.substring(i, i + 1);
//                        if (letter.equals("m")) {
//                            nextDetailNoTime = nextDetailNoDate.substring(i + 4);
//                            break;
//                        }
//                    }
//                    for (int h = 0; h < nextDetailNoTime.length(); h++) {
//                        String lettering = nextDetailNoTime.substring(h, h + 1);
//                        if (lettering.equals("m")) {
//                            nextDetailNoTime2 = nextDetailNoTime.substring(h + 2);
//                            for (int k = 0; k < nextDetailNoTime2.length(); k++) {
//                                String spaceFinder = nextDetailNoTime2.substring(k, k + 1);
//                                if (spaceFinder.equals("\t")) {
//                                    date2 = nextDetailNoTime2.substring(0, k);
//                                    System.out.println(date2);
//                                    break;
//                                }
//                            }
//
//                        }
//                    }
//                    if (date.equals(date2)) {
//                        System.out.println("This is a seminar");
//                    } else if (date2.equals("")) {
//                        System.out.println("This is a lecture");
//                    } else {
//                        System.out.println("This class meets twice a week");
//                    }
//
//
//                }
//
//            }
//
//        }





//        ArrayList<String> classes = new ArrayList<>();
//        while (it.hasNextLine()) {
//            String previousLine = it.nextLine();
//            try {
//                String word = it.next();
//                if (word.equals("Associated")) {
//                    classes.add(previousLine);
//                }
//            } catch (Exception e) {
//                break;
//            }
//        }
//
//        ArrayList<String> parsedClasses = new ArrayList<>();
//        for (int i = 0; i < classes.size(); i++) {
//            String className = classes.get(i);
//            for (int j = 0; j < className.length(); j++) {
//                String letter = className.substring(j, j + 1);
//                if (letter.equals("-")) {
//                    String classNameParse = className.substring(j + 2, className.length() - 6);
//                    parsedClasses.add(classNameParse);
//                    break;
//
//                }
//            }
//        }
//
//        for (int i = 0; i < parsedClasses.size(); i++) {
//            System.out.println(parsedClasses.get(i));
//        }


















































//        for (int i = 0; i < classDetails.size(); i++){
//            String classDets = classDetails.get(i);
//            for (int j = 0; j < classDets.length(); j++){
//                String letter = classDets.substring(j, j+1);
//                if (letter.equals("m")){
//                    noTime1 = classDets.substring(j+4);
//                    break;
//                }
//            }
//            for  (int h = 0; h < noTime1.length(); h++) {
//                String lettering = noTime1.substring(h, h + 1);
//                if (lettering.equals("m")) {
//                    noTime2 = noTime1.substring(h + 2);
//                    break;
//                }
//            }
//            date = noTime2.substring(0,1);
//            System.out.println(date);
//            }


//                String next = s.nextLine();
//                if (!next.equals("")) {
//                    System.out.println(next);
//                    for (int h = 0; h < next.length(); h++) {
//                        String lettering = next.substring(h, h + 1);
//                        if (lettering.equals("pm"))
//                        {
//                            next = next.substring(h);
//                        }
//                        if (lettering.equals("/")) {
//                            next = next.substring(6, h - 3);
//                            System.out.println(next);
//                        }
//                    }
//
//                }
//                for (int j = 0; j < next.length(); j++){
//                    String letters = next.substring(j, j+1);
//                    if(letters.equals("pm")){
//                        next = next.substring(j);
//                        System.out.println(next);
//                    }
//                }

//                while (!s.nextLine().equals("")){
//                    String details2 = s.nextLine();
//                    for(int i = 0; i < details2.length(); i++){
//                        String letters = details2.substring(i, i+1);
//                        if(letters.equals("/")){
//                            details2 = details2.substring(6, i - 3);
//                            int spaceCounter = 0;
//                            int indexOf = 0;
//                            for (int j = 0; j < details2.length() && spaceCounter <= 5 ; j++){
//                                String lettering = details2.substring(j, j+1);
//                                indexOf++;
//                                if(lettering.equals(" ")){
//                                    spaceCounter++;
//                                }
//
//                            }
//                            details2 = details2.substring(indexOf);
//                            System.out.println(details2);
//                            }
//                        }
//
//                }


//        for(int i = 0; i < previousLine.length(); i++){
//            String letter = previousLine.substring(i, i+1);
//            if(letter.equals("-")){
//                String secondLetter = "";
//                String classCode = "";
//                while(!secondLetter.equals("-")){
//                    secondLetter = previousLine.substring(i,i+1);
//                    classCode = classCode + secondLetter;
//                }
//                System.out.println(classCode);
//            }
//        }
//        System.out.println(previousLine);


//            if(word.equals("Hours:")){
//                in.next();
//                String word2 = in.next();
//                System.out.println(word2);
//                String word4 = in.next();
//                String word3 = word + word2;
//                System.out.println(word3);
//                System.out.println(word4);
//                System.out.println("Found");
//                finish = true;
//            }
//
//        }
//        Scanner s = new Scanner(new BufferedInputStream(System.in));
//        System.out.println("Insert your planner here: ");
//        String planner = s.nextLine();
//        System.out.println("\n" + planner);
//


//        String check = "Return to Previous";
//        for (int i = 0; i < planner.length(); i++){
//            if ((planner.substring(i, i + 1) + planner.substring(i + 1, i + 2)).equals("Re")){
//                System.out.println("True");
//
//            }
//        }

//        System.out.println("\n" + planner.replaceAll("\r\n", " "));

//        if (planner.toLowerCase().indexOf(check.toLowerCase()) == -1){ //This checks if the string contains certain words or sentences.
//            System.out.println("Key word found!");
//        }
//        else {
//            System.out.println("keyword not found");
//        }



 }
}


