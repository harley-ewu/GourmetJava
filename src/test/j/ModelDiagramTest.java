import j.ClassBox;
import j.Controller;
import j.ModelDiagram;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;

public class ModelDiagramTest {
   
    // Test objects
    ClassBox classTest = new ClassBox("testClass",1);

    @Test
    public void testAddClass() {

        ModelDiagram.addClass("targetClass", 1);

        // Test adding a class with a null name
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addClass(null, 1));

        // Test adding a class with an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addClass("", 1));

        // Succesful Test
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.addClass("newClass", 1));
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
        // Object for successful test
        ModelDiagram.addClass("testClass", 1);

        // addMethod for successful test
        LinkedList<String> testParams = new LinkedList<String>();

        // Adding a method to test adding a parameter
        ModelDiagram.addMethod("testClass", "testMethod", 1, "returnTest", testParams);

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
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.addParam("targetFail", "testMethod", "returnTest"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.addParam("testClass", "testMethod", "returnTest"));
    }

    @Test
    public void testDeleteMethod(){
        // I need this to test parameters for the LinkedList<String> params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam1");

        // Object for successful test
        ModelDiagram.addClass("targetClass", 1);

        // Adding a method to test deleting a method
        ModelDiagram.addMethod("targetClass", "testMethod", 1, "returnTest", testParams);

        // Test deleting a method with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteMethod(null, "testMethod"));

        // Test deleting a method with methodName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteMethod("testClass", null));

        // Test deleting a method with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteMethod("", "testMethod"));

        // Test deleting a method with methodName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteMethod("testClass", ""));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.deleteMethod("targetFail", "testMethod"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.deleteMethod("targetClass", "testMethod"));
    }

    @Test
    public void testDeleteField(){
        // Object for successful test
        ModelDiagram.addClass("targetClass", 1);

        // Adding a field to test deleting a field
        ModelDiagram.addField("targetClass", "testField", 1, "returnTest");

        // Test deleting a field with className as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteField(null, "testField"));

        // Test deleting a field with fieldName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteField("testClass", null));

        // Test deleting a field with className as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteField("", "testField"));

        // Test deleting a field with fieldName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteField("testClass", ""));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.deleteField("targetFail", "testField"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.deleteField("targetClass", "testField"));
    }

    @Test
    public void testDeleteParam(){
        // Object for successful test
        ModelDiagram.addClass("testClass", 1);

        // addMethod for successful test
        LinkedList<String> testParams = new LinkedList<String>();

        // Adding a method to test adding a parameter
        ModelDiagram.addMethod("testClass", "testMethod", 1, "returnTest", testParams);

        // Adding a parameter to test deleting a parameter
        ModelDiagram.addParam("testClass", "testMethod", "deleteParam");

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
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.deleteParam("targetFail", "testMethod", "testParam"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.deleteParam("testClass", "testMethod", "deleteParam"));
    }

    @Test
    public void testRenameMethod(){
        // I need this to test parameters for the LinkedList<String> params
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam1");

        // Object for successful test
        ModelDiagram.addClass("targetClass", 1);

        // Adding a method to test renaming a method
        ModelDiagram.addMethod("targetClass", "testMethod", 1, "returnTest", testParams);

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
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.renameMethod("targetFail", "testMethod", "testNewMethod"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.renameMethod("targetClass", "testMethod", "testNewMethod"));
    }

    @Test
    public void testRenameField(){
        // Object for successful test
        ModelDiagram.addClass("targetClass", 1);

        // Adding a field to test deleting a field
        ModelDiagram.addField("targetClass", "testField", 1, "returnTest");

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
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.renameField("targetFail", "testField", "testNewField"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.renameField("targetClass", "testField", "testNewField"));
    }

    @Test
    public void testRenameParam(){
        // Object for successful test
        ModelDiagram.addClass("testClass", 1);

        // addMethod for successful test
        LinkedList<String> testParams = new LinkedList<String>();

        // Adding a method to test adding a parameter
        ModelDiagram.addMethod("testClass", "testMethod", 1, "returnTest", testParams);

        // Adding a parameter to test renaming a parameter
        ModelDiagram.addParam("testClass", "testMethod", "regParam");

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
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.renameParam("targetFail", "testMethod", "testParam", "testNewParam"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.renameParam("testClass", "testMethod", "regParam", "testNewParam"));

    }

    @Test
    public void testRenameClass(){
        // Object for successful test
        ModelDiagram.addClass("testOriginal", 1);

        // Object that already exists
        ModelDiagram.addClass("testExists", 1);

        // Test renaming a class with originalName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameClass(null, "testNew"));

        // Test renaming a class with originalName as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.renameClass("testOriginal", null));

        // Test renaming a class with newName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameClass("", "testNew"));

        // Test renaming a class with newName as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.renameClass("testOriginal", ""));

        // Test Object already exists
        assertEquals(Controller.STATUS_CODES.OBJ_ALREADY_EXISTS, ModelDiagram.renameClass("testOriginal", "testExists"));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.renameClass("targetFail", "testNew"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.renameClass("testOriginal", "testNew"));
    }

    @Test
    public void testAddRelationship(){
        // Object for successful test
        ModelDiagram.addClass("testParent", 1);

        // Object for successful test
        ModelDiagram.addClass("testChild", 1);

        // Test adding a relationship with parentClass as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addRelationship(null, "testChild", "1"));

        // Test adding a relationship with childClass as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addRelationship("testParent", null, "1"));

        // Test adding a relationship with both child and parent as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.addRelationship(null, null, "1"));

        // Test adding a relationship with parentClass as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addRelationship("", "testChild", "1"));

        // Test adding a relationship with childClass as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addRelationship("testParent", "", "1"));

        // Test adding a relationship iwth both child and parent as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addRelationship("", "", "1"));

        // Test adding a relationship with type as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.addRelationship("testParent", "testChild", ""));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.addRelationship("targetFail", "testChild", "1"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.addRelationship("testParent", "testChild", "1"));
    }

    @Test
    public void testDeleteRelationship(){
        // Object for successful test
        ModelDiagram.addClass("testParent", 1);

        // Object for successful test
        ModelDiagram.addClass("testChild", 1);

        // Adding a relationship to test deleting a relationship
        ModelDiagram.addRelationship("testParent", "testChild", "1");

        // Test deleting a relationship with cb1 as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteRelationship(null, "testChild"));

        // Test deleting a relationship with cb2 as null
        assertEquals(Controller.STATUS_CODES.NULL_STRING, ModelDiagram.deleteRelationship("testParent", null));

        // Test deleting a relationship with cb1 as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteRelationship("", "testChild"));

        // Test deleting a relationship with cb2 as an empty string
        assertEquals(Controller.STATUS_CODES.EMPTY_STRING, ModelDiagram.deleteRelationship("testParent", ""));

        // Test Object not found
        assertEquals(Controller.STATUS_CODES.OBJ_NOT_FOUND, ModelDiagram.deleteRelationship("targetFail", "testChild"));

        // Test Success
        assertEquals(Controller.STATUS_CODES.SUCCESS, ModelDiagram.deleteRelationship("testParent", "testChild"));
    }

    @Test
    public void testListClassesSuite(){
        // Objects for successful test
        ModelDiagram.addClass("testClass1", 1);
        ModelDiagram.addClass("testClass2", 1);
        ModelDiagram.addClass("testClass3", 1);

        // Apparently ModelDiagram is not just an instance in each individual test method.
        // So we will delete all the classes from before targetClass, testClass, testNew, testExists, newClass,

        ModelDiagram.deleteClass("targetClass");
        ModelDiagram.deleteClass("testClass");
        ModelDiagram.deleteClass("testNew");
        ModelDiagram.deleteClass("testExists");
        ModelDiagram.deleteClass("newClass");
        
        // Turing listClasses to an actual String to test
        String [] classes = ModelDiagram.listClasses();
        String classString = Arrays.toString(classes);
        // System.out.println(classString);

        String [][] classesAndTypesArray = ModelDiagram.listClassesAndTypes();
        StringBuilder sb = new StringBuilder();

        for (String[] array: classesAndTypesArray){
            sb.append(Arrays.toString(array));
        }

        String twoDimArrayString = sb.toString();
        System.out.println(twoDimArrayString);
        
        String [][] classDetails = ModelDiagram.listAllClassDetails("testClass1");
        StringBuilder sb2 = new StringBuilder();

        for (String[] array: classDetails){
            sb2.append(Arrays.toString(array));
        }

        String twoDimArrayString2 = sb2.toString();
        System.out.println(twoDimArrayString2);

        // Test Success
        assertEquals("[testClass1, testClass2, testClass3]", classString);
        assertEquals("[testClass1, CLASS][testClass2, CLASS][testClass3, CLASS]", twoDimArrayString);
        assertEquals("[testClass1, CLASS][][][]", twoDimArrayString2);
    }

}