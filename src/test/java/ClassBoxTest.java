package src.test.java;

import org.junit.Test;
import src.main.java.ClassBox;
import src.main.java.Visibility;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class ClassBoxTest {

    @Test
    public void testAddField() {
        // Create a new ClassBox object
        ClassBox classBox = new ClassBox("Main", 1);

        // Add a new field to the ClassBox object
        classBox.addField("firstName", Visibility.PUBLIC, new LinkedList<>(), "String");

        // Check that the field was added correctly
        // Checks if one field was instantiated and added to the list
        assertEquals(1, classBox.getFields().size());

        // checks the name of the field to make sure the correct information is in there
        assertEquals("firstName", classBox.getFields().get(0).getName());
        assertEquals(Visibility.PUBLIC, classBox.getFields().get(0).getView());
        assertEquals("String", classBox.getFields().get(0).getType());
    }
}