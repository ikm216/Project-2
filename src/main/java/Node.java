/**
 * Abstract class of a node in a quadtree.
 */
public abstract class Node {

    /**
     * Insert a rectangle into the node.
     *
     * @param rect the rectangle to insert
     */
    public abstract void insertRect(Rect rect);

    /**
     * Delete a rectangle from the node.
     *
     * @param rect the rectangle to delete
     * @return true if the rectangle was deleted
     */
    public abstract boolean deleteRect(Rect rect);

    /**
     * Finds a rectangle within the node by its coordinates.
     *
     * @param cordx the x-coordinate of the rectangle
     * @param cordy the y-coordinate of the rectangle
     * @return the rectangle if found, null otherwise
     */
    public abstract Rect findRect(double cordx, double cordy);

    /**
     * Updates a rectangle within the node.
     *
     * @param rect the rectangle to update
     * @param updatelen the new length for the rectangle
     * @param updatewid the new width for the rectangle
     */
    public abstract void updateRect(Rect rect, double updatelen, double updatewid);

    /**
     * Dumps the structure of the node 
     *
     * @param level the indentation level for output formatting
     */
    public abstract void dump(int level);
}
