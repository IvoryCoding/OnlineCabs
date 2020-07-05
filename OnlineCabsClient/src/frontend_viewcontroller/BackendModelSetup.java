package frontend_viewcontroller;

import backend_models.*;

public class BackendModelSetup {

    CabProgress theCabProgress;
    RegisteredUser theRegisteredUser;
    NewCustomer theNewCustomer;
    Payment thePayment;
    ManageBookings theManageBookings;
    Feedback theFeedback;
    Booking theBooking;
    PointsSystem thePointSystem;
    GraphicPointCounter theGPointCounter;

    public BackendModelSetup() {
        this.theCabProgress = new CabProgress();
        this.theBooking = new Booking();
        this.theManageBookings = new ManageBookings();
        this.theFeedback = new Feedback();
        this.theNewCustomer = new NewCustomer();
        this.thePayment = new Payment();
        this.theRegisteredUser = new RegisteredUser();
        this.thePointSystem = new PointsSystem();
        this.theGPointCounter = new GraphicPointCounter();
    }
}
