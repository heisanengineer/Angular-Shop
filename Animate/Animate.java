package Animate;

import java.awt.Button;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animate extends JPanel implements ActionListener {
	Timer timer;
	Point onePo;
	Point twoPo;
	GradientPaint diaPaint;

	private final double myPoints[][] = { { 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 }, { 200, 85 }, { 150, 125 },
			{ 160, 190 }, { 100, 150 }, { 40, 190 }, { 50, 125 }, { 0, 85 } };
	double myScaletor = 0.1;
	private Point2D pt = new Point2D.Float(0, 0);;

	private int deltaPoint;

	public Animate() {
		timer = new Timer(10, this);
		timer.setInitialDelay(100);
		timer.start();
		pt = new Point2D.Float(0, 0);
		deltaPoint = 4;
	}

	public void Gradient(int a) {

		if (a == 0) {
			Color c1 = Color.getHSBColor(0.75f, 0.8f, 0.3f);
			Color c2 = Color.getHSBColor(0.65f, 0.8f, 0.5f);
			onePo = new Point(0, 200);
			twoPo = new Point(0, 400);
			diaPaint = new GradientPaint(onePo, c1, twoPo, c2);
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Gradient(0);
		g2d.setPaint(diaPaint);
		g2d.fillRect(0, 0, 400, 400);

		GeneralPath path = new GeneralPath();
		path.moveTo(myPoints[0][0] + pt.getX(), myPoints[0][1] + pt.getY());
		for (int i = 1; i < myPoints.length; i++) {
			path.lineTo(myPoints[i][0] + pt.getX(), myPoints[i][1] + pt.getY());
		}
		path.closePath();

		g2d.scale(myScaletor, myScaletor);
		g2d.setColor(Color.yellow);
		g2d.fill(path);

	}

	private int getXMax() {
		int myMax = (int) myPoints[0][0];
		for (int i = 0; i < myPoints.length; i++) {
			myMax = (int) Math.max(myMax, myPoints[i][0]);
		}
		return myMax;
	}

	private int getXMin() {
		int myMin = (int) myPoints[0][0];
		for (int i = 0; i < myPoints.length; i++) {
			myMin = (int) Math.min(myMin, myPoints[i][0]);
		}
		return myMin;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		int x = (int) pt.getX();
		int myMaxPoint = getXMax();
		int myMinPoint = getXMin();
		if (x + myMaxPoint + this.getWidth() / 10 > this.getWidth() || x + myMinPoint < 0)
			deltaPoint = -deltaPoint;
		pt.setLocation(pt.getX() + deltaPoint, pt.getY() + deltaPoint);

		myScaletor += (double) deltaPoint / 5 / 100d;
		if (myScaletor < 0) {
			myScaletor = deltaPoint;
		} else if (myScaletor > 1) {
			myScaletor = 1.0;
		}

		repaint();
	}

	public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.add(new Animate());
		myFrame.setSize(400, 400);
		myFrame.setVisible(true);
	}

}
