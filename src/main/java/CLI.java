package src.main.java;

/**
 * This class is for when the view selected is for CLI (Command Line Interface). It will handle displaying
 * the information retrieved for the CLI.
 */

import java.util.LinkedList;
import java.util.Scanner;

public class CLI {
    //Scanner to read user input
    private static final Scanner kb = new Scanner(System.in);

    /**
     * The menu method displays the first few options for the user.
     * It promts the user to select a number corresponding to a command.
     * Some of the menu option open submenus with more specific options.
     * The while loop checks if a boolean variable 'cont' is true. If it is,
     * it continues the loop. The only time cont will return false is if the user selects
     * the exit option from the menu.
     *
     */
    public static void menu() {
        boolean cont = true;
        while (cont) {
            int input2;
            int input3;
            CLI.printStringList(Controller.printMenu());
            //Retrieves the user input in 'input'
            // get user input of 1-15
            // call io method below
            // io method calls actual method in other classes
            String[] input = CLI.readStringSplit("Choice: ");
            //int firstSpaceIndex = findSpace(0, input);
            //String command = input.substring(0, firstSpaceIndex);

            if(input.length == 0){
                System.out.println("Please enter a command");
                break;
            }
            switch (input[0]) {
                //Switch statement controls different options the user could select
                case "add":

                    switch (input[1]) {
                        case "class": {
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            Controller.STATUS_CODES status = Controller.addClass(input[2], Integer.parseInt(input[3]));
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Class " + input[2] + " " + status.toString());
                            }else{
                                System.out.println("Class " + input[2] + " added!");
                            }
                            //CLI.printArrayOfStringList(Controller.listAllClassDetails(input[2]));
                            break;
                        }
                        case "method": {
                            if(input.length != 6){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            System.out.println("found method");
                            LinkedList<String> params = new LinkedList<String>();


                            params.add("stub");
                            Controller.STATUS_CODES status = Controller.addMethod(input[2], input[3], Integer.parseInt(input[4]), input[5], params);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Method " + input[3] + " " + status.toString());
                            }else{
                                System.out.println("Method " + input[3] + " added to class " + input[2] + "!");
                            }
                            break;
                        }
                        case "field": {
                            if(input.length != 6){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            Controller.STATUS_CODES status = Controller.addField(input[2], input[3], Integer.parseInt(input[4]), input[5]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Field " + input[3] + " " + status.toString());
                            }else{
                                System.out.println("Field " + input[3] + " added to class " + input[2] + "!");
                            }
                            CLI.printArrayOfStringList(Controller.listAllClassDetails(input[2]));
                            System.out.println("Found field");
                            break;
                        }
                        case "relationship": {
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            System.out.println("Found relationship");
                            Controller.STATUS_CODES status = Controller.addRelationship(input[2], input[3], input[4]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Relationship " + status.toString());
                            }else{
                                System.out.println("Relationship between " + input[2] + " and " + input[3] + " created!");
                            }
                            CLI.printArrayOfStringList(Controller.listRelationships());
                            break;
                        }
                        default: {
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                    break;
                case "delete":
                    System.out.println("Found delete");
                    switch(input[1]){
                        case "class":{
                            if(input.length != 3){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            Controller.STATUS_CODES status = Controller.deleteClass(input[2]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Class " + status.toString());
                            }else{
                                System.out.println("Class " + input[2] + " deleted!");
                            }
                            break;
                        }
                        case "method":{
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            System.out.println("found method");
                            Controller.STATUS_CODES status = Controller.deleteMethod(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Method " + status.toString());
                            }else{
                                System.out.println("Method " + input[3] + " removed from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "field":{
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            System.out.println("found field");
                            Controller.STATUS_CODES status = Controller.deleteField(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Field " + status.toString());
                            }else{
                                System.out.println("Field " + input[3] + " removed from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "relationship": {
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }

                            System.out.println("found relationship");
                            Controller.STATUS_CODES status = Controller.deleteRelationship(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Relationship " + status.toString());
                            }else{
                                System.out.println("Relationship between " + input[2] + " and " + input[3] + " deleted!");
                            }
                            break;
                        }
                        default:{
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                    break;
                case "list":
                    System.out.println("Found list!");
                    printStringListNumbered(Controller.listClasses());
                    break;
                case "help":
                    System.out.println("Found help!");
                    Controller.help();
                    break;
                case "rename":
                    System.out.println("Found rename");
                    switch(input[1]){
                        case "class":{
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            System.out.println("Found rename");
                            Controller.STATUS_CODES status = Controller.renameClass(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Class " + status.toString());
                            }else{
                                System.out.println("Class " + input[2] +  "renamed to " + input[3]);
                            }
                            break;
                        }
                        case "method":{
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            System.out.println("Found method");
                            Controller.STATUS_CODES status = Controller.renameMethod(input[2], input[3], input[4]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Method " + status.toString());
                            }else{
                                System.out.println("Method " + input[3] + " renamed to " + input [4] +"from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "field":{
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            System.out.println("found field");
                            Controller.STATUS_CODES status = Controller.renameField(input[2], input[3], input[4]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Field " + status.toString());
                            }else{
                                System.out.println("Field " + input[3] + " renamed to " + input [4] +"from class " + input[2] + "!");
                            }
                            break;
                        }
                        default:{
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                case "save":
                    System.out.println("Found save!");
                    Controller.save();
                    break;
                case "load":
                    System.out.println("Found load!");
                    Controller.load();
                    break;
                case "gui":
                    System.out.println("Found GUI!");
                    Main.gview = true;
                    GUI.startGUIMenu();
                    break;
                default:
                    break;
            }

                    /**
                     * //If the user selects 1, it will display the listing options
                    //These are all display commands
                    if (Controller.getCreatedClassesSize() == 0) {
                        System.out.println("Nothing to display! Please make a class first");
                    } else {
                        printStringList(Controller.subMenu1());
                        //'input2' takes in user input for the submenu
                        input2 = CLI.readInt("Choice: ");
                        if (input2 == 1) {
                            CLI.printStringListNumbered(Controller.listClasses());
                        } else if (input2 == 2) {
                            CLI.listClassesDetailed();
                        } else if (input2 == 3) {
                            CLI.listAllClassDetails();
                        } else if (input2 == 4) {
                            //Rerieves relationships for all class objects and displays them
                            for (String[] list : Controller.listRelationships()) {
                                printStringList(list);
                            }
                        } else if (input2 == 5) {
                            CLI.printStringList(Controller.listHelp());
                        } else if (input2 == 6) {
                            //This is the back option, it just returns to the original menu
                            break;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                    }
                    break;
                case 2:
                    //If the user selects 2, it takes them to the class manipulation submenu
                    //This is where the user can add, delete, and rename classes
                    printStringList(Controller.subMenu2());
                    //'input2' reads user input for the sub menu
                    input2 = CLI.readInt("Choice: ");
                    if (input2 == 1) {
                        CLI.addClass();
                    } else if (input2 == 2) {
                        CLI.deleteClass();
                    } else if (input2 == 3) {
                        CLI.renameClass();
                    } else if (input2 == 4) {
                        CLI.printStringList(Controller.classHelp());
                    } else if (input2 == 5) {
                        break;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                    break;
                case 3:
                    //If the user selects 3, it takes them to the attribute submenu
                    //Here, the user can add, delete, or rename fields and methods
                    if (Controller.getCreatedClassesSize() == 0) {
                        System.out.println("Please create a class first");
                    } else {
                        printStringList(Controller.subMenu3());
                        //'input2' reads the user input for the submenu
                        input2 = CLI.readInt("Choice: ");
                        if (input2 == 1) {
                            CLI.addAttribute();
                        } else if (input2 == 2) {
                            CLI.deleteAttribute();
                        } else if (input2 == 3) {
                            CLI.renameAttribute();
                        } else if (input2 == 4) {
                            printStringList(Controller.subMenu6());
                            input3 = CLI.readInt("Choice ");
                            if (input3 == 1) {
                                CLI.addParam();
                            } else if (input3 == 2) {
                                CLI.deleteParam();
                            } else if (input3 == 3) {
                                CLI.renameParam();
                            } else if (input3 == 4) {
                                printStringList(Controller.editParamHelp());
                            } else if (input3 == 5) {
                                break;
                            }
                        } else if (input2 == 5) {
                            CLI.printStringList(Controller.attributeHelp());
                        } else if (input2 == 6) {
                            return;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                    }
                    break;
                case 4:
                    //If the user selects 4, it takes them to the relationships submenu
                    //This is where the user can create, delete, and rename relationships between classes

                    if (Controller.getCreatedClassesSize() < 2) {
                        System.out.println("Please create 2 classes first");
                    } else {
                        printStringList(Controller.subMenu4());
                        //'input2' reads the user input for the submenu
                        input2 = CLI.readInt("Choice: ");
                        if (input2 == 1) {
                            CLI.addRelationship();
                        } else if (input2 == 2) {
                            CLI.deleteRelationship();
                        } else if (input2 == 3) {
                            CLI.printStringList(Controller.relationshipHelp());
                        } else if (input2 == 4) {
                            break;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                    }
                    break;
                case 5:
                    //If the user selects 5, it takes them to the save / load menu
                    //It allows them to choose if they want to save their current progress or load previously saved
                    printStringList(Controller.subMenu5());
                    //'input2' takes in user input for the small menu
                    input2 = CLI.readInt("Choice: ");
                    if (input2 == 1) {
                        CLI.save();
                    } else if (input2 == 2) {
                        CLI.load();
                    } else if (input2 == 3) {
                        printStringList(Controller.saveLoadHelp());
                    } else if (input2 == 4) {
                        break;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                    break;
                case 6:
                    //If the user selects 6, it opens the help menu which can help you understand how the program runs
                    printArrayOfStringList(Controller.help());
                    break;
                case 7:
                    //If the user selects 7, it will open and start the GUI menu with the same information from CLI
                    Main.gview = true;
                    GUI.startGUIMenu();
                    break;
                case 8:
                    //If the user selects 8, it will pull up the exit menu, confirm exit with the user, then proceed as directed
                    System.out.println("Are you sure you want to exit? Type \"yes\" to confirm");
                    System.out.print("yes/no: ");
                    if (kb.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("Would you like to save first?");
                        System.out.print("yes/no: ");
                        if (kb.nextLine().equalsIgnoreCase("yes")) {
                            CLI.save();
                        }
                        System.out.println("Program Closed! Bye!");
                        cont = false;
                        Main.cview = false;
                    }
                    break;
                default:
                    //If the user selects an invalid option, it will let them know and bring them back to the main menu
                    System.out.println("That is not a valid input. Please try again");
                    break;*/
            }
            if(Main.gview){
                GUI.displayGUI();
            }
        }

    /**
     *  This method takes in an arraylist of strings and is able to print it out line after line.
     *  This method is used for many other methods that return String arrays full of data
     */

    public static void printStringList(final String[] list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void printArrayOfStringList(final String[][] list) {
        for (String[] s : list) {
            for (String string : s)
                System.out.println(string);
        }
    }

    public static void printStringListNumbered(final String[] list){
        int counter = 1;
        for (String s : list) {
            System.out.println(counter + ". " + s);
            counter++;
        }
    }

    public static void listClassesDetailed() {
        String[] classes = Controller.listClasses();
        for (String aClass : classes)
            printArrayOfStringList(Controller.listAllClassDetails(aClass));
    }

    public static void save() {
        if (Controller.save()) {
            System.out.println("Your progress has been saved!");
        } else
            System.out.println("Nothing to save");
    }

    public static void load() {
        if (Controller.load())
            System.out.println("Your previous save has been loaded!");
        else
            System.out.println("There is no save to load.");
    }

    public static int readInt(final String msg) {
        System.out.print(msg);
        while (true) {
            try {
                return Integer.parseInt(kb.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input, try again");
                System.out.print(msg);
            }
        }
    }

    public static String readString(final String msg) {
        System.out.print(msg);
        return kb.nextLine().toLowerCase();
    }
    public static String[] readStringSplit(final String msg) {
        System.out.print(msg);
        return kb.nextLine().toLowerCase().split(" ");
    }
    private static int findSpace(int startIndex, String command) {
        int result = -1;

        for (int i = startIndex; i < command.length() - startIndex; i++) {
            if (Character.isWhitespace(command.charAt(i))) {
                result = i;
            }

        }
        return result;
    }

}
