import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Display {
    int maxWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int maxHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    File teacherFile;
    File quizFile;
    File answerFile;
    String correctAnswer;
    boolean pause;

    JFrame frame;
    Display(){
        frame = new JFrame("QuizSmart");
        frame.setSize(maxWidth, maxHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void start() throws InterruptedException {
        JFrame loadingFrame = new JFrame();
        JLabel titleImage = new JLabel(new ImageIcon("Title.png"));
        loadingFrame.setUndecorated(true);
        loadingFrame.add(titleImage);
        loadingFrame.setLocation(maxWidth/2 - maxWidth/8, maxHeight/2 - maxHeight/10);
        loadingFrame.setSize(maxWidth/4,maxHeight/5);
        loadingFrame.setVisible(true);
        Thread.sleep(5000);
        //loadingFrame.setVisible(false);
        loadingFrame.dispose();
        Thread.sleep(2000);
        //Start-up screen
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridLayout());
        //startPanel.setLayout(new BorderLayout());
        frame.add(startPanel);
        JButton teacherButton = new JButton("Teacher");
        teacherButton.addActionListener(new TeacherActionListener());
        JButton studentButton = new JButton("Student");
        studentButton.addActionListener(new StudentActionListener());
        startPanel.add(teacherButton);
        startPanel.add(studentButton);
        frame.setVisible(true);
    }

    public class TeacherActionListener implements ActionListener {
        /**JTextField quizTextField;
        JTextField answerTextField;
        JButton quizButton;
        JButton answerButton;*/
        String quizFileName;
        String answerFileName;/**
        JPanel teacherPanel;
        
        TeacherActionListener() {
            quizTextField = new JTextField();
            answerTextField = new JTextField();
            quizButton = new JButton("Submit quiz");
            answerButton = new JButton("Submit answers");
            quizFileName = null;
            answerFileName = null;
            teacherPanel = new JPanel();
        }*/
        
        @Override
        public void actionPerformed(ActionEvent e) {
            //Teacher portion of app code here
            quizFileName = null;
            answerFileName = null;
            JFrame teacherFrame = new JFrame();
            teacherFrame.setSize(maxWidth/3, maxHeight/3);
            teacherFrame.setVisible(true);
            JPanel teacherPanel = new JPanel();
            teacherPanel.setLayout(new GridLayout());
            JTextField quizTextField = new JTextField("quizFile name");
            JTextField answerTextField = new JTextField("answerFile name");
            JButton teacherButton = new JButton("Submit");
            //JButton answerButton = new JButton("Submit answer");
            teacherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    quizFile = new File(quizTextField.getText());
                    answerFile = new File (answerTextField.getText());

                        Converter converter = new Converter();
                    try {
                        converter.fileToQuiz(quizFile, answerFile);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    teacherFrame.setVisible(false);
                    teacherFrame.dispose();
                }
            });
            teacherPanel.add(quizTextField);
            teacherPanel.add(answerTextField);
            teacherPanel.add(teacherButton);
            teacherFrame.add(teacherPanel);
        }
    }

    public class StudentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Student portion of app code here
            JFrame studentFrame = new JFrame();
            studentFrame.setSize(maxWidth/3, maxHeight/3);
            studentFrame.setVisible(true);
            JPanel studentPanel = new JPanel();
            studentPanel.setLayout(new GridLayout());
            JTextField studentTextField = new JTextField("Teacher-sent File");
            JButton studentButton = new JButton("Submit File");
            studentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    teacherFile = new File(studentTextField.getText());
                    studentFrame.setVisible(false);
                    frame.setVisible(false);
                    try {
                        Interactive interactive = new Interactive(teacherFile);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            studentPanel.add(studentTextField);
            studentPanel.add(studentButton);
            studentFrame.add(studentPanel);
            studentFrame.setVisible(true);
        }

        public void startInteractive() throws FileNotFoundException {
            Scanner read = new Scanner(teacherFile);
            JFrame kahoot = new JFrame();
            kahoot.setLayout(new BorderLayout());
            kahoot.setSize(500,300);
            kahoot.setVisible(true);
            while (read.hasNextLine()){
                pause = true;
                String[] questionElements = read.nextLine().split("^");
                kahoot.removeAll();
                JPanel questionPanel = new JPanel();
                JLabel question = new JLabel(questionElements[0]);
                questionPanel.add(question);
                kahoot.add(questionPanel, BorderLayout.NORTH);
                JPanel currentPanel = new JPanel();
                kahoot.add(currentPanel, BorderLayout.SOUTH);

                for (int i = 1; i < questionElements.length; i++) {
                    String currentAnswer = questionElements[i];
                    if (currentAnswer.charAt(0) == '*') {
                        currentAnswer = currentAnswer.substring(1);
                        correctAnswer = currentAnswer;
                    }
                    JButton currentButton = new JButton(currentAnswer);
                    currentButton.addActionListener(new KahootActionListener());
                    currentPanel.add(currentButton);
                }
                while (pause) {

                }
            }
        }

        class KahootActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == correctAnswer) {
                    ((JButton)e.getSource()).setForeground(Color.GREEN);
                } else {
                    ((JButton)e.getSource()).setForeground(Color.RED);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                pause = false;
            }
        }
    }
}
