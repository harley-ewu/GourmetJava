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
public class ModelDiagram implements Comparable<ClassBox> {

    private final static ArrayList<ClassBox> createdClasses = new ArrayList<>();
    // Created classes will now be stored here
    // Methods for manipulating the model will be stored here
    // Also needs some sort of way to push data to the view so it is able to display it

    private static ClassBox findClassBox(final String name) {
        for (ClassBox cb : createdClasses) {
            if (cb.equals("name"))
                return cb;
        }
        return null;
    }

    public static boolean addClass(final String name, final String type) {
        if(name == null || type == null || name.isEmpty() || type.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.addClass");

        ClassBox newBox = findClassBox(name);
        if (newBox != null)
            return false;

        createdClasses.add(new ClassBox(name, type));
        return true;
    }

    public static boolean deleteClass(final String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("bad String name passed to ModelDiagram.deleteClass");

        ClassBox targetBox = findClassBox(name);
        if (targetBox == null)
            return false;

        createdClasses.remove(targetBox);
        return true;
    }

    public static String[] listClasses() {
        String[] list = new String[createdClasses.size()];
        for (int i = 0; i < createdClasses.size(); ++i) {
            list[i] = createdClasses.get(i).getName();
        }
        return list;
    }

    public static String[] listDetailedClasses() {

        return null;
    }

    public static String[] listClassAttributes(final String cb) {

        return null;
    }

    public static boolean renameClass(final String originalName, final String newName) {
        if (originalName == null || newName == null || originalName.isEmpty() || newName.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.renameClass");

        ClassBox newBox = findClassBox(newName);
        if (newBox != null)
            return false;

        ClassBox originalBox = findClassBox(originalName);
        if (originalBox == null)
            return false;

        return originalBox.rename(newName);
    }


    //does not handle which class is the parent or child
    public static boolean addRelationship(final String cb1, final String cb2, final String type) {
        if (cb1 == null || cb2 == null || type == null || cb1.isEmpty() || cb2.isEmpty() || type.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.addRelationship");

        ClassBox box1 = findClassBox(cb1);
        ClassBox box2 = findClassBox(cb2);
        if (box1 == null || box2 == null)
            return false;

        //check if relationship between boxes exist within ClassBox

        return box1.addRelationship(box2, Integer.parseInt(type));

    }

    public static boolean deleteRelationship(final String cb1, final String cb2) {
        if (cb1 == null || cb2 == null || cb1.isEmpty() || cb2.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.deleteRelationship");

        ClassBox box1 = findClassBox(cb1);
        ClassBox box2 = findClassBox(cb2);
        if (box1 == null || box2 == null)
            return false;

        return ClassBox.deleteRelationship(box1, box2);

    }

    public static String[][] listRelationships() {
        return ClassBox.listRelationships();
    }

    public static String[] listClass(final String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.listClass");

        return ClassBox.listClass();
    }


    //might not need this/Comparable because ClassBox objects should not be visible outside of ModelDiagram/ClassBox
    @Override
    public int compareTo(final ClassBox cb) {
        return 0;
    }

    //might not need this, idk what it would be
    @Override
    public String toString() {
        return null;
    }

    public static boolean equals(final String cb1, final String cb2) {
        if (cb1 == null || cb2 == null || cb1.isEmpty() || cb2.isEmpty())
            throw new IllegalArgumentException("bad param passed to ModelDiagram.deleteRelationship");

        ClassBox box1 = findClassBox(cb1);
        ClassBox box2 = findClassBox(cb2);
        if (box1 == null || box2 == null)
            return false;

        return ClassBox.compare(box1, box2);
    }

    //might not need this, idk what it would be used for where Strings cannot be used
    private static boolean equals(final ClassBox o1, final ClassBox o2) {
        return false;
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
            LinkedList<Relationship> relationships = createdClasses.get(i).getRelationships();
            for (int j = 0; j < relationships.size(); j++) {

                // Find second index
                int secondIndex = -1;
                for (int p = 0; p < createdClasses.size(); p++) {
                    if (createdClasses.get(p).getName().equals(relationships.get(j).getFrom().getName())) {
                        secondIndex = p;
                        p = createdClasses.size();
                    }
                }

                // find type index for creation
                int typeSelection = relationships.get(j).getTypeOrdinal();

                RelationshipBuilder relB = new RelationshipBuilder(i, secondIndex, typeSelection);

                try {
                    writer.append(gson.toJson(relB));
                    writer.flush();
                    writer.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
                    && classboxString.contains("attributes") && classboxString.contains("relationships")) {
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

            if (inputString.contains("name") && inputString.contains("type") && inputString.contains("attributes")
                    && inputString.contains("relationships")) {
                endOfRelationships = true;
            } else {
                firstIndex = relB.getFirstIndex();
                secondIndex = relB.getSecondIndex();
                type = relB.getRelationshipType();
                ClassBox class1 = createdClasses.get(firstIndex);
                ClassBox class2 = createdClasses.get(secondIndex);

                class1.addRelationship(class2, type);
            }
        }
        fileScanner.close();
        System.out.println("Your previous save has been loaded!");
    }

    /*Methods needed to be implemented
        - addClass
        - deleteClass
        - renameClass
        - addRelationship
        - deleteRelationship
        - addAttribute
        - deleteAttribute
        - renameAttribute
        - Save
        - Load

        Not sure about implementation yet:
        - Some sort of method to update to view
     */
}
