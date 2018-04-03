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
	static JMenu clustering, plot3D, plotHeatMap;
	JMenu Formats, Loadall, menuDataFile, menuEdit, menuView, menuExport, menuVisualization, menuHelp;
	
	JMenuItem LoadData, ShowDataMatrix, DataMatrixFormat, exitAction, openFromLocation,ShortenLabels,DistanceMatrixFormat,loadOriginalAttributes, loadFormatedAttributes,AttributesFormat,aboutAction,manualAction;
	
	static JMenuItem ShowPower, KMeansClustering,DBSCAN,plotHeatMapO,plotHeatMapC,plotHeatMapA,loadAttributes,ShowDistanceMatrix,ShowLabels,numberOfCluster,HierarchicalClustering,ShowAttributes,addClusteringResults,saveAttributes,ShowAttributesMatrix,ChooseAttributes,ChooseOtherDM,plotSamples,plotAttributes;
	
	
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
		
		
		
		// File
		menuDataFile = new JMenu("File");
		Loadall = new JMenu("Open");
		LoadData = new JMenuItem("Raw data");
		openFromLocation = new JMenuItem("Distance matrix");
		loadAttributes = new JMenuItem("Attributes");
		Formats = new JMenu("Formats");
        DataMatrixFormat = new JMenuItem("Raw data");	
		DistanceMatrixFormat = new JMenuItem("Distance matrix");	
		AttributesFormat = new JMenuItem("Attributes");
		saveAttributes = new JMenuItem("Save the SampleInfo");
		exitAction = new JMenuItem("Exit");

		menuDataFile.add(Loadall);
		Loadall.add(LoadData);
		Loadall.add(openFromLocation);
		Loadall.add(loadAttributes);
		menuDataFile.add(Formats);
		Formats.add(DataMatrixFormat);
		Formats.add(DistanceMatrixFormat);
		Formats.add(AttributesFormat);
		menuDataFile.add(saveAttributes);
		menuDataFile.add(exitAction);

		// Edit
		menuEdit = new JMenu("Edit");
		ChooseOtherDM = new JMenuItem("Select other distance matrix");
		ChooseAttributes = new JMenuItem("Select an attribute to plot");
		addClusteringResults = new JMenuItem("Add clusters to SampleInfo");
		
		menuEdit.add(ChooseOtherDM);
		menuEdit.add(ChooseAttributes);
		menuEdit.add(addClusteringResults);

		// View
		menuView = new JMenu("View");
		ShowDistanceMatrix = new JMenuItem("Distance matrix");
		ShowLabels = new JMenuItem("Label IDs");
		clustering = new JMenu("Clustering");
		numberOfCluster = new JMenuItem("Numbers");
		HierarchicalClustering = new JMenuItem("Hierarchical");
		KMeansClustering = new JMenuItem("KMeans");
		DBSCAN = new JMenuItem("DBSCAN");
		ShowAttributesMatrix = new JMenuItem("Sorted sampleInfo");
		ShowAttributes = new JMenuItem("All attributes");		
		ShowPower = new JMenuItem("Discriminative power");

		menuView.add(ShowDistanceMatrix);
		menuView.add(ShowLabels);
		menuView.add(clustering);
		clustering.add(numberOfCluster);
		clustering.add(HierarchicalClustering);
		clustering.add(KMeansClustering);
		clustering.add(DBSCAN);
		menuView.add(ShowAttributesMatrix);
		menuView.add(ShowAttributes);
		menuView.add(ShowPower);
		
		//ShortenLabels = new JMenuItem("Shorten Labels");
		
		
		// Visualization menu items  
		menuVisualization = new JMenu("Visualization");	
		plot3D = new JMenu("3D plot");
		plotSamples = new JMenuItem("Samples");
		plotAttributes = new JMenuItem("Selected attribute");
		plotHeatMap = new JMenu("Heat Map");
		plotHeatMapO = new JMenuItem("Original distance matrix");
		plotHeatMapC = new JMenuItem("After clustering");
		plotHeatMapA = new JMenuItem("With selected attribute");
		
		menuVisualization.add(plot3D);
		menuVisualization.add(plotHeatMap);
		plot3D.add(plotSamples);
		plot3D.add(plotAttributes);
		plotHeatMap.add(plotHeatMapO);
		plotHeatMap.add(plotHeatMapC);
		plotHeatMap.add(plotHeatMapA);
		
		// Help menu items
		menuHelp = new JMenu("Help");
		aboutAction = new JMenuItem("About");
		manualAction = new JMenuItem("User Manual");
		
				
		menuHelp.add(aboutAction);
		menuHelp.add(manualAction);
		
		menubar.add(menuDataFile);
		menubar.add(menuEdit);
		menubar.add(menuView);
		menubar.add(menuVisualization);
		menubar.add(menuHelp);
		
		ListenForMenu lForMenu = new ListenForMenu();
		Formats.setBackground(new Color(230,230,230));
		LoadData.addActionListener(lForMenu);
		LoadData.setBackground(new Color(230,230,230));
		openFromLocation.addActionListener(lForMenu);
		loadAttributes.addActionListener(lForMenu);
		loadAttributes.setEnabled(false);
		loadAttributes.setBackground(new Color(230,230,230));
		DistanceMatrixFormat.addActionListener(lForMenu);
		DataMatrixFormat.addActionListener(lForMenu);
		DataMatrixFormat.setBackground(new Color(230,230,230));
		AttributesFormat.addActionListener(lForMenu);
		AttributesFormat.setBackground(new Color(230,230,230));	
		saveAttributes.addActionListener(lForMenu);
		exitAction.addActionListener(lForMenu);
		exitAction.setBackground(new Color(230,230,230));	

		ChooseOtherDM.addActionListener(lForMenu);
		ChooseOtherDM.setEnabled(false);
		ChooseAttributes.addActionListener(lForMenu);
		ChooseAttributes.setEnabled(false);
		ChooseAttributes.setBackground(new Color(230,230,230));
		addClusteringResults.addActionListener(lForMenu);
		addClusteringResults.setEnabled(false);
			
		ShowDistanceMatrix.addActionListener(lForMenu);
		ShowDistanceMatrix.setEnabled(false);
		ShowLabels.addActionListener(lForMenu);
		ShowLabels.setEnabled(false);
		clustering.setEnabled(false);
		clustering.setBackground(new Color(230,230,230));
		numberOfCluster.addActionListener(lForMenu);
		numberOfCluster.setEnabled(false);
		numberOfCluster.setBackground(new Color(230,230,230));
		HierarchicalClustering.addActionListener(lForMenu);
		HierarchicalClustering.setEnabled(false);
		KMeansClustering.addActionListener(lForMenu);
		KMeansClustering.setEnabled(false);
		KMeansClustering.setBackground(new Color(230,230,230));
		DBSCAN.addActionListener(lForMenu);
		DBSCAN.setEnabled(false);
		ShowPower.addActionListener(lForMenu);
		ShowPower.setEnabled(false);
		ShowAttributes.addActionListener(lForMenu);
		ShowAttributes.setEnabled(false);
		ShowAttributesMatrix.addActionListener(lForMenu);
		ShowAttributesMatrix.setEnabled(false);
		
			
		aboutAction.addActionListener(lForMenu);
		manualAction.addActionListener(lForMenu);
		manualAction.setBackground(new Color(230,230,230));
	
		
		plot3D.setEnabled(false);
		plotHeatMap.setEnabled(false);
		plotHeatMap.setBackground(new Color(230,230,230));

		plotSamples.addActionListener(lForMenu);
		plotSamples.setEnabled(false);
		plotAttributes.addActionListener(lForMenu);
		plotAttributes.setEnabled(false);
		plotAttributes.setBackground(new Color(230,230,230));
		plotHeatMapO.addActionListener(lForMenu);
		plotHeatMapC.addActionListener(lForMenu);
		plotHeatMapA.addActionListener(lForMenu);
		plotHeatMapO.setEnabled(false);
		plotHeatMapC.setEnabled(false);
		plotHeatMapA.setEnabled(false);
		plotHeatMapO.setBackground(new Color(230,230,230));
		plotHeatMapA.setBackground(new Color(230,230,230));

		
		
		
		
		

		saveAttributes.addActionListener(lForMenu);
		saveAttributes.setEnabled(false);
	
		return menubar;
	}
	
	private class ListenForMenu implements ActionListener{
		

		/* item clicked events (ice) */
		public void actionPerformed(ActionEvent e) {
					
			if (e.getSource() == LoadData){
				DataM.fd = new FileDialog(ACES.bodyFrame,"Open",FileDialog.LOAD);
				DataM.fd.setVisible(true);  
                ACES.ta.setText("Original DataInfo: \n");
                ACES.ta.setText("\n");
                
                if (DataM.fd.getFile()==null)
	            	  return;
                
                try {   
                	DataM.file1 = new File(DataM.fd.getDirectory(),DataM.fd.getFile());
                    FileReader fr = new FileReader(DataM.file1);
                    BufferedReader br = new BufferedReader(fr);
                    String aline;
                    
                    while ((aline=br.readLine()) != null )
	                    ACES.ta.append(aline+"\r\n");
                    
                    /*int count1 = 0;
                    while ((aline=br.readLine()) != null && count1 <40){
	                    count1 = count1 + 1;
	                    ACES.ta.append(aline+"\r\n");
                    }*/
                    fr.close();
                    br.close();
                    DataM.FileOpenStatus = 1;
                    
                    DataM.setDataInfo();
                    
                	Cactus oneCactus = new Cactus(DataM.fd.getDirectory()+DataM.fd.getFile(),DataM.Row,DataM.Column,DataM.dimension,DataM.LineNo);
                	DataM.setLabel(oneCactus.getLabel()); 
                	DataM.setOriginalDataMatrix(oneCactus.getOriginalData());
                	DataM.setDataMatrix(oneCactus.getCactus());
                	DataM.sampleSize = oneCactus.sampleSize;
                	DataM.size = oneCactus.getSize();
                	
            /*    	for(int i = 0; i < DataM.size; i++){
	                	for(int j = 0; j < DataM.size; j++){
	                		ACES.ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
						}
	                	ACES.ta.append("\n");
	                }
                	*/
                	
                	HClustering CV = new HClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
                	DataM.NumCluster = CV.getNumCluster();
                	DataM.setLabelsIndex(CV.getLabelsIndex());
                	
                	
                	PCA PCA3Axis = new PCA(3,DataM.size,DataM.size,DataM.getDataMatrix());
                	DataM.setDataAxis(PCA3Axis.getDataAxis());
	        		ButtonBar.DMChoose.setEnabled(true);
                	
                	
	               
	        		ShowDistanceMatrix.setEnabled(true);
	        		ShowLabels.setEnabled(true);	 
	        		plot3D.setEnabled(true);
	        		plotHeatMap.setEnabled(true);
	        		plotSamples.setEnabled(true);	
	        		plotHeatMapO.setEnabled(true);
	        		plotHeatMapC.setEnabled(true);
	        		clustering.setEnabled(true);
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
			else if (e.getSource() == openFromLocation){
				DataM.fd = new FileDialog(ACES.bodyFrame,"Open",FileDialog.LOAD);
				DataM.fd.setVisible(true);  
                ACES.ta.setText("Original distance matrix: \n");
                ACES.ta.setText("\n");
                
                if (DataM.fd.getFile()==null)
	            	  return;
                
                try {   
                	DataM.file1 = new File(DataM.fd.getDirectory(),DataM.fd.getFile());
                    FileReader fr = new FileReader(DataM.file1);
                    BufferedReader br = new BufferedReader(fr);
                    String aline;
                  
                    while ((aline=br.readLine()) != null )
	                    ACES.ta.append(aline+"\r\n");
                    
                    fr.close();
                    br.close();
                    DataM.FileOpenStatus = 1;
                    
                    DataM.ChooseAttribute = "attribute";	                		              
                	ChooseAttributes.setEnabled(false);
    	    		ShowAttributes.setEnabled(false);
    	    		ShowAttributesMatrix.setEnabled(false);
    	    		addClusteringResults.setEnabled(false);
    	    		saveAttributes.setEnabled(false);
    	    		
    	    		plotAttributes.setEnabled(false); 
    	    		
    	    		ButtonBar.SIChoose.setEnabled(false);
    	    		ButtonBar.SIShowList.setEnabled(false);
    	    		ButtonBar.SIShow.setEnabled(false);
    	    		ButtonBar.AddCluster.setEnabled(false);
    	    		ButtonBar.SISave.setEnabled(false);		
    	    		ButtonBar.SIPlot.setEnabled(false);
                    
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
	                	
	                	DataM.ChooseDM = (String)JOptionPane.showInputDialog(null,"choose the distance matrix you wish to plot", "Distance matrix", JOptionPane.QUESTION_MESSAGE, icon, DataM.getAllCaci(), DataM.getAllCaci()[0]);
		        		
	                	if(DataM.ChooseDM == null){
	                		DataM.ChooseDM = DataM.CurrentDM;
		        			return;
	                	}
	                	else
	                		DataM.CurrentDM = DataM.ChooseDM;
	           	
		        		ChooseOtherDM.setEnabled(true);
		        		ChooseOtherDM.setText("Choose the the other distance matrix (Current: " + DataM.CurrentDM + ")");

		        		//JOptionPane.showMessageDialog(null, DataM.CurrentDM+" will be opened",null,JOptionPane.INFORMATION_MESSAGE,icon);
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
	        		plot3D.setEnabled(true);
	        		plotHeatMap.setEnabled(true);
	        		plotSamples.setEnabled(true);	
	        		plotHeatMapO.setEnabled(true);
	        		plotHeatMapC.setEnabled(true);
	        		clustering.setEnabled(true);
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
				
		        DataM.setDBSCANparameter();
		        
				DBSCAN DBSCAN = new DBSCAN(DataM.DBr,DataM.DBm);
				DBSCAN.process(points);
				DataM.setLabelsIndex(DBSCAN.getLabelsIndex()); 
				int max = 0;
				for(int i = 0; i < DataM.size; i++){
					if (DataM.labelsIndex[i] > max)
						max = DataM.labelsIndex[i];
				}
            	DataM.NumCluster = max;

				DataM.CreateDataAfterClustering();
	        	 ACES.ta.setText("DBSCAN Clustering results:\n" );  

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ACES.ta.append(DataM.newDataLabel[i] + " ---- " + Integer.toString(DataM.newlabelsIndex[i]) + "\n");
	        	 }
	        	 DataM.clusteringName = "DBSCAN Clustering";
			}
			else if (e.getSource() == KMeansClustering){
				if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
            	KMClustering KMC = new KMClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
            	DataM.NumCluster = KMC.getNumCluster();
            	DataM.setLabelsIndex(KMC.getLabelsIndex()); 
            	
            	DataM.CreateDataAfterClustering();
	        	 ACES.ta.setText("KMeans Clustering results:\n" );  

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ACES.ta.append(DataM.newDataLabel[i] + " ---- " + Integer.toString(DataM.newlabelsIndex[i]) + "\n");
	        	 }
	        	 DataM.clusteringName = "KMeans Clustering";
			}
			else if(e.getSource()==HierarchicalClustering) {
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 HClustering CV = new HClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
	            	DataM.NumCluster = CV.getNumCluster();
	            	DataM.setLabelsIndex(CV.getLabelsIndex()); 
	            	
	             DataM.CreateDataAfterClustering();
	            	
	        	 ACES.ta.setText("Hierarchical Clustering results:\n" );  

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ACES.ta.append(DataM.newDataLabel[i] + " ---- " + Integer.toString(DataM.newlabelsIndex[i]) + "\n");
	        	 }
	        	 DataM.clusteringName = "Hierarchical Clustering";
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
				
				DataM.ChooseDM = (String)JOptionPane.showInputDialog(null,"choose the distance matrix you wish to plot", "Distance matrix", JOptionPane.QUESTION_MESSAGE, icon, DataM.AllCaci, DataM.AllCaci[0]);
				
				if(DataM.ChooseDM == null){
            		DataM.ChooseDM = DataM.CurrentDM;
        			return;
            	}
            	else
            		DataM.CurrentDM = DataM.ChooseDM;
				
        		ChooseOtherDM.setText("Choose the the other distance matrix (Current: " + DataM.CurrentDM + ")");

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
	    			
	    			String[] temp;
	    			temp = DataM.AttributeOriginalMatrix[4].split(",");
	    			DataM.ATSplit = ",";	    		
		    		if (temp.length == 1){
		    			DataM.ATSplit = "\\s+";
		    			temp = DataM.AttributeOriginalMatrix[4].split("\\s+");
		    		}
		    		if (temp.length == 1){
		    			DataM.ATSplit = "\t";
		    			temp = DataM.AttributeOriginalMatrix[4].split("\t");
		    		}
	    				    			
	    			for(int i = 0; i < ACactus.getSize(); i++){
	    				DataM.setAttributeLine(ACactus.getCactusData()[i].split(DataM.ATSplit));
	    				for(int j = 0; j < DataM.AttributeLine.length; j++){  	
	    					ACES.ta.append(DataM.AttributeLine[j]+"   \r");
	        			}
	    				ACES.ta.append("\n");
	    			}
	    			DataM.AttributeOpenStatus = 1;
	    			DataM.setAttributeLine(ACactus.getCactusData()[0].split(DataM.ATSplit));

		        	DataM.AttributeSize = DataM.size+1;
		        	DataM.AttributeMatrix = new String[DataM.size+1];
		        	DataM.AttributeMatrix[0] = DataM.AttributeOriginalMatrix[0];
		        	DataM.changeSampleInfo();
		        	
		        	DataM.newAttributeMatrix = new String[DataM.size+1];
					
					for(int i = 0; i < DataM.size; i++){
						DataM.newAttributeMatrix[i] = DataM.AttributeMatrix[i];
	        		}
	         
		            ShowPower.setEnabled(true);
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
		        catch (IOException ioe){
		            System.out.println(ioe);
		        }
	            
	          
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
			

				
				String[] temp1; 
                for(int i = 0; i <  DataM.size+1; i++){
        			temp1 =  DataM.newAttributeMatrix[i].split(DataM.ATSplit);
        			for(int j = 0; j <  temp1.length; j++){
    	    	    	ACES.ta.append(DataM.newAttributeMatrix[i]+"    ");
        			}
        			ACES.ta.append("\n");
        		}
				
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
		                	templine = DataM.newAttributeMatrix[i].split(DataM.ATSplit);
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
				
				
				DataM.newAttributeMatrix[0] = String.join(",",DataM.CurrentDM, DataM.newAttributeMatrix[0]);
				
				for(int i = 1; i < DataM.size+1; i++){
					DataM.newAttributeMatrix[i]= String.join(",",Integer.toString(DataM.labelsIndex[i-1]), DataM.newAttributeMatrix[i]);
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
			else if (e.getSource()==ShowPower) { 
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
					ACES.ta.setText("The discriminative power of attribute" + "\r\n");
					DataM.AttributeLabel = new String[DataM.size];
		            DataM.AttributeRank = new double[DataM.AttributeLine.length];
		            DataM.newRankAttribute = new String[DataM.AttributeLine.length];
		            
					for(int count = 1; count < DataM.AttributeLine.length; count++){	
	        			
						String[] temp1; 
	                    for(int i = 1; i <  DataM.size+1; i++){
		        			temp1 =  DataM.AttributeMatrix[i].split(DataM.ATSplit);
		        			DataM.AttributeLabel[i-1] = temp1[count];
		        		}
		                Set<String> TT = new LinkedHashSet<String>(Arrays.asList(DataM.AttributeLabel));
		                TT.remove("None");
		                TT.remove("");
		                DataM.setRefLabel(TT.toArray( new String[TT.size()] ));
		                
		                
		                if (DataM.refLabel.length==1){
		                	DataM.AttributeRank[count] = 1000000;
		                	continue;
		                }
		                int count_r = 0;
		                int count_r_max = 0;
		                for(int i = 0; i < DataM.refLabel.length; i++){
		                	count_r = 0;
		                	for(int j = 0; j < DataM.size; j++){
		                		if (DataM.AttributeLabel[j].equals(DataM.refLabel[i]) ){
		                			count_r = count_r + 1; 
		                		}

				        	}

		                	if(count_r > count_r_max) 
		                		count_r_max = count_r;
		        		}
		                	
		                if (count_r_max>DataM.size*5/6){
		                	DataM.AttributeRank[count] =  10000;
		                	continue;
		                }

		                double[][] cluster_ref_weight = new double[DataM.refLabel.length][DataM.NumCluster];
		               
		                double count_num = 0;
		                double count_cluster = 0;
		                for(int i = 0; i < DataM.refLabel.length; i++){ 
		                	count_num = 0;
		                	for(int k = 1; k < DataM.NumCluster+1; k++){
			                	for(int j = 0; j < DataM.size; j++){
			                		if (DataM.AttributeLabel[j].equals(DataM.refLabel[i])&&(DataM.labelsIndex[j] == k)){
			                			count_cluster = count_cluster + 1;
			                			count_num = count_num + 1;
			                		}
					        	} 
			                	cluster_ref_weight[i][k-1] = count_cluster;
			                	count_cluster = 0;
		                	}
		                	for(int k = 1; k < DataM.NumCluster+1; k++){
		                		cluster_ref_weight[i][k-1] = cluster_ref_weight[i][k-1]/count_num;	
		                	}
		        		}
		                double cluster_weight = 0;
		                double count_reflabel_max = 0;
		                double temp_weight = 0;

		                for(int k = 1; k < DataM.NumCluster+1; k++){
		                    count_reflabel_max = 0;
			                for(int i = 0; i < DataM.refLabel.length; i++){
			                	if(cluster_ref_weight[i][k-1] > count_reflabel_max) 
			                		count_reflabel_max = cluster_ref_weight[i][k-1];
			        		}
			                for(int i = 0; i < DataM.refLabel.length; i++){
			                	cluster_ref_weight[i][k-1] = 1-(cluster_ref_weight[i][k-1]/count_reflabel_max);
			                	temp_weight = temp_weight + cluster_ref_weight[i][k-1];

			                }
			                cluster_weight = cluster_weight + temp_weight;
			                temp_weight = 0;
		                }
		                
		                cluster_weight = cluster_weight/(DataM.refLabel.length-1);
		             
		                DataM.AttributeRank[count] = 10 - cluster_weight;
		        		//ACES.ta.append(Integer.toString(count+1)+"   "+ cluster_weight+"\n");
		                if (DataM.refLabel.length>7){
		                	DataM.AttributeRank[count] = cluster_weight + 1000;
		                	
		                }
		                
		               
	                }
					
					/*for(int i = 0; i < DataM.refLabel.length; i++){
		                	for(int j = 0; j <  DataM.size; j++){
		                		if (DataM.AttributeLabel[j].equals(DataM.refLabel[i])){
		                			DataM.AttributeIndex[j] = i+1;   
		                		}
				        	}
		        		}
		        		
					int sum = 0;
	                int countSum = 0;
	                double Smean = 0;
	                double std_temp = 0;
	                double std_cluster = 0;
	                
	                for (int j = 1; j < DataM.NumCluster+1; j++){
	                    sum = 0;
	                    countSum = 0;
	                    Smean = 0;
	                    std_temp = 0;
	                	for(int i = 0; i <  DataM.size; i++){
	                		if (DataM.labelsIndex[i] == j){
			    				countSum = countSum + 1;
			        			sum = sum + DataM.AttributeIndex[i];   					        	
			    			}
		        		}
		                Smean = sum/countSum;
		                for(int i = 0; i <  DataM.size; i++){
	                		if (DataM.labelsIndex[i] == j){
	                			std_temp = std_temp + (DataM.AttributeIndex[i]-Smean)*(DataM.AttributeIndex[i]-Smean);   					        	
			    			}
		        		}
		                std_cluster = std_cluster + std_temp/countSum;
	                }
	               // std_cluster = std_cluster/DataM.NumCluster;
	                DataM.AttributeRank[count] = std_cluster;
	                if (DataM.refLabel.length>7)
	                	DataM.AttributeRank[count] = std_cluster + 1000;
                }
				*/
					
					DataM.AttributeRank[0] = 1000000;     
	        
	                double[] Rank = new double[DataM.AttributeLine.length];
	 
		        	for(int count = 0; count < DataM.AttributeLine.length; count++){
		        		Rank[count] = DataM.AttributeRank[count];
		        		//ACES.ta.append(Integer.toString(count+1)+"   "+ DataM.AttributeLine[count] + "   "+Rank[count]+"\n");
					}
		        	Arrays.sort(Rank);
		        	int index1 = 0; int index2 = 0;
		        	for(int i = 0; i < DataM.AttributeLine.length; i++){
		        			if(Rank[0] == DataM.AttributeRank[i]){
		        				DataM.newRankAttribute[0] = DataM.AttributeLine[i];
		        				index2 = i;
			        			break;
		        			}
		        		}
	        			ACES.ta.append(Integer.toString(1)+"   "+ DataM.newRankAttribute[0] +"\n");
	        			
	        	    
	        	    
		        	for(int count = 1; count < DataM.AttributeLine.length; count++){
		        		
		        		if (Rank[count] != Rank[count-1]){
		        			for(int i = 0; i < DataM.AttributeLine.length; i++){
			        			if(Rank[count] == DataM.AttributeRank[i]){
			        				DataM.newRankAttribute[count] = DataM.AttributeLine[i];
			        				index2 = i;
			        				break; 			
			        			}
			        		}
		        			ACES.ta.append(Integer.toString(count+1)+"   "+ DataM.newRankAttribute[count] +"\n");
		        			index1 = 0;
		        			
		        		}
		        		else{
		        			index1 = index1+1;
		        			for(int i = index2+1; i < DataM.AttributeLine.length; i++){
			        			if(Rank[count] == DataM.AttributeRank[i]){
			        				DataM.newRankAttribute[count] = DataM.AttributeLine[i];
			        				index2 = i;
				        			break;
			        			}
			        		}
		        			ACES.ta.append(Integer.toString(count-index1+1)+"   "+ DataM.newRankAttribute[count] +"\n");
		        		}
		        		
					}
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
	        			temp1 =  DataM.AttributeMatrix[i].split(DataM.ATSplit);
	        			DataM.AttributeLabel[i-1] = temp1[count];
	        			ACES.ta.append(Integer.toString(i)+"   "+ DataM.AttributeLabel[i-1] +"\n");
	        		}
	                ACES.ta.append("\n");
	                ACES.ta.append("\n");
	               
	                Set<String> TT = new LinkedHashSet<String>(Arrays.asList(DataM.AttributeLabel));
	                TT.remove("None");
	                TT.remove("");
	                DataM.setRefLabel(TT.toArray( new String[TT.size()] ));
	                
	                Object[] message = new Object[DataM.refLabel.length+2];
	                message[0] = "selected Attribute: " + DataM.ChooseAttribute+ "\r\n"; 
	                message[1] = "\n"; 
	                for(int i = 0; i < DataM.refLabel.length; i++){
	                	message[i+2] = DataM.refLabel[i] + "\n"; 
	        		}
	        		ButtonBar.SIHeat.setEnabled(true);
	        		plotHeatMapA.setEnabled(true);

                	JOptionPane.showMessageDialog(null, message,"Unique Labels", JOptionPane.CLOSED_OPTION, icon);
                
	                DataM.AttributeChooseStatus = 1;
	        	}
                
             }
			 else if (e.getSource() == plotHeatMapA){
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
			 else if (e.getSource() == DataMatrixFormat){
	        	 ShowFormat AF = new ShowFormat();
	        	 AF.show_O_Format(ACES.ta);	  
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
	        	 if (DataM.ChooseAttribute == "attribute"){
	        		 DataM.CreateDataAfterClustering();

		        	 try {
						new Visualization(DataM.newData, DataM.size,"Distance Matrix after Clustering", DataM.newDataLabel, DataM.newDataLabel);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        	 }else{
	        		 DataM.CreateDataAfterClusteringandChooseAttri();

		        	 try {
						new Visualization(DataM.newData, DataM.size,"Distance Matrix after Clustering: "+ " "+DataM.ChooseAttribute+ " ", DataM.newAttributeLabel,DataM.newDataLabel,DataM.newlabelsIndex);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	 }	        	 	 
	         }
	         else if(e.getSource()==plotSamples){
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 new Visualization(DataM.getLabelsIndex(), DataM.getLabel(), DataM.getDataAxis(), DataM.size, DataM.CurrentDM, DataM.clusteringName);
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