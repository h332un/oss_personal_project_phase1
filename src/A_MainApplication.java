package oss_personal_project_phase1.src;

import java.io.File;
import javax.swing.JDialog;

public class A_MainApplication {
    public static void main(String[] args) {
        A_MainApplication app = new A_MainApplication();
        if (app.doRead()) {
            C_LoginScreen login = new C_LoginScreen();
            login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            login.setVisible(true);
        } else {
            B_SignupScreen signupScreen = new B_SignupScreen();
            signupScreen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            signupScreen.setVisible(true);
        }
    }

    private boolean doRead() {
        File file = new File("SignupInfo.txt");
        return file.exists();
    }
}