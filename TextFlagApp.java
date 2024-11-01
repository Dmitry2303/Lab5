import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TextFlagApp extends JFrame {
    private JCheckBox[] colorCheckBoxes;
    private JButton drawButton;
    private String[] colors = {"Белый", "Желтый", "Синий","Зеленый", "Красный"};
    private ArrayList<String> selectedColors;

    public TextFlagApp() {
        setTitle("Текстовый флаг");
        setSize(300, 300);
        setResizable(false); // Запрет на изменение размера окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(colors.length + 1, 1));

        // Создание массива чекбоксов для выбора цвета
        colorCheckBoxes = new JCheckBox[colors.length];

        for (int i = 0; i < colors.length; i++) {
            colorCheckBoxes[i] = new JCheckBox(colors[i]);
            add(colorCheckBoxes[i]);
        }

        // Кнопка для рисования
        drawButton = new JButton("Нарисовать");
        drawButton.addActionListener(new DrawButtonListener());
        add(drawButton);

        selectedColors = new ArrayList<>(); // Инициализация списка выбранных цветов
    }

    private class DrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder message = new StringBuilder();
            selectedColors.clear(); // Очищаем список при каждом новом нажатии на кнопку

            // Собирать выбранные цвета в порядке выбора
            for (JCheckBox checkBox : colorCheckBoxes) {
                // Если чекбокс был нажат, добавляем его в список
                if (checkBox.isSelected()) {
                    selectedColors.add(checkBox.getText());
                }
            }

            // Формируем сообщение
            if (!selectedColors.isEmpty()) {
                message.append("Выбранные цвета: ");
                for (String color : selectedColors) {
                    message.append(color).append(", ");
                }
                // Удаляем последнюю запятую и пробел
                message.setLength(message.length() - 2);
            } else {
                message.append("Нет выбранных цветов.");
            }

            // Отображаем сообщение в диалоговом окне
            JOptionPane.showMessageDialog(TextFlagApp.this, message.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextFlagApp app = new TextFlagApp();
            app.setVisible(true);
        });
    }
}