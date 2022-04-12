package Transformations2;

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

public class Transformations2 extends Frame implements ActionListener {

	// gereken değişken vs tanımları

	int fileCounter = 0;
	static int Width = 800;
	static int Height = 800;
	public int[] cubeX = { 400, 600, 600, 400 };
	public int[] cubeY = { 200, 200, 400, 400 };

	// main method
	public static void main(String[] args) {
		// macOS'da menübarda açılması için gereken kod
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");

		// nesnenin yaratılması ve görüntülenmesi
		Transformations2 t = new Transformations2();

		t.setSize(800, 800);
		t.setTitle("TransformationsContinued");
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
		Button myShearInX = new Button();
		myShearInX.setLabel("Shear in X");
		myShearInX.addActionListener(this);
		Button myShearInY = new Button();
		myShearInY.setLabel("Shear in Y");
		myShearInY.addActionListener(this);
		Button myStretchInX = new Button();
		myStretchInX.setLabel("Stretch in X");
		myStretchInX.addActionListener(this);
		Button myStretchInY = new Button();
		myStretchInY.setLabel("Stretch in Y");
		myStretchInY.addActionListener(this);
		Button myScaleUp = new Button();
		myScaleUp.setLabel("Scale Up");
		myScaleUp.addActionListener(this);
		Button myScaleDown = new Button();
		myScaleDown.setLabel("Scale Down");
		myScaleDown.addActionListener(this);

		// add panel
		myPanel.add(myShearInX);
		myPanel.add(myShearInY);
		myPanel.add(myStretchInX);
		myPanel.add(myStretchInY);
		myPanel.add(myScaleUp);
		myPanel.add(myScaleDown);

		// button işlemlerinin tanımlama başlangıcı
		myShearInX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeX[0] += 10;
				cubeX[1] += 10;
				repaint();
			}
		});

		myShearInY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeY[2] -= 10;
				cubeY[1] -= 10;
				repaint();
			}
		});

		myStretchInX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeX[1] += 10;
				cubeX[2] += 10;
				repaint();
			}
		});

		myStretchInY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeY[1] -= 10;
				cubeY[0] -= 10;
				repaint();
			}
		});

		myScaleUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeY[1] -= 10;
				cubeY[0] -= 10;
				cubeX[1] += 10;
				cubeX[2] += 10;
				repaint();
			}
		});

		myScaleDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cubeY[1] += 10;
				cubeY[0] += 10;
				cubeX[1] -= 10;
				cubeX[2] -= 10;
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
				cubeX = new int[] { 400, 600, 600, 400 };
				cubeY = new int[] { 200, 200, 400, 400 };
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

	// grafiğin oluşturulmasını sağlayan fonksiyon
	public void paint(Graphics g) {
		g.drawLine(800, 400, 0, 400);
		g.drawLine(400, 800, 400, 0);
		g.fillPolygon(cubeX, cubeY, 4);
		g.setColor(Color.DARK_GRAY);
		}

	// Frame ayarları
	public void setMainLayout() {
		setBackground(Color.getHSBColor(0.55f, 0.2f, 0.9f));
		setResizable(false);
		addWindowListener(new myWindow());
	}

	// çizim ayarları

	// nesnenin çağıracağı fonksiyonlar
	public Transformations2() {
		setMainLayout();
		createMenuButtonsandPanel();
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
}