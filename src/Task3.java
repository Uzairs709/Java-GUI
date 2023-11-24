import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task3 {
    private JFrame frame;
    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;
    private JTextField taskInput;
    private JButton addButton;
    private JButton deleteButton;
    private JButton markCompleteButton;

    public Task3() {
        frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        todoListModel = new DefaultListModel<>();
        todoList = new JList<>(todoListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        taskInput = new JTextField(20);
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");
        markCompleteButton = new JButton("Mark Complete");

        addButton.addActionListener(new AddButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        markCompleteButton.addActionListener(new MarkCompleteButtonListener());

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskInput);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(markCompleteButton);

        frame.setLayout(new BorderLayout());
        frame.add(todoList, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newTask = taskInput.getText();
            if (!newTask.isEmpty()) {
                todoListModel.addElement(newTask);
                taskInput.setText("");
            }
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = todoList.getSelectedIndex();
            if (selectedIndex != -1) {
                todoListModel.remove(selectedIndex);
            }
        }
    }

    class MarkCompleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = todoList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = todoListModel.get(selectedIndex);
                todoListModel.set(selectedIndex,todoList.getSelectedValue()+"(Done)");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task3());
    }
}
