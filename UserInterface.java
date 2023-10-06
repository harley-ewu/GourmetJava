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
            save();
        }else if(input.equals("11")){
            load();
        }else if(input.equals("12")){
            help();
        }else if(input.equals("13")){
            menuExit();    
        }else{
            System.out.println("That is not a valid input. Please try again");
        }
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

    }
    //confirm?
    public void deleteRelationship(){

    }
    public void addAttribute(){

    }
    //confirm?
    public void deleteAttribute(){

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
