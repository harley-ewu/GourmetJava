package src.main.java;

//The "view" when the program is in command line interface (CLI) mode

import java.util.LinkedList;
import java.util.Scanner;

public class CLI {
    //Scanner to read user input
    private static final Scanner kb = new Scanner(System.in);

    public static void menu() {
        boolean cont = true;
        while (cont) {
            int input2;
            int input3;
            CLI.printStringList(Controller.printMenu());
            // get user input of 1-15
            // call io method below
            // io method calls actual method in other classes
            int input = CLI.readInt("Choice: ");
            switch (input) {
                case 1:
                    if (Controller.getCreatedClassesSize() == 0) {
                        System.out.println("Nothing to display! Please make a class first");
                    } else {
                        printStringList(Controller.subMenu1());
                        input2 = CLI.readInt("Choice: ");
                        if (input2 == 1) {
                            CLI.listClasses();
                        } else if (input2 == 2) {
                            CLI.listClassesDetailed();
                        } else if (input2 == 3) {
                            CLI.listAllClassDetails();
                        } else if (input2 == 4) {
                            for (String[] list : Controller.listRelationships()) {
                                printStringList(list);
                            }
                        } else if (input2 == 5) {
                            CLI.printStringList(Controller.listHelp());
                        } else if (input2 == 6) {
                            break;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                    }
                    break;
                case 2:
                    printStringList(Controller.subMenu2());
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
                    //GUI.displayGUI();
                    break;
                case 3:

                    if (Controller.getCreatedClassesSize() == 0) {
                        System.out.println("Please create a class first");
                    } else {
                        printStringList(Controller.subMenu3());
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
                        //GUI.displayGUI();
                    }
                    break;
                case 4:
                    //if (createdClasses.size() < 2) {
                    if (Controller.getCreatedClassesSize() < 2) {
                        System.out.println("Please create 2 classes first");
                    } else {
                        printStringList(Controller.subMenu4());
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
                        //GUI.displayGUI();
                    }
                    break;
                case 5:
                    printStringList(Controller.subMenu5());
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
                    //GUI.displayGUI();
                    break;
                case 6:
                    printArrayOfStringList(Controller.help());
                    break;
                case 7:
                    //GUI.startGUIMenu();
                    break;
                case 8:
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
        if (Controller.listClasses().length != 0) {
            System.out.println("Existing Classes: ");
            CLI.listClasses();
        }
        String name = CLI.readString("Class Name: ");
        if(Controller.existentialCrisisExists(name)){
            System.out.println("Class " + name + " already exists, try again");
            return;
        }
        System.out.println("What number below corresponds to the class type?");
        String[] classTypes = Controller.listClassTypes();
        for (int i = 0; i < classTypes.length; i++) {
            System.out.println((i + 1) + ": " + classTypes[i]);
        }
        int type = CLI.readInt("Class Type Number: ");
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
        System.out.println("What is the name of the class you want to delete?");
        CLI.listClasses();
        String input = CLI.readString("Class Name: ");
        if(!Controller.existentialCrisisExists(input)){
            System.out.println("Class " + input + " does not exist, try again");
            return;
        }

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
        String oldName = CLI.readString("Class Name: ");
        if(!Controller.existentialCrisisExists(oldName)){
            System.out.println("Class " + oldName + " does not exist, try again");
            return;
        }

        System.out.println("What would you like to rename your class?");
        String name = CLI.readString("New Class Name: ");
        if(Controller.existentialCrisisExists(name)){
            System.out.println("Class " + name + " already exists, try again");
            return;
        }

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
        String name1 = CLI.readString("Class 1 name: ");
        if(!Controller.existentialCrisisExists(name1)){
            System.out.println("Class " + name1 + " does not exist, try again");
            return;
        }

        System.out.println("What is the name of the second class you want to have a relationship?(The higher/from class, e.g the other class implements this)");
        CLI.printStringList(Controller.listClasses());
        String name2 = CLI.readString("Class 2 name: ");
        if(!Controller.existentialCrisisExists(name2)){
            System.out.println("Class " + name2 + " does not exist, try again");
            return;
        }

        CLI.printStringListNumbered(Controller.listRelationshipTypes());
        System.out.println("Please select an option for the relationship type by number");
        String type = CLI.readString("Type #: ");

        //Test to see if adding relationship was sucessful

        if (Controller.addRelationship(name1, name2, type)) {
            System.out.println("Relationship created!");
        } else {
            System.out.println("Bad inputs for relationship, creation cancelled");
        }
    }

    public static void deleteRelationship() {

        System.out.println("What is the name of the first class of this relationship?");
        printStringList(Controller.listClasses());
        String name1 = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(name1)){
            System.out.println("Class " + name1 + " does not exist, try again");
            return;
        }
        System.out.println("What is the name of the second class?");
        printStringList(Controller.listClasses());
        String name2 = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(name2)){
            System.out.println("Class " + name2 + " does not exist, try again");
            return;
        }

        //Test to see if deleting relationship was sucessful
        //if (Controller.deleteRelationship(index1, index2, answer)) {
        if (Controller.deleteRelationship(name1, name2)) {
            System.out.println("Relationship deleted!");
        } else {
            System.out.println("Bad inputs for relationship, deletion cancelled.");
        }
    }

    //Structure complete errors will fix when merged
    public static void addAttribute() {
        String[] visibilityList = Controller.listVisibilityTypes();
        System.out.println("Are you wanting to add a field or a method?" +
                "\nType '1' for field, or '2' for method");
        int input = CLI.readInt("Input: ");

        if (input == 1) {
            System.out.println("Enter the name of the class you are adding the field to:");
            CLI.listClasses();
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the name of the field?");
            String fieldName = CLI.readString("Field name: ");
            System.out.println("Enter the visibility number below:");
            for (int i = 0; i < visibilityList.length; i++)
                System.out.println((i + 1) + ": " + visibilityList[i]);
            int view = CLI.readInt("Field visibility: ");
            System.out.println("Lastly, what is the data type of this field?");
            String type = CLI.readString("Field type: ");

            if (Controller.addField(className, fieldName, view, type)) {
                System.out.println("Field " + fieldName + " added to class " + className + "\n");
            } else {
                System.out.println("Failed to add field. Please try again");
            }
        } else if (input == 2) {
            System.out.println("Enter the name of the class you are adding this method to:");
            CLI.listClasses();
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What do you want to name this method?");
            String methodName = CLI.readString("Method name: ");
            System.out.println("Enter the visibility number below:");
            for (int i = 0; i < visibilityList.length; i++)
                System.out.println((i + 1) + ": " + visibilityList[i]);
            int view = CLI.readInt("Method visibility: ");
            System.out.println("What is the return type of this method?");
            String type = CLI.readString("Method return type: ");
            LinkedList<String> params = new LinkedList<>();
            System.out.print("Lastly what parameters does the method have?" +
                    "\nWhen you are finished press the enter key when the line is blank" +
                    "\nParameter: ");
            String paramInput;
            while (!(paramInput = kb.nextLine()).isEmpty()) {
                System.out.print("Parameter: ");
                params.add(paramInput);
            }

            if (Controller.addMethod(className, methodName, view, type, params)) {
                System.out.println("Method " + methodName + " added to class " + className);
            } else {
                System.out.println("Failed to add method. Please try again");
            }
        } else {
            System.out.println("Please enter either '1' or '2'. Please try again");
        }
    }

    public static void deleteAttribute() {
        System.out.println("Are you wanting to delete a field or a method?" +
                "\nType '1' for field, or '2' for method");
        int input = CLI.readInt("Input: ");

        if (input == 1) {
            System.out.println("What is the name of the class you want to remove a field from?");
            CLI.listClasses();
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the name of the field you wish to delete?");
            String[] fieldList = Controller.listClassFields(className);
            printStringList(fieldList);
            String fieldName = CLI.readString("Field name: ");

            if (Controller.deleteField(className, fieldName)) {
                System.out.println("Field " + fieldName + " was removed from class " + className);
            } else {
                System.out.println("Failed to delete field. Please try again");
            }


        } else if (input == 2) {
            System.out.println("What is the name of the class you want to remove a Method from??");
            CLI.listClasses();
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the name of the method you wish to delete?");
            String[] methodList = Controller.listClassMethods(className);
            printStringList(methodList);
            String methodName = CLI.readString("Method name: ");

            if (Controller.deleteMethod(className, methodName)) {
                System.out.println("Method " + methodName + " was removed from class " + className);
            } else {
                System.out.println("Failed to delete method. Please try again");
            }
        } else {
            System.out.println("Please enter either '1' or '2'. Please try again");
        }
    }

    public static void addParam() {
        System.out.println("What class contains the method you would like to add a parameter to?");
        CLI.listClasses();
        String className = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(className)){
            System.out.println("Class " + className + " does not exist, try again");
            return;
        }
        System.out.println("What is the name of the method you are adding the param to?");
        printStringList(Controller.listClassMethods(className));
        String methodName = CLI.readString("Method name: ");
        System.out.println("What is the new parameter you are adding?");
        String paramName = CLI.readString("Parameter: ");

        if (Controller.addParam(className, methodName, paramName)) {
            System.out.println("Parameter successfully added to " + methodName + "!");
        } else {
            System.out.println("Failed to add parameter. Please try again");
        }
    }

