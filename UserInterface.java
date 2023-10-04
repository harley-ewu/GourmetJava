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
            listClass(kb);
        }else if(input.equals("3")){
            addClass();
        }else if(input.equals("4")){
            deleteClass(kb);
        }else if(input.equals("5")){
            renameClass(kb);
        }else if(input.equals("6")){
            addAttribute(kb);
        }else if(input.equals("7")){
            deleteAttribute(kb);
        }else if(input.equals("8")){
            renameAttribute(kb);
        }else if(input.equals("9")){
            listRelationships();
        }else if(input.equals("10")){
            save(kb);
        }else if(input.equals("11")){
            load(kb);
        }else if(input.equals("12")){
            help();
        }else if(input.equals("13")){
            menuExit(kb);    
        }else{
            System.out.println("That is not a valid input. Please try again");
        }
    }
    public void addClass(){
        ClassBox newClass = new ClassBox(null, null, null, null);
        createdClasses.add(newClass);
    }
    //confirm?
    public void deleteClass(Scanner kb){

    }
    public void renameClass(Scanner kb){

    }
    public void addRelationship(Scanner kb){

    }
    //confirm?
    public void deleteRelationship(Scanner kb){

    }
    public void addAttribute(Scanner kb){

    }
    //confirm?
    public void deleteAttribute(Scanner kb){

    }
    public void renameAttribute(Scanner kb){

    }
    //export createdClasses
    //confirm?
    public void save(Scanner kb){

    }
    //import/set createdClasses
    //confirm?
    public void load(Scanner kb){

    }
    public void listClasses(){

    }
    public void listRelationships(){

    }
    public void listClass(Scanner kb){

    }
    public void help(){

    }
    //confirm?
    public void menuExit(Scanner kb){
        System.out.println("Type \"c\" to confirm: ");
        if(!(kb.nextLine().equals("c"))){
            //returns to menu loop
            return;
        }
        System.out.println("bye!");
        exit(0);
    }
}
