package src.main.java;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

// This is the class representing all data within the model
public class ModelDiagram implements Comparable<ClassBox>{

    private final static ArrayList<ClassBox> createdClasses = new ArrayList<>();
    // Created classes will now be stored here
    // Methods for manipulating the model will be stored here
    // Also needs some sort of way to push data to the view so it is able to display it

    private static ClassBox findClassBox(final String name){
        for(ClassBox cb : createdClasses){
            if (cb.equals("name"))
                return cb;
        }
        return null;
    }

    public static boolean addClass(final String className, final String type){
        if(findClassBox("className") == null){
            //class does not exist
        }
        return false;
    }

    public static boolean deleteClass(final String name){

        return false;
    }

    public static String[] listClasses(){

        return null;
    }

    public static String[] listDetailedClasses() {

        return null;
    }

    public static String[] listClassAttributes(final String cb) {
        ClassBox crap = findClassBox(cb);
        return null;
    }

    @Override
    public int compareTo(final ClassBox o) {
        return 0;
    }

    @Override
    public String toString(){
        return null;
    }

    public static boolean equals(final String o1, final String o2){
        return false;
    }

    private static boolean equals(final ClassBox o1, final ClassBox o2){
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
