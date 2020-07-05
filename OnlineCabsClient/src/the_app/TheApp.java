/**
 * There is essentially nothing in this file for students to edit,
 * especially if you are creating single window apps
 * (which is what you probably should be doing).
 */

package the_app;

import frontend_viewcontroller.*;

/**
 * Sets up the model-view-controller classes of TheApp.
 * 
 * @author cheng
 */
public class TheApp implements Runnable {

    @Override
    public void run() {
        BackendModelSetup theBackendModel = new BackendModelSetup();
        MainViewDisplay theMainViewDisplay = new MainViewDisplay(theBackendModel);
        RegisterViewDisplay theRegisterViewDisplay = new RegisterViewDisplay(theBackendModel);
        LoginViewDisplay theLoginViewDisplay = new LoginViewDisplay(theBackendModel);
        FeedbackViewDisplay theFeedbackViewDisplay = new FeedbackViewDisplay(theBackendModel);
        BookingViewDisplay theBookingViewDisplay = new BookingViewDisplay(theBackendModel);
        ManageBookingViewDisplay theManageBookingViewDisplay = new ManageBookingViewDisplay(theBackendModel);
        PaymentViewDisplay thePaymentViewDisplay = new PaymentViewDisplay(theBackendModel);
        LoadingViewDisplay theLoadingViewDisplay = new LoadingViewDisplay(theBackendModel);
        CabProgressViewDisplay theCabProgressViewDisplay = new CabProgressViewDisplay(theBackendModel);
        ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theBackendModel, theMainViewDisplay, theRegisterViewDisplay, theLoginViewDisplay, theFeedbackViewDisplay, theBookingViewDisplay, theManageBookingViewDisplay, thePaymentViewDisplay, theLoadingViewDisplay, theCabProgressViewDisplay);

        theLoginViewDisplay.setVisible(true);
    }
}