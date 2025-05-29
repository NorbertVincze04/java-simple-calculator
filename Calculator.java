package Lab11.Pb1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    JTextField textField = new JTextField();;
    String currentText = "";
    JPanel buttonPanel = new JPanel();

    public Calculator() {
        super("Calculator");

        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setFont(new Font("Arial", Font.BOLD, 24));

        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String label : buttons) {
            JButton button = new JButton(label);
            if (label.matches("[/*\\-+=]")) {
                button.setBackground(Color.ORANGE);
                button.setForeground(Color.WHITE);
            } else if (label.equals("C")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.LIGHT_GRAY);
            }
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout(5, 5));
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            currentText += command;
            textField.setText(currentText);
        } else if (command.matches("[/*\\-+]")) {
            currentText += " " + command + " ";
            textField.setText(currentText);
        } else if (command.equals("C")) {
            currentText = "";
            textField.setText("");
        } else if (command.equals("=")) {
            try {
                String[] tokens = currentText.split(" ");
                if (tokens.length == 3) {
                    double op1 = Double.parseDouble(tokens[0]);
                    double op2 = Double.parseDouble(tokens[2]);
                    String op = tokens[1];
                    double result = switch (op) {
                        case "+" -> op1 + op2;
                        case "-" -> op1 - op2;
                        case "*" -> op1 * op2;
                        case "/" -> op1 / op2;
                        default -> 0;
                    };
                    textField.setText(String.valueOf(result));
                    currentText = String.valueOf(result);
                }
            } catch (Exception ex) {
                textField.setText("Error");
                currentText = "";
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}