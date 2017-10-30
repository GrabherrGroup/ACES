package clustering;

public class Point {
	private int size;
    private double[] x;


	private boolean isVisit;
    private int cluster;
    private boolean isNoised;

    public Point(double[] x) {
        this.x = x;
        this.isVisit = false;
        this.cluster = 0;
        this.isNoised = false;
    }

    public double getDistance(Point point) {
    	double temp = 0;
    	
    	for (int i = 0; i < point.size; i++) {
			
			temp = temp + (this.x[i]-point.x[i])*(this.x[i]-point.x[i]);
			System.out.println(point.x[i]);
    	}
    	temp = Math.sqrt(temp);
        return temp;
    }

    public void setX(double[] x) {
 		this.x = x;
 	}


	public double[] getX() {
		return x;
	}
  

    public void setVisit(boolean isVisit) {
        this.isVisit = isVisit;
    }

    public boolean getVisit() {
        return isVisit;
    }

    public int getCluster() {
        return cluster;
    }

    public void setNoised(boolean isNoised) {
        this.isNoised = isNoised;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public boolean getNoised() {
        return this.isNoised;
    }

    /*@Override
    public String toString() {
        return x+" "+y+" "+cluster+" "+(isNoised?1:0);
    }*/

}