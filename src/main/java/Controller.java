package src.main.java;

import java.util.*;


public class Controller {


    // Display all program options, choose from list, call other method based on
    // choice
    private Controller() {
        // createdClasses = new ArrayList<>();
        // kb = new Scanner(System.in);
    }

    // recall menu at end if not


    // Creates a new Classbox object and adds to to the arraylist createdClasses
    // Rachael
    // Allows the user to name their class, then adds it to the list of created
    // classes
    public static boolean addClass(final String name, final String type) {
        //return ModelDiagram.addClass(name, type);
        return false;
    }

    public static boolean addClass(final String name, final int type) {
        return ModelDiagram.addClass(name, type);
    }

    // Removes class from createdClasses
    // Rachael
    // Takes in the index of the item user wants removed from the list and removes
    // it
    public static boolean deleteClass(final String name) {
        return ModelDiagram.deleteClass(name);
    }

    // Renames a classbox item that has already been created
    // Rachael
    // Takes in the index of the item they want renamed, then asks them to type in a
    // new name
    public static boolean renameClass(final String originalName, final String newName) {
        return ModelDiagram.renameClass(originalName, newName);
    }

    public static boolean addRelationship(final String cb1, final String cb2, final int type) {
        return ModelDiagram.addRelationship(cb1, cb2, type);

    }

    public static String[] listClassMethods(final String name){
        return ModelDiagram.listClassMethods(name);
    }

    public static String[] listClassFields(final String name){
        return ModelDiagram.listClassFields(name);
    }

    // Deletes a relationship between two classes while prompting the user to verify
    // they wish to delete along the way
    public static boolean deleteRelationship(final String cb1, final String cb2) {
        return ModelDiagram.deleteRelationship(cb1, cb2);

    } // End of deleteRelationship

    // className is the name of the class you want to add a method to
    public static boolean addMethod(String className, String name, int view, String type, LinkedList<String> params) {
        return ModelDiagram.addMethod(className, name, view, type, params);
    }

    // className is the name of the class you want to add a field to
    public static boolean addField(String className, String name, int view, String type) {
        return ModelDiagram.addField(className, name, view, type);
    }

    // Adds a new param to a method within the classbox if both exist
    public static boolean addParam(String className, String methodName, String paramName) {
        return ModelDiagram.addParam(className, methodName, paramName);
    }

    //deletes method based on className and methodName
    //Needs params eventually to differentiate overloaded methods
    public static boolean deleteMethod(String className, String methodName/*, LinkedList params*/) {
        return ModelDiagram.deleteMethod(className, methodName);
    }

    //deletes field based on className and methodName
    public static boolean deleteField(String className, String fieldName) {
        return ModelDiagram.deleteField(className, fieldName);
    }

    public static boolean deleteParam(String className, String methodName, String paramName) {
        return ModelDiagram.deleteParam(className, methodName, paramName);
    }

    public static boolean renameMethod(String className, String methodName, String newMethodName) {
        return ModelDiagram.renameMethod(className, methodName, newMethodName);
    }

    public static boolean renameField(String className, String fieldName, String newFieldName) {
        return ModelDiagram.renameField(className, fieldName, newFieldName);
    }

    public static boolean renameParam(String className, String methodName, String oldParamName, String newParamName) {
        return ModelDiagram.renameParam(className, methodName, oldParamName, newParamName);
    }

   

    public static String[] listClasses() {
        return ModelDiagram.listClasses();
    }

    /*
        Returns the details of a class in the format:
        {
            { Class name, Type},
            { List of Methods },
            { List of Fields }
        }
     */
    public static String[][] listAllClassDetails(final String name) {
        return ModelDiagram.listAllClassDetails(name);
    }

    // Returns an array of String arrays
    // We have a list of createdClass objects, and each createdClass object has a
    // list of relationships
    public static String[][] listRelationships() {
        return ModelDiagram.listRelationships();
    }

    public static String[] listClassTypes(){
        return ClassBox.listClassTypes();
    }

    public static String[] listAttributeTypes(){
        return Attribute.listAttributeTypes();
    }

    public static String[] listVisibilityTypes(){
        return ClassBox.listVisibilityTypes();
    }

    public static String[] listRelationshipTypes(){
        return Relationship.listRelationshipTypes();
    }


    // Allows the user to choose what Classbox item they want to see in detail
    // Rachael
    // Takes input from user on what index from the list they want to see then calls
    // a toString for that object
    public static String[] subMenu1(){
        return new String[]{"1.) List Options.",
        "These options are listing options. They will lead you to the options where you can list classes, list class details, and list relationships.",
                "The options are as listed below:",
                "1.) List Classes",
                "2.) List All Classes Detailed",
                "3.) List class details.",
                "4.) List relationships.",

        };
    }
    public static String[] subMenu2(){
        return new String[]{"Please choose a number from the options below: ",
        "1.) Add Class",
        "2.) Remove Class",
        "3.) Rename Class",
        "4.) Help",
        "5.) Back",
        };
    }
    public static String[] subMenu3(){
        return new String[]{"Please choose a number from the options below: ",
        "1.) Add Attribute",
        "2.) Remove Attribute",
        "3.) Rename Attribute",
        "4.) Help",
        "5.) Back",
        };
    }
    public static String[] subMenu4(){
        return new String[]{"Please choose a number from the options below: ",
                "1.) Add Relationship",
                "2.) Remove Relationship",
                "3.) Help",
                "4.) Back",
        };
    }
    public static String[] subMenu5(){
        return new String[]{"Please choose a number from the options below: ",
                "1.) Save",
                "2.) Load",
                "3.) Help",
                "4.) Back",
        };
    }

