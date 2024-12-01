/**
 * The Main class demonstrates the functionality of the Quadtree data structure.
 * It uses the InternalNode, LeafNode, and Rect classes to:
 * - Insert rectangles into the Quadtree.
 * - Search for a rectangle at a specific point.
 * - Update an existing rectangle.
 * - Delete a rectangle.
 * - Dump the tree structure to view the layout of the Quadtree.
 */

public class Main {
    public static void main(String[] args) {
        InternalNode rootNode = new InternalNode(0, 0, 100, 100);

        System.out.println("Inserting Rectangles:");
        Rect rect1 = new Rect(10, 10, 5, 5);
        Rect rect2 = new Rect(15, 15, 3, 3);
        Rect rect3 = new Rect(5, 5, 2, 2);
        Rect rect4 = new Rect(20, 20, 4, 4);

        rootNode.insertRect(rect1);
        rootNode.insertRect(rect2);
        rootNode.insertRect(rect3);
        rootNode.insertRect(rect4);

        System.out.println("\nTree Structure After Insertion:");
        rootNode.dump(0);

        System.out.println("\nFinding Rectangle at (15, 15):");
        Rect foundRect = rootNode.findRect(15, 15);
        if (foundRect != null) {
            System.out.println("Found rectangle: Length = " + foundRect.getLen() + ", Width = " + foundRect.getWid());
        } else {
            System.out.println("Rectangle not found.");
        }

        System.out.println("\nUpdating Rectangle at (10, 10):");
        rootNode.updateRect(rect1, 10, 10); 
        rootNode.dump(0);

        System.out.println("\nDeleting Rectangle at (20, 20):");
        boolean isDeleted = rootNode.deleteRect(rect4);
        if (isDeleted) {
            System.out.println("Rectangle deleted successfully.");
        } else {
            System.out.println("Rectangle not found.");
        }

        System.out.println("\nTree Structure After Deletion:");
        rootNode.dump(0);
    }
}
