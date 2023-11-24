import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task6 {
    private JFrame frame;
    private JPanel colorPanel;
    private JComboBox<String> colorComboBox;

    public Task6() {
        frame = new JFrame("Color Palette");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);

        String[] colorOptions = {"Red", "Green", "Blue", "Yellow", "Purple", "Orange", "Pink", "Cyan", "Magenta", "Gray"};
        colorComboBox = new JComboBox<>(colorOptions);

        colorComboBox.addActionListener(new ColorSelectionListener());

        frame.setLayout(new BorderLayout());
        frame.add(colorPanel, BorderLayout.CENTER);
        frame.add(colorComboBox, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    class ColorSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedColor = (String) colorComboBox.getSelectedItem();
            Color color = getColorFromString(selectedColor);
            colorPanel.setBackground(color);
        }

        private Color getColorFromString(String colorName) {
            switch (colorName) {
                case "Red":
                    return Color.RED;
                case "Green":
                    return Color.GREEN;
                case "Blue":
                    return Color.BLUE;
                case "Yellow":
                    return Color.YELLOW;
                case "Purple":
                    return new Color(128, 0, 128); // Purple
                case "Orange":
                    return Color.ORANGE;
                case "Pink":
                    return Color.PINK;
                case "Cyan":
                    return Color.CYAN;
                case "Magenta":
                    return Color.MAGENTA;
                case "Gray":
                    return Color.GRAY;
                default:
                    return Color.WHITE;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task6());
    }
}
