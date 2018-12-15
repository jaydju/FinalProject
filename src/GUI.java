import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GUI {
    private JFrame f;
    private JPanel p;
    private JButton b1;
    private JLabel lab;
    private JTextArea tf;

    private static final String FileName = "schedule.txt";

    public void show()
    {
        f = new JFrame("myTC SChedule Visualizer");
        f.setVisible(true);
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p = new JPanel();
        //p.setPreferredSize(new Dimension(200, 400));

        b1 = new JButton("Run");

        p.add(b1);

        f.add(p);
        f.add(p, BorderLayout.SOUTH); //This shows the panel at the bottom of the frame

        tf = new JTextArea();
        tf.setPreferredSize(new Dimension(300, 200));

        p.add(tf);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tf.getText();
                try {
                    FileWriter fw = new FileWriter(FileName);
                    fw.write(text);
                    fw.close();
                    parseInfo();
                }
                catch(Exception ex)
                {

                }
            }
        });
    }

    private void parseInfo()
    {
        try { //This try catches non .txt file formats
            //We Initialize the File Name, remember to ask for user input and print line by line to a text file.
            Scanner in = new Scanner(new FileReader(FileName));

            //Initialize the student class which holds an array of list of Classes and their Information
            Student s1 = new Student();

            //Method to Find Class Term and NAme
            String classTerm = Classes.findTerm(in);
            s1.setClassTerm(classTerm);

            //Method to Find Name
            Scanner name = new Scanner(new FileReader(FileName));
            String studentName = Student.findName(name);

            //Method to Find All Class Names
            Scanner it = new Scanner(new FileReader(FileName));
            ArrayList<String> allClassNames;
            allClassNames = Classes.findAllClassNames(it);

            //Finds Class Details but only UP To Two Lines
            Scanner s = new Scanner(new FileReader(FileName));
            ArrayList<String> classDetailFirst = new ArrayList<>();
            ArrayList<String> classDetailSecond = new ArrayList<>();
            Classes.fincClassDetails(s, classDetailFirst, classDetailSecond);


//                while (s.hasNextLine()) {
//                    String line = s.next();
//                    if (line.equals("Type")) {
//                        s.nextLine();
//                        String firstDetails = s.nextLine();
//                        classDetailFirst.add(firstDetails);
//                        String secondDetails = s.nextLine();
//                        classDetailSecond.add(secondDetails);
//                    }
//                }


            //Checking if Class Details is Complete (Conditioned On Only First Line Containing TBA
            // If TBA is in the First Line, it is removed from the Array. The Second Line index of that same class is also removed.
            ArrayList<String> incompleteClass = new ArrayList<>(); //The incompleteClass Array List saves the incomplete line information to be printed out to the user later.
            Classes.removeInstanceTBA(incompleteClass, classDetailFirst, classDetailSecond, allClassNames);

//                for (int j = 0; j < classDetailFirst.size(); j++) {
//                    String thisClassDetail = classDetailFirst.get(j);
//                    if (thisClassDetail.toLowerCase().contains("TBA".toLowerCase())) {
//                        incompleteClass.add(allClassNames.get(j) + classDetailFirst.get(j));
//                        classDetailFirst.remove(j);
//                        classDetailSecond.remove(j);
//                        allClassNames.remove(j);
//                    }
//                }


            //Handling Instances of Seminars if the Time for Both Seminars is the Same Replace
            ArrayList<String> varyingClassDetails = new ArrayList<>();
            Classes.seminarAndLaboratoryHandler(varyingClassDetails, classDetailFirst, classDetailSecond, allClassNames);
//                for (int j = 0; j < classDetailFirst.size(); j++) {
//                    ArrayList<String> seminarTokens1 = new ArrayList<>();
//                    ArrayList<String> seminarTokens2 = new ArrayList<>();
//                    String thisClassDetail = classDetailFirst.get(j);
//                    if (thisClassDetail.toLowerCase().contains("Seminar".toLowerCase()) || thisClassDetail.toLowerCase().contains("Laboratory".toLowerCase())) {
//                        String[] words = thisClassDetail.split("\\s+");
//                        for (String word : words) {
//                            seminarTokens1.add(word);
//                        }
//                        String[] words2 = classDetailSecond.get(j).split("\\s+");
//                        for (String word : words2) {
//                            seminarTokens2.add(word);
//                        }
//                        //********** NEED TO RECODE THIS TO MORE GENERAL/ROBUST CASE. IF THE DAYS ARE THE SAME AND THE TIME OVERLAPS, THEN REMOVE IT.
//                        String day1 = seminarTokens1.get(6);
//                        String day2 = seminarTokens2.get(6);
//                        String time1 = seminarTokens1.get(1) + seminarTokens1.get(2) + seminarTokens1.get(3) + seminarTokens1.get(4) + seminarTokens1.get(5);
//                        String time2 = seminarTokens2.get(1) + seminarTokens2.get(2) + seminarTokens2.get(3) + seminarTokens2.get(4) + seminarTokens2.get(5);
//                        if (time1.equals(time2) || day1.equals(day2)) {
//                            varyingClassDetails.add(allClassNames.get(j) + classDetailSecond.get(j));
//                            classDetailSecond.set(j, " ");
//                        }
//                    }
//                }

            //We iterate through the Array Containing the Lines of Class Details for the FIRST ARRAY
            for (int i = 0; i < classDetailFirst.size(); i++) {
                //We create another Array List to Save the Tokens
                ArrayList<String> classDetailTokens1 = new ArrayList<>();
                String firstClassDetail = classDetailFirst.get(i);
                String[] words = firstClassDetail.split("\\s+");
                //We Add the Tokenized Details of Each Class Detail Line into the Token Array list
                for (String word : words) {
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

            //We iterate through the Array Containing the Lines of Class Details for the SECOND ARRAY
            for (int i = 0; i < classDetailSecond.size(); i++) {
                //We create another Array List to Save the Tokens
                ArrayList<String> classDetailTokens2 = new ArrayList<>();
                String secondClassDetail = classDetailSecond.get(i);
                if (!(secondClassDetail.equals(" ") || secondClassDetail.equals(""))) {
                    String validClassDetails = classDetailSecond.get(i);
                    String[] words = validClassDetails.split("\\s+");
                    for (String word : words) {
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
                    for (int j = 8; j < classDetailTokens2.size(); j++) {
                        //We check to see if the Token refers to the Date which is identified by "/"
                        if (classDetailTokens2.get(j).contains("/")) {
                            break;
                        } else {
                            location += " " + classDetailTokens2.get(j);
                        }


                    }
                    c1.setClassLoc(location);

                    //Class Professor
                    for (int j = 0; j < classDetailTokens2.size(); j++) {
                        String token = classDetailTokens2.get(j);
                        //We check if the Tokens equals Lecture/Seminar/Laboratory etc; we know the Token After This is the Start of the Prof Name
                        if (token.equals("Lecture") || token.equals("Seminar") || token.equals("Laboratory")) {
                            String professorName = "";
                            for (int h = j + 1; h < classDetailTokens2.size(); h++) {
                                if (classDetailTokens2.get(h).equals(("(P)E-mail")) || classDetailTokens2.get(h).equals("E-mail")) {
                                    break;
                                } else {
                                    professorName = professorName + " " + classDetailTokens2.get(h);
                                }
                            }
                            professorName = professorName.substring(1);
                            c1.setClassProf(professorName);
                        }
                    }

                    s1.addClass(c1);
                }
            }


            //        //         CREATING LINE SEGMENTS ACTUAL DRAWING HERE
            Line horizontalTop = new Line(10, 10, 1280, 10);
            horizontalTop.draw();
            Line subHeaderTop = new Line(10, 60, 1280, 60);
            subHeaderTop.draw();
            Line verticalLeft = new Line(10, 10, 10, 850);
            verticalLeft.draw();
            Line horizontalBot = new Line(10, 810, 1280, 810);
            horizontalBot.draw();
            Line verticalRight = new Line(1280, 850, 1280, 10);
            verticalRight.draw();
            Line verticalTime = new Line(80, 10, 80, 850);
            verticalTime.draw();

            //If Student Name is Empty, we simply output the term and change the spacing
            if (studentName.equals("")) {
                Text header = new Text(625, 30, classTerm + " Schedule");
                header.draw();
                header.setColor(new Color(124, 10, 2));
                header.grow(90, 10);
            } else {
                Text header = new Text(550, 30, studentName + "'s " + classTerm + " Schedule");
                header.draw();
                header.setColor(new Color(124, 10, 2));
                header.grow(130, 15);
            }

            int hour = 9;
            int minute = 0;
            String meridian = "AM";
            for (int i = 90; i <= 850; i += 20) {
                Line segmented = new Line(10, i, 1280, i);
                segmented.draw();
                String displayTime = hour + ":" + minute + "0 " + meridian;
                Text time = new Text(15, i, displayTime);
                if (i < 850) {
                    time.draw();
                } else {
                    continue;
                }
                time.draw();
                minute += 2;
                if (minute >= 6) {
                    minute = 0;
                    hour++;
                }
                if (hour > 12) {
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
            for (int i = 80; i <= 1280; i += 240) {
                Line verticalSegment = new Line(i, 60, i, 850);
                verticalSegment.draw();

                if (i < 1280) {
                    String curDay = daysOfWeek.get(indexOfDay);
                    Text days = new Text(i + 95, 65, curDay);
                    days.grow(35, 8);
                    days.draw();
                    indexOfDay++;
                }
            }

            //Iterating through the Students Classes:
            int boxX = 0;
            int boxY = 0;
            int boxHeight = 0;
            for (int i = 0; i < s1.getClasses().size(); i++) {
                Classes c1 = s1.getClasses().get(i);
                int classSize = c1.getClassDays().length();

                for (int j = 0; j < classSize; j++) {
                    String firstClassDay = c1.getClassDays().substring(j, j + 1);
                    if (firstClassDay.equals("M")) {
                        boxX = 80;
                    } else if (firstClassDay.equals("T")) {
                        boxX = 320;
                    } else if (firstClassDay.equals("W")) {
                        boxX = 560;
                    } else if (firstClassDay.equals("R")) {
                        boxX = 800;
                    } else {
                        boxX = 0;
                        System.out.println("Can't find class day");
                    }

                    //Excess String Traversals

                    ArrayList<String> tokenTime = new ArrayList<>();
                    String[] classTime = c1.getClassTime().split("[\\s-:]+");
                    for (String words : classTime) {
                        tokenTime.add(words);
                    }
                    int firstTime = Integer.parseInt(tokenTime.get(0));
                    String secondDetail = tokenTime.get(1);
                    if (secondDetail.toLowerCase().contains("pm") && firstTime != 12) {
                        firstTime += 12;
                    }
//                boxY = firstTime; //Algorithm To Relate the Coordinates to the Time
                    //Consider Switch Cases:
                    if (firstTime == 9) {
                        boxY = 90;
                    } else if (firstTime == 10) {
                        boxY = 150;
                    } else if (firstTime == 11) {
                        boxY = 210;
                    } else if (firstTime == 12) {
                        boxY = 270;
                    } else if (firstTime == 13) {
                        boxY = 330;
                    } else if (firstTime == 14) {
                        boxY = 390;
                    } else if (firstTime == 15) {
                        boxY = 450;
                    } else if (firstTime == 16) {
                        boxY = 510;
                    } else if (firstTime == 17) {
                        boxY = 570;
                    } else if (firstTime == 18) {
                        boxY = 630;
                    } else if (firstTime == 19) {
                        boxY = 690;
                    } else if (firstTime == 20) {
                        boxY = 750;
                    } else {
                        boxY = 810;
                    }

                    //difference between hour is 60 units
                    int minutes = Integer.parseInt(secondDetail.substring(0, secondDetail.length() - 2));
                    boxY += minutes;


                    //Find Height here to determine size of the box
                    int secondTime = Integer.parseInt(tokenTime.get(2));
                    String fourthDetail = tokenTime.get(3);
                    if (fourthDetail.toLowerCase().contains("pm") && secondTime != 12) {
                        secondTime += 12;
                    }

                    if (secondTime == 9) {
                        boxHeight = 110;
                    } else if (secondTime == 10) {
                        boxHeight = 170;
                    } else if (secondTime == 11) {
                        boxHeight = 230;
                    } else if (secondTime == 12) {
                        boxHeight = 290;
                    } else if (secondTime == 13) {
                        boxHeight = 350;
                    } else if (secondTime == 14) {
                        boxHeight = 410;
                    } else if (secondTime == 15) {
                        boxHeight = 470;
                    } else if (secondTime == 16) {
                        boxHeight = 530;
                    } else if (secondTime == 17) {
                        boxHeight = 590;
                    } else if (secondTime == 18) {
                        boxHeight = 650;
                    } else if (secondTime == 19) {
                        boxHeight = 710;
                    } else if (secondTime == 20) {
                        boxHeight = 770;
                    } else {
                        boxHeight = 830;
                    }

                    //Add Second Minutes
                    int minutes2 = Integer.parseInt(fourthDetail.substring(0, fourthDetail.length() - 2));
                    boxHeight += minutes2;

                    boxHeight = boxHeight - boxY;

                    //Need to adjust the positive Y here.
                    //THe length of each vertical segment is 840 long we need to parse by time between 9am - 920pm.

//                Rectangle classBox = new Rectangle(boxX, boxY, 240, boxHeight);
//                double indivBoxHeight = boxHeight / 3f;

                    Rectangle firstBox = new Rectangle(boxX, boxY, 240, boxHeight / 3f);
                    firstBox.setColor(new Color(17, 30, 108));
                    firstBox.fill();
                    Rectangle secondBox = new Rectangle(boxX, boxY + (boxHeight / 3f), 240, boxHeight / 3f);
                    secondBox.setColor(new Color(255, 221, 175));
                    secondBox.fill();
                    Rectangle thirdBox = new Rectangle(boxX, boxY + (2 * boxHeight / 3f), 240, boxHeight / 3f);
                    thirdBox.setColor(new Color(199, 234, 70));
                    thirdBox.fill();

                    Text className = new Text(boxX, boxY, c1.getClassName() + ": " + c1.getClassProf());
                    className.setColor(new Color(255, 255, 255));
                    className.draw();
                    className.translate(20, 8);
                    className.grow(5, 5);
                    Text classLoc = new Text(boxX, boxY + boxHeight / 3f, c1.getClassLoc());
                    classLoc.draw();
                    classLoc.translate(20, 8);
                    classLoc.grow(5, 5);
                    Text classTim = new Text(boxX, boxY + 2 * boxHeight / 3f, c1.getClassTime());
                    classTim.draw();
                    classTim.translate(20, 8);
                    classTim.grow(5, 5);

                }


            }
            //Printing out Incomplete Class Details
            Text missingMessage = new Text(1300, 40, "Missing Information: Could't Process Following");
            missingMessage.draw();

            System.out.println(Arrays.toString(incompleteClass.toArray()));
            System.out.println(Arrays.toString(varyingClassDetails.toArray()));
        } catch (Exception e) {
            System.out.println("Please copy directly to .txt file format");

        }
    }


}
