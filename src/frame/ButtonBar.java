package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import data.*;
import clustering.*;
import visualization.*;


public class ButtonBar extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel DMPanel, SIPanel, buttonsPanel;
	JLabel DMLabel, SILabel;
	
	static JButton DMLoad,DMShow,DMLabelID,DMCluster,DMChoose,DMPlot,DMHeatO,DMHeatC;
	static JButton SILoad,SIShow,SIShowList,AddCluster,SIChoose,SIPlot,SISave,SIHeat;
	final ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));
	final ImageIcon iconOpen = new ImageIcon(getClass().getResource("/resources/openFile.png"));

	final ImageIcon iconHMO = new ImageIcon(getClass().getResource("/resources/HeatMapO.png"));
	final ImageIcon iconHMC = new ImageIcon(getClass().getResource("/resources/HeatMapC.png"));
	final ImageIcon iconDataDM = new ImageIcon(getClass().getResource("/resources/data.png"));
	final ImageIcon iconSave = new ImageIcon(getClass().getResource("/resources/save.png"));
	final ImageIcon iconHC = new ImageIcon(getClass().getResource("/resources/HC.png"));
	final ImageIcon iconAdd = new ImageIcon(getClass().getResource("/resources/add.png"));
	final ImageIcon iconCO = new ImageIcon(getClass().getResource("/resources/chooseA.png"));
	final ImageIcon iconCA = new ImageIcon(getClass().getResource("/resources/chooseO.png"));
	final ImageIcon iconID = new ImageIcon(getClass().getResource("/resources/ID.png"));
	final ImageIcon iconlist = new ImageIcon(getClass().getResource("/resources/list.png"));
	final ImageIcon iconPlot = new ImageIcon(getClass().getResource("/resources/plot.png"));

	
	//DataManagement DataM = new DataManagement();;
	DataManagement DataM;
	public ButtonBar(DataManagement Data) {
		DataM = Data;
	}
	
	public JPanel makeDM(){
	
		DMPanel = new JPanel();
		DMPanel.setLayout(new BorderLayout());
		DMPanel.setVisible(true);
		DMPanel.setPreferredSize(new Dimension(50,500));
		DMPanel.setBackground(Color.LIGHT_GRAY);
		
		DMLabel = new JLabel();
		DMLabel.setText("");
		DMLabel.setBackground(Color.RED);
		DMLabel.setPreferredSize(new Dimension(50,30));
		

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setBackground(Color.WHITE);

		addComp(buttonsPanel, DMLabel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		
		DMLoad = new JButton(iconOpen);
		DMLoad.setBorderPainted(false);
		DMLoad.setEnabled(true);
		DMLoad.setToolTipText("Load your Distance Matrix file");
		DMLoad.setPreferredSize(new Dimension(30,30));
		DMLoad.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMLoad, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		DMShow = new JButton(iconDataDM);
		DMShow.setBorderPainted(false);
		DMShow.setEnabled(false);
		DMShow.setToolTipText("Distance Matrix data");
		DMShow.setPreferredSize(new Dimension(30,30));
		DMShow.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMShow, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		DMLabelID = new JButton(iconID);
		DMLabelID.setEnabled(false);
		DMLabelID.setBorderPainted(false);
		DMLabelID.setToolTipText("Label ID");
		DMLabelID.setPreferredSize(new Dimension(30,30));
		DMLabelID.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMLabelID, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		DMCluster = new JButton(iconHC);
		DMCluster.setEnabled(false);
		DMCluster.setBorderPainted(false);
		DMCluster.setToolTipText("Clustering Results");
		DMCluster.setPreferredSize(new Dimension(30,30));
		DMCluster.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMCluster, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
	
		
		
		DMPlot = new JButton(iconPlot);
		DMPlot.setEnabled(false);
		DMPlot.setBorderPainted(false);
		DMPlot.setToolTipText("Plot your Distance Matrix");
		DMPlot.setPreferredSize(new Dimension(30,30));
		DMPlot.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMPlot, 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		DMChoose = new JButton(iconCO);
		DMChoose.setEnabled(false);
		DMChoose.setBorderPainted(false);
		DMChoose.setToolTipText("Choose the other distance matrix");
		DMChoose.setPreferredSize(new Dimension(30,30));
		DMChoose.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMChoose, 0, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		DMHeatO = new JButton(iconHMO);
		DMHeatO.setEnabled(false);
		DMHeatO.setBorderPainted(false);
		DMHeatO.setToolTipText("Show the Original Heat Map of DM");
		DMHeatO.setPreferredSize(new Dimension(30,30));
		DMHeatO.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMHeatO, 0, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		DMHeatC = new JButton(iconHMC);
		DMHeatC.setEnabled(false);
		DMHeatC.setBorderPainted(false);
		DMHeatC.setToolTipText("Show the sorted Heat Map of DM");
		DMHeatC.setPreferredSize(new Dimension(30,30));
		DMHeatC.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMHeatC, 0, 8, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SILabel = new JLabel();
		SILabel.setText("-----");
		SILabel.setBackground(Color.RED);
		SILabel.setPreferredSize(new Dimension(50,30));
		addComp(buttonsPanel, SILabel, 0, 9, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,2);

		
		SILoad = new JButton(iconOpen);
		SILoad.setEnabled(false);
		SILoad.setBorderPainted(false);
		SILoad.setToolTipText("Load the Sample Info file");
		SILoad.setPreferredSize(new Dimension(30,30));
		SILoad.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SILoad, 0, 10, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIShow = new JButton(iconDataDM);
		SIShow.setEnabled(false);
		SIShow.setBorderPainted(false);
		SIShow.setToolTipText("Show the Sample Info content");
		SIShow.setPreferredSize(new Dimension(30,30));
		SIShow.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIShow, 0, 11, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		SIShowList = new JButton(iconlist);
		SIShowList.setEnabled(false);
		SIShowList.setBorderPainted(false);
		SIShowList.setToolTipText("Show all Attributes");
		SIShowList.setPreferredSize(new Dimension(30,30));
		SIShowList.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIShowList, 0, 12, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIChoose = new JButton(iconCA);
		SIChoose.setEnabled(false);
		SIChoose.setBorderPainted(false);
		SIChoose.setToolTipText("Select an Attribute to plot");
		SIChoose.setPreferredSize(new Dimension(30,30));
		SIChoose.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIChoose, 0, 13, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		AddCluster = new JButton(iconAdd);
		AddCluster.setEnabled(false);
		AddCluster.setBorderPainted(false);
		AddCluster.setToolTipText("Add the Clusters Info to the Sample Info file");
		AddCluster.setPreferredSize(new Dimension(30,30));
		AddCluster.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, AddCluster, 0, 14, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIPlot = new JButton(iconPlot);
		SIPlot.setEnabled(false);
		SIPlot.setBorderPainted(false);
		SIPlot.setToolTipText("Plot Attributes");
		SIPlot.setPreferredSize(new Dimension(30,30));
		SIPlot.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIPlot, 0, 15, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		SISave = new JButton(iconSave);
		SISave.setEnabled(false);
		SISave.setBorderPainted(false);
		SISave.setToolTipText("Save the sorted Sample Info file");
		SISave.setPreferredSize(new Dimension(30,30));
		SISave.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SISave, 0, 16, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIHeat = new JButton(iconHMC);
		SIHeat.setEnabled(false);
		SIHeat.setBorderPainted(false);
		SIHeat.setToolTipText("Show the Heat Map with selected attribute info");
		SIHeat.setPreferredSize(new Dimension(30,30));
		SIHeat.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIHeat, 0, 17, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		DMPanel.add(buttonsPanel, BorderLayout.NORTH);
		
		ListenForButton lForButton = new ListenForButton();
		DMLoad.addActionListener(lForButton);
		DMShow.addActionListener(lForButton);
		DMLabelID.addActionListener(lForButton);
		DMCluster.addActionListener(lForButton);
		DMChoose.addActionListener(lForButton);
		DMPlot.addActionListener(lForButton);
		DMHeatO.addActionListener(lForButton);
		DMHeatC.addActionListener(lForButton);
		SILoad.addActionListener(lForButton);
		SIShow.addActionListener(lForButton);
		SIShowList.addActionListener(lForButton);
		AddCluster.addActionListener(lForButton);
		SIChoose.addActionListener(lForButton);
		SIPlot.addActionListener(lForButton);
		SISave.addActionListener(lForButton);
		SIHeat.addActionListener(lForButton);

		
		return DMPanel;
		
		
	}
	
	private class ListenForButton implements ActionListener{

		public void actionPerformed(ActionEvent bc) {
			
			if (bc.getSource() == DMLoad){
				DataM.fd = new FileDialog(ACES.bodyFrame,"Open",FileDialog.LOAD);
				DataM.fd.setVisible(true);  
                
                if (DataM.fd.getFile()==null)
	            	  return;
                
                try {   
                	DataM.file1 = new File(DataM.fd.getDirectory(),DataM.fd.getFile());
                    
                    DataM.FileOpenStatus = 1;
                    DataM.currentFilename = DataM.file1.getName();
                    DataM.ChooseAttribute = "attribute";	                		              
                    Menubar.ChooseAttributes.setEnabled(false);
                    Menubar.ShowAttributes.setEnabled(false);
                    Menubar.ShowAttributesMatrix.setEnabled(false);
                    Menubar.addClusteringResults.setEnabled(false);
                    Menubar.saveAttributes.setEnabled(false);
    	    		
                    Menubar.plotAttributes.setEnabled(false); 
    	    		
    	    		ButtonBar.SIChoose.setEnabled(false);
    	    		ButtonBar.SIShowList.setEnabled(false);
    	    		ButtonBar.SIShow.setEnabled(false);
    	    		ButtonBar.AddCluster.setEnabled(false);
    	    		ButtonBar.SISave.setEnabled(false);		
    	    		ButtonBar.SIPlot.setEnabled(false);
                    
                	Cactus oneCactus = new Cactus(DataM.fd.getDirectory()+DataM.fd.getFile());

	                if (oneCactus.getAllCacti() == null){
	                	
	                	FileReader fr = new FileReader(DataM.file1);
	                    BufferedReader br = new BufferedReader(fr);
	                    String aline;
	                  
	                    JTextArea ta = new JTextArea();
	            		JScrollPane sp = new JScrollPane(ta);        	
	            		ACES.drawingPanel.addTab(DataM.file1.getName(), sp);
	                    ta.setText("\n");
	                    ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

	                    while ((aline=br.readLine()) != null )
		                    ta.append(aline+"\r\n");
	                    ta.setCaretPosition(0);

	                    fr.close();
	                    br.close();
	                	
	                    Menubar.ChooseOtherDM.setEnabled(false);

	                	DataM.setLabel(oneCactus.getLabel()); 	
	                	DataM.setDataMatrix(oneCactus.getCactus());
	                	DataM.setSize(oneCactus.getSize());
	                	
	                	HClustering CV = new HClustering(oneCactus.getLabel(),oneCactus.getSize(),oneCactus.getCactus());
	                	DataM.setNumCluster(CV.getNumCluster());
	                	DataM.setLabelsIndex(CV.getLabelsIndex());
	                	
	                	PCA PCA3Axis = new PCA(3,oneCactus.getSize(),oneCactus.getSize(),oneCactus.getCactus());
	                	DataM.setDataAxis(PCA3Axis.getDataAxis());
	                	
	                	Menubar.menuCurrent.setVisible(true);
	                	Menubar.CurrentFile = "    Current File: ("+DataM.file1.getName()+")";
	                	Menubar.menuCurrent.setText(Menubar.CurrentFile);  
    	        		
			
	                }
	             	else{
	             		FileReader fr = new FileReader(DataM.file1);
	                    BufferedReader br = new BufferedReader(fr);
	                    String aline;
	                  
	                    JTextArea ta = new JTextArea();
	            		JScrollPane sp = new JScrollPane(ta);  
	            		
	                    ta.setText("\n");
	                    while ((aline=br.readLine()) != null )
		                    ta.append(aline+"\r\n");
	                    
	                    ta.setCaretPosition(0);

	                    fr.close();
	                    br.close();
	             		
	             		
	                    Menubar.ChooseOtherDM.setEnabled(true);

	                	DataM.setLabel(oneCactus.getLabel()); 
	                	DataM.setSize(DataM.getLabel().length);
	                	DataM.setAllDataMatrix(oneCactus.getCactus());
	                	DataM.setAllCaci(oneCactus.getAllCacti());
	                	DataM.currentFilename = DataM.file1.getName();
	                	
	                	DataM.ChooseDM = (String)JOptionPane.showInputDialog(null,"choose the distance matrix you wish to plot", "Distance matrix", JOptionPane.QUESTION_MESSAGE, icon, DataM.getAllCaci(), DataM.getAllCaci()[0]);
		        		
	                	if(DataM.ChooseDM == null){
	                		DataM.ChooseDM = DataM.CurrentDM;
		        			return;
	                	}
	                	else
	                		DataM.CurrentDM = DataM.ChooseDM;
	           	
	                	Menubar.ChooseOtherDM.setEnabled(true);
	                	Menubar.ChooseOtherDM.setText("Select other distance matrix (Current: " + DataM.CurrentDM + ")");

		        		
	                    
	            		ACES.drawingPanel.addTab(DataM.CurrentDM, sp);                   
	                    ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

		        		ta.setText(DataM.CurrentDM + "\r\n");
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
		                		ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
							}
		                	ta.append("\n");
		                }
	                    ta.setCaretPosition(0);

		        		Menubar.menuCurrent.setVisible(true);
		        		Menubar.CurrentFile = "    Current File: ("+DataM.file1.getName()+")";
	                	Menubar.menuCurrent.setText(Menubar.CurrentFile+ " ("+ DataM.CurrentDM + ")"); 
	                }
           
	                Menubar.ShowDistanceMatrix.setEnabled(true);
	        		Menubar.ShowLabels.setEnabled(true);
	        		Menubar.plot3D.setEnabled(true);
	        		Menubar.plotHeatMap.setEnabled(true);
	        		Menubar.plotSamples.setEnabled(true);	
	        		Menubar.plotHeatMapO.setEnabled(true);
	        		Menubar.plotHeatMapC.setEnabled(true);
	        		Menubar.clustering.setEnabled(true);
	        		Menubar.numberOfCluster.setEnabled(true);
	        		Menubar.HierarchicalClustering.setEnabled(true);
	        		Menubar.KMeansClustering.setEnabled(true);
	        		Menubar.DBSCAN.setEnabled(true);

	        		Menubar.loadAttributes.setEnabled(true);
	        		
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
			else if (bc.getSource() == DMShow){
				if (DataM.FileOpenStatus == 0){
	        		  JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		  return;
	        	}
      	
				JTextArea ta = new JTextArea();
	        	JScrollPane sp = new JScrollPane(ta);        	
	        	ACES.drawingPanel.addTab("Distance Matrix", sp);
	            ta.setText("Distance Matrix \n");
	            ta.append("\n");
	            ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

              for(int i = 0; i < DataM.size; i++){
              	for(int j = 0; j < DataM.size; j++){
              		ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
					}
              	ta.append("\n");
              }	
              ta.setCaretPosition(0);

			} 
			else if (bc.getSource() == DMLabelID){
				if (DataM.FileOpenStatus == 0){
	        		JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
        	
				JTextArea ta = new JTextArea();
	        	JScrollPane sp = new JScrollPane(ta);        	
	        	ACES.drawingPanel.addTab("Labels", sp);
	            ta.setText("Labels \n");
				ta.append("\n");
				ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

                for(int i = 0; i < DataM.size; i++){
                	ta.append(Integer.toString(i+1) + "     " + DataM.Label[i] +"\n");
                }
                ta.setCaretPosition(0);

			} 
			else if (bc.getSource() == DMCluster){
				if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 HClustering CV = new HClustering(DataM.Label,DataM.size,DataM.getDataMatrix());
	            	DataM.NumCluster = CV.getNumCluster();
	            	DataM.setLabelsIndex(CV.getLabelsIndex()); 
	            	
	             DataM.CreateDataAfterClustering();
	            
	             JTextArea ta = new JTextArea();
	        	 JScrollPane sp = new JScrollPane(ta);        	
	        	 ACES.drawingPanel.addTab("Hierarchical", sp);
	             ta.setText("Hierarchical Clustering results:\n" );  
	             ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ta.append(DataM.newDataLabel[i] + " ---- " + Integer.toString(DataM.newlabelsIndex[i]) + "\n");
	        	 }
	        	 DataM.clusteringName = "Hierarchical Clustering";
                 ta.setCaretPosition(0);

			} 
			else if (bc.getSource() == DMChoose){
				DataM.ChooseDM = (String)JOptionPane.showInputDialog(null,"choose the distance matrix you wish to plot", "Distance matrix", JOptionPane.QUESTION_MESSAGE, icon, DataM.AllCaci, DataM.AllCaci[0]);
				
				if(DataM.ChooseDM == null){
            		DataM.ChooseDM = DataM.CurrentDM;
        			return;
            	}
            	else
            		DataM.CurrentDM = DataM.ChooseDM;
				
        		Menubar.ChooseOtherDM.setText("Select other distance matrix (Current: " + DataM.CurrentDM + ")");

            	//JOptionPane.showMessageDialog(null, DataM.CurrentDM+" will be opened",null,JOptionPane.INFORMATION_MESSAGE,icon);
        		
        		JTextArea ta = new JTextArea();
	        	JScrollPane sp = new JScrollPane(ta);        	
	        	ACES.drawingPanel.addTab(DataM.CurrentDM, sp);
	            ta.setText(DataM.CurrentDM + "\r\n");
	            ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

        		for(int i = 0; i < DataM.size; i++){
                	for(int j = 0; j < DataM.size; j++){
                		ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
					}
                	ta.append("\n");
                }
                ta.setCaretPosition(0);

              
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
            	
            	Menubar.CurrentFile = "    Current File: ("+DataM.file1.getName()+")";
            	Menubar.menuCurrent.setText(Menubar.CurrentFile+ " ("+ DataM.CurrentDM + ")"); 
			} 
			else if (bc.getSource() == DMPlot){
				if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 new Visualization(DataM.getLabelsIndex(), DataM.getLabel(), DataM.getDataAxis(), DataM.size, DataM.currentFilename, DataM.CurrentDM, DataM.clusteringName);
			}
			else if(bc.getSource()==DMHeatO){
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
	         else if(bc.getSource()==DMHeatC){
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
			else if (bc.getSource() == SILoad){
				if (DataM.FileOpenStatus == 0){
					JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		return;
	        	}
	        	  
	        	 // JOptionPane.showMessageDialog(null, "The sampleInfo file should be sorted as the same order of Distance Matrix file:\nBoth the Distance Matrix label and SampleInfo Label should be consistent.", null, JOptionPane.INFORMATION_MESSAGE, icon);
      		 
				DataM.fd = new FileDialog(ACES.bodyFrame,"Open",FileDialog.LOAD);
				DataM.fd.setVisible(true);  
	            
	            if (DataM.fd.getFile()==null)
	            	return;


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
		    		
		    		JTextArea ta = new JTextArea();
		        	JScrollPane sp = new JScrollPane(ta);        	
		        	ACES.drawingPanel.addTab("SampleInfo", sp);
		            ta.setText("SampleInfo \n");
		            ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);
    			
	    			for(int i = 0; i < ACactus.getSize(); i++){
	    				DataM.setAttributeLine(ACactus.getCactusData()[i].split(DataM.ATSplit));
	    				for(int j = 0; j < DataM.AttributeLine.length; j++){  	
	    					ta.append(DataM.AttributeLine[j]+"   \r");
	        			}
	    				ta.append("\n");
	    			}
	    			
                    ta.setCaretPosition(0);

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
		        	  
		            Menubar.ShowPower.setEnabled(true);
		            Menubar.ChooseAttributes.setEnabled(true);
		            Menubar.ShowAttributes.setEnabled(true);
		            Menubar.ShowAttributesMatrix.setEnabled(true);
		            Menubar.addClusteringResults.setEnabled(true);
		            Menubar.saveAttributes.setEnabled(true);
		    		
		            Menubar.plotAttributes.setEnabled(true); 
		            
		            
		            SIChoose.setEnabled(true);
		            SIShowList.setEnabled(true);
		            SIShow.setEnabled(true);
		            AddCluster.setEnabled(true);
		            SISave.setEnabled(true);
		    		
		            SIPlot.setEnabled(true);
	            }
		        catch (IOException ioe){
		            System.out.println(ioe);
		        }
	            
	            
			}
			else if (bc.getSource() == SIShow){
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
	        	
	        	JTextArea ta = new JTextArea();
	        	JScrollPane sp = new JScrollPane(ta);        	
	        	ACES.drawingPanel.addTab("Sorted SampleInfo", sp);
	            ta.setText("Attributes Matrix:\n");		
	            ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

				String[] temp1; 
                for(int i = 0; i <  DataM.size+1; i++){
        			temp1 =  DataM.newAttributeMatrix[i].split(DataM.ATSplit);
        			for(int j = 0; j <  temp1.length; j++){
    	    	    	ta.append(DataM.newAttributeMatrix[i]+"    ");
        			}
        			ta.append("\n");
        		}
                
                ta.setCaretPosition(0);

			}
			else if (bc.getSource() == SIShowList){
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
	        	
	        	JTextArea ta = new JTextArea();
	        	JScrollPane sp = new JScrollPane(ta);        	
	        	ACES.drawingPanel.addTab("All attributes", sp);
	            ta.setText("All attributes \n");
	            ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

	        	for(int i = 0; i < DataM.AttributeLine.length; i++){
	        		ta.append(Integer.toString(i)+"   "+ DataM.AttributeLine[i]+"\r\n");
        		}
	        	
                ta.setCaretPosition(0);

			}
			else if (bc.getSource() == AddCluster){
				DataM.newAttributeMatrix = new String[DataM.size+1];
				
				for(int i = 0; i < DataM.size; i++){
					DataM.newAttributeMatrix[i] = DataM.AttributeMatrix[i];
        		}
				DataM.newAttributeMatrix[0] = String.join(",",DataM.CurrentDM, DataM.newAttributeMatrix[0]);
				
				for(int i = 1; i < DataM.size+1; i++){
					DataM.newAttributeMatrix[i]= String.join(",",Integer.toString(DataM.labelsIndex[i-1]), DataM.newAttributeMatrix[i]);
        		}	
                JOptionPane.showMessageDialog(null, "The Clustering results of ("+DataM.CurrentDM + ") has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE, icon);

			}
			else if (bc.getSource() == SIChoose){
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
		        	
		        	JTextArea ta = new JTextArea();
		        	JScrollPane sp = new JScrollPane(ta);        	
		        	ACES.drawingPanel.addTab(DataM.ChooseAttribute, sp);
		            ta.setText("selected Attribute: " + DataM.ChooseAttribute+ "\r\n");
		            ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);
 
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
	        			ta.append(Integer.toString(i)+"   "+ DataM.AttributeLabel[i-1] +"\n");
	        		}
	                ta.append("\n");
	                ta.append("\n");
                    ta.setCaretPosition(0);

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
	        		Menubar.plotHeatMapA.setEnabled(true);

                	JOptionPane.showMessageDialog(null, message,"Unique Labels", JOptionPane.CLOSED_OPTION, icon);
                
	                DataM.AttributeChooseStatus = 1;
	        	}	
	        	
			}
			else if (bc.getSource() == SIPlot){
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
	        	 new Visualization(DataM.getAttributeLabel(), DataM.getRefLabel(), DataM.getDataAxis(), DataM.size, DataM.refLabel.length, DataM.ChooseAttribute, DataM.currentFilename);
  
			}
			else if (bc.getSource() == SISave){
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

		                JOptionPane.showMessageDialog(null, "File has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE,icon);
		         
		            } catch (IOException ioe) {
		            	ioe.printStackTrace();
		            }
		        }else if(sf == JFileChooser.CANCEL_OPTION){
		            JOptionPane.showMessageDialog(null, "File save has been canceled");
		        }	
			}
			else if(bc.getSource()==SIHeat){
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
			
		}
	}
	
	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch, int above, int below){
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		
		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		//insets (above, left,below, right)
		gridConstraints.insets = new Insets(above,3,below,3);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;
		
		thePanel.add(comp, gridConstraints);	
	}
	
}
