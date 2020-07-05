package frontend_viewcontroller;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BookingViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;

    JButton backToMain;
    JButton addBookingButton;
    JLabel nameLabel;
    JLabel pickUpLocationLabel;
    JLabel dropOffLocationLabel;
    JLabel dateOfPickupLabel;
    JLabel detailsLabel;
    JTextField nameTextFiled;
    JTextField pickUpLocationTextField;
    JTextField dropOffLocationTextField;
    JTextField dateOfPickupTextField;
    JTextArea detailsTextArea;
    JScrollPane detailsScrollPane;

    public BookingViewDisplay(BackendModelSetup aBackend) {
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

        this.backToMain = new JButton();
        this.backToMain.setText("Back");

        this.addBookingButton = new JButton();
        this.addBookingButton.setText("Book");

        this.nameLabel = new JLabel();
        this.nameLabel.setText("Name: ");
        this.nameTextFiled = new JTextField();

        this.pickUpLocationLabel = new JLabel();
        this.pickUpLocationLabel.setText("Pick up location: ");
        this.pickUpLocationTextField = new JTextField();

        this.dropOffLocationLabel = new JLabel();
        this.dropOffLocationLabel.setText("Drop off location: ");
        this.dropOffLocationTextField = new JTextField();

        this.dateOfPickupLabel = new JLabel();
        this.dateOfPickupLabel.setText("Date of Pick up: ");
        this.dateOfPickupTextField = new JTextField();

        this.detailsLabel = new JLabel();
        this.detailsLabel.setText("Other Deatils: ");
        this.detailsTextArea = new JTextArea();
        this.detailsScrollPane = new JScrollPane(this.detailsTextArea);

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
        mainDisplayPane.add(this.backToMain, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.nameLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 25;
        c.ipadx = 200;
        mainDisplayPane.add(this.nameTextFiled, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.pickUpLocationLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 25;
        c.ipadx = 200;
        mainDisplayPane.add(this.pickUpLocationTextField, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.dropOffLocationLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 25;
        c.ipadx = 200;
        mainDisplayPane.add(this.dropOffLocationTextField, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.dateOfPickupLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 25;
        c.ipadx = 200;
        mainDisplayPane.add(this.dateOfPickupTextField, c);

        c = new GridBagConstraints(); // construct a new GridBagConstraints each time you use it, to avoid subtle bugs...
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.detailsLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.ipadx = 300;
        c.ipady = 100;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        mainDisplayPane.add(this.detailsScrollPane, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.addBookingButton, c);

        this.pack();
    }

    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}
