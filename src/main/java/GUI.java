package src.main.java;
    // Java program to construct
// Menu bar to add menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
