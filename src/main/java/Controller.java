package src.main.java;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Controller {
    private final static ArrayList<ClassBox> createdClasses = new ArrayList<>();
    private final static Scanner kb = new Scanner(System.in);
    //Display all program options, choose from list, call other method based on choice
    public Controller(){
        //createdClasses = new ArrayList<>();
       // kb = new Scanner(System.in);
    }
    //recall menu at end if not
    public static void menu(){
        boolean cont = true;
        while(cont) {
            printMenu();
            int input2;
            System.out.print("Choice:");
            //get user input of 1-15
            //call io method below
            //io method calls actual method in other classes
            int input = Integer.parseInt(kb.nextLine());
                switch (input) {
                    case 1:
                        if (createdClasses.isEmpty()) {
                            System.out.println("Nothing to display! Please make a class first");
                        } else {
                            System.out.println("Please choose a number from the options below: ");
                            System.out.println("1.) Display Classes");
                            System.out.println("2.) Display Classes Detailed");
                            System.out.println("3.) Display Class Details");
                            System.out.println("4.) Display Relationships");
                            System.out.println("5.) Help");
                            System.out.println("6.) Back");
                            System.out.print("Choice:");
                            input2 = Integer.parseInt(kb.nextLine());
                            if (input2 == 1) {
                               listClasses();
                            } else if (input2 == 2) {
                                listDetailedClasses();
                            } else if (input2 == 3) {
                                listClass();
                            } else if (input2 == 4) {
                                listRelationships();
                            } else if (input2 == 5) {
                                listHelp();
                            } else if (input2 == 6) {
                                return;
                            } else {
                                System.out.println("Invalid input, please try again");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Please choose a number from the options below: ");
                        System.out.println("1.) Add Class");
                        System.out.println("2.) Remove Class");
                        System.out.println("3.) Rename Class");
                        System.out.println("4.) Help");
                        System.out.println("5.) Back");
                        System.out.print("Choice:");
                        input2 = Integer.parseInt(kb.nextLine());
                        if (input2 == 1) {
                            addClass();
                        } else if (input2 == 2) {
                            deleteClass();
                        } else if (input2 == 3) {
                            renameClass();
                        } else if (input2 == 4) {
                            classHelp();
                        } else if (input2 == 5) {
                            return;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                        break;
                    case 3:
                        if (createdClasses.isEmpty()) {
                            System.out.println("Please create a class first");
                        } else {
                            System.out.println("Please choose a number from the options below: ");
                            System.out.println("1.) Add Attribute");
                            System.out.println("2.) Remove Attribute");
                            System.out.println("3.) Rename Attribute");
                            System.out.println("4.) Help");
                            System.out.println("5.) Back");
                            System.out.print("Choice:");
                            input2 = Integer.parseInt(kb.nextLine());
                            if (input2 == 1) {
                                addAttribute();
                            } else if (input2 == 2) {
                                deleteAttribute();
                            } else if (input2 == 3) {
                                renameAttribute();
                            } else if (input2 == 4) {
                                attributeHelp();
                            } else if (input2 == 5) {
                                return;
                            } else {
                                System.out.println("Invalid input, please try again");
                            }
                        }
                        break;
                    case 4:
                        if (createdClasses.size() < 2) {
                            System.out.println("Please create 2 classes first");
                        } else {
                            System.out.println("Please choose a number from the options below: ");
                            System.out.println("1.) Add Relationship");
                            System.out.println("2.) Remove Relationship");
                            System.out.println("3.) Help");
                            System.out.println("4.) Back");
                            System.out.print("Choice:");
                            input2 = Integer.parseInt(kb.nextLine());
                            if (input2 == 1) {
                                addRelationship();
                            } else if (input2 == 2) {
                                deleteRelationship();
                            } else if (input2 == 3) {
                                relationshipHelp();
                            } else if (input2 == 4) {
                                return;
                            } else {
                                System.out.println("Invalid input, please try again");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Please choose a number from the options below: ");
                        System.out.println("1.) Save");
                        System.out.println("2.) Load");
                        System.out.println("3.) Help");
                        System.out.println("4.) Back");
                        System.out.print("Choice:");
                        input2 = Integer.parseInt(kb.nextLine());
                        if (input2 == 1) {
                            if (createdClasses.isEmpty()) {
                                System.out.println("Nothing to save!");
                            } else {
                                save();
                            }
                        } else if (input2 == 2) {
                            load();
                        } else if (input2 == 3) {
                            saveLoadHelp();
                        } else if (input2 == 4) {
                            return;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                        break;
                    case 6:
                        help();
                        break;
                    case 7:
                        System.out.println("Are you sure you want to exit? Type \"yes\" to confirm");
                        System.out.print("yes/no:");
                        if (kb.nextLine().equalsIgnoreCase("yes")) {
                            System.out.println("Program Closed! Bye!");
                            cont = false;
                        }
                        break;
                    default:
                        System.out.println("That is not a valid input. Please try again");
                        break;
                }
        }
    }

    // PrintMenu will display the menu options and prompt the user to choose a
    // corresponding number on the menu
    public static void printMenu() {
        System.out.println("\nPlease choose a number from the following options below");
        System.out.println("1.) List Display (Classes, Class details, Relationships)");
        System.out.println("2.) Class Options (Add, Delete, Rename)");
        System.out.println("3.) Attribute Options (Add, Delete, Rename)");
        System.out.println("4.) Relationship Options (Add, Delete)");
        System.out.println("5.) Save/Load");
        System.out.println("6.) Help");
        System.out.println("7.) Exit");
    }

    // Creates a new Classbox object and adds to to the arraylist createdClasses
    // Rachael
    // Allows the user to name their class, then adds it to the list of created
    // classes
    public static void addClass() {
        System.out.println("What would you like to name your class?");
        System.out.print("Class Name:");
        String name = kb.nextLine();
        boolean copy = false;
        for(ClassBox c: createdClasses){
            if (name.equalsIgnoreCase(c.getName())) {
                copy = true;
                break;
            }
        }
        if(copy){
            System.out.println("Sorry, that class name is taken!");
        }
        else {
            System.out.println("What is the class's type?");

            ClassBox.printClassTypes();

            int result = 0;
            System.out.print("Type #:");
            String type = kb.nextLine();

            try {
                result = Integer.parseInt(type);
            } catch (Exception e) {
                System.out.print("Input is not valid. Please try again.");
                return;
            }
            try {
                ClassBox newClass = new ClassBox(name, result);
                createdClasses.add(newClass);
                System.out.println("Class created!");
            }
            catch (Exception e){
                System.out.println("Bad inputs, no class created");
            }
        }

    }

    // Removes class from createdClasses
    // Rachael
    // Takes in the index of the item user wants removed from the list and removes
    // it
    public static void deleteClass() {
        if (createdClasses.isEmpty()) {
            System.out.println("Nothing to delete!");
        }
        else{
            System.out.println("What index do you want to remove?");
            listClasses();
            System.out.print("Class Index:");
            int input = Integer.parseInt(kb.nextLine());
            if(input > 0 && input<=createdClasses.size()){
                ClassBox clas = createdClasses.get(input-1);
                for(ClassBox otherClass: createdClasses){
                    if(!(otherClass.equals(clas))) {
                        for (Relationship r : otherClass.getRelationships()) {
                            if (r.getFrom().equals(clas)) {
                                otherClass.deleteRelationship(clas);
                            }
                        }
                    }
                }
                createdClasses.remove(clas);
                System.out.println("Class deleted");
            }
            else{
                System.out.println("Invalid input. Try again");
            }
        }
    }

    // Renames a classbox item that has already been created
    // Rachael
    // Takes in the index of the item they want renamed, then asks them to type in a
    // new name
    public static void renameClass() {
        if (createdClasses.isEmpty()) {
            System.out.println("Nothing to rename!");
         }
         else{
            System.out.println("What index do you want to rename?");
            listClasses();
            System.out.print("Class Index:");
            int num = Integer.parseInt(kb.nextLine());
            if(num > 0 && num<=createdClasses.size()){
                int index = num-1;
                System.out.println("What would you like to rename your class?");
                System.out.print("New Class Name:");
                String name = kb.nextLine();
                createdClasses.get(index).renameClass(name);
                System.out.println("Class renamed!");
                listClasses();
            }
            else{
            System.out.println("Invalid input. Try again");
            }
         }
    }
    public static void addRelationship(){
        listClasses();
        System.out.println("What is the index of the first class you want to have a relationship? (The lower/to class, e.g this implements the other class)");
        System.out.print("Class 1 Index:");
        int index1 = Integer.parseInt(kb.nextLine());
        System.out.println("What is the index of the second class you want to have a relationship?(The higher/from class, e.g the other class implements this)");
        System.out.print("Class 2 Index:");
        int index2 = Integer.parseInt(kb.nextLine());
        Relationship.printRelationshipTypes();
        System.out.println("Please select an option for the relationship type by number");
        System.out.print("Type #:");
        int num = Integer.parseInt(kb.nextLine());
        if (index1 < 1 || index2 < 1 || index1 > createdClasses.size() || index2 > createdClasses.size()) {
            System.out.println("Thats not a vaild option. Please try again");

        } else {
            System.out.println("Relationship created!");
            try {
                createdClasses.get(index1 - 1).addRelationship(createdClasses.get(index2 - 1), num);
            }
            catch (Exception e){
                System.out.println("Bad inputs for relationship, creation cancelled");
            }
        }
    }


    // Deletes a relationship between two classes while prompting the user to verify they wish to delete along the way
    public static void deleteRelationship() {
        // Add an else for if one of the names isn't found
        ClassBox c1 = null, c2 = null;
        listClasses();
        System.out.println("What is the index of the first class of this relationship?");
        System.out.print("Class Index:");
        int className = Integer.parseInt(kb.nextLine());
        System.out.println("What is the index of the second");
        System.out.print("Class Index:");
        int className2 = Integer.parseInt(kb.nextLine());
        System.out.println("Are you sure you want to delete this relationship? Please write yes or no.");
        System.out.print("yes/no:");
        String answer = kb.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            c1 = createdClasses.get(className-1);
            c2 = createdClasses.get(className2-1);
            if(c1==null && c2==null){
                System.out.println("Error, classes not found");
            }
            else if(c1==null||c2==null) {
                if (c1 == null) {
                    System.out.println("Error, class 1 not found");
                }
                else{
                    System.out.println("Error, class 2 not found");
                }
            }
            else {
                try {
                    c1.deleteRelationship(c2);
                }
                catch (Exception e){
                    System.out.println("No relationship found");
                }
            }
        } else {
            System.out.println("No relationship deleted");
        }

    } // End of deleteRelationship

    //Adds an attribute to a given class
    //NEW: ask for field or method, do io based on each, pass fields to classbox add
    public static void addAttribute() {
        listClasses();
        System.out.println("What is the index of the class you would like to add an attribute to?");
        System.out.print("Class Index:");
        int ind = Integer.parseInt(kb.nextLine());
        if(ind < 1 || ind>createdClasses.size()){
            System.out.println("That class does not exist.");
        }
        else {
            ClassBox c = createdClasses.get(ind - 1);
            System.out.println("What would you like to call your attribute?");
            System.out.print("Attribute name:");
            String attributeName = kb.nextLine();
            System.out.println("What is the attribute's view? (public, private, or protected)");
            System.out.print("public/private/protected:");
            String view = kb.nextLine().trim().toLowerCase(Locale.ROOT);
            if (!(view.equals("public") || view.equals("private") || view.equals("protected"))) {
                System.out.println("Invalid view");
                return;
            }
            System.out.println("Enter any applicable modifiers, such as static, seperated by commas(a,b,c)");
            System.out.print("Tags:");
            String modstring = kb.nextLine();
            String[] modarray = modstring.split(",");
            LinkedList<String> modll = new LinkedList<String>(Arrays.asList(modarray));
            System.out.println("Enter the type (the return type if it is a method, the variable type if it is a variable");
            System.out.print("Type:");
            String type = kb.nextLine();
            System.out.println("Enter the parameters, seperated by commas, if this is a method (Leave blank for a variable)");
            System.out.print("Parameters:");
            String params = kb.nextLine();
            LinkedList<String> paramsss = null;
            if (!params.isEmpty()){
                String[] paramss = params.split(",");
                paramsss = new LinkedList<String>(Arrays.asList(paramss));
            }
            try {
                c.addAttribute(attributeName, view, modll, type, paramsss);
                System.out.println(attributeName + " has been added to " + c.getName());
            }
            catch (Exception e){
                System.out.println("Bad inputs, no attribute created");
            }
        }
    }// end addAttribute


    // Finds an attribute, checks with the user to verify intent, then deletes the attribute
    //NEW - search both class lists on name, if in method twice, ask for params
    public static void deleteAttribute() {
        listClasses();
        System.out.println("What is the index of the class you'd like to remove an attribute from?");
        System.out.print("Class Index:");
        int ind = Integer.parseInt(kb.nextLine());
        ClassBox c = createdClasses.get(ind-1);
        LinkedList<Attribute> attList = c.getAttributes();
        if(!(attList.isEmpty())) {
            System.out.println("Which attribute would you like to delete?");
            //find attributes using a loop
            for(int i = 0; i<attList.size(); i++){
                System.out.println(i+1+".)"+attList.get(i).toString());
            }
            System.out.print("Attribute Index:");
            int index = Integer.parseInt(kb.nextLine());

            System.out.println("Are you sure you want to delete " + attList.get(index-1).getName() + "? Please enter yes or no.");
            System.out.print("yes/no:");
            String answer = kb.nextLine().toLowerCase();
            //stick the part below in a loop so if an incorrect input is entered, it'll re-prompt
            while(!(answer.equals("yes") || answer.equals("no"))){
                    System.out.println("That is not a valid input. Please enter yes or no.");
                    System.out.print("yes/no:");
                    answer = kb.nextLine().toLowerCase();
            }//end of while loop checking for valid input
            if (answer.equals("yes")) {
                c.deleteAttribute(attList.get(index-1));
            } else if (answer.equals("no")) {
                System.out.println("Canceled");
            }

        }
        else {
            System.out.println("No attributes found for class " + c.getName());
        }
    }

    public static void renameAttribute() {

        if(createdClasses.isEmpty()){
            System.out.println("Nothing to rename!");
        }
        
        else{

            listClasses();
            System.out.println("What is the index of the class you'd like to rename an attribute from?");
            System.out.print("Class Index:");
            int ind = Integer.parseInt(kb.nextLine());
            ClassBox c = createdClasses.get(ind-1);
            LinkedList<Attribute> attList = c.getAttributes();
            if(!(attList.isEmpty())) {
                System.out.println("Which attribute would you like to rename?");
                //find attributes using a loop
                for (int i = 0; i < attList.size(); i++) {
                    System.out.println(i + 1 + ".)" + attList.get(i).toString());
                }
                System.out.println("Attribute Index:");
                int index = Integer.parseInt(kb.nextLine());
                System.out.println("What would you like the new name to be?");
                System.out.print("New Name:");
                String newName = kb.nextLine();
                try {
                    c.renameAttribute(attList.get(index - 1), newName);
                }
                catch (Exception e){
                    System.out.println("Bad new name, no change done");
                }
            }
            else {
                System.out.println("No attributes found for class " + c.getName());
            }
        }

    }

    // The save method takes the current state of the program and saves it into a .json file
    // Currently only a single save is supported
    public static void save(){
        //Create a gson object that will take java objects and translate them to json
        Gson gson = new Gson();
        // Create a FileWriter that will write the converted Java to SavedFile.json
        FileWriter writer = null;
        try {
            writer = new FileWriter("SavedFile.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Get relationship info ie : (index of first class), (index of second class), (RelationshipType)
        for (int i = 0; i < createdClasses.size(); i++) {
            //need to store index of first ClassBox and index of second classBox to create a relationship
            // index of first class will be i

            //With this for loop we are going to write each relationship to the file so that we can recreate
            //them in the load method
            LinkedList<Relationship> relationships = createdClasses.get(i).getRelationships();
            for(int j = 0; j < relationships.size(); j++) {

                //Find second index
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

        //Set relationships to null to avoid StackOverflow, then write ClassBoxes to file
        for (int i = 0; i < createdClasses.size(); i++) {
            //Delete all relationships to avoid StackOverflow
            createdClasses.get(i).getRelationships().clear();
            //Now that our relationships list is empty, we can safely store each ClassBox in our json file
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

    //The load function is used to restore data that was previously saved using the save function
    //Again we only support up to a single save
    public static void load(){
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

        //Gson object created to transfer the json to Java objects
        Gson gson = new Gson();

        // Scan in each line containing a ClassBox and create it using our gson object
        while (fileScanner.hasNextLine()) {
            String classboxString = fileScanner.nextLine();
            if (classboxString.contains("name") && classboxString.contains("type") && classboxString.contains("attributes") && classboxString.contains("relationships")) {
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

            if (inputString.contains("name") && inputString.contains("type") && inputString.contains("attributes") && inputString.contains("relationships")) {
                endOfRelationships = true;
            }
            else {
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



    public static String[] listClasses() {
        //System.out.println("Current Class list");
        String[] classes = new String[createdClasses.size()];
        for (int i = 0; i < createdClasses.size(); i++) {
            classes[i] = createdClasses.get(i).getName();
        }
        return classes;
    }

    //Returns a list of Strings, each String holding the detailed info for a CreatedClass
    public static String[] listDetailedClasses(){
        String[] classDetails = new String[createdClasses.size()];
        for(int i = 0; i < createdClasses.size(); i++){
            classDetails[i] = createdClasses.get(i).toString();
        }
        return classDetails;
    }

    //What are attributes?
    public static String[] listAttributes(ClassBox cb){
        if(cb == null) {
            throw new IllegalArgumentException("null ClassBox object passed to listAttributes");
        }
        return cb.getAttributes();
    }


    //Returns an array of String arrays
    //We have a list of createdClass objects, and each createdClass object has a list of relationships
    public static String[][] listRelationships(){
        String[][] rels = new String[createdClasses.size()][];
        for(int i= 0; i < createdClasses.size(); ++i){
            rels[i] = createdClasses.get(i).listRelationships();
        }
        return rels;
    }

    // Allows the user to choose what Classbox item they want to see in detail
    // Rachael
    // Takes input from user on what index from the list they want to see then calls
    // a toString for that object
    public static String[] listClass() {
        System.out.println("What index do you want to see?");
        listClasses();
        System.out.print("Class Index:");
        int input = Integer.parseInt(kb.nextLine());
        if(input > 0&&input<=createdClasses.size()){
            System.out.println(createdClasses.get(input -1).toString());
        }
        else{
            System.out.println("Invalid input. Try again");
        }

    }
    public static String[] listHelp(){
        System.out.println("1.) List Options.");
        System.out.println("These options are listing options. They will lead you to the options where you can list classes, list class details, and list relationships.");
        System.out.println("The options are as listed below:");
        System.out.println("");
        System.out.println("1.) List Classes");
        System.out.println("This command will display all created classes with their name.");
        System.out.println("");
        System.out.println("2.) List Classes Detailed");
        System.out.println("This command will display all created classes with all of their elements, as in class details");
        System.out.println();
        System.out.println("3.) List relationships.");
        System.out.println("This command will display relationships between classes as well as the type of relationships." );
        System.out.println("");
        System.out.println("4.) List class details.");
        System.out.println("This command shows all created classes with their index.");
        System.out.println("It asks you to enter the index of the class you want to see. It will then show you all the elements associated with that class, such as type, attributes, and relationships.");
    }
    public static String[] classHelp(){
        System.out.println("2.) Class Options.");
        System.out.println("These options are related to classes. They will give you the option to create, delete, and rename classes.");
        System.out.println("The options are as listed below:");
        System.out.println("");
        System.out.println("1.) Create a class.");
        System.out.println("This option will allow you to create a class. It will prompt you to name the class and choose the type from a list.");
        System.out.println("");
        System.out.println("2.) Delete a class");
        System.out.println("This command deletes a previously created class. It will ask you for the index of the class you want to delete and remove it from created classes.");
        System.out.println("");
        System.out.println("3.) Rename a class");
        System.out.println("This command will ask for an index of the class you would like to rename. It will then ask you for the new name and replace the old name associated with the class.");
    }
    public static String[] attributeHelp(){
        System.out.println("3.) Attribute Options.");
        System.out.println("These options are related to class attributes. They will allow you to create, delete, and rename attributes associated with classes.");
        System.out.println("The options are as listed below:");
        System.out.println("");
        System.out.println("1.) Add an attribute.");
        System.out.println("This command asks for the name of the class you want to add the attribute to. It will then ask about the attribute you want to add to the class.");
        System.out.println("");
        System.out.println("2.) Delete an attribute.");
        System.out.println("This command asks for the name of the class you want to remove the attribute from. It will then ask about the attribute you want to remove.");
        System.out.println("It will delete the attribute you selected from that class.");
        System.out.println("");
        System.out.println("3.) Rename an attribute.");
        System.out.println("This command asks for the name of the class you want to rename the attribute from. It will then ask about the attribute you want to rename.");
        System.out.println("It will then prompt you for the new name and rename the attribute with the value you enter." + "\n");
    }
    public static String[] relationshipHelp(){
        System.out.println("4.) Relationship Options.");
        System.out.println("These options are related to relationships between classes.");
        System.out.println("The options are as listed below:");
        System.out.println("");
        System.out.println("1.) Add a relationship.");
        System.out.println("This command will add a relationship between two specified classes. It will ask for the indexes/names of the two classes.");
        System.out.println("It will then ask you about the type of relationship between the two classes. That type of relationship will be created between the two classes." + "\n");
        System.out.println("");
        System.out.println("2.) Delete a relationship.");
        System.out.println("This command deletes a previously created relationship. It will display a list of created relationships and then ask which of those you would like to delete.");
        System.out.println("That relationship will then be removed from the two classes that it was associated with." + "\n");

    }
    public static String[] saveLoadHelp(){
        System.out.println("5.) Save/Load.");
        System.out.println("These commands will aid in saving current projects and loading previous ones.");
        System.out.println("The options are as listed below:");
        System.out.println("");
        System.out.println("1.) Save");
        System.out.println("This command will save the current classes, attributes and relationships to a file that can be loaded in a later session.");
        System.out.println("");
        System.out.println("2.) Load");
        System.out.println("This command will load a previously saved file with information about classes, relationships, attributes, and details.");
        System.out.println("It will bring in previously created classes and class information for you to continue to edit.");
    }
    public static String[] help() {
        System.out.println("This program is a UML Editor. It lets you create, manipulate, connect, and edit classes for your program.");
         System.out.println("This help menu will walk you through each command step by step.");

        System.out.println("Press enter to continue.");
        kb.nextLine();

        listHelp();

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();
        
        classHelp();

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();

        attributeHelp();

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();

        relationshipHelp();

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();

        saveLoadHelp();

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();


        System.out.println("6.) Help.");
        System.out.println("This command displays this help menu with descriptions about each command. You can bring this list up anytime the menu resets." + "\n");
        
        System.out.println("7.) Exit.");
        System.out.println("This command closes the program." + "\n");

        System.out.println("Thank you for reading and happy editing!");
        

    }


    // confirm?

}
