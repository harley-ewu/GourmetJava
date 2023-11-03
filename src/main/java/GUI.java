package src.main.java;
    // Java program to construct
// Menu bar to add menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GUI extends JFrame implements ActionListener{
    // Creates a dropdown style menu framework at the top of the frame
    static JMenuBar mainMenu;

    // Creates individual dropdown menus for each category within the overall menu
    static JMenu displayDropdown,classDropdown,attributeDropdown,relationshipDropdown,saveLoadDropdown,helpDropdown;

    // Individual menu items/buttons under their individual category menus
    static JMenuItem display,addClass,deleteClass,renameClass,addAtt,delAtt,renameAtt,addRelation,delRelation,save,load,help;


    //creates a frame to be the main, base window to hold the entirety of the GUI
    static JFrame guiWindow;

    //public static void startGUIMenu(ArrayList<ClassBox> cc){
    public static void main(String[] args){
        GUI mainContainer = new GUI();

        guiWindow = new JFrame("UML Editor");

        // create a menubar
        mainMenu = new JMenuBar();

        displayDropdown = new JMenu("Display");
        display = new JMenuItem("Display");
        display.addActionListener(mainContainer);
        displayDropdown.add(display);
        classDropdown = new JMenu("Class");
        addClass = new JMenuItem("Add Class");
        deleteClass = new JMenuItem("Delete Class");
        renameClass = new JMenuItem("Rename Class");
        addClass.addActionListener(mainContainer);
        deleteClass.addActionListener(mainContainer);
        renameClass.addActionListener(mainContainer);
        classDropdown.add(addClass);
        classDropdown.add(deleteClass);
        classDropdown.add(renameClass);
        attributeDropdown = new JMenu("Attribute");
        addAtt = new JMenuItem("Add Attribute");
        delAtt = new JMenuItem("Delete Attribute");
        renameAtt = new JMenuItem("Rename Attribute");
        addAtt.addActionListener(mainContainer);
        delAtt.addActionListener(mainContainer);
        renameAtt.addActionListener(mainContainer);
        attributeDropdown.add(addAtt);
        attributeDropdown.add(delAtt);
        attributeDropdown.add(renameAtt);
        relationshipDropdown = new JMenu("Relationship");
        addRelation = new JMenuItem("Add Relationship");
        delRelation = new JMenuItem("Delete Relationship");
        addRelation.addActionListener(mainContainer);
        delRelation.addActionListener(mainContainer);
        relationshipDropdown.add(addRelation);
        relationshipDropdown.add(delRelation);
        saveLoadDropdown = new JMenu("Save/Load");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        save.addActionListener(mainContainer);
        load.addActionListener(mainContainer);
        saveLoadDropdown.add(save);
        saveLoadDropdown.add(load);
        helpDropdown = new JMenu("Help");
        /*Maybe take out the dropdown part of "help" since there's just one component.
         that isn't exactly needed to be repeated if we can get around it*/
        help = new JMenuItem("Help");
        helpDropdown.add(help);
        help.addActionListener(mainContainer);
        // add individual dropdown menus to menu bar
        mainMenu.add(displayDropdown);
        mainMenu.add(classDropdown);
        mainMenu.add(attributeDropdown);
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
        //TODO why use pack?
        // Is this code below just for testing purposes?
        mainContainer.pack();
        ArrayList<ClassBox> cc = new ArrayList<>();
        ClassBox c1 = new ClassBox("tim", 1);
        ClassBox c2 = new ClassBox("dave", 2);
        LinkedList<String> params = new LinkedList<>();
        params.add("String");
        params.add("int");
        c1.addField("name", Visibility.PROTECTED,"String");
        c1.addMethod("toString", Visibility.PUBLIC,"String", params);
        c1.addRelationship(c2, 2);
        //for(int i = 0; i<6; i++){
            cc.add(c1);
            cc.add(c2);
        //}
        displayGUI(guiWindow, cc);
    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("Add Class")){
            //I/o, then call method
        }
    }
    public static void displayGUI(JFrame guiWindow, ArrayList<ClassBox> createdClasses){
        guiWindow.add(new ShapeDrawing(createdClasses));
        guiWindow.setVisible(true);
    }
    public static class ShapeDrawing extends JComponent{
        ArrayList<ClassBox> createdClasses;
        public ShapeDrawing(ArrayList<ClassBox> createdClasses){
            super();
            this.createdClasses = createdClasses;
        }
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            //Spacing out based on the number of classes
            int numberOfClasses = createdClasses.size();
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
            //TODO
            //this will stick every other class on an upper row, and the ones in between on a lower row
            for(int i = 0; i < numberOfClasses; i++){
                if(i % 2 == 0){
                    drawClass(createdClasses.get(i), curx, 200, g2);
                    coords.add(curx);
                    coords.add(200);
                }
                else{
                    drawClass(createdClasses.get(i), curx, 400, g2);
                    coords.add(curx);
                    coords.add(400);
                }
                curx += (1.5 * spaceWidth);
            }
            //Prints the line for each relationship
            for (int i = 0; i < createdClasses.size(); i++){
                for(Relationship r: createdClasses.get(i).getRelationships()){
                    //For each relationship, retrieve the coordinates of each, and draw a line between them
                    int class1XIndex = i * 2; //index of where the coordinates are in the array
                    int class1YIndex = class1XIndex + 1;
                    int class2 = createdClasses.indexOf(r.getFrom());
                    int class2XIndex = class2 * 2;
                    int class2YIndex = class2XIndex + 1;
                    int class1XCoords = coords.get(class1XIndex);
                    int class1YCoords = coords.get(class1YIndex);
                    int class2XCoords = coords.get(class2XIndex);
                    int class2YCoords = coords.get(class2YIndex);
                    class1XIndex += 10;
                    //scooches the line over to the right a bit so it isn't on the corner
                    class2XCoords += 10;
                    g2.drawLine(class1XCoords, class1YCoords, class2XCoords, class2YCoords);
                    //Display the relationship type at the line's midpoint
                    //Finds midpoint and then prints the relationship string. "Aggregates" for example
                    g2.drawString(r.getType(), ((class1XCoords + class2XCoords)/2), ((class1YCoords + class2YCoords)/2));
                }
            }
        }

        //Draws the class boxes
        public void drawClass(ClassBox c, int x, int y, Graphics2D g2){
            //number of fields and methods
            int height = 15 * (c.getFields().size() + c.getMethods().size()+2);
            int width = c.getName().length();
            //Set width to largest of the attribute toStrings
            for(int i = 0; i < c.getFields().size(); i++){
                if(c.getFields().get(i).GUIToString().length() > width){
                    width = c.getFields().get(i).GUIToString().length();
                }
            }
            for(int i = 0; i < c.getMethods().size(); i++){
                if(c.getMethods().get(i).GUIToString().length() > width){
                    width = c.getMethods().get(i).GUIToString().length();
                }
            }
            //the 8 here is just for good spacing
            width *= 8;
            //Outer rectangle
            g2.drawRect(x,y,width,height);
            //Write Class name
            g2.drawString(c.getName(), x+5, y+15);
            //Draw line under the name
            g2.drawLine(x,y + 15,x+width, y+15);
            //moves down twice the spacing of above
            y = y + 30;
            //For each attribute, print the gui toString
            for(int i=0; i < c.getFields().size(); i++){
                g2.drawString(c.getFields().get(i).GUIToString(), x+10, y);
                //moves down 15
                y += 15;
            }
            for(int i=0; i <c.getMethods().size(); i++){
                g2.drawString(c.getMethods().get(i).GUIToString(), x+10, y);
                y += 15;
            }
        }
    }
}

// make the buttons work
// to display, change the actual display button under Display to open CLI
// we need to get the actual class boxes listed
// all of a class's fields, methods, and relationships are added to the box as well
