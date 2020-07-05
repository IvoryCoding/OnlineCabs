package frontend_viewcontroller;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RegisterViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;
    
    
    JLabel usernameLabel;
    JTextField usernameTextField;
    JLabel passwordTextLabel;
    JPasswordField passwordTextField;
    JLabel confirmPasswordLabel;
    JPasswordField confirmPasswordField;
    JLabel phoneNumberLabel;
    JTextField phoneNumberTextField;
    JButton registerButton;
    JLabel backToLoginLabel;
    JButton backToLoginButton;
    
    public RegisterViewDisplay(BackendModelSetup aBackend) {
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
        
        this.confirmPasswordField = new JPasswordField();
        this.confirmPasswordLabel = new JLabel();
        this.confirmPasswordLabel.setText("Confirm Password: ");
        
        this.phoneNumberTextField = new JTextField();
        this.phoneNumberLabel = new JLabel();
        this.phoneNumberLabel.setText("Phone Number: ");
        
        this.registerButton = new JButton();
        this.registerButton.setText("Register");
        
        this.backToLoginLabel = new JLabel();
        this.backToLoginLabel.setText("Already have an account?");

        this.backToLoginButton = new JButton();
        this.backToLoginButton.setText("Login");
        
        Container registerDisplayPane = this.getContentPane();
        registerDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.usernameLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.usernameTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.passwordTextLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.passwordTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.confirmPasswordLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.confirmPasswordField, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.phoneNumberLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        registerDisplayPane.add(this.phoneNumberTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        //c.ipady = 0;
        //c.ipadx = 0;
        registerDisplayPane.add(this.registerButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        registerDisplayPane.add(this.backToLoginLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        registerDisplayPane.add(this.backToLoginButton, c);
        
        this.pack();
    }
    
    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}

