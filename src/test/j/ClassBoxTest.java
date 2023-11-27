package j;

import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ClassBoxTest {

    @Test
    public void testClassBoxConstructor() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {new ClassBox("", 2);
        });
        assertTrue(e.getMessage().contains("Bad params at ClassBox constructor"));
    }

    @Test
    public void testEquals() {
        ClassBox c1 = new ClassBox("Test", 1);
        ClassBox c2 = c1;
        assertTrue(c1.equals(c2));
    }
    
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
        Controller.STATUS_CODES c = classBox.addField("int", 1,"testType");
        assertEquals(c.toString(), "already exists");
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
        Controller.STATUS_CODES c = classBox.addParam("doo", "testP");
        assertEquals(c.toString(), "object not found");
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
        Controller.STATUS_CODES c = classBox.deleteField("doo");
        assertEquals(c.toString(), "object not found");
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
        Controller.STATUS_CODES c = classBox.renameParam("doo","pink","ponk");
        assertEquals(c.toString(), "object not found");
    }

    @Test
    public void testGetType() {
        ClassBox classBox = new ClassBox("Test", 1);
        assertEquals("CLASS", classBox.getType());
    }

    @Test
    public void testClone() {
        ClassBox classBox = new ClassBox("Test", 1);
        ClassBox clonedBox = classBox.clone();
        assertEquals(clonedBox.getType(), classBox.getType());
        assertEquals(clonedBox.getName(), classBox.getName());
    }

    @Test
    public void testDeleteMethod() {
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam1");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);
        classBox.deleteMethod("testMethod");
        assertEquals(classBox.getMethods().size(), 0);
        Controller.STATUS_CODES c = classBox.deleteMethod("doo");
        assertEquals(c.toString(), "object not found");
    }

    @Test
    public void testDeleteParam() {
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);

        classBox.deleteParam("testMethod", "testParam");
        assertEquals("[-testMethod() : int]", classBox.getMethods().toString());
        Controller.STATUS_CODES c = classBox.deleteParam("doo","pink");
        assertEquals(c.toString(), "object not found");
    }

    @Test
    public void renameMethodTest() {
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam1");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        classBox.addMethod("testMethod", 1,"int", testParams);
        classBox.renameMethod("testMethod","newTestMethod");
        assertEquals(classBox.getMethods().get(0).getName(), "newTestMethod" );
        Controller.STATUS_CODES c = classBox.renameMethod("doo","pink");
        assertEquals(c.toString(), "object not found");
    }

    @Test
    public void renameFieldTest() {
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a field
        classBox.addField("testField", 1,"int");
        classBox.renameField("testField","newTestField");
        assertEquals(classBox.getFields().get(0).getName(), "newTestField" );
        Controller.STATUS_CODES c = classBox.renameField("doo","pink");
        assertEquals(c.toString(), "object not found");
    }

    @Test
    public void testFindMethod() {
        LinkedList<String> testParams = new LinkedList<String>();
        testParams.add("testParam1");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        Methods m = new Methods("testMethod", 1, "int", testParams);
        classBox.getMethods().add(m);
        assertEquals(classBox.findMethod("testMethod"), m);
        assertNull(classBox.findMethod("dogs"));
    }

    @Test
    public void testFindField() {
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a field
        Field f = new Field("int", 1,"testType");
        classBox.getFields().add(f);
        assertEquals(f, classBox.findField("int"));
        assertNull(classBox.findField("dogs"));
    }

    @Test
    public void testAddRelationship() {
        ClassBox c1 = new ClassBox("Test", 1);
        ClassBox c2 = new ClassBox("Test2", 2);

        c1.addRelationship(c1,c2,2);
        assertEquals(c1.getChildren().get(0).toString(), "composes " + c2.getName());
        assertEquals(c2.getParents().get(0).toString(), "composes " + c1.getName());

        Controller.STATUS_CODES c = c1.addRelationship(c1,c2,2);
        assertEquals(c.toString(), "already exists");

        Controller.STATUS_CODES statCode = c2.addRelationship(c1,c2,2);
        assertEquals(statCode.toString(), "already exists");
    }

    @Test
    public void testDeleteRelationship() {
        ClassBox c1 = new ClassBox("Test", 1);
        ClassBox c2 = new ClassBox("Test2", 2);

        c1.addRelationship(c1,c2,2);
        c1.deleteRelationship(c1,c2);
        assertEquals(c1.getChildren().size(), 0);
        assertEquals(c2.getParents().size(), 0);
        c1.addRelationship(c2,c1,2);
        c2.deleteRelationship(c1,c2);
        assertEquals(c2.getChildren().size(), 0);
        assertEquals(c1.getParents().size(), 0);

        ClassBox c3 = null;
        Controller.STATUS_CODES statCode = c2.deleteRelationship(c1,c3);
        assertEquals(statCode.toString(), "object not found");
    }

    @Test
    public void testRename() {
        ClassBox c1 = new ClassBox("Test", 1);
        c1.rename("pogs");
        assertEquals(c1.getName(), "pogs");
    }

    @Test
    public void testToString() {
        ClassBox c1 = new ClassBox("Test", 1);
        assertEquals(c1.toString(), "ClassBox toString() not implemented");
    }

    @Test
    public void testGetName() {
        ClassBox c1 = new ClassBox("Test", 1);
        assertEquals(c1.getName(), "Test");
    }

    @Test
    public void testGetMethods() {
        ClassBox c1 = new ClassBox("Test", 1);
        LinkedList<String> testParams = new LinkedList<>();
        testParams.add("testParam1");
        // Add a method
        Methods m = new Methods("testMethod", 1, "int", testParams);
        LinkedList<Methods> llM = new LinkedList<>();
        llM.add(m);
        c1.getMethods().add(m);

        assertEquals(c1.getMethods(), llM);
    }

    @Test
    public void testGetFields() {
        ClassBox c1 = new ClassBox("Test", 1);
        // Add a method
        Field m = new Field("testMethod", 1, "int");
        LinkedList<Field> llM = new LinkedList<>();
        llM.add(m);
        c1.getFields().add(m);

        assertEquals(c1.getFields(), llM);
    }

    @Test
    public void testGetParents() {
        /*ClassBox c1 = new ClassBox("Test", 1);
        ClassBox c2 = new ClassBox("Test2", 2);
        Relationship r = new Relationship(c2, 2);
        c1.addRelationship(c1,c2,2);
        assertEquals(c1.getChildren().get(0), r);
        //assertEquals(c2.getParents().get(0), r);*/
    }

    @Test
    public void testListRelationShips() {
        ClassBox c1 = new ClassBox("Test", 1);
        ClassBox c2 = new ClassBox("Test2", 2);

        c1.addRelationship(c1,c2,2);
        assertEquals(c2.listRelationships()[0], "Test2 composes Test");
    }

    @Test
    public void testListMethods() {
        ClassBox c1 = new ClassBox("Test", 1);

        LinkedList<String> testParams = new LinkedList<>();
        testParams.add("testParam1");

        // Make a new classBox
        ClassBox classBox = new ClassBox("Test", 1);
        // Add a method
        c1.addMethod("testMethod", 1,"int", testParams);
        assertEquals(c1.listMethods()[0], "-testMethod(testParam1) : int");
    }

    @Test
    public void testlistFields() {
        ClassBox c1 = new ClassBox("Test", 1);

        // Add a method
        c1.addField("testMethod", 1,"int");
        assertEquals(c1.listFields()[0], "-testMethod : int");
    }

    @Test
    public void testListVisibilityTypes() {
        ClassBox c1 = new ClassBox("Test", 1);

        assertEquals(c1.listVisibilityTypes()[0], "PRIVATE");
        assertEquals(c1.listVisibilityTypes()[1], "PUBLIC");
        assertEquals(c1.listVisibilityTypes()[2], "PROTECTED");
    }

    @Test
    public void testListRelationshipSaveHelper() {
/*        ClassBox c1 = new ClassBox("Test", 1);

        assertEquals(c1.listRelationshipsSaveHelper().get(0)[0], "PRIVATE");*/
    }

    @Test
    public void testListRelationshipTypes() {
        ClassBox c1 = new ClassBox("Test", 1);

        assertEquals(c1.listRelationshipTypes()[0], "AGGREGATION");
        assertEquals(c1.listRelationshipTypes()[1], "COMPOSITION");
        assertEquals(c1.listRelationshipTypes()[2], "IMPLEMENTATION");
        assertEquals(c1.listRelationshipTypes()[3], "REALIZATION");
    }

    @Test
    public void testListClassTypes() {
        ClassBox c1 = new ClassBox("Test", 1);
        assertEquals(c1.listClassTypes()[0], "CLASS");
        assertEquals(c1.listClassTypes()[1], "INTERFACE");
        assertEquals(c1.listClassTypes()[2], "RECORD");
        assertEquals(c1.listClassTypes()[3], "ENUMERATION");
        assertEquals(c1.listClassTypes()[4], "ANNOTATION");
    }

}