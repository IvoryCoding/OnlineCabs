package backend_models;

import frontend_viewcontroller.MainViewDisplay;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TimeOnAThread implements Runnable {

    MainViewDisplay theMainViewDisplay;

    public boolean stopRunning;
    int timer = 0, row = 0;
    private JTable bookingData;
    String name, pickUpLocation, dropOffLocation, dateOfPickup, bookingID, payed;

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;

    public TimeOnAThread(JTable table) {
        this.bookingData = table;
        this.stopRunning = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (this.stopRunning) {
                    return;
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
                st = (Statement) conn.createStatement();
                rs = st.executeQuery("SELECT * FROM cabbooking");
                int length = 0;
                while (rs.next()) {
                    length++;
                }
                this.bookingData.setModel(new javax.swing.table.DefaultTableModel(new Object[length][length], new String[]{"Booking ID", "Name", "Pickup Location", "DropOff Location", "Date of Pickup", "payed"}));
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
                    st = (Statement) conn.createStatement();
                    rs = st.executeQuery("SELECT * FROM cabbooking");
                    while (rs.next() != false) {
                        bookingID = rs.getString(1);
                        name = rs.getString(2);
                        pickUpLocation = rs.getString(3);
                        dropOffLocation = rs.getString(4);
                        dateOfPickup = rs.getString(5);
                        payed = rs.getString(7);
                        bookingData.getModel().setValueAt(bookingID, row, 0);
                        bookingData.getModel().setValueAt(name, row, 1);
                        bookingData.getModel().setValueAt(pickUpLocation, row, 2);
                        bookingData.getModel().setValueAt(dropOffLocation, row, 3);
                        bookingData.getModel().setValueAt(dateOfPickup, row, 4);
                        bookingData.getModel().setValueAt(payed, row, 5);
                        row++;
                    }
                } catch (SQLException ex) {
                    
                }
                row = 0;
                
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimeOnAThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TimeOnAThread.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
}
