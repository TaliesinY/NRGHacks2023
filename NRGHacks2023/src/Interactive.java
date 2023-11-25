import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interactive {
    File teacherFile;
    Interactive(File teacherFile) throws FileNotFoundException {
        int score = 0;
        this.teacherFile = teacherFile;
        Scanner read = new Scanner(teacherFile);
//        JFrame kahoot = new JFrame();
//        kahoot.setLayout(new BorderLayout());
//        kahoot.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        while (read.hasNextLine()) {
//                kahoot.removeAll();
//                kahoot.setVisible(true);
//                kahoot.revalidate();
//                JPanel kahootPanel = new JPanel();
//                kahootPanel.setLayout(new GridLayout());
                String[] questions = read.nextLine().split("\\^");
//                kahootPanel.add(new JLabel(questions[0] + ""));
                System.out.println(questions[0]);
                for (int i = 1; i < questions.length - 1; i++) {
                    JButton optionButton = new JButton(questions[i] + "");
                    System.out.println(questions[i]);
//                    kahootPanel.add(optionButton);


                }
            Scanner terminalInput = new Scanner(System.in);
                String answer = terminalInput.nextLine();
            if (("*" + answer).equals(questions[questions.length - 1])) {
                System.out.println("Right!");
                score++;
            } else {
                System.out.println("Wrong");
            }
//                kahoot.add(kahootPanel);
            }
        System.out.println("final score:" + score);
        }
    }
