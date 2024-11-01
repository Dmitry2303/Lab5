package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Word_switcher extends JFrame {
    private JTextField inputField1;
    private JTextField inputField2;
    private JButton toggleButton;
    private boolean isField1ToField2 = true; // Начальное состояние: поле 1 в поле 2

    public Word_switcher() {
        setTitle("Перекидыватель слов");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        inputField1 = new JTextField(20);
        inputField2 = new JTextField(20);
        inputField2.setEditable(false);

        toggleButton = new JButton("➡️");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isField1ToField2) {
                    inputField2.setText(inputField1.getText());
                    inputField1.setText("");
                    toggleButton.setText("⬅️"); // Меняем стрелку
                } else {
                    inputField1.setText(inputField2.getText());
                    inputField2.setText("");
                    toggleButton.setText("➡️"); // Меняем стрелку обратно
                }
                isField1ToField2 = !isField1ToField2; // Меняем состояние
            }
        });

        add(inputField1);
        add(inputField2);
        add(toggleButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Word_switcher wordSwitcher = new Word_switcher();
            wordSwitcher.setVisible(true);
        });
    }
}