    public static String[] subMenu6(){
        return new String[]{"Please choose a number from the options below: ",
                "1.) Add Param to method",
                "2.) Delete Param in method",
                "3.) Rename Param in method",
                "4.) Help",
                "5.) Back",
        };
    }


    public static String[] listClassDetails(final String name) {
        return ModelDiagram.listClassDetails(name);
    }



    public static String[] listHelp() {
        return new String[]{"1.) List Options.",
                "These options are listing options. They will lead you to the options where you can list classes, list class details, and list relationships.",
                "The options are as listed below:",
                "1.) List Classes",
                "This command will display all created classes with their name.",
                "2.) List Classes Detailed",
                "This command will display all created classes with all of their elements, as in class details",
                "3.) List relationships.",
                "This command will display relationships between classes as well as the type of relationships.",
                "4.) List class details.",
                "This command shows all created classes with their index.",
                "It asks you to enter the index of the class you want to see. It will then show you all the elements associated with that class, such as type, attributes, and relationships.",
        };
    }


    public static String[] classHelp() {
        return new String[]{"2.) Class Options.",
                "These options are related to classes. They will give you the option to create, delete, and rename classes.",
                "The options are as listed below:",
                "1.) Create a class.",
                "This option will allow you to create a class. It will prompt you to name the class and choose the type from a list.",
                "2.) Delete a class",
                "This command deletes a previously created class. It will ask you for the index of the class you want to delete and remove it from created classes.",
                "3.) Rename a class",
                "This command will ask for an index of the class you would like to rename. It will then ask you for the new name and replace the old name associated with the class.",
        };
    }

    public static String[] attributeHelp() {
        return new String[]{"3.) Attribute Options.",
                "These options are related to class attributes. They will allow you to create, delete, and rename attributes associated with classes.",
                "The options are as listed below:",
                "1.) Add an attribute.",
                "This command asks for the name of the class you want to add the attribute to. It will then ask about the attribute you want to add to the class.",
                "2.) Delete an attribute.",
                "This command asks for the name of the class you want to remove the attribute from. It will then ask about the attribute you want to remove.",
                "It will delete the attribute you selected from that class.",
                "3.) Rename an attribute.",
                "This command asks for the name of the class you want to rename the attribute from. It will then ask about the attribute you want to rename.",
                "It will then prompt you for the new name and rename the attribute with the value you enter."
        };
    }

    public static String[] relationshipHelp() {
        return new String[]{"4.) Relationship Options.",
                "These options are related to relationships between classes.",
                "The options are as listed below:",
                "1.) Add a relationship.",
                "This command will add a relationship between two specified classes. It will ask for the indexes/names of the two classes.",
                "It will then ask you about the type of relationship between the two classes. That type of relationship will be created between the two classes.",
                "2.) Delete a relationship.",
                "This command deletes a previously created relationship. It will display a list of created relationships and then ask which of those you would like to delete.",
                "That relationship will then be removed from the two classes that it was associated with."
        };
    }

    public static String[] saveLoadHelp() {
        return new String[]{"5.) Save/Load.",
                "These commands will aid in saving current projects and loading previous ones.",
                "The options are as listed below:",
                "1.) Save",
                "This command will save the current classes, attributes and relationships to a file that can be loaded in a later session.",
                "2.) Load",
                "This command will load a previously saved file with information about classes, relationships, attributes, and details.",
                "It will bring in previously created classes and class information for you to continue to edit."
        };
    }

    /*
        Because this is split up with enter spaces, should this be returned as an array of string arrays?
        THIS IS A LITTLE GROSS AND I WANT MORE INPUT ON HOW TO IMPLEMENT THIS
            -David
     */
    public static String[][] help() {
        return new String[][]{
                {                   //array 1
                        "This program is a UML Editor. It lets you create, manipulate, connect, and edit classes for your program.",
                        "This help menu will walk you through each command step by step."
                },
                listHelp(),         //array 2
                classHelp(),        //array 3
                attributeHelp(),    //array 4
                relationshipHelp(), //array 5
                saveLoadHelp(),     //array 6
                {                   //array 7
                        "6.) Help.",
                        "This command displays this help menu with descriptions about each command. You can bring this list up anytime the menu resets."
                },
                {                   //array 8
                        "7.) Exit.",
                        "This command closes the program."
                },
                {                   //array 9
                        "Thank you for reading and happy editing!"
                }
        };
    }

    // PrintMenu will display the menu options and prompt the user to choose a
    // corresponding number on the menu
    public static String[] printMenu() {
        return new String[]{
                "Please choose a number from the following options below",
                "1.) List Display (Classes, Class details, Relationships)",
                "2.) Class Options (Add, Delete, Rename)",
                "3.) Attribute Options (Add, Delete, Rename)",
                "4.) Relationship Options (Add, Delete)",
                "5.) Save/Load",
                "6.) Help",
                "7.) Exit"
        };
    }


    public static int getCreatedClassesSize() {
        return ModelDiagram.getCreatedClassesSize();
    }
}
