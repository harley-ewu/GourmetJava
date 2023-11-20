package j;

import java.util.Scanner;

public class Main {
    public static boolean cview = false;
    public static boolean gview = false;
    public static GUI mainContainer = new GUI();
    public static void main(String[] args) {
        //System.out.println("Welcome to Gourmet Java's UML Editor!");
        System.out.println("Welcome to Gourmet Java's UML Editor!");
        System.out.println("Let's get started!");
        System.out.println("When the program opens, a menu will display with options. A number will correspond to each option.");
        System.out.println("When you see an option you want, select the corresponding number and hit the enter key.");
        System.out.println("Answer the prompts that follow, hitting enter after each response, and we'll take care of the rest!");
        System.out.println("Happy editing!");
        System.out.println("Which view would you like to begin in? (\"c\" for command line or \"g\" for graphic interface");
        System.out.print("(c/g):");
        Scanner kb = new Scanner(System.in);
        char input = '.';
        while(!(input=='c'||input=='g')){
            input = kb.nextLine().charAt(0);
            if(input=='c'){
                cview = true;
                System.out.println("For instructions on how to use this program, please type the word \"help\"");
                CLI.menu();
            }
            else if (input=='g'){
                gview = true;
                GUI.startGUIMenu();
            }
            else{
                System.out.println("Invalid option! Please try again");
                System.out.print("(c/g):");
            }
        }
    }


}