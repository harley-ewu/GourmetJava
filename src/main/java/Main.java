package src.main.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface m = new UserInterface();
        //System.out.println("Welcome to Gourmet Java's UML Editor!");
        System.out.println("Welcome to Gourmet Java's UML Editor!");
        System.out.println("Let's get started!");
        System.out.println("When the program opens, a menu will display with options. A number will correspond to each option.");
        System.out.println("When you see an option you want, select the corresponding number and hit the enter key.");
        System.out.println("Answer the prompts that follow, hitting enter after each response, and we'll take care of the rest!");
        System.out.println("Happy editing!");
        Scanner kb = new Scanner(System.in);
        String input = "";
        while (!input.equals("7")) {
            m.printMenu();
            input = kb.nextLine();
            m.menu(input);
        }
        System.out.println("Program Closed! Bye!");
    }

       // This is a test

}
