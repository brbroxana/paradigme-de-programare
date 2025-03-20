import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
//Clasa calculator extinde JFrame pentru a crera o fereastra grafica
public class Calculator extends JFrame {
    //Declararea butoanelor pentru cifrele de la 0 la 9
    JButton digits[] = {
            new JButton(" 0 "),
            new JButton(" 1 "),
            new JButton(" 2 "),
            new JButton(" 3 "),
            new JButton(" 4 "),
            new JButton(" 5 "),
            new JButton(" 6 "),
            new JButton(" 7 "),
            new JButton(" 8 "),
            new JButton(" 9 ")
    };
//Declararea butoanelor pentru operatori
    JButton operators[] = {
            new JButton(" + "),
            new JButton(" - "),
            new JButton(" * "),
            new JButton(" / "),
            new JButton(" ( "),
            new JButton(" ) "),
            new JButton(" = "),
            new JButton(" C ")
    };
//Valorile asociate operatiilor
    String oper_values[] = {"+", "-", "*", "/", "(", ")", "=", ""};

    String value;
    char operator;
//Zona de text unde se afiseaza expresia si rezultatul
    JTextArea area = new JTextArea(3, 20);
//Metoda principala pentru a porni aplicatia
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setSize(230, 300); //Seteaza dimensiunea ferestrei
        calculator.setTitle(" Java-Calc, PP Lab1 "); //Seteaza titlul ferestrei
        calculator.setResizable(false); //Nu permite redimensionarea
        calculator.setVisible(true); //face fereastra vizibila
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Inchide aplicatia la apasarea X
    }
//Constructorul clasei Calculator
    public Calculator() {
        //Adauga zone de text in partea de sus a ferestrei
        add(new JScrollPane(area), BorderLayout.NORTH);
        //Creeaza un panou pentru butoane
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());
//adauga butoanele cu cifre in panou
        for (int i=0;i<10;i++)
            buttonpanel.add(digits[i]);
//adauga butoanele cu operatori in panou
        for (int i=0;i<8;i++)
            buttonpanel.add(operators[i]);
//adauga panoul de butoane in centru ferestrei
        add(buttonpanel, BorderLayout.CENTER);
        //Seteaza stilul zonei de text
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);//face zona de text doar pentru citire
//Adauga functionalitatea butoanelor pentru cifre
        for (int i=0;i<10;i++) {
            int finalI = i;
            digits[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    area.append(Integer.toString(finalI));
                }//Adauga cifra apasata in zona de text
            });
        }
//Adauga functionalitatea butoanelor pentru operatori
        for (int i=0;i<8;i++){
            int finalI = i;
            operators[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    //Daca se apasa tasta "C", se sterge continutul zonei de text
                    if (finalI == 7)
                        area.setText("");
                    else
                    if (finalI == 6) {//Daca se apasa "=", se evalueaza expresia
                        try {
                            area.append("=" + EvaluateString.evaluate(area.getText()));
                        } catch (Exception e) {
                            area.setText(" !!!Probleme!!! ");
                        }
                    }
                    else {
                        area.append(oper_values[finalI]);//Adauga operatorul in zona text
                        operator = oper_values[finalI].charAt(0);
                    }
                }
            });
        }
    }
}