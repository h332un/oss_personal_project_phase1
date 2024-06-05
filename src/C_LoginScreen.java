package oss_personal_project_phase1.src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class C_LoginScreen extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldUserName;
    private JPasswordField textFieldPassword;

    public static void main(String[] args) {
        try {
            C_LoginScreen dialog = new C_LoginScreen();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public C_LoginScreen() {
        setTitle("To-Do-List Login");
        setBounds(100, 100, 450, 185);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            {
                JLabel lblUserName = new JLabel("User Name");
                panel.add(lblUserName);
            }
            {
                textFieldUserName = new JTextField();
                panel.add(textFieldUserName);
                textFieldUserName.setColumns(10);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            {
                JLabel lblPassword = new JLabel("Password");
                panel.add(lblPassword);
            }
            {
                textFieldPassword = new JPasswordField();
                textFieldPassword.setColumns(10);
                panel.add(textFieldPassword);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String userName = "";
                        String password = "";
                        try (FileInputStream file = new FileInputStream("SignupInfo.txt");
                                Scanner buffer = new Scanner(file)) {
                            while (buffer.hasNext()) {
                                String line = buffer.nextLine();
                                String[] tokens = line.split(":");
                                if (tokens[0].equals("UserName")) {
                                    userName = tokens[1];
                                } else if (tokens[0].equals("Password")) {
                                    password = tokens[1];
                                }
                            }

                            String inputUserName = textFieldUserName.getText().trim();
                            char[] inputPasswordArray = textFieldPassword.getPassword();
                            String inputPassword = new String(inputPasswordArray).trim();

                            if (inputUserName.equals(userName) && inputPassword.equals(password)) {
                                E1_TaskOverviewScreen mainApp = new E1_TaskOverviewScreen(userName);
                                mainApp.setVisible(true);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(C_LoginScreen.this, "User name or password is incorrect",
                                        "Login Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(C_LoginScreen.this,
                                    "An error occurred while reading the file.",
                                    "File Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}