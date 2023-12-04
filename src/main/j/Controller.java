package j;

import java.util.*;

/**
 * The interface between CLI/GUI and the backend
 */
public class Controller implements j.Observable {

    private static final ArrayList<Observer> observers = new ArrayList<>();
    private static final Controller ControllerObservable = new Controller();

    public static void addSubscriber(final Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(final int reason, final String msg) {
        for (Observer o : Controller.observers)
            o.update(reason, msg);
    }

    public static void updateGUI(final int reason, final String msg) {
        ControllerObservable.notifyObservers(reason, msg);
    }

    public enum STATUS_CODES {
        EXCEPTION("Operation failed"),
        SUCCESS("Operation success"),
        OBJ_ALREADY_EXISTS("Class already exists"),
        OBJ_NOT_FOUND("Object not found"),
        METHOD_NOT_FOUND("not found"),
        FIELD_NOT_FOUND("not found"),
        PARAM_NOT_FOUND("not found"),
        OBJ_FOUND("Object was found"),
        NULL_PARAM_OBJ("Object is null"),
        EMPTY_STRING("The entered string is empty"),
        NULL_STRING("The entered string is null"),
        UNDO_FAILED("Failed to perform undo"),
        FILE_NOT_FOUND("File does not exist"),
        EMPTY_FILE("File specified is empty"),
        REDO_FAILED("Failed to perform redo");


        private final String msg;

        STATUS_CODES(final String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }

    public final static int ADD_CLASS = 0,
            RENAME_CLASS = 1,
            ADD_RELATIONSHIP = 2,
            DELETE_RELATIONSHIP = 3,
            DELETE_CLASS = 4,
            UPDATE_ATTRIBUTE = 5,
            UNDO = 6,
            REDO = 7,
            FULL_REFRESH = 8;

    private Controller() {
    }

    /**
     * checks if a class with the given name exists
     *
     * @param crisis name of the class
     * @return STATUS_CODES
     */
    public static STATUS_CODES existentialCrisisExists(final String crisis) {
        return ModelDiagram.existentialCrisisExists(crisis);
    }

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

    /**
     * makes a list of all classes
     *
     * @return String[] containing the names of all classes, each name a separate element
     */
    public static String[] listClasses() {
        return ModelDiagram.listClasses();
    }

    /**
     * Lists the all the details for one class
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


    /**
     * Lists the all the details for every class. An array of the results from listAllClassDetails() ran for every class.
     *
     * @return String[][][] in the format:<br>
     * {<br>
     * {<br>
     * [0][x] - { Class name, Type},<br>
     * [1][x] - { List of Methods },<br>
     * [2][x] - { List of Fields }<br>
     * [3][x] - { List of Relationships }<br>
     * },<br>
     * and another String[][] for every class<br>
     * }<br>
     */
    public static String[][][] listEveryClassAndAllDetails() {
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


    public static String[] listRelationshipTypes() {
        return Relationship.listRelationshipTypes();
    }

    /**
     * Saves the program to json file<br>
     * only a single save can be made
     *
     * @return true if it saved, false if there was an error
     */
    public static Controller.STATUS_CODES save(String fileName) {
        return ModelDiagram.save(fileName);
    }

    /**
     * loads a save file<br>
     * only one save is supported
     *
     * @return true if it loaded, false if there was a problem
     */
    public static Controller.STATUS_CODES load(String fileName) {
        return ModelDiagram.load(fileName);
    }


    /**
     * displays the menu options and prompts the user to choose a
     * corresponding number on the menu
     *
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

