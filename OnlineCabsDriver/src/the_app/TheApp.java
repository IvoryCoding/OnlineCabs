package the_app;

import frontend_viewcontroller.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheApp implements Runnable {

    @Override
    public void run() {
        BackendModelSetup theBackendModel = new BackendModelSetup();
        MainViewDisplay theMainViewDisplay = null;
        try {
            theMainViewDisplay = new MainViewDisplay(theBackendModel);
        } catch (SQLException ex) {
            Logger.getLogger(TheApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theBackendModel, theMainViewDisplay);

        theMainViewDisplay.setVisible(true);
    }
}