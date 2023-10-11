import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class UserInterface {
    private ArrayList<ClassBox> createdClasses;
    public Scanner kb;
    //Display all program options, choose from list, call other method based on choice
    public UserInterface(){
        createdClasses = new ArrayList<>();
        kb = new Scanner(System.in);

    }
    //recall menu at end if not
    public void menu(String input){
        //get user input of 1-15
        //call io method below
        //io method calls actual method in other classes
        printMenu();
        
         if(input.equals("1")){
            listClasses();
        }else if(input.equals("2")){
            listClass();
        }else if(input.equals("3")){
            addClass();
        }else if(input.equals("4")){
            deleteClass();
        }else if(input.equals("5")){
            renameClass();
        }else if(input.equals("6")){
            addAttribute();
        }else if(input.equals("7")){
            deleteAttribute();
        }else if(input.equals("8")){
            renameAttribute();
        }else if(input.equals("9")){
            listRelationships();
        }else if(input.equals("10")){
            addRelationship();
        }else if(input.equals("11")){
            deleteRelationship();
        }else if(input.equals("12")){
            save();
        }else if(input.equals("13")){
            load();
        }else if(input.equals("14")){
            help();
        }else if(input.equals("15")){
            menuExit();    
        }else{
            System.out.println("That is not a valid input. Please try again");
        }
    }

    //PrintMenu will display the menu options and prompt the user to choose a corresponding number on the menu
    public void printMenu(){
        System.out.println("Please choose a number from the following options below");
        System.out.println("1.) List classes");
        System.out.println("2.) List class details");
        System.out.println("3.) Add a class");
        System.out.println("4.) Delete a class");
        System.out.println("5.) Rename a class");
        System.out.println("6.) Add an attribute");
        System.out.println("7.) Delete an attribute");
        System.out.println("8.) Rename an attribute");
        System.out.println("9.) List relationships");
        System.out.println("10.) Save");
        System.out.println("11.) Load");
        System.out.println("12.) Help");
        System.out.println("13.) Exit");
    }


    // Creates a new Classbox object and adds to to the arraylist createdClasses
    //Rachael
    //Allows the user to name their class, then adds it to the list of created classes
    public void addClass(){
        System.out.println("What would you like to name your class?");
        String name = kb.nextLine();

        ClassBox newClass = new ClassBox(name, null, null, null);
        createdClasses.add(newClass);
        System.out.println("Class created!");
    }

    //Removes class from createdClasses
    //Rachael
    //Takes in the index of the item user wants removed from the list and removes it
    public void deleteClass(){
        if(createdClasses.isEmpty()){
            System.out.println("Nothing to delete!");
        }else{
        System.out.println("What index do you want to remove?");
        listClasses();
        int input = kb.nextInt();
        if(input > 0){
            input -= 1;
            createdClasses.remove(input);
            System.out.println("Class deleted");
        }else if(input <= 0){
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
        }else{
        System.out.println("What index do you want to rename?");
        listClasses();
        int input = kb.nextInt();
        if(input > 0){
            input -= 1;
            System.out.println("What would you like to rename your class?");
            Scanner temp = new Scanner(System.in);
            String name = temp.nextLine();
            createdClasses.get(input).renameClass(name);
            temp.close();
            System.out.println("Class renamed!");
            listClasses();
        }else if(input <= 0){
            System.out.println("Invalid input. Try again");
        }
        
        }
    }
    public void addRelationship(){
        System.out.println("What is the index of the first class you want to have a relationship?");
        int index1 = kb.nextInt();
        System.out.println("What is the index of the second class you want to have a relationship?");
        Scanner kb2 = new Scanner(System.in);
        int index2 = kb2.nextInt();
        kb2.close();
        if(index1 < 0 || index2 < 0 || index1 >= createdClasses.size() || index2 >= createdClasses.size()){
            System.out.println("That's not a vaild option. Please try again");

        }else{
            System.out.println("Relationship created!");
            createdClasses.get(index1).addRelationship(null, createdClasses.get(index1).getName(), createdClasses.get(index2).getName(), null);
        }
    }
    //confirm?
    //Delete a relationship between two classes
    public void deleteRelationship(){
        //Add an else for if one of the names isn't found

        System.out.println("What is the name of the first class of this relationship?");
        String className = kb.nextLine();
        for(int i = 0; i < createdClasses.size(); i++){
            if(createdClasses.get(i).getName().equals(className)){
                System.out.println("What is the name of the second");
                String className2 = kb.nextLine();
                System.out.println("Are you sure you want to delete this relationship? Please write yes or no.");
                String answer = kb.nextLine();
                if(answer.toLowerCase().equals("yes")){
                    createdClasses.get(i).deleteRelationship(className2);
                    System.out.println("Relationship Deleted!");
                }
            }
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
        for(int i = 0; i < createdClasses.size(); i++){
            if(createdClasses.get(i).getName().toLowerCase().equals(className)){
                //check if attributes is empty, if not do below
                System.out.println("Which attribute would you like to delete?");
                // list attributes?
                String attribute = kb.nextLine();
                //find attribute using loop
                System.out.println("Are you sure you want to delete " + attribute + "? Please enter yes or no.");
                String answer = kb.nextLine().toLowerCase();
                if(answer.equals("yes")){
                    //delete the relationship
                }
                else if (answer.equals("no")) {
                    System.out.println("Canceled");
                }
                else{
                    System.out.println("That is not a valid input");
                }
            }
        }
    }

    public void renameAttribute(){

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
    public void listRelationships(){

    }
    //Allows the user to choose what Classbox item they want to see in detail
    //Rachael
    //Takes input from user on what index from the list they want to see then calls a toString for that object
    public void listClass(){
        System.out.println("What index do you want to see?");
        listClasses();
        int input = kb.nextInt();
        if(input > 0){
            System.out.println(createdClasses.get(input -1).toString());
        }else if(input <= 0){
            System.out.println("Invalid input. Try again");
        }

    }
    public void help(){

    }
    //confirm?
    public void menuExit(){
        System.out.println("Type \"c\" to confirm: ");
        if(!(kb.nextLine().equals("c"))){
            //returns to menu loop
            return;
        }
        System.out.println("bye!");
        exit(0);
    }
}
