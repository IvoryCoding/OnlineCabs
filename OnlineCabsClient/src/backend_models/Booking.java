package backend_models;

import frontend_viewcontroller.BookingViewDisplay;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Booking {
    
    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;
    ResultSet rst = null;
    
    BookingViewDisplay theBookingViewDisplay;
    
    public void show() {
    }
    
    public void add(String bookingID, String name, String pickUpLocation, String dropOffLocation, String dateOfPickup, String driverDetails, String payed) {
        
        PreparedStatement addBooking = null;
        try {
            // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();

            // int s = st.executeUpdate("INSERT INTO cabbooking VALUES ('" + bookingID + "', '" + name + "', '" + pickUpLocation + "', '" + dropOffLocation + "', '" + dateOfPickup + "', '" + driverDetails + "')");

            try {
                conn.setAutoCommit(false);
                String updateString = "INSERT INTO cabbooking VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                addBooking = conn.prepareStatement(updateString);
                addBooking.setString(1, bookingID);
                addBooking.setString(2, name);
                addBooking.setString(3, pickUpLocation);
                addBooking.setString(4, dropOffLocation);
                addBooking.setString(5, dateOfPickup);
                addBooking.setString(6, driverDetails);
                addBooking.setString(7, payed);
                addBooking.setString(8, "");
                addBooking.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(theBookingViewDisplay, "Something went wrong. Please try again.");
            } finally {
                if (addBooking != null) {
                    addBooking.close();
                }
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            System.out.println("Failed to get id");
        }
    }
    
    public void update() {
    }
}
