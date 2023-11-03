package src.main.java;

//The "view" when the program is in command line interface (CLI) mode

import java.util.Scanner;

public class CLI {
    //Scanner to read user input
    private static Scanner kb = new Scanner(System.in);

    public static void menu() {
        boolean cont = true;
        while (cont) {
            int input2;
            CLI.printStringList(Controller.printMenu());
            System.out.print("Choice:");
            // get user input of 1-15
            // call io method below
            // io method calls actual method in other classes
            int input = Integer.parseInt(kb.nextLine());
            switch (input) {
                case 1:
                    if(Controller.getCreatedClassesSize() == 0){
                        System.out.println("Nothing to display! Please make a class first");
                    } else {
                        printStringList(Controller.printMenu());
                        input2 = Integer.parseInt(kb.nextLine());
                        if (input2 == 1) {
                            CLI.listClasses();
                        } else if (input2 == 2) {
                            printStringList(Controller.listAllClassDetails());
                        } else if (input2 == 3) {
                            CLI.listClassDetails();
                        } else if (input2 == 4) {
                            for(String[] list : Controller.listRelationships()){
                                printStringList(list);
                            }

                        } else if (input2 == 5) {
                            CLI.printStringList(Controller.listHelp());
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
                        CLI.addClass();
                    } else if (input2 == 2) {
                        CLI.deleteClass();
                    } else if (input2 == 3) {
                        CLI.renameClass();
                    } else if (input2 == 4) {
                        CLI.printStringList(Controller.classHelp());
                    } else if (input2 == 5) {
                        return;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                    break;
                case 3:
                    //if (createdClasses.isEmpty()) {
                    if(Controller.getCreatedClassesSize() == 0){
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
                            Controller.addAttribute();
                        } else if (input2 == 2) {
                            Controller.deleteAttribute();
                        } else if (input2 == 3) {
                            Controller.renameAttribute();
                        } else if (input2 == 4) {
                            CLI.printStringList(Controller.attributeHelp());
                        } else if (input2 == 5) {
                            return;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                    }
                    break;
                case 4:
                    //if (createdClasses.size() < 2) {
                    if(Controller.getCreatedClassesSize() < 2){
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
                            CLI.addRelationship();
                        } else if (input2 == 2) {
                            CLI.deleteRelationship();
                        } else if (input2 == 3) {
                            CLI.printStringList(Controller.relationshipHelp());
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
                        printStringList(Controller.saveLoadHelp());
                    } else if (input2 == 4) {
                        return;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                    break;
                case 6:
                    Controller.help();
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

    // This method will take class name input from the user in CLI mode and send it
    // to the controller
    // The controller will return true or false based on whether or not the class
    // was created
    public static void addClass() {
        System.out.println("What would you like to name your class?");
        System.out.print("Class Name:");
        String name = kb.nextLine();
        System.out.println("What is the class's type?");

        printStringList(ClassBox.listClassTypes());

        int type = Integer.parseInt(kb.nextLine());
        //Test to see if adding was sucessful
        if (Controller.addClass(name, type)) {
            System.out.println("Class created!");
        } else {
            System.out.println("Class already exists or bad inputs were provided. Please try again.");
        }
    }

    // This method will take class inex input from the user in CLI mode and send it
    // to the controller
    // The controller will return true or false based on whether or not the class
    // was deleted

    public static void deleteClass() {
        System.out.println("What index do you want to remove?");
        Controller.listClasses();
        System.out.print("Class Index:");
        String input = kb.nextLine();

        //Test to see if deleting was sucessful
        if (Controller.deleteClass(input)) {
            System.out.println("Class deleted!");
        } else {
            System.out.println("Bad input was provided. Failed to delete class.");
        }
    }

    // This method will take the index of the class as input from the user in CLI
    // mode and send it to the controller
    // The controller will return true or false based on whether or not the class
    // was renamed
    public static void renameClass() {
        System.out.println("What is the name of the class you want to rename?");
        CLI.listClasses();
        System.out.print("Class Name:");
        String oldName = kb.nextLine();

        System.out.println("What would you like to rename your class?");
        System.out.print("New Class Name:");
        String name = kb.nextLine();

        //Test to see if rename was sucessful
        if (Controller.renameClass(oldName, name)) {
            System.out.println("Class renamed!");
        } else {
            System.out.println("Bad input was provided. Failed to rename class.");
        }
    }

    // This method will take both indexs for both classes as input from the user in
    // CLI mode and send it to the controller
    // The controller will return true or false based on whether or not the
    // relationship was created
    public static void addRelationship() {

        System.out.println("What is the name of the first class you want to have a relationship? (The lower/to class, e.g this implements the other class)");
        CLI.printStringList(Controller.listClasses());
        System.out.print("Class 1 name:");
        String name1 = kb.nextLine();

        System.out.println("What is the name of the second class you want to have a relationship?(The higher/from class, e.g the other class implements this)");
        CLI.printStringList(Controller.listClasses());
        System.out.print("Class 2 name:");
        String name2 = kb.nextLine();

        Relationship.printRelationshipTypes();
        System.out.println("Please select an option for the relationship type by number");
        System.out.print("Type #:");
        int type = Integer.parseInt(kb.nextLine());

        //Test to see if adding relationship was sucessful
        //if (Controller.addRelationship(index1, index2, type)) {
        if(Controller.addRelationship(name1, name2, type)){
            System.out.println("Relationship created!");
        } else {
            System.out.println("Bad inputs for relationship, creation cancelled");
        }
    }

    public static void deleteRelationship() {
        Controller.listClasses();
        System.out.println("What is the index of the first class of this relationship?");
        System.out.print("Class Index:");
        int index1 = Integer.parseInt(kb.nextLine());
        System.out.println("What is the index of the second class?");
        System.out.print("Class Index:");
        int index2 = Integer.parseInt(kb.nextLine());
        System.out.println("Are you sure you want to delete this relationship? Please write yes or no.");
        System.out.print("yes/no:");
        String answer = kb.nextLine();

        //Test to see if deleting relationship was sucessful
        //if (Controller.deleteRelationship(index1, index2, answer)) {
        if(false){
            System.out.println("Relationship created!");
        } else {
            System.out.println("Bad inputs for relationship, deletion cancelled.");
        }
    }




    public static void listClassDetails(){
        System.out.println("What is the name of the class you want see?");
        System.out.println("Class name: ");
        String input = kb.nextLine();
        printStringList(Controller.listClassDetails(input));
    }

    //This method takes in an arraylist of strings and is able to print it out line after line.
    //This method is used for many other methods that return String arrays full of data
    public static void printStringList(final String[] list){
        for(String s : list){
            System.out.println(s);
        }
    }
    public static void listClasses(){
        int counter = 1;
        String[] list = Controller.listClasses();
        for(String s : list){
            System.out.println(counter + ". " + s);
            counter++;
        }
    }



}
