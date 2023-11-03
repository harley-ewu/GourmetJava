package src.main.java;

import java.util.LinkedList;

public class Methods extends Attribute{
    //parameter types
    private LinkedList<String> paramTypes;
    //return type
    private String returnType;

    public Methods(String name, Visibility view, String type, LinkedList<String> params) {
        super(name, view);

        this.paramTypes = params;
        this.returnType = type;
    }

    public void deleteParam(String param) {
        for (int i = 0; i < paramTypes.size(); i++) {
            if (paramTypes.get(i).equals(param)) {
                paramTypes.remove(i);
            }
        }
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
        StringBuilder s = new StringBuilder(this.getView().name().toLowerCase() + " ");
        s.append(this.getName());
        s.append("(");
        for(int i = 0; i<this.getParamTypes().size()-1; i++){
            s.append(this.getParamTypes().get(i)).append(", ");
        }
        s.append(this.getParamTypes().get(this.getParamTypes().size()-1));
        s.append(")");
        return s.toString();
    }

    @Override
    public String GUIToString() {
        StringBuilder s = new StringBuilder(this.getView().getSymbol() + " ");
        s.append(this.getName());
        s.append("(");
        for(int i = 0; i<this.getParamTypes().size()-1; i++){
            s.append(this.getParamTypes().get(i)).append(", ");
        }
        s.append(this.getParamTypes().get(this.getParamTypes().size()-1));
        s.append(")");
        return s.toString();
    }
}
