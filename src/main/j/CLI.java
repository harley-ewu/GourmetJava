package j;

/**
 * This class is for when the view selected is for CLI (Command Line Interface). It will handle displaying
 * the information retrieved for the CLI.
 */

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
        ArgumentCompleter AddCompleter = new ArgumentCompleter(
                new StringsCompleter("add"),
                new StringsCompleter("class", "method", "field", "relationship", "parameter"),
                new NullCompleter());

        ArgumentCompleter ClassCompleter = new ArgumentCompleter(
                new StringsCompleter("add"),
                new StringsCompleter("class"),
                new StringsCompleter("class", "interface", "record", "enumeration", "annotation"),
                new NullCompleter());

        ArgumentCompleter MethodCompleter = new ArgumentCompleter(
                new StringsCompleter("add"),
                new StringsCompleter("method"),
                new StringsCompleter("private", "public", "protected"),
                new NullCompleter());

        ArgumentCompleter FieldCompleter = new ArgumentCompleter(
                new StringsCompleter("add"),
                new StringsCompleter("field"),
                new StringsCompleter("private", "public", "protected"),
                new NullCompleter());

        ArgumentCompleter RelationshipCompleter = new ArgumentCompleter(
                new StringsCompleter("add"),
                new StringsCompleter("relationship"),
                new StringsCompleter("aggregates", "composes", "implements", "realizes"),
                new NullCompleter());

        ArgumentCompleter DeleteCompleter = new ArgumentCompleter(
                new StringsCompleter("delete"),
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

        AggregateCompleter CombinedCompleters = new AggregateCompleter(AddCompleter, DeleteCompleter, ListCompleter, SingleCompleter, RenameCompleter, ClassCompleter, MethodCompleter, FieldCompleter, RelationshipCompleter);
        // This is the LineReader that handles input and brings it all together
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(CombinedCompleters)
                .parser(new DefaultParser())
                .build();

        Map<String, CmdDesc> tailTips = new HashMap<>();
        Map<String, List<AttributedString>> widgetOpts = new HashMap<>();

        // Descriptors that display each commands requirements
        List<AttributedString> addDesc = Arrays.asList(new AttributedString("add class [class-type (options: CLASS, INTERFACE, RECORD, ENUMERATION, ANNOTATION)] [class-name]"),
                new AttributedString("add method [visibility-type (options: PRIVATE, PUBLIC, PROTECTED)] [class-name] [method-name] [return-type] [Param-1] [Param-2] ... [Param-N]"),
                new AttributedString("add field [visibility-type (options: PRIVATE, PUBLIC, PROTECTED)] [class-name] [field-name] [data-type]"),
                new AttributedString("add relationship [relationship-type (options: aggregates, composes, implements, realizes)] [1st-class-name] [2nd-class-name]"),
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

        List<AttributedString> saveDesc = Arrays.asList(new AttributedString("save [file-name]"));
        List<AttributedString> loadDesc = Arrays.asList(new AttributedString("load [file-name]"));

        // This attaches the descriptions to the commands
        tailTips.put("add", new CmdDesc(addDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("delete", new CmdDesc(deleteDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("list", new CmdDesc(listDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("rename", new CmdDesc(renameDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("save", new CmdDesc(saveDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));
        tailTips.put("load", new CmdDesc(loadDesc, ArgDesc.doArgNames(Arrays.asList("")), widgetOpts));

        // Create tailtip widgets that uses description window size 5 and
        // does not display suggestions after the cursor
        TailTipWidgets tailtipWidgets = new TailTipWidgets(reader, tailTips, 5, TailTipWidgets.TipType.COMBINED);
        // Enable autosuggestions
        tailtipWidgets.enable();

        boolean cont = true;
        while (cont) {

            String[] input = reader.readLine("Command: ").split(" ");
            //If the user enters nothing, we have them restart
            if (input.length == 0) {
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
                            if (input.length != 4) {
                                //If the command is more or less than 4 parameters it will not be a valid method
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }

                            //Retrieves the status code for the method and displays results
                            int classTypeNum = getClassTypeNumber(input[2]);
                            if (classTypeNum == -1) {
                                System.out.println('"' + input[2] + '"' + " is not a valid class type, please see help for valid types");
                            } else {
                                Controller.STATUS_CODES status = Controller.addClass(input[3], classTypeNum);

                                if (status != Controller.STATUS_CODES.SUCCESS) {
                                    System.out.println("Class " + input[3] + " " + status.toString());
                                } else {
                                    System.out.println("Class " + input[3] + " added!");
                                }
                            }


                            break;
                        }
                        //The next part of the command is method
                        case "method": {
                            if (input.length < 6) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }

                            LinkedList<String> params = new LinkedList<String>();
                            params.addAll(Arrays.asList(input).subList(6, input.length));

                            //Retrieves the status code for the method and displays results
                            int visibilityTypeNum = getVisibilityNumber(input[2]);
                            if (visibilityTypeNum == -1) {
                                System.out.println("'" + input[2] + "' is not a valid visibility type, please see help for valid types");
                            } else {
                                Controller.STATUS_CODES status = Controller.addMethod(input[3], input[4], visibilityTypeNum, input[5], params);
                                if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                    System.out.println("Class '" + input[3] + "' " + status.toString());
                                } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                    System.out.println("Method '" + input[4] + "' " + status.toString());
                                } else {
                                    System.out.println("Method " + input[4] + " added to class " + input[3] + "!");
                                }
                            }
                            break;
                        }
                        //The next part of the command is field
                        case "field": {
                            if (input.length != 6) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            int visibilityTypeNum = getVisibilityNumber(input[2]);
                            if (visibilityTypeNum == -1) {
                                System.out.println("'" + input[2] + "' is not a valid visibility type, please see help for valid types");
                            } else {
                                Controller.STATUS_CODES status = Controller.addField(input[3], input[4], visibilityTypeNum, input[5]);
                                if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                    System.out.println("Class '" + input[3] + "' " + status.toString());
                                } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                    System.out.println("Field " + input[4] + " " + status.toString());
                                } else {
                                    System.out.println("Field " + input[4] + " added to class " + input[3] + "!");
                                    CLI.printArrayOfStringList(Controller.listAllClassDetails(input[3]));
                                }
                            }


                            //Retrieves the status code for the method and displays results

                            break;
                        }
                        //The next part of the command is relationship
                        case "relationship": {
                            if (input.length != 5) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }

                            int relationshipTypeNum = getRelationshipTypeNumber(input[2]);
                            if (relationshipTypeNum == -1) {
                                System.out.println("'" + input[2] + "' is not a valid visibility type, please see help for valid types");
                            } else {
                                //Retrieves the status code for the method and displays results
                                Controller.STATUS_CODES status = Controller.addRelationship(input[4], input[3], relationshipTypeNum);
                                if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                    System.out.println("One or both of the classes entered does not exist, try again with names of existing classes");
                                } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                    System.out.println("Relationship " + status.toString());
                                } else {
                                    System.out.println("Relationship between " + input[3] + " and " + input[4] + " created!");
                                }
                            }

                            break;
                        }
                        //The next part of command is parameter
                        case "parameter": {
                            if (input.length != 5) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.addParam(input[2], input[3], input[4]);
                            if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' not found, try again with a valid name of an existing class");
                            } else if (status == Controller.STATUS_CODES.METHOD_NOT_FOUND) {
                                System.out.println("Method '" + input[3] + "' " + status + ", try again with a valid name of an existing method");
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Parameter " + status.toString());
                            } else {
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

                    switch (input[1]) {
                        //The next part of the command is class
                        case "class": {
                            if (input.length != 3) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteClass(input[2]);
                            if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Class '" + input[2] + "' does not exist");
                            } else {
                                System.out.println("Class " + input[2] + " deleted!");
                            }
                            break;
                        }
                        case "method": {
                            //The next part of the command is method
                            if (input.length != 4) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteMethod(input[2], input[3]);
                            if (status == Controller.STATUS_CODES.METHOD_NOT_FOUND) {
                                System.out.println("Method '" + input[3] + "' " + status.toString());
                            } else if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' does not exist");
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Method " + status.toString());
                            } else {
                                System.out.println("Method " + input[3] + " removed from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "field": {
                            //The next part of the command is field
                            if (input.length != 4) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteField(input[2], input[3]);
                            if (status == Controller.STATUS_CODES.FIELD_NOT_FOUND) {
                                System.out.println("Field '" + input[3] + "' " + status.toString());
                            } else if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' does not exist");
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Class '" + input[2] + "' " + status.toString());
                            } else {
                                System.out.println("Field " + input[3] + " removed from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "relationship": {
                            //The next part of the command is relationship
                            if (input.length != 4) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteRelationship(input[2], input[3]);
                            if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("One or both classes don't exist, please ensure you enter existing class names");
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Relationship " + status.toString());
                            } else {
                                System.out.println("Relationship between " + input[2] + " and " + input[3] + " deleted!");
                            }
                            break;
                        }
                        case "parameter": {
                            if (input.length != 5) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.deleteParam(input[2], input[3], input[4]);
                            if (status == Controller.STATUS_CODES.PARAM_NOT_FOUND) {
                                System.out.println("Parameter '" + input[4] + "' does not exist in method '" + input[3] + "'");
                            } else if (status == Controller.STATUS_CODES.METHOD_NOT_FOUND) {
                                System.out.println("Method '" + input[3] + "' " + status.toString());
                            } else if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' " + status.toString());
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Class " + status.toString());
                            } else {
                                System.out.println("Parameter " + input[4] + " deleted!");
                            }

                            break;
                        }
                        default: {
                            //The next part of the command is invalid
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                    break;
                case "list":
                    //The first part of the command is list
                    if (input.length != 2) {
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
                        default: {
                            System.out.println("Not a valid list command. Please try again");
                            break;
                        }
                    }
                    break;
                case "rename":
                    //The next part of the command is rename
                    switch (input[1]) {
                        case "class": {
                            //The next part of the command is class
                            if (input.length != 4) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameClass(input[2], input[3]);
                            if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' " + status.toString());
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Class " + status.toString());
                            } else {
                                System.out.println("Class " + input[2] + " renamed to " + input[3]);
                            }
                            break;
                        }
                        case "method": {
                            //The next part of the command is method
                            if (input.length != 5) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameMethod(input[2], input[3], input[4]);
                            if (status == Controller.STATUS_CODES.METHOD_NOT_FOUND) {
                                System.out.println("Method '" + input[3] + "' " + status.toString());
                            } else if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' " + status.toString());
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Method " + status.toString());
                            } else {
                                System.out.println("Method " + input[3] + " renamed to " + input[4] + "from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "field": {
                            //The next part of the command is field
                            if (input.length != 5) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameField(input[2], input[3], input[4]);
                            if (status == Controller.STATUS_CODES.FIELD_NOT_FOUND) {
                                System.out.println("Field '" + input[3] + "' " + status.toString());
                            } else if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' " + status.toString());
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Field " + status.toString());
                            } else {
                                System.out.println("Field " + input[3] + " renamed to " + input[4] + "from class " + input[2] + "!");
                            }
                            break;
                        }
                        case "parameter": {
                            if (input.length != 6) {
                                System.out.println("Command is an invalid length. Please try again");
                                break;
                            }
                            //Retrieves the status code for the method and displays results
                            Controller.STATUS_CODES status = Controller.renameParam(input[2], input[3], input[4], input[5]);
                            if (status == Controller.STATUS_CODES.PARAM_NOT_FOUND) {
                                System.out.println("Parameter '" + input[4] + "' does not exist in method '" + input[3] + "'");
                            } else if (status == Controller.STATUS_CODES.METHOD_NOT_FOUND) {
                                System.out.println("Method '" + input[3] + "' " + status.toString());
                            } else if (status == Controller.STATUS_CODES.OBJ_NOT_FOUND) {
                                System.out.println("Class '" + input[2] + "' " + status.toString());
                            } else if (status != Controller.STATUS_CODES.SUCCESS) {
                                System.out.println("Parameter " + status.toString());
                            } else {
                                System.out.println("Parameter " + input[4] + " renamed to " + input[5]);
                            }
                            break;
                        }
                        default: {
                            System.out.println("Your command is not valid please enter a new command.");
                            break;
                        }
                    }
                    break;
                case "save":
                    //The next part of the command is save
                    if (input.length != 2) {
                        System.out.println("Command is an invalid length. Please try again");
                        break;
                    }
                    CLI.save(input[1]);
                    break;
                case "load":
                    if (input.length != 2) {
                        System.out.println("Command is an invalid length. Please try again");
                        break;
                    }
                    //The next part of the command is load
                    CLI.load(input[1]);
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
                            System.out.println("What is the name of the file you would like to overwrite / create?");
                            System.out.print("Filename: ");
                            CLI.save(kb.nextLine());
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

        }

    }

    /**
     * This method takes in an arraylist of strings and is able to print it out line after line.
     * This method is used for many other methods that return String arrays full of data
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

    public static void printStringListNumbered(final String[] list) {
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


    public static void save(String fileName) {
        if (Controller.getCreatedClassesSize() == 0) {
            System.out.println("Nothing to save");
        } else if (Controller.save(fileName) == Controller.STATUS_CODES.SUCCESS) {
            System.out.println("Your progress has been saved!");
        } else if (Controller.save(fileName) == Controller.STATUS_CODES.EXCEPTION) {
            System.out.println("Save name specified is invalid");
        }
    }

    public static void load(String fileName) {
        if (Controller.load(fileName) == Controller.STATUS_CODES.SUCCESS) {
            System.out.println("Your previous save has been loaded!");
        } else if (Controller.load(fileName) == Controller.STATUS_CODES.FILE_NOT_FOUND) {
            System.out.println(Controller.STATUS_CODES.FILE_NOT_FOUND);
        } else if (Controller.load(fileName) == Controller.STATUS_CODES.EMPTY_FILE) {
            System.out.println(Controller.STATUS_CODES.EMPTY_FILE);
        }
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
