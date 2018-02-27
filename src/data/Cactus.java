package data;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane; 

public class Cactus {
	public String[] Label;
	private int size;
	public int sampleSize;
	
	private double[][] cactus;
	private double[][] OriginalData; //data matrix
	
	private String[] CactusData;
	private String[][] CactusDataMatrix;
	
	private String[] CactusLine;
	private Scanner sc;
	private String[] AllCacti;
	final ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_mlv_small.png"));

	
	public String[] getAllCacti() {
		return AllCacti;
	}

	public int[] getStartLine() {
		return StartLine;
	}


	private int[] StartLine;
	
	public Cactus(String[] label, int size, double[][] cactus) {
		this.Label = label;
		this.size = size;
		this.cactus = cactus;
		
	}
	
	public Cactus(String path,int row, int column, int direction, int labelnum) throws FileNotFoundException {
	
		
		int originalsize = 0;
		sc = new Scanner(new File(path));
		List<String> lines = new ArrayList<String>();
	
		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine());
		  originalsize++;
		}
		int sizeR = originalsize;
		
		CactusData = lines.toArray(new String[0]);
		
		String[] FourthLine = CactusData[4].split("\t");
		int sizeC = FourthLine.length;
		String Split = "\t";
		
		if (FourthLine.length == 1)
			Split = " ";
		
		if (direction == 1)
			this.size = sizeR-row;
		else
			this.size = sizeC-column;
		
		if(row == 0 && column == 0){
			if(direction == 1){
				this.OriginalData = new double[sizeR][sizeC];
				this.Label = new String[this.size];
				
				for(int i = 0; i < sizeR; i++){
					CactusLine = CactusData[i].split(Split);
					
					for(int j = 0; j < sizeC; j++){
						OriginalData[i][j] = Double.parseDouble(CactusLine[j]);
					}
				}
				JOptionPane.showMessageDialog(null, "The labels are formated as Sample1, Sample2, Sample3, Sample4 ....");
				for(int i = 0; i < this.size; i++){
					this.Label[i] = "Sample"+Integer.toString(i+1);
				}
				this.sampleSize = sizeC;
			}
			else{
				this.OriginalData = new double[sizeC][sizeR];
				this.Label = new String[this.size];
				
				for(int i = 0; i < sizeR; i++){
					CactusLine = CactusData[i].split(Split);
					
					for(int j = 0; j < sizeC; j++){
						OriginalData[j][i] = Double.parseDouble(CactusLine[j]);
					}
				}
				JOptionPane.showMessageDialog(null, "The labels are formated as Sample1, Sample2, Sample3, Sample4 ....");
				for(int i = 0; i < this.size; i++){
					this.Label[i] = "Sample"+Integer.toString(i+1);
				}
				this.sampleSize = sizeR;
			}
		}
		else{
			
			if (direction == 1){// vertical
				this.OriginalData = new double[sizeR-row][sizeC-column];
				this.Label = new String[this.size];
				if (labelnum == -5){
					JOptionPane.showMessageDialog(null, "The labels are formated as Sample1, Sample2, Sample3, Sample4 ....");
					for(int i = 0; i < this.size; i++){
						this.Label[i] = "Sample"+Integer.toString(i+1);
					}
					for(int i = row; i < sizeR; i++){
						CactusLine = CactusData[i].split(Split);
						
						for(int j = column; j < sizeC; j++){
							OriginalData[i-row][j-column] = Double.parseDouble(CactusLine[j]);
						}
					}
					this.sampleSize = sizeC-column;
				}
				else{
					if (labelnum == -5){
						JOptionPane.showMessageDialog(null, "The labels are formated as Sample1, Sample2, Sample3, Sample4 ....");
						for(int i = 0; i < this.size; i++){
							this.Label[i] = "Sample"+Integer.toString(i+1);
						}
						
						for(int i = row; i < sizeR; i++){
							CactusLine = CactusData[i].split(Split);
							
							for(int j = column; j < sizeC; j++){
								OriginalData[i-row][j-column] = Double.parseDouble(CactusLine[j]);
							}
						}
						this.sampleSize = sizeC-column;
					}else{
						for(int i = row; i < sizeR; i++){
							CactusLine = CactusData[i].split(Split);
							
							for(int j = column; j < sizeC; j++){
								OriginalData[i-row][j-column] = Double.parseDouble(CactusLine[j]);
							}
							this.Label[i-row] = CactusLine[labelnum];
						}
						this.sampleSize = sizeC-column;
					}
				}	
			}  
			else{// horizontal
				this.OriginalData = new double[sizeC-column][sizeR-row];
				this.Label = new String[this.size];
				if (labelnum == -5){
					JOptionPane.showMessageDialog(null, "The labels are formated as Sample1, Sample2, Sample3, Sample4 ....");
					for(int i = 0; i < this.size; i++){
						this.Label[i] = "Sample"+Integer.toString(i+1);
					}
				}else{
					CactusLine = CactusData[labelnum].split(Split);
					
					for(int j = column; j < sizeC; j++){
						try {
							this.Label[j-column] = CactusLine[j];
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
			        		JOptionPane.showMessageDialog(null, "Please reformat your data file!",null,JOptionPane.INFORMATION_MESSAGE,icon);	
			        		break;
						}
					}
				}
				
				for(int i = row; i < sizeR; i++){
					CactusLine = CactusData[i].split(Split);
					
					for(int j = column; j < sizeC; j++){
						OriginalData[j-column][i-row] = Double.parseDouble(CactusLine[j]);
					}
				}
				this.sampleSize = sizeR-row;
			}                 
		
		
			this.cactus = new double[this.size][this.size];	
			double dist,d,m; 
			m = 0;
		    for (int i=0; i<this.size; i++) {
		        for (int j=0; j<this.size; j++) {
		            dist = 0;
		            d = 0;
		            for (int k=0; k< this.sampleSize; k++) {
		                d = Math.abs(OriginalData[i][k] - OriginalData[j][k]);
		                dist = dist + d;
		            }
		            this.cactus[i][j] = dist;
		            if (dist>m)
		            	m = dist;
		        }
		    }
			for (int i=0; i<this.size; i++) {
		        for (int j=0; j<this.size; j++) {
		            this.cactus[i][j] = this.cactus[i][j]/m;   
		        }
		    }
		}
		
		
			
	}

	
	public Cactus(String path) throws FileNotFoundException {
		
		int originalsize = 0;
		sc = new Scanner(new File(path));
		List<String> lines = new ArrayList<String>();
	
		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine());
		  originalsize++;
		}
		size = originalsize;
		
		CactusData = lines.toArray(new String[0]);
		
		String[] FourthLine = CactusData[4].split("\t");
		
		if(FourthLine.length*1.5 > size){
			
			String[] FirstLine, SecondLine;
			FirstLine = CactusData[0].split("\t"); 
			SecondLine = CactusData[1].split("\t");
			
			if((FirstLine.length < (originalsize-1))&&(SecondLine.length < (originalsize-6))){
	  		  	size = 0;
	  		  	return;
			}
			
			if(FirstLine[0]=="0.0"){	
				this.cactus = new double[size][size];
				this.Label = new String[size];
				
				for(int i = 0; i < size; i++){
					CactusLine = CactusData[i].split("\t");
					
					for(int j = 0; j < size; j++){
						cactus[i][j] = Double.parseDouble(CactusLine[j]);
					}
				}
				JOptionPane.showMessageDialog(null, "The labels are formated as Sample1, Sample2, Sample3, Sample4 ....");
				for(int i = 0; i < size; i++){
					this.Label[i] = "Sample"+Integer.toString(i+1);
				}
			}
			else if(SecondLine[0]=="0.0"){	
				size = size-1;
				this.cactus = new double[size][size];
				this.Label = new String[size];
				
				for(int i = 1; i < originalsize; i++){
					CactusLine = CactusData[i].split("\t");
					
					for(int j = 0; j < size; j++){
						cactus[i-1][j] = Double.parseDouble(CactusLine[j]);
					}
				}
				JOptionPane.showMessageDialog(null, "The labels are formated as Sample1, Sample2, Sample3, Sample4 ....");
				for(int i = 0; i < size; i++){
					this.Label[i] = "Sample"+Integer.toString(i+1);
				}
			}
			else if((FirstLine.length==originalsize-1) && (FirstLine[0]!="0.0")){
				size = size-1;
				this.cactus = new double[size][size];
				this.Label = new String[size];
				this.Label = CactusData[0].split("\t");
				for(int i = 1; i < originalsize; i++){
					CactusLine = CactusData[i].split("\t");	
					for(int j = 0; j < size; j++){
						cactus[i-1][j] = Double.parseDouble(CactusLine[j+1]);
					}
				}
			}
			else if((FirstLine.length<originalsize) && (SecondLine[0]!="0.0")){
				size = size-2;
				this.cactus = new double[size][size];
				this.Label = new String[size];
				this.Label = CactusData[1].split("\t");
				//this.AllCacti = CactusData[0].split("\t");
				for(int i = 2; i < originalsize; i++){
					CactusLine = CactusData[i].split("\t");	
					for(int j = 0; j < size; j++){
						cactus[i-2][j] = Double.parseDouble(CactusLine[j+1]);
					}
				}
			}
		}
		else{
			int count = 0;
			
			this.Label = CactusData[1].split("\t");
			size = this.Label.length;
			
			
			int deleteline = 1;
			int size_AllCacti = originalsize/(size+2);
			this.cactus = new double[size_AllCacti*size][size];
			this.AllCacti = new String[size_AllCacti];
			
			for(int i = 0; i < originalsize; i++){
				if(i != deleteline){
					CactusLine = CactusData[i].split("\t");	
					if (CactusLine.length<3){
						AllCacti[count] = CactusLine[0];
						count = count+1;	
						deleteline = i+1;
					}
					else{
						for(int j = 0; j < size; j++){
							cactus[i-2*count][j] = Double.parseDouble(CactusLine[j+1]);
						}
					}
				
				}
			} 
		}
		

	}
	
	public Cactus(String path, String attribute) throws FileNotFoundException {
		
		sc = new Scanner(new File(path));
		List<String> lines = new ArrayList<String>();
	
		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine());
		  size++;
		}
	
		CactusData = lines.toArray(new String[0]);	
	
		
	}
	public double[][] getOriginalData() {
		return OriginalData;
	}

	
	public String[] getCactusData() {
		return CactusData;
	}


	public String[] getCactusLine() {
		return CactusLine;
	}

	public Scanner getSc() {
		return sc;
	}	

	public String[][] getCactusDataMatrix() {
		return CactusDataMatrix;
	}

	public double[][] getCactus() {
		return cactus;
	}


	public String[] getLabel() {
		return Label;
	}


	public int getSize() {
		return size;
	}
}
