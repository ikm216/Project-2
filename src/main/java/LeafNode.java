import java.util.*;

/**
 * Represents a leaf node in a quadtree, which stores rectangles directly.
 */
public class LeafNode extends Node {
    private List<Rect> rects;
    private double cordx;
    private double cordy;
    private double wid;
    private double heg;
    private static final int max_rects = 5;

    /**
     * Constructs a LeafNode with specified boundaries.
     *
     * @param cordx the x-coordinate of the node
     * @param cordy the y-coordinate of the node
     * @param wid the width of the node
     * @param heg the height of the node
     */
    public LeafNode(double cordx, double cordy, double wid, double heg) {
        this.cordx = cordx;
        this.cordy = cordy;
        this.wid = wid;
        this.heg = heg;
        this.rects = new ArrayList<>();
    }

    /**
     * Insert a rectangle into the leaf node. If the node is full, it goes to an InternalNode.
     *
     * @param rect the rectangle to insert
     */
    public void insertRect(Rect rect) {
        if (rects.size() < max_rects) {
            rects.add(rect);
        } else {
            InternalNode internalNode = new InternalNode(cordx, cordy, heg, wid);
            for (Rect shape : rects) {
                internalNode.insertRect(shape);
            }
            internalNode.insertRect(rect);
        }
    }

    /**
     * Deletes a rectangle from the leaf node.
     *
     * @param rect the rectangle to delete
     * @return true if the rectangle was successfully deleted, false otherwise
     */
    public boolean deleteRect(Rect rect) {
        return rects.remove(rect);
    }

    /**
     * Finds a rectangle within the leaf node by its coordinates.
     *
     * @param cordx the x-coordinate of the rectangle
     * @param cordy the y-coordinate of the rectangle
     * @return the rectangle if found, null otherwise
     */
    public Rect findRect(double cordx, double cordy) {
        for (Rect rect : rects) {
            if (rect.getCordx() == cordx && rect.getCordy() == cordy) {
                return rect;
            }
        }
        return null;
    }

    /**
     * Updates the dimensions of a rectangle within the leaf node.
     *
     * @param rect the rectangle to update
     * @param updatelen the new length of the rectangle
     * @param updatewid the new width of the rectangle
     */
    public void updateRect(Rect rect, double updatelen, double updatewid) {
        if (rects.remove(rect)) {
            rect = new Rect(rect.getCordx(), rect.getCordy(), updatelen, updatewid);
            insertRect(rect);
        }
    }

    /**
     * Dumps the structure of the leaf node, showing all stored rectangles.
     *
     * @param level the indentation level for formatting
     */
    public void dump(int level) {
        System.out.println("  ".repeat(level) + "LeafNode: " + rects.size() + " rectangles");
        for (Rect rect : rects) {
            System.out.println("  ".repeat(level + 1) + rect);
        }
    }
}

