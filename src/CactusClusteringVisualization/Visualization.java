package CactusClusteringVisualization;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.freehep.util.export.ExportDialog;
import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;
import org.tc33.jheatchart.HeatChart;
import org.freehep.graphics2d.VectorGraphics;

public class Visualization {
	 private int [] labelsIndex;
		
	 final ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));

	
 	 public Visualization(int [] labelsIndex, String [] Label, double [][] DataAxis, int size, String ChooseDM) {
 		 super();
 		 
 		 double[] x = {1};
		 double[] y = {1};
	     double[] z = {1};
	     
	     Plot3DPanel plot = new Plot3DPanel();
	     
	     for (int j = 0; j < size; j++){
	    	 
	    	x[0] = DataAxis[0][j];
			y[0] = DataAxis[1][j];
			z[0] = DataAxis[2][j];
			
			if (labelsIndex[j] == 1)
				{plot.addScatterPlot(Label[j], Color.RED, x, y, z);
			//plot.addLabel("#", Color.RED, where);
			plot.addLegend("EAST");
			}
			else if (labelsIndex[j] == 2){
				plot.addScatterPlot(Label[j], Color.BLUE, x, y, z);
			//	plot.addLabel("#", Color.BLUE, where);
			}
			else if (labelsIndex[j] == 3){
				plot.addScatterPlot(Label[j], Color.GREEN, x, y, z);
			}
			else if (labelsIndex[j] == 4){
				plot.addScatterPlot(Label[j], Color.CYAN, x, y, z);
			}
			else if (labelsIndex[j] == 5){
				plot.addScatterPlot(Label[j], Color.MAGENTA, x, y, z);
			}
			else if (labelsIndex[j] == 6){
				plot.addScatterPlot(Label[j], Color.ORANGE, x, y, z);
			}
			else if (labelsIndex[j] == 7){
				plot.addScatterPlot(Label[j], Color.YELLOW, x, y, z);
			}
			else if (labelsIndex[j] == 8){
				plot.addScatterPlot(Label[j], Color.PINK, x, y, z);
			}
			else if (labelsIndex[j] == 9){
				plot.addScatterPlot(Label[j], Color.GRAY, x, y, z);
			}
			else{
				plot.addScatterPlot(Label[j], Color.BLACK, x, y, z);
			}
			
       }
  
	     JFrame frame = new JFrame("Visualization using the " + ChooseDM);
	     frame.setContentPane(plot);
	     frame.setVisible(true);
	     frame.setSize(900, 900);
	     /*
	     try
	        {
	            ExportDialog export = new ExportDialog();
	            export.showExportDialog( null, "Export view as ...", plot, "export" );
	            System.err.println("Image save complete");

	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }*/
 	 }
 	 
 	public Visualization(String [] label, String [] refLabel, double [][] DataAxis, int size, int num, String Attribute) {
		super();
	
		double[] x = {1};
		double[] y = {1};
	    double[] z = {1};
	    
	    Plot3DPanel plot = new Plot3DPanel();
	    labelsIndex = new int[size];
	   
	    for (int i = 0; i < size; i++){
	    	labelsIndex[i] = 0; 
	    	for(int j = 0; j < num; j++){
	    		if (label[i].equals(refLabel[j]))
	    			labelsIndex[i] = j+1;  		
	    	}
	    }
	    
	    for (int j = 0; j < size; j++){
	    	
			x[0] = DataAxis[0][j];
			y[0] = DataAxis[1][j];
			z[0] = DataAxis[2][j];
			
			if (labelsIndex[j] == 1){
				plot.addScatterPlot(label[j], Color.RED, x, y, z);
				plot.addLegend("EAST");
			}
			else if (labelsIndex[j] == 2){
				plot.addScatterPlot(label[j], Color.BLUE, x, y, z);
			}
			else if (labelsIndex[j] == 3){
				plot.addScatterPlot(label[j], Color.GREEN, x, y, z);
			}
			else if (labelsIndex[j] == 4){
				plot.addScatterPlot(label[j], Color.CYAN, x, y, z);
			}
			else if (labelsIndex[j] == 5){
				plot.addScatterPlot(label[j], Color.MAGENTA, x, y, z);
			}
			else if (labelsIndex[j] == 6){
				plot.addScatterPlot(label[j], Color.ORANGE, x, y, z);
			}
			else if (labelsIndex[j] == 7){
				plot.addScatterPlot(label[j], Color.YELLOW, x, y, z);
			}
			else if (labelsIndex[j] == 8){
				plot.addScatterPlot(label[j], Color.PINK, x, y, z);
			}
			else if (labelsIndex[j] == 9){
				plot.addScatterPlot(label[j], Color.GRAY, x, y, z);
			}
			else{
				plot.addScatterPlot(label[j], Color.BLACK, x, y, z);
			}
			
       }
	    
	    JFrame frame = new JFrame("Visualization using the " + Attribute);
	    frame.setContentPane(plot);
	    frame.setVisible(true);
	    frame.setSize(900, 900);
 	 }
 	

	 public Visualization(double[][] Data, int size, String Label, String[] L) throws IOException {
		
	     HeatChart map = new HeatChart(Data);

	     map.setTitle(Label);
	     map.setXAxisLabel("Samples ID");
	     map.setXValues(L);
	     map.setYValues(L);
	     map.setYAxisLabel("Samples ID");
	     map.setLowValueColour(Color.WHITE);
	     map.setHighValueColour(Color.BLACK);
	     
	     File file = new File("heatmap_temp_output.png");
	     
	     map.saveToFile(file);
	     
	     ImageIcon imageIcon = new ImageIcon(new ImageIcon("heatmap_temp_output.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
	     
         JLabel label = new JLabel(imageIcon);
         
         JFrame f = new JFrame(Label);
         f.getContentPane().add(label);
         f.pack();
         f.setVisible(true);
        
         f.addWindowListener(new java.awt.event.WindowAdapter() {
 		    @Override
 		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
 		    	int option = JOptionPane.showConfirmDialog(null, "Do you want to save the Heat map?", "Save", JOptionPane.OK_OPTION, JOptionPane.OK_CANCEL_OPTION,icon);
 		    	if (option == JOptionPane.OK_OPTION){
 		        	JFileChooser savefile = new JFileChooser();
 	 		        savefile.setSelectedFile(new File("HeatMap.png"));
 	 		        int sf = savefile.showSaveDialog(null);
 	 		        

 	 		        if(sf == JFileChooser.APPROVE_OPTION){
 	 		            try {
 	 		            	File ff = savefile.getSelectedFile();
 	 		 		        String filename = ff.getCanonicalPath();
 	 		 		        
 	 		 			    map.saveToFile(new File(filename));
 	 		 			    
 	 		 			    JOptionPane.showMessageDialog(null, "File has been saved","File Saved", JOptionPane.INFORMATION_MESSAGE);
 	 		 			    file.delete();

 	 		 	      } catch (IOException ioe) {
 	 		            	ioe.printStackTrace();
 	 		            }
 	 		            
 	 		        }else if(sf == JFileChooser.CANCEL_OPTION){
 	 		            JOptionPane.showMessageDialog(null, "File save has been canceled");
 	 		        }
 		        	 
 		         }else{
 		        	 file.delete();
 		         }     	
 		    }
 		});  
	 }
	

}

