package frontend_viewcontroller;

import backend_models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModelsAndViewsController {

    BackendModelSetup theBackendModel;
    MainViewDisplay theMainViewDisplay;
    TimeOnAThread theTimeOnAThread;

    public java.util.List<String> list;

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;

    int row = 0;

    private class ClaimBookings implements ActionListener {

        String bookingID;

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (theMainViewDisplay.bookingData.getSelectedRow() != -1) {
                final DefaultTableModel model = (DefaultTableModel) theMainViewDisplay.bookingData.getModel();

                // Get stored varible
                int index = theMainViewDisplay.bookingData.getSelectedRow();
                bookingID = theMainViewDisplay.bookingID[index];
                theMainViewDisplay.pickUpLocation = model.getValueAt(index, 1).toString();
                theMainViewDisplay.dateOfPickup = model.getValueAt(index, 3).toString();

                // Remove booking from GUI
                model.removeRow(theMainViewDisplay.bookingData.getSelectedRow());
                theBackendModel.theManageBooking.update(bookingID);
                JOptionPane.showMessageDialog(theMainViewDisplay, "Pick up loaction and time is: " + theMainViewDisplay.pickUpLocation + " at " + theMainViewDisplay.dateOfPickup);
                
                // Remove bookingID from Array
                theMainViewDisplay.list.remove(bookingID);
                System.out.println(theMainViewDisplay.list);
                theMainViewDisplay.bookingID = theMainViewDisplay.list.toArray(new String[0]);
            }
        }
    }

    private class UpdateBookings implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            TimeOnAThread theTimer = new TimeOnAThread(theMainViewDisplay.bookingData);
            Thread theThreadedTimer = new Thread(theTimer);
            theThreadedTimer.start();
        }
    }

    public ModelsAndViewsController(BackendModelSetup aBackend, MainViewDisplay aMainViewDisplay) {
        this.theBackendModel = aBackend;
        this.theMainViewDisplay = aMainViewDisplay;
        this.initController();
    }

    private void initController() {
        this.theMainViewDisplay.removeSelectedButton.addActionListener(new ClaimBookings());
        this.theMainViewDisplay.getBookingsButton.addActionListener(new UpdateBookings());
    }
}
