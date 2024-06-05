import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class B_SignupScreen extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldName;
    private JTextField textFieldUserName;
    private JPasswordField textFieldPassword;
    private JPasswordField textFieldConfirmPassword;
    private JRadioButton rdbtnMale;
    private JRadioButton rdbtnFemale;

    public static void main(String[] args) {
        try {
            B_SignupScreen dialog = new B_SignupScreen();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public B_SignupScreen() {
        setTitle("To-Do-List Registration Form");
        setBounds(100, 100, 344, 487);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(10, 2, 0, 0));
        {
            JLabel lblName = new JLabel("Name");
            contentPanel.add(lblName);
        }
        {
            textFieldName = new JTextField();
            contentPanel.add(textFieldName);
            textFieldName.setColumns(10);
        }
        {
            JLabel lblGender = new JLabel("Gender");
            contentPanel.add(lblGender);
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            {
                rdbtnMale = new JRadioButton("Male");
                panel.add(rdbtnMale);
            }
            {
                rdbtnFemale = new JRadioButton("Female");
                panel.add(rdbtnFemale);
            }
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(rdbtnFemale);
            genderGroup.add(rdbtnMale);
        }
        {
            JLabel lblUserName = new JLabel("User Name");
            contentPanel.add(lblUserName);
        }
        {
            textFieldUserName = new JTextField();
            contentPanel.add(textFieldUserName);
            textFieldUserName.setColumns(10);
        }
        {
            JLabel lblPassword = new JLabel("Password");
            contentPanel.add(lblPassword);
        }
        {
            textFieldPassword = new JPasswordField();
            textFieldPassword.setColumns(10);
            contentPanel.add(textFieldPassword);
        }
        {
            JLabel lblConfirmPassword = new JLabel("Confirm Password");
            contentPanel.add(lblConfirmPassword);
        }
        {
            textFieldConfirmPassword = new JPasswordField();
            textFieldConfirmPassword.setColumns(10);
            contentPanel.add(textFieldConfirmPassword);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Register");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        char[] password = textFieldPassword.getPassword();
                        char[] confirmPassword = textFieldConfirmPassword.getPassword();
                        if (java.util.Arrays.equals(password, confirmPassword)) {
                            try (FileOutputStream file = new FileOutputStream("SignupInfo.txt");
                                    PrintWriter buffer = new PrintWriter(file)) {

                                buffer.println("Full name:" + textFieldName.getText());
                                if (rdbtnMale.isSelected()) {
                                    buffer.println("Gender:Male");
                                } else if (rdbtnFemale.isSelected()) {
                                    buffer.println("Gender:Female");
                                } else {
                                    buffer.println("Gender:Unknown");
                                }

                                buffer.println("UserName:" + textFieldUserName.getText());
                                buffer.println("Password:" + new String(password));

                                JOptionPane.showMessageDialog(B_SignupScreen.this, "Registration Completed! Thank you");
                                C_LoginScreen loginWindow = new C_LoginScreen();
                                loginWindow.setVisible(true);
                                dispose();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(B_SignupScreen.this,
                                        "An error occurred during registration.");
                            } finally {
                                java.util.Arrays.fill(password, '0');
                                java.util.Arrays.fill(confirmPassword, '0');
                            }
                        } else {
                            JOptionPane.showMessageDialog(B_SignupScreen.this,
                                    "Passwords do not match. Please try again.");
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