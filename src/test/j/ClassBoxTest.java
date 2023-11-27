package j;

import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

public class ClassBoxTest {
    
    @Test
    public void testAddMethod() {
        // I need this to test parameters for the LinkedList<String> params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam1");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);
        // Check that the method was added
        assertEquals("[-testMethod(testParam1) : int]", classBox.getMethods().toString());
    }

    @Test
    public void testAddField() {
        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a field
        classBox.addField("int", 1,"testType");
        // Check that the field was added
        assertEquals("[-int : testType]", classBox.getFields().toString());
    }

    @Test
    public void addParam(){
        // I need this to test parameters for the LinkedList<String> params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);        
        // Add a field
        classBox.addParam("testMethod", "addedParam");
        // Check that the field was added
        assertEquals("[-testMethod(testParam, addedParam) : int]", classBox.getMethods().toString());
    }

    @Test
    public void testDeleteField(){
        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a field
        classBox.addField("testField", 1,"int");
        // Delete the field
        classBox.deleteField("testField");
        // Check that the field was deleted
        assertEquals("[]", classBox.getFields().toString());
    }

    @Test
    public void testRenameParam(){
        // I need this to test parameters for the LinkedList<String> params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);        
        // Rename the param
        classBox.renameParam("testMethod", "testParam", "renamedParam");
        // Check that the param was renamed 
        // THIS NEEDS TO BE FIXED // It should be [-testMethod(renamedParam) : int] as output
        assertEquals("[-testMethod(renamedParam) : int]", classBox.getMethods().toString());
    }

    @Test
    public void testAttributeSetName(){
        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam");
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);
        // Rename the method
        classBox.getMethods().get(0).setName("renamedMethod");
        // 
        assertEquals("[-renamedMethod(testParam) : int]", classBox.getMethods().toString());
    }

    @Test
    public void testAttributeCompareTo(){
        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam");
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);
        // Add a method
        classBox.addMethod("testMethod2", 1,"int", testParams);
        // Compare the methods
        assertEquals(-1, classBox.getMethods().get(0).compareTo(classBox.getMethods().get(1)));
    }
}