    public static void deleteParam() {
        System.out.println("What class contains the method you would like to remove a parameter from?");
        CLI.listClasses();
        String className = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(className)){
            System.out.println("Class " + className + " does not exist, try again");
            return;
        }
        System.out.println("What is the name of the method you are deleting the param from?");
        printStringList(Controller.listClassMethods(className));
        String methodName = CLI.readString("Method name: ");
        System.out.println("What is the param you are deleting?");
        String paramName = CLI.readString("Parameter: ");

        if (Controller.deleteParam(className, methodName, paramName)) {
            System.out.println("Parameter successfully removed from " + methodName + "!");
        } else {
            System.out.println("Failed to add parameter. Please try again");
        }
    }

    public static void renameParam() {
        System.out.println("What class contains the method you would like to rename a parameter in?");
        CLI.listClasses();
        String className = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(className)){
            System.out.println("Class " + className + " does not exist, try again");
            return;
        }
        System.out.println("What is the name of the method containing the parameter you are renaming?");
        printStringList(Controller.listClassMethods(className));
        String methodName = CLI.readString("Method name: ");
        System.out.println("Which parameter are you renaming?");
        String oldParamName = CLI.readString("Parameter: ");
        System.out.println("What will be the new name?");
        String newParamName = CLI.readString("New parameter: ");

        if (Controller.renameParam(className, methodName, oldParamName, newParamName)) {
            System.out.println("Parameter successfully renamed from " + oldParamName + " to " + newParamName + "!");
        } else {
            System.out.println("Failed to rename parameter. Please try again");
        }
    }


    public static void renameAttribute() {
        System.out.println("Are you wanting to delete a field or a method?" +
                "\nType '1' for field, or '2' for method");
        int input = readInt("Choice: ");
        if (input == 1) {
            System.out.println("What is the name of the class containing the field you wish to rename?");
            CLI.listClasses();
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the current name of the field you want to rename?");
            printStringList(Controller.listClassFields(className));
            String fieldName = CLI.readString("Current field name: ");
            System.out.println("What would you like this field's new name to be?");
            String newName = CLI.readString("New field name: ");
            if (Controller.renameField(className, fieldName, newName)) {
                System.out.println("Field " + fieldName + " renamed to " + newName);
            } else {
                System.out.println("Failed to rename field. Please try again");
            }

        } else if (input == 2) {
            System.out.println("What is the name of the class containing the method you wish to rename?");
            CLI.listClasses();
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the current name of the method you want to rename?");
            printStringList(Controller.listClassMethods(className));
            String methodName = CLI.readString("Current method name: ");
            System.out.println("What would you like this method's new name to be?");
            String newName = CLI.readString("New Method name: ");
            if (Controller.renameMethod(className, methodName, newName)) {
                System.out.println("Method " + methodName + " renamed to " + newName);
            } else {
                System.out.println("Failed to rename method. Please try again");
            }
        } else {
            System.out.println("Please enter either Field or Method. Please try again");
        }
    }


    public static void listAllClassDetails() {
        System.out.println("What is the name of the class you want see?");
        printStringList(Controller.listClasses());
        String input = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(input)){
            System.out.println("Class " + input + " does not exist, try again");
            return;
        }
        String[][] details = Controller.listAllClassDetails(input);
        if(details == null)
            System.out.println("Failed to get details for class: \"" + input + "\"");
        else
            printArrayOfStringList(details);

    }

    //This method takes in an arraylist of strings and is able to print it out line after line.
    //This method is used for many other methods that return String arrays full of data
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

    public static void listClasses() {
        int counter = 1;
        String[] list = Controller.listClasses();
        for (String s : list) {
            System.out.println(counter + ". " + s);
            counter++;
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
                System.out.print(msg);
            }
        }
    }

    public static String readString(final String msg) {
        System.out.print(msg);
        return kb.nextLine();
    }


}
