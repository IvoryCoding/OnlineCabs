package frontend_viewcontroller;

import backend_models.*;
import java.awt.*;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;
    TimeOnAThread theTimeOnAThread;

    JLabel tableLabel;
    JButton removeSelectedButton;
    JButton getBookingsButton;
    public JTable bookingData;

    public String name, pickUpLocation, dropOffLocation, dateOfPickup, claimed;
    public String[] bookingID;
    public int length = 0;
    public java.util.List<String> list;

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;

    int row = 0;

    public MainViewDisplay(BackendModelSetup aBackend) throws SQLException {
        this.theBackendModel = aBackend;
        this.initComponents();
        setIcon();
    }

    private void initComponents() throws SQLException {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(440, 700));
        try {
            String pathName = new File("./src/images/taxiBackground.png").getAbsolutePath();
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(pathName)))));
        } catch (IOException ex) {
            System.out.println("Image not found.");
        }

        this.tableLabel = new JLabel();
        this.tableLabel.setText("Name                 Pickup                Drop off                  Time");

        this.bookingData = new JTable();
        this.bookingData.setMinimumSize(new Dimension(10, 250));
        this.bookingData.setMaximumSize(new Dimension(10, 250));
        this.bookingData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        updateDisplay();

        this.getBookingsButton = new JButton();
        this.getBookingsButton.setText("Update Booking");

        this.removeSelectedButton = new JButton();
        this.removeSelectedButton.setText("Claim Booking");

        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 2;
        mainDisplayPane.add(this.tableLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.ipadx = 400;
        c.ipady = 200;
        mainDisplayPane.add(this.bookingData, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.ipadx = 50;
        c.ipady = 15;
        mainDisplayPane.add(this.getBookingsButton, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.ipadx = 50;
        c.ipady = 15;
        mainDisplayPane.add(this.removeSelectedButton, c);

        this.pack();
    }
    
    public void updateDisplay() throws SQLException {
        //conn = DriverManager.getConnection("jdbc:mysql://192.168.1.64:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "ro0tAdmin");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
        st = (Statement) conn.createStatement();
        rs = st.executeQuery("SELECT * FROM cabbooking");
        while (rs.next()) {
            if (!rs.getString(8).equals("claimed")) {
                length++;
            }
        }
        this.bookingData.setModel(new javax.swing.table.DefaultTableModel(new Object[length][length], new String[]{"Name", "Pickup Location", "DropOff Location", "Date of Pickup"}));
        try {
            int i = 0;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM cabbooking");
            bookingID = new String[length];
            while (rs.next() != false) {
                if (!rs.getString(8).equals("claimed")) {
                    name = rs.getString(2);
                    pickUpLocation = rs.getString(3);
                    dropOffLocation = rs.getString(4);
                    dateOfPickup = rs.getString(5);
                    bookingID[i] = rs.getString(1);
                    System.out.println(bookingID[i]);
                    bookingData.getModel().setValueAt(name, row, 0);
                    bookingData.getModel().setValueAt(pickUpLocation, row, 1);
                    bookingData.getModel().setValueAt(dropOffLocation, row, 2);
                    bookingData.getModel().setValueAt(dateOfPickup, row, 3);
                    i++;
                    row++;
                }
            }
            
            list = new ArrayList<String>(Arrays.asList(bookingID));
            System.out.println(list);
            
        } catch (SQLException ex) {

        }
        row = 0;
    }

    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath();
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}
