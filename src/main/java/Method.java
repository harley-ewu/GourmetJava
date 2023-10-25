package src.main.java;

import java.util.LinkedList;

public class Method extends Attribute{
    //parameter types
    private LinkedList<String> paramTypes;
    //return type
    private String returnType;

    public Method(String name, Visibility view, LinkedList<String> tags, String type, LinkedList<String> params) {
        super(name, view, tags);
        this.paramTypes = params;
        this.returnType = type;
    }
        /*

        tostring for GUI
        tostring for CLI
     */

}
