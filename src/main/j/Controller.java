package j;

import java.util.*;

/**
 * The interface between CLI/GUI and the backend
 */
public class Controller implements j.Observable{

    private static final ArrayList<Observer> observers = new ArrayList<>();
    private static final Controller ControllerObservable = new Controller();

    public static void addSubscriber(final Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for(Observer o : Controller.observers)
            o.update();
    }

    public static void updateGUI(){
        ControllerObservable.notifyObservers();
    }

    public enum STATUS_CODES {
        EXCEPTION("operation failed - exception caught"),
        SUCCESS("operation success"),
        OBJ_ALREADY_EXISTS("already exists"),
        OBJ_NOT_FOUND("object not found"),
        OBJ_FOUND("object was found"),
        NULL_PARAM_OBJ("object is null"),
        EMPTY_STRING("entered string is empty"),
        NULL_STRING("entered string is null"),
        UNDO_FAILED("failed to perform undo"),
        REDO_FAILED("failed to perform redo");

        private final String msg;

        STATUS_CODES(final String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }

    private Controller() {
    }

    /**
     * checks if a class with the given name exists
     *
     * @param crisis name of the class
     * @return STATUS_CODES
     */
//    public static STATUS_CODES existentialCrisisExists(final String crisis) {
//        return ModelDiagram.existentialCrisisExists(crisis);
//    }

    /**
     * Adds a class with the given name and type
     *
     * @param name name of the class
     * @param type name of the class's type
     * @return STATUS_CODES
     */
    public static STATUS_CODES addClass(final String name, final int type) {
        return ModelDiagram.addClass(name, type);
    }

    /**
     * deletes a class with the given name
     *
     * @param name name of the class to delete
     * @return STATUS_CODES
     */
    public static STATUS_CODES deleteClass(final String name) {
        return ModelDiagram.deleteClass(name);
    }

    /**
     * Renames a class with the given name
     *
     * @param originalName name of the class to rename
     * @param newName      new name of the class
     * @return STATUS_CODES
     */
    public static STATUS_CODES renameClass(final String originalName, final String newName) {
        return ModelDiagram.renameClass(originalName, newName);
    }

    /**
     * Adds a relationship between 2 classes
     *
     * @param parentClass name of the "parent" class
     * @param childClass  name of the "child" class
     * @param type        type of relationship
     * @return STATUS_CODES
     */
    public static STATUS_CODES addRelationship(final String parentClass, final String childClass, final int type) {
        return ModelDiagram.addRelationship(parentClass, childClass, type);
    }

    /**
     * Adds a relationship between 2 classes
     *
     * @param parentClass name of the "parent" class
     * @param childClass  name of the "child" class
     * @param type        type of relationship
     * @return STATUS_CODES
     */
    public static STATUS_CODES addRelationship(final String parentClass, final String childClass, final String type) {
        return ModelDiagram.addRelationship(parentClass, childClass, type);
    }

    /**
     * deletes a relationship between 2 classes
     *
     * @param cb1 name of one of the classes (order does not matter)
     * @param cb2 name of the second class (order does not matter)
     * @return STATUS_CODES
     */
    public static STATUS_CODES deleteRelationship(final String cb1, final String cb2) {
        return ModelDiagram.deleteRelationship(cb1, cb2);

    }

    /**
     * Adds a method to an existing class
     *
     * @param className  name of the class to add the method to
     * @param name       name of the method being created
     * @param view       visibility of the new method
     * @param returnType return type of the new method
     * @param params     LinkedList of params the new method takes
     * @return STATUS_CODES depending on the result<br>
     * NULL_STRING/EMPTY_STRING if any Strings are null or empty<br>
     * NULL_PARAM_OBJ if the list of params is null<br>
     * OBJ_NOT_FOUND if there isn't a class with the given name<br>
     * SUCCESS if there wasn't a problem<br>
     * EXCEPTION if creating the Method object threw an exception
     */
    public static STATUS_CODES addMethod(String className, String name, int view, String returnType, LinkedList<String> params) {
        return ModelDiagram.addMethod(className, name, view, returnType, params);
    }

    /**
     * Adds a field to an existing class
     *
     * @param className name of the class to add the field to
     * @param name      name of the new field
     * @param view      visibility of the new field
     * @param type      data type of the new field
     * @return STATUS_CODES depending on the result<br>
     * NULL_STRING/EMPTY_STRING if any Strings are null or empty<br>
     * OBJ_NOT_FOUND if there isn't a class with the given name<br>
     * SUCCESS if there wasn't a problem<br>
     * EXCEPTION if creating the Field object threw an exception
     */
    public static STATUS_CODES addField(String className, String name, int view, String type) {
        return ModelDiagram.addField(className, name, view, type);
    }

    /**
     * Adds a parameter to a class's method
     *
     * @param className  name of the class
     * @param methodName name of the method
     * @param paramName  name of the new param
     * @return STATUS_CODES
     */
    public static STATUS_CODES addParam(String className, String methodName, String paramName) {
        return ModelDiagram.addParam(className, methodName, paramName);
    }

