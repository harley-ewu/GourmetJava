package j;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This is the class representing all data within the model
 */
public class ModelDiagram {


    private static ArrayList<ClassBox> createdClasses;

    /*
        Necessary so that we always have an empty list of classes to undo to
        I didn't know this was possible until I looked for it specifically
     */
    static {
        createdClasses = new ArrayList<>();
        updateChange();
    }


    /**
     * Creates a backup copy of the list of created classes (as a Memento Object) and passes that to the Caretaker to store
     *
     * @return STATUS_CODES.EXCEPTION if there was an error when creating the update, or SUCCESS otherwise
     */
    public static Controller.STATUS_CODES updateChange() {
        try {
            Memento snapshot = new Memento(createdClasses);
            Caretaker.getInstance().updateChange(snapshot);
        } catch (Exception ignored) {
            return Controller.STATUS_CODES.EXCEPTION;
        }
        Controller.updateGUI();
        return Controller.STATUS_CODES.SUCCESS;
    }

    /**
     * Calls the Caretaker.redo() method to retrieve a Memento Object that holds a LinkedList of ClassBox objects,
     * then copies that list into the createdClasses list. This un-does an "undo"
     *
     * @return STATUS_CODES.REDO_FAILED if the redo failed for any reason, SUCCESS otherwise
     */
    public static Controller.STATUS_CODES redo() {
        try {
            Caretaker.getInstance().redo().restore();
        } catch (Exception ignored) {
            return Controller.STATUS_CODES.REDO_FAILED;
        }
        return Controller.STATUS_CODES.SUCCESS;
    }

    /**
     * Calls the Caretaker.undo() method to retrieve a Memento Object that holds a LinkedList of ClassBox objects,
     * then copies that list into the createdClasses list. The list retrieved is the most recent state of the
     * program, just before the most recent change
     *
     * @return STATUS_CODES.UNDO_FAILED if the redo failed for any reason, SUCCESS otherwise
     */
    public static Controller.STATUS_CODES undo() {
        try {
            Caretaker.getInstance().undo().restore();
        } catch (Exception ignored) {
            return Controller.STATUS_CODES.UNDO_FAILED;
        }
        return Controller.STATUS_CODES.SUCCESS;
    }

    /**
     * Class that stores a list of ClassBox objects, used with Caretaker for undo/redo
     */
    public static class Memento {
        private final ArrayList<ClassBox> snap;

        /**
         * Creates a Memento Object using the createdClasses list
         *
         * @param snapshot the createdClasses list (this should always be ModelDiagram.createdClasses)
         */
        public Memento(final ArrayList<ClassBox> snapshot) {
            this.snap = new ArrayList<>();
            for (ClassBox classBox : snapshot) {
                this.snap.add(classBox.clone());
            }
        }

        /**
         * Creates a new list using a Memento object's object, then it to ModelDiagram.createdClasses
         */
        public void restore() {
            ArrayList<ClassBox> list = new ArrayList<>();
            for (ClassBox cb : this.snap) {
                list.add(cb.clone());
            }
            createdClasses = list;
        }
    }

    /**
     * Searches the list of created classes for a ClassBox with the given name
     *
     * @param name The name of the ClassBox object to find
     * @return a ClassBox object if it exists, or null otherwise
     */
    private static ClassBox findClassBox(final String name) {
        if (name == null || name.isEmpty()) return null;

        for (ClassBox cb : createdClasses) {
            if (cb.equals(name)) return cb;
        }
        return null;
    }

