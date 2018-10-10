package data;


import java.util.*;
import java.awt.FileDialog;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frame.ACES;

public class DataManagement {
	public FileDialog fd; //read the files
	public File file1 = null;
	final ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));

	public int FileOpenStatus = 0; //check whether the distance matrix files have been loaded first
	public int AttributeOpenStatus = 0; 
	public int AttributeChooseStatus = 0;
	public int RawFileLoadStatus = 0;
	public String currentFilename;

	public double DBr = 1; //DBSCAN eps
	public int DBm = 10;  //DBSCAN minPts
	
	public String[] Label;
	public String[] newDataLabel;
	public int size;
	
	public double[][] OriginalDataMatrix;
	public int sampleSize;
	public int Row;
	public int Column;
	public int direction = 0;
	public int disoption = 0;
	public String disoptionName = "Manhattan distance";
	public int LineNo;

	public String clusteringName = "Hierarchical Clustering";
	public int NumCluster;
 	public int[] labelsIndex;
 	public int[] newlabelsIndex;
	public double[][] DataAxis;// the location of each point in 3D
	public double[][] DataMatrix;

	public double[][] newData;  //for heatmap after clustering

	public double[][] AllDataMatrix;
	public String[] AllCaci;
	public String ChooseDM = "current Distance Matrix";
	public String CurrentDM = "current Distance Matrix";

	public String[] AttributeLine; //the list of all the attributes
	public static String[] AttributeMatrix; //all the attributes info
	public String[] newAttributeMatrix; //all the attributes info

	public String[] AttributeOriginalMatrix; //all the original attributes info
	public String ChooseAttribute = "attribute"; // selected attribute
	public String[] AttributeLabel;//list all the labels belong to the selected attribute
	public String[] newAttributeLabel;
	public String[] newRankAttribute;
	public double[] AttributeRank;
	public String ATSplit = ",";

	public String[] refLabel;
	public int AttributeSize;
	public int AttributeOriginalSize;
	public String[] SampleInfoLabel;
	
	public double[][] distanceBank;
	
	
	public DataManagement(){	 
	}
	
	
	public void CreateDataAfterClustering(){
		 newData = new double[size][size];
    	 newDataLabel = new String[size];
    	 newlabelsIndex = new int[size];
    	 int[] index = new int[size];
    	 
    	 int count = 1;
    	 int num = 0;
    	 for(int i = 0; i< size; i++){
    		 if(getLabelsIndex()[i]==0)
    			 count = 0;
    	 }
    	 do{
    		 for(int i = 0; i< size ; i++){
    			 if(getLabelsIndex()[i] == count){
    				 index[num] = i;
    				 num = num + 1;
    			 }
    		 }
    		 count = count +1;

    	 }while(count <= getNumCluster());
    		
  	
    	 for(int i = 0; i< size; i++){
    		 for(int j = 0; j< size; j++){
    			 newData[i][j] = DataMatrix[index[i]][index[j]];
			 }
    		  newDataLabel[i] = Label[index[i]];
    		  newlabelsIndex[i] = getLabelsIndex()[index[i]];
		 }
	}
	
	public void CreateDataAfterClusteringandChooseAttri(){
		 newData = new double[size][size];
    	 newDataLabel = new String[size];
		 newlabelsIndex = new int[size];
	   	 newAttributeLabel = new String[size];
	   	 int[] index = new int[size];
	   	 
	   	 int count = 1;
	   	 int num = 0;
	   	 
	   	 do{
	   		 for(int i = 0; i< size ; i++){
	   			 if(getLabelsIndex()[i] == count){
	   				 index[num] = i;
	   				 num = num + 1;
	   			 }
	   		 }
	   		 count = count +1;
	
	   	 }while(count <= getNumCluster());
	   	 
	   	int[] LI = new int[size];
		   
	    for (int i = 0; i < size; i++){
	    	LI[i] = 0; 
	    	for(int j = 0; j < refLabel.length; j++){
	    		if (AttributeLabel[i].equals(refLabel[j]))
	    			LI[i] = j+1;  		
	    	}
	    }
	    for(int i = 0; i< size; i++){
	    	for(int j = 0; j< size; j++){
   				newData[i][j] = DataMatrix[index[i]][index[j]];
			}
   		    newDataLabel[i] = Label[index[i]];
   		    newAttributeLabel[i] = AttributeLabel[index[i]];
   		 	newlabelsIndex[i] = LI[index[i]];
		}
	   	
	}
	

	public void changeSampleInfo() {
		String ChooseLabel = (String)JOptionPane.showInputDialog(null,"Please choose the Label info column", "Labels", JOptionPane.QUESTION_MESSAGE, icon, AttributeLine, AttributeLine[0]);
        if(ChooseLabel == null){
        	return;
        }
        	
		int count;
        for(count = 0; count < AttributeLine.length; count++){	
			if(AttributeLine[count].equals(ChooseLabel))
			{break;}
        }

        String[] temp; 
        SampleInfoLabel = new String[AttributeOriginalSize-1];
        
        JTextArea ta = new JTextArea();
    	JScrollPane sp = new JScrollPane(ta);        	
    	ACES.drawingPanel.addTab("Sorted SampleInfo", sp);
        ta.setText("Attributes Matrix:\n");		
        ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);


        
		char[] LabelChar= Label[1].toCharArray();
		int[] LabelInt = new int[LabelChar.length];
		
		for(int i = 0; i < LabelChar.length; i++){
			LabelInt[i] = i;
		}
		
		temp = AttributeOriginalMatrix[4].split(",");
			ATSplit = ",";
		
		if (temp.length == 1){
			ATSplit = "\\s+";
			temp = AttributeOriginalMatrix[4].split("\\s+");
		}
		if (temp.length == 1){
			ATSplit = "\t";
			temp = AttributeOriginalMatrix[4].split("\t");
		}
			
		
        for(int i = 1; i < AttributeOriginalSize; i++){
    		temp = AttributeOriginalMatrix[i].split(ATSplit);
			SampleInfoLabel[i-1] = temp[count];
	        //ta.append(SampleInfoLabel[i-1] +"\n");
			}
        
		 JTextField field1 = new JTextField();
         JTextField field2 = new JTextField();
         field1.setText(Label[0]);
         field2.setText(SampleInfoLabel[0]);
        
         Object[] message = {
        		 "To make the Distance Matrix and Sample Info labels consistent, please reformat",
        		 "one or both of them according to the given examples from your input files!",
        		 "\n",
        		 "Reformat the Distance Matrix label:", field1,
        		 "Reformat the Sample Info label:", field2,
        		 "\n",
        		 "\n",
        		 "(For example, if you want to shorten the Distance Matrix label to match the Sample Info label,",
        		 "you can simply delete the extra character in the first field.)",
        		 "\n",		 
         };
         field1.getActionListeners();
         field2.getActionListeners();
         int option = JOptionPane.showConfirmDialog(null, message, "Please convert the Label IDs ", JOptionPane.OK_OPTION, JOptionPane.OK_CANCEL_OPTION,icon);
         if (option == JOptionPane.OK_OPTION)
         {
             String value1 = field1.getText();
             String value2 = field2.getText();
             
             int startrow=0, endrow=0;
  
             if ((value1.length()>0)&&(value1.length()<Label[0].length())){
            	 
            	 String[] shortLabel = value1.split("");
            	 String[] longLabel = Label[0].split("");
           	 
            	 for(int i = 0; i <longLabel.length-shortLabel.length+1;i++){
            		 if((longLabel[i].equals(shortLabel[0]))&&(longLabel[i+shortLabel.length-1].equals(shortLabel[shortLabel.length-1]))){
            			startrow = i;
           		 	 	endrow = i+shortLabel.length-1;
           		 	 	break;
            		 }
            	 }
            	 //ACES.ta.setText("new Distance Matrix Labels \n");
				 //ACES.ta.append("\n");
				 
				 for(int i = 0; i < size; i++){
					 String[] t = Label[i].split("");
               	 	 StringBuffer result = new StringBuffer();
               	 	 for(int j = startrow; j <endrow+1;j++){
               	 		 result.append(t[j]);
               	 	 }
               	 	 Label[i] =  result.toString();
               	 	 //ACES.ta.append(Integer.toString(i+1) + "     " + Label[i] +"\n");
	
                } 
             }
             if((value2.length()>0) && (value2.length()<SampleInfoLabel[0].length())){
            	 String[] shortLabel = value2.split("");
            	 String[] longLabel = SampleInfoLabel[0].split("");
	             
            	 for(int i = 0; i <longLabel.length-shortLabel.length+1;i++){
            		 if((longLabel[i].equals(shortLabel[0]))&&(longLabel[i+shortLabel.length-1].equals(shortLabel[shortLabel.length-1]))){
	            		 startrow = i;
	            		 endrow = i+shortLabel.length-1;
	            		 break;
	            	 }
	             }
	          //   ACES.ta.setText("new SampleInfo Labels \n");
	 			// ACES.ta.append("\n");
	 				 
	             for(int i = 0; i < AttributeOriginalSize-1; i++){
	            	 String[] t = SampleInfoLabel[i].split("");
	                 StringBuffer result = new StringBuffer();
	                 for(int j = startrow; j <endrow+1;j++){
	                	 result.append(t[j]);
		             }
	                 SampleInfoLabel[i] =  result.toString();
	            //     ACES.ta.append(Integer.toString(i+1) + "     " + SampleInfoLabel[i] +"\n");
	 	
	             }  
             }
             if((value1.length()==0)&&(value2.length()==0)&&(Label[0].length()!=SampleInfoLabel[0].length())){
            	 JOptionPane.showMessageDialog(null, "Please reset the labels.\nOtherwise, the attribute file cannot be applied.", null, JOptionPane.INFORMATION_MESSAGE, icon);  	  
            	 return; 
       		 }
             AttributeOriginalSize = size+1;
         }

         StringBuffer nullLine = new StringBuffer();
         
         for(int i = 0; i < AttributeLine.length; i++){
        	 nullLine.append("None").append(",");
         }
         
         if(Label[0].length()==SampleInfoLabel[0].length()){
        	 for(int i = 0; i < Label.length; i++){
        		 for(int j = 0; j < SampleInfoLabel.length; j++){
        			 if(Label[i].equals(SampleInfoLabel[j])){
        				 AttributeMatrix[i+1] = AttributeOriginalMatrix[j+1];
       			  	 }
       		     }
       		     if(AttributeMatrix[i+1] == null){
       		    	 AttributeMatrix[i+1] = nullLine.toString();	            					  
       		     }
       	  	 }	      	  	            	  
         }
         String[] temp1; 
         for(int i = 0; i <  size+1; i++){
 			temp1 =  AttributeMatrix[i].split(ATSplit);
 			for(int j = 0; j <  temp1.length; j++){
	    	    	ta.append(AttributeMatrix[i]+"\r");
 			}
 			ta.append("\n");
 		}
         ta.setCaretPosition(0);
			
	}
	
	
	public void setDBSCANparameter() {
		
		double[] temp = new double[size*(size-1)/2];
		int count = 0;
		double dist = 0;
		for (int i = 0; i < size-1; i++) 
			for(int j = i+1; j < size; j++){
				dist = 0;
				for(int h = 0; h < size; h++){
					dist = dist + (DataMatrix[i][h]-DataMatrix[j][h])*(DataMatrix[i][h]-DataMatrix[j][h]);
				}
				temp[count] = Math.sqrt(dist);
				count = count + 1;
				
		}
		Arrays.sort(temp);
		count = (int) (count/3.5);
		DBr = temp[count];
		if (size < 50)
			DBm = 2;
		else if(size>=50 && size < 100)
			DBm = 3;
		else if(size>=100 && size < 150)
			DBm = 5;
		else if(size>=150 && size < 200)
			DBm = 6;
		else 
			DBm = 7;
	
		 JTextField field1 = new JTextField();
         JTextField field2 = new JTextField();
         field1.setText(Double.toString(DBr));
         field2.setText(Integer.toString(DBm));
        
         Object[] message = {
        		 "Please set the parameters of DBSCAN",
        		 "\n",
        		 "Scan Radius (eps):", field1,
        		 "Minimum number of samples (minPts):", field2,
        		 "\n",
        		 "\n",
        		 "(The recommended value are shown above.)",
        		 "\n",		 
         };
         field1.getActionListeners();
         field2.getActionListeners();
        
         int option = JOptionPane.showConfirmDialog(null, message, "Please set the parameters of DBSCAN ", JOptionPane.OK_OPTION, JOptionPane.OK_CANCEL_OPTION,icon);
         if (option == JOptionPane.OK_OPTION){
             DBr = Double.parseDouble(field1.getText());
             DBm = Integer.parseInt(field2.getText());
         }
     }
	
	public void setDataInfo() {
	
		 JTextField field1 = new JTextField();
         JTextField field2 = new JTextField();
         JTextField field3 = new JTextField();
         
         String[] directions ={"Row","Column"};
         String[] DistanceOption={"Manhattan distance","Euclidean distance", "Pearson's correlation"};
         
         JComboBox jcd = new JComboBox(directions);
         JComboBox DisOption = new JComboBox(DistanceOption);
         disoption = 0;
         direction = 0;
         disoptionName = "Manhattan distance";
         Object[] message = {
        		 "Data Info Extraction",
        		 "\n",
        		 "Format:",
        		 "Row and Column numbers start from '1'.",
        		 "\n",
        		 "Start Row:", field1,
        		 "Start Column:", field2,
        		 "\n",
        		 "Please set the distance/correlation measurement method.",
       		      DisOption,
       		      "\n",
       		      "\n",
        		 "Please set which Row or Column is the Label ID.",
        		 "Direction (Row/Column):", jcd,
        		 "No.:", field3,	 		 
        		 "Please leave it blank if there is no label info.",
        		 "ACES will set the labels as Sample1, Sample2, Sample3 .....",
        		 "\n",
        		 "\n",
         };
         field1.getActionListeners();
         field2.getActionListeners();
         field3.getActionListeners();
         jcd.getActionListeners();
         DisOption.getActionListeners();
         int option = JOptionPane.showConfirmDialog(null, message, "Please set the parameters to extract data. ", JOptionPane.OK_OPTION, JOptionPane.OK_CANCEL_OPTION,icon);
         if (option == JOptionPane.OK_OPTION){
             Row = Integer.parseInt(field1.getText())-1;
             Column = Integer.parseInt(field2.getText())-1;
             try{
                 LineNo = Integer.parseInt(field3.getText())-1;
             } catch(Exception e){
            	 LineNo = -5; 
             }
             
             if (jcd.getSelectedItem() == "Column")
            	 direction = 1;
             if (DisOption.getSelectedItem() == "Euclidean distance"){
            	 disoptionName = "Euclidean distance";
            	 disoption = 1;
             }
             if (DisOption.getSelectedItem() == "Pearson's correlation"){
            	 disoptionName = "Pearson's correlation";
            	 disoption = 2;
             }
            	 
             
             RawFileLoadStatus = 1;
         }
         if (option == JOptionPane.NO_OPTION){
        	 RawFileLoadStatus = 0; 
        	 JOptionPane.showMessageDialog(null, "Please set those parameters.\nOtherwise, the raw data file cannot be extracted.", null, JOptionPane.INFORMATION_MESSAGE, icon);  	  
         }
         if (option == JOptionPane.CLOSED_OPTION){
        	 RawFileLoadStatus = 0; 
        	 JOptionPane.showMessageDialog(null, "Please set those parameters.\nOtherwise, the raw data file cannot be extracted.", null, JOptionPane.INFORMATION_MESSAGE, icon);  	  
         }
     }
	
	public void createDisPower(){
		
		JTextArea ta = new JTextArea();
    	JScrollPane sp = new JScrollPane(ta);        	
    	ACES.drawingPanel.addTab("Attributes Rank", sp);
        ta.setText("The discriminative power of attribute" + "\r\n");
        ACES.drawingPanel.setSelectedIndex(ACES.drawingPanel.getTabCount() - 1);

		AttributeLabel = new String[size];
        AttributeRank = new double[AttributeLine.length];
        newRankAttribute = new String[AttributeLine.length];
        
        int outputCount = 0;
        
        double[][] distanceBank = {{0.5,0,0,0,0,0,0,0,0,0},
        		{-0.5,0,0,0,0,0,0,0,0,0},
        		{0,0.866025,0,0,0,0,0,0,0,0},
        		{0,0.288675,0.816497,0,0,0,0,0,0,0},
        		{0,0.288675,0.204124,0.790569,0,0,0,0,0,0},
        		{0,0.288675,0.204124,0.158114,0.774597,0,0,0,0,0},
        		{0,0.288675,0.204124,0.158114,0.129099,0.763763,0,0,0,0},
        		{0,0.288675,0.204124,0.158114,0.129099,0.109109,0.755929,0,0,0},
        		{0,0.288675,0.204124,0.158114,0.129099,0.109109,0.0944911,0.75,0,0},
        		{0,0.288675,0.204124,0.158114,0.129099,0.109109,0.0944911,0.0833333,0.745356,0},
        		{0,0.288675,0.204124,0.158114,0.129099,0.109109,0.0944911,0.0833333,0.0745356, 0.74162}};
        
        double[][] meanWithin = new double[NumCluster][10];
        double[] meanAll = new double[10];
        int[] clusterWithinNum = new int[NumCluster];
        double Sb;
        double Sw;
        
		for(int count = 1; count < AttributeLine.length; count++){				
			String[] temp1; 
            for(int i = 1; i < size+1; i++){
    			temp1 = AttributeMatrix[i].split(ATSplit);
    			AttributeLabel[i-1] = temp1[count];
    		}
            Set<String> TT = new LinkedHashSet<String>(Arrays.asList(AttributeLabel));
            TT.remove("None");
            TT.remove("");
            setRefLabel(TT.toArray( new String[TT.size()] ));
          
            if (refLabel.length==1){
            	AttributeRank[count] = 1000000; // no discriminative power
            	continue;
            }
            if (refLabel.length>10){
            	AttributeRank[count] = 1000000; // no discriminative power
            	continue;
            }
            
            outputCount = outputCount + 1;
            
            for(int i = 0; i < NumCluster; i++){ 
            	clusterWithinNum[i] = 0;
            	for(int j = 0; j < 10; j++){
            		meanWithin[i][j] = 0;
            		meanAll[j] = 0;
            	}
            }
   
            //sum the values for mean
            for(int i = 0; i < refLabel.length; i++){ 
            	for(int k = 1; k < NumCluster+1; k++){
            		for(int j = 0; j < size; j++){
                		if (AttributeLabel[j].equals(refLabel[i])&&(labelsIndex[j] == k)){
                			for(int m = 0; m<10;m++){
                				meanWithin[k-1][m] = meanWithin[k-1][m] + distanceBank[i][m]; 
                				meanAll[m] = meanAll[m] + distanceBank[i][m];
                			}  
                			clusterWithinNum[k-1] = clusterWithinNum[k-1] + 1;
                		}
		        	} 		
            	}       	
            }
            
            for(int k = 1; k < NumCluster+1; k++){
	            for(int m = 0; m<10;m++){
					meanWithin[k-1][m] = meanWithin[k-1][m]/clusterWithinNum[k-1];
				}
			}
            
            for(int m = 0; m<10;m++){
        		meanAll[m] = meanAll[m]/size;               				
			}
            
            Sb = 0;
            double[] tempmean = new double[10];
            
            // calculate Sb
            for(int k = 0; k < NumCluster; k++){
            	for(int m = 0; m<10;m++){
            		tempmean[m] = meanWithin[k][m];               				
    			}
	            Sb = Sb + distance(tempmean,meanAll)*clusterWithinNum[k];
				
			}
            
		    // calculate Sw
		    Sw = 0;
		    double[] tempValue = new double[10];
		    
			for(int k = 1; k < NumCluster+1; k++){ 
				for(int m = 0; m<10;m++){
            		tempmean[m] = meanWithin[k-1][m];               				
    			}
				for(int i = 0; i < refLabel.length; i++){
            		for(int j = 0; j < size; j++){
                		if (AttributeLabel[j].equals(refLabel[i])&&(labelsIndex[j] == k)){
                			for(int m = 0; m<10;m++){
                				tempValue[m] = distanceBank[i][m];               				
                			}
                			Sw = Sw + distance(tempValue,tempmean);
                		}
		        	}       				
    			}
            }

            AttributeRank[count] = -Sb/Sw;
        }
		
		AttributeRank[0] = 1000000;     

        double[] Rank = new double[AttributeLine.length];

    	for(int count = 0; count < AttributeLine.length; count++){
    		Rank[count] = AttributeRank[count];
    		//ACES.ta.append(Integer.toString(count+1)+"   "+ DataM.AttributeLine[count] + "   "+Rank[count]+"\n");
		}
    	Arrays.sort(Rank);
    	int index1 = 0; int index2 = 0;
    	for(int i = 0; i < AttributeLine.length; i++){
    			if(Rank[0] == AttributeRank[i]){
    				newRankAttribute[0] = AttributeLine[i];
    				index2 = i;
        			break;
    			}
    	}
    	
		ta.append(Integer.toString(1)+"   "+ newRankAttribute[0] + "  -  " + Float.toString(-(float)Rank[0]) +"\n");
		
	    
	    
    	for(int count = 1; count < outputCount; count++){
    		
    		if (Rank[count] != Rank[count-1]){
    			for(int i = 0; i < AttributeLine.length; i++){
        			if(Rank[count] == AttributeRank[i]){
        				newRankAttribute[count] = AttributeLine[i];
        				index2 = i;
        				break; 			
        			}
        		}
    			ta.append(Integer.toString(count+1)+"   "+ newRankAttribute[count] + "  -  " + Float.toString(-(float)Rank[count]) +"\n");
    			index1 = 0;
    			
    		}
    		else{
    			index1 = index1+1;
    			for(int i = index2+1; i < AttributeLine.length; i++){
        			if(Rank[count] == AttributeRank[i]){
        				newRankAttribute[count] = AttributeLine[i];
        				index2 = i;
	        			break;
        			}
        		}
    			ta.append(Integer.toString(count-index1+1)+"   "+ newRankAttribute[count] + "  -  " + Float.toString(-(float)Rank[count]) +"\n");
    		}   		
		}
    	ta.setCaretPosition(0);
    	
	}
	
	public double distance(double[] target1, double[] target2){
		double output = 0;		
		for (int i = 0; i < target1.length; i++){
			output = output + (target1[i] - target2[i])*(target1[i] - target2[i]);
		}		
		return output;		
	}

	
 	public double[][] getOriginalDataMatrix() {
		return OriginalDataMatrix;
	}


	public void setOriginalDataMatrix(double[][] originalDataMatrix) {
		OriginalDataMatrix = originalDataMatrix;
	}
	
	public void setLabel(String[] label) {
		Label = label;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public void setNumCluster(int numCluster) {
		NumCluster = numCluster;
	}


	public void setLabelsIndex(int[] labelsIndex) {
		this.labelsIndex = labelsIndex;
	}


	public void setDataAxis(double[][] dataAxis) {
		DataAxis = dataAxis;
	}


	public void setDataMatrix(double[][] dataMatrix) {
		DataMatrix = dataMatrix;
	}
	

	public void setAllDataMatrix(double[][] allDataMatrix) {
		AllDataMatrix = allDataMatrix;
	}


	public void setAllCaci(String[] allCaci) {
		AllCaci = allCaci;
	}


	public void setChooseDM(String chooseDM) {
		ChooseDM = chooseDM;
	}


	public void setAttributeLine(String[] attributeLine) {
		AttributeLine = attributeLine;
	}


	public static void setAttributeMatrix(String[] attributeMatrix) {
		AttributeMatrix = attributeMatrix;
	}


	public void setAttributeOriginalMatrix(String[] attributeOriginalMatrix) {
		AttributeOriginalMatrix = attributeOriginalMatrix;
	}


	public void setChooseAttribute(String chooseAttribute) {
		ChooseAttribute = chooseAttribute;
	}


	public void setAttributeLabel(String[] attributeLabel) {
		AttributeLabel = attributeLabel;
	}


	public void setRefLabel(String[] refLabel) {
		this.refLabel = refLabel;
	}


	public void setAttributeSize(int attributeSize) {
		AttributeSize = attributeSize;
	}


	public void setAttributeOriginalSize(int attributeOriginalSize) {
		AttributeOriginalSize = attributeOriginalSize;
	}


	public void setSampleInfoLabel(String[] sampleInfoLabel) {
		SampleInfoLabel = sampleInfoLabel;
	}

	public String[] getLabel() {
		return Label;
	}

	public int getSize() {
		return size;
	}

	public int getNumCluster() {
		return NumCluster;
	}

	public int[] getLabelsIndex() {
		return labelsIndex;
	}

	public double[][] getDataAxis() {
		return DataAxis;
	}

	public double[][] getDataMatrix() {
		return DataMatrix;
	}


	public double[][] getAllDataMatrix() {
		return AllDataMatrix;
	}

	public String[] getAllCaci() {
		return AllCaci;
	}

	public String getChooseDM() {
		return ChooseDM;
	}

	public String[] getAttributeLine() {
		return AttributeLine;
	}

	public static String[] getAttributeMatrix() {
		return AttributeMatrix;
	}

	public String[] getAttributeOriginalMatrix() {
		return AttributeOriginalMatrix;
	}

	public String getChooseAttribute() {
		return ChooseAttribute;
	}

	public String[] getAttributeLabel() {
		return AttributeLabel;
	}

	public String[] getRefLabel() {
		return refLabel;
	}

	public int getAttributeSize() {
		return AttributeSize;
	}

	public int getAttributeOriginalSize() {
		return AttributeOriginalSize;
	}

	public String[] getSampleInfoLabel() {
		return SampleInfoLabel;
	}

}
	