package Transformations;

//imports
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Transformations extends Frame implements ActionListener {

	public enum PositionType {
		Top, Bottom, Left, Right
	}

	int fileCounter = 0;
	static int Width = 800;
	static int Height = 800;
	private int[] x = { 500, 700, 600 };
	private int[] y = { 310, 310, 110 };
	protected int moveLen = 20;
	protected int panelSize = 800;

	public void left() {
		int[] backup = this.x.clone();
		for (int i = 0; i < 3; i++) {
			this.x[i] -= this.moveLen;
			if (this.x[i] < 0) {
				this.x = backup;
				break;
			}
		}
	}

	public void right() {
		int[] backup = this.x.clone();
		for (int i = 0; i < 3; i++) {
			this.x[i] += this.moveLen;
			if (this.x[i] > this.panelSize) {
				this.x = backup;
				break;
			}
		}
	}

	public void up() {
		int[] backup = this.y.clone();
		for (int i = 0; i < 3; i++) {
			this.y[i] -= this.moveLen;
			if (this.y[i] < 60) {
				this.y = backup;
				break;
			}
		}
	}

	public void down() {
		int[] backup = this.y.clone();
		for (int i = 0; i < 3; i++) {
			this.y[i] += this.moveLen;
			if (this.y[i] > this.panelSize) {
				this.y = backup;
				break;
			}
		}
	}

	public void reflectX() {
		/*
		 * Tam ortada iken dönüştürme engellemeye yarar if(!this.getReflectStatus(false,
		 * this.x)) {
		 * 
		 * return this; }
		 */
		for (int i = 0; i < 3; i++) {
			this.x[i] = this.panelSize - this.x[i];
		}

	}

	public void reflectY() {
		for (int i = 0; i < 3; i++) {
			this.y[i] = this.panelSize - this.y[i];
		}

	}

	private boolean getReflectStatus(boolean axis, int[] pos) {
		PositionType positionType = this.getPositionType(axis, pos[0]);
		for (int i = 0; i < 3; i++) {
			if (this.getPositionType(axis, pos[i]) != positionType)
				return false;
		}
		return true;
	}

	// axis==false => x
	// axis==true => y
	private PositionType getPositionType(boolean axis, int pos) {
		if (axis) {
			if (this.panelSize / 2 > pos) {
				return PositionType.Top;
			} else {
				return PositionType.Bottom;
			}
		} else {
			if (this.panelSize / 2 > pos) {
				return PositionType.Left;
			} else {
				return PositionType.Right;
			}
		}
	}

	// main method
	public static void main(String[] args) {
		// macOS'da menübarda açılması için gereken kod
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");

		// nesnenin yaratılması ve görüntülenmesi
		Transformations t = new Transformations();

		t.setSize(800, 800);
		t.setTitle("Transformation");
		t.setVisible(true);
	}

	// Frame içi ve panel, menü ayarlarını veren fonksiyon
	public void createMenuButtonsandPanel() {

		// panel
		Panel myPanel = new Panel();
		myPanel.setBounds(0, 28, Width, 35);
		myPanel.setBackground(Color.black);

		// menübar
		MenuBar myMenubar = new MenuBar();
		Menu myMenu = new Menu("Menu");
		myMenubar.add(myMenu);

		// menu items
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

		// buttons
		Button myTranslateUp = new Button();
		myTranslateUp.setLabel("Translate Up");
		myTranslateUp.addActionListener(this);
		Button myTranslateDown = new Button();
		myTranslateDown.setLabel("Translate Down");
		myTranslateDown.addActionListener(this);
		Button myTranslateLeft = new Button();
		myTranslateLeft.setLabel("Translate Left");
		myTranslateLeft.addActionListener(this);
		Button myTranslateRight = new Button();
		myTranslateRight.setLabel("Translate Right");
		myTranslateRight.addActionListener(this);
		Button myReflectX = new Button();
		myReflectX.setLabel("Reflect X");
		myReflectX.addActionListener(this);
		Button myReflectY = new Button();
		myReflectY.setLabel("Reflect Y");
		myReflectY.addActionListener(this);

		// add panel
		myPanel.add(myTranslateUp);
		myPanel.add(myTranslateDown);
		myPanel.add(myTranslateLeft);
		myPanel.add(myTranslateRight);
		myPanel.add(myReflectX);
		myPanel.add(myReflectY);

		// button işlemlerinin tanımlama başlangıcı
		myTranslateUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				up();
				repaint();
			}
		});

		myTranslateDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				down();
				repaint();

			}
		});

		myTranslateLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				left();
				repaint();
			}
		});

		myTranslateRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				right();
				repaint();
			}
		});

		myReflectX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reflectX();
				repaint();
			}
		});

		myReflectY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reflectY();
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
				defaultOpt();
				repaint();
			}
		});

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// button işlemlerinin tanımalama sonu

		// panelin frame'e eklenmesi
		add(myPanel);
		setLayout(null);
	}

	// Frame ayarları
	public void setMainLayout() {
		setBackground(Color.getHSBColor(0.8f, 0.2f, 0.9f));
		setResizable(false);
		addWindowListener(new myWindow());

	}

	// çizim ayarları
	public void draw(Graphics2D draw2d, Rectangle grm) {
		draw2d.setColor(Color.DARK_GRAY);
		draw2d.fillOval(grm.x, grm.y, grm.width, grm.height);
	}

	// nesnenin çağıracağı fonksiyonlar
	public Transformations() {
		setMainLayout();
		createMenuButtonsandPanel();
	}

	// grafiği default ayarlara çeken fonksiyon
	public void defaultOpt() {
		x = new int[] { 500, 700, 600 };
		y = new int[] { 310, 310, 110 };
	}

	// grafiğin oluşturulmasını sağlayan fonksiyon
	public void paint(Graphics g) {

		g.drawLine(800, 400, 0, 400);
		g.drawLine(400, 800, 400, 0);
		g.fillPolygon(x, y, 3);

	}

	// ekran görüntüsü alınmasını mümkün kılan fonksiyon
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

	// programın kapatılması için
	public class myWindow extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	// eclipse'nin otomatik oluşturduğu kullanılmayan fakat bulunması gereken kodlar
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}