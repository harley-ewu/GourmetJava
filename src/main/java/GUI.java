package src.main.java;

// Java program to construct
// Menu bar to add menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GUI extends JFrame implements ActionListener{
    // menubar
    static JMenuBar mb;

    // JMenu
    static JMenu a,b,c,d,e,f;

    // Menu items
    static JMenuItem a1,b1,b2,b3,c1,c2,c3,d1,d2,e1,e2,f1;


    // create a frame
    static JFrame g;

    //public static void startGUIMenu(){
    public static void main(String[] args){
        GUI m = new GUI();
        // create a frame
        g = new JFrame("Menu demo");

        // create a menubar
        mb = new JMenuBar();

        a = new JMenu("Display");
        a1 = new JMenuItem("Display");
        a1.addActionListener(m);
        a.add(a1);
        b = new JMenu("Class");
        b1 = new JMenuItem("Add Class");
        b2 = new JMenuItem("Delete Class");
        b3 = new JMenuItem("Rename Class");
        b1.addActionListener(m);
        b2.addActionListener(m);
        b3.addActionListener(m);
        b.add(b1);
        b.add(b2);
        b.add(b3);
        c = new JMenu("Attribute");
        c1 = new JMenuItem("Add Class");
        c2 = new JMenuItem("Delete Class");
        c3 = new JMenuItem("Rename Class");
        c1.addActionListener(m);
        c2.addActionListener(m);
        c3.addActionListener(m);
        c.add(c1);
        c.add(c2);
        c.add(c3);
        d = new JMenu("Relationship");
        d1 = new JMenuItem("Add Relationship");
        d2 = new JMenuItem("Delete Relationship");
        d1.addActionListener(m);
        d2.addActionListener(m);
        d.add(d1);
        d.add(d2);
        e = new JMenu("Save/Load");
        e1 = new JMenuItem("Save");
        e2 = new JMenuItem("Load");
        e1.addActionListener(m);
        e2.addActionListener(m);
        e.add(e1);
        e.add(e2);
        f = new JMenu("Help");
        f1 = new JMenuItem("Help");
        f.add(f1);
        f1.addActionListener(m);
        // add menu to menu bar
        mb.add(a);
        mb.add(b);
        mb.add(c);
        mb.add(d);
        mb.add(e);
        mb.add(f);

        // add menubar to frame
        g.setJMenuBar(mb);

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
        cc.add(c1);
        cc.add(c2);
        displayGUI(g, cc);
    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("Add Class")){
            //I/o, then call method
        }
    }
    public static void displayGUI(JFrame j, ArrayList<ClassBox> createdClasses){
        j.getContentPane().add(new ShapeDrawing(createdClasses));
        j.setVisible(true);
    }
    public static class ShapeDrawing extends JComponent{
        ArrayList<ClassBox> cc;
        public ShapeDrawing(ArrayList<ClassBox> cc){
            super();
            this.cc = cc;
        }
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            drawClass(cc.get(0), 300, 400, g2);
            drawClass(cc.get(1), 600, 400, g2);
        }
        public void drawClass(ClassBox c, int x, int y, Graphics2D g2){
            int height = 15*(c.getFields().size()+c.getMethods().size()+2);
            int width = c.getName().length();
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
            g2.drawRect(x,y,width,height);
            g2.drawString(c.getName(), x+5, y+15);
            g2.drawLine(x,y+15,x+width, y+15);
            y = y+30;
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
