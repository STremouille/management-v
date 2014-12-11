package start;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.LoginFrame;
import view.MainView;
import controller.LoginController;
import controller.MainController;
import model.Factory;
import model.FactorySQLUtility;


public class ManagementVinci {

	public static final String fontName = "Arial";
	public static int fontSize=15;
	public static int fontSizeTitle=20;
	
	public static void main(String[] args) {
		
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			loadFont();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Factory factory = new Factory();
		MainView view = new MainView(factory);
		MainController controller = new MainController(factory, view);
		view.setFullScreen();
		//view.setVisible(true);
		
		LoginFrame lf = new LoginFrame();
		LoginController lc = new LoginController(factory, lf,view);
		lf.setVisible(true);
	}
	
	public static void loadFont(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//InputStream in = this.getClass().getResourceAsStream("lib"+File.separatorChar+"font"+"coolvetica.ttf");
		try {
			Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, new File("src"+File.separator+"lib"+File.separator+"font"+File.separator+"Drakoheart Revofit Solid.ttf"));
			//Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, new File("src"+File.separator+"lib"+File.separator+"font"+File.separator+"GeosansLight.ttf"));
			ge.registerFont(fontRaw);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


