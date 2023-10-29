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

    @Override
    public boolean equalTo(Attribute another) {
        if (another instanceof Method) {
            Method other = (Method) another;
            if (this.getName().equals(other.getName()) && this.paramTypes.equals(other.paramTypes)) {
                return true;
            }
        }
        return false;
    }

    // to string for CLI    
    @Override
    public String CLIToString() {
        return super.toString() +  "Parameter Types: " + paramTypes.toString() + "Return Type: " + returnType;
    }

    @Override
    public String GUIToString() {
        throw new UnsupportedOperationException("Unimplemented method 'GUIToString'");

        return "tempString fix this";
    }

}