    /**
     * Deletes a method from a class
     *
     * @param className  name of the class
     * @param methodName name of the method to delete
     * @return STATUS_CODES
     */
    public static STATUS_CODES deleteMethod(String className, String methodName/*, LinkedList params*/) {
        return ModelDiagram.deleteMethod(className, methodName);
    }

    /**
     * Deletes a field from a class
     *
     * @param className name of the class
     * @param fieldName name of the field to delete
     * @return STATUS_CODES
     */
    public static STATUS_CODES deleteField(String className, String fieldName) {
        return ModelDiagram.deleteField(className, fieldName);
    }

    /**
     * Deletes a field from a class
     *
     * @param className  name of the class
     * @param methodName name of the field to delete
     * @param paramName  name of the param to delete
     * @return STATUS_CODES
     */
    public static STATUS_CODES deleteParam(String className, String methodName, String paramName) {
        return ModelDiagram.deleteParam(className, methodName, paramName);
    }

    /**
     * Renames a method in a class
     *
     * @param className     name of the class
     * @param methodName    name of the existing method
     * @param newMethodName new name of the method
     * @return STATUS_CODES
     */
    public static STATUS_CODES renameMethod(String className, String methodName, String newMethodName) {
        return ModelDiagram.renameMethod(className, methodName, newMethodName);
    }

    /**
     * Renames a field in a class
     *
     * @param className    name of the class
     * @param fieldName    name of the existing field
     * @param newFieldName new name of the field
     * @return STATUS_CODES
     */
    public static STATUS_CODES renameField(String className, String fieldName, String newFieldName) {
        return ModelDiagram.renameField(className, fieldName, newFieldName);
    }

    /**
     * Renames a param in a class's method
     *
     * @param className    name of the class
     * @param methodName   name of the method
     * @param oldParamName name of the existing param
     * @param newParamName new name of the param
     * @return STATUS_CODES
     */
    public static STATUS_CODES renameParam(String className, String methodName, String oldParamName, String newParamName) {
        return ModelDiagram.renameParam(className, methodName, oldParamName, newParamName);
    }

    /**
     * Un-does the most recent change
     *
     * @return STATUS_CODES.UNDO_FAILED if the redo failed for any reason, SUCCESS otherwise
     */
    public static STATUS_CODES undo() {
        return ModelDiagram.undo();
    }

    /**
     * Un-does the most recent undo
     *
     * @return STATUS_CODES.UNDO_FAILED if the redo failed for any reason, SUCCESS otherwise
     */
    public static STATUS_CODES redo() {
        return ModelDiagram.redo();
    }

    public final static int DETAILS_NAME_TYPE = 0;
    public final static int DETAILS_METHODS = 1;
    public final static int DETAILS_FIELDS = 2;
    public final static int DETAILS_RELATIONSHIPS = 3;

    /**
     * makes a list of all classes
     *
     * @return String[] containing the names of all classes, each name a separate element
     */
    public static String[] listClasses() {
        return ModelDiagram.listClasses();
    }

    /**
     * Lists the classes and their types
     *
     * @return String[][] in the format:<br>
     * {<br>
     * [0][x] - { Class name, Type},<br>
     * [1][x] - { List of Methods },<br>
     * [2][x] - { List of Fields }<br>
     * [3][x] - { List of Relationships }<br>
     * }
     */
    public static String[][] listAllClassDetails(final String name) {
        return ModelDiagram.listAllClassDetails(name);
    }

    public static String[][][] listEveryClassAndAllDetails(){
        return ModelDiagram.listEveryClassAndAllDetails();
    }

    /**
     * makes a list of all relationships
     *
     * @return String[][] of names of ONLY each createdClasses's parent classes<br>
     * each createdClass's list is stored in a list (list of lists)
     */
    public static String[][] listRelationships() {
        return ModelDiagram.listRelationships();
    }

    public static String[] listClassTypes() {
        return ClassBox.listClassTypes();
    }


    public static String[] listVisibilityTypes() {
        return ClassBox.listVisibilityTypes();
    }

    public static String[] listRelationshipTypes() {
        return Relationship.listRelationshipTypes();
    }

    /**
     * Lists the classes and their types
     *
     * @return String[][] in the format:<br>
     * {Class1, Class1 type},<br>
     * {Class2, Class2 types},<br>
     * etc.
     */
    public static String[][] listClassesAndTypes() {
        return ModelDiagram.listClassesAndTypes();
    }

    /**
     * Saves the program to json file<br>
     * only a single save can be made
     *
     * @return true if it saved, false if there was an error
     */
    public static boolean save() {
        return ModelDiagram.save();
    }

    /**
     * loads a save file<br>
     * only one save is supported
     *
     * @return true if it loaded, false if there was a problem
     */
    public static boolean load() {
        return ModelDiagram.load();
    }

