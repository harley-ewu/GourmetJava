package src.test.java;

import org.junit.Test;
import src.main.java.ModelDiagram;
import static org.junit.Assert.*;

public class ModelDiagramTest {

    @Test
    public void testAddClass() {
        // Test adding a new class
        assertTrue(ModelDiagram.addClass("TestClass", 1));

        // Test adding a class with the same name
        assertFalse(ModelDiagram.addClass("TestClass", 2));

        // Test adding a class with a null name
        try {
            ModelDiagram.addClass(null, 1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException exception) {
            assertEquals("Bad parameter passed to ModelDiagram.addClass()", exception.getMessage());
        }

        // Test adding a class with an empty name
        try {
            ModelDiagram.addClass("", 1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException exception) {
            assertEquals("Bad parameter passed to ModelDiagram.addClass()", exception.getMessage());
        }
    }
}