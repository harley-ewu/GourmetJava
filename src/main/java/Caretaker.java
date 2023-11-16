package src.main.java;

import java.util.ArrayList;

public class Caretaker {

    private final static ArrayList<ModelDiagram.Memento> stack = new ArrayList<>();

    /*
        stackPointer keeps track of the "current state"
     */
    private static int stackPointer = 0;

    /*
        stackSize keeps track of the most updated state that we are allowed to redo to
     */
    private static int stackSize = 0;

    private Caretaker(){

    }

    public static void push(final ModelDiagram.Memento memento){

    }

    public static ModelDiagram.Memento undo(){
        return null;
    }

    public static ModelDiagram.Memento redo(){
        return null;
    }
    public static void updateChange(final ModelDiagram.Memento snapshot){

    }

}
