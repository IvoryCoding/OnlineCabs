package backend_models;

import frontend_viewcontroller.CabProgressViewDisplay;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

public class CabProgress extends JFrame{
    CabProgressViewDisplay theCabProgressViewDisplay;

    String name, pickUpLocation, dropOffLocation, dateOfPickup, claimed, bookingID;
    
    public boolean found = false;

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;

    public void checkClaimed(String bookedID) {

        try {
            int i = 0;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM cabbooking");
            while (rs.next()) {
                found = rs.getString(1).equals(bookedID) && rs.getString(8).equals("claimed");
            }
        } catch (SQLException ex) {
        }
    }
}
