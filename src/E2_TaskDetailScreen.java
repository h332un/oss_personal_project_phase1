import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class E2_TaskDetailScreen extends JDialog {
    private D_Task task;
    private E1_TaskOverviewScreen taskOverviewScreen;
    private JTextField textFieldTitle;
    private JTextField textFieldDueDate;
    private JCheckBox checkBoxPinned;
    private JCheckBox checkBoxMonthly;
    private JCheckBox checkBoxWeekly;
    private JCheckBox checkBoxDaily;
    private JCheckBox checkBoxCompleted;

    public E2_TaskDetailScreen(D_Task task, E1_TaskOverviewScreen taskOverviewScreen) {
        this.task = task;
        this.taskOverviewScreen = taskOverviewScreen;
        setTitle("Set Task's Detail: " + task.getTitle());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new GridLayout(9, 2, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblTitle = new JLabel("Title:");
        contentPanel.add(lblTitle);

        textFieldTitle = new JTextField(task.getTitle());
        contentPanel.add(textFieldTitle);

        JLabel lblDueDate = new JLabel("Due Date (YYYY-MM-DD):"); // Updated label text
        contentPanel.add(lblDueDate);

        textFieldDueDate = new JTextField(task.getDueDate());
        contentPanel.add(textFieldDueDate);

        JLabel lblPinned = new JLabel("Pinned:");
        contentPanel.add(lblPinned);

        checkBoxPinned = new JCheckBox();
        checkBoxPinned.setSelected(task.isPinned());
        contentPanel.add(checkBoxPinned);

        JLabel lblMonthly = new JLabel("Monthly Periodic:");
        contentPanel.add(lblMonthly);

        checkBoxMonthly = new JCheckBox();
        checkBoxMonthly.setSelected(task.isMonthlyPeriodic());
        contentPanel.add(checkBoxMonthly);

        JLabel lblWeekly = new JLabel("Weekly Periodic:");
        contentPanel.add(lblWeekly);

        checkBoxWeekly = new JCheckBox();
        checkBoxWeekly.setSelected(task.isWeeklyPeriodic());
        contentPanel.add(checkBoxWeekly);

        JLabel lblDaily = new JLabel("Daily Periodic:");
        contentPanel.add(lblDaily);

        checkBoxDaily = new JCheckBox();
        checkBoxDaily.setSelected(task.isDailyPeriodic());
        contentPanel.add(checkBoxDaily);

        JLabel lblCompleted = new JLabel("Completed:");
        contentPanel.add(lblCompleted);

        checkBoxCompleted = new JCheckBox();
        checkBoxCompleted.setSelected(task.isCompleted());
        contentPanel.add(checkBoxCompleted);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dueDateText = textFieldDueDate.getText();
                if (!dueDateText.isEmpty()) {
                    try {
                        LocalDate dueDate = LocalDate.parse(dueDateText);
                        task.setDueDate(dueDate.toString());
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(E2_TaskDetailScreen.this,
                                "Invalid date format. Please enter a date in YYYY-MM-DD format.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(E2_TaskDetailScreen.this,
                            "Due date cannot be empty. Please enter a valid date.");
                    return;
                }

                if (!taskOverviewScreen.containsTask(task)) {
                    taskOverviewScreen.addTask(task);
                }
                taskOverviewScreen.updateTaskLists();
                dispose();
            }
        });
        buttonPane.add(saveButton);
        getRootPane().setDefaultButton(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldTitle.setText(task.getTitle());
                textFieldDueDate.setText(task.getDueDate());
                checkBoxPinned.setSelected(task.isPinned());
                checkBoxMonthly.setSelected(task.isMonthlyPeriodic());
                checkBoxWeekly.setSelected(task.isWeeklyPeriodic());
                checkBoxDaily.setSelected(task.isDailyPeriodic());
                checkBoxCompleted.setSelected(task.isCompleted());

                dispose();
            }
        });
        buttonPane.add(cancelButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                D_Task sampleTask = new D_Task();
                sampleTask.setTitle("Sample Task");
                sampleTask.setDueDate("2023-06-15");
                sampleTask.setPinned(true);
                sampleTask.setMonthlyPeriodic(false);
                sampleTask.setWeeklyPeriodic(true);
                sampleTask.setDailyPeriodic(false);
                sampleTask.setCompleted(false);

                E2_TaskDetailScreen dialog = new E2_TaskDetailScreen(sampleTask, null); // Passing null for simplicity
                dialog.setVisible(true);
            }
        });
    }
}