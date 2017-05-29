package CactusClusteringVisualization;

public class PCA {
	
	private int numAxis;
	private int size;
	private double[][] Data; //original matrix
	private double[][] DataMean; //original matrix - mean
	private double[][] DataMeanTrans; // transpose matrix
	private double[][] DataSvdU;// u
	private double[][] DataSvdV;// v
	private double[][] DataW;//first three columns of DataSvdV
	private double[][] DataAxis;// the location of each point in 3D
	private int DataRow;
	private int DataColumn;
	private int dim;
	
	private double[] w;
	
	public double[][] getDataAxis() {
		return DataAxis;
	}
	
	public PCA(int numAxis, int r, int c, double[][] inputData) {

		this.numAxis = numAxis;
		this.DataRow = r;
		this.DataColumn = c;
	
		this.Data = new double[DataRow][DataColumn];
		this.DataMean = new double[size][size]; 
		this.DataMeanTrans = new double[size][size]; 
		this.DataSvdU = new double[size][size];
		this.DataSvdV = new double[numAxis][size]; 
		this.DataAxis = new double[numAxis][size]; 
		    
		for(int i = 0; i < DataRow; i++){
			for(int j = 0; j < DataColumn; j++){
				this.Data[i][j] = inputData[i][j];
			}
		}
		
	    this.dim = Math.max(DataRow, DataColumn);

		
    	this.w = new double[dim];
    	
	    
	    for (int j = 0; j < dim; j++) {
	    	w[j] = 0;
	    }
		
		calPCA(DataRow,DataColumn);
	}
	
	public void calPCA(int row, int column){
	    int i, j;
	    
	    this.DataMean = new double[dim][dim]; //original matrix - mean
	    this.DataMeanTrans = new double[dim][dim]; // transpose matrix
	    this.DataSvdU = new double[dim][dim]; // u
	    this.DataSvdV = new double[dim][dim]; // v
	    this.DataW = new double[numAxis][dim]; //first three columns of DataSvdV
	    this.DataAxis = new double[numAxis][dim]; // the location of each point in 3D 
	    
	    
	    for (i = 0; i < row; i++) {
	        for (j = 0; j < column; j++)
	            DataMean[i][j] = Data[i][j];
	    }
	    
	    double[] mean = new double[dim];
	    double all_row = 0;
	    
	    for (j = 0; j < column; j++) {
	        for (i = 0; i < row; i++){
	            all_row += DataMean[i][j];
	        }
	        mean[j] = all_row/row;
	        all_row = 0;
	    }
	    	    
	    for (i = 0; i < row; i++) {
	        for (j = 0; j < column; j++)
	        	DataMean[i][j] = DataMean[i][j]-mean[i];
	    }

	    for (i = 0; i < row; i++) {
	        for (j = 0; j < column; j++){

	        	DataMeanTrans[i][j] = DataMean[j][i];
	        }
	    }
	   
	    int k;
	    double temp;
	    for (i = 0; i < row; i++) {
	        for (j = 0; j < column; j++){
	            temp=0;
	            for (k = 0; k < column; k++){
	                 temp += DataMean[i][k]*DataMeanTrans[k][j];
	            }
	            DataSvdU[i][j] = temp;
	        }
	    }

	    CalSvd(dim,dim);  // SVD calculation
	    
	    for (i = 0; i < numAxis; i++) {
	        for (j = 0; j < dim; j++){
	            DataW[i][j] = DataSvdV[j][i];
	        }
	    }
	    
	    for (i = 0; i < numAxis; i++) {
	        for (j = 0; j < dim; j++){
	            temp=0;
	            for (k = 0; k < column; k++){
	                 temp += DataW[i][k]*DataMean[k][j];
	            }
	            DataAxis[i][j] = temp;
	        }
	    }
	    	    
	}

	public double SIGN(double a,double b){
		
		double g;
		if (b>= 0.0) 
		g = Math.abs(a);
		else
		g = -Math.abs(a);
		return g;
	}
	
	public static double Jiang(double a, double b)
	{
	    double temp, result;
	    
	    a = Math.abs(a);
	    b = Math.abs(b);
	    
	    if (a > b){ 
	    	temp = b/a; 
	    	result = a * Math.sqrt(1.0 + temp*temp); 
	    	}
	    else if (b > 0.0){ 
	    	temp = a/b; 
	    	result = b * Math.sqrt(1.0 + temp*temp); 
	    	}
	    else 
	    	result = 0.0;
	    
	    return result;
	}

