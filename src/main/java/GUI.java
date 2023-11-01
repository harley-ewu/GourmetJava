//package src.main.java;
    // Java program to construct
// Menu bar to add menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener{
    // Creates a dropdown style menu framework at the top of the frame
    static JMenuBar mainMenu;

    // Creates individual dropdown menus for each category within the overall menu
    static JMenu displayDropdown,classDropdown,attributeDropdown,relationshipDropdown,saveLoadDropdown,helpDropdown;

    // Individual menu items/buttons under their individual category menus
    static JMenuItem display,addClass,deleteClass,renameClass,addAtt,delAtt,renameAtt,addRelation,delRelation,save,load,help;


    //creates a frame to be the main, base window to hold the entirety of the GUI
    static JFrame guiWindow;

    //public static void startGUIMenu(){
    public static void main(String[] args){
        GUI m = new GUI();

        guiWindow = new JFrame("UML Editor");

        // create a menubar
        mainMenu = new JMenuBar();

        displayDropdown = new JMenu("Display");
        display = new JMenuItem("Display");
        display.addActionListener(m);
        displayDropdown.add(display);
        classDropdown = new JMenu("Class");
        addClass = new JMenuItem("Add Class");
        deleteClass = new JMenuItem("Delete Class");
        renameClass = new JMenuItem("Rename Class");
        addClass.addActionListener(m);
        deleteClass.addActionListener(m);
        renameClass.addActionListener(m);
        classDropdown.add(addClass);
        classDropdown.add(deleteClass);
        classDropdown.add(renameClass);
        attributeDropdown = new JMenu("Attribute");
        addAtt = new JMenuItem("Add Attribute");
        delAtt = new JMenuItem("Delete Attribute");
        renameAtt = new JMenuItem("Rename Attribute");
        addAtt.addActionListener(m);
        delAtt.addActionListener(m);
        renameAtt.addActionListener(m);
        attributeDropdown.add(addAtt);
        attributeDropdown.add(delAtt);
        attributeDropdown.add(renameAtt);
        relationshipDropdown = new JMenu("Relationship");
        addRelation = new JMenuItem("Add Relationship");
        delRelation = new JMenuItem("Delete Relationship");
        addRelation.addActionListener(m);
        delRelation.addActionListener(m);
        relationshipDropdown.add(addRelation);
        relationshipDropdown.add(delRelation);
        saveLoadDropdown = new JMenu("Save/Load");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        save.addActionListener(m);
        load.addActionListener(m);
        saveLoadDropdown.add(save);
        saveLoadDropdown.add(load);
        helpDropdown = new JMenu("Help");
        /*Maybe take out the dropdown part of "help" since there's just one component.
         that isn't exactly needed to be repeated if we can get around it*/
        help = new JMenuItem("Help");
        helpDropdown.add(help);
        help.addActionListener(m);
        // add individual dropdown menus to menu bar
        mainMenu.add(displayDropdown);
        mainMenu.add(classDropdown);
        mainMenu.add(attributeDropdown);
        mainMenu.add(relationshipDropdown);
        mainMenu.add(saveLoadDropdown);
        mainMenu.add(helpDropdown);

        // add menubar to our main GUI display frame
        guiWindow.setJMenuBar(mainMenu);

        // set the size of the main GUI frame
        guiWindow.setSize(1000, 800);
        guiWindow.setPreferredSize(new Dimension(1000, 800));
        guiWindow.setResizable(false);
        guiWindow.setVisible(true);
        m.displayGUI();
    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("Add Class")){
            //I/o, then call method
        }
    }
    public void displayGUI(){
        System.out.println(this.getContentPane().getSize().getWidth());
        System.out.println(this.getContentPane().getSize().getHeight());
    }
}
