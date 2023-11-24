import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task2 {
    private JFrame frame;
    private JPanel panel;
    private JTextField inputField;
    private JButton[] digitButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private double firstOperand = 0;
    private char operation = ' ';
    private boolean isNewInput = true;

    public Task2() {
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setHorizontalAlignment(JTextField.RIGHT);

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].addActionListener(new DigitButtonAction(i));
        }

        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operationButtons[i].addActionListener(new OperationButtonAction(i));
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsButtonAction());

        panel.add(inputField);
        for (int i = 1; i <= 9; i++) {
            panel.add(digitButtons[i]);
        }
        panel.add(operationButtons[0]);
        panel.add(digitButtons[0]);
        for (int i = 1; i <= 3; i++) {
            panel.add(digitButtons[i]);
        }
        panel.add(operationButtons[1]);
        panel.add(operationButtons[2]);
        panel.add(operationButtons[3]);
        panel.add(equalsButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    class DigitButtonAction implements ActionListener {
        private int digit;

        public DigitButtonAction(int digit) {
            this.digit = digit;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isNewInput) {
                inputField.setText(String.valueOf(digit));
                isNewInput = false;
            } else {
                inputField.setText(inputField.getText() + digit);
            }
        }
    }

    class OperationButtonAction implements ActionListener {
        private int op;

        public OperationButtonAction(int op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            firstOperand = Double.parseDouble(inputField.getText());
            switch (op) {
                case 0:
                    operation = '+';
                    break;
                case 1:
                    operation = '-';
                    break;
                case 2:
                    operation = '*';
                    break;
                case 3:
                    operation = '/';
                    break;
                default:
                    operation = ' ';
                    break;
            }
            isNewInput = true;
        }
    }

    class EqualsButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (operation != ' ') {
                double secondOperand = Double.parseDouble(inputField.getText());
                double result = switch (operation) {
                    case '+' -> firstOperand + secondOperand;
                    case '-' -> firstOperand - secondOperand;
                    case '*' -> firstOperand * secondOperand;
                    case '/' -> secondOperand != 0 ? firstOperand / secondOperand : Double.POSITIVE_INFINITY;
                    default -> firstOperand;
                };
                inputField.setText(String.valueOf(result));
                isNewInput = true;
                operation = ' ';
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task2());
    }
}
