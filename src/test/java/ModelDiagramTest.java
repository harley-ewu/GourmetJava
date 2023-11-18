import org.junit.Test;
import static org.junit.Assert.*;
import src.main.java.ModelDiagram;
import src.main.java.Controller;

public class ModelDiagramTest {

    @Test
    public void testAddClass() {
        // Test adding a class with a null name
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addClass(null, 1));
    }

}