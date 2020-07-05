package backend_models;

import frontend_viewcontroller.MainViewDisplay;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ManageBookings {

    public boolean stopRunning;
    int timer = 0, row = 0;
    public JTable bookingData;
    public String name, pickUpLocation, dropOffLocation, dateOfPickup;
    public String[] bookingID;
    public int length = 0;

    public java.util.List<String> list;    

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;
    ResultSet rst = null;

    MainViewDisplay theMainViewDisplay;

    String claimed;

    public void update(String bookingID) {
        PreparedStatement updateBooking = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM cabbooking");

            claimed = "claimed";
            try {
                conn.setAutoCommit(false);
                String updateString = "UPDATE cabbooking SET claimed = ? WHERE bookingID = ?";

                updateBooking = conn.prepareStatement(updateString);
                updateBooking.setString(1, claimed);
                updateBooking.setString(2, bookingID);
                updateBooking.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                if (updateBooking != null) {
                    updateBooking.close();
                }
                conn.setAutoCommit(true);
            }
            claimed = "";
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(theMainViewDisplay, "Something went wrong. Please try again.");
        }
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
        } catch (SQLException ex) {

        }
        row = 0;
    }
}
