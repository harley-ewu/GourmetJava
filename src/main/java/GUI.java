package src.main.java;
    // Java program to construct
// Menu bar to add menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class GUI extends JFrame {
    // Creates a dropdown style menu framework at the top of the frame
    static JMenuBar mainMenu;

    // Creates individual dropdown menus for each category within the overall menu
    static JMenu parameterDropdown, displayDropdown,classDropdown,attributeDropdown,relationshipDropdown,saveLoadDropdown,helpDropdown;

    // Individual menu items/buttons under their individual category menus
    static JMenuItem display,addClass,deleteClass,renameClass,addAtt,delAtt,renameAtt,addRelation,delRelation,save,load,help,addPar, delPar, renPar;


    //creates a frame to be the main, base window to hold the entirety of the GUI
    static JFrame guiWindow;

    public static void startGUIMenu(){

        guiWindow = new JFrame("UML Editor");

        // create a menubar
        mainMenu = new JMenuBar();

        displayDropdown = new JMenu("Display");
        display = new JMenuItem(new AbstractAction("Refresh") {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayGUI();
            }
        });
        displayDropdown.add(display);


        classDropdown = new JMenu("Class");
        addClass = new JMenuItem(new AbstractAction("Add Class") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //popup box with user input fields
                //submit button sends shit to Controller.addClass()
                //Draw new box (either entire screen refresh or just draw new box)
                //done

                /* 
                       maybe test:
                            popup box that takes one integer (as string)
                            String -> int
                            pass that int to System.exit status code
                 */
                System.exit(4);
            }
        });
        deleteClass = new JMenuItem(new AbstractAction("Delete Class") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(12);
            }
        });
        renameClass = new JMenuItem(new AbstractAction("Rename Class") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(13);
            }
        });
        classDropdown.add(addClass);
        classDropdown.add(deleteClass);
        classDropdown.add(renameClass);

        attributeDropdown = new JMenu("Attribute");
        addAtt = new JMenuItem(new AbstractAction("Add Attribute") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(14);
            }
        });
        delAtt = new JMenuItem(new AbstractAction("Delete Attribute") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(15);
            }
        });
        renameAtt = new JMenuItem(new AbstractAction("Rename Attribute") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(16);
            }
        });
        attributeDropdown.add(addAtt);
        attributeDropdown.add(delAtt);
        attributeDropdown.add(renameAtt);

        parameterDropdown = new JMenu("Parameters");
        addPar = new JMenuItem(new AbstractAction("Add Parameter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(14);
            }
        });
        delPar = new JMenuItem(new AbstractAction("Delete Parameter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(15);
            }
        });
        renPar = new JMenuItem(new AbstractAction("Rename Parameter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(16);
            }
        });
        parameterDropdown.add(addPar);
        parameterDropdown.add(delPar);
        parameterDropdown.add(renPar);

        relationshipDropdown = new JMenu("Relationship");
        addRelation = new JMenuItem(new AbstractAction("Add Relationship") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        delRelation = new JMenuItem(new AbstractAction("Delete Relationship") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        relationshipDropdown.add(addRelation);
        relationshipDropdown.add(delRelation);

        saveLoadDropdown = new JMenu("Save/Load");
        save = new JMenuItem(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        load = new JMenuItem(new AbstractAction("Load") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        saveLoadDropdown.add(save);
        saveLoadDropdown.add(load);

        helpDropdown = new JMenu("Help");
        /*Maybe take out the dropdown part of "help" since there's just one component.
         that isn't exactly needed to be repeated if we can get around it*/
        help = new JMenuItem(new AbstractAction("Help") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        helpDropdown.add(help);

        // add individual dropdown menus to menu bar
        mainMenu.add(displayDropdown);
        mainMenu.add(classDropdown);
        mainMenu.add(attributeDropdown);
        mainMenu.add(parameterDropdown);
        mainMenu.add(relationshipDropdown);
        mainMenu.add(saveLoadDropdown);
        mainMenu.add(helpDropdown);

        // add menubar to our main GUI display frame
        guiWindow.setJMenuBar(mainMenu);

        // set the size of the frame
        guiWindow.setSize(1000, 800);
        guiWindow.setPreferredSize(new Dimension(1000, 800));
        guiWindow.setResizable(false);
        guiWindow.setVisible(true);
        Main.mainContainer.pack();
        guiWindow.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){
                Main.gview = false;
            }
        });
        displayGUI();
        //Want to stay idle if CLI view is not there; need to keep program running
        Controller.addClass("shit",1);
        Controller.addField("shit", "crap",1,"dumb fucking program");
        LinkedList<String> params = new LinkedList<>();
        params.add("AHH");
        params.add("i'd rather be sleeping");
        Controller.addMethod("shit","I hate this",1,"FUCK",params);
        Controller.addField("shit","iHateThis",1,"please");

        Controller.addClass("fuck",1);
        LinkedList<String> params2 = new LinkedList<>();
        params2.add("ugh");
        params2.add("i'd rather be dead");
        Controller.addMethod("fuck","I hate this",1,"FUCK",params);
        Controller.addField("fuck","weeeeeeeee",1,"snore");
        while(!Main.cview) {
            ;
        }
    
    }
    public static void displayGUI(){
        guiWindow.add(new ShapeDrawing());
        guiWindow.setVisible(true);
    }
    public static class ShapeDrawing extends JComponent{
        public ShapeDrawing(){
            super();
        }
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            //Spacing out based on the number of classes
            int numberOfClasses = Controller.getCreatedClassesSize();
            // number of total spaces including class boxes and empty spaces in between
            // If there are 3 classes, it has 3 spots with one classbox space between each box
            int totalSpace = (2 * numberOfClasses) + 1;
            //Overall width of panel divided by spots
            int spaceWidth = 1000/totalSpace;
            //Current x coordinate
            int curx = spaceWidth;
            //Stores coordinates of each class when printed for relationship printing
            LinkedList<Integer> coords = new LinkedList<>();
            //Goes through all classes and prints them
            String[] classNames = Controller.listClasses();
            //this will stick every other class on an upper row, and the ones in between on a lower row
            for(int i = 0; i < numberOfClasses; i++){
                if(i % 2 == 0){
                    drawClass(classNames[i], curx, 200, g2);
                    coords.add(curx);
                    coords.add(200);
                }
                else{
                    drawClass(classNames[i], curx, 400, g2);
                    coords.add(curx);
                    coords.add(400);
                }
                curx += (1.5 * spaceWidth);
            }
            String[] classes = Controller.listClasses();
            //Prints the line for each relationship
            for (int i = 0; i < Controller.getCreatedClassesSize(); i++){
                String[][] relationships = Controller.listRelationships();
                for(int j = 0; i<relationships[i].length; j++){
                    //For each relationship, retrieve the coordinates of each, and draw a line between them
                    int class1XIndex = i * 2; //index of where the coordinates are in the array
                    int class1YIndex = class1XIndex + 1;
                    String[] relationship = relationships[i][j].split(" ");
                    int class2Index = -1;
                    for(int k = 0;k<classes.length;k++){
                        if (relationship[2].equals(classes[k])){
                            class2Index = k;
                        }
                    }
                    int class2XIndex = class2Index * 2;

                    int class2YIndex = class2XIndex + 1;
                    int class1XCoords = coords.get(class1XIndex);
                    int class1YCoords = coords.get(class1YIndex);
                    int class2XCoords = coords.get(class2XIndex);
                    int class2YCoords = coords.get(class2YIndex);
                    class1XCoords += 10;
                    //scooches the line over to the right a bit so it isn't on the corner
                    class2XCoords += 10;
                    g2.drawLine(class1XCoords, class1YCoords, class2XCoords, class2YCoords);
                    //Display the relationship type at the line's midpoint
                    //Finds midpoint and then prints the relationship string. "Aggregates" for example

                    g2.drawString(relationship[1], ((class1XCoords + class2XCoords)/2), ((class1YCoords + class2YCoords)/2));

                }
            }
        }

        //Draws the class boxes
        public void drawClass(String className, int x, int y, Graphics2D g2){
            //number of fields and methods
            String[][] classDetails = Controller.listAllClassDetails(className);
            int height = 15 * (classDetails[Controller.DETAILS_METHODS].length + classDetails[Controller.DETAILS_FIELDS].length+2);
            int width = className.length();
            //Set width to largest of the attribute toStrings
            for(int i = 0; i < classDetails[Controller.DETAILS_METHODS].length; i++){
                if(classDetails[1][i].length() > width){
                    width = classDetails[Controller.DETAILS_METHODS][i].length();
                }
            }
            for(int i = 0; i < classDetails[Controller.DETAILS_FIELDS].length; i++){
                if(classDetails[2][i].length() > width){
                    width = classDetails[Controller.DETAILS_FIELDS][i].length();
                }
            }
            //the 8 here is just for good spacing
            width *= 8;
            //Outer rectangle
            g2.drawRect(x,y,width,height);
            //Write Class name
            g2.drawString(className, x+5, y+15);
            //Draw line under the name
            g2.drawLine(x,y + 15,x+width, y+15);
            //moves down twice the spacing of above
            y = y + 30;
            //For each attribute, print the gui toString
            for(int i=0; i < classDetails[Controller.DETAILS_METHODS].length; i++){
                g2.drawString(classDetails[Controller.DETAILS_METHODS][i], x+10, y);
                //moves down 15
                y += 15;
            }
            for(int i=0; i <classDetails[Controller.DETAILS_FIELDS].length; i++){
                g2.drawString(classDetails[Controller.DETAILS_FIELDS][i], x+10, y);
                y += 15;
            }
        }
    }

}

// make the buttons work
// to display, change the actual display button under Display to open CLI
// we need to get the actual class boxes listed
// all of a class's fields, methods, and relationships are added to the box as well
