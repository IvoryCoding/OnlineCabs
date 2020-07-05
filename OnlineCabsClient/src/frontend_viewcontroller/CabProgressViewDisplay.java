package frontend_viewcontroller;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CabProgressViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;
    
    JButton checkProgress;
    JButton back;
    JLabel bookingIDLabel;
    JTextField bookingIDTextField;
    public JLabel cabOnWayLabel;
    public JLabel cabNotOnWayLabel;

    public CabProgressViewDisplay(BackendModelSetup aBackend) {
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
        
        this.back = new JButton();
        this.back.setText("Back");
        
        this.bookingIDLabel = new JLabel();
        this.bookingIDLabel.setText("Booking ID: ");
        this.bookingIDTextField = new JTextField();
        
        this.checkProgress = new JButton();
        this.checkProgress.setText("Check");
        
        this.cabOnWayLabel = new JLabel();
        String pathName = new File("./src/images/taxiOnWay.gif").getAbsolutePath();
        this.cabOnWayLabel.setIcon(new ImageIcon(pathName));
        this.cabOnWayLabel.setVisible(false);
        
        this.cabNotOnWayLabel = new JLabel();
        pathName = new File("./src/images/taxiNotOnWay.gif").getAbsolutePath();
        this.cabNotOnWayLabel.setIcon(new ImageIcon(pathName));
        this.cabNotOnWayLabel.setVisible(false);

        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.back, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.bookingIDLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        mainDisplayPane.add(this.bookingIDTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.checkProgress, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 1;
        mainDisplayPane.add(this.cabOnWayLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 1;
        mainDisplayPane.add(this.cabNotOnWayLabel, c);
        
        this.pack();
    }
    
    public void displayCabOnWay() {
        if (cabNotOnWayLabel.isShowing()) {
            this.cabNotOnWayLabel.setVisible(false);
        }
        this.cabOnWayLabel.setVisible(true);
    }
    
    
    public void displayCabNotOnWay() {
        if (cabOnWayLabel.isShowing()) {
            this.cabOnWayLabel.setVisible(false);
        }
        this.cabNotOnWayLabel.setVisible(true);
    }

    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}
