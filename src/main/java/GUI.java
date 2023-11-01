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

        // set the size of the frame
        g.setSize(1000, 800);
        g.setPreferredSize(new Dimension(1000, 800));
        g.setResizable(false);
        g.setVisible(true);
        m.pack();
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
        displayGUI(g, cc);
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
    public static class ShapeDrawing extends JComponent{
        ArrayList<ClassBox> cc;
        public ShapeDrawing(ArrayList<ClassBox> cc){
            super();
            this.cc = cc;
        }
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            //Spacing out based on the number of classes
            int n = cc.size();
            //Spots, e.g 3 classes has 3 spots and 4 spaces between them and outside
            int k = (2*n) + 1;
            //Overall width of panel divided by spots
            int j = 1000/k;
            //Current x coordinate
            int curx = j;
            //Stores coordinates of each class when printed for relationship printing
            LinkedList<Integer> coords = new LinkedList<>();
            //Goes through all classes and prints them
            for(int i = 0; i<n; i++){
                if(i%2==0){
                    drawClass(cc.get(i), curx, 200, g2);
                    coords.add(curx);
                    coords.add(200);
                }
                else{
                    drawClass(cc.get(i), curx, 400, g2);
                    coords.add(curx);
                    coords.add(400);
                }
                curx += (1.5*j);
            }
            for (int i = 0; i<cc.size(); i++){
                for(Relationship r: cc.get(i).getRelationships()){
                    //For each relationship, retrieve the coordinates of each, and draw a line between them
                    int c1x = i*2;
                    int c1y = c1x+1;
                    int c2 = cc.indexOf(r.getFrom());
                    int c2x = c2*2;
                    int c2y = c2x+1;
                    c1x = coords.get(c1x);
                    c1y = coords.get(c1y);
                    c2x = coords.get(c2x);
                    c2y = coords.get(c2y);
                    c1x+=10;
                    c2x+=10;
                    g2.drawLine(c1x, c1y, c2x, c2y);
                    //Display the relationship type at the line's midpoint
                    g2.drawString(r.getType(), ((c1x+c2x)/2), ((c1y+c2y)/2));
                }
            }
        }
        public void drawClass(ClassBox c, int x, int y, Graphics2D g2){
            int height = 15*(c.getFields().size()+c.getMethods().size()+2);
            int width = c.getName().length();
            //Set width to largest of the attribute toStrings
            for(int i = 0; i<c.getFields().size();i++){
                if(c.getFields().get(i).GUIToString().length() > width){
                    width = c.getFields().get(i).GUIToString().length();
                }
            }
            for(int i = 0; i<c.getMethods().size();i++){
                if(c.getMethods().get(i).GUIToString().length() > width){
                    width = c.getMethods().get(i).GUIToString().length();
                }
            }
            width*=8;
            //Outer rectangle
            g2.drawRect(x,y,width,height);
            //Write Class name
            g2.drawString(c.getName(), x+5, y+15);
            //Draw line under the name
            g2.drawLine(x,y+15,x+width, y+15);
            y = y+30;
            //For each attribute, print the gui toString
            for(int i=0;i<c.getFields().size();i++){
                g2.drawString(c.getFields().get(i).GUIToString(), x+10, y);
                y+=15;
            }
            for(int i=0;i<c.getMethods().size();i++){
                g2.drawString(c.getMethods().get(i).GUIToString(), x+10, y);
                y+=15;
            }
        }
    }
}
