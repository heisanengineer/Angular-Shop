package demoe;

import javax.swing.*;
import java.awt.*;

public class Gradient extends JPanel {
    
	Point onePo;
	Point twoPo;
	Point threePo;
	Point fourPo;
	GradientPaint diaPaint;
	GradientPaint cycPaint;
	
	public void Gradient(int a) {
		
		if(a==0) {
			onePo = new Point(30, 30);
			twoPo = new Point(340, 540);
			diaPaint = new GradientPaint(onePo, Color.MAGENTA, twoPo, Color.CYAN);
		}
		else if(a==1) {
			threePo = new Point(430, 30);
			fourPo = new Point(430, 100);
			cycPaint = new GradientPaint(threePo, Color.BLACK, fourPo, Color.WHITE, true);
		}
		
	}

	
	public void paint(Graphics g) {
		
        Graphics2D graphics2 = (Graphics2D) g;
        
        Gradient(0);
        graphics2.setPaint(diaPaint);
        graphics2.fillRect(30, 30 , 340, 540);
        

        Gradient(1);
        graphics2.setPaint(cycPaint);
        graphics2.fillRect(430, 30 , 340, 540);
        
    }

    public static void main(String[] args) {
    	Gradient myGrad = new Gradient();
        JFrame myApp = new JFrame();
        
        myApp.add(myGrad);
        myApp.setSize(850, 650);
        myApp.setVisible(true);
        myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}