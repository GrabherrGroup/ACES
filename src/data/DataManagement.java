package data;

import java.awt.FileDialog;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Frame.ACES;

public class DataManagement {
	FileDialog fd; //read the files
	File file1 = null;
	final ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));

	int FileOpenStatus = 0; //check whether the cactus files have been loaded first
	int AttributeOpenStatus = 0; 
	int AttributeChooseStatus = 0;
	
	double DBr = 1;
	double DBm = 10;
	
	String[] Label;
	String[] newDataLabel;
 	int size;
	
	int NumCluster;
	int[] labelsIndex;
	int[] newlabelsIndex;
	double[][] DataAxis;// the location of each point in 3D
	double[][] DataMatrix;
	double[][] newData;
	double[][] AllDataMatrix;
    String[] AllCaci;
	String ChooseDM = "current Distance Matrix";
	String CurrentDM = "current Distance Matrix";

	String[] AttributeLine; //the list of all the attributes
	public static String[] AttributeMatrix; //all the attributes info
	String[] AttributeOriginalMatrix; //all the original attributes info
	String ChooseAttribute = "attribute"; // selected attribute
	String[] AttributeLabel;//list all the labels belong to the selected attribute
	String[] newAttributeLabel;
	String[] refLabel;
	int AttributeSize;
	int AttributeOriginalSize;
	String[] SampleInfoLabel;
	
	public void CreateDataAfterClustering(){
		 newData = new double[size][size];
    	 newDataLabel = new String[size];
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
    	 
    	 for(int i = 0; i< size; i++){
    		 for(int j = 0; j< size; j++){
    			 newData[i][j] = DataMatrix[index[i]][index[j]];
			 }
    		  newDataLabel[i] = Label[index[i]];
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
	
	
	
	public DataManagement(){
		 
		 
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
        ACES.ta.setText("\n");

        
		char[] LabelChar= Label[1].toCharArray();
		int[] LabelInt = new int[LabelChar.length];
		
		for(int i = 0; i < LabelChar.length; i++){
			LabelInt[i] = i;
		}
		
        for(int i = 1; i < AttributeOriginalSize; i++){
			temp = AttributeOriginalMatrix[i].split(",");
			SampleInfoLabel[i-1] = temp[count];
			ACES.ta.append(SampleInfoLabel[i-1] +"\n");
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
            	 ACES.ta.setText("new Distance Matrix Labels \n");
				 ACES.ta.append("\n");
				 
				 for(int i = 0; i < size; i++){
					 String[] t = Label[i].split("");
               	 	 StringBuffer result = new StringBuffer();
               	 	 for(int j = startrow; j <endrow+1;j++){
               	 		 result.append(t[j]);
               	 	 }
               	 	 Label[i] =  result.toString();
               	 	 ACES.ta.append(Integer.toString(i+1) + "     " + Label[i] +"\n");
	
                } 
             }
             else if((value2.length()>0) && (value2.length()<SampleInfoLabel[0].length())){
            	 String[] shortLabel = value2.split("");
            	 String[] longLabel = SampleInfoLabel[0].split("");
	             
            	 for(int i = 0; i <longLabel.length-shortLabel.length+1;i++){
            		 if((longLabel[i].equals(shortLabel[0]))&&(longLabel[i+shortLabel.length-1].equals(shortLabel[shortLabel.length-1]))){
	            		 startrow = i;
	            		 endrow = i+shortLabel.length-1;
	            		 break;
	            	 }
	             }
	             ACES.ta.setText("new SampleInfo Labels \n");
	 			 ACES.ta.append("\n");
	 				 
	             for(int i = 0; i < AttributeOriginalSize; i++){
	            	 String[] t = SampleInfoLabel[i].split("");
	                 StringBuffer result = new StringBuffer();
	                 for(int j = startrow; j <endrow+1;j++){
	                	 result.append(t[j]);
		             }
	                 SampleInfoLabel[i] =  result.toString();
	                 ACES.ta.append(Integer.toString(i+1) + "     " + SampleInfoLabel[i] +"\n");
	 	
	             }  
             }
             else if((value1.length()==0)&&(value2.length()==0)&&(Label[0].length()!=SampleInfoLabel[0].length())){
            	 JOptionPane.showMessageDialog(null, "Please reset the labels.\nOtherwise, the attribute file cannot be applied.", null, JOptionPane.INFORMATION_MESSAGE, icon);  	  
            	 return; 
       		 }
             AttributeOriginalSize = size+1;
         }

         StringBuffer nullLine = new StringBuffer();
         
         for(int i = 0; i < AttributeLine.length; i++){
        	 nullLine.append("0").append(",");
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
	}
	
	public void DBp() {
	
		 JTextField field1 = new JTextField();
         JTextField field2 = new JTextField();
         field1.setText(Double.toString(DBr));
         field2.setText(Double.toString(DBm));
        
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
             DBm = Double.parseDouble(field2.getText());
         }
     }

}
	