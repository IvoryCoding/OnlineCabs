package backend_models;

import frontend_viewcontroller.MainViewDisplay;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public class TimeOnAThread implements Runnable {

    MainViewDisplay theMainViewDisplay;

    public boolean stopRunning;
    int timer = 0, row = 0;
    public final JTable bookingData;
    public String name, pickUpLocation, dropOffLocation, dateOfPickup;
    public String[] bookingID;

    public java.util.List<String> list;

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
                            bookingID[i] = rs.getString(1);
                            name = rs.getString(2);
                            pickUpLocation = rs.getString(3);
                            dropOffLocation = rs.getString(4);
                            dateOfPickup = rs.getString(5);
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
