package org.example.app;

import org.example.library.Encrypt_Decrypt;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    private JFileChooser fileChooser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        JFrame frame = new JFrame("File Encryption/Decryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel algorithmLabel = new JLabel("Wybierz Algorytm szyfrowania:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(algorithmLabel, gbc);

        String[] algorithms = {"AES", "DES", "RSA"};
        JComboBox<String> algorithmComboBox = new JComboBox<>(algorithms);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(algorithmComboBox, gbc);

        JButton encryptButton = new JButton("Zakoduj");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(encryptButton, gbc);

        JButton decryptButton = new JButton("Odkoduj");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(decryptButton, gbc);

        fileChooser = new JFileChooser();

        encryptButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                returnVal = fileChooser.showSaveDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File outputFile = fileChooser.getSelectedFile();

                    String password = JOptionPane.showInputDialog(frame, "Podaj hasło:", "hasło szyfrujące", JOptionPane.PLAIN_MESSAGE);
                    if (password != null) {
                        try {
                            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                            Encrypt_Decrypt.encryptFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), password, selectedAlgorithm);
                            JOptionPane.showMessageDialog(frame, "Zakodowano plik!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "no cóz ... -> . Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        decryptButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                returnVal = fileChooser.showSaveDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File outputFile = fileChooser.getSelectedFile();

                    String password = JOptionPane.showInputDialog(frame, "Wprowadz hasło deszyfrujące:", "hasło deszyfrujące", JOptionPane.PLAIN_MESSAGE);
                    if (password != null) {
                        try {
                            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                            Encrypt_Decrypt.decryptFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), password, selectedAlgorithm);
                            JOptionPane.showMessageDialog(frame, "Rozkodowano pomyślnie plik!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "no cóz ... -> Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
