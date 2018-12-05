import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ScheduleMain {
    public static void main(String[] args) throws java.io.FileNotFoundException {
        Scanner in = new Scanner(new FileReader("scheduleTC.txt"));
        boolean termFound = false;
        String term = "";
        while (in.hasNext() && !termFound) {
            String word = in.next();
            if (word.equals("Autumn")) {
                term = "Fall";
                in.next();
                String year = in.next();
                term = term + year;
                termFound = true;
            } else if (word.equals("Spring")) {
                term = "Spring";
                in.next();
                String year = in.next();
                term = term + year;
                termFound = true;
            } else if (word.equals("Winter")) {
                term = "Winter";
                in.next();
                String year = in.next();
                term = term + year;
                termFound = true;
            } else if (word.equals("Summer")) {
                term = "Summer";
                in.next();
                String year = in.next();
                term = term + year;
                termFound = true;
            }
        }
        if (term.equals("")) {
            System.out.println("Term not found, be sure copy whole thing.");
        }

        System.out.println(term);

//        boolean validClass = false;
//        ArrayList<String> validClasses = new ArrayList<>();
//        validClasses.add("A&HL");
//        validClasses.add("BBSR");

//        while (in.hasNext() && !validClass) {
//            String word = in.next();
//            if (word.equals("A&HL") || word.equals(""))
//        }

//        while (in.hasNext()) {
//            String previousLine = in.nextLine();
//            try {
//                String word = in.next();
//            if (word.equals("Associated")) {
//                count ++;
//                System.out.println(previousLine);
//            }
//            } catch (Exception e){
//                break;
//            }
//        }
//        System.out.println(count);

        ArrayList<String> classes = new ArrayList<>();
        ArrayList<String> teachers = new ArrayList<>();
        int count = 0;
        while (in.hasNext()) {
            String previousLine = in.nextLine();
            try {
                String word = in.next();
                if (word.equals("Associated")) {
                    count++;
                    classes.add(previousLine);
                    in.nextLine();
                    in.nextLine();
                    in.nextLine();
                    String teacher = in.nextLine();
                    String teacher2 = teacher.substring(21, teacher.length() - 6);
                    teachers.add(teacher2);
                }
            } catch (Exception e) {
                break;
            }
        }
        System.out.println(count);

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

        for (int i = 0; i < parsedClasses.size(); i++) {
            System.out.println(parsedClasses.get(i));
        }

        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(teachers.get(i));
        }

        ArrayList<String> classDetails = new ArrayList<>();
        Scanner s = new Scanner(new FileReader("scheduleTC.txt"));
        String details = "";
        String noTime1 = "";
        String noTime2 = "";
        String date = "";
        String date2 = "";
        while (s.hasNext()) {
            String finder = s.next();
            if (finder.equals("Scheduled")) {
                s.nextLine();
                s.nextLine();
                details = s.nextLine();
                for (int i = 0; i < details.length(); i++) {
                    String letters = details.substring(i, i + 1);
                    if (letters.equals("/")) {
                        details = details.substring(6, i - 3);
                        classDetails.add(details);
                    }
                }
                for (int i = 0; i < details.length(); i++) {
                    String letter = details.substring(i, i + 1);
                    if (letter.equals("m")) {
                        noTime1 = details.substring(i + 4);
                        break;
                    }
                }
                for (int h = 0; h < noTime1.length(); h++) {
                    String lettering = noTime1.substring(h, h + 1);
                    if (lettering.equals("m")) {
                        noTime2 = noTime1.substring(h + 2);
                        for (int k = 0; k < noTime2.length(); k++) {
                            String spaceFinder = noTime2.substring(k, k + 1);
                            if (spaceFinder.equals("\t")) {
                                date = noTime2.substring(0, k);
                                System.out.println(date);
                                break;
                            }
                        }

                    }
                }
                String nextDetail = s.nextLine();
                String nextDetailNoDate = "";
                String nextDetailNoTime = "";
                String nextDetailNoTime2;
                if (!nextDetail.equals("")) {
                    for (int i = 0; i < nextDetail.length(); i++) {
                        String letters = nextDetail.substring(i, i + 1);
                        if (letters.equals("/")) {
                            nextDetailNoDate = nextDetail.substring(6, i - 3);
                            break;
                        }
                    }

                    for (int i = 0; i < nextDetailNoDate.length(); i++) {
                        String letter = nextDetailNoDate.substring(i, i + 1);
                        if (letter.equals("m")) {
                            nextDetailNoTime = nextDetailNoDate.substring(i + 4);
                            break;
                        }
                    }
                    for (int h = 0; h < nextDetailNoTime.length(); h++) {
                        String lettering = nextDetailNoTime.substring(h, h + 1);
                        if (lettering.equals("m")) {
                            nextDetailNoTime2 = nextDetailNoTime.substring(h + 2);
                            for (int k = 0; k < nextDetailNoTime2.length(); k++) {
                                String spaceFinder = nextDetailNoTime2.substring(k, k + 1);
                                if (spaceFinder.equals("\t")) {
                                    date2 = nextDetailNoTime2.substring(0, k);
                                    System.out.println(date2);
                                    break;
                                }
                            }

                        }
                    }
                    if (date.equals(date2)) {
                        System.out.println("This is a seminar");
                    } else if (date2.equals("")) {
                        System.out.println("This is a lecture");
                    } else {
                        System.out.println("This class meets twice a week");
                    }


                }

            }

        }



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

//
        Line segment = new Line(10, 10, 1210, 10);
        segment.draw();
        Line verticalLeft = new Line(10, 10, 10, 810);
        verticalLeft.draw();
        Line horizontalBot = new Line(10, 810, 1210, 810);
        horizontalBot.draw();
        Line verticalRight = new Line(1210, 810, 1210, 10);
        verticalRight.draw();
        Line header = new Line(10, 100, 1210, 100);
        header.draw();
        Line subHeader = new Line(10, 150, 1210, 150);
        subHeader.draw();

        for(int i = 150; i <= 810; i += 20){
            Line segmented = new Line(10, i,1210, i );
            segmented.draw();
        }

        for (int i = 10; i <= 1210; i+= 240 ){
            Line verticalSegment = new Line (i, 10, i, 810);
            verticalSegment.draw();
   }
 }
}


