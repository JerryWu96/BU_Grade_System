package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
Author: Ziqi Tan
*/
public class MainFrame extends JFrame {
	
	// Singleton Pattern
	private static MainFrame mainFrame = new MainFrame();
	
	private LoginPanel loginPanel;		
	private BufferedImage bgImage;
	
	private int frameWidth;
	private int frameHeight;
	
	public static MainFrame getInstance() {
		return mainFrame;
	}
		
	private MainFrame() {
		System.out.println("A main frame is creating.");
		setTitle("Grading System");	
		
			
		bgImage = getBgImage("./image/bg-image.jpg");		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		
		ImageIcon bgImageIcon = new ImageIcon(bgImage);
		// If the image is too big for the screen
		bgImageIcon = resizeImageIcon(bgImageIcon, screenWidth, screenHeight);
		
		frameWidth = bgImageIcon.getIconWidth();
		frameHeight = bgImageIcon.getIconHeight();
		setSize(frameWidth, frameHeight);
		
        // Set a background for JFrame
        setContentPane(new JLabel(bgImageIcon));
        setLayout(new BorderLayout());
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         		
		setLocationRelativeTo(null);   // this will center your frame
		
		loginPanel = new LoginPanel();
		add(loginPanel, "North");
		setLoginPanel();
		
		setVisible(true);				
	}
	
	/**
	 * Method: getBgImage
	 * */
	private BufferedImage getBgImage(String imagePath) {
		BufferedImage bgImage = null;
		try {
			bgImage = ImageIO.read(new File(imagePath));
		}
		catch( Exception error ) {
			error.printStackTrace();
		}
		return bgImage;
	}
	
	/**
	 * Method: resizeBgImage
	 * Function: 
	 * 		If the image is too big for the screen,
	 * 		resize it.
	 * */
	private ImageIcon resizeImageIcon(ImageIcon icon, int targetWidth, int targetHeight ) {
		
		int newWidth = icon.getIconWidth();
		int newHeight = icon.getIconHeight();
		
		// keep the width-height ratio
		if( icon.getIconWidth() > targetWidth ) {
			newWidth = targetWidth;
			newHeight = newWidth * icon.getIconHeight() / icon.getIconWidth();
		}
		if( newHeight > targetHeight ) {
			newHeight = targetHeight;
			newWidth = newHeight * icon.getIconWidth() / icon.getIconHeight();
		}
		
		return new ImageIcon(icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));
	}
	
	/**
	 * getter()
	 * */
	public int getFrameWidth() {
		return frameWidth;
	}
	
	public int getFrameHeight() {
		return frameHeight;
	}
	
	/**
	 * setter()
	 * */
	public void setLoginPanel() {
		loginPanel.setEnabled(true);
		loginPanel.setVisible(true);
		System.out.println("setLoginPanel");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame.getInstance();
	}

}
