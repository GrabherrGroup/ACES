package clustering;

import java.util.ArrayList;


public class DBSCAN {
    private double radius;
    private int minPts;
    private int[] labelsIndex;
	
	public int[] getLabelsIndex() {
		return labelsIndex;
	}

    public DBSCAN(double radius,int minPts) {
        this.radius = radius;
        this.minPts = minPts;
        
    }

    public void process(ArrayList<Point> points) {
    	
        int size = points.size();
	
        labelsIndex = new int[size];
        int idx = 0;
        int cluster = 1;
        while (idx<size) {
            Point p = points.get(idx++);
            //choose an unvisited point
            if (!p.getVisit()) {
                p.setVisit(true);//set visited
                ArrayList<Point> adjacentPoints = getAdjacentPoints(p, points);
                int a =0;
                a = adjacentPoints.size();
                System.out.println(a);
                //set the point which adjacent points less than minPts noised
                if (adjacentPoints != null && adjacentPoints.size() < minPts) {
                    p.setNoised(true);
                } else {
                    p.setCluster(cluster);
                    for (int i = 0; i < adjacentPoints.size(); i++) {
                        Point adjacentPoint = adjacentPoints.get(i);
                        //only check unvisited point, cause only unvisited have the chance to add new adjacent points
                        if (!adjacentPoint.getVisit()) {
                            adjacentPoint.setVisit(true);
                            ArrayList<Point> adjacentAdjacentPoints = getAdjacentPoints(adjacentPoint, points);
                            //add point which adjacent points not less than minPts noised
                            if (adjacentAdjacentPoints != null && adjacentAdjacentPoints.size() >= minPts) {
                                adjacentPoints.addAll(adjacentAdjacentPoints);
                            }
                        }
                        //add point which doest not belong to any cluster
                        if (adjacentPoint.getCluster() == 0) {
                            adjacentPoint.setCluster(cluster);
             
                            //set point which marked noised before non-noised
                            if (adjacentPoint.getNoised()) {
                                adjacentPoint.setNoised(false);
                            }
                        }
                    }
                    cluster++;
                }
            }
        }
        for (int i = 0; i < size; i++){
        	Point p = points.get(i);
        	labelsIndex[i] = p.getCluster();
        }
    }

    private ArrayList<Point> getAdjacentPoints(Point centerPoint,ArrayList<Point> points) {
        ArrayList<Point> adjacentPoints = new ArrayList<Point>();
        for (Point p:points) {
            //include centerPoint itself
           
			double distance = 0;
			for (int i = 0; i < points.size(); i++) {
				
				distance = distance + (centerPoint.getX()[i]-p.getX()[i])*(centerPoint.getX()[i]-p.getX()[i]);
	    	}
			distance = Math.sqrt(distance);

			

            if (distance<=radius) {
                adjacentPoints.add(p);
                
            }
        }
        return adjacentPoints;
    }

}