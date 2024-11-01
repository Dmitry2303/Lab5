import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame {
    private JTextField display;
    private double firstNumber;
    private String operation;

    public SimpleCalculator() {
        setTitle("Калькулятор");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                ".", "", "", ""
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C":
                    display.setText("");
                    break;
                case "=":
                    if (!display.getText().isEmpty() && operation != null) {
                        double secondNumber = Double.parseDouble(display.getText());
                        double result = performCalculation(firstNumber, secondNumber, operation);
                        display.setText(String.valueOf(result));
                        operation = null; // Сброс операции
                    }
                    break;
                case "/":
                case "*":
                case "-":
                case "+":
                    if (!display.getText().isEmpty()) {
                        firstNumber = Double.parseDouble(display.getText());
                        operation = command;
                        display.setText(""); // Очистка дисплея для второго числа
                    }
                    break;
                default:
                    display.setText(display.getText() + command);
                    break;
            }
        }
    }

    private double performCalculation(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "Ошибка: Деление на ноль!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
                return num1 / num2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}