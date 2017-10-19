package Frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class About extends JDialog{
	
	JFrame aboutFrame;
	ImageIcon aboutImage = new ImageIcon(getClass().getResource("/resources/about.png"));
	
	public About(){
		
		
		JLabel panel = new JLabel(aboutImage);
		aboutFrame = new JFrame();
		aboutFrame.setSize(175, 178);
		aboutFrame.setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (aboutFrame.getWidth() / 2);
		int yPos = (dim.height / 2) - (aboutFrame.getHeight() / 2);
		aboutFrame.setLocation(xPos,yPos);
		aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aboutFrame.setTitle("About ACES");
		aboutFrame.add(panel);
		aboutFrame.pack();
		aboutFrame.setVisible(true);
		
	}
}
