package j;

    // Java program to construct
// Menu bar to add menu items
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class GUI extends JFrame {
    // Creates a dropdown style menu framework at the top of the frame
    static JMenuBar mainMenu;

    // Creates individual dropdown menus for each category within the overall menu
    static JMenu parameterDropdown, displayDropdown,classDropdown,attributeDropdown,relationshipDropdown,saveLoadDropdown,helpDropdown, CLIDropdown;

    // Individual menu items/buttons under their individual category menus
    static JMenuItem display,addClass,deleteClass,renameClass,addAtt,delAtt,renameAtt,addRelation,delRelation,save,load,help,addPar, delPar, renPar, openCLI;


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


        //Adds a class and updates the display to show the new class in a box
        classDropdown = new JMenu("Class");
        addClass = new JMenuItem(new AbstractAction("Add Class") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //popup box with user input fields.
                String className = JOptionPane.showInputDialog("What is the name of the class you want to add? ");
                String classType = JOptionPane.showInputDialog("          What type is this? \n Please enter one of the numbers below: \n \n " +
                        "1. Class \n 2. Interface \n 3. Enum \n 4. Record \n 5. Annotation");
                //get int that corresponds to the enum type
                int typeToInt = Integer.parseInt(classType);
                Controller.addClass(className, typeToInt);
                //Draw new box (either entire screen refresh or just draw new box)
                //TODO added a test on the line below
                guiWindow.add(new ShapeDrawing());
                displayGUI();
            }
        });

        //Deletes a class and removes it from the display
        deleteClass = new JMenuItem(new AbstractAction("Delete Class") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //popup box asks which class to delete
                String className = JOptionPane.showInputDialog("What is the name of the class you want to delete? ");
                //TODO - Popup prompt to make sure you want to delete the class
                Controller.deleteClass(className);
                displayGUI();
            }
        });

        //renames an existing class and updates the display with the new class name
        renameClass = new JMenuItem(new AbstractAction("Rename Class") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldName = JOptionPane.showInputDialog("What is the name of the class you want to rename? ");
                String newName = JOptionPane.showInputDialog("What would you like to rename this class to? ");
                Controller.renameClass(oldName, newName);
                displayGUI();
            }
        });
        classDropdown.add(addClass);
        classDropdown.add(deleteClass);
        classDropdown.add(renameClass);

        attributeDropdown = new JMenu("Attribute");
        addAtt = new JMenuItem(new AbstractAction("Add Attribute") {
            @Override

            //Adds an attribute when the "Add attribute" button is clicked
            public void actionPerformed(ActionEvent e) {
                String attType = JOptionPane.showInputDialog("Do you want to add a field or a method? \n" +
                        "Type '1' for field, '2' for method");
                int attTypeAsInt = 0;

                try {
                   attTypeAsInt = Integer.parseInt(attType);

                   if(attTypeAsInt == 1){ //adding a field
                       String className = JOptionPane.showInputDialog("Enter the name of the class you are adding the field to");
                       //TODO use existentialcrisis here
                       String fieldName = JOptionPane.showInputDialog("What would you like to call your field?");
                       //Makes sure the visibility choice will pair with one of the 3 possible visibility options
                       int visibilityChoice = 0;
                       while (visibilityChoice < 1 || visibilityChoice > 3){
                           String visibilityChoiceAsString = JOptionPane.showInputDialog("Enter the visibility number below. \n" +
                                   "1.) Private \n 2.) Public \n 3.) Protected");
                           visibilityChoice = Integer.parseInt(visibilityChoiceAsString);
                       }
                       String dataType = JOptionPane.showInputDialog("What is the data type for this field? \n" +
                               "Example: int, string");
                       //creates field
                       Controller.addField(className,fieldName, visibilityChoice, dataType);
                       displayGUI();

                   }
                   else if (attTypeAsInt == 2) { //adding a method
                       String className = JOptionPane.showInputDialog("Enter the name of the class you are adding this method to");
                       String methodName = JOptionPane.showInputDialog("What is the name of this new method?");
                       int visibilityChoice = 0;
                       while (visibilityChoice < 1 || visibilityChoice > 3){
                           String visibilityChoiceAsString = JOptionPane.showInputDialog("Enter the visibility number below. \n" +
                                   "1.) Private \n 2.) Public \n 3.) Protected");
                           visibilityChoice = Integer.parseInt(visibilityChoiceAsString);
                       }
                       String returnType = JOptionPane.showInputDialog("What is the return type of this method?");
                       String params = JOptionPane.showInputDialog("What parameters does this method have?");
                       LinkedList<String> parameters = new LinkedList<>();
                       parameters.add(params);
                       //TODO come back to this when my brain turns back on and finish allowing more params to be added
                       /*boolean moreParams = false;
                       String areMoreParams = JOptionPane.showInputDialog("Does this method have any more parameters?\n" +
                               "Please enter 'yes' to add more, or any key to continue.");
                       if(areMoreParams.equalsIgnoreCase("yes")){
                           moreParams = true;
                       }
                       while (moreParams = true){
                           String params2 = JOptionPane.showInputDialog("What parameters does this method have?");
                       } */

                       //Creates a new method with the given inputs from the user
                       Controller.addMethod(className,methodName,visibilityChoice,returnType, parameters);
                       displayGUI();
                   }
                   else{
                       attType = JOptionPane.showInputDialog("Invalid Input, please try again. \n" +
                               "Do you want to add a field or a method? \n" +
                               "Type '1' for field, '2' for method");
                   }
                } catch (Exception ex){
                     attType = JOptionPane.showInputDialog("Please enter either '1' or '2'. Please try again. \nDo you want to add a field or a method? \n" +
                            "Type '1' for field, '2' for method");
                    //System.out.println("");
                    return;
                }

                //Pop up menu for buttons. This will be returned to if I have time to go back and make this prettier
                    /* JPopupMenu fieldOrMethod = new JPopupMenu("Which would you like to add?");
                    JMenuItem field = new JMenuItem("Field");
                    JMenuItem method = new JMenuItem("Method");
                    fieldOrMethod.add(field);
                    fieldOrMethod.add(method);
                    fieldOrMethod.setVisible(true);
                     */

            }
        });

        //Deletes an attribute from a class
        delAtt = new JMenuItem(new AbstractAction("Delete Attribute") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fieldOrMethodAsString = JOptionPane.showInputDialog("Are you wanting to delete a field or a method? \n" +
                        "Type 1 for 'field' or 2 for 'method'");
                int fieldOrMethod = Integer.parseInt(fieldOrMethodAsString);

                if (fieldOrMethod == 1) {
                    String className = JOptionPane.showInputDialog("What is the name of the class you want to remove a field from?");
                    String fieldName = JOptionPane.showInputDialog("What is the name of the field you wish to delete?");
                    //Controller delete field method is called with user inputs
                    Controller.deleteField(className, fieldName);
                    /*if (Controller.deleteField(className, fieldName)) {
                        System.out.println("Field " + fieldName + " was removed from class " + className);
                    } else {
                        System.out.println("Failed to delete field. Please try again");
                    } */
                    displayGUI();


                } else if (fieldOrMethod == 2) {
                    String className = JOptionPane.showInputDialog("What is the name of the class you want to remove a Method from?");
                    String methodName = JOptionPane.showInputDialog("What is the name of the method you wish to delete?");
                    //Deletes Method with input from user
                    Controller.deleteMethod(className, methodName);

                    /*if (Controller.deleteMethod(className, methodName)) {
                        System.out.println("Method " + methodName + " was removed from class " + className);
                    } else {
                        System.out.println("Failed to delete method. Please try again");
                    } */
                    displayGUI();
                } else {
                    fieldOrMethodAsString = JOptionPane.showInputDialog(" Invalid input, please try again. \n Are you wanting to delete a field or a method? \n" +
                            "Type 1 for 'field' or 2 for 'method'");
                }

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

        //Adds a parameter to a method attached to a class
        addPar = new JMenuItem(new AbstractAction("Add Parameter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classWMethod = JOptionPane.showInputDialog("What class contains the method you would like to add a parameter to?");
                String methodName = JOptionPane.showInputDialog("What is the name of the method you are adding the param to?");
                String paramName = JOptionPane.showInputDialog("What is the new parameter you are adding?");
                Controller.addParam(classWMethod, methodName, paramName);
                displayGUI();
            }
        });

        //deletes an existing parameter from a method in a class
        delPar = new JMenuItem(new AbstractAction("Delete Parameter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classWMethod = JOptionPane.showInputDialog("What class contains the method you would like to delete a parameter from?");
                String methodName = JOptionPane.showInputDialog("What is the name of the method you are deleting the param from?");
                String paramName = JOptionPane.showInputDialog("What is the parameter you are deleting?");
                Controller.deleteParam(classWMethod, methodName, paramName);
                displayGUI();
            }
        });

        //renames an existing parameter
        renPar = new JMenuItem(new AbstractAction("Rename Parameter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classWMethod = JOptionPane.showInputDialog("What class contains the method you would like to rename a parameter in?");
                String methodName = JOptionPane.showInputDialog("What is the name of the method containing the parameter you are renaming?");
                String paramName = JOptionPane.showInputDialog("Which parameter do you want to rename?");
                String newParamName = JOptionPane.showInputDialog("What do you want to rename it to?");
                Controller.renameParam(classWMethod,methodName, paramName, newParamName);
                displayGUI();
            }
        });
        parameterDropdown.add(addPar);
        parameterDropdown.add(delPar);
        parameterDropdown.add(renPar);

        relationshipDropdown = new JMenu("Relationship");

        //Adds a relationship between two existing classes
        addRelation = new JMenuItem(new AbstractAction("Add Relationship") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstClass = JOptionPane.showInputDialog("What is the name of the first class you want to have a relationship?\n" +
                        "(The lower/to class, e.g this implements the other class)");
                String secondClass = JOptionPane.showInputDialog("What is the name of the second class you want to have a relationship?\n" +
                        "(The higher/from class, e.g the other class implements this)");
                String typeAsString = JOptionPane.showInputDialog("Please enter the relationship type's number below\n" +
                        "1.) Aggregation \n 2.) Composition \n 3.) Implementation \n 4.) Realization");
                int type = Integer.parseInt(typeAsString);
                Controller.addRelationship(firstClass, secondClass, type);
                displayGUI();

                //Different prompts letting the user know if the relationship was successfully added or not
            }
        });
        delRelation = new JMenuItem(new AbstractAction("Delete Relationship") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstClass = JOptionPane.showInputDialog("What is the name of the first class with this relationship? ");
                String secondClass = JOptionPane.showInputDialog("What is the name of the second class with this relationship? ");
                //TODO Prompt with a message asking "Delete the relationship from firstClass to secondClass?" with an okay or cancel button
                String doubleCheckDelete = JOptionPane.showInputDialog("Delete the relationship between " + firstClass + " and " + secondClass + "? \n" +
                        "Please type yes to confirm, or enter anything else to cancel. ");


            }
        });
        relationshipDropdown.add(addRelation);
        relationshipDropdown.add(delRelation);

        saveLoadDropdown = new JMenu("Save/Load");

        save = new JMenuItem(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModelDiagram.save();
            }
        });
        load = new JMenuItem(new AbstractAction("Load") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModelDiagram.load();
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
        CLIDropdown = new JMenu("CLI");
        openCLI = new JMenuItem(new AbstractAction("Open CLI") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cview = true;
                CLI.menu();
            }
        });
        CLIDropdown.add(openCLI);

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
                if(!Main.cview){
                    int a = JOptionPane.showConfirmDialog(guiWindow,"Are you sure you want to exit?", "Question", YES_NO_OPTION );
                    if(a==0){
                        System.exit(0);
                    }
                }
            }
        });

        //TODO commented out line below
        //displayGUI();
        //Want to stay idle if CLI view is not there; need to keep program running


        while(!Main.cview) {
            ;
        }

    }
    public static void displayGUI(){
        SwingUtilities.updateComponentTreeUI(guiWindow);
        //guiWindow.add(new ShapeDrawing());
        guiWindow.setVisible(true);

        //guiWindow.invalidate();
        //guiWindow.repaint();

        guiWindow.setLayout(null);
        String[] classNames = Controller.listClasses();


        ShapeDrawing test = new ShapeDrawing();

        //Issue: graphicy2 doesn't pass in correctly as it says g2, which is what graphicy below gets, is null.
        for(int i = 0; i < classNames.length; i++){
            //ShapeDrawing classBoxy = new ShapeDrawing();
            test.setBorder(new LineBorder(Color.BLUE, 3));
            //Graphics2D graphicy2 = classBoxy.getGraphicy();
            //classBoxy.drawClass(classNames[i], 0, 0, graphicy2);
            guiWindow.add(test);
        }



        //int numberOfClasses = Controller.getCreatedClassesSize();
        //String[] classNames = Controller.listClasses();
        //for(int i = 0; i < numberOfClasses; i++){
            //MyDraggableComponent newbie = new MyDraggableComponent();
            //guiWindow.add(newbie);
       // }

    }

    /* public static class ShapeDrawing extends JComponent{
        //Start of blobs
        private volatile int screenX = 0;
        private volatile int screenY = 0;
        private volatile int myX = 0;
        private volatile int myY = 0;
        //End of blobs

        public ShapeDrawing(){
            super();
        // code blob error report: Same as below unfortunately :<
        //Start of blob
            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    myX = getX();
                    myY = getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) { }

                @Override
                public void mouseExited(MouseEvent e) { }

            });
            addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    setLocation(myX + deltaX, myY + deltaY);
                }

                @Override
                public void mouseMoved(MouseEvent e) { }

            });
        //end of blob

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

            /*TODO remove the for loop. Place it in it's own method to print all classes. This print all classes method will be
              outside of the shape drawing class, and will call shapedrawing for each class listed. You'll need int numberofclasses
              from above to be able to do this. This could get complicated as it might require moving multiple methods
               outside of ShapeDrawing*/
            /*for(int i = 0; i < numberOfClasses; i++){
                //issue report with the mouse listeners in the if/else below:
                // Classes are duplicated in display, so for 1 class, 2 show up. They're in weirdly bounded boxes,
                // and the components in each box are not moveable
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
            int rcount = 0;
            for (int i = 0; i < Controller.getCreatedClassesSize(); i++){
                String[][] relationships = Controller.listRelationships();
                for(int j = 0; j<relationships[i].length; j++) {
                    //For each relationship, retrieve the coordinates of each, and draw a line between them
                    int class1XIndex = i * 2; //index of where the coordinates are in the array
                    int class1YIndex = class1XIndex + 1;
                    String[] relationship = relationships[i][j].split(" ");
                    int class2Index = -1;
                    for (int k = 0; k < classes.length; k++) {
                        if (relationship[2].equals(classes[k])) {
                            class2Index = k;
                        }
                    }
                    int class2XIndex = class2Index * 2;

                    int class2YIndex = class2XIndex + 1;
                    int class1XCoords = coords.get(class1XIndex) + (rcount * 20);
                    int class1YCoords = coords.get(class1YIndex) - (rcount * 10);
                    int class2XCoords = coords.get(class2XIndex) + (rcount * 20);
                    int class2YCoords = coords.get(class2YIndex) - (rcount * 10);
                    class1XCoords += 10;
                    //scooches the line over to the right a bit so it isn't on the corner
                    class2XCoords += 10;
                    String relationshipType = relationship[1];
                    if (relationshipType.equals("aggregates") || relationshipType.equals("composes")) {
                        if (class1YCoords == class2YCoords) {
                            g2.drawLine(class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                            g2.drawLine(class1XCoords, class1YCoords - 20, class2XCoords, class2YCoords - 20);
                            g2.drawLine(class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                        }
                        else {
                            if (class1XCoords < class2XCoords) {
                                g2.drawLine(class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                g2.drawLine(class1XCoords, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20);
                                g2.drawLine(class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20);
                                g2.drawLine(class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                g2.drawLine(class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            } else {
                                g2.drawLine(class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                g2.drawLine(class1XCoords, class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20);
                                g2.drawLine(class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20);
                                g2.drawLine(class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                g2.drawLine(class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            }
                        }
                        class2YCoords += 10*rcount-20;
                        int[] xpoints = {class2XCoords, class2XCoords - 10, class2XCoords, class2XCoords + 10};
                        int[] ypoints = {class2YCoords, class2YCoords + 10, class2YCoords + 20, class2YCoords + 10};
                        int npoints = 4;
                        if (relationshipType.equals("aggregates")) {
                            g2.drawPolygon(xpoints, ypoints, npoints);
                        } else {
                            g2.fillPolygon(xpoints, ypoints, npoints);
                        }
                    }
                    else{
                        if (class1YCoords == class2YCoords) {
                            drawDashedLine(g2, class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                            drawDashedLine(g2, class1XCoords, class1YCoords - 20, class2XCoords, class2YCoords - 20);
                            drawDashedLine(g2, class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                        }
                        else {
                            if (class1XCoords < class2XCoords) {
                                drawDashedLine(g2, class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20);
                                drawDashedLine(g2, class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                drawDashedLine(g2, class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            } else {
                                drawDashedLine(g2, class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords, class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20);
                                drawDashedLine(g2, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                drawDashedLine(g2, class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            }
                        }
                        class2YCoords += 10*rcount-20;
                        int[] xpoints = {class2XCoords,class2XCoords-10,class2XCoords+10};
                        int[] ypoints = {class2YCoords+20,class2YCoords,class2YCoords};
                        int npoints = 3;
                        g2.drawPolygon(xpoints, ypoints, npoints);
                    }
                    rcount++;
                }
            }
        }
        public int getClassWidth(String className, Graphics2D g2){
            String[][] classDetails = Controller.listAllClassDetails(className);
            int width = g2.getFontMetrics().stringWidth(className);
            //Set width to largest of the attribute toStrings
            for(int i = 0; i < classDetails[Controller.DETAILS_METHODS].length; i++){
                if(g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_METHODS][i]) > width){
                    width = g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_METHODS][i]) + 10;
                }
            }
            for(int i = 0; i < classDetails[Controller.DETAILS_FIELDS].length; i++){
                if(g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_FIELDS][i]) > width){
                    width = g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_FIELDS][i]) + 10;
                }
            }

            String classType = "<<"+classDetails[Controller.DETAILS_NAME_TYPE][1].toLowerCase()+">>";
            if(width<g2.getFontMetrics().stringWidth(classType)){
                width = g2.getFontMetrics().stringWidth(classType);
            }
            //Add 10 to width for nice spacing
            width += 10;
            return width;
        }
        //Draws the class boxes
        public void drawClass(String className, int x, int y, Graphics2D g2){
            //TODO issue report with the mouse listeners in the if/else below:
            // Classes are duplicated in display, so for 1 class, 2 show up. They're in weirdly bounded boxes,
            // and the components in each box are not moveable

            //number of fields and methods
            String[][] classDetails = Controller.listAllClassDetails(className);
            int height = 15 * (classDetails[Controller.DETAILS_METHODS].length + classDetails[Controller.DETAILS_FIELDS].length+2);
            int width = getClassWidth(className, g2);
            //If the box is not a class, it needs a special header above the name
            boolean isClass = classDetails[Controller.DETAILS_NAME_TYPE][1].equals("CLASS");
            if(!isClass)
                height += 15;
            //Outer rectangle
            g2.drawRect(x,y,width,height);
            //If the box is not a class, it needs a special header above the name
            String classType = "<<"+classDetails[Controller.DETAILS_NAME_TYPE][1].toLowerCase()+">>";
            if(!isClass) {
                g2.drawString(classType, x + width / 2 - g2.getFontMetrics().stringWidth(classType)/2, y + 15);
                y += 15;
            }
            //Write Class name
            g2.drawString(className, x + ((width/2) - (g2.getFontMetrics().stringWidth(className)/2)), y+15);
            //Draw line under the name
            g2.drawLine(x,y + 17,x+width, y+17);
            //moves down twice the spacing of above
            y = y + 30;
            //For each attribute, print the gui toString
            for(int i=0; i <classDetails[Controller.DETAILS_FIELDS].length; i++){
                g2.drawString(classDetails[Controller.DETAILS_FIELDS][i], x+10, y);
                y += 15;
            }
            g2.drawLine(x,y - 12,x+width, y - 12);
            for(int i=0; i < classDetails[Controller.DETAILS_METHODS].length; i++){
                g2.drawString(classDetails[Controller.DETAILS_METHODS][i], x+10, y);
                //moves down 15
                y += 15;
            }
            //TODO added below line displayGui


        }
        public static void drawDashedLine(Graphics2D g, int x1, int y1, int x2, int y2){
            Graphics2D g2d = (Graphics2D) g.create();
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);
            g2d.dispose();
        }
    } */



    //Start of absolute mess of code

    public static class MyDraggableComponent
            extends JComponent {

        private volatile int screenX = 0;
        private volatile int screenY = 0;
        private volatile int myX = 0;
        private volatile int myY = 0;


        public MyDraggableComponent() {
            super();
            //this.setPreferredSize(new Dimension(100,300));
            setBorder(new LineBorder(Color.BLUE, 3));
            setBackground(Color.WHITE);
            setBounds(0, 0, 100, 100);
            setOpaque(false);

            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    myX = getX();
                    myY = getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) { }

                @Override
                public void mouseExited(MouseEvent e) { }

            });
            addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    setLocation(myX + deltaX, myY + deltaY);
                }

                @Override
                public void mouseMoved(MouseEvent e) { }

            });
        }

    }


    public static class ShapeDrawing extends JComponent{
        //Start of blobs
        private volatile int screenX = 0;
        private volatile int screenY = 0;
        private volatile int myX = 0;
        private volatile int myY = 0;
        //End of blobs

        public ShapeDrawing(){
            super();

            // code blob error report: Same as below unfortunately :<
            //Makes a Shapedrawing moveable
            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    myX = getX();
                    myY = getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) { }

                @Override
                public void mouseExited(MouseEvent e) { }

            });
            addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    setLocation(myX + deltaX, myY + deltaY);
                }

                @Override
                public void mouseMoved(MouseEvent e) { }

            });

        }

        //Made to pass up a graphic to the for loop I tried in displayGUI
        //Graphics2D graphicy;

        public void paintComponent(Graphics g){
            super.paintComponent(g);
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

            /* remove the for loop. Place it in it's own method to print all classes. This print all classes method will be
              outside of the shape drawing class, and will call shapedrawing for each class listed. You'll need int numberofclasses
              from above to be able to do this. This could get complicated as it might require moving multiple methods
               outside of ShapeDrawing*/
            //Report on the above:This shouldn't work as the methods need to be nested in the component it seems. Might be able to
            //make it work with extra workarounds


            //Prints a line of classboxes. Relationship drawing breaks and crashes the program. That specific line is moveable
            //for (int i = 0; i < numberOfClasses; i++){
                //drawClass(classNames[i], curx, 200, g2);
                //curx += (1.5 * spaceWidth);
            //}

            int lastNameNumber = classNames.length - 1;
            drawClass(classNames[lastNameNumber], curx, 200, g2);




             /*for(int i = 0; i < numberOfClasses; i++){
                //issue report with the mouse listeners in the if/else below:
                // Classes are duplicated in display, so for 1 class, 2 show up. They're in weirdly bounded boxes,
                // and the components in each box are not moveable
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
            } */
            String[] classes = Controller.listClasses();
            //Prints the line for each relationship
            int rcount = 0;
            for (int i = 0; i < Controller.getCreatedClassesSize(); i++){
                String[][] relationships = Controller.listRelationships();
                for(int j = 0; j<relationships[i].length; j++) {
                    //For each relationship, retrieve the coordinates of each, and draw a line between them
                    int class1XIndex = i * 2; //index of where the coordinates are in the array
                    int class1YIndex = class1XIndex + 1;
                    String[] relationship = relationships[i][j].split(" ");
                    int class2Index = -1;
                    for (int k = 0; k < classes.length; k++) {
                        if (relationship[2].equals(classes[k])) {
                            class2Index = k;
                        }
                    }
                    int class2XIndex = class2Index * 2;

                    int class2YIndex = class2XIndex + 1;
                    int class1XCoords = coords.get(class1XIndex) + (rcount * 20);
                    int class1YCoords = coords.get(class1YIndex) - (rcount * 10);
                    int class2XCoords = coords.get(class2XIndex) + (rcount * 20);
                    int class2YCoords = coords.get(class2YIndex) - (rcount * 10);
                    class1XCoords += 10;
                    //scooches the line over to the right a bit so it isn't on the corner
                    class2XCoords += 10;
                    String relationshipType = relationship[1];
                    if (relationshipType.equals("aggregates") || relationshipType.equals("composes")) {
                        if (class1YCoords == class2YCoords) {
                            g2.drawLine(class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                            g2.drawLine(class1XCoords, class1YCoords - 20, class2XCoords, class2YCoords - 20);
                            g2.drawLine(class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                        }
                        else {
                            if (class1XCoords < class2XCoords) {
                                g2.drawLine(class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                g2.drawLine(class1XCoords, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20);
                                g2.drawLine(class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20);
                                g2.drawLine(class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                g2.drawLine(class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            } else {
                                g2.drawLine(class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                g2.drawLine(class1XCoords, class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20);
                                g2.drawLine(class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20);
                                g2.drawLine(class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                g2.drawLine(class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            }
                        }
                        class2YCoords += 10*rcount-20;
                        int[] xpoints = {class2XCoords, class2XCoords - 10, class2XCoords, class2XCoords + 10};
                        int[] ypoints = {class2YCoords, class2YCoords + 10, class2YCoords + 20, class2YCoords + 10};
                        int npoints = 4;
                        if (relationshipType.equals("aggregates")) {
                            g2.drawPolygon(xpoints, ypoints, npoints);
                        } else {
                            g2.fillPolygon(xpoints, ypoints, npoints);
                        }
                    }
                    else{
                        if (class1YCoords == class2YCoords) {
                            drawDashedLine(g2, class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                            drawDashedLine(g2, class1XCoords, class1YCoords - 20, class2XCoords, class2YCoords - 20);
                            drawDashedLine(g2, class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                        }
                        else {
                            if (class1XCoords < class2XCoords) {
                                drawDashedLine(g2, class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords + getClassWidth(relationship[0], g2) + 40, class1YCoords - 20, class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20);
                                drawDashedLine(g2, class1XCoords + getClassWidth(relationship[0], g2) + 40, class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                drawDashedLine(g2, class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            } else {
                                drawDashedLine(g2, class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords, class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20);
                                drawDashedLine(g2, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class1YCoords - 20, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20);
                                drawDashedLine(g2, class1XCoords - (class1XCoords - class2XCoords - getClassWidth(relationship[2], g2) - 20), class2YCoords - 20, class2XCoords, class2YCoords - 20);
                                drawDashedLine(g2, class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                            }
                        }
                        class2YCoords += 10*rcount-20;
                        int[] xpoints = {class2XCoords,class2XCoords-10,class2XCoords+10};
                        int[] ypoints = {class2YCoords+20,class2YCoords,class2YCoords};
                        int npoints = 3;
                        g2.drawPolygon(xpoints, ypoints, npoints);
                    }
                    rcount++;
                }
            }
        }

        /*this was added in the last push
        public Graphics2D getGraphicy(){
            return this.graphicy;
        } */

        public int getClassWidth(String className, Graphics2D g2){
            String[][] classDetails = Controller.listAllClassDetails(className);
            int width = g2.getFontMetrics().stringWidth(className);
            //Set width to largest of the attribute toStrings
            for(int i = 0; i < classDetails[Controller.DETAILS_METHODS].length; i++){
                if(g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_METHODS][i]) > width){
                    width = g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_METHODS][i]) + 10;
                }
            }
            for(int i = 0; i < classDetails[Controller.DETAILS_FIELDS].length; i++){
                if(g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_FIELDS][i]) > width){
                    width = g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_FIELDS][i]) + 10;
                }
            }

            String classType = "<<"+classDetails[Controller.DETAILS_NAME_TYPE][1].toLowerCase()+">>";
            if(width<g2.getFontMetrics().stringWidth(classType)){
                width = g2.getFontMetrics().stringWidth(classType);
            }
            //Add 10 to width for nice spacing
            width += 10;
            return width;
        }
        //Draws the class boxes
        public void drawClass(String className, int x, int y, Graphics2D g2){
            //issue report with the mouse listeners in the if/else below:
            // Classes are duplicated in display, so for 1 class, 2 show up. They're in weirdly bounded boxes,
            // and the components in each box are not moveable

            //number of fields and methods
            String[][] classDetails = Controller.listAllClassDetails(className);
            int height = 15 * (classDetails[Controller.DETAILS_METHODS].length + classDetails[Controller.DETAILS_FIELDS].length+2);
            int width = getClassWidth(className, g2);
            //If the box is not a class, it needs a special header above the name
            boolean isClass = classDetails[Controller.DETAILS_NAME_TYPE][1].equals("CLASS");
            if(!isClass)
                height += 15;
            //Outer rectangle
            g2.drawRect(x,y,width,height);
            //If the box is not a class, it needs a special header above the name
            String classType = "<<"+classDetails[Controller.DETAILS_NAME_TYPE][1].toLowerCase()+">>";
            if(!isClass) {
                g2.drawString(classType, x + width / 2 - g2.getFontMetrics().stringWidth(classType)/2, y + 15);
                y += 15;
            }
            //Write Class name
            g2.drawString(className, x + ((width/2) - (g2.getFontMetrics().stringWidth(className)/2)), y+15);
            //Draw line under the name
            g2.drawLine(x,y + 17,x+width, y+17);
            //moves down twice the spacing of above
            y = y + 30;
            //For each attribute, print the gui toString
            for(int i=0; i <classDetails[Controller.DETAILS_FIELDS].length; i++){
                g2.drawString(classDetails[Controller.DETAILS_FIELDS][i], x+10, y);
                y += 15;
            }
            g2.drawLine(x,y - 12,x+width, y - 12);
            for(int i=0; i < classDetails[Controller.DETAILS_METHODS].length; i++){
                g2.drawString(classDetails[Controller.DETAILS_METHODS][i], x+10, y);
                //moves down 15
                y += 15;
            }

        }
        public static void drawDashedLine(Graphics2D g, int x1, int y1, int x2, int y2){
            Graphics2D g2d = (Graphics2D) g.create();
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);
            g2d.dispose();
        }
    }


}

// make the buttons work
// to display, change the actual display button under Display to open CLI
// we need to get the actual class boxes listed
// all of a class's fields, methods, and relationships are added to the box as well