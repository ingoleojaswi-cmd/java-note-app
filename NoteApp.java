import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NoteApp extends JFrame {

    JTextArea textArea;

    NoteApp() {
        setTitle("Note App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton saveButton = new JButton("Save");
        JButton openButton = new JButton("Open");
        JButton clearButton = new JButton("Clear");

        JPanel panel = new JPanel();
        panel.add(saveButton);
        panel.add(openButton);
        panel.add(clearButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> saveNote());
        openButton.addActionListener(e -> openNote());
        clearButton.addActionListener(e -> textArea.setText(""));

        setVisible(true);
    }

    void saveNote() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter writer = new FileWriter(chooser.getSelectedFile());
                writer.write(textArea.getText());
                writer.close();

                JOptionPane.showMessageDialog(this, "Note saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error while saving note.");
            }
        }
    }

    void openNote() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader reader =
                        new BufferedReader(new FileReader(chooser.getSelectedFile()));

                textArea.read(reader, null);
                reader.close();

                JOptionPane.showMessageDialog(this, "Note opened successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error while opening note.");
            }
        }
    }

    public static void main(String[] args) {
        new NoteApp();
    }
}