package oss_personal_project_phase1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class E1_TaskOverviewScreen extends JFrame {
    private JPanel contentPane;
    private JProgressBar progressBar;
    private DefaultListModel<D_Task> remainingTasksModel;
    private DefaultListModel<D_Task> completedTasksModel;
    private JList<D_Task> remainingTasksList;
    private JList<D_Task> completedTasksList;

    private ArrayList<D_Task> tasks = new ArrayList<>();

    public E1_TaskOverviewScreen(String username) {
        setTitle(username + "'s To-Do-List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        Font listFont = new Font("Arial", Font.BOLD, 16);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel topPanel = new JPanel();
        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(2, 2));

        JPanel topLeftPanel = new JPanel();
        topPanel.add(topLeftPanel);
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                E2_TaskDetailScreen taskDetailScreen = new E2_TaskDetailScreen(new D_Task(),
                        E1_TaskOverviewScreen.this);
                taskDetailScreen.setVisible(true);
            }
        });
        topLeftPanel.add(addTaskButton);

        JButton deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                D_Task selectedTask = remainingTasksList.getSelectedValue();
                if (selectedTask != null) {
                    removeTask(selectedTask);
                } else {
                    selectedTask = completedTasksList.getSelectedValue();
                    if (selectedTask != null) {
                        removeTask(selectedTask);
                    }
                }
            }
        });
        topLeftPanel.add(deleteTaskButton);

        JButton taskDetailEditButton = new JButton("Task Detail / Edit");
        taskDetailEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                D_Task selectedTask = remainingTasksList.getSelectedValue();
                if (selectedTask == null) {
                    selectedTask = completedTasksList.getSelectedValue();
                }
                if (selectedTask != null) {
                    E2_TaskDetailScreen taskDetailScreen = new E2_TaskDetailScreen(selectedTask,
                            E1_TaskOverviewScreen.this);
                    taskDetailScreen.setVisible(true);
                }
            }
        });
        topLeftPanel.add(taskDetailEditButton);

        JPanel topRightPanel = new JPanel();
        topPanel.add(topRightPanel);
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel belowLeftLabel = new JLabel("Completed Tasks");
        topPanel.add(belowLeftLabel);
        belowLeftLabel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel belowRightLabel = new JLabel("Remaining Tasks");
        topPanel.add(belowRightLabel);
        belowRightLabel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        contentPane.add(splitPane, BorderLayout.CENTER);

        remainingTasksModel = new DefaultListModel<>();
        completedTasksModel = new DefaultListModel<>();

        remainingTasksList = new JList<>(remainingTasksModel);
        completedTasksList = new JList<>(completedTasksModel);

        JScrollPane remainingScrollPane = new JScrollPane(remainingTasksList);
        JScrollPane completedScrollPane = new JScrollPane(completedTasksList);

        splitPane.setLeftComponent(completedScrollPane);
        splitPane.setRightComponent(remainingScrollPane);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        contentPane.add(progressBar, BorderLayout.SOUTH);

        remainingTasksList.setFont(listFont);
        completedTasksList.setFont(listFont);

        updateTaskLists();
    }

    private void updateProgressBar() {
        int totalTasks = remainingTasksModel.getSize() + completedTasksModel.getSize();
        if (totalTasks == 0) {
            progressBar.setValue(0);
        } else {
            int completedTasks = completedTasksModel.getSize();
            int progress = (int) ((completedTasks / (double) totalTasks) * 100);
            progressBar.setValue(progress);
        }
    }

    public void addTask(D_Task task) {
        tasks.add(task);
        updateTaskLists();
    }

    public void removeTask(D_Task task) {
        tasks.remove(task);
        updateTaskLists();
    }

    public void updateTaskLists() {
        remainingTasksModel.clear();
        completedTasksModel.clear();

        // Sort tasks by priority: pinned tasks first, then by due date
        tasks.sort(Comparator.comparing((D_Task task) -> !task.isPinned())
                .thenComparing(task -> LocalDate.parse(task.getDueDate())));

        for (D_Task task : tasks) {
            String dueDateText = "Due Date: " + task.getDueDate();
            if (task.isCompleted()) {
                dueDateText += " (Completed)";
            } else {
                if (isNearDeadline(task.getDueDate())) {
                    dueDateText = "<html><font color='gray'>" + dueDateText + "</font></html>";
                }
            }

            if (task.isCompleted()) {
                completedTasksModel.addElement(task);
            } else {
                remainingTasksModel.addElement(task);
            }
        }

        updateProgressBar();
    }

    private boolean isNearDeadline(String dueDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate dueDateObj = LocalDate.parse(dueDate, formatter);
        long daysUntilDue = java.time.temporal.ChronoUnit.DAYS.between(today, dueDateObj);
        return daysUntilDue < 3;
    }

    public boolean containsTask(D_Task task) {
        return tasks.contains(task);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    E1_TaskOverviewScreen frame = new E1_TaskOverviewScreen("Username");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
