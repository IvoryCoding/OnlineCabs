package backend_models;

import frontend_viewcontroller.MainViewDisplay;
import java.awt.Component;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

public class ManageBookings {

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;
    ResultSet rst = null;

    MainViewDisplay theMainViewDisplay;

    public void remove(String bookingID) {
        PreparedStatement updateBooking = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM cabbooking");

            while (rs.next()) {
                if (!rs.getString(7).equals("yes") && rs.getString(1).equals(bookingID)) {
                    try {
                        conn.setAutoCommit(false);
                        String updateString = "DELETE FROM cabbooking WHERE bookingID = ?";

                        updateBooking = conn.prepareStatement(updateString);
                        updateBooking.setString(1, bookingID);
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
                    JOptionPane.showMessageDialog(theMainViewDisplay, "Successfully removed booking.");
                } else if (rs.getString(7).equals("yes") && rs.getString(1).equals(bookingID)) {
                    JOptionPane.showMessageDialog(theMainViewDisplay, "Did not remove booking. Already payed for.");
                } 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(theMainViewDisplay, "Something went wrong. Please try again.");
        }
    }
}
