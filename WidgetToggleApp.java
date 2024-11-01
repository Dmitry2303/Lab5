import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WidgetToggleApp extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;

    public WidgetToggleApp() {
        setTitle("Тогглер Виджетов");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        textField1 = new JTextField(15);
        textField2 = new JTextField(15);
        textField3 = new JTextField(15);

        checkBox1 = new JCheckBox("Показать 1-й текстовое поле");
        checkBox2 = new JCheckBox("Показать 2-й текстовое поле");
        checkBox3 = new JCheckBox("Показать 3-й текстовое поле");

        // Добавляем обработчики действий для чекбоксов
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setVisible(checkBox1.isSelected());
            }
        });

        checkBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setVisible(checkBox2.isSelected());
            }
        });

        checkBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setVisible(checkBox3.isSelected());
            }
        });

        // Расположим виджеты и чекбоксы
        add(checkBox1);
        add(textField1);
        add(checkBox2);
        add(textField2);
        add(checkBox3);
        add(textField3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WidgetToggleApp app = new WidgetToggleApp();
            app.setVisible(true);
        });
    }
}