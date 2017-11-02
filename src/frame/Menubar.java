package frame;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import data.*;
import visualization.*;
import clustering.*;


public class Menubar extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu plotHeatMap, menuFile, openDistanceMatrix, menuCluster, menuAttributes, menuExport, menuVisualization, menuHelp;
	
	JMenuItem openFromLocation,ShortenLabels,DistanceMatrixFormat,exitAction,loadOriginalAttributes, loadFormatedAttributes,AttributesFormat,aboutAction,manualAction;
	
	static JMenuItem KMeansClustering,DBSCAN,plotHeatMapSI,plotHeatMapO,plotHeatMapC,loadAttributes,ShowDistanceMatrix,ShowLabels,numberOfCluster,HierarchicalClustering,ShowAttributes,addClusteringResults,saveAttributes,ShowAttributesMatrix,ChooseAttributes,ChooseOtherDM,plotSamples,plotAttributes;
	
	
	JMenuBar menu;
    DataManagement DataM;

	JLabel waitMessage = new JLabel();
	final ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));
	
	public Menubar(DataManagement Data){
		DataM = Data;
		menu = makeMenubar(DataM);	 
	}
	
	public JMenuBar getMenu(){
		return menu;
	}
	
	private JMenuBar makeMenubar(DataManagement DataM) {

		JMenuBar menubar = new JMenuBar();

		// Distance Matrix menu items
		menuFile = new JMenu("Distance Matrix");
		openFromLocation = new JMenuItem("Load");
		ShowDistanceMatrix = new JMenuItem("Show Distance Matrix");
		ShowLabels = new JMenuItem("Show Label ID");
		//ShortenLabels = new JMenuItem("Shorten Labels");
		DistanceMatrixFormat = new JMenuItem("Distance Matrix Format");	
		exitAction = new JMenuItem("Exit");
		ChooseOtherDM = new JMenuItem("Choose other Cacti");
	
		// Cluster menu items
		menuCluster = new JMenu("Clustering");
		numberOfCluster = new JMenuItem("Number of clusters");
		HierarchicalClustering = new JMenuItem("Hierarchical Clustering Results");
		KMeansClustering = new JMenuItem("KMeans Clustering Results");
		DBSCAN = new JMenuItem("DBSCAN Clustering Results");
				
		// Attributes menu items
		menuAttributes = new JMenu("Attributes");
		loadAttributes = new JMenuItem("Load");
		loadFormatedAttributes = new JMenuItem("Load");
		ShowAttributesMatrix = new JMenuItem("Show sorted SampleInfo");
		ShowAttributes = new JMenuItem("Show all Attributes");		
		AttributesFormat = new JMenuItem("Attributes Format");
		ChooseAttributes = new JMenuItem("Select an Attribute to plot");
		addClusteringResults = new JMenuItem("Add Clusters Info to the SampleInfo file");
		saveAttributes = new JMenuItem("Save the SampleInfo");
		        
		// Export menu items  
		menuExport = new JMenu("Export");
		
		// Visualization menu items  
		menuVisualization = new JMenu("Visualization");	
		plotSamples = new JMenuItem("Plot Samples");
		plotAttributes = new JMenuItem("Plot Attributes");
		plotHeatMap = new JMenu("Heat Map");
		plotHeatMapO = new JMenuItem("Original Distance Matrix");
		plotHeatMapC = new JMenuItem("After Clustering");
		plotHeatMapSI = new JMenuItem("with Selected Attribute Info");
				        
		// Help menu items
		menuHelp = new JMenu("Help");
		aboutAction = new JMenuItem("About");
		manualAction = new JMenuItem("User Manual");
	
		menuFile.add(openFromLocation);
		menuFile.add(ShowDistanceMatrix);
		menuFile.add(ShowLabels);
		menuFile.add(ChooseOtherDM);
		menuFile.add(DistanceMatrixFormat);
		menuFile.add(exitAction);
		
		menuCluster.add(numberOfCluster);
		menuCluster.add(HierarchicalClustering);
		menuCluster.add(KMeansClustering);
		menuCluster.add(DBSCAN);
		
		menuAttributes.add(loadAttributes);
		menuAttributes.add(ShowAttributesMatrix);
		menuAttributes.add(ShowAttributes);
		menuAttributes.add(ChooseAttributes);
		menuAttributes.add(addClusteringResults);
		menuAttributes.add(saveAttributes);
		menuAttributes.add(AttributesFormat);
		
		menuVisualization.add(plotSamples);
		menuVisualization.add(plotAttributes);
		menuVisualization.add(plotHeatMap);
		
		menuHelp.add(aboutAction);
		menuHelp.add(manualAction);
		
		plotHeatMap.add(plotHeatMapO);
		plotHeatMap.add(plotHeatMapC);
		plotHeatMap.add(plotHeatMapSI);
		menubar.add(menuFile);
		menubar.add(menuCluster);
		menubar.add(menuAttributes);
		menubar.add(menuVisualization);
		menubar.add(menuHelp);
		
		ListenForMenu lForMenu = new ListenForMenu();
		
		openFromLocation.addActionListener(lForMenu);
		ShowDistanceMatrix.addActionListener(lForMenu);
		ShowDistanceMatrix.setEnabled(false);
		ShowDistanceMatrix.setBackground(new Color(230,230,230));
		ShowLabels.addActionListener(lForMenu);
		ShowLabels.setEnabled(false);
		DistanceMatrixFormat.addActionListener(lForMenu);
		exitAction.addActionListener(lForMenu);
		exitAction.setBackground(new Color(230,230,230));
		
		aboutAction.addActionListener(lForMenu);
		manualAction.addActionListener(lForMenu);
		
		plotSamples.addActionListener(lForMenu);
		plotSamples.setEnabled(false);
		plotHeatMapO.addActionListener(lForMenu);
		plotHeatMapC.addActionListener(lForMenu);
		plotHeatMapSI.addActionListener(lForMenu);
		plotHeatMapO.setEnabled(false);
		plotHeatMapC.setEnabled(false);
		plotHeatMapSI.setEnabled(false);
		plotAttributes.addActionListener(lForMenu);
		plotAttributes.setEnabled(false);
		plotAttributes.setBackground(new Color(230,230,230));
		ChooseOtherDM.addActionListener(lForMenu);
		ChooseOtherDM.setEnabled(false);
		ChooseOtherDM.setBackground(new Color(230,230,230));
		
		numberOfCluster.addActionListener(lForMenu);
		numberOfCluster.setEnabled(false);
		HierarchicalClustering.addActionListener(lForMenu);
		HierarchicalClustering.setEnabled(false);
		HierarchicalClustering.setBackground(new Color(230,230,230));
		KMeansClustering.addActionListener(lForMenu);
		KMeansClustering.setEnabled(false);
		KMeansClustering.setBackground(new Color(230,230,230));
		DBSCAN.addActionListener(lForMenu);
		DBSCAN.setEnabled(false);
		DBSCAN.setBackground(new Color(230,230,230));
		
		loadAttributes.addActionListener(lForMenu);
		loadAttributes.setEnabled(false);
		ChooseAttributes.addActionListener(lForMenu);
		ChooseAttributes.setEnabled(false);
		ChooseAttributes.setBackground(new Color(230,230,230));
		ShowAttributes.addActionListener(lForMenu);
		ShowAttributes.setEnabled(false);
		ShowAttributesMatrix.addActionListener(lForMenu);
		ShowAttributesMatrix.setEnabled(false);
		ShowAttributesMatrix.setBackground(new Color(230,230,230));
		AttributesFormat.addActionListener(lForMenu);
		addClusteringResults.addActionListener(lForMenu);
		addClusteringResults.setEnabled(false);
		saveAttributes.addActionListener(lForMenu);
		saveAttributes.setEnabled(false);
		saveAttributes.setBackground(new Color(230,230,230));
	
		return menubar;
	}
	
	private class ListenForMenu implements ActionListener{
		

		/* item clicked events (ice) */
		public void actionPerformed(ActionEvent e) {
					
			if (e.getSource() == openFromLocation){
				DataM.fd = new FileDialog(ACES.bodyFrame,"Open",FileDialog.LOAD);
				DataM.fd.setVisible(true);  
                ACES.ta.setText("Orignial Cactus: \n");
                ACES.ta.setText("\n");
                
                if (DataM.fd.getFile()==null)
	            	  return;
                
                try {   
                	DataM.file1 = new File(DataM.fd.getDirectory(),DataM.fd.getFile());
                    FileReader fr = new FileReader(DataM.file1);
                    BufferedReader br = new BufferedReader(fr);
                    String aline;
                    while ((aline=br.readLine()) != null)
                    ACES.ta.append(aline+"\r\n");
                    fr.close();
                    br.close();
                    DataM.FileOpenStatus = 1;
                	Cactus oneCactus = new Cactus(DataM.fd.getDirectory()+DataM.fd.getFile());

	                if (oneCactus.getAllCacti() == null){
	                	
	            		ChooseOtherDM.setEnabled(false);

	                	DataM.setLabel(oneCactus.getLabel()); 	
	                	DataM.setDataMatrix(oneCactus.getCactus());
	                	DataM.setSize(oneCactus.getSize());
	                	
	                	HClustering CV = new HClustering(oneCactus.getLabel(),oneCactus.getSize(),oneCactus.getCactus());
	                	DataM.setNumCluster(CV.getNumCluster());
	                	DataM.setLabelsIndex(CV.getLabelsIndex());
	                	
	                	PCA PCA3Axis = new PCA(3,oneCactus.getSize(),oneCactus.getSize(),oneCactus.getCactus());
	                	DataM.setDataAxis(PCA3Axis.getDataAxis());
	                }
	             	else{
	                	
	            		ChooseOtherDM.setEnabled(true);

	                	DataM.setLabel(oneCactus.getLabel()); 
	                	DataM.setSize(DataM.getLabel().length);
	                	DataM.setAllDataMatrix(oneCactus.getCactus());
	                	DataM.setAllCaci(oneCactus.getAllCacti());
	                	
	                	DataM.ChooseDM = (String)JOptionPane.showInputDialog(null,"choose the cactus you wish to plot", "Cactus", JOptionPane.QUESTION_MESSAGE, icon, DataM.getAllCaci(), DataM.getAllCaci()[0]);
		        		
	                	if(DataM.ChooseDM == null){
	                		DataM.ChooseDM = DataM.CurrentDM;
		        			return;
	                	}
	                	else
	                		DataM.CurrentDM = DataM.ChooseDM;
	           	
		        		ChooseOtherDM.setEnabled(true);
		        		ChooseOtherDM.setText("Choose the Cacti (Current: " + DataM.CurrentDM + ")");

		        		JOptionPane.showMessageDialog(null, DataM.CurrentDM+" will be opened",null,JOptionPane.INFORMATION_MESSAGE,icon);
		        		ACES.ta.setText(DataM.CurrentDM + "\r\n");

		                int count;
						for(count = 0; count < DataM.getAllCaci().length; count++){	
		        			if(DataM.getAllCaci()[count].equals(DataM.CurrentDM))
		        			{break;}
		                }
						
						DataM.DataMatrix = new double[DataM.size][DataM.size];
						for(int i= count*DataM.size;i< (count+1)*DataM.size;i++){
							for(int j = 0; j < DataM.size; j++ ){
								DataM.DataMatrix[i-(count*DataM.size)][j] = oneCactus.getCactus()[i][j];
							}
						}
	
	                	HClustering CV = new HClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
	                	DataM.NumCluster = CV.getNumCluster();
	                	DataM.setLabelsIndex(CV.getLabelsIndex());
	                	
	                	
	                	PCA PCA3Axis = new PCA(3,DataM.size,DataM.size,DataM.getDataMatrix());
	                	DataM.setDataAxis(PCA3Axis.getDataAxis());
		        		ButtonBar.DMChoose.setEnabled(true);
		        		
		        		for(int i = 0; i < DataM.size; i++){
		                	for(int j = 0; j < DataM.size; j++){
		                		ACES.ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
							}
		                	ACES.ta.append("\n");
		                }
	                }
           
	        		ShowDistanceMatrix.setEnabled(true);
	        		ShowLabels.setEnabled(true);	        
	        		plotSamples.setEnabled(true);	
	        		plotHeatMapO.setEnabled(true);
	        		plotHeatMapC.setEnabled(true);
	        		numberOfCluster.setEnabled(true);
	        		HierarchicalClustering.setEnabled(true);
	        		KMeansClustering.setEnabled(true);
	        		DBSCAN.setEnabled(true);

	        		loadAttributes.setEnabled(true);
	        		
	        		ButtonBar.DMShow.setEnabled(true);
	        		ButtonBar.DMLabelID.setEnabled(true);	        
	        		ButtonBar.DMCluster.setEnabled(true);	
	        		ButtonBar.DMPlot.setEnabled(true);
	        		ButtonBar.SILoad.setEnabled(true);
	        		ButtonBar.DMHeatO.setEnabled(true);
	        		ButtonBar.DMHeatC.setEnabled(true);
                }
                catch (IOException ioe){
                	System.out.println(ioe);
                }
			} 	
			else if (e.getSource() == DBSCAN){
				if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
				
				ArrayList<Point> points = new ArrayList<Point>();

				for (int i = 0; i < DataM.size; i++) {
					double[] temp = new double[DataM.size];

					for(int j = 0; j < DataM.size; j++){
						temp[j] = DataM.getDataMatrix()[i][j];
					}
					points.add(new Point(temp));

	        	}
				
				DataM.calDBSCANparameter();
		        DataM.setDBSCANparameter();
		        
				DBSCAN DBSCAN = new DBSCAN(DataM.DBr,DataM.DBm);
				DBSCAN.process(points);
				DataM.setLabelsIndex(DBSCAN.getLabelsIndex()); 
		       
            	
	        	 ACES.ta.setText("DBSCAN Clustering results:\n" );  

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ACES.ta.append(DataM.Label[i] + " ---- " + Integer.toString(DataM.labelsIndex[i]) + "\n");
	        	 }
			}
			else if (e.getSource() == KMeansClustering){
				if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
            	KMClustering KMC = new KMClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
            	DataM.NumCluster = KMC.getNumCluster();
            	DataM.setLabelsIndex(KMC.getLabelsIndex()); 
	        	 ACES.ta.setText("KMeans Clustering results:\n" );  

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ACES.ta.append(DataM.Label[i] + " ---- " + Integer.toString(DataM.labelsIndex[i]) + "\n");
	        	 }
			}
			else if(e.getSource()==HierarchicalClustering) {
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 HClustering CV = new HClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
	            	DataM.NumCluster = CV.getNumCluster();
	            	DataM.setLabelsIndex(CV.getLabelsIndex()); 
	            	
	        	 ACES.ta.setText("Hierarchical Clustering results:\n" );  

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ACES.ta.append(DataM.Label[i] + " ---- " + Integer.toString(DataM.labelsIndex[i]) + "\n");
	        	 }
	         }
			else if (e.getSource() == ShowDistanceMatrix){
				
				if (DataM.FileOpenStatus == 0){
	        		  JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		  return;
	        	}
        	
				ACES.ta.setText("Distance Matrix \n");
				ACES.ta.append("\n");
                for(int i = 0; i < DataM.size; i++){
                	for(int j = 0; j < DataM.size; j++){
                		ACES.ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
					}
                	ACES.ta.append("\n");
                }
			}
			else if(e.getSource() == ChooseOtherDM){
				
				DataM.ChooseDM = (String)JOptionPane.showInputDialog(null,"choose the cactus you wish to plot", "Cactus", JOptionPane.QUESTION_MESSAGE, icon, DataM.AllCaci, DataM.AllCaci[0]);
				
				if(DataM.ChooseDM == null){
            		DataM.ChooseDM = DataM.CurrentDM;
        			return;
            	}
            	else
            		DataM.CurrentDM = DataM.ChooseDM;
				
        		ChooseOtherDM.setText("Choose the Cacti (Current: " + DataM.CurrentDM + ")");

            	JOptionPane.showMessageDialog(null, DataM.CurrentDM+" will be opened",null,JOptionPane.INFORMATION_MESSAGE,icon);
        		ACES.ta.setText(DataM.CurrentDM + "\r\n");

        		for(int i = 0; i < DataM.size; i++){
                	for(int j = 0; j < DataM.size; j++){
                		ACES.ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
					}
                	ACES.ta.append("\n");
                }
              
                int count;
				for(count = 0; count < DataM.getAllCaci().length; count++){	
        			if(DataM.getAllCaci()[count].equals(DataM.CurrentDM))
        			{break;}
                }
				
				DataM.DataMatrix = new double[DataM.size][DataM.size];
				for(int i= count*DataM.size;i< (count+1)*DataM.size;i++){
					for(int j = 0; j < DataM.size; j++ ){
						DataM.DataMatrix[i-(count*DataM.size)][j] = DataM.getAllDataMatrix()[i][j];
					}
				}

            	HClustering CV = new HClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
            	DataM.NumCluster = CV.getNumCluster();
            	DataM.setLabelsIndex(CV.getLabelsIndex());

            	PCA PCA3Axis = new PCA(3,DataM.size,DataM.size,DataM.getDataMatrix());
            	DataM.setDataAxis(PCA3Axis.getDataAxis());
            	
			}
			else if (e.getSource() == ShowLabels){
				
				if (DataM.FileOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
        	
				ACES.ta.setText("Labels \n");
				ACES.ta.append("\n");
                for(int i = 0; i < DataM.size; i++){
                	ACES.ta.append(Integer.toString(i+1) + "     " + DataM.Label[i] +"\n");
                }
			}
			else if (e.getSource() == DistanceMatrixFormat){
				ShowFormat DMF = new ShowFormat();
	        	DMF.show_DM_Format(ACES.ta);	
			}
			else if (e.getSource()==loadAttributes) {        	  
				if (DataM.FileOpenStatus == 0){
					JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	  
	        	 // JOptionPane.showMessageDialog(null, "The sampleInfo file should be sorted as the same order of Distance Matrix file:\nBoth the Distance Matrix label and SampleInfo Label should be consistent.", null, JOptionPane.INFORMATION_MESSAGE, icon);
      		 
				DataM.fd = new FileDialog(ACES.bodyFrame,"Open",FileDialog.LOAD);
				DataM.fd.setVisible(true);  
	            
	            if (DataM.fd.getFile()==null)
	            	return;

	            ACES.ta.setText("\n");

	            try {   
	            	DataM.file1 = new File(DataM.fd.getDirectory(),DataM.fd.getFile());
	            	
                    Cactus ACactus = new Cactus(DataM.fd.getDirectory()+DataM.fd.getFile(), "Attribute");
	    			DataM.setAttributeOriginalMatrix(ACactus.getCactusData());
	    			DataM.setAttributeMatrix(ACactus.getCactusData());
	    			DataM.AttributeOriginalSize = ACactus.getSize();
	    			
	    			for(int i = 0; i < ACactus.getSize(); i++){
	    				DataM.setAttributeLine(ACactus.getCactusData()[i].split(","));
	    				for(int j = 0; j < DataM.AttributeLine.length; j++){  	
	    					ACES.ta.append(DataM.AttributeLine[j]+"   \r");
	        			}
	    				ACES.ta.append("\n");
	    			}
	    			DataM.AttributeOpenStatus = 1;
	    			DataM.setAttributeLine(ACactus.getCactusData()[0].split(","));
	            }
		        catch (IOException ioe){
		            System.out.println(ioe);
		        }
	            
	            if(DataM.AttributeMatrix.length>DataM.size+1){
		        	JOptionPane.showMessageDialog(null, "Please rearrange your SampleInfo file.", null, JOptionPane.INFORMATION_MESSAGE, icon);
		        	DataM.AttributeSize = DataM.size+1;
		        	DataM.AttributeMatrix = new String[DataM.size+1];
		        	DataM.AttributeMatrix[0] = DataM.AttributeOriginalMatrix[0];
		        	DataM.changeSampleInfo();
	        	}  
	            ChooseAttributes.setEnabled(true);
	    		ShowAttributes.setEnabled(true);
	    		ShowAttributesMatrix.setEnabled(true);
	    		addClusteringResults.setEnabled(true);
	    		saveAttributes.setEnabled(true);
	    		
	    		plotAttributes.setEnabled(true); 
	    		
	    		ButtonBar.SIChoose.setEnabled(true);
	    		ButtonBar.SIShowList.setEnabled(true);
	    		ButtonBar.SIShow.setEnabled(true);
	    		ButtonBar.AddCluster.setEnabled(true);
	    		ButtonBar.SISave.setEnabled(true);
	    		
	    		ButtonBar.SIPlot.setEnabled(true);
		    }    
			else if (e.getSource()==ShowAttributesMatrix){
				if (DataM.FileOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	if (DataM.AttributeOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the attributes info first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	if(DataM.AttributeOriginalSize>DataM.size+1){
		        	JOptionPane.showMessageDialog(null, "Please rearrange your SampleInfo file.", null, JOptionPane.INFORMATION_MESSAGE, icon);
		        	DataM.AttributeSize = DataM.size+1;
		        	DataM.AttributeMatrix = new String[DataM.size+1];
		        	DataM.AttributeMatrix[0] = DataM.AttributeOriginalMatrix[0];
		        	DataM.changeSampleInfo();
	        	}
				ACES.ta.setText("Attributes Matrix\n");
			
				for(int i = 0; i < DataM.AttributeLine.length; i++)
	    	    	ACES.ta.append(DataM.AttributeMatrix[i]+"   \n");
				
			}
			else if (e.getSource()==saveAttributes){

				JFileChooser savefile = new JFileChooser();
		        savefile.setSelectedFile(new File("SortedSampleInfo.csv"));
 		        int sf = savefile.showSaveDialog(null);

		        if(sf == JFileChooser.APPROVE_OPTION){
		            try {
		            	File f = savefile.getSelectedFile();
		 		        String filename = f.getCanonicalPath();

		 		        String [] templine;
		            	FileWriter writer = new FileWriter(filename); 

		                for(int i = 0; i < DataM.size+1; i++){
		                	templine = DataM.AttributeMatrix[i].split(",");
		                	for (int j=0; j< templine.length; j++){
		                        writer.append(templine[j]);
		                        writer.append(',');
		                    }		                 
		                      writer.append('\n');
		                      writer.flush();
		                }
		                writer.close();

		                JOptionPane.showMessageDialog(null, "File has been saved","File Saved", JOptionPane.INFORMATION_MESSAGE, icon);
		         
		            } catch (IOException ioe) {
		            	ioe.printStackTrace();
		            }
		        }else if(sf == JFileChooser.CANCEL_OPTION){
		            JOptionPane.showMessageDialog(null, "File save has been canceled");
		        }
			}
			else if (e.getSource()==addClusteringResults){
				
				DataM.AttributeMatrix[0] = String.join(",",DataM.CurrentDM, DataM.AttributeMatrix[0]);
				
				for(int i = 1; i < DataM.size+1; i++){
					DataM.AttributeMatrix[i]= String.join(",",Integer.toString(DataM.labelsIndex[i-1]), DataM.AttributeMatrix[i]);
        		}	
                JOptionPane.showMessageDialog(null, "The Clustering results of ("+DataM.CurrentDM + ") has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE, icon);

			}
			else if (e.getSource()==ShowAttributes) { 
				if (DataM.FileOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	if (DataM.AttributeOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the attributes info first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	if(DataM.AttributeOriginalSize>DataM.size+1){
		        	JOptionPane.showMessageDialog(null, "Please rearrange your SampleInfo file.", null, JOptionPane.INFORMATION_MESSAGE, icon);
		        	DataM.AttributeSize = DataM.size+1;
		        	DataM.AttributeMatrix = new String[DataM.size+1];
		        	DataM.AttributeMatrix[0] = DataM.AttributeOriginalMatrix[0];
		        	DataM.changeSampleInfo();
	        	}
	              
	        	ACES.ta.setText("All attributes \n");
	        	for(int i = 0; i < DataM.AttributeLine.length; i++){
	        		ACES.ta.append(Integer.toString(i)+"   "+ DataM.AttributeLine[i]+"\r\n");
        		}
            }  
			else if (e.getSource()==ChooseAttributes) { 
				if (DataM.FileOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	if (DataM.AttributeOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the attributes info first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	if(DataM.AttributeOriginalSize>DataM.size+1){
		        	JOptionPane.showMessageDialog(null, "Please rearrange your SampleInfo file.", null, JOptionPane.INFORMATION_MESSAGE, icon);
		        	DataM.AttributeSize = DataM.size+1;
		        	DataM.AttributeMatrix = new String[DataM.size+1];
		        	DataM.AttributeMatrix[0] = DataM.AttributeOriginalMatrix[0];
		        	DataM.changeSampleInfo();
	        	}
	        	
	        	if(DataM.AttributeOriginalSize>DataM.size+1)
	        	  	return;
	        	else{
	        		DataM.ChooseAttribute = (String)JOptionPane.showInputDialog(null,"choose the attribute you wish to plot", "Attributes", JOptionPane.QUESTION_MESSAGE, icon, DataM.getAttributeLine(), DataM.getAttributeLine()[0]);
		        	if(DataM.ChooseAttribute == null)
		        		return;
		        	ACES.ta.setText("selected Attribute: " + DataM.ChooseAttribute+ "\r\n");
		              
		        	int count;
					for(count = 0; count < DataM.AttributeLine.length; count++){	
	        			if(DataM.AttributeLine[count].equals(DataM.ChooseAttribute))
	        			{break;}
	                }

	                String[] temp1; 
	                DataM.AttributeLabel = new String[DataM.size];    
	                for(int i = 1; i <  DataM.size+1; i++){
	        			temp1 =  DataM.AttributeMatrix[i].split(",");
	        			DataM.AttributeLabel[i-1] = temp1[count];
	        			ACES.ta.append(Integer.toString(i)+"   "+ DataM.AttributeLabel[i-1] +"\n");
	        		}
	                ACES.ta.append("\n");
	                ACES.ta.append("\n");
	               
	                Set<String> TT = new LinkedHashSet<String>(Arrays.asList(DataM.AttributeLabel));
	                TT.remove("0");
	                TT.remove("");
	                DataM.setRefLabel(TT.toArray( new String[TT.size()] ));
	                
	                Object[] message = new Object[DataM.refLabel.length+2];
	                message[0] = "selected Attribute: " + DataM.ChooseAttribute+ "\r\n"; 
	                message[1] = "\n"; 
	                for(int i = 0; i < DataM.refLabel.length; i++){
	                	message[i+2] = DataM.refLabel[i] + "\n"; 
	        		}
	        		plotHeatMapSI.setEnabled(true);
	        		ButtonBar.SIHeat.setEnabled(true);

                	JOptionPane.showMessageDialog(null, message,"Unique Labels", JOptionPane.CLOSED_OPTION, icon);
                
	                DataM.AttributeChooseStatus = 1;
	        	}
                
             }    
			 else if (e.getSource() == AttributesFormat){
	        	 ShowFormat AF = new ShowFormat();
	        	 AF.show_A_Format(ACES.ta);	  
	         }
	         else if(e.getSource()==numberOfCluster) {
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        	     return;
	        	 }
	        	 ACES.ta.setText("There are " + Integer.toString(DataM.size) + " samples and " + Integer.toString(DataM.NumCluster) + " clusters in total.\n" );  
	         }
	         else if(e.getSource()==plotHeatMapO){
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 try {
					new Visualization(DataM.DataMatrix, DataM.size,"Original Distance Matrix",DataM.Label,DataM.Label);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	         else if(e.getSource()==plotHeatMapC){
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 DataM.CreateDataAfterClustering();

	        	 try {
					new Visualization(DataM.newData, DataM.size,"Distance Matrix after Clustering", DataM.newDataLabel, DataM.newDataLabel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	         else if(e.getSource()==plotHeatMapSI){
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 DataM.CreateDataAfterClusteringandChooseAttri();

	        	 try {
					new Visualization(DataM.newData, DataM.size,"Distance Matrix after Clustering: "+ " "+DataM.ChooseAttribute+ " ", DataM.newAttributeLabel,DataM.newDataLabel,DataM.newlabelsIndex);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	         else if(e.getSource()==plotSamples){
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 new Visualization(DataM.getLabelsIndex(), DataM.getLabel(), DataM.getDataAxis(), DataM.size, DataM.CurrentDM);
	         }
	         else if(e.getSource()==plotAttributes){
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 if (DataM.AttributeOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the attributes info first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 if (DataM.AttributeChooseStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please choose the attribute you wish to plot.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 if(DataM.AttributeOriginalSize>DataM.size+1){
		        	 JOptionPane.showMessageDialog(null, "Please rearrange your SampleInfo file.", null, JOptionPane.INFORMATION_MESSAGE, icon);
		        	 DataM.AttributeSize = DataM.size+1;
		        	 DataM.AttributeMatrix = new String[DataM.size+1];
		        	 DataM.AttributeMatrix[0] = DataM.AttributeOriginalMatrix[0];
		        	 DataM.changeSampleInfo();
	        	 }
	        	 new Visualization(DataM.getAttributeLabel(), DataM.getRefLabel(), DataM.getDataAxis(), DataM.size, DataM.refLabel.length, DataM.ChooseAttribute);
   
	        }
			else if (e.getSource() == exitAction){
				int dialogResult = JOptionPane.showConfirmDialog(ACES.bodyFrame, "Are you sure to quit?", "Closing confirmation", 
			            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
				if (dialogResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			else if (e.getSource() == aboutAction){
				new About();
			}
			
				
		} 

		
	}
	
}



/*else if (e.getSource()==loadOriginalAttributes) { 
if (FileOpenStatus == 0){
	  JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	  return;
}

fd = new FileDialog(ACES.bodyFrame,"Open",FileDialog.LOAD);
fd.setVisible(true);  
if (fd.getFile()==null)
	  return;
ACES.ta.setText("\n");
try {   
    
      file1 = new File(fd.getDirectory(),fd.getFile());
      Cactus ACactus = new Cactus(fd.getDirectory()+fd.getFile(), "Attribute");
      AttributeOriginalMatrix = ACactus.getCactusData();
		AttributeOriginalSize = ACactus.getSize();
		for(int i = 0; i < ACactus.getSize(); i++){
			AttributeLine = ACactus.getCactusData()[i].split(",");
			for(int j = 0; j < AttributeLine.length; j++){  	
				ACES.ta.append(AttributeLine[j]+"   \r");
			}
			ACES.ta.append("\n");
		}
		AttributeOpenStatus = 1;
		AttributeLine = ACactus.getCactusData()[0].split(",");
    }
    
   catch (IOException ioe){
    
      System.out.println(ioe);
    }

	AttributeSize = size+1;
 	AttributeMatrix = new String[size+1];
 	AttributeMatrix[0] = AttributeOriginalMatrix[0];	 	          	
  changeSampleInfo();
	       
}*/