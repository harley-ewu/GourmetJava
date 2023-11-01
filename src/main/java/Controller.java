package src.main.java;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Controller {


    // Display all program options, choose from list, call other method based on
    // choice
    private Controller() {
        // createdClasses = new ArrayList<>();
        // kb = new Scanner(System.in);
    }

    // recall menu at end if not
    public static void menu() {
        boolean cont = true;
        while (cont) {
            printMenu();
            int input2;
            System.out.print("Choice:");
            // get user input of 1-15
            // call io method below
            // io method calls actual method in other classes
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
                        Controller.addClass("test","test2");
                    } else if (input2 == 2) {
                        Controller.deleteClass("name");
                    } else if (input2 == 3) {
                        //renameClass();
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
                            //addRelationship();
                        } else if (input2 == 2) {
                            //deleteRelationship();
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
                        ModelDiagram.save();
                    } else if (input2 == 2) {
                        ModelDiagram.load();
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

    // Creates a new Classbox object and adds to to the arraylist createdClasses
    // Rachael
    // Allows the user to name their class, then adds it to the list of created
    // classes
    public static boolean addClass(final String name, final String type) {
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

    public static boolean addRelationship(final String cb1, final String cb2, final String type) {
        return ModelDiagram.addRelationship(cb1, cb2, type);
    }

    // Deletes a relationship between two classes while prompting the user to verify
    // they wish to delete along the way
    public static boolean deleteRelationship(final String cb1, final String cb2) {
        return ModelDiagram.deleteRelationship(cb1, cb2);

    } // End of deleteRelationship

    // Adds an attribute to a given class
    // NEW: ask for field or method, do io based on each, pass fields to classbox
    // add
    public static void addAttribute() {
        /*
        listClasses();
        System.out.println("What is the index of the class you would like to add an attribute to?");
        System.out.print("Class Index:");
        int ind = Integer.parseInt(kb.nextLine());
        if (ind < 1 || ind > createdClasses.size()) {
            System.out.println("That class does not exist.");
        } else {
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
            System.out.println(
                    "Enter the type (the return type if it is a method, the variable type if it is a variable");
            System.out.print("Type:");
            String type = kb.nextLine();
            System.out.println(
                    "Enter the parameters, seperated by commas, if this is a method (Leave blank for a variable)");
            System.out.print("Parameters:");
            String params = kb.nextLine();
            LinkedList<String> paramsss = null;
            if (!params.isEmpty()) {
                String[] paramss = params.split(",");
                paramsss = new LinkedList<String>(Arrays.asList(paramss));
            }
            try {
                c.addAttribute(attributeName, view, modll, type, paramsss);
                System.out.println(attributeName + " has been added to " + c.getName());
            } catch (Exception e) {
                System.out.println("Bad inputs, no attribute created");
            }
        }

         */
    }// end addAttribute

    // Finds an attribute, checks with the user to verify intent, then deletes the
    // attribute
    // NEW - search both class lists on name, if in method twice, ask for params
    public static void deleteAttribute() {
        /*
        listClasses();
        System.out.println("What is the index of the class you'd like to remove an attribute from?");
        System.out.print("Class Index:");
        int ind = Integer.parseInt(kb.nextLine());
        ClassBox c = createdClasses.get(ind - 1);
        LinkedList<Attribute> attList = c.getAttributes();
        if (!(attList.isEmpty())) {
            System.out.println("Which attribute would you like to delete?");
            // find attributes using a loop
            for (int i = 0; i < attList.size(); i++) {
                System.out.println(i + 1 + ".)" + attList.get(i).toString());
            }
            System.out.print("Attribute Index:");
            int index = Integer.parseInt(kb.nextLine());

            System.out.println("Are you sure you want to delete " + attList.get(index - 1).getName()
                    + "? Please enter yes or no.");
            System.out.print("yes/no:");
            String answer = kb.nextLine().toLowerCase();
            // stick the part below in a loop so if an incorrect input is entered, it'll
            // re-prompt
            while (!(answer.equals("yes") || answer.equals("no"))) {
                System.out.println("That is not a valid input. Please enter yes or no.");
                System.out.print("yes/no:");
                answer = kb.nextLine().toLowerCase();
            } // end of while loop checking for valid input
            if (answer.equals("yes")) {
                c.deleteAttribute(attList.get(index - 1));
            } else if (answer.equals("no")) {
                System.out.println("Canceled");
            }

        } else {
            System.out.println("No attributes found for class " + c.getName());
        }

         */
    }

    public static void renameAttribute() {
        /*

        if (createdClasses.isEmpty()) {
            System.out.println("Nothing to rename!");
        }

        else {

            listClasses();
            System.out.println("What is the index of the class you'd like to rename an attribute from?");
            System.out.print("Class Index:");
            int ind = Integer.parseInt(kb.nextLine());
            ClassBox c = createdClasses.get(ind - 1);
            LinkedList<Attribute> attList = c.getAttributes();
            if (!(attList.isEmpty())) {
                System.out.println("Which attribute would you like to rename?");
                // find attributes using a loop
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
                } catch (Exception e) {
                    System.out.println("Bad new name, no change done");
                }
            } else {
                System.out.println("No attributes found for class " + c.getName());
            }
        }


         */

        // Set relationships to null to avoid StackOverflow, then write ClassBoxes to
        // file
        for (int i = 0; i < createdClasses.size(); i++) {
            // Delete all relationships to avoid StackOverflow
            createdClasses.get(i).getRelationships().clear();
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

   

    public static String[] listClasses() {
        return ModelDiagram.listClasses();
    }

    // Returns a list of Strings, each String holding the detailed info for a
    // CreatedClass
    public static String[] listDetailedClasses() {
        return ModelDiagram.listDetailedClasses();
    }

    // What are attributes?
    public static String[] listClassAttributes(final String cb) {
        return ModelDiagram.listClassAttributes(cb);
    }

    // Returns an array of String arrays
    // We have a list of createdClass objects, and each createdClass object has a
    // list of relationships
    public static String[][] listRelationships() {
        return ModelDiagram.listRelationships();
    }

    // Allows the user to choose what Classbox item they want to see in detail
    // Rachael
    // Takes input from user on what index from the list they want to see then calls
    // a toString for that object
    public static String[] listClass(final String name) {
        return ModelDiagram.listClass(name);
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


}
