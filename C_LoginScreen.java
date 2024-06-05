// C_LoginScreen.java (continued)

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class C_LoginScreen extends JDialog {

    // UI 관련 필드 생략

    public C_LoginScreen() {
        setTitle("To-Do-List Login");
        setBounds(100, 100, 450, 185);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
        {
            // User Name 입력 부분
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
            // Password 입력 부분
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
            // 버튼 패널
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                // OK 버튼
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // OK 버튼 동작 내용은 생략
                    }
                });

                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                // Cancel 버튼
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
