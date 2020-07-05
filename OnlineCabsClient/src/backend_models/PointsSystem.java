package backend_models;

import frontend_viewcontroller.BackendModelSetup;
import frontend_viewcontroller.MainViewDisplay;
import frontend_viewcontroller.ModelsAndViewsController;
import frontend_viewcontroller.PaymentViewDisplay;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PointsSystem {

    BackendModelSetup theBackendModel;
    PaymentViewDisplay thePaymentViewDisplay;
    MainViewDisplay theMainViewDisplay;
    public int points;
    public int cost;

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;

    PreparedStatement createRewards = null;
    PreparedStatement updateRewards = null;
    PreparedStatement updateBooking = null;

    public void usePoints(int pointsToUse, String username) {

        // Get Points
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM rewardsystem");
            while (rs.next()) {
                while (rs.getString(1).equals(username) && points < Integer.parseInt(rs.getString(2))) {
                    points = Integer.parseInt(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(thePaymentViewDisplay, "Something went wrong. Please try again.");
        }
        
        points = points - pointsToUse;
        while (pointsToUse > 10) {
            pointsToUse = pointsToUse - 10;
            System.out.println(pointsToUse);
            cost = cost - 2;
        }
        
        // Update reward for user
        try {
            conn.setAutoCommit(false);
            String updateString = "UPDATE rewardsystem SET points= ? WHERE username= ?";

            updateRewards = conn.prepareStatement(updateString);
            updateRewards.setInt(1, points);
            updateRewards.setString(2, username);
            updateRewards.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (updateRewards != null) {
                try {
                    updateRewards.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void minusPoints (int pointsToUse, int cost) {
        
    }
    
    public void setGraphics(String username) {
        points = 0;

        // Get Points
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM rewardsystem");
            while (rs.next()) {
                while (rs.getString(1).equals(username) && points < Integer.parseInt(rs.getString(2))) {
                    points = Integer.parseInt(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(thePaymentViewDisplay, "Something went wrong. Please try again.");
        }
    }
}