    // Allows the user to choose what Classbox item they want to see in detail
    // Rachael
    // Takes input from user on what index from the list they want to see then calls
    // a toString for that object
   /* public static String[] subMenu1() {
        return new String[]{"\n1.) List Options.",
                "These options are listing options. They will lead you to the options where you can list classes, list class details, and list relationships.",
                "The options are as listed below:",
                "1.) List Classes",
                "2.) List all details for every class",
                "3.) List all details for one specific class",
                "4.) List all relationships for every class",
                "5.) List help for all the options in this menu and go back to the main menu",
                "6.) Back"
        };
    }

    public static String[] subMenu2() {
        return new String[]{"\nPlease choose a number from the options below: ",
                "1.) Add Class",
                "2.) Delete Class",
                "3.) Rename Class",
                "4.) Help",
                "5.) Back"
        };
    }

    public static String[] subMenu3() {
        return new String[]{"\nPlease choose a number from the options below: ",
                "1.) Add Attribute",
                "2.) Delete Attribute",
                "3.) Rename Attribute",
                "4.) Edit Method Parameters",
                "5.) Help",
                "6.) Back"
        };
    }

    public static String[] subMenu4() {
        return new String[]{"\nPlease choose a number from the options below: ",
                "1.) Add Relationship",
                "2.) Delete Relationship",
                "3.) Help",
                "4.) Back"
        };
    }

    public static String[] subMenu5() {
        return new String[]{"\nPlease choose a number from the options below: ",
                "1.) Save",
                "2.) Load",
                "3.) Help",
                "4.) Back"
        };
    }

    public static String[] subMenu6() {
        return new String[]{"\nPlease choose a number from the options below: ",
                "1.) Add Param to method",
                "2.) Delete Param in method",
                "3.) Rename Param in method",
                "4.) Help",
                "5.) Back"
        };
    }


    public static String[] listHelp() {
        return new String[]{"\n1.) List Options.",
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
                "It asks you to enter the index of the class you want to see. It will then show you all the elements associated with that class, such as type, attributes, and relationships."
        };
    }


    public static String[] classHelp() {
        return new String[]{"\n2.) Class Options.",
                "These options are related to classes. They will give you the option to create, delete, and rename classes.",
                "The options are as listed below:",
                "1.) Create a class.",
                "This option will allow you to create a class. It will prompt you to name the class and choose the type from a list.",
                "2.) Delete a class",
                "This command deletes a previously created class. It will ask you for the index of the class you want to delete and remove it from created classes.",
                "3.) Rename a class",
                "This command will ask for an index of the class you would like to rename. It will then ask you for the new name and replace the old name associated with the class."
        };
    }

    public static String[] attributeHelp() {
        return new String[]{"\n3.) Attribute Options.",
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

    public static String[] editParamHelp() {
        return new String[]{"\n4.) Edit Method Parameters",
                "These options are related to editting method parameters. They will allow you to create, delete, and rename parameters associated with methods.",
                "The options are as listed below:",
                "1.) Add Parameter to method",
                "This command first asks for the class name containing the method you are editing, then for the method containing the parameter.",
                "It then asks for the name of the new parameter you are adding.",
                "2.) Delete Parameter in method",
                "This command first asks for the class name containing the method you are editing, then for the method containing the parameter.",
                "It will then ask for the parameter name you are looking to delete.",
                "3.) Rename Parameter in method",
                "This command first asks for the class name containing the method you are editing, then for the method containing the parameter.",
                "It will then ask for the current parameter name, followed by the new parameter name."
        };
    }

    public static String[] relationshipHelp() {
        return new String[]{"\n4.) Relationship Options.",
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
        return new String[]{"\n5.) Save/Load.",
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
*/

    /**
     * displays the menu options and prompts the user to choose a
     * corresponding number on the menu
     * @return String[] with the menu items
     */
    public static String[] printMenu() {
        return new String[]{
                "\nPlease enter a full command. You will need to fill in the required parameters with the command separated by a space. Example: add class name1 2.",
                "Numbers that corespond to class types and relationship types will be listed at the end of this menu.",
                "Commands to add: ",
                "   add class [class-name] [class-type-number]",
                "   add field [class-name] [field-name] [visibility-number] [data-type]",
                "   add method [class-name] [method-name] [visibility-number] [return-type] [parameters]",
                "   add relationship [1st-class-name] [2nd-class-name] [relationship-type-number]",
                "Commands to delete: ",
                "   delete class [class-name]",
                "   delete field [class-name] [field-name]",
                "   delete method [class-name] [method-name]",
                "   delete relationship [1st-class-name] [2nd-class-name]",
                "Commands to rename: ",
                "   rename class [old-class-name] [new-class-name]",
                "   rename field [class-name] [old-field-name] [new-field-name]",
                "   rename method [class-name] [old-method-name] [new-method-name]",
                "Commands to Save/Load",
                "    save",
                "    load",
                "Commands to list items",
                "    list all",
                "    list classes",
                "    list relationships",
                "Command for help",
                "    help",
                "Command to open window interface",
                "    window",
                "Command to exit",
                "    exit"
        };
    }

    /**
     * Gets the size of the createdClasses list
     *
     * @return size of the createdClasses list
     */
    public static int getCreatedClassesSize() {
        return ModelDiagram.getCreatedClassesSize();
    }
}

