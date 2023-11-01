package src.main.java;

import java.util.LinkedList;

public class Field extends Attribute{
    //type of field
    private String type;
    public Field(String name, Visibility view, String type) {
        super(name, view);
        this.type = type;
    }

    @Override
    public String CLIToString() {
        StringBuilder s = new StringBuilder(this.getView().name().toLowerCase() + " ");
        s.append(this.getName());
        return s.toString();
    }

    @Override
    public String GUIToString() {
        StringBuilder s = new StringBuilder(this.getView().getSymbol() + " ");
        s.append(this.getName());
        return s.toString();
    }

    @Override
    boolean equalTo(Attribute another) {
        if (another instanceof Field) {
            Field other = (Field) another;
            if (this.getName().equals(other.getName()) && this.type.equals(other.type)) {
                return true;
            }
        }
        return false;
    }    

}
