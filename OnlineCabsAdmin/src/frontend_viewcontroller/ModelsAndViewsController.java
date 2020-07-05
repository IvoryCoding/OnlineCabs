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

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;

    int row = 0;

    private class RemoveBookings implements ActionListener {

        String bookingID;

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (theMainViewDisplay.bookingData.getSelectedRow() != -1) {
                final DefaultTableModel model = (DefaultTableModel) theMainViewDisplay.bookingData.getModel();
                
                // Get stored varible
                int index = theMainViewDisplay.bookingData.getSelectedRow();
                theMainViewDisplay.bookingID = model.getValueAt(index, 0).toString();
                bookingID = theMainViewDisplay.bookingID;
                theMainViewDisplay.pickUpLocation = model.getValueAt(index, 2).toString();
                theMainViewDisplay.dateOfPickup = model.getValueAt(index, 4).toString();
                theMainViewDisplay.payed = model.getValueAt(index, 5).toString();

                // Remove booking from database
                model.removeRow(theMainViewDisplay.bookingData.getSelectedRow());
                theBackendModel.theManageBookings.remove(bookingID);
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
        this.theMainViewDisplay.removeSelectedButton.addActionListener(new RemoveBookings());
        this.theMainViewDisplay.getBookingsButton.addActionListener(new UpdateBookings());
    }
}
