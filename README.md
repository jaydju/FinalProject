# myTC Scheduler
Problem: The current view of the schedule on myTCportal is not user-friendly and difficult to visualize the relationship between your classes and availabilities throughout the week. 

Project Descritpion: The idea is to write a program that can create a more graphical view of the schedule for students at TC achieved by simply copying and pasting their schedule from the myTCPortal website to our program. 

An alternative approach is to create multiple questions which prompts the user for input regarding their class schedule for a single week; however, this is cumbersome for the user and does not automate the process as much as we would like. Having the user simply copy and paste the schedule, while more challenging, gives a far better user experience. The most relevant information a user needs is the class name,class code, class time, class location, and the professor of that class. We aim to provide all this information within every instance of this class (every Java pun in this paragraph is unintended).
A better approach for further improved user experience could be utilizing a web-scraper to collect relevant data based on the user provided link:
https://my.tc.columbia.edu/web/home-community/student-detail-schedule
But this link does not redirect us immediately to the schedule itself as it prompts as to specify a term in a drop-down menu. Additionally, the link when not opened on the same browser or opened through a third-party program will probably require login credentials that the user must provide. This makes for a poorer user experience than all the other options - it also primarily, and unfortunately, beyond the scope of our abilities.
