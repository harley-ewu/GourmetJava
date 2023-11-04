package src.main.java;

//The "view" when the program is in command line interface (CLI) mode

import java.util.LinkedList;
import java.util.Scanner;

public class CLI {
    //Scanner to read user input
    private static Scanner kb = new Scanner(System.in);

    public static void menu() {
        boolean cont = true;
        while (cont) {
            int input2;
            int input3;
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
                        printStringList(Controller.subMenu1());
                        System.out.print("Choice: ");
                        input2 = Integer.parseInt(kb.nextLine());
                        if (input2 == 1) {
                            CLI.listClasses();
                        } else if (input2 == 2) {
                            //printStringList(Controller.listAllClassDetails());
                        } else if (input2 == 3) {
                            CLI.listClassDetails();
                        } else if (input2 == 4) {
                            for(String[] list : Controller.listRelationships()){
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
                    System.out.print("Choice: ");
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
                        break;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                    GUI.displayGUI();
                    break;
                case 3:

                    if(Controller.getCreatedClassesSize() == 0){
                        System.out.println("Please create a class first");
                    } else {
                        printStringList(Controller.subMenu3());
                        System.out.print("Choice:");
                        input2 = Integer.parseInt(kb.nextLine());
                        if (input2 == 1) {
                            CLI.addAttribute();
                        } else if (input2 == 2) {
                            CLI.deleteAttribute();
                        } else if (input2 == 3) {
                            CLI.renameAttribute();
                        } else if (input2 == 4) {

                            printStringList(Controller.subMenu6());
                            input3 = Integer.parseInt(kb.nextLine());
                            if (input3 == 1) {
                                CLI.addParam();
                            } else if (input3 == 2) {
                                CLI.deleteParam();
                            } else if (input3 == 3) {
                                CLI.renameParam();
                            } else if (input3 == 4) {
                                System.out.println("WE NEED TO MAKE HELP FOR PARAM MENU");
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
                        GUI.displayGUI();
                    }
                    break;
                case 4:
                    //if (createdClasses.size() < 2) {
                    if(Controller.getCreatedClassesSize() < 2){
                        System.out.println("Please create 2 classes first");
                    } else {
                        printStringList(Controller.subMenu4());
                        System.out.print("Choice:");
                        input2 = Integer.parseInt(kb.nextLine());
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
                        GUI.displayGUI();
                    }
                    break;
                case 5:
                    printStringList(Controller.subMenu5());
                    System.out.print("Choice:");
                    input2 = Integer.parseInt(kb.nextLine());
                    if (input2 == 1) {
                        ModelDiagram.save();
                    } else if (input2 == 2) {
                        ModelDiagram.load();
                    } else if (input2 == 3) {
                        printStringList(Controller.saveLoadHelp());
                    } else if (input2 == 4) {
                        break;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                    GUI.displayGUI();
                    break;
                case 6:
                    Controller.help();
                    break;
                case 7:
                    GUI.startGUIMenu();
                    break;
                case 8:
                    System.out.println("Are you sure you want to exit? Type \"yes\" to confirm");
                    System.out.print("yes/no:");
                    if (kb.nextLine().equalsIgnoreCase("yes")) {
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
        System.out.print("Class Name:");
        String name = kb.nextLine();
        System.out.println("What is the class's type?");

        //printStringList(ClassBox.listClassTypes());

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

        if(Controller.addRelationship(name1, name2, type)){
            System.out.println("Relationship created!");
        } else {
            System.out.println("Bad inputs for relationship, creation cancelled");
        }
    }

    public static void deleteRelationship() {

        System.out.println("What is the name of the first class of this relationship?");
        printStringList(Controller.listClasses());
        System.out.print("Class name:");
        String name1 = kb.nextLine();
        System.out.println("What is the name of the second class?");
        printStringList(Controller.listClasses());
        System.out.print("Class name:");
        String name2 = kb.nextLine();


        //Test to see if deleting relationship was sucessful
        //if (Controller.deleteRelationship(index1, index2, answer)) {
        if(Controller.deleteRelationship(name1, name2)){
            System.out.println("Relationship deleted!");
        } else {
            System.out.println("Bad inputs for relationship, deletion cancelled.");
        }
    }

    //Structure complete errors will fix when merged
    public static void addAttribute(){
        String[] visibilityList = Controller.listVisibilityTypes();
        System.out.println("Are you wanting to add a field or a method?" +
                            "\nType 'Field' for field, or 'Method' for method");
        String input = kb.nextLine();

        if(input.equalsIgnoreCase("Field")){
            System.out.println("Enter the name of the class you are adding the field to:");
            CLI.listClasses();
            System.out.print("Class name: ");
            String className = kb.nextLine();
            System.out.println("What is the name of the field?");
            System.out.print("Field name: ");
            String fieldname = kb.nextLine();
            System.out.println("Enter the visibility number below:");
            for (int i = 0; i < visibilityList.length; i++)
                System.out.println((i + 1) + ": " + visibilityList[i]);
            System.out.print("Field visibility: ");
            int view = Integer.parseInt(kb.nextLine());
            System.out.println("Lastly, what is the data type of this field?");
            System.out.print("Field type: ");
            String type = kb.nextLine();

            if(Controller.addField(className, fieldname, view, type)){
                System.out.println("Field " + fieldname + " added to class " + className + "\n");
            }else{
                System.out.println("Failed to add field. Please try again");
            }
        }else if(input.equalsIgnoreCase("Method")){
            System.out.println("Enter the name of the class you are adding this method to:");
            CLI.listClasses();
            System.out.print("Class name: ");
            String className = kb.nextLine();
            System.out.println("What do you want to name this method?");
            System.out.print("Method name: ");
            String methodName = kb.nextLine();
            System.out.println("Enter the visibility number below:");
            for (int i = 0; i < visibilityList.length; i++)
                System.out.println((i + 1) + ": " + visibilityList[i]);
            System.out.print("Method visibility: ");
            int view = Integer.parseInt(kb.nextLine());
            System.out.println("What is the return type of this method?");
            System.out.print("Method return type: ");
            String type = kb.nextLine();
            LinkedList<String> params = new LinkedList<>();
            System.out.print("Lastly what parameters does the method have?" +
                                "\nWhen you are finished press the enter key" +
                                "\nParam: ");
            while (!kb.nextLine().isEmpty()) {
                System.out.print("Param: ");
            }

            if(Controller.addMethod(className, methodName, view, type, params)){
                System.out.println("Field  " + methodName + " added to class " + className);
            }else{
                System.out.println("Failed to add field. Please try again");
            }
        }else{
            System.out.println("Please enter either Field or Method. Please try again");
        }
    }
    public static void deleteAttribute() {
        System.out.println("Are you wanting to delete a field or a method?");
        String input = kb.nextLine();
        if (input.equalsIgnoreCase("Field")) {
            System.out.println("What is the name of the class you want to remove a field from?");
            CLI.listClasses();
            System.out.print("Class name: ");
            String className = kb.nextLine();
            System.out.println("What is the name of the field you wish to delete?");
            String[] fieldList = Controller.listClassFields(className);
            printStringList(fieldList);
            System.out.print("Field name: ");
            String fieldname = kb.nextLine();

            if (Controller.deleteField(className, fieldname)) {
                System.out.println("Field " + fieldname + " was removed from class " + className);
            } else {
                System.out.println("Failed to delete field. Please try again");
            }


        } else if (input.equalsIgnoreCase("Method")) {
            System.out.println("What is the name of the class you want to remove a Method from??");
            CLI.listClasses();
            System.out.print("Class name: ");
            String className = kb.nextLine();
            System.out.println("What is the name of the method you wish to delete?");
            String[] methodList = Controller.listClassMethods(className);
            printStringList(methodList);
            System.out.print("Method name: ");
            String methodName = kb.nextLine();

            if (Controller.deleteMethod(className, methodName)) {
                System.out.println("Method " + methodName + " was removed from class " + className);
            } else {
                System.out.println("Failed to delete method. Please try again");
            }
        }else{
        System.out.println("Please enter either Field or Method. Please try again");
    }
    }

    public static void addParam() {
        System.out.println("What class contains the method you would like to add a parameter to?");
        CLI.listClasses();
        System.out.print("Class name: ");
        String className = kb.nextLine();
        System.out.println("What is the name of the method you are adding the param to?");
        Controller.listClassMethods(className);
        System.out.print("Method name: ");
        String methodName = kb.nextLine();
        System.out.println("What is the new parameter you are adding?");
        System.out.print("Parameter: ");
        String paramName = kb.nextLine();

        if (Controller.addParam(className, methodName, paramName)) {
            System.out.println("Parameter successfully added to " + methodName + "!");
        }
        else {
            System.out.println("Failed to add parameter. Please try again");
        }
    }

    public static void deleteParam() {
        System.out.println("What class contains the method you would like to remove a parameter from?");
        CLI.listClasses();
        System.out.print("Class name: ");
        String className = kb.nextLine();
        System.out.println("What is the name of the method you are deleting the param from?");
        Controller.listClassMethods(className);
        System.out.print("Method name: ");
        String methodName = kb.nextLine();
        System.out.println("What is the param you are deleting?");
        System.out.print("Parameter: ");
        String paramName = kb.nextLine();

        if (Controller.deleteParam(className, methodName, paramName)) {
            System.out.println("Parameter successfully added to " + methodName + "!");
        }
        else {
            System.out.println("Failed to add parameter. Please try again");
        }
    }

    public static void renameParam() {
        System.out.println("What class contains the method you would like to rename a parameter in?");
        CLI.listClasses();
        System.out.print("Class name: ");
        String className = kb.nextLine();
        System.out.println("What is the name of the method containing the parameter you are renaming?");
        Controller.listClassMethods(className);
        System.out.print("Method name: ");
        String methodName = kb.nextLine();
        System.out.println("Which parameter are you renaming?");
        System.out.print("Parameter: ");
        String oldParamName = kb.nextLine();
        System.out.println("What will be the new name?");
        System.out.print("New parameter: ");
        String newParamName = kb.nextLine();

        if (Controller.renameParam(className, methodName, oldParamName, newParamName)) {
            System.out.println("Parameter successfully renamed from " + oldParamName + " to " + newParamName + "!");
        }
        else {
            System.out.println("Failed to rename parameter. Please try again");
        }
    }


    public static void renameAttribute(){
        System.out.println("Are you wanting to rename a field or a method?");
        String input = kb.nextLine();
        if(input.equalsIgnoreCase("Field")){
            System.out.println("What is the name of the class containing the field you wish to rename?");
            CLI.listClasses();
            System.out.print("Class name: ");
            String className = kb.nextLine();
            System.out.println("What is the current name of the field you want to rename?");

            System.out.print("Current field name: ");
            String fieldName = kb.nextLine();
            System.out.println("What would you like this field's new name to be?");
            System.out.print("New field name: ");
            String newName = kb.nextLine();
            if(Controller.renameField(className, fieldName, newName)){
                System.out.println("Field  " + fieldName + " renamed to " + newName);
            }else{
                System.out.println("Failed to rename field. Please try again");
            }

        }else if(input.equalsIgnoreCase("Method")){
            System.out.println("What is the name of the class containing the method you wish to rename?");
            CLI.listClasses();
            System.out.print("Class name: ");
            String className = kb.nextLine();
            System.out.println("What is the current name of the method you want to rename?");

            System.out.print("Current method name: ");
            String methodName = kb.nextLine();
            System.out.println("What would you like this method's new name to be?");
            System.out.print("Class name: ");
            String newName = kb.nextLine();
            if(Controller.renameMethod(className, methodName, newName)){
                System.out.println("Field  " + methodName + " renamed to " + newName);
            }else{
                System.out.println("Failed to rename method. Please try again");
            }
        }else{
            System.out.println("Please enter either Field or Method. Please try again");
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
