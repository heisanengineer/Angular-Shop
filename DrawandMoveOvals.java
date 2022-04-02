package homework3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.w3c.dom.events.MouseEvent;

public class c1811291_question1 extends JFrame implements ActionListener, MouseListener {

	static int t = 100;
	static int r = 100;
	int mousex;
	int mousey;
	
	static JMenuBar mb;

	static JMenu x;

	static JMenuItem m1;

	static JFrame f;

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");
		c1811291_question1 A = new c1811291_question1(1);
	}
	
	public void paint(Graphics g) {
		g.drawOval(t, r, 100, 100);
		t = t;
		r = r;
		g.drawOval(t, r, 200, 200);
	}
	
	public void mouseClicked(java.awt.event.MouseEvent m) {
		mousex = m.getPoint().x;
        mousey = m.getPoint().y; 
        repaint(mousex,mousey,500,500);
	}

	public void actionPerformed(ActionEvent ae) {

	}

	public c1811291_question1(int a) {

		f = new JFrame("c1811291");

		mb = new JMenuBar();

		x = new JMenu("File");

		m1 = new JMenuItem("Exit");

		x.add(m1);

		mb.add(x);

		f.setJMenuBar(mb);

		f.setSize(500, 500);

		f.setVisible(true);

		m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		switch (a) {
		case 1:
			f.setTitle("Lab 3a");
			break;
		case 2:
			f.setTitle("Lab 3b");
			break;
		default:
		}
		
		setSize(500,500);
		setVisible(true);
	}
	

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