    /**
     * Checks if a ClassBox object exists
     *
     * @param crisis The name of the ClassBox object to search for
     * @return STATUS_CODES.NULL_STRING or EMPTY_STRING if the passed String is null or empty<br>
     * STATUS_CODES.OBJ_NOT_FOUND if the ClassBox does not exist, OBJ_FOUND if it does
     */
    public static Controller.STATUS_CODES existentialCrisisExists(final String crisis) {
        if (crisis == null) return Controller.STATUS_CODES.NULL_STRING;

        if (crisis.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        if (findClassBox(crisis) == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;
        return Controller.STATUS_CODES.OBJ_FOUND;
    }

    /**
     * Adds a class
     *
     * @param name The name of the new Class to create
     * @param type The type of the class that is being created
     * @return STATUS_CODES.NULL_STRING/EMPTY_STRING if the given name is null/empty<br>
     * OBJ_ALREADY_EXISTS if a created class with the given name already exists<br>
     * EXCEPTION if there was an error making the ClassBox object or if there was a problem updating the Memento list<br>
     * SUCCESS if there was no issue
     */
    public static Controller.STATUS_CODES addClass(final String name, final int type) {
        if (name == null) return Controller.STATUS_CODES.NULL_STRING;

        if (name.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox newBox = findClassBox(name);
        if (newBox != null) return Controller.STATUS_CODES.OBJ_ALREADY_EXISTS;

        try {
            createdClasses.add(new ClassBox(name, type));
        } catch (Exception e) {
            return Controller.STATUS_CODES.EXCEPTION;
        }

        return updateChange();
    }

    /**
     * Deletes a class with the given name
     *
     * @param name the name of the class to delete
     * @return OBJ_NOT_FOUND if the class with the given name DNE<br>
     * SUCCESS if there was no issue<br>
     * NULL_STRING/EMPTY_STRING if the given name is null/empty
     */
    public static Controller.STATUS_CODES deleteClass(final String name) {
        if (name == null) return Controller.STATUS_CODES.NULL_STRING;

        if (name.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox targetBox = findClassBox(name);
        if (targetBox == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        createdClasses.remove(targetBox);
        return updateChange();
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
    public static Controller.STATUS_CODES addMethod(final String className, final String name, final int view, final String returnType, final LinkedList<String> params) {
        if (className == null || name == null || returnType == null) return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || name.isEmpty() || returnType.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        if (params == null) return Controller.STATUS_CODES.NULL_PARAM_OBJ;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        try {
            target.addMethod(name, view, returnType, params);
            return updateChange();
        } catch (Exception e) {
            return Controller.STATUS_CODES.EXCEPTION;
        }
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
    public static Controller.STATUS_CODES addField(final String className, final String name, int view, final String type) {
        if (className == null || name == null || type == null) return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || name.isEmpty() || type.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        try {
            Controller.STATUS_CODES i = target.addField(name, view, type);
            if (i == Controller.STATUS_CODES.SUCCESS) {
                return updateChange();
            } else {
                return i;
            }
        } catch (Exception e) {
            return Controller.STATUS_CODES.EXCEPTION;
        }
    }

    /**
     * Adds a parameter to a class's method
     *
     * @param className  name of the class
     * @param methodName name of the method
     * @param paramName  name of the new param
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES addParam(final String className, final String methodName, final String paramName) {
        if (className == null || methodName == null || paramName == null) return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || methodName.isEmpty() || paramName.isEmpty())
            return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        Controller.STATUS_CODES status = target.addParam(methodName, paramName);
        if (status != Controller.STATUS_CODES.SUCCESS)
            return status;

        return updateChange();
    }

    /**
     * Deletes a method from a class
     *
     * @param className  name of the class
     * @param methodName name of the method to delete
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES deleteMethod(final String className, final String methodName) {
        if (className == null || methodName == null) return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || methodName.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        Controller.STATUS_CODES status = target.deleteMethod(methodName);
        if (status != Controller.STATUS_CODES.SUCCESS)
            return status;

        return updateChange();
    }

    /**
     * Deletes a field from a class
     *
     * @param className name of the class
     * @param fieldName name of the field to delete
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES deleteField(final String className, final String fieldName) {
        if (className == null || fieldName == null) return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || fieldName.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        Controller.STATUS_CODES status = target.deleteField(fieldName);
        if (status != Controller.STATUS_CODES.SUCCESS)
            return status;

        return updateChange();
    }

    /**
     * Deletes a field from a class
     *
     * @param className  name of the class
     * @param methodName name of the field to delete
     * @param paramName  name of the param to delete
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES deleteParam(final String className, final String methodName, final String paramName) {
        if (className == null || methodName == null || paramName == null) return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || methodName.isEmpty() || paramName.isEmpty())
            return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        Controller.STATUS_CODES status = target.deleteParam(methodName, paramName);
        if (status != Controller.STATUS_CODES.SUCCESS)
            return status;

        return updateChange();
    }

    /**
     * Renames a method in a class
     *
     * @param className     name of the class
     * @param methodName    name of the existing method
     * @param newMethodName new name of the method
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES renameMethod(final String className, final String methodName, final String newMethodName) {
        if (className == null || methodName == null || newMethodName == null)
            return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || methodName.isEmpty() || newMethodName.isEmpty())
            return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        Controller.STATUS_CODES status = target.renameMethod(methodName, newMethodName);
        if (status != Controller.STATUS_CODES.SUCCESS)
            return status;

        return updateChange();
    }

    /**
     * Renames a field in a class
     *
     * @param className    name of the class
     * @param fieldName    name of the existing field
     * @param newFieldName new name of the field
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES renameField(final String className, final String fieldName, final String newFieldName) {
        if (className == null || fieldName == null || newFieldName == null) return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || fieldName.isEmpty() || newFieldName.isEmpty())
            return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        try {
            Controller.STATUS_CODES status = target.renameField(fieldName, newFieldName);
            if (status != Controller.STATUS_CODES.SUCCESS)
                return status;

            return updateChange();
        } catch (Exception e) {
            return Controller.STATUS_CODES.EXCEPTION;
        }

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
    public static Controller.STATUS_CODES renameParam(final String className, final String methodName, final String oldParamName, final String newParamName) {
        if (className == null || methodName == null || oldParamName == null || newParamName == null)
            return Controller.STATUS_CODES.NULL_STRING;

        if (className.isEmpty() || methodName.isEmpty() || oldParamName.isEmpty() || newParamName.isEmpty())
            return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox target = findClassBox(className);
        if (target == null) {
            return Controller.STATUS_CODES.OBJ_NOT_FOUND;
        }

        try {
            Controller.STATUS_CODES status = target.renameParam(methodName, oldParamName, newParamName);
            if (status != Controller.STATUS_CODES.SUCCESS)
                return status;

            return updateChange();
        } catch (Exception e) {
            return Controller.STATUS_CODES.EXCEPTION;
        }

    }

    /**
     * makes a list of all classes
     *
     * @return String[] containing the names of all classes, each name a separate element
     */
    public static String[] listClasses() {
        String[] list = new String[createdClasses.size()];
        for (int i = 0; i < createdClasses.size(); ++i) {
            list[i] = createdClasses.get(i).getName();
        }
        return list;
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
        String[][] list = new String[getCreatedClassesSize()][2];
        for (int i = 0; i < getCreatedClassesSize(); ++i) {
            list[i][0] = createdClasses.get(i).getName();
            list[i][1] = createdClasses.get(i).getType();
        }
        return list;
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
        if (name == null || name.isEmpty()) return null;

        ClassBox box = findClassBox(name);
        if (box == null) return null;

        String[][] details = new String[4][];
        details[0] = new String[]{box.getName(), box.getType()};
        details[1] = box.listMethods();
        details[2] = box.listFields();
        details[3] = box.listRelationships();
        return details;
    }

    /**
     * renames a class
     *
     * @param originalName name of the existing class
     * @param newName      name to give to the existing class
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES renameClass(final String originalName, final String newName) {
        if (originalName == null || newName == null) return Controller.STATUS_CODES.NULL_STRING;

        if (originalName.isEmpty() || newName.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox newBox = findClassBox(newName);
        if (newBox != null) return Controller.STATUS_CODES.OBJ_ALREADY_EXISTS;

        ClassBox originalBox = findClassBox(originalName);
        if (originalBox == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        originalBox.rename(newName);
        return updateChange();
    }

    /**
     * adds a relationship between 2 classes
     *
     * @param parentClass name of the "parent" class
     * @param childClass  name of the "child" class
     * @param type        type of relationship between the classes
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES addRelationship(final String parentClass, final String childClass, final String type) {
        if (parentClass == null || childClass == null || type == null) return Controller.STATUS_CODES.NULL_STRING;

        if (parentClass.isEmpty() || childClass.isEmpty() || type.isEmpty())
            return Controller.STATUS_CODES.EMPTY_STRING;

        int relationshipType;
        try {
            relationshipType = Integer.parseInt(type);
            return addRelationship(parentClass, childClass, relationshipType);
        } catch (Exception e) {
            return Controller.STATUS_CODES.EXCEPTION;
        }
    }

    /**
     * adds a relationship between 2 classes
     *
     * @param parentClass name of the "parent" class
     * @param childClass  name of the "child" class
     * @param type        type of relationship between the classes
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES addRelationship(final String parentClass, final String childClass, final int type) {
        if (parentClass == null || childClass == null) return Controller.STATUS_CODES.NULL_STRING;

        if (parentClass.isEmpty() || childClass.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox parent = findClassBox(parentClass);
        ClassBox child = findClassBox(childClass);
        if (parent == null || child == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        Controller.STATUS_CODES status = ClassBox.addRelationship(parent, child, type);
        if (status != Controller.STATUS_CODES.SUCCESS)
            return status;

        return updateChange();
    }

    /**
     * Deletes a relationship bewteen 2 classes
     *
     * @param cb1 name of the first class
     * @param cb2 name of the seconds class
     * @return STATUS_CODES
     */
    public static Controller.STATUS_CODES deleteRelationship(final String cb1, final String cb2) {
        if (cb1 == null || cb2 == null) return Controller.STATUS_CODES.NULL_STRING;

        if (cb1.isEmpty() || cb2.isEmpty()) return Controller.STATUS_CODES.EMPTY_STRING;

        ClassBox parent = findClassBox(cb1);
        ClassBox child = findClassBox(cb2);
        if (parent == null || child == null) return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        try {
            Controller.STATUS_CODES status = ClassBox.deleteRelationship(parent, child);
            if (status != Controller.STATUS_CODES.SUCCESS)
                return status;
            return updateChange();
        } catch (Exception e) {
            return Controller.STATUS_CODES.EXCEPTION;
        }

    }

    /**
     * makes a list of all relationships
     *
     * @return String[][] of names of ONLY each createdClasses's parent classes<br>
     * each createdClass's list is stored in a list (list of lists)
     */
    public static String[][] listRelationships() {
        String[][] list = new String[createdClasses.size()][];
        for (int i = 0; i < createdClasses.size(); ++i) {
            list[i] = createdClasses.get(i).listRelationships();
        }
        return list;
    }


    /**
     * Lists classes and relationships, used for save/load
     *
     * @return ArrayList&lt;String[]&gt; in the format:<br>
     * {<br>
     * { parent, child, type (integer stored as String) },<br>
     * { parent, child, type (integer stored as String) },<br>
     * etc.<br>
     * }
     */
    public static ArrayList<String[]> listRelationshipsSaveHelper() {
        ArrayList<String[]> list = new ArrayList<>();
        for (ClassBox cb : createdClasses) {
            list.addAll(cb.listRelationshipsSaveHelper());
        }
        return list;
    }

    /**
     * Saves the program to json file<br>
     * only a single save can be made
     *
     * @return true if it saved, false if there was an error
     */
    public static boolean save() {
        // If there is nothing to save return false
        if (createdClasses.size() == 0) return false;
        // Create a gson object that will take java objects and translate them to json
        Gson gson = new Gson();
        // Create a FileWriter that will write the converted Java to SavedFile.json
        FileWriter writer = null;
        try {
            writer = new FileWriter("SavedFile.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Get relationship info ie : (index of first class), (index of second class),
        // (RelationshipType)
        for (int i = 0; i < createdClasses.size(); i++) {
            // need to store index of first ClassBox and index of second classBox to create
            // a relationship
            // index of first class will be i

            // With this for loop we are going to write each relationship to the file so
            // that we can recreate
            // them in the load method
            LinkedList<Relationship> relationships = createdClasses.get(i).getParents();
            for (int j = 0; j < relationships.size(); j++) {

                // Find second index
                int secondIndex = -1;
                for (int p = 0; p < createdClasses.size(); p++) {
                    if (createdClasses.get(p).getName().equals(relationships.get(j).getOtherClass())) {
                        secondIndex = p;
                        p = createdClasses.size();
                    }
                }

                // find type index for creation
                int typeSelection = relationships.get(j).getTypeOrdinal();

                RelationshipBuilder relB = new RelationshipBuilder(secondIndex, i, typeSelection, "child");

                try {
                    writer.append(gson.toJson(relB));
                    writer.flush();
                    writer.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Set relationships to null to avoid StackOverflow, then write ClassBoxes to
        // file
        for (int i = 0; i < createdClasses.size(); i++) {
            // Delete all relationships to avoid StackOverflow
            createdClasses.get(i).getParents().clear();
            createdClasses.get(i).getChildren().clear();
            // Now that our relationships list is empty, we can safely store each ClassBox
            // in our json file
            gson.toJson(createdClasses.get(i), writer);
            try {
                writer.flush();
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * loads a save file<br>
     * only one save is supported
     *
     * @return true if it loaded, false if there was a problem
     */
    public static boolean load() {
        // Create a File and add a scanner to it to read the data
        File inputFile = new File("SavedFile.json");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!fileScanner.hasNextLine()) {
            return false;
        }
        // Delete current ClassBox's to avoid Stu loading more than once.
        createdClasses.clear();

        // Gson object created to transfer the json to Java objects
        Gson gson = new Gson();

        // Scan in each line containing a ClassBox and create it using our gson object
        while (fileScanner.hasNextLine()) {
            String classboxString = fileScanner.nextLine();
            if (classboxString.contains("name") && classboxString.contains("type") && classboxString.contains("children") && classboxString.contains("parents")) {
                createdClasses.add(gson.fromJson(classboxString, ClassBox.class));
            }
        }

        fileScanner.close();
        // Reopen our scanner to fetch the Relationship data
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create the relationships between classes to finish restoring the save state
        String inputString = "";
        int firstIndex = -1;
        int secondIndex = -1;
        int type = -1;
        boolean endOfRelationships = false;

        while (!endOfRelationships) {
            inputString = fileScanner.nextLine();
            RelationshipBuilder relB = gson.fromJson(inputString, RelationshipBuilder.class);

            if (inputString.contains("name") && inputString.contains("type") && inputString.contains("children") && inputString.contains("parents")) {
                endOfRelationships = true;
            } else {
                firstIndex = relB.getFirstIndex();
                secondIndex = relB.getSecondIndex();
                type = relB.getRelationshipType();
                ClassBox class1 = createdClasses.get(firstIndex);
                ClassBox class2 = createdClasses.get(secondIndex);

                ClassBox.addRelationship(class1, class2, type);
            }
        }
        fileScanner.close();
        return true;
    }

    /**
     * Gets the size of the createdClasses list
     *
     * @return size of the createdClasses list
     */
    public static int getCreatedClassesSize() {
        return createdClasses.size();
    }

}
