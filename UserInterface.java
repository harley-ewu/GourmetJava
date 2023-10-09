import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
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
    public void addClass(Scanner kb){

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
    public void save() {
        Gson gson = new Gson();
        FileWriter converter = null;
        try {
            converter = new FileWriter("SavedFile.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String test = "dogs";
        gson.toJson(test, converter);

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
