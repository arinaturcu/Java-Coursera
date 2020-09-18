import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int num = 0;
        for (Point p : s.getPoints()) {
            num = num  + 1;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        double sum = 0;
        int numL = getNumPoints(s);
      
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            sum += currDist;
            prevPt = currPt;
        }
        return sum / (double)numL;
    }

    public double getLargestSide(Shape s) {
        double largest = 0;
        
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            
            if (currDist > largest) {
                largest = currDist;
            }
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        Point prevPt = s.getLastPoint();
        double largestX = prevPt.getX();
        for (Point currPt : s.getPoints()) {
            double currX = prevPt.getX();
            
            if (currX > largestX) {
                largestX = currX;
            }
            prevPt = currPt;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double maxPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
                        
            if (currPerim > maxPerim) {
                maxPerim = currPerim;
            }
        }
        return maxPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double maxPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
                        
            if (currPerim > maxPerim) {
                maxPerim = currPerim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        // perimeter
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        // number of points
        int num = getNumPoints(s);
        System.out.println("number of points = " + num);
        
        // average length
        double average = getAverageLength(s);
        System.out.println("average length = " + average);
        
        // largest side
        double largestS = getLargestSide(s);
        System.out.println("largest side = " + largestS);
        
        // largest x
        double largestX = getLargestX(s);
        System.out.println("largest x = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        String largestPerimFile = getFileWithLargestPerimeter() ;
        System.out.println("largest perimeter file = " + largestPerimFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
