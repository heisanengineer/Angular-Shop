//c1811291
//Emre Can Aşık
//Bilgisayar Mühendisliği IV

// ödeve ek olarak ekranda çizilen grafiğin "programın açıldığı yerde konumunun değişmemesi şartıyla
// ekran görüntüsünü kaydetmesini sağladım. Kaydedilen görüntü eclipse workspace'de proje klasörüne 
// Kaydediliyor. Ve exit butonu ekledim, bunları macOS için menübarda görüntülemesini, 
//windows ise menü çubuğunda görüntülenmesini sağladım.

package c1811291_mickey;

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

public class c1811291_mickey extends Frame implements ActionListener, MouseListener {

	//gereken değişken vs tanımları
	int mouseX;
	int mouseY;
	int x = 250;
	int y = 300;
	int dx = 300;
	int dy = 300;
	int fileCounter = 0;
	int clickCount = 0;
	boolean handler = false;
	List<Point2D.Double> dots;
	static int Width = 800;
	static int Height = 800;

	//Frame içi ve panel, menü ayarlarını veren fonksiyon
	public void createMenuButtonsandPanel() {

		//panel
		Panel myPanel = new Panel();
		myPanel.setBounds(0, 50, Width, 35);
		myPanel.setBackground(Color.black);

		//menübar
		MenuBar myMenubar = new MenuBar();
		Menu myMenu = new Menu("Menu");
		myMenubar.add(myMenu);

		//menu items
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

		//buttons
		Button mySmallBtn = new Button();
		mySmallBtn.setLabel("Smaller");
		mySmallBtn.addActionListener(this);
		Button myBigBtn = new Button();
		myBigBtn.setLabel("Bigger");
		myBigBtn.addActionListener(this);
		myPanel.add(mySmallBtn);
		myPanel.add(myBigBtn);

		//button işlemlerinin tanımlama başlangıcı
		mySmallBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smaller();
			}
		});

		myBigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigger();
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
			}
		});

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//button işlemlerinin tanımalama sonu

		//panelin frame'e eklenmesi
		add(myPanel);
		setLayout(null);
	}

	//Frame ayarları
	public void setMainLayout() {
		setTitle("Mickey Assignment");
		setBackground(Color.red);
		setResizable(false);
		addWindowListener(new myWindow());
		addMouseListener(this);
	}

	//çizim ayarları
	public void draw(Graphics2D draw2d, Rectangle grm) {
		draw2d.setColor(Color.black);
		draw2d.fillOval(grm.x, grm.y, grm.width, grm.height);
	}

	//nesnenin çağıracağı fonksiyonlar
	public c1811291_mickey() {
		setMainLayout();
		createMenuButtonsandPanel();
		dots = new ArrayList<Point2D.Double>();
	}

	//grafiği küçültmeyi sağlayan fonksiyon
	public void smaller() {
		this.dy = this.dy - 40;
		this.dx = this.dx - 40;
		repaint();
	}

	//grafiği büyütmeyi sağlayan fonksiyon
	public void bigger() {
		this.dy = this.dy + 40;
		this.dx = this.dx + 40;
		repaint();
	}

	// grafiği default ayarlara çeken fonksiyon
	public void defaultOpt() {
		handler = false;
		this.dx = 300;
		this.dy = 300;
		repaint();
	}

	//mouse tıklandığında yapılacak işlemler
	public void process() {
		handler = true;
		Point2D.Double drawPoint = new Point2D.Double(mouseX, mouseY);
		dots.add(drawPoint);
		clickCount++;
		if (clickCount > 1) {
			dots.remove(0);
		}
		repaint();
	}

	//grafiğin oluşturulmasını sağlayan fonksiyon
	public void paint(Graphics g) {
		super.paint(g);
		int x, y;
		Graphics2D draw2d;
		draw2d = (Graphics2D) g;
		Rectangle r1;
		Rectangle r2;
		if (!handler) {
			r1 = new Rectangle(this.x, this.y, this.dx, this.dy);
			draw(draw2d, r1);
			x = r1.width / 2;
			y = r1.height / 2;
			r2 = new Rectangle(r1.x, r1.y, x, y);
			r2.translate(-x / 2, -y / 2);
			draw(draw2d, r2);
			r2.translate(x * 2, 0);
			draw(draw2d, r2);
		} else if (handler){
			for (Point2D.Double myPoint : dots) {
				r1 = new Rectangle((int) (myPoint.x - this.dx / 2), (int) (myPoint.y - this.dy / 2), this.dx, this.dy);
				draw(draw2d, r1);
				x = r1.width / 2;
				y = r1.height / 2;
				r2 = new Rectangle(r1.x, r1.y, x, y);
				r2.translate(-x / 2, -y / 2);
				draw(draw2d, r2);
				r2.translate(x * 2, 0);
				draw(draw2d, r2);
			}
		}
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

	//programın kapatılması için
	public class myWindow extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	//mouse tıklandığında koordinat bilgilerinin alınması ve grafiğin yeniden oluşturulması
	@Override
	public void mouseClicked(MouseEvent mouseClickEvent) {
		mouseX = mouseClickEvent.getX();
		mouseY = mouseClickEvent.getY();
		process();
	}

	//main method
	public static void main(String[] args) {
		//macOS'da menübarda açılması için gereken kod
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");

		//nesnenin yaratılması ve görüntülenmesi
		c1811291_mickey myMickey = new c1811291_mickey();
		myMickey.setSize(Width, Height);
		myMickey.setVisible(true);
	}

	//eclipse'nin otomatik oluşturduğu kullanılmayan fakat bulunması gereken kodlar
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}