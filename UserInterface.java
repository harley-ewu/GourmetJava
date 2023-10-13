import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private ArrayList<ClassBox> createdClasses;
    private Scanner kb;

    // Display all program options, choose from list, call other method based on
    // choice
    public UserInterface() {
        createdClasses = new ArrayList<>();
        kb = new Scanner(System.in);
    }

    // recall menu at end if not
    public void menu(String input) {

        // get user input of 1-15
        // call io method below
        // io method calls actual method in other classes

        if (input.equals("1")) {
            listClasses();
        } else if (input.equals("2")) {
            listClass();
        } else if (input.equals("3")) {
            addClass();
        } else if (input.equals("4")) {
            deleteClass();
        } else if (input.equals("5")) {
            renameClass();
        } else if (input.equals("6")) {
            addAttribute();
        } else if (input.equals("7")) {
            deleteAttribute();
        } else if (input.equals("8")) {
            renameAttribute();
        } else if (input.equals("9")) {
            listRelationships();
        } else if (input.equals("10")) {
            addRelationship();
        } else if (input.equals("11")) {
            deleteRelationship();
        } else if (input.equals("12")) {
            save();
        } else if (input.equals("13")) {
            load();
        } else if (input.equals("14")) {
            help();
        } else if (input.equals("15")) {
            return;
        } else {
            System.out.println("That is not a valid input. Please try again");
        }
    }

    // PrintMenu will display the menu options and prompt the user to choose a
    // corresponding number on the menu
    public void printMenu() {
        System.out.println("\nPlease choose a number from the following options below");
        System.out.println("1.) List classes");
        System.out.println("2.) List class details");
        System.out.println("3.) Add a class");
        System.out.println("4.) Delete a class");
        System.out.println("5.) Rename a class");
        System.out.println("6.) Add an attribute");
        System.out.println("7.) Delete an attribute");
        System.out.println("8.) Rename an attribute");
        System.out.println("9.) List relationships");
        System.out.println("10.) Add a relationship");
        System.out.println("11.) Delete a relationship");
        System.out.println("12.) Save");
        System.out.println("13.) Load");
        System.out.println("14.) Help");
        System.out.println("15.) Exit");
    }

    // Creates a new Classbox object and adds to to the arraylist createdClasses
    // Rachael
    // Allows the user to name their class, then adds it to the list of created
    // classes
    public void addClass() {
        System.out.println("What would you like to name your class?");
        String name = kb.nextLine();
        System.out.println("What is the class's type?");

        ClassBox.printClassTypes();

        int result = 0;
        String type = kb.nextLine();

        try {
            result = Integer.parseInt(type);
        } catch (Exception e) {
            System.out.print("Input is not valid. Please try again.");
            return;
        }

        ClassBox newClass = new ClassBox(name, result);
        createdClasses.add(newClass);
        System.out.println("Class created!");

    }

    // Removes class from createdClasses
    // Rachael
    // Takes in the index of the item user wants removed from the list and removes
    // it
    public void deleteClass() {
        if (createdClasses.isEmpty()) {
            System.out.println("Nothing to delete!");
        } else {
            System.out.println("What index do you want to remove?");
            listClasses();
            int input = Integer.parseInt(kb.nextLine());
            if (input > 0) {
                input -= 1;
                createdClasses.remove(input);
                System.out.println("Class deleted");
            } else if (input <= 0) {
                System.out.println("Invalid input. Try again");
            }

        }

    }

    // Renames a classbox item that has already been created
    // Rachael
    // Takes in the index of the item they want renamed, then asks them to type in a
    // new name
    public void renameClass() {
        if (createdClasses.isEmpty()) {
            System.out.println("Nothing to rename!");
        } else {
            System.out.println("What index do you want to rename?");
            listClasses();
            int input = Integer.parseInt(kb.nextLine());
            if (input > 0) {
                input -= 1;
                System.out.println("What would you like to rename your class?");
                String name = kb.nextLine();
                createdClasses.get(input).renameClass(name);
                System.out.println("Class renamed!");
                listClasses();
            } else if (input <= 0) {
                System.out.println("Invalid input. Try again");
            }

        }
    }

    public void addRelationship() {
        System.out.println("What is the index of the first class you want to have a relationship?");
        int index1 = Integer.parseInt(kb.nextLine());
        System.out.println("What is the index of the second class you want to have a relationship?");
        int index2 = Integer.parseInt(kb.nextLine());
        Relationship.printRelationshipTypes();
        System.out.println("Please select an option for the relationship type by number");
        int num = Integer.parseInt(kb.nextLine());
        if (index1 < 1 || index2 < 1 || index1 > createdClasses.size() || index2 > createdClasses.size()) {
            System.out.println("Thats not a vaild option. Please try again");

        } else {
            System.out.println("Relationship created!");
            ClassBox.addRelationship(createdClasses.get(index1 - 1), createdClasses.get(index2 - 1), num);
        }
    }


    // Deletes a relationship between two classes while prompting the user to verify they wish to delete along the way
    public void deleteRelationship() {
        // Add an else for if one of the names isn't found
        ClassBox c1 = null, c2 = null;
        System.out.println("What is the name of the first class of this relationship?");
        String className = kb.nextLine();
        System.out.println("What is the name of the second");
        String className2 = kb.nextLine();
        System.out.println("Are you sure you want to delete this relationship? Please write yes or no.");
        String answer = kb.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            for (ClassBox createdClass : createdClasses) {
                if (createdClass.getName().equals(className)) {
                    c1 = createdClass;
                }
                if (createdClass.getName().equals(className2)) {
                    c2 = createdClass;
                }
            }
            if (c1 == null || c2 == null) {
                System.out.println("Error, class(es) not found");
            } else {
                c1.deleteRelationship(c2);
            }
        } else {
            System.out.println("No relationship deleted");
        }

    } // End of deleteRelationship

    //Adds an attribute to a given class
    public void addAttribute() {

        System.out.println("What is the name of the class you would like to add an attribute to?");
        String className = kb.nextLine().toLowerCase();
        ClassBox temp = null;
        for (int i = 0; i < createdClasses.size(); i++) {
            if (createdClasses.get(i).getName().toLowerCase().equals(className)) {
                temp = createdClasses.get(i);
                System.out.println("What would you like to call your attribute?");
                String attributeName = kb.nextLine();
                createdClasses.get(i).addAttribute(attributeName);
                System.out.println(attributeName + " has been added to " + className);
            }
        }
        if(temp == null){
            System.out.println("That name does not exist.");
        }
    }// end addAttribute


    // Finds an attribute, checks with the user to verify intent, then deletes the attribute
    public void deleteAttribute() {
        System.out.println("What is the name of the class you'd like to remove an attribute from?");
        String className = kb.nextLine().toLowerCase();
        for (int i = 0; i < createdClasses.size(); i++) {
            if (createdClasses.get(i).getName().toLowerCase().equals(className)) {
                // check if attributes is empty, if not do below
                System.out.println("Which attribute would you like to delete?");
                // list attributes
                for(int j = 0; j < createdClasses.get(i).getAttributes().size(); i++){
                    int counter = j + 1;
                    System.out.println(counter + ".) " + createdClasses.get(i).getAttributes().get(j));
                }
                String attribute = kb.nextLine();
                // find attribute using loop
                System.out.println("Are you sure you want to delete " + attribute + "? Please enter yes or no.");
                String answer = kb.nextLine().toLowerCase();
                //stick the part below in a loop so if an incorrect input is entered, it'll re-prompt
                if (answer.equals("yes")) {
                    createdClasses.get(i).deleteAttribute(attribute);
                } else if (answer.equals("no")) {
                    System.out.println("Canceled");
                } else {
                    System.out.println("That is not a valid input");
                }
            }
        }
    }

    public void renameAttribute() {

    }

    // export createdClasses
    // confirm?
    public void save() {

    }

    // import/set createdClasses
    // confirm?
    public void load() {

    }

    public void listClasses() {
        System.out.println("Current Class list");
        for (int i = 0; i < createdClasses.size(); i++) {
            System.out.println(i + 1 + " . " + createdClasses.get(i).getName() + " ");
        }
    }

    public void listRelationships() {

    }

    // Allows the user to choose what Classbox item they want to see in detail
    // Rachael
    // Takes input from user on what index from the list they want to see then calls
    // a toString for that object
    public void listClass() {
        System.out.println("What index do you want to see?");
        listClasses();
        int input = Integer.parseInt(kb.nextLine());
        if (input > 0) {
            System.out.println(createdClasses.get(input - 1).toString());
        } else if (input <= 0) {
            System.out.println("Invalid input. Try again");
        }

    }

    public void help() {
        System.out.println("This program is a UML Editor. It lets you create, manipulate, connect, and edit classes for your program.");
         System.out.println("This help menu will walk you through each command step by step.");
        
        System.out.println("Press enter to continue.");
        kb.nextLine();
        
        System.out.println("1.) List Options.");
        System.out.println("These options are listing options. They will lead you to the options where you can list classes, list class details, and list relationships.");
        System.out.println("The options are as listed below:");
        System.out.println("");
        System.out.println("1.) List Classes");
        System.out.println("This command will display all created classes with their name.");
        System.out.println("");
        System.out.println("2.) List relationships.");
        System.out.println("This command will display relationships between classes as well as the type of relationships." );
        System.out.println("");
        System.out.println("3.) List class details.");
        System.out.println("This command shows all created classes with their index."); 
        System.out.println("It asks you to enter the index of the class you want to see. It will then show you all the elements associated with that class, such as type, attributes, and relationships.");

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();
        
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

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();

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

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();

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

        System.out.println("");
        System.out.println("Press enter to see more options.");
        kb.nextLine();

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
