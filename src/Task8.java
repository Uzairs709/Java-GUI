import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task8 {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private static final String CORRECT_USERNAME = "user";
    private static final String CORRECT_PASSWORD = "password";

    public Task8() {
        frame = new JFrame("Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(loginButton);

        loginButton.addActionListener(new LoginButtonListener());

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredUsername = usernameField.getText();
            char[] enteredPassword = passwordField.getPassword();
            String enteredPasswordString = new String(enteredPassword);

            if (enteredUsername.equals(CORRECT_USERNAME) && enteredPasswordString.equals(CORRECT_PASSWORD)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed. Please check your username and password.");
            }

            // Clear the fields
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task8());
    }
}
