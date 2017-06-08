package Frame;

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
import javax.swing.SwingConstants;

import CactusClusteringVisualization.Cactus;
import CactusClusteringVisualization.Clustering;
import CactusClusteringVisualization.PCA;
import CactusClusteringVisualization.Visualization;

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
	final ImageIcon iconHMO = new ImageIcon(getClass().getResource("/resources/HeatMapO.png"));
	final ImageIcon iconHMC = new ImageIcon(getClass().getResource("/resources/HeatMapC.png"));
	final ImageIcon iconHMA = new ImageIcon(getClass().getResource("/resources/HeatMap3.png"));
	
	//DataManagement DataM = new DataManagement();;
	DataManagement DataM;
	public ButtonBar(DataManagement Data) {
		DataM = Data;
	}
	
	public JPanel makeDM(){
	
		DMPanel = new JPanel();
		DMPanel.setLayout(new BorderLayout());
		DMPanel.setVisible(true);
		DMPanel.setPreferredSize(new Dimension(150,500));
		DMPanel.setBackground(Color.LIGHT_GRAY);
		
		DMLabel = new JLabel();
		DMLabel.setText("Distance Matrix");
		DMLabel.setBackground(Color.RED);
		DMLabel.setPreferredSize(new Dimension(100,30));
		

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setBackground(new Color(200,200,200));

		addComp(buttonsPanel, DMLabel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		
		DMLoad = new JButton();
		DMLoad.setEnabled(true);
		DMLoad.setText("Load");
		DMLoad.setToolTipText("Load your Distance Matrix file");
		DMLoad.setPreferredSize(new Dimension(60,30));
		DMLoad.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMLoad, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		DMShow = new JButton();
		DMShow.setEnabled(false);
		DMShow.setText("Show");
		DMShow.setPreferredSize(new Dimension(60,30));
		DMShow.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMShow, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		DMLabelID = new JButton();
		DMLabelID.setEnabled(false);
		DMLabelID.setText("Label ID");
		DMLabelID.setPreferredSize(new Dimension(80,30));
		DMLabelID.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMLabelID, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		DMCluster = new JButton();
		DMCluster.setEnabled(false);
		DMCluster.setText("Clustering Results");
		DMCluster.setPreferredSize(new Dimension(150,30));
		DMCluster.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMCluster, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		DMPlot = new JButton();
		DMPlot.setEnabled(false);
		DMPlot.setText("Plot Samples");
		DMPlot.setPreferredSize(new Dimension(100,30));
		DMPlot.setHorizontalAlignment(SwingConstants.CENTER);
		
		addComp(buttonsPanel, DMPlot, 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		DMChoose = new JButton();
		DMChoose.setEnabled(false);
		DMChoose.setText("Choose other Cacti");
		DMChoose.setPreferredSize(new Dimension(150,30));
		DMChoose.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMChoose, 0, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		DMHeatO = new JButton();
		DMHeatO.setEnabled(false);
		DMHeatO.setText("Original Heat Map");
		DMHeatO.setPreferredSize(new Dimension(150,30));
		DMHeatO.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMHeatO, 0, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		DMHeatC = new JButton();
		DMHeatC.setEnabled(false);
		DMHeatC.setText("New Heat Map");
		DMHeatC.setPreferredSize(new Dimension(150,30));
		DMHeatC.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, DMHeatC, 0, 8, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SILabel = new JLabel();
		SILabel.setText("Sample Info");
		SILabel.setBackground(Color.RED);
		SILabel.setPreferredSize(new Dimension(100,30));
		addComp(buttonsPanel, SILabel, 0, 9, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,20,2);

		
		SILoad = new JButton();
		SILoad.setEnabled(false);
		SILoad.setText("Load");
		SILoad.setPreferredSize(new Dimension(60,30));
		SILoad.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SILoad, 0, 10, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIShow = new JButton();
		SIShow.setEnabled(false);
		SIShow.setText("Show");
		SIShow.setPreferredSize(new Dimension(60,30));
		SIShow.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIShow, 0, 11, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		SIShowList = new JButton();
		SIShowList.setEnabled(false);
		SIShowList.setText("Show all Attributes");
		SIShowList.setPreferredSize(new Dimension(150,30));
		SIShowList.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIShowList, 0, 12, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIChoose = new JButton();
		SIChoose.setEnabled(false);
		SIChoose.setText("Select an Attribute");
		SIChoose.setPreferredSize(new Dimension(150,30));
		SIChoose.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIChoose, 0, 13, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		AddCluster = new JButton();
		AddCluster.setEnabled(false);
		AddCluster.setText("Add Clusters Info");
		AddCluster.setPreferredSize(new Dimension(150,30));
		AddCluster.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, AddCluster, 0, 14, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIPlot = new JButton();
		SIPlot.setEnabled(false);
		SIPlot.setText("Plot Attributes");
		SIPlot.setPreferredSize(new Dimension(80,30));
		SIPlot.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SIPlot, 0, 15, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);
		
		SISave = new JButton();
		SISave.setEnabled(false);
		SISave.setText("Save ");
		SISave.setPreferredSize(new Dimension(80,30));
		SISave.setHorizontalAlignment(SwingConstants.CENTER);
		addComp(buttonsPanel, SISave, 0, 16, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,2,2);

		SIHeat = new JButton();
		SIHeat.setEnabled(false);
		SIHeat.setText("Heat Map");
		SIHeat.setPreferredSize(new Dimension(100,30));
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
	                	
	                	Menubar.ChooseOtherDM.setEnabled(false);
	                	DataM.setLabel(oneCactus.getLabel()); 	
	                	DataM.setDataMatrix(oneCactus.getCactus());
	                	DataM.setSize(oneCactus.getSize());
	                	
	                	Clustering CV = new Clustering(oneCactus.getLabel(),oneCactus.getSize(),oneCactus.getCactus());
	                	DataM.setNumCluster(CV.getNumCluster());
	                	DataM.setLabelsIndex(CV.getLabelsIndex());
	                	
	                	PCA PCA3Axis = new PCA(3,oneCactus.getSize(),oneCactus.getSize(),oneCactus.getCactus());
	                	DataM.setDataAxis(PCA3Axis.getDataAxis());
	                	}
	                else{
	                	Menubar.ChooseOtherDM.setEnabled(true);

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
	                	
	                		
	                	Menubar.ChooseOtherDM.setEnabled(true);
	                	Menubar.ChooseOtherDM.setText("Choose the Cacti (Current: " + DataM.CurrentDM + ")");
	                	
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
	
	                	Clustering CV = new Clustering(DataM.Label,DataM.size,DataM.getDataMatrix());
	                	DataM.NumCluster = CV.getNumCluster();
	                	DataM.setLabelsIndex(CV.getLabelsIndex());
	                	
	                	
	                	PCA PCA3Axis = new PCA(3,DataM.size,DataM.size,DataM.getDataMatrix());
	                	DataM.setDataAxis(PCA3Axis.getDataAxis());
	                	
	                	for(int i = 0; i < DataM.size; i++){
		                	for(int j = 0; j < DataM.size; j++){
		                		ACES.ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
							}
		                	ACES.ta.append("\n");
		                }
	                }	
		         
	                Menubar.ShowDistanceMatrix.setEnabled(true);
	                Menubar.ShowLabels.setEnabled(true);	        
	                Menubar.plotSamples.setEnabled(true);	
	                Menubar.numberOfCluster.setEnabled(true);
	                Menubar.HierarchicalClustering.setEnabled(true);
	                Menubar.loadAttributes.setEnabled(true);
	                Menubar.plotHeatMapO.setEnabled(true);
	                Menubar.plotHeatMapC.setEnabled(true);
	              
	            	DMShow.setEnabled(true);
	            	DMLabelID.setEnabled(true);	        
	            	DMCluster.setEnabled(true);	
	            	DMChoose.setEnabled(true);
	            	DMPlot.setEnabled(true);
	            	DMHeatO.setEnabled(true);
	            	DMHeatC.setEnabled(true);
	            	SILoad.setEnabled(true);
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
      	
				ACES.ta.setText("Distance Matrix \n");
				ACES.ta.append("\n");
				for(int i = 0; i < DataM.size; i++){
					for(int j = 0; j < DataM.size; j++){
						ACES.ta.append(Double.toString(DataM.DataMatrix[i][j])+"       \r");
					}
              	ACES.ta.append("\n");
              }	
			} 
			else if (bc.getSource() == DMLabelID){
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
			else if (bc.getSource() == DMCluster){
				if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 ACES.ta.setText("Clustering results:\n" );  

	        	 for (int i = 0; i < DataM.size; i++) {
	        		 ACES.ta.append(DataM.Label[i] + " ---- " + Integer.toString(DataM.labelsIndex[i]) + "\n");
	        	 }
			} 
			else if (bc.getSource() == DMChoose){
				DataM.ChooseDM = (String)JOptionPane.showInputDialog(null,"choose the cactus you wish to plot", "Cactus", JOptionPane.QUESTION_MESSAGE, icon, DataM.getAllCaci(), DataM.getAllCaci()[0]);
				if(DataM.ChooseDM == null){
            		DataM.ChooseDM = DataM.CurrentDM;
        			return;
            	}
            	else
            		DataM.CurrentDM = DataM.ChooseDM;
        		Menubar.ChooseOtherDM.setText("Choose the Cacti (Current: " + DataM.CurrentDM + ")");

        		
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

            	Clustering CV = new Clustering(DataM.Label,DataM.size,DataM.getDataMatrix());
            	DataM.NumCluster = CV.getNumCluster();
            	DataM.setLabelsIndex(CV.getLabelsIndex());
            	
            	
            	PCA PCA3Axis = new PCA(3,DataM.size,DataM.size,DataM.getDataMatrix());
            	DataM.setDataAxis(PCA3Axis.getDataAxis());
			} 
			else if (bc.getSource() == DMPlot){
				if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 Visualization v1 = new Visualization(DataM.getLabelsIndex(), DataM.getLabel(), DataM.getDataAxis(), DataM.size, DataM.CurrentDM);
			}
			else if(bc.getSource()==DMHeatO){
	        	 if (DataM.FileOpenStatus == 0){
	        		 JOptionPane.showMessageDialog(null, "Please load the distance matrix first.", null, JOptionPane.INFORMATION_MESSAGE, icon);
	        		 return;
	        	 }
	        	 
	        	 try {
					Visualization v1 = new Visualization(DataM.DataMatrix, DataM.size,"Original Distance Matrix",DataM.Label,DataM.Label);
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
	        	 
	        	 DataM.CreateDataAfterClustering();

	        	 try {
					Visualization v1 = new Visualization(DataM.newData, DataM.size,"Distance Matrix after Clustering", DataM.newDataLabel,DataM.newDataLabel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
				ACES.ta.setText("Attributes Matrix\n");
				
				for(int i = 0; i < DataM.AttributeLine.length; i++)
	    	    	ACES.ta.append(DataM.AttributeMatrix[i]+"   \n");	
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
	              
	        	ACES.ta.setText("All attributes \n");
	        	for(int i = 0; i < DataM.AttributeLine.length; i++){
	        		ACES.ta.append(Integer.toString(i)+"   "+ DataM.AttributeLine[i]+"\r\n");
        		}
			}
			else if (bc.getSource() == AddCluster){
				DataM.AttributeMatrix[0] = String.join(",",DataM.CurrentDM, DataM.AttributeMatrix[0]);
				
				for(int i = 1; i < DataM.size+1; i++){
					DataM.AttributeMatrix[i]= String.join(",",Integer.toString(DataM.labelsIndex[i-1]), DataM.AttributeMatrix[i]);
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
	                SIHeat.setEnabled(true);
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
	        	 Visualization v2 = new Visualization(DataM.getAttributeLabel(), DataM.getRefLabel(), DataM.getDataAxis(), DataM.size, DataM.refLabel.length, DataM.ChooseAttribute);
  
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
		                	templine = DataM.AttributeMatrix[i].split(",");
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
					Visualization v1 = new Visualization(DataM.newData, DataM.size,"Distance Matrix after Clustering: "+ " "+DataM.ChooseAttribute+ " ", DataM.newAttributeLabel,DataM.newDataLabel,DataM.newlabelsIndex);
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
