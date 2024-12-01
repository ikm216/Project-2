/**
 * Represents a rectangle with the coordinates, length, and width.
 */
public class Rect {
    private double cordx;
    private double cordy;
    private double len;
    private double wid;

    /**
     * Constructs a Rect with specified coordinates
     *
     * @param cordx the x-coordinate of the rectangle
     * @param cordy the y-coordinate of the rectangle
     * @param len the length of the rectangle
     * @param wid the width of the rectangle
     */
    public Rect(double cordx, double cordy, double len, double wid) {
        this.cordx = cordx;
        this.cordy = cordy;
        this.len = len;
        this.wid = wid;
    }

    public double getCordx(){ 
        return cordx; 
    }
    public double getCordy(){ 
        return cordy; 
    }
    public double getLen(){ 
        return len; 
    }
    public double getWid(){ 
        return wid; 
    }
}

