import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface m = new UserInterface();
        System.out.println("Welcome to Gourmet Java's UML Editor!");
        Scanner kb = new Scanner(System.in);
        String input = "";
        while (!input.equals("15")) {
            m.printMenu();
            input = kb.nextLine();
            m.menu(input);
        }
        System.out.println("bye!");
    }

       // This is a test

}
