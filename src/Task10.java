import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task10 {
    private JFrame frame;
    private JPanel calendarPanel;
    private JButton prevMonthButton;
    private JButton nextMonthButton;
    private JLabel monthLabel;
    private Map<Integer, List<String>> events;

    private int currentYear;
    private int currentMonth;

    public Task10() {
        frame = new JFrame("Monthly Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        calendarPanel = new JPanel();
        prevMonthButton = new JButton("Previous Month");
        nextMonthButton = new JButton("Next Month");
        monthLabel = new JLabel();

        calendarPanel.setLayout(new GridLayout(7, 7));

        prevMonthButton.addActionListener(new PrevMonthListener());
        nextMonthButton.addActionListener(new NextMonthListener());

        frame.setLayout(new BorderLayout());
        frame.add(prevMonthButton, BorderLayout.WEST);
        frame.add(nextMonthButton, BorderLayout.EAST);
        frame.add(monthLabel, BorderLayout.NORTH);
        frame.add(calendarPanel, BorderLayout.CENTER);

        events = new HashMap<>();

        // Get the current date
        java.util.Calendar cal = java.util.Calendar.getInstance();
        currentYear = cal.get(java.util.Calendar.YEAR);
        currentMonth = cal.get(java.util.Calendar.MONTH);

        updateCalendar();

        frame.setVisible(true);
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        monthLabel.setText(getMonthName(currentMonth) + " " + currentYear);

        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        for (String dayName : dayNames) {
            calendarPanel.add(new JLabel(dayName, SwingConstants.CENTER));
        }

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(currentYear, currentMonth, 1);
        int firstDayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int numDaysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= numDaysInMonth; day++) {
            JButton dayButton = new JButton(Integer.toString(day));
            dayButton.addActionListener(new DayButtonListener(day));
            calendarPanel.add(dayButton);
        }

        frame.validate();
        frame.repaint();
    }

    private String getMonthName(int month) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[month];
    }

    class DayButtonListener implements ActionListener {
        private int day;

        public DayButtonListener(int day) {
            this.day = day;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String inputEvent = JOptionPane.showInputDialog(frame, "Enter an event for " + day + " " + getMonthName(currentMonth) + " " + currentYear);
            if (inputEvent != null && !inputEvent.trim().isEmpty()) {
                events.computeIfAbsent(day, k -> new ArrayList<>()).add(inputEvent);
            }
        }
    }

    class PrevMonthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentMonth == 0) {
                currentMonth = 11;
                currentYear--;
            } else {
                currentMonth--;
            }
            updateCalendar();
        }
    }

    class NextMonthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentMonth == 11) {
                currentMonth = 0;
                currentYear++;
            } else {
                currentMonth++;
            }
            updateCalendar();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task10());
    }
}
