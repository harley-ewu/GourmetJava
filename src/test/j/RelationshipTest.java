package j;

import j.ClassBox;
import j.Relationship;

import org.junit.Test;
import static org.junit.Assert.*;

public class RelationshipTest {

    @Test
    public void testClone() {
        // Create a new class for testing purposes
        ClassBox classBox = new ClassBox("TestClass", 1);
        // New relationship class for testing purposes
        Relationship relationship = new Relationship(classBox, "AGGREGATION");

        // Clone the relationship
        Relationship clonedRelationship = relationship.clone();

        // Test that the cloned relationship is not exactly the same because they are not referencing the same object
        assertNotSame(relationship, clonedRelationship);
        // Test that the cloned relationship is equal to the original
        assertEquals(relationship.getOtherClass(), clonedRelationship.getOtherClass());
        // Test that the cloned relationship is equal to the original
        assertEquals(relationship.getType(), clonedRelationship.getType());
    }

    @Test
    public void testToString() {
        // Make a new class for testing purposes
        ClassBox classBox = new ClassBox("TestClass", 1);
        // Make a new relationship for testing purposes
        Relationship relationship = new Relationship(classBox, "COMPOSITION");

        String expected = "composes TestClass";
        String actual = relationship.toString();
        
        // Making sure that the strings when it does toString()
        assertEquals(expected, actual);
    }

    @Test
    public void testGetOtherClass() {
        // Make a new class for testing purposes
        ClassBox classBox = new ClassBox("TestClass", 1);
        // Make a new relationship for testing purposes
        Relationship relationship = new Relationship(classBox, "IMPLEMENTATION");

        String expected = "TestClass";
        String actual = relationship.getOtherClass();

        // Making sure that the strings match from getOtherClass()
        assertEquals(expected, actual);
    }

    @Test
    public void testGetType() {
        // Make a new class for testing purposes
        ClassBox classBox = new ClassBox("TestClass", 1);
        // Make a new relationship for testing purposes
        Relationship relationship = new Relationship(classBox, "REALIZATION");

        String expected = "REALIZATION";
        String actual = relationship.getType();

        // Making sure that the strings match from getType()
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTypeOrdinal() {
        // Make a new class for testing purposes
        ClassBox classBox = new ClassBox("TestClass", 1);
        // Make a new relationship for testing purposes
        Relationship relationship = new Relationship(classBox, "AGGREGATION");

        int expected = 1;
        int actual = relationship.getTypeOrdinal();

        // Making sure that getTypeOrdinal() works correctly
        assertEquals(expected, actual);
    }

    @Test
    public void testListRelationshipTypes() {
        // Making a array of string for the ENUMS
        String[] expected = {"AGGREGATION", "COMPOSITION", "IMPLEMENTATION", "REALIZATION"};
        String[] actual = Relationship.listRelationshipTypes();

        // Matching the ENUMS to the array of strings we created to test
        assertArrayEquals(expected, actual);
    }
}