import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task9 {
    private JFrame frame;
    private JPanel controlPanel;
    private JButton startButton;
    private JButton resetButton;
    private JProgressBar progressBar;
    private Timer timer;
    private int progressValue;

    public Task9() {
        frame = new JFrame("Progress Bar App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        controlPanel = new JPanel();
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        progressBar = new JProgressBar(0, 100);

        controlPanel.add(startButton);
        controlPanel.add(resetButton);

        progressBar.setStringPainted(true);
        progressBar.setValue(0);

        frame.setLayout(new BorderLayout());
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(progressBar, BorderLayout.CENTER);

        startButton.addActionListener(new StartButtonListener());
        resetButton.addActionListener(new ResetButtonListener());

        frame.setVisible(true);

        timer = new Timer(100, new TimerListener());
        progressValue = 0;
    }

    class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startButton.setEnabled(false);
            timer.start();
        }
    }

    class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            progressValue = 0;
            progressBar.setValue(progressValue);
            startButton.setEnabled(true);
        }
    }

    class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (progressValue < 100) {
                progressValue++;
                progressBar.setValue(progressValue);
            } else {
                timer.stop();
                startButton.setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task9());
    }
}
