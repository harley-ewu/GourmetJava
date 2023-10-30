package src.main.java;

//The "view" when the program is in command line interface (CLI) mode

import java.util.Scanner;

public class CLI {
    //Scanner to read user input
    Scanner kb = new Scanner(System.in);

    // This method will take class name input from the user in CLI mode and send it
    // to the controller
    // The controller will return true or false based on whether or not the class
    // was created
    public void addClass() {
        System.out.println("What would you like to name your class?");
        System.out.print("Class Name:");
        String name = kb.nextLine();
        System.out.println("What is the class's type?");

        ClassBox.printClassTypes();

        String type = kb.nextLine();
        //Test to see if adding was sucessful
        if (Controller.addClass(name, type)) {
            System.out.println("Class created!");
        } else {
            System.out.println("Class already exists or bad inputs were provided. Please try again.");
        }
    }

    // This method will take class inex input from the user in CLI mode and send it
    // to the controller
    // The controller will return true or false based on whether or not the class
    // was deleted
    public void deleteClass() {
        System.out.println("What index do you want to remove?");
        Controller.listClasses();
        System.out.print("Class Index:");
        int input = Integer.parseInt(kb.nextLine());

        //Test to see if deleting was sucessful
        if (Controller.deleteClass(input)) {
            System.out.println("Class deleted!");
        } else {
            System.out.println("Bad input was provided. Failed to delete class.");
        }
    }

    // This method will take the index of the class as input from the user in CLI
    // mode and send it to the controller
    // The controller will return true or false based on whether or not the class
    // was renamed
    public void renameClass() {
        System.out.println("What index do you want to rename?");
        Controller.listClasses();
        System.out.print("Class Index:");
        int num = Integer.parseInt(kb.nextLine());

        System.out.println("What would you like to rename your class?");
        System.out.print("New Class Name:");
        String name = kb.nextLine();

        //Test to see if rename was sucessful
        if (Controller.renameClass(num, name)) {
            System.out.println("Class renamed!");
        } else {
            System.out.println("Bad input was provided. Failed to rename class.");
        }
    }

    // This method will take both indexs for both classes as input from the user in
    // CLI mode and send it to the controller
    // The controller will return true or false based on whether or not the
    // relationship was created
    public void addRelationship() {
        Controller.listClasses();
        System.out.println("What is the index of the first class you want to have a relationship? (The lower/to class, e.g this implements the other class)");
        System.out.print("Class 1 Index:");
        int index1 = Integer.parseInt(kb.nextLine());

        System.out.println("What is the index of the second class you want to have a relationship?(The higher/from class, e.g the other class implements this)");
        System.out.print("Class 2 Index:");
        int index2 = Integer.parseInt(kb.nextLine());

        Relationship.printRelationshipTypes();
        System.out.println("Please select an option for the relationship type by number");
        System.out.print("Type #:");
        int type = Integer.parseInt(kb.nextLine());

        //Test to see if adding relationship was sucessful
        if (Controller.addRelationship(index1, index2, type)) {
            System.out.println("Relationship created!");
        } else {
            System.out.println("Bad inputs for relationship, creation cancelled");
        }
    }

    public void deleteRelationship() {
        Controller.listClasses();
        System.out.println("What is the index of the first class of this relationship?");
        System.out.print("Class Index:");
        int index1 = Integer.parseInt(kb.nextLine());
        System.out.println("What is the index of the second class?");
        System.out.print("Class Index:");
        int index2 = Integer.parseInt(kb.nextLine());
        System.out.println("Are you sure you want to delete this relationship? Please write yes or no.");
        System.out.print("yes/no:");
        String answer = kb.nextLine();

        //Test to see if deleting relationship was sucessful
        if (Controller.deleteRelationship(index1, index2, answer)) {
            System.out.println("Relationship created!");
        } else {
            System.out.println("Bad inputs for relationship, deletion cancelled.");
        }
    }

}
