import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task1 {
    private static int clickCount = 0;
    private static JLabel countLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Click Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton button = new JButton("Click Me!  ");
        panel.add(button);

        countLabel = new JLabel("Click count: "+clickCount);
        panel.add(countLabel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                countLabel.setText("Click count: " + clickCount);
            }
        });

        frame.setVisible(true);
    }
}
