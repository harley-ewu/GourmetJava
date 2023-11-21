<<<<<<< HEAD:src/test/java/ModelDiagramTest.java
import `org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

import src.main.java.ModelDiagram;
import src.main.java.Controller;
import src.main.java.ClassBox;
=======
package j;

import org.junit.Test;
import static org.junit.Assert.*;
>>>>>>> Development:src/test/j/ModelDiagramTest.java

public class ModelDiagramTest {
   
    // Test objects
    ClassBox classTest = new ClassBox("testClass",1);

    @Test
    public void testAddClass() {

        ModelDiagram.addClass("targetClass", 1);

        // Test adding a class with a null name
<<<<<<< HEAD:src/test/java/ModelDiagramTest.java
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addClass(null, 1));

        // Test adding a class with an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addClass("", 1));

        // Succesful Test
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.addClass("testClass", 1));
    }
    
    @Test
    public void testDeleteClass() {
        // Test deleting a class with a null name
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteClass(null));

        // Test deleting a class with an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteClass(""));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.deleteClass("targetFail"));
    }

    @Test
    public void testAddMethod(){
        // I need this to test parameters for the LinkedList<String> params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam1");

        // Object for successful test
        ModelDiagram.addClass("targetClass", 1);

        // Test adding a method with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addMethod(null, "testName", 1, "returnTest", testParams));

        // Test adding a method with name as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addMethod("testClass", null, 1, "returnTest", testParams));

        // Test adding a method with returnType as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addMethod("testClass", "testName", 1, null, testParams));

        // Test adding a method with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addMethod("", "testName", 1, "returnTest", testParams));

        // Test adding a method with name as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addMethod("testClass", "", 1, "returnTest", testParams));

        // Test adding a method with returnType as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addMethod("testClass", "testName", 1, "", testParams));

        // Test adding a method with a null object passed in params
        assertEquals(Controller.STATUS_CODES.NULL_PARAM_OBJ, ModelDiagram.addMethod("testClass", "testName", 1, "returnTest", null));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.addMethod("targetFail", "testName", 1, "returnTest", testParams));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.addMethod("targetClass", "testName", 1, "returnTest", testParams));
    }
    
    @Test
    public void testAddField(){
        // Object for successful test
        ModelDiagram.addClass("targetClass", 1);

        // Test adding a field with className as null || (final String className, final String name, int view, final String type)
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addField(null, "testName", 1, "returnTest"));

        // Test adding a field with name as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addField("testClass", null, 1, "returnTest"));

        // Test adding a field with type as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addField("testClass", "testName", 1, null));

        // Test adding a field with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addField("", "testName", 1, "returnTest"));

        // Test adding a field with name as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addField("testClass", "", 1, "returnTest"));

        // Test adding a field with type as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addField("testClass", "testName", 1, ""));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.addField("targetFail", "testName", 1, "returnTest"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.addField("targetClass", "testName", 1, "returnTest"));
    }

    @Test
    public void testAddParam(){
        // Test adding a parameter with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addParam(null, "testMethod", "returnTest"));

        // Test adding a parameter with methodName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addParam("testClass", null, "returnTest"));

        // Test adding a parameter with paraName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addParam("testClass", "testMethod", null));

        // Test adding a parameter with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addParam("", "testMethod", "returnTest"));

        // Test adding a parameter with methodName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addParam("testClass", "","returnTest"));

        // Test adding a parameter with paraName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addParam("testClass", "testMethod",""));

        // Test Object not found
    }

    @Test
    public void testDeleteMethod(){
        // Test deleting a method with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteMethod(null, "testMethod"));

        // Test deleting a method with methodName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteMethod("testClass", null));

        // Test deleting a method with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteMethod("", "testMethod"));

        // Test deleting a method with methodName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteMethod("testClass", ""));

        // Test Object not found
    }

    @Test
    public void testDeleteField(){
        // Test deleting a field with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteField(null, "testField"));

        // Test deleting a field with fieldName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteField("testClass", null));

        // Test deleting a field with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteField("", "testField"));

        // Test deleting a field with fieldName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteField("testClass", ""));

        // Test Object not found
    }

    @Test
    public void testDeleteParam(){
        // Test deleting a parameter with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteParam(null, "testMethod", "testParam"));

        // Test deleting a parameter with methodName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteParam("testClass", null, "testParam"));

        // Test deleting a parameter with paraName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteParam("testClass", "testMethod", null));

        // Test deleting a parameter with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteParam("", "testMethod", "testParam"));

        // Test deleting a parameter with methodName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteParam("testClass", "", "testParam"));

        // Test deleting a parameter with paraName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteParam("testClass", "testMethod", ""));

        // Test Object not found
    }

    @Test
    public void testRenameMethod(){
        // Test renaming a method with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameMethod(null, "testMethod", "testNewMethod"));

        // Test renaming a method with methodName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameMethod("testClass", null, "testNewMethod"));

        // Test renaming a method with newMethodName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameMethod("testClass", "testMethod", null));

        // Test renaming a method with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameMethod("", "testMethod", "testNewMethod"));

        // Test renaming a method with methodName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameMethod("testClass", "", "testNewMethod"));

        // Test renaming a method with newMethodName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameMethod("testClass", "testMethod", ""));

        // Test Object not found
    }

    @Test
    public void testRenameField(){
        // Test renaming a field with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameField(null, "testField", "testNewField"));

        // Test renaming a field with fieldName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameField("testClass", null, "testNewField"));

        // Test renaming a field with newFieldName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameField("testClass", "testField", null));

        // Test renaming a field with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameField("", "testField", "testNewField"));

        // Test renaming a field with fieldName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameField("testClass", "", "testNewField"));

        // Test renaming a field with newFieldName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameField("testClass", "testField", ""));

        // Test Object not found
    }

    @Test
    public void testRenameParam(){
        // Test renaming a parameter with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameParam(null, "testMethod", "testParam", "testNewParam"));

        // Test renaming a parameter with methodName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameParam("testClass", null, "testParam", "testNewParam"));

        // Test renaming a parameter with oldParaName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameParam("testClass", "testMethod", null, "testNewParam"));

        // Test renaming a parameter with newParaName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameParam("testClass", "testMethod", "testParam", null));

        // Test renaming a parameter with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameParam("", "testMethod", "testParam", "testNewParam"));

        // Test renaming a parameter with methodName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameParam("testClass", "", "testParam", "testNewParam"));

        // Test renaming a parameter with oldParaName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameParam("testClass", "testMethod", "", "testNewParam"));

        // Test renaming a parameter with newParaName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameParam("testClass", "testMethod", "testParam", ""));

        // Test Object not found
    }

    @Test
    public void testRenameClass(){
        // Test renaming a class with originalName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameClass(null, "testNew"));

        // Test renaming a class with originalName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameClass("testOriginal", null));

        // Test renaming a class with newName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameClass("", "testNew"));

        // Test renaming a class with newName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameClass("testOriginal", ""));

        // Test Object already exists

        // Test Object not found
    }

    @Test
    public void testAddRelationship(){
        // Test adding a relationship with parentClass as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addRelationship(null, "testChild", "testType"));

        // Test adding a relationship with childClass as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addRelationship("testParent", null, "testType"));

        // Test adding a relationship with type as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addRelationship("testParent", "testChild", null));

        // Test adding a relationship with parentClass as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addRelationship("", "testChild", "testType"));

        // Test adding a relationship with childClass as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addRelationship("testParent", "", "testType"));

        // Test adding a relationship with type as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addRelationship("testParent", "testChild", ""));

        // Test Object not found
    }

    @Test
    public void testDeleteRelationship(){
        // Test deleting a relationship with cb1 as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteRelationship(null, "testChild"));

        // Test deleting a relationship with cb2 as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteRelationship("testParent", null));

        // Test deleting a relationship with cb1 as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteRelationship("", "testChild"));

        // Test deleting a relationship with cb2 as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteRelationship("testParent", ""));
    }

    @Test
    public void testListClassMethods(){
        // Test listing methods of a class with name as null
        assertNull(ModelDiagram.listClassMethods(null));

        // Test listing methods of a class with name as an empty string
        assertNull(ModelDiagram.listClassMethods(""));
=======
        //assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addClass(null, 1));
>>>>>>> Development:src/test/j/ModelDiagramTest.java
    }

}