	public void CalSvd(int row, int column)
	{
	    int flag=0, i=0, its=0, j=0, jj=0, k=0, l=0, nm=0;
	    double c = 0.0, f = 0.0, h = 0.0, s = 0.0, x = 0.0, y = 0.0, z = 0.0;
	    double anorm = 0.0, g = 0.0, scale = 0.0;
	    
	    if (row < column)
	    {
	        System.out.println("row must be bigger than column");
	        return;
	    }
	    
	    double[] Temp = new double[dim];
	    
	    
	    /* Householder reduction to bidiagonal form */
	    for (i = 0; i < column; i++)
	    {
	        /* left-hand reduction */
	        l = i + 1;
	        Temp[i] = scale * g;
	        g = s = scale = 0.0;
	        if (i < row)
	        {
	            for (k = i; k < row; k++)
	                scale += Math.abs((double)DataSvdU[k][i]);
	            if (scale!=0.0)
	            {
	                for (k = i; k < row; k++)
	                {
	                	DataSvdU[k][i] = (double)((double)DataSvdU[k][i] / scale);
	                    s += ((double)DataSvdU[k][i] * (double)DataSvdU[k][i]);
	                }
	                f = (double)DataSvdU[i][i];
	                g = -SIGN(Math.sqrt(s), f);
	                h = f * g - s;
	                DataSvdU[i][i] = (double)(f - g);
	                if (i != column - 1)
	                {
	                    for (j = l; j < column; j++)
	                    {
	                        for (s = 0.0, k = i; k < row; k++)
	                            s += ((double)DataSvdU[k][i] * (double)DataSvdU[k][j]);
	                        f = s / h;
	                        for (k = i; k < row; k++)
	                        	DataSvdU[k][j] += (double)(f * (double)DataSvdU[k][i]);
	                    }
	                }
	                for (k = i; k < row; k++)
	                	DataSvdU[k][i] = (double)((double)DataSvdU[k][i] * scale);
	            }
	        }
	        w[i] = (double)(scale * g);
	        
	        /* right-hand reduction */
	        g = s = scale = 0.0;
	        if (i < row && i != column - 1)
	        {
	            for (k = l; k < column; k++)
	                scale += Math.abs((double)DataSvdU[i][k]);
	            if (scale!=0.0)
	            {
	                for (k = l; k < column; k++)
	                {
	                	DataSvdU[i][k] = (double)((double)DataSvdU[i][k] / scale);
	                    s += ((double)DataSvdU[i][k] * (double)DataSvdU[i][k]);
	                }
	                f = (double)DataSvdU[i][l];
	                g = -SIGN(Math.sqrt(s), f);
	                h = f * g - s;
	                DataSvdU[i][l] = (double)(f - g);
	                for (k = l; k < column; k++)
	                    Temp[k] = (double)DataSvdU[i][k] / h;
	                if (i != row - 1)
	                {
	                    for (j = l; j < row; j++)
	                    {
	                        for (s = 0.0, k = l; k < column; k++)
	                            s += ((double)DataSvdU[j][k] * (double)DataSvdU[i][k]);
	                        for (k = l; k < column; k++)
	                        	DataSvdU[j][k] += (double)(s * Temp[k]);
	                    }
	                }
	                for (k = l; k < column; k++)
	                	DataSvdU[i][k] = (double)((double)DataSvdU[i][k] * scale);
	            }
	        }
	        anorm = Math.max(anorm, (Math.abs((double)w[i]) + Math.abs(Temp[i])));
	    }
	    
	    /* accumulate the right-hand transformation */
	    for (i = column - 1; i >= 0; i--)
	    {
	        if (i < column - 1)
	        {
	            if (g!=0.0)
	            {
	                for (j = l; j < column; j++)
	                    DataSvdV[j][i] = (double)(((double)DataSvdU[i][j] / (double)DataSvdU[i][l]) / g);
	                /* double divisiocolumn to avoid underflow */
	                for (j = l; j < column; j++)
	                {
	                    for (s = 0.0, k = l; k < column; k++)
	                        s += ((double)DataSvdU[i][k] * (double)DataSvdV[k][j]);
	                    for (k = l; k < column; k++)
	                    	DataSvdV[k][j] += (double)(s * (double)DataSvdV[k][i]);
	                }
	            }
	            for (j = l; j < column; j++)
	            	DataSvdV[i][j] = DataSvdV[j][i] = 0.0;
	        }
	        DataSvdV[i][i] = 1.0;
	        g = Temp[i];
	        l = i;
	    }
	    
	    /* accumulate the left-hand transformation */
	    for (i = column - 1; i >= 0; i--)
	    {
	        l = i + 1;
	        g = (double)w[i];
	        if (i < column - 1)
	            for (j = l; j < column; j++)
	                DataSvdU[i][j] = 0.0;
	        if (g!=0.0)
	        {
	            g = 1.0 / g;
	            if (i != column - 1)
	            {
	                for (j = l; j < column; j++)
	                {
	                    for (s = 0.0, k = l; k < row; k++)
	                        s += ((double)DataSvdU[k][i] * (double)DataSvdU[k][j]);
	                    f = (s / (double)DataSvdU[i][i]) * g;
	                    for (k = i; k < row; k++)
	                        DataSvdU[k][j] += (double)(f * (double)DataSvdU[k][i]);
	                }
	            }
	            for (j = i; j < row; j++)
	                DataSvdU[j][i] = (double)((double)DataSvdU[j][i] * g);
	        }
	        else
	        {
	            for (j = i; j < row; j++)
	                DataSvdU[j][i] = 0.0;
	        }
	        ++DataSvdU[i][i];
	    }
	    
	    /* diagonalize the bidiagonal form */
	    for (k = column - 1; k >= 0; k--)
	    {                             /* loop over singular values */
	        for (its = 0; its < 30; its++)
	        {                         /* loop over allowed iterations */
	            flag = 1;
	            for (l = k; l >= 0; l--)
	            {                     /* test for splitting */
	                nm = l - 1;
	                if (Math.abs(Temp[l]) + anorm == anorm)
	                {
	                    flag = 0;
	                    break;
	                }
	                if (Math.abs((double)w[nm]) + anorm == anorm)
	                    break;
	            }
	            if (flag!=0)
	            {
	                c = 0.0;
	                s = 1.0;
	                for (i = l; i <= k; i++)
	                {
	                    f = s * Temp[i];
	                    if (Math.abs(f) + anorm != anorm)
	                    {
	                        g = (double)w[i];
	                        h = Jiang(f, g);
	                        w[i] = (double)h;
	                        h = 1.0 / h;
	                        c = g * h;
	                        s = (-f * h);
	                        for (j = 0; j < row; j++)
	                        {
	                            y = (double)DataSvdU[j][nm];
	                            z = (double)DataSvdU[j][i];
	                            DataSvdU[j][nm] = (double)(y * c + z * s);
	                            DataSvdU[j][i] = (double)(z * c - y * s);
	                        }
	                    }
	                }
	            }
	            z = (double)w[k];
	            if (l == k)
	            {                  /* convergence */
	                if (z < 0.0)
	                {              /* make singular value nonnegative */
	                    w[k] = (double)(-z);
	                    for (j = 0; j < column; j++)
	                    	DataSvdV[j][k] = (-DataSvdV[j][k]);
	                }
	                break;
	            }
	            if (its >= 30) {
	               
	                System.out.println("No convergence after 30,000! iterations");
	                return;
	            }
	            
	            /* shift from bottom 2 x 2 minor */
	            x = (double)w[l];
	            nm = k - 1;
	            y = (double)w[nm];
	            g = Temp[nm];
	            h = Temp[k];
	            f = ((y - z) * (y + z) + (g - h) * (g + h)) / (2.0 * h * y);
	            g = Jiang(f, 1.0);
	            f = ((x - z) * (x + z) + h * ((y / (f + SIGN(g, f))) - h)) / x;
	            
	            /* next QR transformation */
	            c = s = 1.0;
	            for (j = l; j <= nm; j++)
	            {
	                i = j + 1;
	                g = Temp[i];
	                y = (double)w[i];
	                h = s * g;
	                g = c * g;
	                z = Jiang(f, h);
	                Temp[j] = z;
	                c = f / z;
	                s = h / z;
	                f = x * c + g * s;
	                g = g * c - x * s;
	                h = y * s;
	                y = y * c;
	                for (jj = 0; jj < column; jj++)
	                {
	                    x = (double)DataSvdV[jj][j];
	                    z = (double)DataSvdV[jj][i];
	                    DataSvdV[jj][j] = (double)(x * c + z * s);
	                    DataSvdV[jj][i] = (double)(z * c - x * s);
	                }
	                z = Jiang(f, h);
	                w[j] = (double)z;
	                if (z!=0.0)
	                {
	                    z = 1.0 / z;
	                    c = f * z;
	                    s = h * z;
	                }
	                f = (c * g) + (s * y);
	                x = (c * y) - (s * g);
	                for (jj = 0; jj < row; jj++)
	                {
	                    y = (double)DataSvdU[jj][j];
	                    z = (double)DataSvdU[jj][i];
	                    DataSvdU[jj][j] = (double)(y * c + z * s);
	                    DataSvdU[jj][i] = (double)(z * c - y * s);
	                }
	            }
	            Temp[l] = 0.0;
	            Temp[k] = f;
	            w[k] = (double)x;
	        }
	    }
	
	    return;
	}

	

}
