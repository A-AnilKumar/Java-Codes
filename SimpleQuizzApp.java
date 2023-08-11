
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class SimpleQuizzApp extends JFrame implements ActionListener {

    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5];
    JButton Next, Result;
    ButtonGroup btnGrp;

    int count = 0, current = 0, totalQues = 4;

    SimpleQuizzApp(String str) {
        super(str);

        label = new JLabel();
        add(label);
        btnGrp = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radioButton[i] = new JRadioButton();
            add(radioButton[i]);
            btnGrp.add(radioButton[i]);
        }

        Next = new JButton("Next");
        Result = new JButton("RESULT");
        Result.setVisible(false);
        Next.addActionListener((ActionListener) this);
        Result.addActionListener((ActionListener) this);

        add(Next);
        add(Result);

        displayQuestions();

        label.setBounds(30, 40, 450, 20);

        radioButton[0].setBounds(50, 80, 450, 20);
        radioButton[1].setBounds(50, 110, 200, 20);
        radioButton[2].setBounds(50, 140, 200, 20);
        radioButton[3].setBounds(50, 170, 200, 20);

        Next.setBounds(100, 240, 100, 30);
        Result.setBounds(270, 240, 100, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Next) {
            if (checkAns())
                count += 1;
            current++;
            displayQuestions();

            if (current == totalQues-1) {
                Next.setEnabled(false);
                Result.setVisible(true);
                Result.setText("RESULT");
            }

        }

        if (e.getActionCommand().equals("RESULT")) {
            if (checkAns())
                count += 1;
            current++;
            JOptionPane.showMessageDialog(this, "Scored " + count + " out of " + totalQues);
            System.exit(0);
        }
    }


    private void displayQuestions() {

        radioButton[4].setSelected(true);
        switch (current) {

            case 0:
                label.setText("Q1. What is a correct syntax to output \"Hello World\" in Java?");
                radioButton[0].setText("echo(\"hello World\")");
                radioButton[1].setText("printf(\" hello wolrd \");");
                radioButton[2].setText("print(\"hello world\" )");
                radioButton[3].setText("System.out.print(\" Hello wolrd\");");
                break;
            case 1:
                label.setText("Q2. Comments in java ?");
                radioButton[0].setText("//");
                radioButton[1].setText("#");
                radioButton[2].setText("/*");
                radioButton[3].setText("=begin // stmts =cut");
                break;
            case 2:
                label.setText("Q3. Datatype to store names or texts ?");
                radioButton[0].setText("str");
                radioButton[1].setText("char");
                radioButton[2].setText("String");
                radioButton[3].setText("string");
                break;
            case 3:
                label.setText("Q4. Correct form Floating point number in java ?");
                radioButton[0].setText("Float f = 12.8;");
                radioButton[1].setText("Float f =12.8f;");
                radioButton[2].setText("float f =12.8;");
                radioButton[3].setText("float f = 12.8f;");
                break;
   
   
   
   
   
        }
        label.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 30; i += 30, j++) {
            radioButton[j].setBounds(50, 80 + i, 200, 20);
        }

    }

    private boolean checkAns() {
        switch (current) {
            case 0:
                return (radioButton[3].isSelected());

            case 1:
                return (radioButton[0].isSelected());

            case 2:
                return (radioButton[2].isSelected());

            case 3:
                return (radioButton[3].isSelected());
        }
        return false;
    }

    public static void main(String[] args) {
        new SimpleQuizzApp("QUIZZ APP");
    }
}