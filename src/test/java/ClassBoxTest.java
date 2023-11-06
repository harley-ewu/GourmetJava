package src.test.java;

import org.junit.Test;
import src.main.java.ClassBox;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassBoxTest {

    @Test
    public void testAddField() {
        // create a class to test
        ClassBox cb = new ClassBox("TestClass", 1);
        // Because addField returns a bool it should assertTrue = True if created successfully
        assertTrue(cb.addField("days", 1, "int"));
        // This is going to check that the size increases by 1
        assertEquals(1, cb.getFields().size());
        // This will actually check that the String is being passed in correctly
        assertEquals("days", cb.getFields().get(0).getName());
    }

}