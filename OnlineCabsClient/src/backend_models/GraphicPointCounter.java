package backend_models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GraphicPointCounter extends JPanel{
    int percent;
    public void UpdateProgress(int points_value) {
        if (points_value >= 0 && points_value < 200) {
            percent = 10;
        } else if (points_value >= 200 && points_value < 400) {
            percent = 20;
        }  else if (points_value >= 400 && points_value < 600) {
            percent = 30;
        } else if (points_value >= 600 && points_value < 800) {
            percent = 40;
        } else if (points_value >= 800 && points_value < 1000) {
            percent = 50;
        } else if (points_value >= 1000 && points_value < 1200) {
            percent = 60;
        }  else if (points_value >= 1200 && points_value < 1400) {
            percent = 70;
        } else if (points_value >= 1400 && points_value < 1600) {
            percent = 80;
        } else if (points_value >= 1600 && points_value < 1800) {
            percent = 90;
        } else if (points_value >= 2000) {
            percent = 100;
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.translate(200, 200);
        g2.rotate(Math.toRadians(270));
        Arc2D arc = new Arc2D.Float(Arc2D.PIE);
        Ellipse2D circle = new Ellipse2D.Float(0, 0, 190, 190);
        circle.setFrameFromCenter(new Point(0, 0), new Point(190, 190));
        arc.setFrameFromCenter(new Point(0, 0), new Point(200, 200));
        arc.setAngleStart(1);
        arc.setAngleExtent(-(percent)*3.6);
        g2.setColor(Color.black);
        g2.draw(arc);
        g2.fill(arc);
        g2.setColor(new Color (240, 189, 10));
        g2.draw(circle);
        g2.fill(circle);
    }
}
