package Frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

public class ExportSVG extends JFrame implements ActionListener {
	    
	    public ExportSVG(String title) {
	        super(title);
	        add(createContent());
	    }
	    
	    private JComponent createContent() {
	        JPanel content = new JPanel(new BorderLayout());
	        JTabbedPane tabs = new JTabbedPane();
	        tabs.add("Tab 1", new JButton("First Tab"));
	        tabs.add("Tab 2", new JButton("Second Tab"));
	        JButton button = new JButton("Save to SVG");
	        button.addActionListener(this);
	        content.add(tabs);
	        content.add(button, BorderLayout.SOUTH);
	        return content;
	    }
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JComponent c = (JComponent) getContentPane().getComponent(0);
	        SVGGraphics2D g2 = new SVGGraphics2D(c.getWidth(), c.getHeight());
	        c.paint(g2);
	        File f = new File("SwingUIToSVGDemo.svg");
	        try {
	            SVGUtils.writeToSVG(f, g2.getSVGElement());
	        } catch (IOException ex) {
	            System.err.println(ex);
	        }
	    }

	    public static void main(String[] args) {
	        try {
	            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception e) {
	            // just take the default look and feel
	        }

	        ExportSVG app = new ExportSVG("SwingUIToSVGDemo.java");
	        app.pack();
	        app.setVisible(true);
	        
	    }
}
