package frontend_viewcontroller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class LoadingViewDisplay extends JFrame {

    BackendModelSetup theBackendModel;

    JLabel loadingImgLabel;

    public LoadingViewDisplay(BackendModelSetup aBackend) {
        this.theBackendModel = aBackend;
        this.initComponents();
        setIcon();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(440, 700));
        String pathName = new File("./src/images/taxiLoadingPaper.gif").getAbsolutePath();
        this.setContentPane(new JLabel(new ImageIcon(pathName)));

        this.loadingImgLabel = new JLabel();

        Container registerDisplayPane = this.getContentPane();
        registerDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        registerDisplayPane.add(this.loadingImgLabel, c);

        this.pack();
    }

    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}
