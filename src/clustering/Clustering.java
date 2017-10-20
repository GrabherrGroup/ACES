package clustering;

public class Clustering {
	
	public int countIndex;
	public int[] selected_index= {0,0,0,0,0,0,0,0,0,0};;
	public int step;
	public int cactusSize;
	public double[][] newCactusData; 
	public double[][] CactusData; 
	public String[] labels;
	private int NumCluster;
	private int[] labelsIndex;
	
	
	public int[] getLabelsIndex() {
		return labelsIndex;
	}



	public int getNumCluster() {
		return NumCluster;
	}

	public Clustering(String[] label, int size, double[][] cactusData) {
		//super();
		this.cactusSize = size;
		step = cactusSize/6;
		
		this.CactusData = new double[size][size];
		this.newCactusData = new double[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				this.CactusData[i][j] = cactusData[i][j];
				this.newCactusData[i][j] = cactusData[i][j];
			}
		}

		this.labels = label;
		
		countIndex = 0;
		GetNumOfCluster(); 
	    labelsIndex = new int[size];
	    GetCluster();

	}


	
	public void GetNumOfCluster() {
		
	    int iter;
	    
	    if (step<6) {
	        step = 6;
	    }else if (step>30){
	        step = 30;
	    }
	    if (cactusSize> 50){
	        iter = 20;
	    }
	    else{
	        iter = 11;
	    }
	    
	    // new_cactus matrix creation and
	    
	    double cactus_mean = 0;
	    double cactus_var = 0;
	    int i, j, k;
	    double dist,d;
	    
	    double var = 0;
	    for (i=0; i<cactusSize; i++) {
	        for (j=0; j<cactusSize; j++) {
	            dist = 0;
	            d = 0;
	            for (k=0; k<cactusSize; k++) {
	                d = Math.abs(CactusData[i][k] - CactusData[j][k]);
	                dist = dist + d;
	            }
	            newCactusData[i][j] = dist;
	            cactus_mean = cactus_mean + dist;
	        }
	        cactus_mean = cactus_mean/cactusSize;
	        d = 0;
	        dist = 0;
	        for (k=0; k<cactusSize; k++) {
	            d = Math.pow(newCactusData[i][k] - cactus_mean,2);
	            cactus_var = cactus_var + d;
	        }
	        cactus_var = Math.sqrt(cactus_var/cactusSize);
	        if (cactus_var > var) {
	            selected_index[0] = i;
	            var = cactus_var;
	        }
	        cactus_var = 0;
	        cactus_mean = 0;
	    }
	    double[] selected_line = new double[cactusSize];
	    int[] Rank1 = new int[iter];
	    
	    for (k=0; k<cactusSize; k++) {
	        selected_line[k] = newCactusData[selected_index[0]][k];
	    }
	    
	    int r = 0;
	    for (i=0; i<iter; i++) {
	        double m = 0;
	        for (j=0; j<cactusSize; j++) {
	            if (selected_line[j] > m) {
	                m = selected_line[j];
	                r = j;
	            }
	        }
	        selected_line[r] = 0;
	        Rank1[i] = r;
	    }
	    selected_index[1] = Rank1[0];
	    countIndex = 1;
	    
	    for (i=1; i<iter; i++) {
	        GetIndex(Rank1[i]);
	    }
	    
	    
	    
	    for (k=0; k<cactusSize; k++) {
	        selected_line[k] = newCactusData[selected_index[1]][k];
	    }
	    for (i=0; i<3; i++) {
	        double m = 0;
	        for (j=0; j<cactusSize; j++) {
	            if (selected_line[j] > m) {
	                m = selected_line[j];
	                r = j;
	            }
	        }
	        selected_line[r] = 0;
	        Rank1[i] = r;
	    }
	    
	    for (i=1; i<3; i++) {
	        GetIndex(Rank1[i]);
	    }
	    
	    
	    if (selected_index[2]>0) {
	        for (k=0; k<cactusSize; k++) {
	            selected_line[k] = newCactusData[selected_index[2]][k];
	        }
	        for (i=0; i<3; i++) {
	            double m = 0;
	            for (j=0; j<cactusSize; j++) {
	                if (selected_line[j] > m) {
	                    m = selected_line[j];
	                    r = j;
	                }
	            }
	            selected_line[r] = 0;
	            Rank1[i] = r;
	        }
	        
	        for (i=1; i<3; i++) {
	            GetIndex(Rank1[i]);
	        }
	    }
	    
	    if (selected_index[3]>0) {
	        for (k=0; k<cactusSize; k++) {
	            selected_line[k] = newCactusData[selected_index[3]][k];
	        }
	        for (i=0; i<3; i++) {
	            double m = 0;
	            for (j=0; j<cactusSize; j++) {
	                if (selected_line[j] > m) {
	                    m = selected_line[j];
	                    r = j;
	                }
	            }
	            selected_line[r] = 0;
	            Rank1[i] = r;
	        }
	        
	        for (i=1; i<3; i++) {
	            GetIndex(Rank1[i]);
	        }
	    }

	    
	}
	
	public void GetIndex(int rank){
	    int i, j, k;
	    double[] selected_line = new double[cactusSize];
	    
	    int a = 0;
	    
	    for (i=0; i<=countIndex; i++) {
	        for (j=0; j<cactusSize; j++) {
	        selected_line[j] = newCactusData[selected_index[i]][j];
	        }
	        
	        int r = 0;
	        
	        for (k=0; k<step; k++) {
	            double m = 100;
	            for (j=0; j<cactusSize; j++) {
	                if (selected_line[j] < m) {
	                    m = selected_line[j];
	                    r = j;
	                }
	            }
	            selected_line[r] = 100;
	        }
	        
	        if (selected_line[rank] == 100) {
	            a = 1;
	        }
	    }
	    if (a == 0) {
	    	countIndex = countIndex+1;
	        selected_index[countIndex] = rank;
	    }
	}
	
	public void GetCluster(){
		
	    int i, j;
	    
	    for (i=0; i<10; i++) {
	        if (selected_index[i] == 0) {
	            NumCluster = i;  // number of the clusters
	            break;
	        }
	    }
	        
	    for (i=0; i<cactusSize; i++) {
	        for (j=0; j<cactusSize; j++) {
	            if (i == j) {
	                newCactusData[i][j] = 1000;
	            }
	        }
	    }
	    
	    int numCluster = cactusSize;
	    
	    for (i = 0; i<cactusSize; i++) {
	    	labelsIndex[i] = i+1;
	    }
	    
	    int count = cactusSize + 1;
	    
	    // hierarchical clustering 
	    int row = 0, column = 0, selected_line_r, selected_line_c;
	    do {	        
	        //find the min of row and column
		    double m = 100;
		    for (i=0; i<cactusSize; i++) {
		        for (j=0; j<cactusSize; j++) {
		            if (newCactusData[i][j] < m && labelsIndex[i]!=labelsIndex[j]) {
		                m = newCactusData[i][j];
		                row = i;
		                column = j;
		            }
		        } 
		    }

		    selected_line_r = labelsIndex[row];  
	        selected_line_c = labelsIndex[column];
	        
	        int count_rc = 0;
	        // find the closest two points and set the new number
	        for (i=0; i<cactusSize; i++) {
	            if (labelsIndex[i] == selected_line_r) {
	                labelsIndex[i] = count;
	                count_rc += 1;
	            }else if (labelsIndex[i] == selected_line_c){
	                labelsIndex[i] = count;
	                count_rc += 1;
	            }
	        }
	        // temp is for average value calculation
	        double[] temp = new double[cactusSize];
	        for (i = 0; i<cactusSize; i++) {
	            temp[i] =0;
	        }
	        // sum each sample of current cluster
	        for (i=0;i<cactusSize; i++) {
	            if (labelsIndex[i] == count) {
	                for (j=0; j<cactusSize; j++) {
	                    temp[j] = temp[j] + newCactusData[i][j];
	                }
	            }
	        }
	        // average each sample of current cluster
	        for (i=0;i<cactusSize; i++) {
	            if (labelsIndex[i] == count) {
	                for (j=0; j<cactusSize; j++) {
	                	newCactusData[i][j] = temp[j]/count_rc;
	                	newCactusData[j][i] = temp[j]/count_rc;
	                }
	            }
	        }

	        
	        numCluster -= 1;
	        count += 1;
	    } while (numCluster>NumCluster);
	    
	    int m = 0;
	    
	    
	    // set new clusters index
	    for(count=1;count<=NumCluster; count++){
	        for (i=0;i<cactusSize; i++) {
	            if (labelsIndex[i]>count) {
	                m = labelsIndex[i];
	                break;
	            }
	        }
	        for (j=0; j<cactusSize; j++) {
	            if (labelsIndex[j] == m) {
	                labelsIndex[j] = count;
	            }
	        }
	    }

	}
	


}

