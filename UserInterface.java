import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class UserInterface {
    private final ArrayList<ClassBox> createdClasses;
    private final Scanner kb;
    //Display all program options, choose from list, call other method based on choice
    public UserInterface(){
        createdClasses = new ArrayList<>();
        kb = new Scanner(System.in);
    }
    //recall menu at end if not
    public void menu(String input){
        int input2 = 0;
        //get user input of 1-15
        //call io method below
        //io method calls actual method in other classes

        switch (input) {
            case "1" -> {
                if(this.createdClasses.isEmpty()){
                    System.out.println("Nothing to display! Please make a class first");
                }
                else {
                    System.out.println("Please choose a number from the options below: ");
                    System.out.println("1.) Display Classes");
                    System.out.println("2.) Display Class Details");
                    System.out.println("3.) Display Relationships");
                    System.out.println("4.) Back");
                    input2 = Integer.parseInt(kb.nextLine());
                    if (input2 == 1) {
                        listClasses();
                    } else if (input2 == 2) {
                        listClass();
                    } else if (input2 == 3) {
                        listRelationships();
                    } else if (input2 == 4) {
                        return;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                }
            }
            case "2" -> {
                System.out.println("Please choose a number from the options below: ");
                System.out.println("1.) Add Class");
                System.out.println("2.) Remove Class");
                System.out.println("3.) Rename Class");
                System.out.println("4.) Back");
                input2 = Integer.parseInt(kb.nextLine());
                if (input2 == 1) {
                    addClass();
                } else if (input2 == 2) {
                    deleteClass();
                } else if (input2 == 3) {
                    renameClass();
                } else if (input2 == 4) {
                    return;
                } else {
                    System.out.println("Invalid input, please try again");
                }
            }
            case "3" -> {
                if (this.createdClasses.isEmpty()) {
                    System.out.println("Please create a class first");
                } else {
                    System.out.println("Please choose a number from the options below: ");
                    System.out.println("1.) Add Attribute");
                    System.out.println("2.) Remove Attribute");
                    System.out.println("3.) Rename Attribute");
                    System.out.println("4.) Back");
                    input2 = Integer.parseInt(kb.nextLine());
                    if (input2 == 1) {
                        addAttribute();
                    } else if (input2 == 2) {
                        deleteAttribute();
                    } else if (input2 == 3) {
                        renameAttribute();
                    } else if (input2 == 4) {
                        return;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                }
            }
            case "4" -> {
                if (this.createdClasses.size()<2) {
                    System.out.println("Please create 2 classes first");
                } else {
                    System.out.println("Please choose a number from the options below: ");
                    System.out.println("1.) Add Relationship");
                    System.out.println("2.) Remove Relationship");
                    System.out.println("3.) Back");
                    input2 = Integer.parseInt(kb.nextLine());
                    if (input2 == 1) {
                        addRelationship();
                    } else if (input2 == 2) {
                        deleteRelationship();
                    } else if (input2 == 3) {
                        return;
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                }
            }
            case "5" -> {
                System.out.println("Please choose a number from the options below: ");
                System.out.println("1.) Save");
                System.out.println("2.) Load");
                System.out.println("3.) Back");
                input2 = Integer.parseInt(kb.nextLine());
                if (input2 == 1) {
                    if (this.createdClasses.isEmpty()) {
                        System.out.println("Nothing to save!");
                    } else {
                        save();
                    }
                } else if (input2 == 2) {
                    load();
                } else if (input2 == 3) {
                    return;
                } else {
                    System.out.println("Invalid input, please try again");
                }
            }
            case "6" -> help();
            case "7" -> {
                return;
            }
            default -> System.out.println("That is not a valid input. Please try again");
        }
    }

    //PrintMenu will display the menu options and prompt the user to choose a corresponding number on the menu
    public void printMenu(){
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
    //Rachael
    //Allows the user to name their class, then adds it to the list of created classes
    public void addClass(){
        System.out.println("What would you like to name your class?");
        String name = kb.nextLine();
        System.out.println("What is the class's type?");
        
        ClassBox.printClassTypes();

        int result = 0;
        String type = kb.nextLine();
       
        try{
            result = Integer.parseInt(type);
        }catch(Exception e){
            System.out.print("Input is not valid. Please try again.");
            return;
        }

        ClassBox newClass = new ClassBox(name, result);
        createdClasses.add(newClass);
        System.out.println("Class created!");
    
    }

    //Removes class from createdClasses
    //Rachael
    //Takes in the index of the item user wants removed from the list and removes it
    public void deleteClass(){
        if(createdClasses.isEmpty()){
            System.out.println("Nothing to delete!");
        }
        else{
            System.out.println("What index do you want to remove?");
            listClasses();
            int input = Integer.parseInt(kb.nextLine());
            if(input > 0&&input<=this.createdClasses.size()){
                ClassBox clas = createdClasses.get(input-1);
                for(ClassBox otherClass: this.createdClasses){
                    if(!(otherClass.equals(clas))) {
                        for (Relationship r : otherClass.getRelationships()) {
                            if (r.getTo().equals(clas)) {
                                otherClass.deleteRelationship(clas);
                            }
                        }
                    }
                }
                this.createdClasses.remove(clas);
                System.out.println("Class deleted");
            }
            else{
                System.out.println("Invalid input. Try again");
            }
        }
    }
    //Renames a classbox item that has already been created
    //Rachael
    //Takes in the index of the item they want renamed, then asks them to type in a new name
    public void renameClass(){
         if(createdClasses.isEmpty()){
            System.out.println("Nothing to rename!");
         }
         else{
            System.out.println("What index do you want to rename?");
            listClasses();
            int num = Integer.parseInt(kb.nextLine());
            if(num > 0&&num<=this.createdClasses.size()){
                int index = num-1;
                System.out.println("What would you like to rename your class?");
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
    public void addRelationship(){
        System.out.println("What is the index of the first class you want to have a relationship? (The from class)");
        int index1 = Integer.parseInt(kb.nextLine());
        System.out.println("What is the index of the second class you want to have a relationship?(The to class)");
        int index2 = Integer.parseInt(kb.nextLine());
        Relationship.printRelationshipTypes();
        System.out.println("Please select an option for the relationship type by number");
        int num = Integer.parseInt(kb.nextLine());
        if(index1 < 1 || index2 < 1 || index1 > createdClasses.size() || index2 > createdClasses.size()){
            System.out.println("Thats not a vaild option. Please try again");

        }else{
            System.out.println("Relationship created!");
            createdClasses.get(index1-1).addRelationship(createdClasses.get(index2-1), num);
        }
    }
    //confirm?
    //Delete a relationship between two classes
    public void deleteRelationship(){
        //Add an else for if one of the names isn't found
        ClassBox c1=null, c2 = null;
        System.out.println("What is the name of the first class of this relationship?");
        String className = kb.nextLine();
        System.out.println("What is the name of the second");
        String className2 = kb.nextLine();
        System.out.println("Are you sure you want to delete this relationship? Please write yes or no.");
        String answer = kb.nextLine();
        if(answer.equalsIgnoreCase("yes")) {
            for (ClassBox createdClass : createdClasses) {
                if (createdClass.getName().equals(className)) {
                    c1 = createdClass;
                }
                if (createdClass.getName().equals(className2)) {
                    c2 = createdClass;
                }
            }
            if(c1==null&&c2==null){
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
                c1.deleteRelationship(c2);
            }
        }
        else {
            System.out.println("No relationship deleted");
        }

    } //End of deleteRelationship


    public void addAttribute(){
        // possibly change to take in a classbox, with prompt in menu
        //check for if name doesn't exist
        //Not completed

        System.out.println("What is the name of the class you would like to add an attribute to?");
        String className = kb.nextLine().toLowerCase();
        for(int i = 0; i < createdClasses.size(); i++){
            if(createdClasses.get(i).getName().toLowerCase().equals(className)){
                
            }
        }

    }


    //confirm?
    public void deleteAttribute(){
        System.out.println("What is the name of the class you'd like to remove an attribute from?");
        String className = kb.nextLine().toLowerCase();
        for (ClassBox createdClass : createdClasses) {
            if (createdClass.getName().equalsIgnoreCase(className)) {
                //check if attributes is empty, if not do below
                System.out.println("Which attribute would you like to delete?");
                // list attributes?
                String attribute = kb.nextLine();
                //find attribute using loop
                System.out.println("Are you sure you want to delete " + attribute + "? Please enter yes or no.");
                String answer = kb.nextLine().toLowerCase();
                if (answer.equals("yes")) {
                    //delete the attribute
                    createdClass.deleteAttribute(attribute);
                } else if (answer.equals("no")) {
                    System.out.println("Canceled");
                } else {
                    System.out.println("That is not a valid input");
                }
            }
        }
    }

    public void renameAttribute(){

        if(createdClasses.isEmpty()){
            System.out.println("Nothing to rename!");
        }
        
        else{

            listClasses();            
            System.out.println("What index do you want to manipulate?"); 
            int input = Integer.parseInt(kb.nextLine());
            // may have indexing errors

            if(createdClasses.size() <= input && input >= 1){
                System.out.println("That is not a valid input. Please try again");
            }

            ClassBox cb = createdClasses.get(input-1); // may need to sort out later depending on indexing
            listAttributes(cb);

            System.out.println("Type the name of the attribute you want to rename");
            String attributeChoice = kb.nextLine().toLowerCase();

            System.out.println("What would you like to rename the attribute to?");
            String newName = kb.nextLine().toLowerCase();
            
            cb.renameAttribute(attributeChoice, newName);
            System.out.println("Attribute renamed!");
        }

    }

    //export createdClasses
    //confirm?
    public void save(){

    }
    //import/set createdClasses
    //confirm?
    public void load(){

    }
    public void listClasses(){
        System.out.println("Current Class list");
        for(int i = 0; i < createdClasses.size(); i++){
            System.out.println(i+1 + " . " + createdClasses.get(i).getName() + " ");
        }
    }

    public void listAttributes(ClassBox cb){
        System.out.println("Current Attribute list");
        LinkedList<Attribute> attributes = cb.getAttributes();
        for(int i = 0; i < attributes.size(); i++){
            System.out.println(i+1 + " . " + attributes.get(i).getName() + " ");
        }
    }

    public void listRelationships(){

    }
    //Allows the user to choose what Classbox item they want to see in detail
    //Rachael
    //Takes input from user on what index from the list they want to see then calls a toString for that object
    public void listClass(){
        System.out.println("What index do you want to see?");
        listClasses();
        int input = Integer.parseInt(kb.nextLine());
        if(input > 0&&input<=this.createdClasses.size()){
            System.out.println(createdClasses.get(input -1).toString());
        }
        else{
            System.out.println("Invalid input. Try again");
        }

    }
    public void help(){

    }
    //confirm?

}
