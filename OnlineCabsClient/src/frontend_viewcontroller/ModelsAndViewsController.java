package frontend_viewcontroller;

import backend_models.Feedback;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// Database
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ModelsAndViewsController {

    BackendModelSetup theBackendModel;
    MainViewDisplay theMainViewDisplay;
    RegisterViewDisplay theRegisterViewDisplay;
    LoginViewDisplay theLoginViewDisplay;
    FeedbackViewDisplay theFeedbackViewDisplay;
    BookingViewDisplay theBookingViewDisplay;
    ManageBookingViewDisplay theManageBookingViewDisplay;
    PaymentViewDisplay thePaymentViewDisplay;
    LoadingViewDisplay theLoadingViewDisplay;
    CabProgressViewDisplay theCabProgressViewDisplay;

    String username;
    String bookingIDRemoval;
    String bookedID;
    String payed = "no";
    int cost;
    int uPoints;

    // Database
    java.sql.Connection conn = null;
    ResultSet rs = null;
    Statement st;

    private class LoginAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            // Access the data base and fetch user data
            String iuid = theLoginViewDisplay.usernameTextField.getText();
            String ipwd = theLoginViewDisplay.passwordTextField.getText();
            username = theLoginViewDisplay.usernameTextField.getText();
            boolean userFound = false;

            theBackendModel.theRegisteredUser.encryptPass(ipwd);
            ipwd = theBackendModel.theRegisteredUser.encryptedPasscode;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
                st = (Statement) conn.createStatement();
                rs = st.executeQuery("SELECT * FROM userinfo");

                while (rs.next()) {
                    if (rs.getString(2).equals(iuid) && rs.getString(3).equals(ipwd)) {
                        userFound = true;
                    } else if (userFound != true) {
                        userFound = false;
                    }
                }
                if (userFound == true) {
                    if (iuid.equals("Admin")) {
                        theMainViewDisplay.connectAdminButton.setVisible(true);
                    } else {
                        theMainViewDisplay.connectAdminButton.setVisible(false);
                    }
                    theMainViewDisplay.setVisible(true);
                    theMainViewDisplay.setBounds(theLoginViewDisplay.getBounds());
                    theLoginViewDisplay.setVisible(false);
                    theLoginViewDisplay.usernameTextField.setText("");
                    theLoginViewDisplay.passwordTextField.setText("");
                    theMainViewDisplay.user = iuid;
                    theBackendModel.thePointSystem.setGraphics(iuid);
                    uPoints = theBackendModel.thePointSystem.points;
                    theMainViewDisplay.counter.setText(uPoints + "");
                    theMainViewDisplay.graphicCounterPanel.UpdateProgress(uPoints);
                    theMainViewDisplay.graphicCounterPanel.repaint();
                } else if (userFound == false) {
                    JOptionPane.showMessageDialog(theLoginViewDisplay, "Incorrect username or password.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(theLoginViewDisplay, "Something went wrong. Please try again.");
            }
        }
    }

    private class RegisterAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            // Access the data base and store new user information
            String uid = theRegisterViewDisplay.usernameTextField.getText();
            String pwd = theRegisterViewDisplay.passwordTextField.getText();
            String cpwd = theRegisterViewDisplay.confirmPasswordField.getText();
            String phoneNumber = theRegisterViewDisplay.phoneNumberTextField.getText();

            int id;

            if (pwd.equals(cpwd)) {
                id = theBackendModel.theNewCustomer.prviousID;
                id++;
                theBackendModel.theNewCustomer.add(id, uid, pwd, phoneNumber);
                theLoginViewDisplay.setVisible(true);
                theLoginViewDisplay.setBounds(theRegisterViewDisplay.getBounds());
                theRegisterViewDisplay.setVisible(false);
                theBackendModel.theNewCustomer.prviousID = id;

                theRegisterViewDisplay.usernameTextField.setText("");
                theRegisterViewDisplay.passwordTextField.setText("");
                theRegisterViewDisplay.confirmPasswordField.setText("");
                theRegisterViewDisplay.phoneNumberTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(theRegisterViewDisplay, "Password don't match.");
            }
        }
    }

    public class SubmitFeedbackAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theLoadingViewDisplay.setVisible(true);
            theLoadingViewDisplay.setBounds(theFeedbackViewDisplay.getBounds());
            theFeedbackViewDisplay.setVisible(false);
            String feedback = theFeedbackViewDisplay.feedBackContentField.getText();
            String sender = theFeedbackViewDisplay.senderTextField.getText();
            String subject = theFeedbackViewDisplay.subjectTextField.getText();
            Feedback.mail(sender, subject, feedback);
            JOptionPane.showMessageDialog(theLoadingViewDisplay, "Succeesfully given feedback.");
            theMainViewDisplay.setVisible(true);
            theMainViewDisplay.setBounds(theLoadingViewDisplay.getBounds());
            theLoadingViewDisplay.setVisible(false);
            theFeedbackViewDisplay.feedBackContentField.setText("");
            theFeedbackViewDisplay.senderTextField.setText("");
            theFeedbackViewDisplay.subjectTextField.setText("");

        }
    }

    public class AddBooking implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            char[] randomArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
            int lengthCounter = 8;
            String name, pickUpLocation, dropOffLocation, dateOfPickup, driverDetails;
            String bookingID = "";
            name = theBookingViewDisplay.nameTextFiled.getText();
            pickUpLocation = theBookingViewDisplay.pickUpLocationTextField.getText();
            dropOffLocation = theBookingViewDisplay.dropOffLocationTextField.getText();
            dateOfPickup = theBookingViewDisplay.dateOfPickupTextField.getText();
            driverDetails = theBookingViewDisplay.detailsTextArea.getText();

            if (name.equals("") || pickUpLocation.equals("") || dropOffLocation.equals("") || dateOfPickup.equals("")) {
                JOptionPane.showMessageDialog(theBookingViewDisplay, "Missing information.");
            } else {
                // Error 
                do {
                    int i;
                    i = (int) (Math.random() * 62);
                    bookingID += randomArray[i] + "";
                } while (bookingID.length() < lengthCounter);
                theBackendModel.theBooking.add(bookingID, name, pickUpLocation, dropOffLocation, dateOfPickup, driverDetails, payed);
                JOptionPane.showMessageDialog(theBookingViewDisplay, "Succeesfully booked. Please remember the following. " + bookingID);
                theBookingViewDisplay.nameTextFiled.setText("");
                theBookingViewDisplay.pickUpLocationTextField.setText("");
                theBookingViewDisplay.dropOffLocationTextField.setText("");
                theBookingViewDisplay.dateOfPickupTextField.setText("");
                theBookingViewDisplay.detailsTextArea.setText("");
                theMainViewDisplay.setVisible(true);
                theMainViewDisplay.setBounds(theBookingViewDisplay.getBounds());
                theBookingViewDisplay.setVisible(false);

            }
        }
    }

    public class UpdateBookingAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name, pickUpLocation, dropOffLocation, dateOfPickup, driverDetails;
            name = theManageBookingViewDisplay.nameTextFiled.getText();
            pickUpLocation = theManageBookingViewDisplay.pickUpLocationTextField.getText();
            dropOffLocation = theManageBookingViewDisplay.dropOffLocationTextField.getText();
            dateOfPickup = theManageBookingViewDisplay.dateOfPickupTextField.getText();
            driverDetails = theManageBookingViewDisplay.detailsTextArea.getText();

            if (name.equals("") || pickUpLocation.equals("") || dropOffLocation.equals("") || dateOfPickup.equals("")) {
                JOptionPane.showMessageDialog(theBookingViewDisplay, "Something went wrong. Please try again.");
            } else {
                theBackendModel.theManageBookings.add(bookedID, name, pickUpLocation, dropOffLocation, dateOfPickup, driverDetails, payed);
                JOptionPane.showMessageDialog(theManageBookingViewDisplay, "Succeesfully booked. Please remember the following. " + bookedID);
                theMainViewDisplay.setVisible(true);
                theMainViewDisplay.setBounds(theManageBookingViewDisplay.getBounds());
                theManageBookingViewDisplay.setVisible(false);
            }
        }
    }

    public class GetBookedAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name, pickUpLocation, dropOffLocation, dateOfPickup, driverDetails;
            bookedID = theManageBookingViewDisplay.bookingIDTextField.getText();

            PreparedStatement updateBooking = null;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
                st = (Statement) conn.createStatement();
                rs = st.executeQuery("SELECT * FROM cabbooking");
                while (rs.next()) {
                    if (rs.getString(1).equals(bookedID)) {
                        String bookedID = rs.getString(1);
                        name = rs.getString(2);
                        pickUpLocation = rs.getString(3);
                        dropOffLocation = rs.getString(4);
                        dateOfPickup = rs.getString(5);
                        driverDetails = rs.getString(6);
                        payed = rs.getString(7);

                        theManageBookingViewDisplay.nameTextFiled.setText(name);
                        theManageBookingViewDisplay.pickUpLocationTextField.setText(pickUpLocation);
                        theManageBookingViewDisplay.dropOffLocationTextField.setText(dropOffLocation);
                        theManageBookingViewDisplay.dateOfPickupTextField.setText(dateOfPickup);
                        theManageBookingViewDisplay.detailsTextArea.setText(driverDetails);

                        // Can use but prone to SQL attacks
                        // int s = st.executeUpdate("DELETE FROM cabbooking WHERE bookingID='"+bookedID+"'");
                        try {
                            conn.setAutoCommit(false);
                            String updateString = "DELETE FROM cabbooking WHERE bookingID = ?";

                            updateBooking = conn.prepareStatement(updateString);
                            updateBooking.setString(1, bookedID);
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
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(theBookingViewDisplay, "Something went wrong. Please try again.");
            }
        }
    }

    public class GetCost implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String start;
            String end;
            String bookingID = thePaymentViewDisplay.bookingIDTextField.getText();
            String amountPassenger = thePaymentViewDisplay.addPeopleTextField.getText();
            bookingIDRemoval = bookingID;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
                st = (Statement) conn.createStatement();
                rs = st.executeQuery("SELECT * FROM cabbooking");
                while (rs.next()) {
                    if (rs.getString(1).equals(bookingID)) {
                        start = rs.getString(3);
                        end = rs.getString(4);
                        start = start.replaceAll("[\\s|\\u00A0]", "%20");
                        end = end.replaceAll("[\\s|\\u00A0]", "%20");
                        theBackendModel.thePayment.getDistance(start, end);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(theBookingViewDisplay, "Something went wrong. Please try again.");
            } catch (IOException ex) {
                Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (Integer.parseInt(thePaymentViewDisplay.usePointsTextField.getText()) > 0) {
                int usedPoints = Integer.parseInt(thePaymentViewDisplay.usePointsTextField.getText());
                theBackendModel.thePayment.minusPoints(usedPoints);
            }

            cost = theBackendModel.thePayment.displayTotalCost;
            cost = cost + (int) (Integer.parseInt(amountPassenger) * 8.3);
            thePaymentViewDisplay.calculatedPriceLabel.setText("Total Cost: $" + cost);
        }
    }

    public class AddPointsAndPay implements ActionListener {

        PreparedStatement createRewards = null;
        PreparedStatement updateRewards = null;
        PreparedStatement updateBooking = null;

        @Override
        public void actionPerformed(ActionEvent ae) {

            int points = Integer.parseInt(thePaymentViewDisplay.usePointsTextField.getText());
            int addUPoints = 0;

            if (thePaymentViewDisplay.usePointsTextField.getText().equals("0")) {
                // Add points and open Payment Gateway
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
                    st = (Statement) conn.createStatement();
                    rs = st.executeQuery("SELECT * FROM rewardsystem");
                    while (rs.next()) {
                        while (rs.getString(1).equals(username) && addUPoints <= Integer.parseInt(rs.getString(2))) {
                            addUPoints = Integer.parseInt(rs.getString(2));
                            theBackendModel.thePayment.addPoints(addUPoints);
                            addUPoints = theBackendModel.thePayment.totalPoints;
                            theMainViewDisplay.counter.setText(addUPoints + "");
                            theMainViewDisplay.graphicCounterPanel.UpdateProgress(addUPoints);
                            theMainViewDisplay.graphicCounterPanel.repaint();
                            theBackendModel.thePayment.pay();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(thePaymentViewDisplay, "Something went wrong. Please try again.");
                }
                // Remove reward for user
                try {
                    conn.setAutoCommit(false);
                    String updateString = "DELETE FROM rewardsystem WHERE username= ?";

                    updateRewards = conn.prepareStatement(updateString);
                    updateRewards.setString(1, username);
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
                // Create new reward for user
                try {
                    conn.setAutoCommit(false);
                    String updateString = "INSERT INTO rewardsystem VALUES (?, ?)";

                    createRewards = conn.prepareStatement(updateString);
                    createRewards.setString(1, username);
                    createRewards.setInt(2, addUPoints);
                    createRewards.executeUpdate();
                    conn.commit();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(thePaymentViewDisplay, "Fix Here | Something went wrong. Please try again.");
                    System.out.println(e);
                } finally {
                    if (createRewards != null) {
                        try {
                            createRewards.close();
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
            } else {
                // Get Points
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecabs?zeroDateTimeBehavior=convertToNull", "root", "");
                    st = (Statement) conn.createStatement();
                    rs = st.executeQuery("SELECT * FROM rewardsystem");
                    while (rs.next()) {
                        while (rs.getString(1).equals(username) && addUPoints < Integer.parseInt(rs.getString(2))) {
                            addUPoints = Integer.parseInt(rs.getString(2));
                            theBackendModel.thePayment.pay();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(thePaymentViewDisplay, "Something went wrong. Please try again.");
                }

                theBackendModel.thePointSystem.usePoints(points, username);
                addUPoints = addUPoints - points;
                theMainViewDisplay.counter.setText(addUPoints + "");
            }
            // Update booking status to yes
            bookedID = thePaymentViewDisplay.bookingIDTextField.getText();
            payed = "yes";
            try {
                conn.setAutoCommit(false);
                String updateString = "UPDATE cabbooking SET payed= ? WHERE bookingID= ?";
                updateBooking = conn.prepareStatement(updateString);
                updateBooking.setString(1, payed);
                payed = "no";
                updateBooking.setString(2, bookedID);
                updateBooking.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                if (updateBooking != null) {
                    try {
                        updateBooking.close();
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

            // Display points and change pane
            JOptionPane.showMessageDialog(thePaymentViewDisplay, "You have " + addUPoints + " points.");
            theMainViewDisplay.setVisible(true);
            theMainViewDisplay.setBounds(thePaymentViewDisplay.getBounds());
            thePaymentViewDisplay.setVisible(false);
            thePaymentViewDisplay.addPeopleTextField.setText("");
            thePaymentViewDisplay.bookingIDTextField.setText("");
            thePaymentViewDisplay.usePointsTextField.setText("");
            thePaymentViewDisplay.calculatedPriceLabel.setText("Cost: ");

        }
    }

    public class checkProgress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            // Checks progress
            String bookingID;
            bookingID = theCabProgressViewDisplay.bookingIDTextField.getText();
            theBackendModel.theCabProgress.checkClaimed(bookingID);
            if (theBackendModel.theCabProgress.found == true) {
                theCabProgressViewDisplay.displayCabOnWay();
            } else {
                theCabProgressViewDisplay.displayCabNotOnWay();
            }
        }
    }

    public class showCheckProgress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theCabProgressViewDisplay.setVisible(true);
            theCabProgressViewDisplay.setBounds(theMainViewDisplay.getBounds());
            theMainViewDisplay.setVisible(false);
        }
    }

    public class ShowPayment implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            thePaymentViewDisplay.setVisible(true);
            thePaymentViewDisplay.setBounds(theMainViewDisplay.getBounds());
            theMainViewDisplay.setVisible(false);
        }
    }

    public class LogoutAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theLoginViewDisplay.setVisible(true);
            theLoginViewDisplay.setBounds(theMainViewDisplay.getBounds());
            theMainViewDisplay.setVisible(false);
        }
    }

    public class ShowRegisterAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theRegisterViewDisplay.setVisible(true);
            theRegisterViewDisplay.setBounds(theLoginViewDisplay.getBounds());
            theLoginViewDisplay.setVisible(false);
        }
    }

    public class ShowLoginAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theLoginViewDisplay.setVisible(true);
            theLoginViewDisplay.setBounds(theRegisterViewDisplay.getBounds());
            theRegisterViewDisplay.setVisible(false);
        }
    }

    public class ShowFeedbackAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theFeedbackViewDisplay.setVisible(true);
            theFeedbackViewDisplay.setBounds(theMainViewDisplay.getBounds());
            theMainViewDisplay.setVisible(false);
        }
    }

    public class ShowBookCabAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theBookingViewDisplay.setVisible(true);
            theBookingViewDisplay.setBounds(theMainViewDisplay.getBounds());
            theMainViewDisplay.setVisible(false);
        }
    }

    public class ShowManageBookingAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theManageBookingViewDisplay.setVisible(true);
            theManageBookingViewDisplay.setBounds(theMainViewDisplay.getBounds());
            theMainViewDisplay.setVisible(false);
        }
    }

    public class OpenAdminSideAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Open admin
            File fileName = new File("OnlineCabsAdmin.jar");
            String filePath = fileName.getAbsolutePath();
            File file = new File(filePath);
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public class BackToMainAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theMainViewDisplay.setVisible(true);

            if (theFeedbackViewDisplay.isShowing()) {
                theMainViewDisplay.setBounds(theFeedbackViewDisplay.getBounds());
                theFeedbackViewDisplay.setVisible(false);
            } else if (theManageBookingViewDisplay.isShowing()) {
                theMainViewDisplay.setBounds(theManageBookingViewDisplay.getBounds());
                theManageBookingViewDisplay.setVisible(false);
            } else if (thePaymentViewDisplay.isShowing()) {
                theMainViewDisplay.setBounds(thePaymentViewDisplay.getBounds());
                thePaymentViewDisplay.setVisible(false);
            } else if (theBookingViewDisplay.isShowing()) {
                theMainViewDisplay.setBounds(theBookingViewDisplay.getBounds());
                theBookingViewDisplay.setVisible(false);
            } else if (theCabProgressViewDisplay.isShowing()) {
                theMainViewDisplay.setBounds(theCabProgressViewDisplay.getBounds());
                theCabProgressViewDisplay.setVisible(false);
                theCabProgressViewDisplay.cabNotOnWayLabel.setVisible(false);
                theCabProgressViewDisplay.cabOnWayLabel.setVisible(false);
            }

            // Reset Payment Fields
            thePaymentViewDisplay.addPeopleTextField.setText("");
            thePaymentViewDisplay.bookingIDTextField.setText("");
            thePaymentViewDisplay.usePointsTextField.setText("");
            thePaymentViewDisplay.calculatedPriceLabel.setText("Cost: ");
            // Reset Booking Fields
            theBookingViewDisplay.dateOfPickupTextField.setText("");
            theBookingViewDisplay.detailsTextArea.setText("");
            theBookingViewDisplay.dropOffLocationTextField.setText("");
            theBookingViewDisplay.nameTextFiled.setText("");
            theBookingViewDisplay.pickUpLocationTextField.setText("");
            // Reset Manage Booking Fields
            theManageBookingViewDisplay.bookingIDTextField.setText("");
            theManageBookingViewDisplay.dateOfPickupTextField.setText("");
            theManageBookingViewDisplay.detailsTextArea.setText("");
            theManageBookingViewDisplay.dropOffLocationTextField.setText("");
            theManageBookingViewDisplay.nameTextFiled.setText("");
            theManageBookingViewDisplay.pickUpLocationTextField.setText("");
            // Reset Feedback Fields
            theFeedbackViewDisplay.feedBackContentField.setText("");
            theFeedbackViewDisplay.senderTextField.setText("");
            theFeedbackViewDisplay.subjectTextField.setText("");
            // Reset CabProgress Fields
            theCabProgressViewDisplay.bookingIDTextField.setText("");
        }
    }

    public ModelsAndViewsController(BackendModelSetup aBackend, MainViewDisplay aMainViewDisplay, RegisterViewDisplay aRegisterViewDisplay, LoginViewDisplay aLoginViewDisplay, FeedbackViewDisplay aFeedbackViewDisplay, BookingViewDisplay aBookingViewDisplay, ManageBookingViewDisplay aManageBooking, PaymentViewDisplay aPaymentViewDisplay, LoadingViewDisplay aLoadingViewDisplay, CabProgressViewDisplay aCabProgressViewDisplay) {
        this.theBackendModel = aBackend;
        this.theMainViewDisplay = aMainViewDisplay;
        this.theRegisterViewDisplay = aRegisterViewDisplay;
        this.theLoginViewDisplay = aLoginViewDisplay;
        this.theFeedbackViewDisplay = aFeedbackViewDisplay;
        this.theBookingViewDisplay = aBookingViewDisplay;
        this.theManageBookingViewDisplay = aManageBooking;
        this.thePaymentViewDisplay = aPaymentViewDisplay;
        this.theLoadingViewDisplay = aLoadingViewDisplay;
        this.theCabProgressViewDisplay = aCabProgressViewDisplay;
        this.initController();
    }

    private void initController() {
        this.theRegisterViewDisplay.registerButton.addActionListener(new RegisterAction());
        this.theLoginViewDisplay.loginButton.addActionListener(new LoginAction());
        this.theLoginViewDisplay.registerButton.addActionListener(new ShowRegisterAction());
        this.theMainViewDisplay.backToLogin.addActionListener(new LogoutAction());
        this.theMainViewDisplay.feedbackButton.addActionListener(new ShowFeedbackAction());
        this.theFeedbackViewDisplay.backToMain.addActionListener(new BackToMainAction());
        this.theFeedbackViewDisplay.submitFBButton.addActionListener(new SubmitFeedbackAction());
        this.theMainViewDisplay.bookCabButton.addActionListener(new ShowBookCabAction());
        this.theBookingViewDisplay.backToMain.addActionListener(new BackToMainAction());
        this.theBookingViewDisplay.addBookingButton.addActionListener(new AddBooking());
        this.theManageBookingViewDisplay.backToMain.addActionListener(new BackToMainAction());
        this.theMainViewDisplay.updateCabButton.addActionListener(new ShowManageBookingAction());
        this.theManageBookingViewDisplay.getInfoButton.addActionListener(new GetBookedAction());
        this.theManageBookingViewDisplay.changeBookingButton.addActionListener(new UpdateBookingAction());
        this.theMainViewDisplay.paymentButton.addActionListener(new ShowPayment());
        this.thePaymentViewDisplay.backButton.addActionListener(new BackToMainAction());
        this.thePaymentViewDisplay.calculatePriceButton.addActionListener(new GetCost());
        this.thePaymentViewDisplay.payButton.addActionListener(new AddPointsAndPay());
        this.theRegisterViewDisplay.backToLoginButton.addActionListener(new ShowLoginAction());
        this.theMainViewDisplay.connectAdminButton.addActionListener(new OpenAdminSideAction());
        this.theMainViewDisplay.cabProgress.addActionListener(new showCheckProgress());
        this.theCabProgressViewDisplay.back.addActionListener(new BackToMainAction());
        this.theCabProgressViewDisplay.checkProgress.addActionListener(new checkProgress());
    }
}
