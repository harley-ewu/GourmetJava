package src.main.java;

import java.util.ArrayList;

public class Caretaker {

    private final static ArrayList<ModelDiagram.Memento> stack = new ArrayList<>();
    private static int stackPointer = 0;

    private static int stackSize = 0;

    private Caretaker(){

    }

    public static void push(final ModelDiagram.Memento memento){

    }

    public static ModelDiagram.Memento pop(){
        return null;
    }

}
