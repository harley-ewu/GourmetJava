package src.main.java;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

// This is the class representing all data within the model
public class ModelDiagram {

    private final static ArrayList<ClassBox> createdClasses = new ArrayList<>();
    // Created classes will now be stored here
    // Methods for manipulating the model will be stored here
    // Also needs some sort of way to push data to the view so it is able to display it

    //searched the list of created classes for a ClassBox with the given name
    //returns the ClassBox object if it exists, or null otherwise
    private static ClassBox findClassBox(final String name) {
        for (ClassBox cb : createdClasses) {
            if (cb.equals(name))
                return cb;
        }
        return null;
    }


    //returns true only if a class was added
    //returns false if a class was not added or a class with the same name already existed
    //throws an exception if the input "type" int was invalid
    public static boolean addClass(final String name, final int type) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.addClass");

        ClassBox newBox = findClassBox(name);
        if (newBox != null)
            return false;

        createdClasses.add(new ClassBox(name, type));
        return true;
    }

    //returns true only if a class was deleted
    //returns false if the class was not deleted or the class with the given name DNE
    public static boolean deleteClass(final String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("bad String name passed to ModelDiagram.deleteClass");

        ClassBox targetBox = findClassBox(name);
        if (targetBox == null)
            return false;

        createdClasses.remove(targetBox);
        return true;
    }

    // This method finds classBox within createdClasses
    // If not found returns false
    // Else adds method to the classBox and returns true
    public static boolean addMethod(String className, String name, int view, String returnType, LinkedList<String> params) {
        ClassBox target = findClassBox(className);
        if (target == null)
            return false;

        return target.addMethod(name, view, returnType, params);

    }

    // This method finds classBox within createdClasses
    // If not found returns false
    // Else adds field to the classBox and returns true
    public static boolean addField(String className, String name, int view, String type) {
        ClassBox target = findClassBox(className);
        if (target == null)
            return false;

        return target.addField(name, view, type);

    }

    public static boolean addParam(String className, String methodName, String paramName) {
        ClassBox target = findClassBox(className);
        if (target == null) {
            return false;
        } else {
            target.addParam(methodName, paramName);
            return true;
        }
    }

    public static boolean deleteMethod(String className, String methodName) {
        ClassBox target = findClassBox(className);
        if (target == null) {
            return false;
        } else {
            return target.deleteMethod(methodName);
        }
    }

    public static boolean deleteField(String className, String fieldName) {
        ClassBox target = findClassBox(className);
        if (target == null) {
            return false;
        } else {
            return target.deleteField(fieldName);
        }
    }

    public static boolean deleteParam(String className, String methodName, String paramName) {
        ClassBox target = findClassBox(className);
        if (target == null) {
            return false;
        } else {
            return target.deleteParam(methodName, paramName);
        }
    }

    public static boolean renameMethod(String className, String methodName, String newMethodName) {
        ClassBox target = findClassBox(className);
        if (target == null) {
            return false;
        } else {
            return target.renameMethod(methodName, newMethodName);
        }
    }

    public static boolean renameField(String className, String fieldName, String newFieldName) {
        ClassBox target = findClassBox(className);
        if (target == null) {
            return false;
        } else {
            return target.renameField(fieldName, newFieldName);
        }
    }

    public static boolean renameParam(String className, String methodName, String oldParamName, String newParamName) {
        ClassBox target = findClassBox(className);
        if (target == null) {
            return false;
        } else {
            return target.renameParam(methodName, oldParamName, newParamName);
        }
    }


    public static String[] listClasses() {
        String[] list = new String[createdClasses.size()];
        for (int i = 0; i < createdClasses.size(); ++i) {
            list[i] = createdClasses.get(i).getName();
        }
        return list;
    }

    /*
        lists the classes in the format:
            {Class1, Class1 type},
            {Class2, Class2 types},
            etc.
        Made a separate method to not break existing code
     */
    public static String[][] listClassesAndTypes() {
        String[][] list = new String[getCreatedClassesSize()][2];
        for (int i = 0; i < getCreatedClassesSize(); ++i) {
            list[i][0] = createdClasses.get(i).getName();
            list[i][1] = createdClasses.get(i).getType();
        }
        return list;
    }

    /*
        Returns the details of a class in the format:
        {
           [0][x] - { Class name, Type},
           [1][x] - { List of Methods },
           [2][x] - { List of Fields }
           [3][x] - { List of Relationships }
        }
     */
    public static String[][] listAllClassDetails(final String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("bad name passed to ModelDiagram.listAllClassDetails");

        ClassBox box = findClassBox(name);
        if(box == null)
            return null;

        String[][] details = new String[4][];
        details[0] = new String[]{box.getName(), box.getType()};
        details[1] = box.listMethods();
        details[2] = box.listFields();
        details[3] = box.listRelationships();
        return details;
    }

    //returns true only if the class was renamed
    //returns false if the class DNE or the name was not changed
    public static boolean renameClass(final String originalName, final String newName) {
        if (originalName == null || newName == null || originalName.isEmpty() || newName.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.renameClass");

        ClassBox newBox = findClassBox(newName);
        if (newBox != null)
            return false;

        ClassBox originalBox = findClassBox(originalName);
        if (originalBox == null)
            return false;

        originalBox.rename(newName);
        return true;
    }


    //does not handle which class is the parent or child
    //returns true if a relationship was added, false otherwise (also returns false if a relationship existed)
    //idk if the type should be an int or a String
    public static boolean addRelationship(final String parentClass, final String childClass, final int type) {
        if (parentClass == null || childClass == null || parentClass.isEmpty() || childClass.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.addRelationship");

        ClassBox parent = findClassBox(parentClass);
        ClassBox child = findClassBox(childClass);
        if (parent == null || child == null)
            return false;

        Relationship relationship = ClassBox.findRelationship(parent, child);
        if (relationship != null)
            return false;

        ClassBox.addRelationship(parent, child, type);
        return true;
    }

    //returns true if a relationship between the classes was deleted
    //returns false if the box objects do not exist or if there wasn't a relationship to begin with
    public static boolean deleteRelationship(final String parentClass, final String childClass) {
        if (parentClass == null || childClass == null || parentClass.isEmpty() || childClass.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.deleteRelationship");

        ClassBox parent = findClassBox(parentClass);
        ClassBox child = findClassBox(childClass);
        if (parent == null || child == null)
            return false;

        Relationship relationship = ClassBox.findRelationship(parent, child);
        if (relationship == null)
            return false;

        ClassBox.deleteRelationship(parent, child);
        return true;
    }

    //returns list of names of ONLY each createdClasses's parent classes
    //each createdClass's list is stored in a list (list of lists)
    public static String[][] listRelationships() {
        String[][] list = new String[createdClasses.size()][];
        for (int i = 0; i < createdClasses.size(); ++i) {
            list[i] = createdClasses.get(i).listRelationships();
        }
        return list;
    }

    /*We need to return:
        Class name
            - Class type
        Its methods
            - method return types and parameter list
            - visibility
        Its fields
            - the field data types and visibility

        I'm not sure how to handle this -David
     */
    public static String[] listClassDetails(final String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.listClass");

        ClassBox box = findClassBox(name);
        if (box == null)
            return null;

        return null;
    }


    // The save method takes the current state of the program and saves it into a
    // .json file
    // Currently only a single save is supported
    public static void save() {
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
            LinkedList<Relationship> relationships = createdClasses.get(i).getChildren();
            for (int j = 0; j < relationships.size(); j++) {

                // Find second index
                int secondIndex = -1;
                for (int p = 0; p < createdClasses.size(); p++) {
                    if (createdClasses.get(p).getName().equals(relationships.get(j).getOtherClass().getName())) {
                        secondIndex = p;
                        p = createdClasses.size();
                    }
                }

                // find type index for creation
                int typeSelection = relationships.get(j).getTypeOrdinal();

                RelationshipBuilder relB = new RelationshipBuilder(i, secondIndex, typeSelection, "child");

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
        System.out.println("Your progress has been saved!");
    }

    // The load function is used to restore data that was previously saved using the
    // save function
    // Again we only support up to a single save
    public static void load() {
        // Create a File and add a scanner to it to read the data
        File inputFile = new File("SavedFile.json");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!fileScanner.hasNextLine()) {
            System.out.println("There is no save to load.");
            return;
        }
        // Delete current ClassBox's to avoid Stu loading more than once.
        createdClasses.clear();

        // Gson object created to transfer the json to Java objects
        Gson gson = new Gson();

        // Scan in each line containing a ClassBox and create it using our gson object
        while (fileScanner.hasNextLine()) {
            String classboxString = fileScanner.nextLine();
            if (classboxString.contains("name") && classboxString.contains("type")
                    && classboxString.contains("children") && classboxString.contains("parents")) {
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

            if (inputString.contains("name") && inputString.contains("type") && inputString.contains("children")
                    && inputString.contains("parents")) {
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
        System.out.println("Your previous save has been loaded!");
    }

    /*
        Lists the class methods as a list in the format:
           { [visibility symbol][method name] ([param types]) : [return type] },
           etc.
        Returns null if the ClassBox does not exist
     */
    public static String[] listClassMethods(final String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.listClassMethods");

        ClassBox box = findClassBox(name);
        if (box == null)
            return null;

        return box.listMethods();
    }

    /*
        Lists the class methods as a list in the format:
        { [visibility symbol][field name] : [field type] },
        etc.
        Returns null if the ClassBox does not exist
     */
    public static String[] listClassFields(final String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.listClassFields");

        ClassBox box = findClassBox(name);
        if (box == null)
            return null;

        return box.listFields();

    }

    public static int getCreatedClassesSize() {
        return createdClasses.size();
    }

    public static String[] getClassMethods(String className) {
        return null;
    }

    public static String[] getClassFields(String name) {
        return null;
    }


}
