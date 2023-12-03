package j;

/**
 * This class is for when the view selected is for CLI (Command Line Interface). It will handle displaying
 * the information retrieved for the CLI.
 */

import org.jline.builtins.Completers;
import org.jline.console.ArgDesc;
import org.jline.console.CmdDesc;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.*;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.widget.TailTipWidgets;

import java.io.IOException;
import java.util.*;

public class CLI {
    //Scanner to read user input
    private static final Scanner kb = new Scanner(System.in);

    /**
     * The menu method displays the first few options for the user.
     * It promts the user to type in a full command to specify the action they want to complete.
     * The while loop checks if a boolean variable 'cont' is true. If it is,
     * it continues the loop. The only time cont will return false is if the user selects
     * the exit option from the menu.
     *
     */
    public static void menu() {
        Terminal terminal = null;
        /*
            This is where the terminal object is created.
            It essentially just works on it's own based on the OS.
         */
        try {
            terminal = TerminalBuilder.terminal();
        } catch (IOException e) {
            System.out.print("Failed to integrate with terminal");
        }

        /*
            The completers are configured to give autosuggestions
            at each command level, the null completer prevents further suggestions.
         */
        ArgumentCompleter AddDeleteCompleter = new ArgumentCompleter(
                new StringsCompleter("add", "delete"),
                new StringsCompleter("class", "method", "field", "relationship", "parameter"),
                new NullCompleter());

        ArgumentCompleter ListCompleter = new ArgumentCompleter(
                new StringsCompleter("list"),
                new StringsCompleter("all", "classes", "relationships"),
                new NullCompleter());

        ArgumentCompleter SingleCompleter = new ArgumentCompleter(
                new StringsCompleter("save", "load", "help", "window", "exit", "undo", "redo"),
                new NullCompleter());

        ArgumentCompleter RenameCompleter = new ArgumentCompleter(
                new StringsCompleter("rename"),
                new StringsCompleter("class", "field", "method", "parameter"),
                new NullCompleter());

        AggregateCompleter CombinedCompleters = new AggregateCompleter(AddDeleteCompleter, ListCompleter, SingleCompleter ,RenameCompleter);
        // This is the LineReader that handles input and brings it all together
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(CombinedCompleters)
                .parser(new DefaultParser())
                .build();

        Map<String, CmdDesc> tailTips = new HashMap<>();
        Map<String, List<AttributedString>> widgetOpts = new HashMap<>();

        // Descriptors that display each commands requirements
        List<AttributedString> addDesc = Arrays.asList(new AttributedString("add class [class-name] [class-type (options: CLASS, INTERFACE, RECORD, ENUMERATION, ANNOTATION)]"),
                new AttributedString("add method [class-name] [method-name] [visibility-type (options: PRIVATE, PUBLIC, PROTECTED)] [return-type] [Param-1] [Param-2] ... [Param-N]"),
                new AttributedString("add field [class-name] [field-name] [visibility-type (options: PRIVATE, PUBLIC, PROTECTED)] [data-type]"),
                new AttributedString("add relationship [1st-class-name] [relationship-type (options: aggregates, composes, implements, realizes)] [2nd-class-name]"),
                new AttributedString("add parameter [class-name] [method-name] [parameter-name]")
        );

        List<AttributedString> deleteDesc = Arrays.asList(new AttributedString("delete class [class-name]"),
                new AttributedString("delete method [class-name] [method-name]"),
                new AttributedString("delete field [class-name] [field-name]"),
                new AttributedString("delete relationship [1st-class-name] [2nd-class-name]"),
                new AttributedString("delete parameter [class-name] [method-name] [parameter-name]")
        );

        List<AttributedString> listDesc = Arrays.asList(new AttributedString("list all"),
                new AttributedString("list classes"),
                new AttributedString("list relationships")
        );

        List<AttributedString> renameDesc = Arrays.asList(new AttributedString("rename class [old-class-name] [new-class-name]"),
                new AttributedString("rename field [class-name] [old-field-name] [new-field-name]"),
                new AttributedString("rename method [class-name] [old-method-name] [new-method-name]"),
                new AttributedString("rename parameter [class-name] [method-name] [old-parameter-name] [new-parameter-name]")
        );

        // This attaches the descriptions to the commands
        tailTips.put("add", new CmdDesc(addDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("delete", new CmdDesc(deleteDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("list", new CmdDesc(listDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("rename", new CmdDesc(renameDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));

        // Create tailtip widgets that uses description window size 5 and
        // does not display suggestions after the cursor
        TailTipWidgets tailtipWidgets = new TailTipWidgets(reader, tailTips, 5, TailTipWidgets.TipType.COMBINED);
        // Enable autosuggestions
        tailtipWidgets.enable();

        boolean cont = true;
        while (cont) {
            int input2;
            int input3;

            //Retrieves the user input in 'input'
            //Calls a method readStringSplit that splits the users full command into elements in an array
            //Each element in the array is a word separated by a space
            //We create an array where each element is a word for us to check the type of command that is used
            String[] input = reader.readLine("Command: ").split(" ");
            //If the user enters nothing, we have them restart
            if(input.length == 0){
                System.out.println("Please enter a command");
                break;
            }
            //We use a switch statement to check the first word in the command
            switch (input[0]) {
                //The command begins with add
                case "add":

                    switch (input[1]) {
                        //The next part of the command is class
                        case "class": {
                            if(input.length != 4){
                                //If the command is more or less than 4 parameters it will not be a valid method
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }

                            /*if(!readInt(input[3])){
                                System.out.println("Please enter a valid number");
                                break;
                            }
                            if(isNegative(Integer.parseInt(input[3]))){
                                System.out.println("Please enter a non-negative number");
                                break;
                            }*/

                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.addClass(input[2], getClassTypeNumber(input[3]));
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Class " + input[2] + " " + status.toString());
                            }else{
                                System.out.println("Class " + input[2] + " added!");
                            }

                            break;
                        }
                        //The next part of the command is method
                        case "method": {
                            if(input.length < 6){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }

                            LinkedList<String> params = new LinkedList<String>();
                            params.addAll(Arrays.asList(input).subList(6, input.length));


                            /*if(!readInt(input[4])){
                                System.out.println("Please enter a valid visibility number");
                                break;
                            }
                            if(isNegative(Integer.parseInt(input[4]))){
                                System.out.println("Please enter a non-negative number");
                                break;
                            }*/

                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.addMethod(input[2], input[3], getVisibilityNumber(input[4]), input[5], params);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Method " + input[3] + " " + status.toString());
                            }else{
                                System.out.println("Method " + input[3] + " added to class " + input[2] + "!");
                            }
                            break;
                        }
                        //The next part of the command is field
                        case "field": {
                            if(input.length != 6){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }

                            /*if(!readInt(input[4])){
                                System.out.println("Please enter a valid visibility number");
                                break;
                            }*/
                            /*if(isNegative(Integer.parseInt(input[4]))){
                                System.out.println("Please enter a non-negative number");
                                break;
                            }*/

                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.addField(input[2], input[3], CLI.getVisibilityNumber(input[4]), input[5]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Field " + input[3] + " " + status.toString());
                            }else{
                                System.out.println("Field " + input[3] + " added to class " + input[2] + "!");
                            }
                            CLI.printArrayOfStringList(Controller.listAllClassDetails(input[2]));

                            break;
                        }
                        //The next part of the command is relationship
                        case "relationship": {
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }



                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.addRelationship(input[4], input[2], getRelationshipTypeNumber(input[3]));
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Relationship " + status.toString());
                            }else{
                                System.out.println("Relationship between " + input[2] + " and " + input[4] + " created!");
                            }

                            break;
                        }
                        //The next part of command is parameter
                        case "parameter": {
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.addParam(input[2], input[3], input[4]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Parameter " + status.toString());
                            }else{
                                System.out.println("Parameter " + input[4] + " created!");
                            }

                            break;
                        }
                        default: {
                            //The next part of the command is not one of the prior options
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                    break;
                //The first part of the command is delete
                case "delete":

                    switch(input[1]){
                        //The next part of the command is class
                        case "class":{
                            if(input.length != 3){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteClass(input[2]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Class " + status.toString());
                            }else{
                                System.out.println("Class " + input[2] + " deleted!");
                            }
                            break;
                        }
                        case "method":{
                            //The next part of the command is method
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteMethod(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Method " + status.toString());
                            }else{
                                System.out.println("Method " + input[3] + " removed from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "field":{
                            //The next part of the command is field
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteField(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Field " + status.toString());
                            }else{
                                System.out.println("Field " + input[3] + " removed from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "relationship": {
                            //The next part of the command is relationship
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteRelationship(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Relationship " + status.toString());
                            }else{
                                System.out.println("Relationship between " + input[2] + " and " + input[3] + " deleted!");
                            }
                            break;
                        }
                        case "parameter": {
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteParam(input[2], input[3], input[4]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Parameter " + status.toString());
                            }else{
                                System.out.println("Parameter " + input[4] + " deleted!");
                            }

                            break;
                        }
                        default:{
                            //The next part of the command is invalid
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                    break;
                case "list":
                    //The first part of the command is list
                    if(input.length != 2){
                        System.out.println("Command is an invalid length. Please try again");
                        break;
                    }
                    //Displays information based on the second part of the list command
                    switch (input[1]) {
                        case "all": {
                            listClassesDetailed();
                            break;
                        }
                        case "classes": {
                            printStringListNumbered(Controller.listClasses());
                            break;
                        }
                        case "relationships": {
                            CLI.printArrayOfStringList(Controller.listRelationships());
                            break;
                        }
                        default:{
                            System.out.println("Not a valid list command. Please try again");
                            break;
                        }
                    }
                    break;
                case "rename":
                    //The next part of the command is rename
                    switch(input[1]){
                        case "class":{
                            //The next part of the command is class
                            if(input.length != 4){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameClass(input[2], input[3]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Class " + status.toString());
                            }else{
                                System.out.println("Class " + input[2] +  " renamed to " + input[3]);
                            }
                            break;
                        }
                        case "method":{
                            //The next part of the command is method
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameMethod(input[2], input[3], input[4]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Method " + status.toString());
                            }else{
                                System.out.println("Method " + input[3] + " renamed to " + input [4] +"from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "field":{
                            //The next part of the command is field
                            if(input.length != 5){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameField(input[2], input[3], input[4]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Field " + status.toString());
                            }else{
                                System.out.println("Field " + input[3] + " renamed to " + input [4] +"from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "parameter": {
                            if(input.length != 6){
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameParam(input[2], input[3], input[4], input[5]);
                            if(status != Controller.STATUS_CODES.SUCCESS){
                                System.out.println("Parameter " + status.toString());
                            }else{
                                System.out.println("Parameter " + input[4] + " renamed to " + input[5]);
                            }
                            break;
                        }
                        default:{
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                    break;
                case "save":
                    //The next part of the command is save
                    CLI.save();
                    break;
                case "load":
                    //The next part of the command is load

                    CLI.load();
                    break;
                case "window":
                    //The next part of the command is window
                    Main.gview = true;
                    GUI.startGUIMenu();
                    Controller.updateGUI(Controller.FULL_REFRESH, null);
                    break;

                case "undo":
                    System.out.println("undo: " + Controller.undo().toString());
                    break;

                case "redo":
                    System.out.println("redo: " + Controller.redo().toString());
                    break;
                    
                case "exit":
                    //The next part of the command is exit
                    System.out.println("Are you sure you want to exit? Type \"yes\" to confirm");
                    System.out.print("yes/no: ");
                    //confirms whether or not you want to save and exit
                    if (kb.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("Would you like to save first?");
                        System.out.print("yes/no: ");
                        if (kb.nextLine().equalsIgnoreCase("yes")) {
                            CLI.save();
                        }
                        System.out.println("Program Closed! Bye!");
                        cont = false;
                        Main.cview = false;
                        System.exit(0);
                    }
                    break;
                case "help":
                    //CDisplays the help menu
                    CLI.printStringList(Controller.printMenu());
                    System.out.println("Class Types:");
                    CLI.printStringListNumbered(Controller.listClassTypes());
                    System.out.println("Relationship Types:");
                    CLI.printStringListNumbered(Controller.listRelationshipTypes());
                    break;
                default:
                    System.out.println("Your command is invalid. Please type a valid command");
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
                //GUI.displayGUI();
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
        System.out.println("All created classes and their details: ");
        String[] classes = Controller.listClasses();
        for (String aClass : classes) {
            printArrayOfStringList(Controller.listAllClassDetails(aClass));
            System.out.println(" ");
        }
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

    public static boolean readInt(final String msg) {
            try {
                Integer.parseInt(msg);
                return true;
            } catch (Exception e) {
                return false;
            }


    }
    public static boolean isNegative(int num){
        return num < 0;
    }

    public static String readString(final String msg) {
        System.out.print(msg);
        return kb.nextLine().toLowerCase();
    }
    public static String[] readStringSplit(final String msg) {
        System.out.print(msg);
        return kb.nextLine().toLowerCase().split(" ");
    }

    public static int getVisibilityNumber(final String type) {
        if (type.equalsIgnoreCase("PRIVATE")) {
            return 1;
        } else if (type.equalsIgnoreCase("PUBLIC")) {
            return 2;
        } else if (type.equalsIgnoreCase("PROTECTED")) {
            return 3;
        }
        return -1;
    }

    public static int getClassTypeNumber(final String type) {
        if (type.equalsIgnoreCase("CLASS")) {
            return 1;
        } else if (type.equalsIgnoreCase("INTERFACE")) {
            return 2;
        } else if (type.equalsIgnoreCase("RECORD")) {
            return 3;
        } else if (type.equalsIgnoreCase("ENUMERATION")) {
            return 4;
        } else if (type.equalsIgnoreCase("ANNOTATION")) {
            return 5;
        }
        return -1;
    }

    public static int getRelationshipTypeNumber(final String type) {
        if (type.equalsIgnoreCase("aggregates")) {
            return 1;
        } else if (type.equalsIgnoreCase("composes")) {
            return 2;
        } else if (type.equalsIgnoreCase("implements")) {
            return 3;
        } else if (type.equalsIgnoreCase("realizes")) {
            return 4;
        }
        return -1;
    }


}
