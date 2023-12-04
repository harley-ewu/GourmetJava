package j;

// Java program to construct
// Menu bar to add menu items

import org.jline.console.impl.SystemRegistryImpl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.LinkedList;

import static javax.swing.JOptionPane.*;

/**
 * This class has a lot of commented out lines of code in it as I've tried to work through things.
 * Issue reports are where I tried to do something, usually adding mouse listeners, and didn't get the desired result
 * TODOs are where I left a thought to return to later
 * <p>
 * It's best to ignore all of these unless they spark an idea on how to fix something, which is mostly why
 * I left them visible and in the code still
 */


public class GUI extends JFrame implements j.Observer {

    private static final LinkedList<ClassPanel> classes = new LinkedList<>();

    public static class ClassPanel extends JPanel {
        private final String name;

        private final String type;

        private final String[] classFields;

        private final String[] classMethods;
        private final int heightScale;

        private int xDelta;

        private int yDelta;

        private int height = 300;

        private int width = 200;

        //to help make dragging smooth
        public MouseEvent pressed;

        public ClassPanel(final String[][] details, final int x, final int y) {
            super(new GridLayout(0, 1));
            this.name = details[Controller.DETAILS_NAME_TYPE][0];
            this.type = details[Controller.DETAILS_NAME_TYPE][1];
            this.classMethods = details[Controller.DETAILS_METHODS];
            this.classFields = details[Controller.DETAILS_FIELDS];
            this.xDelta = x;
            this.yDelta = y;
            double maxWidth = 1.0;

            boolean isClass = this.type.equals("CLASS");
            //If the box is not a class, it needs a special header
            if (!isClass) {
                JLabel header = new JLabel("<<" + this.type + ">>");
                header.setFont(new Font("Verdana", Font.PLAIN, 10));
                header.setHorizontalAlignment(SwingConstants.CENTER);
                header.setVisible(true);
                if (header.getPreferredSize().getWidth() > maxWidth)
                    maxWidth = header.getPreferredSize().getWidth();
                this.add(header);
            }

            //Add the name label
            JLabel className = new JLabel(this.name);
            className.setFont(new Font("Verdana", Font.PLAIN, 10));
            className.setHorizontalAlignment(SwingConstants.CENTER);
            className.setVisible(true);
            if (className.getPreferredSize().getWidth() > maxWidth)
                maxWidth = className.getPreferredSize().getWidth();
            this.add(className);

            for (String f : this.classFields) {
                JLabel lbl = new JLabel(f);
                lbl.setFont(new Font("Verdana", Font.PLAIN, 10));
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setVisible(true);
                if (lbl.getPreferredSize().getWidth() > maxWidth)
                    maxWidth = lbl.getPreferredSize().getWidth();
                this.add(lbl);
            }

            for (String m : this.classMethods) {
                JLabel lbl = new JLabel(m);
                lbl.setFont(new Font("Verdana", Font.PLAIN, 10));
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setVisible(true);
                if (lbl.getPreferredSize().getWidth() > maxWidth)
                    maxWidth = lbl.getPreferredSize().getWidth();
                this.add(lbl);
            }

            //set the panel size
            this.width = (int) maxWidth + 5;
            this.heightScale = (int) className.getPreferredSize().getHeight();
            this.height = this.heightScale * 2 + this.heightScale * this.classFields.length + this.heightScale * this.classMethods.length + 5;
            if (!isClass)
                this.height += this.heightScale;
            this.setBounds(this.xDelta, this.yDelta, this.width, this.height);
            this.setBorder(BorderFactory.createLineBorder(Color.black));

            //Add the mouse event handlers
            ClassPanel thisPanel = this;

            //We need a mouse listener to catch where the initial click is, otherwise the panel will "jump"
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me){
                    thisPanel.pressed = me;
                }
            });

            this.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent me) {
                    //Offset the translate by the difference between the panel's location and the initial click
                    int xTranslated = thisPanel.getLocation().x - thisPanel.pressed.getX();
                    int yTranslated = thisPanel.getLocation().y- thisPanel.pressed.getY();
                    me.translatePoint(xTranslated, yTranslated);
                    thisPanel.setLocation(me.getX(),me.getY());
                }
            });
        }

        public ClassPanel(final String[][] details) {
            this(details, 0, 0);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int nameLineY;
            if (!this.type.equals("CLASS"))
                nameLineY = this.heightScale * 2 + 6;
            else
                nameLineY = this.heightScale + 6;

            int fieldsLineY = nameLineY + this.heightScale * this.classFields.length + 6;
            g.drawLine(0, nameLineY, this.width, nameLineY);
            g.drawLine(0, fieldsLineY, this.width, fieldsLineY);
        }

    }

    // Creates a dropdown style menu framework at the top of the frame
    static JMenuBar mainMenu;

    // Creates individual dropdown menus for each category within the overall menu
    static JMenu parameterDropdown, displayDropdown, classDropdown, attributeDropdown, relationshipDropdown, saveLoadDropdown, helpDropdown, CLIDropdown;

    // Individual menu items/buttons under their individual category menus
    static JMenuItem display, addClass, deleteClass, renameClass, addAtt, delAtt, renameAtt, addRelation, delRelation, save, load, help, addPar, delPar, renPar, openCLI;


    //creates a frame to be the main, base window to hold the entirety of the GUI
    static JFrame guiWindow;

    private static final GUI GUIObserver = new GUI();

    public void update() {
        displayGUI();
    }

    public static void startGUIMenu() {
        Controller.addSubscriber(GUIObserver);

        guiWindow = new JFrame("UML Editor");
        guiWindow.getContentPane().setBackground(Color.BLUE);
        // create a menubar
        mainMenu = new JMenuBar();

        displayDropdown = new JMenu("Display");
        display = new JMenuItem(new AbstractAction("Refresh") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        displayDropdown.add(display);


        //Adds a class and updates the display to show the new class in a box
        classDropdown = new JMenu("Class");
        addClass = new JMenuItem(new AbstractAction("Add Class") {
            @Override
            public void actionPerformed(ActionEvent e) {

                String className = className = JOptionPane.showInputDialog(guiWindow, "What is the name of the class you want to add? ");

                //Ensures a user enters a valid class name
                //while(className.equals("") || className.equals(null)|| className.equals(" ")){
                    //popup box asking the user to enter a string to use as the new class's name
                   // className = JOptionPane.showInputDialog(guiWindow, "What is the name of the class you want to add? ");
                //}


                //Creates radio buttons for each class type option
                JRadioButton classButton = new JRadioButton("Class");
                JRadioButton interfaceButton = new JRadioButton("Interface");
                JRadioButton enumButton = new JRadioButton("Enum");
                JRadioButton recordButton = new JRadioButton("Record");
                JRadioButton annotationButton = new JRadioButton("Annotation");

                // Creates button group, making it so only one option can be selected instead of multiple
                ButtonGroup classTypeGroup = new ButtonGroup();
                classTypeGroup.add(classButton);
                classTypeGroup.add(interfaceButton);
                classTypeGroup.add(enumButton);
                classTypeGroup.add(recordButton);
                classTypeGroup.add(annotationButton);

                //Sets the "Class" button as the default option. Done to ensure a button is always selected.
                classButton.setSelected(true);

                //Create a JPanel and add the button group to it, aligned vertically
                JPanel chooseType = new JPanel(new GridLayout(0,1));
                chooseType.add(new JLabel("Please Choose a Type:"));
                chooseType.add(classButton);
                chooseType.add(interfaceButton);
                chooseType.add(enumButton);
                chooseType.add(recordButton);
                chooseType.add(annotationButton);

                //Displays Type options, and centers the popup window on the main GUI screen
                int typeChoice = JOptionPane.showConfirmDialog(guiWindow, chooseType, "Choose Class Type", JOptionPane.OK_CANCEL_OPTION);
                if(typeChoice == JOptionPane.OK_OPTION) {

                    //Defaults class in order to make classType always initialized
                    String classType = classButton.getText();

                    //Change classType to give the correct text option from the selected button into the switch statement to be converted to an int
                    if(classButton.isSelected()) classType = classButton.getText();
                    if(interfaceButton.isSelected()) classType = interfaceButton.getText();
                    if(enumButton.isSelected()) classType =  enumButton.getText();
                    if(recordButton.isSelected()) classType =  recordButton.getText();
                    if(annotationButton.isSelected()) classType = annotationButton.getText();
                    //get int that corresponds to the enum type
                    int typeToInt = 0;
                    //Convert chosen button's string to an int to be passed into the addClass method
                    switch (classType){
                        case "Class":
                            typeToInt = 1;
                            break;
                        case "Interface":
                            typeToInt = 2;
                            break;
                        case "Record":
                            typeToInt = 3;
                            break;
                        case "Enum":
                            typeToInt = 4;
                            break;
                        case "Annotation":
                            typeToInt = 5;
                            break;
                    }

                    Controller.addClass(className, typeToInt);
                    guiWindow.add(new ShapeDrawing());
                }
            }
        });



        //Deletes a class and removes it from the display
        deleteClass = new JMenuItem(new AbstractAction("Delete Class") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get the list of existing classes
                String[] classList = Controller.listClasses();

                //Add a default option asking the user to pick a class
                String[] classListWDefault = new String[classList.length + 1];
                classListWDefault[0] = "Choose a class";
                System.arraycopy(classList, 0, classListWDefault, 1, classList.length);

                //Creates a combo box with the list of classes
                JComboBox<String> classComboBox = new JComboBox<>(classListWDefault);

                boolean isAClassOption = false;

                while(!isAClassOption){
                    //Put combo box in a dialog "yes/Cancel" popup. Centers it in the GUI window
                    int chosenClass = JOptionPane.showConfirmDialog(guiWindow, classComboBox, "Which Class would you like to delete?",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                    String classToDelete = (String) classComboBox.getSelectedItem();

                    if(chosenClass == JOptionPane.OK_OPTION && !classToDelete.equals("Choose a class")){
                        Controller.deleteClass(classToDelete);
                        isAClassOption = true;
                    }
                    else{
                        if(chosenClass == JOptionPane.CANCEL_OPTION || chosenClass == JOptionPane.CLOSED_OPTION){
                            break;
                        }
                    }
                }

            }
        });

        //renames an existing class and updates the display with the new class name
        renameClass = new JMenuItem(new AbstractAction("Rename Class") {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Get the list of existing classes
                String[] classList = Controller.listClasses();

                //Add a default option asking the user to pick a class
                String[] classListWDefault = new String[classList.length + 1];
                classListWDefault[0] = "Choose a class";
                System.arraycopy(classList, 0, classListWDefault, 1, classList.length);

                //Creates a combo box with the list of classes
                JComboBox<String> classComboBox = new JComboBox<>(classListWDefault);

                boolean isAClassOption = false;

                while(!isAClassOption){
                    //Put combo box in a dialog "yes/Cancel" popup. Centers it in the GUI window
                    int chosenClass = JOptionPane.showConfirmDialog(guiWindow, classComboBox, "Which Class would you like to rename?",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                    String oldName = (String) classComboBox.getSelectedItem();

                    if(chosenClass == JOptionPane.OK_OPTION && !oldName.equals("Choose a class")){
                        String newName = JOptionPane.showInputDialog(guiWindow, "What would you like to rename this class to? ");
                        Controller.renameClass(oldName, newName);
                        isAClassOption = true;
                    }
                    else{
                        if(chosenClass == JOptionPane.CANCEL_OPTION || chosenClass == JOptionPane.CLOSED_OPTION){
                            break;
                        }
                    }
                }

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
                // Create radio buttons for the field and method options.
                JRadioButton fieldButton = new JRadioButton("Field");
                JRadioButton methodButton = new JRadioButton("Method");

                //Add them to a group so only one can be selected, then set "field" as the default option
                ButtonGroup fieldOrMethod = new ButtonGroup();
                fieldOrMethod.add(fieldButton);
                fieldOrMethod.add(methodButton);
                fieldButton.setSelected(true);

                //Make a Jpanel to put the button group into
                JPanel chooseAttType = new JPanel(new GridLayout(0,1));
                chooseAttType.add(new JLabel("Do you want to add a field or a method?"));
                chooseAttType.add(fieldButton);
                chooseAttType.add(methodButton);

                //Displays field and method options on an okay/cancel pane, and centers the popup window on the main GUI screen
                int askAttType = JOptionPane.showConfirmDialog(guiWindow, chooseAttType, "Choose Attribute Type", JOptionPane.OK_CANCEL_OPTION);

                if(askAttType == OK_OPTION){
                    //Defaults field in order to make askAttType always initialized
                    String choice = fieldButton.getText();

                    //Change classType to give the correct text option from the selected button into the switch statement to be converted to an int
                    if(fieldButton.isSelected()) choice = fieldButton.getText();
                    if (methodButton.isSelected()) choice = methodButton.getText();

                    int attTypeAsInt = 0;

                    switch (choice){
                        case "Field":
                            attTypeAsInt = 1;
                            break;
                        case "Method":
                            attTypeAsInt = 2;
                            break;
                    }

                    //Adding a field
                    if (attTypeAsInt == 1) {
                        //Get the list of existing classes
                        String[] classList = Controller.listClasses();

                        //Add a default option asking the user to pick a class
                        String[] classListWDefault = new String[classList.length + 1];
                        classListWDefault[0] = "Choose a class";
                        System.arraycopy(classList, 0, classListWDefault, 1, classList.length);

                        //Creates a combo box with the list of classes
                        JComboBox<String> classComboBox = new JComboBox<>(classListWDefault);

                        boolean isAClassOption = false;

                        while(!isAClassOption){

                            //Put combo box in a dialog "yes/Cancel" popup. Centers it in the GUI window
                            int chosenClass = JOptionPane.showConfirmDialog(guiWindow, classComboBox, "Name of the class to add a field to",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                            String className = (String) classComboBox.getSelectedItem();

                            if(chosenClass == JOptionPane.OK_OPTION && !className.equals("Choose a class")){
                                //Gets the name of the new field
                                String fieldName = JOptionPane.showInputDialog(guiWindow, "What would you like to call your field?");

                                //Makes sure the visibility choice will pair with one of the 3 possible visibility options.
                                JRadioButton privateButton = new JRadioButton("Private");
                                JRadioButton publicButton = new JRadioButton("Public");
                                JRadioButton protectedButton = new JRadioButton("Protected");
                                //Sets protected as the default visibility
                                privateButton.setSelected(true);

                                ButtonGroup visibilityGroup = new ButtonGroup();
                                visibilityGroup.add(privateButton);
                                visibilityGroup.add(publicButton);
                                visibilityGroup.add(protectedButton);

                                JPanel visibility = new JPanel(new GridLayout(0,1));
                                visibility.add(new JLabel("Enter the visibility for your field"));
                                visibility.add(privateButton);
                                visibility.add(publicButton);
                                visibility.add(protectedButton);

                                int visibilityPopup = JOptionPane.showConfirmDialog(guiWindow, visibility, "Visibility Type", OK_CANCEL_OPTION);

                                if(visibilityPopup == OK_OPTION){
                                    //VisibilityChoice will be changed given the button selected, but defaults to private
                                    String visibilityChoice = privateButton.getText();
                                    if(publicButton.isSelected()) visibilityChoice = publicButton.getText();
                                    if(protectedButton.isSelected()) visibilityChoice = protectedButton.getText();

                                    int visibilityChoiceInt = 0;
                                    switch (visibilityChoice){
                                        case ("Private"):
                                            visibilityChoiceInt = 1;
                                            break;
                                        case ("Public"):
                                            visibilityChoiceInt = 2;
                                            break;
                                        case ("Protected"):
                                            visibilityChoiceInt = 3;
                                            break;
                                    }
                                    String dataType = JOptionPane.showInputDialog(guiWindow, "What is the first data type for this field? \n" +
                                            "You can add more later. \n" +
                                            "Example: int, string");
                                    //creates field
                                    Controller.addField(className, fieldName, visibilityChoiceInt, dataType);

                                } else if (visibilityPopup == CANCEL_OPTION || visibilityPopup == CANCEL_OPTION) {
                                    break;
                                }

                                isAClassOption = true;
                            }
                            else{
                                if(chosenClass == JOptionPane.CANCEL_OPTION || chosenClass == JOptionPane.CLOSED_OPTION){
                                    break;
                                }
                            }
                        }


                    } else if (attTypeAsInt == 2) { //adding a method
                        //Get the list of existing classes
                        String[] classList = Controller.listClasses();

                        //Add a default option asking the user to pick a class
                        String[] classListWDefault = new String[classList.length + 1];
                        classListWDefault[0] = "Choose a class";
                        System.arraycopy(classList, 0, classListWDefault, 1, classList.length);

                        //Creates a combo box with the list of classes
                        JComboBox<String> classComboBox = new JComboBox<>(classListWDefault);

                        boolean isAClassOption = false;

                        while(!isAClassOption){

                            //Put combo box in a dialog "yes/Cancel" popup. Centers it in the GUI window
                            int chosenClass = JOptionPane.showConfirmDialog(guiWindow, classComboBox, "Name of the class to add a field to",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                            String className = (String) classComboBox.getSelectedItem();

                            if(chosenClass == JOptionPane.OK_OPTION && !className.equals("Choose a class")){
                                //Gets the name of the new field
                                String methodName = JOptionPane.showInputDialog(guiWindow, "What is the new method's name?");

                                //Makes sure the visibility choice will pair with one of the 3 possible visibility options.
                                JRadioButton privateButton = new JRadioButton("Private");
                                JRadioButton publicButton = new JRadioButton("Public");
                                JRadioButton protectedButton = new JRadioButton("Protected");
                                //Sets protected as the default visibility
                                privateButton.setSelected(true);

                                ButtonGroup visibilityGroup = new ButtonGroup();
                                visibilityGroup.add(privateButton);
                                visibilityGroup.add(publicButton);
                                visibilityGroup.add(protectedButton);

                                JPanel visibility = new JPanel(new GridLayout(0,1));
                                visibility.add(new JLabel("Enter the visibility for your field"));
                                visibility.add(privateButton);
                                visibility.add(publicButton);
                                visibility.add(protectedButton);

                                int visibilityPopup = JOptionPane.showConfirmDialog(guiWindow, visibility, "Visibility Type", OK_CANCEL_OPTION);

                                if(visibilityPopup == OK_OPTION){
                                    //VisibilityChoice will be changed given the button selected, but defaults to private
                                    String visibilityChoice = privateButton.getText();
                                    if(publicButton.isSelected()) visibilityChoice = publicButton.getText();
                                    if(protectedButton.isSelected()) visibilityChoice = protectedButton.getText();

                                    int visibilityChoiceInt = 0;
                                    switch (visibilityChoice){
                                        case ("Private"):
                                            visibilityChoiceInt = 1;
                                            break;
                                        case ("Public"):
                                            visibilityChoiceInt = 2;
                                            break;
                                        case ("Protected"):
                                            visibilityChoiceInt = 3;
                                            break;
                                    }

                                    String returnType = JOptionPane.showInputDialog(guiWindow, "What is the return type of this method?");
                                    String params = JOptionPane.showInputDialog(guiWindow, "What parameters does this method have? \n " +
                                            "If there are multiple, you'll need to do \n this one at a time.");

                                    LinkedList<String> parameters = new LinkedList<>();
                                    parameters.add(params);

                                    boolean moreParams = true;

                                    while(moreParams){
                                        int addMoreParams = JOptionPane.showConfirmDialog(guiWindow, "Does this method have any more parameters?",
                                        "More Parameters", YES_NO_OPTION);

                                        if(addMoreParams == YES_NO_OPTION){
                                            String additionalParam = JOptionPane.showInputDialog(guiWindow, "What is the next parameter?" );
                                            parameters.add(additionalParam);
                                        }
                                        else{
                                            moreParams = false;
                                        }
                                    }

                                    //Creates a new method with the given inputs from the user
                                    Controller.addMethod(className, methodName, visibilityChoiceInt, returnType, parameters);
                                } else if (visibilityPopup == CANCEL_OPTION || visibilityPopup == CANCEL_OPTION) {
                                    break;
                                }

                                isAClassOption = true;
                            }
                            else{
                                if(chosenClass == JOptionPane.CANCEL_OPTION || chosenClass == JOptionPane.CLOSED_OPTION){
                                    break;
                                }
                            }
                        }

                    } else {
                        return;
                    }

                }
            }
        });

        //Deletes an attribute from a class
        delAtt = new JMenuItem(new AbstractAction("Delete Attribute") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create radio buttons for the field and method options.
                JRadioButton fieldButton = new JRadioButton("Field");
                JRadioButton methodButton = new JRadioButton("Method");

                //Add them to a group so only one can be selected, then set "field" as the default option
                ButtonGroup fieldOrMethod = new ButtonGroup();
                fieldOrMethod.add(fieldButton);
                fieldOrMethod.add(methodButton);
                fieldButton.setSelected(true);

                //Make a Jpanel to put the button group into
                JPanel chooseAttType = new JPanel(new GridLayout(0,1));
                chooseAttType.add(new JLabel("Do you want to delete a field or a method?"));
                chooseAttType.add(fieldButton);
                chooseAttType.add(methodButton);



                //Displays field and method options on an okay/cancel pane, and centers the popup window on the main GUI screen
                int askAttType = JOptionPane.showConfirmDialog(guiWindow, chooseAttType, "Choose Attribute Type", JOptionPane.OK_CANCEL_OPTION);

                if(askAttType == OK_OPTION) {
                    //Defaults choice in order to make askAttType always initialized
                    String choice = fieldButton.getText();

                    //Change classType to give the correct text option from the selected button into the switch statement to be converted to an int
                    if (fieldButton.isSelected()) choice = fieldButton.getText();
                    if (methodButton.isSelected()) choice = methodButton.getText();

                    int attTypeAsInt = 0;

                    switch (choice) {
                        case "Field":
                            attTypeAsInt = 1;
                            break;
                        case "Method":
                            attTypeAsInt = 2;
                            break;
                    }

                    if (attTypeAsInt == 1) {
                        //Get the list of existing classes
                        String[] classList = Controller.listClasses();

                        //Add a default option asking the user to pick a class
                        String[] classListWDefault = new String[classList.length + 1];
                        classListWDefault[0] = "Choose a class";
                        System.arraycopy(classList, 0, classListWDefault, 1, classList.length);

                        //Creates a combo box with the list of classes
                        JComboBox<String> classComboBox = new JComboBox<>(classListWDefault);

                        boolean isAClassOption = false;

                        while(!isAClassOption){
                            //Put combo box in a dialog "yes/Cancel" popup. Centers it in the GUI window
                            int chosenClass = JOptionPane.showConfirmDialog(guiWindow, classComboBox, "Deleting a field from what class?",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                            String className = (String) classComboBox.getSelectedItem();

                            if(chosenClass == JOptionPane.OK_OPTION && !className.equals("Choose a class")){
                                //Gets the name of the new field
                                //String fieldName = JOptionPane.showInputDialog(guiWindow, "Which field do you want to delete?");
                                //String classWField = (String) classComboBox.getSelectedItem();
                                String[][] classDetails = Controller.listAllClassDetails(className);
                                String[] fieldList = classDetails[2];
                                JComboBox<String> fieldComboBox = new JComboBox<>(fieldList);

                                int chosenField = JOptionPane.showConfirmDialog(guiWindow, fieldComboBox, "Delete which field from " + className + "?",
                                        OK_CANCEL_OPTION, QUESTION_MESSAGE);
                                if(chosenField == OK_OPTION){
                                    String fieldToDeleteInfo = (String) fieldComboBox.getSelectedItem();
                                    String fieldToDelete = fieldToDeleteInfo.toString();
                                    String[] seperateFieldName = fieldToDelete.split("\\s+"); //Split by whitespace
                                    //Extract only the field's name
                                    String fieldNameWParam = seperateFieldName[0];
                                    String fieldName = fieldNameWParam.substring(1).trim();


                                    JPanel displayConfirmationMessage = new JPanel(new GridLayout(0,1));
                                    displayConfirmationMessage.add(new JLabel("Delete " + fieldToDelete + " ?"));
                                    int confirmDelete = JOptionPane.showConfirmDialog(guiWindow, displayConfirmationMessage, "Delete ?", OK_CANCEL_OPTION);
                                    if(confirmDelete == OK_OPTION){
                                        System.out.println("This is the fieldtodelete selected: " + fieldName);
                                        Controller.deleteField(className, fieldName);
                                        System.out.println("This is the passed in class name to delete from: " + className);
                                    }

                                }
                                else{
                                    return;
                                }





                                isAClassOption = true;
                            }
                            else{
                                if(chosenClass == JOptionPane.CANCEL_OPTION || chosenClass == JOptionPane.CLOSED_OPTION){
                                    break;
                                }
                            }
                        }

                    }

                    if (attTypeAsInt == 2) {
                        //Get the list of existing classes
                        String[] classList = Controller.listClasses();

                        //Add a default option asking the user to pick a class
                        String[] classListWDefault = new String[classList.length + 1];
                        classListWDefault[0] = "Choose a class";
                        System.arraycopy(classList, 0, classListWDefault, 1, classList.length);

                        //Creates a combo box with the list of classes
                        JComboBox<String> classComboBox = new JComboBox<>(classListWDefault);

                        boolean isAClassOption = false;

                        while(!isAClassOption){
                            //Put combo box in a dialog "yes/Cancel" popup. Centers it in the GUI window
                            int chosenClass = JOptionPane.showConfirmDialog(guiWindow, classComboBox, "Deleting a method from what class?",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                            String className = (String) classComboBox.getSelectedItem();

                            if(chosenClass == JOptionPane.OK_OPTION && !className.equals("Choose a class")){
                                //Gets the name of the new field
                                //String fieldName = JOptionPane.showInputDialog(guiWindow, "Which field do you want to delete?");
                                //String classWField = (String) classComboBox.getSelectedItem();
                                String[][] classDetails = Controller.listAllClassDetails(className);
                                String[] methodList = classDetails[1];
                                JComboBox<String> methodComboBox = new JComboBox<>(methodList);

                                int chosenMethod = JOptionPane.showConfirmDialog(guiWindow, methodComboBox, "Delete which Method?",
                                        OK_CANCEL_OPTION, QUESTION_MESSAGE);
                                if(chosenMethod == OK_OPTION){
                                    String methodToDeleteInfo = (String) methodComboBox.getSelectedItem();
                                    String methodToDelete = methodToDeleteInfo.toString();
                                    String[] seperateMethodName = methodToDelete.split("\\(");  //\\s+//Split by whitespace
                                    //Extract only the field's name
                                    String methodNameWParam = seperateMethodName[0];
                                    String methodName = methodNameWParam.substring(1).trim();



                                    JPanel displayConfirmationMessage = new JPanel(new GridLayout(0,1));
                                    displayConfirmationMessage.add(new JLabel("Delete " + methodToDelete + " ?"));
                                    int confirmDelete = JOptionPane.showConfirmDialog(guiWindow, displayConfirmationMessage, "Delete ?", OK_CANCEL_OPTION);
                                    if(confirmDelete == OK_OPTION){
                                        System.out.println("This is the fieldtodelete selected: " + methodName);
                                        Controller.deleteMethod(className, methodName);
                                        System.out.println("This is the passed in class name to delete from: " + className);
                                    }

                                }
                                else{
                                    return;
                                }





                                isAClassOption = true;
                            }
                            else{
                                if(chosenClass == JOptionPane.CANCEL_OPTION || chosenClass == JOptionPane.CLOSED_OPTION){
                                    break;
                                }
                            }
                        }

                    }

                }





                   /* if (fieldOrMethod == 1) {
                    String className = JOptionPane.showInputDialog("What is the name of the class you want to remove a field from?");
                    String fieldName = JOptionPane.showInputDialog("What is the name of the field you wish to delete?");
                    //Controller delete field method is called with user inputs
                    Controller.deleteField(className, fieldName);
                    /*if (Controller.deleteField(className, fieldName)) {
                        System.out.println("Field " + fieldName + " was removed from class " + className);
                    } else {
                        System.out.println("Failed to delete field. Please try again");
                    } */


              /*  } else if (fieldOrMethod == 2) {
                    String className = JOptionPane.showInputDialog("What is the name of the class you want to remove a Method from?");
                    String methodName = JOptionPane.showInputDialog("What is the name of the method you wish to delete?");
                    //Deletes Method with input from user
                    Controller.deleteMethod(className, methodName);

                    /*if (Controller.deleteMethod(className, methodName)) {
                        System.out.println("Method " + methodName + " was removed from class " + className);
                    } else {
                        System.out.println("Failed to delete method. Please try again");
                    } */

                 /* } else {
                    fieldOrMethodAsString = JOptionPane.showInputDialog(" Invalid input, please try again. \n Are you wanting to delete a field or a method? \n" +
                            "Type 1 for 'field' or 2 for 'method'");
                } */
                    //changes

            }
        });
        renameAtt = new JMenuItem(new AbstractAction("Rename Attribute") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(16);
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
                Controller.renameParam(classWMethod, methodName, paramName, newParamName);

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
                //System.exit(1);
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
        guiWindow.setLayout(null);
        guiWindow.getContentPane().setLayout(null);
        guiWindow.setVisible(true);
        //Main.mainContainer.pack();
        guiWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                Main.gview = false;
                if (!Main.cview) {
                    int a = JOptionPane.showConfirmDialog(guiWindow, "Are you sure you want to exit?", "Question", YES_NO_OPTION);
                    if (a == 0) {
                        System.exit(0);
                    }
                }
            }
        });


        Controller.addClass("test", 1);
        Controller.addField("test", "chicken", 1, "quack");
        Controller.addField("test", "chicken2", 1, "quack quack");
        LinkedList<String> ps = new LinkedList<>();
        ps.add("p1");
        ps.add("p2");
        Controller.addMethod("test", "pizza", 1, "pizzaret", ps);

        Controller.addClass("test2", 2);
        Controller.addField("test2", "chicken", 1, "wee snaw");
        LinkedList<String> ps2 = new LinkedList<>();
        ps2.add("p1");
        ps2.add("p2");
        Controller.addMethod("test2", "pizza", 1, "pizzaret", ps2);
        Controller.addMethod("test2", "pizza2", 1, "pizzaret", ps2);

        ClassPanel testClass = new ClassPanel(Controller.listAllClassDetails("test"),0,0);
        ClassPanel testClass2 = new ClassPanel(Controller.listAllClassDetails("test2"), 600, 300);
        GUI.classes.add(testClass);
        GUI.classes.add(testClass2);




        while (!Main.cview) {
            ;
        }

    }

    public void handleDrag(final JPanel panel){
        final JPanel p = panel;
        panel.addMouseMotionListener(new MouseMotionAdapter() {

           @Override
           public void mouseDragged(MouseEvent me){
               me.translatePoint(me.getComponent().getLocation().x, me.getComponent().getLocation().y);
               p.setLocation(me.getX(), me.getY());
            }

        });

    }





    public static void drawClassPanel(final ClassPanel c) {
        guiWindow.getContentPane().add(c);
        guiWindow.getContentPane().revalidate();
        guiWindow.getContentPane().repaint();
    }

    public static void displayGUI() {
        //SwingUtilities.updateComponentTreeUI(guiWindow);
        //guiWindow.add(new ShapeDrawing());
        //guiWindow.setVisible(true);
        guiWindow.getContentPane().removeAll();
        String[][][] classes = Controller.listEveryClassAndAllDetails();
        GUI.classes.clear();

        int x = 0;

        for(String[][] c : classes) {
            GUI.classes.add(new ClassPanel(c,x,0));
            x += 200;
        }

        for (ClassPanel c : GUI.classes)
            drawClassPanel(c);


        //guiWindow.invalidate();
        //guiWindow.repaint();

        guiWindow.setLayout(null);

        //ShapeDrawing test;

        //Issue: graphicy2 doesn't pass in correctly as it says g2, which is what graphicy below gets, is null.
        /*for(int i = 0; i < classNames.length; i++){
            //ShapeDrawing classBoxy = new ShapeDrawing();
            ShapeDrawing test = new ShapeDrawing();
            test.setBorder(new LineBorder(Color.BLUE, 3));
            //Graphics2D graphicy2 = classBoxy.getGraphicy();
            //classBoxy.drawClass(classNames[i], 0, 0, graphicy2);
            guiWindow.add(test);
        } */

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
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    myX = getX();
                    myY = getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });
            addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    setLocation(myX + deltaX, myY + deltaY);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                }

            });
        }

    }


    public static class ShapeDrawing extends JComponent {
        //Start of blobs
        private volatile int screenX = 0;
        private volatile int screenY = 0;
        private volatile int myX = 0;
        private volatile int myY = 0;
        //End of blobs

        public ShapeDrawing() {
            super();

            // code blob error report: Same as below unfortunately :<
            //Makes a Shapedrawing moveable
            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    myX = getX();
                    myY = getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });
            addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    setLocation(myX + deltaX, myY + deltaY);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                }

            });

        }

        //Made to pass up a graphic to the for loop I tried in displayGUI
        //Graphics2D graphicy;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            //Spacing out based on the number of classes
            int numberOfClasses = Controller.getCreatedClassesSize();
            // number of total spaces including class boxes and empty spaces in between
            // If there are 3 classes, it has 3 spots with one classbox space between each box
            int totalSpace = (2 * numberOfClasses) + 1;
            //Overall width of panel divided by spots
            int spaceWidth = 1000 / totalSpace;
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

            if (classNames.length == 0)
                return;

            int lastNameNumber = classNames.length - 1;
            drawClass(classNames[lastNameNumber], curx, 200, g2);


            for (int i = 0; i < numberOfClasses; i++) {
                //issue report with the mouse listeners in the if/else below:
                // Classes are duplicated in display, so for 1 class, 2 show up. They're in weirdly bounded boxes,
                // and the components in each box are not moveable
                if (i % 2 == 0) {
                    drawClass(classNames[i], curx, 200, g2);

                    coords.add(curx);
                    coords.add(200);
                } else {
                    drawClass(classNames[i], curx, 400, g2);
                    coords.add(curx);
                    coords.add(400);
                }
                curx += (1.5 * spaceWidth);
            }
            String[] classes = Controller.listClasses();
            //Prints the line for each relationship
            int rcount = 0;
            for (int i = 0; i < Controller.getCreatedClassesSize(); i++) {
                String[][] relationships = Controller.listRelationships();
                for (int j = 0; j < relationships[i].length; j++) {
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
                        } else {
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
                        class2YCoords += 10 * rcount - 20;
                        int[] xpoints = {class2XCoords, class2XCoords - 10, class2XCoords, class2XCoords + 10};
                        int[] ypoints = {class2YCoords, class2YCoords + 10, class2YCoords + 20, class2YCoords + 10};
                        int npoints = 4;
                        if (relationshipType.equals("aggregates")) {
                            g2.drawPolygon(xpoints, ypoints, npoints);
                        } else {
                            g2.fillPolygon(xpoints, ypoints, npoints);
                        }
                    } else {
                        if (class1YCoords == class2YCoords) {
                            drawDashedLine(g2, class1XCoords, class1YCoords + (rcount * 10), class1XCoords, class1YCoords - 20);
                            drawDashedLine(g2, class1XCoords, class1YCoords - 20, class2XCoords, class2YCoords - 20);
                            drawDashedLine(g2, class2XCoords, class2YCoords + (rcount * 10), class2XCoords, class2YCoords - 20);
                        } else {
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
                        class2YCoords += 10 * rcount - 20;
                        int[] xpoints = {class2XCoords, class2XCoords - 10, class2XCoords + 10};
                        int[] ypoints = {class2YCoords + 20, class2YCoords, class2YCoords};
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

        public int getClassWidth(String className, Graphics2D g2) {
            String[][] classDetails = Controller.listAllClassDetails(className);
            int width = g2.getFontMetrics().stringWidth(className);
            //Set width to largest of the attribute toStrings
            for (int i = 0; i < classDetails[Controller.DETAILS_METHODS].length; i++) {
                if (g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_METHODS][i]) > width) {
                    width = g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_METHODS][i]) + 10;
                }
            }
            for (int i = 0; i < classDetails[Controller.DETAILS_FIELDS].length; i++) {
                if (g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_FIELDS][i]) > width) {
                    width = g2.getFontMetrics().stringWidth(classDetails[Controller.DETAILS_FIELDS][i]) + 10;
                }
            }

            String classType = "<<" + classDetails[Controller.DETAILS_NAME_TYPE][1].toLowerCase() + ">>";
            if (width < g2.getFontMetrics().stringWidth(classType)) {
                width = g2.getFontMetrics().stringWidth(classType);
            }
            //Add 10 to width for nice spacing
            width += 10;
            return width;
        }

        //Draws the class boxes
        public void drawClass(String className, int x, int y, Graphics2D g2) {
            //issue report with the mouse listeners in the if/else below:
            // Classes are duplicated in display, so for 1 class, 2 show up. They're in weirdly bounded boxes,
            // and the components in each box are not moveable

            //number of fields and methods
            String[][] classDetails = Controller.listAllClassDetails(className);
            int height = 15 * (classDetails[Controller.DETAILS_METHODS].length + classDetails[Controller.DETAILS_FIELDS].length + 2);
            int width = getClassWidth(className, g2);
            //If the box is not a class, it needs a special header above the name
            boolean isClass = classDetails[Controller.DETAILS_NAME_TYPE][1].equals("CLASS");
            if (!isClass)
                height += 15;
            //Outer rectangle
            g2.drawRect(x, y, width, height);
            //If the box is not a class, it needs a special header above the name
            String classType = "<<" + classDetails[Controller.DETAILS_NAME_TYPE][1].toLowerCase() + ">>";
            if (!isClass) {
                g2.drawString(classType, x + width / 2 - g2.getFontMetrics().stringWidth(classType) / 2, y + 15);
                y += 15;
            }
            //Write Class name
            g2.drawString(className, x + ((width / 2) - (g2.getFontMetrics().stringWidth(className) / 2)), y + 15);
            //Draw line under the name
            g2.drawLine(x, y + 17, x + width, y + 17);
            //moves down twice the spacing of above
            y = y + 30;
            //For each attribute, print the gui toString
            for (int i = 0; i < classDetails[Controller.DETAILS_FIELDS].length; i++) {
                g2.drawString(classDetails[Controller.DETAILS_FIELDS][i], x + 10, y);
                y += 15;
            }
            g2.drawLine(x, y - 12, x + width, y - 12);
            for (int i = 0; i < classDetails[Controller.DETAILS_METHODS].length; i++) {
                g2.drawString(classDetails[Controller.DETAILS_METHODS][i], x + 10, y);
                //moves down 15
                y += 15;
            }

        }

        public static void drawDashedLine(Graphics2D g, int x1, int y1, int x2, int y2) {
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