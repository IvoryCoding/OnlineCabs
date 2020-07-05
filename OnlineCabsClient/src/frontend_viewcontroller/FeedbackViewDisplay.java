package frontend_viewcontroller;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FeedbackViewDisplay extends JFrame{
    BackendModelSetup theBackendModel;
    
    JButton submitFBButton;
    JButton backToMain;
    JLabel senderLabel;
    JLabel subjectLabel;
    JTextField senderTextField;
    JTextField subjectTextField;
    JLabel feedBackContentLabel;
    JTextArea feedBackContentField;
    JScrollPane feedBackScrollPane;
    
    public FeedbackViewDisplay(BackendModelSetup aBackend) {
        this.theBackendModel = aBackend;
        this.initComponents();
        setIcon();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(440, 700));
        try {
            String pathName = new File("./src/images/taxiBackground.png").getAbsolutePath();
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(pathName)))));
        } catch (IOException ex) {
            System.out.println("Image not found.");
        }
        
        this.backToMain = new JButton();
        this.backToMain.setText("Back");
        
        this.senderLabel = new JLabel();
        this.senderLabel.setText("Sender: ");
        this.senderTextField = new JTextField();
        
        this.subjectLabel = new JLabel();
        this.subjectLabel.setText("Subject: ");
        this.subjectTextField = new JTextField();
        
        this.feedBackContentLabel = new JLabel();
        this.feedBackContentLabel.setText("Text content");
        
        this.feedBackContentField = new JTextArea();
        this.feedBackScrollPane = new JScrollPane(this.feedBackContentField);
        
        this.submitFBButton = new JButton();
        this.submitFBButton.setText("Submit");

        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());

        GridBagConstraints c;
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.backToMain, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.senderLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        mainDisplayPane.add(this.senderTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.subjectLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipady = 15;
        c.ipadx = 100;
        mainDisplayPane.add(this.subjectTextField, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainDisplayPane.add(this.feedBackContentLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.ipadx = 300;
        c.ipady = 200;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        mainDisplayPane.add(this.feedBackScrollPane, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 10;
        c.ipadx = 10;
        mainDisplayPane.add(this.submitFBButton, c);
        
        this.pack();
    }
    
    private void setIcon() {
        String filePath = new File("./src/images/OnlineCabs2.png").getAbsolutePath(); // 86 characters long
        setIconImage(Toolkit.getDefaultToolkit().getImage(filePath));
    }
}
