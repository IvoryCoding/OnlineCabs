package frontend_viewcontroller;

import backend_models.*;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;
    ModelsAndViewsController theModelViewController;

    JButton bookCabButton;
    JButton updateCabButton;
    JButton paymentButton;
    JButton feedbackButton;
    JButton backToLogin;
    JButton cabProgress;
    public JLabel counter;
    public GraphicPointCounter graphicCounterPanel;
    public JButton connectAdminButton;

    public int userPoints = 0;
    public String user;
    
    Graphics g;

    public MainViewDisplay(BackendModelSetup aBackend) {
        this.theBackendModel = aBackend;
        this.initComponents();
        setIcon();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(440, 700));
        try {
            String pathName = new File("./src/images/taxiBackground.png").getAbsolutePath();
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(pathName)))));
        } catch (IOException ex) {
            System.out.println("Image not found.");
        }
        
        this.graphicCounterPanel = new GraphicPointCounter();
        this.graphicCounterPanel.setMinimumSize(new Dimension(140, 140));
        this.graphicCounterPanel.setBackground(new Color (253, 202, 23));
        
        this.counter = new JLabel();
        userPoints = theBackendModel.thePointSystem.points;
        this.counter.setText(userPoints + "");

        this.backToLogin = new JButton();
        this.backToLogin.setText("Logout");
        
        this.connectAdminButton = new JButton();
        this.connectAdminButton.setText("Admin");
        this.connectAdminButton.setVisible(false);

        this.updateCabButton = new JButton();
        this.updateCabButton.setText("Update Booking");

        this.bookCabButton = new JButton();
        this.bookCabButton.setText("Book Cab");

        this.paymentButton = new JButton();
        this.paymentButton.setText("Payment");

        this.feedbackButton = new JButton();
        this.feedbackButton.setText("Feedback");
        
        this.cabProgress = new JButton();
        this.cabProgress.setText("Check Progress");

        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.backToLogin, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.cabProgress, c);
        
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.connectAdminButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.ipady = 400;
        c.ipadx = 400;
        mainDisplayPane.add(this.graphicCounterPanel, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.counter, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 70;
        c.ipadx = 10;
        mainDisplayPane.add(this.updateCabButton, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 70;
        c.ipadx = 10;
        mainDisplayPane.add(this.bookCabButton, c);

        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 70;
        c.ipadx = 10;
        mainDisplayPane.add(this.paymentButton, c);

        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 70;
        c.ipadx = 10;
        mainDisplayPane.add(this.feedbackButton, c);

        this.pack();
    }

    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}