import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {
    private JLabel window1;
    private JTextField item1;
    private JTextField item2;
    private JTextField item3;
    private JPasswordField pass1;

    public Window(){
        super("The Title Bar");
        setLayout(new FlowLayout());

        item1 = new JTextField(10);
        item2 = new JTextField("Enter Text Here");
        item3 = new JTextField("Uneditable", 34);
        pass1 = new JPasswordField("mypassword");

        add(item1);
        add(item2);
        add(item3);
        add(pass1);

        window1 = new JLabel("Hello World!");
        window1.setToolTipText("This will appear on hover");
        add(window1);
    }


}
