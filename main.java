import java.util.ArrayList;
public class main {
    public static void main(String args[]){
        UserInterface m = new UserInterface();
        System.out.println("Welcome to Gourmet Java's UML Editor!");

        String input;
        while(true) {
            input = m.kb.nextLine();
            m.menu(input);
        }
        
       }
}
