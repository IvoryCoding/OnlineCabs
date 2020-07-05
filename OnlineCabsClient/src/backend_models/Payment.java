package backend_models;

import frontend_viewcontroller.LoadingViewDisplay;
import frontend_viewcontroller.MainViewDisplay;
import frontend_viewcontroller.PaymentViewDisplay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Payment extends JPanel {

    PaymentViewDisplay thePaymentViewDisplay;
    MainViewDisplay theMainViewDisplay;
    LoadingViewDisplay theLoadingViewDisplay;

    // API code for google maps distance matrix api
    private static final String API_KEY = "AIzaSyBaCAr5l4Gec4D4TkuzWG8hJPPS5Oo0FtY";

    double costPerKM = 2.75;
    public int totalCost;
    public int displayTotalCost = 0;
    public double distanceToTravel;
    public int totalPoints;

    public void pay() {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://localhost/checkouts"));
        } catch (IOException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error Code: 1003 | " + ex);
        }
    }

    public void getDistance(String startPoint, String endPoint) throws IOException {
        int num = 0;
        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + startPoint + "&destinations=" + endPoint + "&key=" + API_KEY);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            num++;
            if (num == 9) {
                String distance = line;
                distance = distance.replaceAll("[^\\d\\.]", "");
                distanceToTravel = (1.60934 * (Double.parseDouble(distance)));
                totalCost = (int) ((costPerKM * distanceToTravel) + 6.45);
                displayTotalCost = totalCost;
            }
        }
    }

    public void addPoints(int points) {
        while (totalCost >= 10) {
            points = points + 10;
            totalCost = totalCost - 10;
        }
        totalPoints = points;
    }

    public void minusPoints(int points) {
        while (points >= 10) {
            if (points >= 10000) {
                points -= 10000;
                totalCost -= 1000;
            } else if (points >= 1000) {
                points = points - 100;
                totalCost = totalCost - 100;
            } else if (points >= 100) {
                points -= 100;
                totalCost -= 10;
            } else if (points >= 10) {
                points -= 10;
                totalCost -= 1;
            }
        }
        if (totalCost <= 0) {
            totalCost = 0;
        }
        totalPoints = points;
        displayTotalCost = totalCost;
    }
}
