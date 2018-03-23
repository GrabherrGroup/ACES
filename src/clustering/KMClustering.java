package clustering;


public class KMClustering {
	public int countIndex;
	public int[] selected_index= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public int step;
	public int cactusSize;
	public double[][] newCactusData; 
	public double[][] CactusData; 
	public String[] labels;
	private int NumCluster;
	private int[] labelsIndex;
	
	private double [][] _centroids; // centroids: the center of clusters
    private int _nrows, _ndims; // the number of rows and dimensions
	
	
	public int[] getLabelsIndex() {
		return labelsIndex;
	}

	public int getNumCluster() {
		return NumCluster;
	}

	public KMClustering(String[] label, int size, double[][] cactusData) {
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

	public void GetCluster(){
		
	    int i, j;
	    
	    /*for (i=0; i<10; i++) {
	        if (selected_index[i] == 0 && selected_index[i+1] == 0) {
	            NumCluster = i;  // number of the clusters
	            break;
	        }
	    }*/
	    
	    NumCluster = countIndex + 1;
	    
	    _nrows = cactusSize;
	    _ndims = cactusSize;
	    
		_centroids = new double[NumCluster][cactusSize];
		int c = 0;
		
		for (i=0; i< NumCluster; i++){
	         
			for (j = 0; j<_ndims; j++){
				c = selected_index[i];
            	_centroids[i][j] = this.CactusData[c][j];
		    }
        }
	
	    double [][] c1 = _centroids;

		int niter =50;     
        double threshold = 0.001;
		int round = 0;

		while (true){
			// update _centroids with the last round results
			_centroids = c1;

		    //assign record to the closest centroid
		    labelsIndex = new int[cactusSize];
		    for (i=0; i<_nrows; i++){
		    	labelsIndex[i] = closest(CactusData[i]);
		    }
		        
		 	// recompute centroids based on the assignments  
		    c1 = updateCentroids();
		    round ++;
		    if ((niter >0 && round >=niter) || converge(_centroids, c1, threshold))
		    	break;
		}

		for (i=0; i<cactusSize; i++){
	    	labelsIndex[i] = labelsIndex[i] + 1;
	    }
		      
	}

		  // find the closest centroid for the record v 
		  private int closest(double [] v){
		    double mindist = dist(v, _centroids[0]);
		    int label =0;
		    for (int i=1; i<NumCluster; i++){
		      double t = dist(v, _centroids[i]);
		      if (mindist>t){
		        mindist = t;
		        label = i;
		      }
		    }
		    return label;
		  }

		  // compute Euclidean distance between two vectors v1 and v2
		  private double dist(double [] v1, double [] v2){
		    double sum=0;
		    for (int i=0; i<_ndims; i++){
		      double d = v1[i]-v2[i];
		      sum += d*d;
		    }
		    return Math.sqrt(sum);
		  }

		  // according to the cluster labels, recompute the centroids 
		  // the centroid is updated by averaging its members in the cluster.
		  // this only applies to Euclidean distance as the similarity measure.

		  private double [][] updateCentroids(){
		    // initialize centroids and set to 0
		    double [][] newc = new double [NumCluster][]; //new centroids 
		    int [] counts = new int[NumCluster]; // sizes of the clusters

		    // intialize
		    for (int i=0; i<NumCluster; i++){
		      counts[i] =0;
		      newc[i] = new double [_ndims];
		      for (int j=0; j<_ndims; j++)
		        newc[i][j] =0;
		    }


		    for (int i=0; i<_nrows; i++){
		      int cn = labelsIndex[i]; // the cluster membership id for record i
		      for (int j=0; j<_ndims; j++){
		        newc[cn][j] += CactusData[i][j]; // update that centroid by adding the member data record
		      }
		      counts[cn]++;
		    }

		    // finally get the average
		    for (int i=0; i< NumCluster; i++){
		      for (int j=0; j<_ndims; j++){
		        newc[i][j]/= counts[i];
		      }
		    } 

		    return newc;
		  }

		  // check convergence condition
		  // max{dist(c1[i], c2[i]), i=1..NumCluster < threshold
		  private boolean converge(double [][] c1, double [][] c2, double threshold){
		    // c1 and c2 are two sets of centroids 
		    double maxv = 0;
		    for (int i=0; i< NumCluster; i++){
		        double d= dist(c1[i], c2[i]);
		        if (maxv<d)
		            maxv = d;
		    } 

		    if (maxv <threshold)
		      return true;
		    else
		      return false;
		    
		  }
		  public double[][] getCentroids()
		  {
		    return _centroids;
		  }

		  public int [] getLabel()
		  {
		    return labelsIndex;
		  }

		  public int nrows(){
		    return _nrows;
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
			    if (cactusSize < 15){
			    	step = 4;
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
}
