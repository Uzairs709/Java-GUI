import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Taks4 {
    private JFrame frame;
    private JTextField temperatureField;
    private JComboBox<String> sourceUnit;
    private JComboBox<String> targetUnit;
    private JButton convertButton;
    private JLabel resultLabel;

    public Taks4() {
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        temperatureField = new JTextField(10);
        sourceUnit = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        targetUnit = new JComboBox<>(new String[]{"Fahrenheit", "Celsius"});
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Temperature:"));
        inputPanel.add(temperatureField);
        inputPanel.add(sourceUnit);
        inputPanel.add(targetUnit);
        inputPanel.add(convertButton);

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(resultLabel, BorderLayout.CENTER);

        convertButton.addActionListener(new ConvertButtonListener());

        frame.setVisible(true);
    }

    class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double temperature = Double.parseDouble(temperatureField.getText());

                String source = sourceUnit.getSelectedItem().toString();
                String target = targetUnit.getSelectedItem().toString();

                double result;
                if (source.equals("Celsius") && target.equals("Fahrenheit")) {
                    result = (temperature * 9/5) + 32;
                } else if (source.equals("Fahrenheit") && target.equals("Celsius")) {
                    result = (temperature - 32) * 5/9;
                } else {
                    result = temperature;
                }

                resultLabel.setText("Result: " + result + " " + target);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a valid temperature.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Taks4());
    }
}

