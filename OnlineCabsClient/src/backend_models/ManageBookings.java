package backend_models;

import frontend_viewcontroller.ManageBookingViewDisplay;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ManageBookings {
    
    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;
    ResultSet rst = null;
    
    ManageBookingViewDisplay theManageBookingViewDisplay;
    
    public void cancel() {
    }
    
    public void add(String bookingID, String name, String pickUpLocation, String dropOffLocation, String dateOfPickup, String driverDetails, String payed) {
        
        PreparedStatement manageBooking = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();

            // int s = st.executeUpdate("INSERT INTO cabbooking VALUES ('" + bookingID + "', '" + name + "', '" + pickUpLocation + "', '" + dropOffLocation + "', '" + dateOfPickup + "', '" + driverDetails + "')");

            try {
                conn.setAutoCommit(false);
                String updateString = "INSERT INTO cabbooking VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                manageBooking = conn.prepareStatement(updateString);
                manageBooking.setString(1, bookingID);
                manageBooking.setString(2, name);
                manageBooking.setString(3, pickUpLocation);
                manageBooking.setString(4, dropOffLocation);
                manageBooking.setString(5, dateOfPickup);
                manageBooking.setString(6, driverDetails);
                manageBooking.setString(7, payed);
                manageBooking.setString(8, "");
                manageBooking.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(theManageBookingViewDisplay, "Something went wrong. Please try again.");
            } finally {
                if (manageBooking != null) {
                    manageBooking.close();
                }
                conn.setAutoCommit(true);
            }
            
        } catch (SQLException ex) {
            System.out.println("Failed to insert");
        }
    }
    
    public void edit() {
        
    }
}
