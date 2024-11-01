import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantOrderApp extends JFrame {
    private JCheckBox dish1CheckBox;
    private JCheckBox dish2CheckBox;
    private JCheckBox dish3CheckBox;
    private JTextField dish1Quantity;
    private JTextField dish2Quantity;
    private JTextField dish3Quantity;
    private JButton orderButton;

    private final String[] dishNames = {"Паста", "Пицца", "Салат"};
    private final double[] dishPrices = {200.0, 350.0, 150.0};

    public RestaurantOrderApp() {
        setTitle("Заказ в ресторане");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 3));

        dish1CheckBox = new JCheckBox(dishNames[0] + " - " + dishPrices[0] + " руб.");
        dish1Quantity = new JTextField("1");
        dish1Quantity.setEnabled(false);

        dish2CheckBox = new JCheckBox(dishNames[1] + " - " + dishPrices[1] + " руб.");
        dish2Quantity = new JTextField("1");
        dish2Quantity.setEnabled(false);

        dish3CheckBox = new JCheckBox(dishNames[2] + " - " + dishPrices[2] + " руб.");
        dish3Quantity = new JTextField("1");
        dish3Quantity.setEnabled(false);

        // Добавляем слушателей для чекбоксов
        dish1CheckBox.addActionListener(e -> dish1Quantity.setEnabled(dish1CheckBox.isSelected()));
        dish2CheckBox.addActionListener(e -> dish2Quantity.setEnabled(dish2CheckBox.isSelected()));
        dish3CheckBox.addActionListener(e -> dish3Quantity.setEnabled(dish3CheckBox.isSelected()));

        orderButton = new JButton("Оформить заказ");
        orderButton.addActionListener(new OrderButtonListener());

        // Добавляем элементы на форму
        add(dish1CheckBox);
        add(dish1Quantity);
        add(new JLabel()); // пустая ячейка

        add(dish2CheckBox);
        add(dish2Quantity);
        add(new JLabel()); // пустая ячейка

        add(dish3CheckBox);
        add(dish3Quantity);
        add(new JLabel()); // пустая ячейка

        add(orderButton);
    }

    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double totalCost = 0.0; // Общая стоимость
            StringBuilder receipt = new StringBuilder("Чек:\n");

            totalCost += checkAndAddToReceipt(dish1CheckBox, dish1Quantity, 0, receipt);
            totalCost += checkAndAddToReceipt(dish2CheckBox, dish2Quantity, 1, receipt);
            totalCost += checkAndAddToReceipt(dish3CheckBox, dish3Quantity, 2, receipt);

            receipt.append(String.format("Итоговая стоимость заказа: %.2f руб.", totalCost));

            // Показать чек в модальном окне
            JOptionPane.showMessageDialog(null, receipt.toString(), "Чек", JOptionPane.INFORMATION_MESSAGE);
        }

        private double checkAndAddToReceipt(JCheckBox checkBox, JTextField quantityField, int dishIndex, StringBuilder receipt) {
            double cost = 0.0; // Стоимость конкретного блюда
            if (checkBox.isSelected()) {
                int quantity = Integer.parseInt(quantityField.getText());
                cost = quantity * dishPrices[dishIndex];
                receipt.append(String.format("%s x%d: %.2f руб.\n", dishNames[dishIndex], quantity, cost));
            }
            return cost; // Возвращаем стоимость блюда для суммирования
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantOrderApp app = new RestaurantOrderApp();
            app.setVisible(true);
        });
    }
}