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
    public void addClass(){
        System.out.println("What would you like to name your class?");
        String name = kb.nextLine();
        ClassBox newClass = new ClassBox(name, null, null, null);
        createdClasses.add(newClass);
        System.out.println("Class created!");
    }
    //confirm?
    public void deleteClass(){
        if(createdClasses.isEmpty()){
            System.out.println("Nothing to delete!");
        }else{
        System.out.println("What index do you want to remove?");
        //listClasses();
        int input = kb.nextInt();
        createdClasses.remove(input);
        System.out.println("Class deleted");
        }

    }
    public void renameClass(){

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
        for(int i = 0; i < createdClasses.size() - 1; i++){
            System.out.println(createdClasses.get(i).getName());
        }
    }
    public void listRelationships(){

    }
    public void listClass(){
        
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
