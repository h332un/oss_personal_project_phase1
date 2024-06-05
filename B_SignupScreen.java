// B_SignupScreen.java

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
    }
}