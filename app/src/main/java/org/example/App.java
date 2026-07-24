package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator = ' ';

    public App() {
        setTitle("Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "%", "√", "DEL"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]") || cmd.equals(".")) {
            display.setText(display.getText() + cmd);
        }
        else if (cmd.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = ' ';
        }
        else if (cmd.equals("DEL")) {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }
        else if (cmd.equals("√")) {
            try {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sqrt(value)));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        else if (cmd.equals("%")) {
            try {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(value / 100));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        else if (cmd.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = cmd.charAt(0);
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            display.setText("Cannot divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        display.setText("Error");
                        return;
                }

                if (result == (long) result)
                    display.setText(String.valueOf((long) result));
                else
                    display.setText(String.valueOf(result));

            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
