package rotations;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Rotations extends Frame implements ActionListener {

	int fileCounter = 0;
	static int Width = 800;
	static int Height = 800;
	public int[] cubeX = { 500, 700, 700, 500 };
	public int[] cubeY = { 200, 200, 300, 300 };
	public int location = 1;

	public static void main(String[] args) {

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");

		Rotations t = new Rotations();

		t.setSize(800, 800);
		t.setTitle("Rotations");
		t.setVisible(true);
	}

	public void changeLocation(boolean rotate) {
		location = (rotate) ? location + 1 : location - 1;
		location = location % 4;
	}

	public void createMenuButtonsandPanel() {

		Panel myPanel = new Panel();
		myPanel.setBounds(0, 28, Width, 35);
		myPanel.setBackground(Color.black);

		MenuBar myMenubar = new MenuBar();
		Menu myMenu = new Menu("Menu");
		myMenubar.add(myMenu);

		MenuItem myDefault = new MenuItem("Set Default");
		myMenu.add(myDefault);
		MenuItem mySaveGraphic = new MenuItem("Save Graphic");
		myMenu.add(mySaveGraphic);
		mySaveGraphic.addActionListener(this);
		MenuItem exitBtn = new MenuItem("Exit");
		myMenu.add(exitBtn);
		exitBtn.addActionListener(this);
		myMenu.addActionListener(this);
		setMenuBar(myMenubar);

		Button LBC = new Button();
		LBC.setLabel("Left Bottom - Clockwise");
		LBC.addActionListener(this);
		Button OC = new Button();
		OC.setLabel("Origin - Clockwise");
		OC.addActionListener(this);
		Button OCC = new Button();
		OCC.setLabel("Origin - C.Clockwise");
		OCC.addActionListener(this);

		myPanel.add(LBC);
		myPanel.add(OC);
		myPanel.add(OCC);

		LBC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (location) {
				case 0:
					cubeX[3] = cubeX[2];
					cubeY[3] = cubeY[2];
					cubeX[0] = cubeX[3];
					cubeY[0] = cubeY[3] - 100;
					cubeX[1] = cubeX[0] + 200;
					cubeY[1] = cubeY[0];
					cubeX[2] = cubeX[3] + 200;
					cubeY[2] = cubeY[3];
					break;
				case 1:
					cubeX[0] = cubeX[3];
					cubeY[0] = cubeY[3];
					cubeX[1] = cubeX[0] + 100;
					cubeY[1] = cubeY[0];
					cubeX[2] = cubeX[1];
					cubeY[2] = cubeY[1] + 200;
					cubeX[3] = cubeX[0];
					cubeY[3] = cubeY[0] + 200;
					break;
				case 2:
					cubeX[1] = cubeX[0];
					cubeY[1] = cubeY[0];
					cubeX[2] = cubeX[1];
					cubeY[2] = cubeY[1] + 100;
					cubeX[3] = cubeX[2] - 200;
					cubeY[3] = cubeY[2];
					cubeX[0] = cubeX[1] - 200;
					cubeY[0] = cubeY[1];
					break;
				case 3:
					cubeX[2] = cubeX[1];
					cubeY[2] = cubeY[1];
					cubeX[3] = cubeX[2] - 100;
					cubeY[3] = cubeY[2];
					cubeX[0] = cubeX[3];
					cubeY[0] = cubeY[3] - 200;
					cubeX[1] = cubeX[2];
					cubeY[1] = cubeY[2] - 200;
					break;
				default:
					break;

				}
				changeLocation(true);
				repaint();
			}
		});

		OC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] backup;
				backup = cubeX.clone();
				cubeX[0] = 800 - cubeY[3];
				cubeX[1] = 800 - cubeY[0];
				cubeX[2] = 800 - cubeY[1];
				cubeX[3] = 800 - cubeY[2];
				cubeY[0] = backup[3];
				cubeY[1] = backup[0];
				cubeY[2] = backup[1];
				cubeY[3] = backup[2];
				changeLocation(true);
				repaint();
			}
		});

		OCC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] backup;
				backup = cubeY.clone();
				cubeY[0] = 800 - cubeX[3];
				cubeY[1] = 800 - cubeX[0];
				cubeY[2] = 800 - cubeX[1];
				cubeY[3] = 800 - cubeX[2];
				cubeX[0] = backup[3];
				cubeX[1] = backup[0];
				cubeX[2] = backup[1];
				cubeX[3] = backup[2];
				changeLocation(false);
				repaint();
			}
		});

		mySaveGraphic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myGraphicSS();
			}
		});

		myDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeX = new int[] { 500, 700, 700, 500 };
				cubeY = new int[] { 200, 200, 300, 300 };
				location = 1;
				repaint();
			}
		});

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(myPanel);
		setLayout(null);
	}

	public void paint(Graphics g) {
		g.drawLine(800, 400, 0, 400);
		g.drawLine(400, 800, 400, 0);
		g.fillPolygon(cubeX, cubeY, 4);
		g.setColor(Color.GRAY);
	}

	public void setMainLayout() {
		setBackground(Color.getHSBColor(0.4f, 0.3f, 0.9f));
		setResizable(false);
		addWindowListener(new myWindow());
	}

	public Rotations() {
		setMainLayout();
		createMenuButtonsandPanel();
	}

	public class myWindow extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	void myGraphicSS() {

		try {
			Robot myRobot = new Robot();
			Rectangle myRectangle = new Rectangle(800, 800);
			BufferedImage bufferedImage = myRobot.createScreenCapture(myRectangle);
			File myFile = new File("graphicScreenShot" + fileCounter + ".png");
			boolean status = ImageIO.write(bufferedImage, "png", myFile);
			fileCounter++;
		} catch (AWTException | IOException ex) {
			System.err.println(ex);
		}
	}
}