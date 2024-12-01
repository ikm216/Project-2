/**
 * Represents an internal node in a quadtree
 */
public class InternalNode extends Node {
    private Node t_left, t_right, b_left, b_right;
    private double cordx, cordy, wid, heg;

    /**
     * Constructs an InternalNode with specified boundaries.
     *
     * @param cordx the x-coordinate of the node
     * @param cordy the y-coordinate of the node
     * @param wid the width of the node
     * @param heg the height of the node
     */
    public InternalNode(double cordx, double cordy, double wid, double heg) {
        this.cordx = cordx;
        this.cordy = cordy;
        this.wid = wid;
        this.heg = heg;

        t_left = new LeafNode(cordx, cordy, wid / 2, heg / 2);
        t_right = new LeafNode(cordx + wid / 2, cordy, wid / 2, heg / 2);
        b_left = new LeafNode(cordx, cordy + heg / 2, wid / 2, heg / 2);
        b_right = new LeafNode(cordx + wid / 2, cordy + heg / 2, wid / 2, heg / 2);
    }

    /**
     * Determines the appropriate quadrant for a given rectangle.
     *
     * @param rect the rectangle to locate
     * @return the quadrant node where the rectangle should be placed
     */
    private Node getQuad(Rect rect) {
        double rectX = rect.getCordx();
        double rectY = rect.getCordy();
        double midX = cordx + wid / 2;
        double midY = cordy + heg / 2;

        if (rectX < midX) {
            return (rectY < midY) ? t_left : b_left;
        } else {
            return (rectY < midY) ? t_right : b_right;
        }
    }

    /**
     * Inserts a rectangle into the appropriate quadrant.
     *
     * @param rect the rectangle to insert
     */
    public void insertRect(Rect rect) {
        Node quad = getQuad(rect);
        quad.insertRect(rect);
    }

    /**
     * Deletes a rectangle from the appropriate quadrant.
     *
     * @param rect the rectangle to delete
     * @return true if the rectangle was successfully deleted, false otherwise
     */
    public boolean deleteRect(Rect rect) {
        Node quad = getQuad(rect);
        return quad.deleteRect(rect);
    }

    /**
     * Finds a rectangle within the appropriate quadrant by its coordinates.
     *
     * @param cordx the x-coordinate of the rectangle
     * @param cordy the y-coordinate of the rectangle
     * @return the rectangle if found, null otherwise
     */
    public Rect findRect(double cordx, double cordy) {
        Node quad = getQuad(new Rect(cordx, cordy, 0, 0));
        return quad.findRect(cordx, cordy);
    }

    /**
     * Updates the dimensions of a rectangle and repositions it if necessary.
     *
     * @param rect the rectangle to update
     * @param updatelen the new length for the rectangle
     * @param updatewid the new width for the rectangle
     */
    public void updateRect(Rect rect, double updatelen, double updatewid) {
        Node quad = getQuad(rect);
        quad.updateRect(rect, updatelen, updatewid);
        if (!getQuad(rect).equals(quad)) {
            quad.deleteRect(rect);
            insertRect(new Rect(rect.getCordx(), rect.getCordy(), updatelen, updatewid));
        }
    }

    /**
     * Dumps the structure of the internal node, recursively displaying its quadrants.
     *
     * @param level the indentation level for formatting
     */
    public void dump(int level) {
        System.out.println("  ".repeat(level) + "InternalNode");
        t_left.dump(level + 1);
        t_right.dump(level + 1);
        b_left.dump(level + 1);
        b_right.dump(level + 1);
    }
}


