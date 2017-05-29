/*  
    PiiL: Pathway Interactive vIsualization tooL
    Copyright (C) 2015  Behrooz Torabi Moghadam

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package Frame;

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
import javax.swing.JTextArea;



public class ACES extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9034662823359227749L;
	public static JFrame bodyFrame;
	JPanel sampleInfoPanel, bodyPanel, dmInfoPanel, menuPanel, dmPanel, siPanel, drawingPanel;
	public static JPanel backgroundPanel;
	static JLabel sampleInfoLabel;
	GridBagConstraints gridConstraints = new GridBagConstraints();
	
	public static JTextArea ta; // show the content
	public static JScrollPane sp; 
		
	static Menubar mainMenu;
	DataManagement DataM = new DataManagement();;
	
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
		bodyPanel= new JPanel();
		bodyPanel.setLayout(new BorderLayout());
		backgroundPanel.add(menuPanel,BorderLayout.NORTH);	
		backgroundPanel.add(bodyPanel);
		drawingPanel= new JPanel();
		drawingPanel.setLayout(new BorderLayout());
		
		/* dmPanel holds the dmInfoPanel */
		dmPanel = new JPanel();
		dmPanel.setPreferredSize(new Dimension(150,500));
		dmPanel.setLayout(new BorderLayout());
		
		dmInfoPanel =  new ButtonBar(DataM).makeDM();
		dmInfoPanel.setBackground(new Color(200,200,200));
		
		dmScroll = new JScrollPane(dmInfoPanel);
		dmScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		dmScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dmScroll.setPreferredSize(new Dimension(150,500));
		
		dmPanel.add(dmScroll, BorderLayout.WEST);
		dmPanel.setBackground(new Color(185,185,185));
		dmScroll.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		bodyPanel.add(dmPanel, BorderLayout.WEST);
		bodyPanel.add(drawingPanel, BorderLayout.CENTER);
		
		/*siPanel = new JPanel();
		siPanel.setPreferredSize(new Dimension(150,500));
		siPanel.setLayout(new BorderLayout());
		
		sampleInfoPanel =  new ButtonBar().makeSI();
		sampleInfoPanel.setBackground(new Color(200,200,200));
		
		siScroll = new JScrollPane(sampleInfoLabel);
		siScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		siScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		siScroll.setPreferredSize(new Dimension(150,500));
		
		siPanel.add(siScroll, BorderLayout.EAST);
		siPanel.setBackground(new Color(185,185,185));
		siScroll.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		bodyPanel.add(siPanel, BorderLayout.EAST);*/
		
		
		
		mainMenu = new Menubar(DataM);
		bodyFrame.setJMenuBar(mainMenu.getMenu());
		menuPanel.add(mainMenu, BorderLayout.NORTH);
	
		
		ta = new JTextArea();
		sp = new JScrollPane(ta);
		
		drawingPanel.add(sp);
		gridConstraints.insets = new Insets(1,15,4,1);
		
		bodyFrame.add(backgroundPanel,BorderLayout.CENTER);
		
		bodyFrame.setIconImage(createImage("/resources/logo_mlv_small.png").getImage());
		bodyFrame.setVisible(true);
	}
	
	private ImageIcon createImage(String path) {
		
		return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(path));
	}

	
}
