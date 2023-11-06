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
            int input = CLI.readInt("Choice: ");
            switch (input) {
                //Switch statement controls different options the user could select
                case 1:
                    //If the user selects 1, it will display the listing options
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
                    break;
            }
            if(Main.gview){
                GUI.displayGUI();
            }
        }
    }

    /**
     * This method will take class name input from the user in CLI mode and send it
     * to the controller. The controller will return true or false based on whether or not the class
     * was created.
    */
    public static void addClass() {
        //Prompts the user for a class name
        System.out.println("What would you like to name your class?");
        if (Controller.listClasses().length != 0) {
            System.out.println("Existing Classes: ");
            CLI.printStringListNumbered(Controller.listClasses());
        }
        //Prompts the user for a class type, displaying the class types with corresponding numbers
        //User selects a number and it is recorded
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
        //Then we pass in the name and the type to the controller's addClass
        //Test to see if adding was completed, alerting the user of its status
        int type = CLI.readInt("Class Type Number: ");
        //Test to see if adding was sucessful
        if (Controller.addClass(name, type)) {
            System.out.println("Class created!");
        } else {
            System.out.println("Class already exists or bad inputs were provided. Please try again.");
        }
    }

    /**
     * This method will take class index input from the user in CLI mode and send it
     * to the controller. The controller will return true or false based on whether the class
     * was deleted.
     */

    public static void deleteClass() {
        //Prompts the user for the name of the class they want to delete
        System.out.println("What is the name of the class you want to delete?");
        //Pass the user input to controller's delete class method then
        //test to see if deleting was sucessful
        CLI.printStringListNumbered(Controller.listClasses());
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

    /**
     * This method will take the index of the class as input from the user in CLI
     * mode and send it to the controller. The controller will return true or false based on whether the class
     * was renamed.
     */
    public static void renameClass() {
        //Prompts the user for the class they would like to rename
        System.out.println("What is the name of the class you want to rename?");
        CLI.printStringListNumbered(Controller.listClasses());
        //Prompts the user for the new class name
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

        //We then pass the old name and the new class name to the controller
        //It returns true if it was renamed and false if it fails
        if (Controller.renameClass(oldName, name)) {
            System.out.println("Class renamed!");
        } else {
            System.out.println("Bad input was provided. Failed to rename class.");
        }
    }

    /**
     * This method will take both names for both classes as input from the user in
     * CLI mode and send it to the controller. The controller will return true or false based on whether the
     * relationship was created.
     */
    public static void addRelationship() {
        //Prompts the user for the name of the first class in the desired relationship
        System.out.println("What is the name of the first class you want to have a relationship? (The lower/to class, e.g this implements the other class)");
        CLI.printStringList(Controller.listClasses());
      
        String name1 = CLI.readString("Class 1 name: ");
        if(!Controller.existentialCrisisExists(name1)){
            System.out.println("Class " + name1 + " does not exist, try again");
            return;
        }
        //Prompts the user for the second class in the relationship
        System.out.println("What is the name of the second class you want to have a relationship?(The higher/from class, e.g the other class implements this)");
        CLI.printStringList(Controller.listClasses());
        String name2 = CLI.readString("Class 2 name: ");
        if(!Controller.existentialCrisisExists(name2)){
            System.out.println("Class " + name2 + " does not exist, try again");
            return;
        }

        CLI.printStringListNumbered(Controller.listRelationshipTypes());
        //Prompts the user for a relationship type
        System.out.println("Please select an option for the relationship type by number");
        String type = CLI.readString("Type #: ");

        //User inputs are passed to the controller
        //Controller addRelationship returns true if it is completed and false if it was incorrect.

        if (Controller.addRelationship(name1, name2, type)) {
            System.out.println("Relationship created!");
        } else {
            System.out.println("Bad inputs for relationship, creation cancelled");
        }
    }

    /**
     * DeleteRelationship takes in the two classes in the relationship from the user and passes them
     * both to the controller. The controller method will return true if completed and false if not completed.
     */
    public static void deleteRelationship() {
        //Prompts the user for the first name in the class relationship
        System.out.println("What is the name of the first class of this relationship?");
        printStringList(Controller.listClasses());

        String name1 = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(name1)){
            System.out.println("Class " + name1 + " does not exist, try again");
            return;
        }
        //Prompts the user for the name of the second class
        System.out.println("What is the name of the second class?");
        printStringList(Controller.listClasses());
        String name2 = CLI.readString("Class name: ");
      
        //Passes both names entered by the user to the controller deleteRelationship method
        //Alerts the user if the action was completed
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

    /**
     * AddAttribute will prompt the user if they would like to add a method or a field.
     * It checks to see if the user entered a valid number coorespoinding to method or field
     * It then takes in appropriate input for the controllers addField or addMethod.
     * It calls the controller's method and alerts the user if the addition was a sucess
     */
    //Structure complete errors will fix when merged
    public static void addAttribute() {
        String[] visibilityList = Controller.listVisibilityTypes();
        System.out.println("Are you wanting to add a field or a method?" +
                "\nType '1' for field, or '2' for method");

        int input = CLI.readInt("Input: ");
        
        //If the input is 1, the user wants to add a field.
        //It takes in the name of the class, the name of the field, the visibility, and datatype
        if (input == 1) {
            System.out.println("Enter the name of the class you are adding the field to:");
            CLI.printStringListNumbered(Controller.listClasses());
            //Takes in the name of the class the field is being added to
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the name of the field?");
            //Takes in the field name
            String fieldName = CLI.readString("Field name: ");
            System.out.println("Enter the visibility number below:");
            for (int i = 0; i < visibilityList.length; i++)
                System.out.println((i + 1) + ": " + visibilityList[i]);
            //Takes in the visibility of the field
            int view = CLI.readInt("Field visibility: ");
            System.out.println("Lastly, what is the data type of this field?");
            //Takes in the datatype of the field
            String type = CLI.readString("Field type: ");
            
            //User inputs are called in the Controller addfield method
            //Alerts the user if field was added
            if (Controller.addField(className, fieldName, view, type)) {
                System.out.println("Field " + fieldName + " added to class " + className + "\n");
            } else {
                System.out.println("Failed to add field. Please try again");
            }
        } else if (input == 2) {
            //Takes in the name of the class the method is being added to
            System.out.println("Enter the name of the class you are adding this method to:");
            CLI.printStringListNumbered(Controller.listClasses());
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What do you want to name this method?");

            //Takes in the new method name
            String methodName = CLI.readString("Method name: ");
            System.out.println("Enter the visibility number below:");
            for (int i = 0; i < visibilityList.length; i++)
                System.out.println((i + 1) + ": " + visibilityList[i]);
            //Takes in an int to represent the visibility
            int view = CLI.readInt("Method visibility: ");
            System.out.println("What is the return type of this method?");
            //Takes in the return type of the method 
            String type = CLI.readString("Method return type: ");
            LinkedList<String> params = new LinkedList<>();
            System.out.print("Lastly what parameters does the method have?" +
                    "\nWhen you are finished press the enter key when the line is blank" +
                    "\nParameter: ");
            String paramInput;

            //Takes in parameters until line is empty and enter is pressed
            while (!(paramInput = kb.nextLine()).isEmpty()) {
                System.out.print("Parameter: ");
                params.add(paramInput);
            }
            //Takes all the user inputs and passes them to the Controller addMethod class
            if (Controller.addMethod(className, methodName, view, type, params)) {
                System.out.println("Method " + methodName + " added to class " + className);
            } else {
                System.out.println("Failed to add method. Please try again");
            }
        } else {
            System.out.println("Please enter either '1' or '2'. Please try again");
        }
    }

    /**
     * deleteAttribute will prompt the user if they would like to delete a method or a field.
     * It checks to see if the user entered a valid number coresponding to method or field
     * It then takes in appropriate input for the controllers deleteField or deleteMethod.
     * It calls the controller's method and alerts the user if the addition was a sucess
     */

    public static void deleteAttribute() {
        System.out.println("Are you wanting to delete a field or a method?" +
                "\nType '1' for field, or '2' for method");
        int input = CLI.readInt("Input: ");
        //If input is 1 or 2 it takes you to one of the two spots in the if statement
        if (input == 1) {
            System.out.println("What is the name of the class you want to remove a field from?");
            CLI.printStringListNumbered(Controller.listClasses());
            //Takes in the class name they want to remove the field from
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the name of the field you wish to delete?");
            //Shows the fields available and takes in the name of the field
            String[] fieldList = Controller.listClassFields(className);
            printStringList(fieldList);
            String fieldName = CLI.readString("Field name: ");
            //Controller delete field method is called with user inputs
            if (Controller.deleteField(className, fieldName)) {
                System.out.println("Field " + fieldName + " was removed from class " + className);
            } else {
                System.out.println("Failed to delete field. Please try again");
            }


        } else if (input == 2) {
            System.out.println("What is the name of the class you want to remove a Method from??");
            CLI.printStringListNumbered(Controller.listClasses());
            //Takes in the name of the class they want the method removed from
            String className = CLI.readString("Class name: ");
            if(!Controller.existentialCrisisExists(className)){
                System.out.println("Class " + className + " does not exist, try again");
                return;
            }
            System.out.println("What is the name of the method you wish to delete?");
            String[] methodList = Controller.listClassMethods(className);
            //Shows the list of methods associated with that class then takes in the name of the method
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

    /**
     * AddParam allows the user to add a parameter to a created method inside a class object
     * It takes in the name of the class, the name of the method then allows the user to add the parameter
     * It puts all the user input to the controller method and returns true if it worked, false if it didn't
     *
     */

    public static void addParam() {
        System.out.println("What class contains the method you would like to add a parameter to?");
        CLI.printStringListNumbered(Controller.listClasses());
        //Takes in the name of the class
        String className = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(className)){
            System.out.println("Class " + className + " does not exist, try again");
            return;
        }
        System.out.println("What is the name of the method you are adding the param to?");
        printStringList(Controller.listClassMethods(className));
        //takes in the name of the previously created method
        String methodName = CLI.readString("Method name: ");
        System.out.println("What is the new parameter you are adding?");
        //Takes in the name of the parameter
        String paramName = CLI.readString("Parameter: ");
      
        //Calls controller addParam method and alerts the user if it was added
        if (Controller.addParam(className, methodName, paramName)) {
            System.out.println("Parameter successfully added to " + methodName + "!");
        } else {
            System.out.println("Failed to add parameter. Please try again");
        }
    }

    /**
     * deleteParam allows the user to remove a parameter from a created method inside a class object
     * It takes in the name of the class, the name of the method then allows the user to delete the parameter
     * It puts all the user input to the controller method and returns true if it worked, false if it didn't
     *
     */
    public static void deleteParam() {
        System.out.println("What class contains the method you would like to remove a parameter from?");
        CLI.printStringListNumbered(Controller.listClasses());
        //Takes in the class name
        String className = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(className)){
            System.out.println("Class " + className + " does not exist, try again");
            return;
        }
        System.out.println("What is the name of the method you are deleting the param from?");
        printStringList(Controller.listClassMethods(className));
        //Takes in the method name
        String methodName = CLI.readString("Method name: ");
        System.out.println("What is the param you are deleting?");
        //takes in the Param name
        String paramName = CLI.readString("Parameter: ");
      
        //Passes in the input to the controller deleteParam
        if (Controller.deleteParam(className, methodName, paramName)) {
            System.out.println("Parameter successfully removed from " + methodName + "!");
        } else {
            System.out.println("Failed to add parameter. Please try again");
        }
    }
    /**
     * renameParam allows the user to rename a parameter to a created method inside a class object
     * It takes in the name of the class, the name of the method then allows the user to rename the parameter
     * It puts all the user input to the controller method and returns true if it worked, false if it didn't
     *
     */
    public static void renameParam() {
        System.out.println("What class contains the method you would like to rename a parameter in?");
        CLI.printStringListNumbered(Controller.listClasses());
        //Takes in the class name
        String className = CLI.readString("Class name: ");
        if(!Controller.existentialCrisisExists(className)){
            System.out.println("Class " + className + " does not exist, try again");
            return;
        }
        System.out.println("What is the name of the method containing the parameter you are renaming?");
        printStringList(Controller.listClassMethods(className));
        //Takes in the method from class name
        String methodName = CLI.readString("Method name: ");
        System.out.println("Which parameter are you renaming?");
        //Takes in the old parameter name
        String oldParamName = CLI.readString("Parameter: ");
        System.out.println("What will be the new name?");
        //takes in the new parameter name
        String newParamName = CLI.readString("New parameter: ");
        //Passes in all user input to controller
        //Returns true if competed false if it fails
        if (Controller.renameParam(className, methodName, oldParamName, newParamName)) {
            System.out.println("Parameter successfully renamed from " + oldParamName + " to " + newParamName + "!");
        } else {
            System.out.println("Failed to rename parameter. Please try again");
        }
    }

    /**
     * renameAttribute will prompt the user if they would like to rename a method or a field.
     * It checks to see if the user entered a valid number coresponding to method or field
     * It then takes in appropriate input for the controllers renameField or renameMethod.
     * It calls the controller's method and alerts the user if the addition was a sucess
     */
    public static void renameAttribute() {
        System.out.println("Are you wanting to delete a field or a method?" +
                "\nType '1' for field, or '2' for method");
        int input = readInt("Choice: ");
        if (input == 1) {
            System.out.println("What is the name of the class containing the field you wish to rename?");
            CLI.printStringListNumbered(Controller.listClasses());
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
            CLI.printStringListNumbered(Controller.listClasses());
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

    /**
     * List all class details takes in the name of a previously created class and
     * lists all methods, fields, and relationships associated with that class
     *
     */
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
        return kb.nextLine();
    }


}
