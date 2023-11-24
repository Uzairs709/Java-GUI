import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Task5 {
    private JFrame frame;
    private JLabel imageLabel;
    private JButton previousButton;
    private JButton nextButton;
    private File[] imageFiles;
    private int currentImageIndex = 0;

    public Task5() {
        frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);

        frame.setLayout(new BorderLayout());
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        previousButton.addActionListener(new PreviousButtonListener());
        nextButton.addActionListener(new NextButtonListener());

        // Load a directory of images
        loadImagesFromDirectory("F:\\downloads\\naruto wallpaper");

        if (imageFiles.length > 0) {
            displayImage(currentImageIndex);
        }

        frame.setVisible(true);
    }

    private void loadImagesFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        imageFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
    }

    private void displayImage(int index) {
        try {
            BufferedImage image = ImageIO.read(imageFiles[index]);
            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentImageIndex > 0) {
                currentImageIndex--;
                displayImage(currentImageIndex);
            }
        }
    }

    class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentImageIndex < imageFiles.length - 1) {
                currentImageIndex++;
                displayImage(currentImageIndex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task5());
    }
}
