package backend_models;

import frontend_viewcontroller.RegisterViewDisplay;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NewCustomer {

    RegisterViewDisplay theRegisterViewDisplay;

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;
    ResultSet rst = null;

    public int id;
    public int prviousID;
    public String encryptedPasscode;

    public void add(int id, String user, String pass, String phoneNumber) {

        PreparedStatement registerUser = null;
        PreparedStatement createRewards = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
            st = (Statement) conn.createStatement();
            rs = st.executeQuery("SELECT * FROM userinfo");

            encryptPass(pass);
            pass = encryptedPasscode;

            while (rs.next()) {
                if (rs.next()) {
                    int spid = rs.getRow() + id;
                    prviousID = spid + 1;
                    id = prviousID;
                }
            }

            try {
                conn.setAutoCommit(false);
                String updateString = "INSERT INTO userinfo VALUES (?, ?, ?, ?)";

                registerUser = conn.prepareStatement(updateString);
                registerUser.setInt(1, id);
                registerUser.setString(2, user);
                registerUser.setString(3, pass);
                registerUser.setString(4, phoneNumber);
                registerUser.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(theRegisterViewDisplay, "Something went wrong. Please try again.");
                System.out.println("Error Code: 1001 | " + e); // Error code for SQL Exception
            } finally {
                if (registerUser != null) {
                    registerUser.close();
                }
                conn.setAutoCommit(true);
            }

            try {
                int welcomePoints = 250;
                conn.setAutoCommit(false);
                String updateString = "INSERT INTO rewardsystem VALUES (?, ?)";

                createRewards = conn.prepareStatement(updateString);
                createRewards.setString(1, user);
                createRewards.setInt(2, welcomePoints);
                createRewards.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(theRegisterViewDisplay, "Username already taken. Please choose another one.");
                System.out.println("Error Code: 1001" + e); // Error code for SQL Exception
            } finally {
                if (createRewards != null) {
                    createRewards.close();
                }
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(theRegisterViewDisplay, "Something went wrong. Please try again.");
            System.out.println("Error Code: 1001" + e); // Error code for SQL Exception
        }
    }

    public void encryptPass(String pwd) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pwd.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            encryptedPasscode = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error Code: 1002" + e); // Error code for No Algorithm Exception
        }
    }
}
