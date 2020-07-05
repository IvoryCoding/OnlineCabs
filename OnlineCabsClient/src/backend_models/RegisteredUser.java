package backend_models;

import frontend_viewcontroller.BackendModelSetup;
import frontend_viewcontroller.LoginViewDisplay;
import frontend_viewcontroller.MainViewDisplay;
import frontend_viewcontroller.RegisterViewDisplay;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisteredUser {

    public String uid;
    public String pwd;
    public String encryptedPasscode;

    BackendModelSetup theBackendModel;
    MainViewDisplay theMainViewDisplay;
    RegisterViewDisplay theRegisterViewDisplay;
    LoginViewDisplay theLoginViewDisplay;

    public void update(String username, String Password) {
        // Get the user info and set uid and pwd
    }

    public void edit() {
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
