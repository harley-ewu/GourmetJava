//package src.main.java;

import java.util.LinkedList;

public class Methods extends Attribute{
    //parameter types
    private LinkedList<String> paramTypes;
    //return type
    private String returnType;

    public Methods(String name, Visibility view, LinkedList<String> tags, String type, LinkedList<String> params) {
        super(name, view, tags);
        this.paramTypes = params;
        this.returnType = type;
    }

    public void setParamTypes(LinkedList<String> newParamTypes) {
        if (newParamTypes == null) {
            throw new IllegalArgumentException("Bad paramTypes at Methods setParamTypes");
        }
        this.paramTypes = newParamTypes;
    }

    public LinkedList<String> getParamTypes() {
        return this.paramTypes;
    }

    @Override
    public boolean equalTo(Attribute another) {
        if (another instanceof Methods) {
            Methods other = (Methods) another;
            if (this.getName().equals(other.getName()) && this.paramTypes.equals(other.paramTypes)) {
                return true;
            }
        }
        return false;
    }

    // to string for CLI    
    @Override
    public String CLIToString() {
        return super.getName() + " " + super.getView() + " " + super.getModifiers() +  "Parameter Types: " + paramTypes.toString() + "Return Type: " + returnType;
    }

    @Override
    public String GUIToString() {
        throw new UnsupportedOperationException("Unimplemented method 'GUIToString'");
    }
}
