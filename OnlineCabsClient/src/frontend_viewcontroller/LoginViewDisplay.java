package frontend_viewcontroller;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class LoginViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;

    JLabel usernameLabel;
    JTextField usernameTextField;
    JLabel passwordTextLabel;
    JPasswordField passwordTextField;
    JButton loginButton;
    JLabel registerNewLabel;
    JButton registerButton;

    public LoginViewDisplay(BackendModelSetup aBackend) {
        this.theBackendModel = aBackend;
        this.initComponents();
        setIcon();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(440, 700));
        try {
            String pathName = new File("./src/images/taxiBackgroundLogReg.png").getAbsolutePath();
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(pathName)))));
        } catch (IOException ex) {
            System.out.println("Image not found.");
        }

        this.usernameTextField = new JTextField();
        this.usernameLabel = new JLabel();
        this.usernameLabel.setText("Username: ");

        this.passwordTextField = new JPasswordField();
        this.passwordTextLabel = new JLabel();
        this.passwordTextLabel.setText("Password: ");

        this.loginButton = new JButton();
        this.loginButton.setText("Login");

        this.registerNewLabel = new JLabel();
        this.registerNewLabel.setText("Need to register? ");

        this.registerButton = new JButton();
        this.registerButton.setText("Register");

        Container registerDisplayPane = this.getContentPane();
        registerDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        registerDisplayPane.add(this.usernameLabel, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.usernameTextField, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        registerDisplayPane.add(this.passwordTextLabel, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.passwordTextField, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 5;
        c.ipadx = 30;
        registerDisplayPane.add(this.loginButton, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        registerDisplayPane.add(this.registerNewLabel, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 5;
        c.ipadx = 30;
        registerDisplayPane.add(this.registerButton, c);

        this.pack();
    }

    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}
