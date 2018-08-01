
package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import data.*;

public class ACES extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9034662823359227749L;
	public static JFrame bodyFrame;
	JPanel sampleInfoPanel, bodyPanel, dmInfoPanel, menuPanel, infoPanel, dmPanel, siPanel;
	public static JPanel backgroundPanel;
	static JLabel sampleInfoLabel;
	GridBagConstraints gridConstraints = new GridBagConstraints();

	static JTextArea currFile;
	
	public static JTabbedPane drawingPanel;

	static Menubar mainMenu;
	static JPanel CurrentInfo;
	DataManagement DataM = new DataManagement();
	
	final ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));
	static JScrollPane dmScroll, siScroll;
	
	public static void main(String[] args) {
		new ACES();
		
	}
	
	public ACES(){
		
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ACES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		bodyFrame = new JFrame();
		bodyFrame.setSize(scrWidth, scrHeight);
		bodyFrame.setTitle(" ACES ");
		
		
		
		bodyFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		bodyFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);		
		bodyFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(bodyFrame, 
		            "Are you sure to quit ACES?", "Closing confirmation", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE, icon) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		

		/* backgroundPanel keeps menuPanel and bodyPanel */
		backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new BorderLayout());
		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setPreferredSize(new Dimension(scrWidth-50,25));
	   
		bodyPanel= new JPanel();
		bodyPanel.setLayout(new BorderLayout());
		bodyPanel.add(infoPanel,BorderLayout.NORTH);

		backgroundPanel.add(menuPanel,BorderLayout.NORTH);	
		backgroundPanel.add(bodyPanel);
		
		drawingPanel= new JTabbedPane();	
		
		
		/* dmPanel holds the dmInfoPanel */
		dmPanel = new JPanel();
		dmPanel.setPreferredSize(new Dimension(50,500));
		dmPanel.setLayout(new BorderLayout());
		
		dmInfoPanel =  new ButtonBar(DataM).makeDM();
		dmInfoPanel.setBackground(Color.WHITE);
		
		dmScroll = new JScrollPane(dmInfoPanel);
		dmScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		dmScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dmScroll.setPreferredSize(new Dimension(50,500));
		
		dmPanel.add(dmScroll, BorderLayout.WEST);
		dmPanel.setBackground(Color.WHITE);
		dmScroll.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		bodyPanel.add(dmPanel, BorderLayout.WEST);
		//bodyPanel.add(drawingPanel, BorderLayout.CENTER);
		bodyPanel.add(drawingPanel);

		mainMenu = new Menubar(DataM);
		bodyFrame.setJMenuBar(mainMenu.getMenu());
		menuPanel.add(mainMenu, BorderLayout.CENTER);
	
		currFile = new JTextArea();
	    infoPanel.add(currFile);
	    currFile.setText("Current File:");
	    currFile.setBackground(new Color(255,255,110));
	   
		
		gridConstraints.insets = new Insets(10,15,4,1);
		
		bodyFrame.add(backgroundPanel,BorderLayout.CENTER);
		bodyFrame.setIconImage(createImage("/resources/logo_mlv_small.png").getImage());
		bodyFrame.setVisible(true);
	}
	
	private ImageIcon createImage(String path) {
		
		return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(path));
	}

	
}
