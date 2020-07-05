package frontend_viewcontroller;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PaymentViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;
    
    JButton payButton;
    JButton calculatePriceButton;
    JButton backButton;
    JLabel totalDistanceLabel;
    public JLabel calculatedPriceLabel;
    JLabel bookingIDLabel;
    JTextField bookingIDTextField;
    JLabel addPeopleLabel;
    JTextField addPeopleTextField;
    JLabel usePointsLabel;
    JTextField usePointsTextField;
    
    public PaymentViewDisplay(BackendModelSetup aBackend) {
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
        
        this.backButton = new JButton();
        this.backButton.setText("Back");
        
        this.bookingIDLabel = new JLabel();
        this.bookingIDLabel.setText("BookingID: ");
        this.bookingIDTextField = new JTextField();
        
        this.addPeopleLabel = new JLabel();
        this.addPeopleLabel.setText("Amount of Passengers: ");
        this.addPeopleTextField = new JTextField();
        
        this.usePointsLabel = new JLabel();
        this.usePointsLabel.setText("Use Points: ");
        this.usePointsTextField = new JTextField();
        
        this.calculatedPriceLabel = new JLabel();
        this.calculatedPriceLabel.setText("Total Cost: ");
        this.calculatePriceButton = new JButton();
        this.calculatePriceButton.setText("Calculate Price");
        
        this.payButton = new JButton();
        this.payButton.setText("Pay Now");

        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.backButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.bookingIDLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 25;
        c.ipadx = 200;
        mainDisplayPane.add(this.bookingIDTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.addPeopleLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 25;
        c.ipadx = 200;
        mainDisplayPane.add(this.addPeopleTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.usePointsLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 25;
        c.ipadx = 200;
        mainDisplayPane.add(this.usePointsTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.calculatedPriceLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.calculatePriceButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.payButton, c);
        
        this.pack();
    }
    
    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}
