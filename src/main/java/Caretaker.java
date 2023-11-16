package src.main.java;

import java.util.ArrayList;

public class Caretaker {

    private static Caretaker caretaker = null;

    private final ArrayList<ModelDiagram.Memento> stack;

    /*
        stackPointer keeps track of the "current state"
     */
    private int stackPointer;
    
    /*
        stackSize keeps track of the most updated state that we are allowed to redo to
     */
    private int stackSize;

    private Caretaker(){
        this.stackPointer = 0;
        this.stackSize = 0;
        this.stack = new ArrayList<>();
    }
    
    public static Caretaker getInstance(){
        if(caretaker == null){
            caretaker = new Caretaker();
        }
        return caretaker;
    }

    public static void push(final ModelDiagram.Memento memento){

    }

    public ModelDiagram.Memento undo(){
        return null;
    }

    public ModelDiagram.Memento redo(){
        return null;
    }
    public void updateChange(final ModelDiagram.Memento snapshot){

    }

}
