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
    public void menu(Scanner kb){
        //get user input of 1-15
        //call io method below
        //io method calls actual method in other classes
    }
    public void addClass(kb){

    }
    //confirm?
    public void deleteClass(kb){

    }
    public void renameClass(kb){

    }
    public void addRelationship(kb){

    }
    //confirm?
    public void deleteRelationship(kb){

    }
    public void addAttribute(kb){

    }
    //confirm?
    public void deleteAttribute(kb){

    }
    public void renameAttribute(kb){

    }
    //export createdClasses
    //confirm?
    public void save(kb){

    }
    //import/set createdClasses
    //confirm?
    public void load(kb){

    }
    public void listClasses(){

    }
    public void listRelationships(){

    }
    public void listClass(kb){

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
