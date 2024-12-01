import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class QuadtreeTest {

    private LeafNode leafNode;
    private InternalNode internalNode;

    @BeforeEach
    public void setup() {
        leafNode = new LeafNode(0, 0, 100, 100);
        internalNode = new InternalNode(0, 0, 100, 100);
    }

    
    @Test
    public void testRectCreation() {
        Rect rect = new Rect(10, 20, 30, 40);
        assertEquals(10, rect.getCordx());
        assertEquals(20, rect.getCordy());
        assertEquals(30, rect.getLen());
        assertEquals(40, rect.getWid());
    }


    @Test
    public void testLeafNodeInsertRect() {
        Rect rect = new Rect(10, 10, 20, 20);
        leafNode.insertRect(rect);
        assertEquals(rect, leafNode.findRect(10, 10));
    }

    @Test
    public void testLeafNodeDeleteRect() {
        Rect rect = new Rect(10, 10, 20, 20);
        leafNode.insertRect(rect);
        assertTrue(leafNode.deleteRect(rect));
        assertNull(leafNode.findRect(10, 10));
    }

    @Test
    public void testLeafNodeFindRect() {
        Rect rect = new Rect(15, 15, 20, 20);
        leafNode.insertRect(rect);
        assertNotNull(leafNode.findRect(15, 15));
        assertNull(leafNode.findRect(30, 30));  
    }

    @Test
    public void testLeafNodeUpdateRect() {
        Rect rect = new Rect(20, 20, 15, 15);
        leafNode.insertRect(rect);
        leafNode.updateRect(rect, 25, 25);
        Rect updatedRect = leafNode.findRect(20, 20);
        assertEquals(25, updatedRect.getLen());
        assertEquals(25, updatedRect.getWid());
    }


    @Test
    public void testInternalNodeInsertRect() {
        Rect rect1 = new Rect(10, 10, 10, 10);
        Rect rect2 = new Rect(60, 60, 10, 10);

        internalNode.insertRect(rect1);
        internalNode.insertRect(rect2);

        assertNotNull(internalNode.findRect(10, 10));
        assertNotNull(internalNode.findRect(60, 60));
    }

    @Test
    public void testInternalNodeDeleteRect() {
        Rect rect = new Rect(30, 30, 10, 10);
        internalNode.insertRect(rect);
        assertTrue(internalNode.deleteRect(rect));
        assertNull(internalNode.findRect(30, 30));
    }

    @Test
    public void testInternalNodeFindRect() {
        Rect rect = new Rect(45, 45, 15, 15);
        internalNode.insertRect(rect);
        assertNotNull(internalNode.findRect(45, 45));
        assertNull(internalNode.findRect(90, 90));  
    }

    @Test
    public void testInternalNodeUpdateRect() {
        Rect rect = new Rect(25, 25, 5, 5);
        internalNode.insertRect(rect);
        internalNode.updateRect(rect, 30, 30);

        Rect updatedRect = internalNode.findRect(25, 25);
        assertEquals(30, updatedRect.getLen());
        assertEquals(30, updatedRect.getWid());
    }


    @Test
    public void testDumpTree() {
        Rect rect1 = new Rect(10, 10, 10, 10);
        Rect rect2 = new Rect(60, 60, 10, 10);

        internalNode.insertRect(rect1);
        internalNode.insertRect(rect2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        internalNode.dump(0);

        String output = outContent.toString();
        assertTrue(output.contains("InternalNode"));
        assertTrue(output.contains("LeafNode"));
        assertTrue(output.contains(rect1.toString()));
        assertTrue(output.contains(rect2.toString()));

        System.setOut(System.out); 
    }
}

