//package src.main.java;

import java.util.LinkedList;

public class Field extends Attribute{
    //type of field
    private String type;
    public Field(String name, Visibility view, LinkedList<String> tags, String type) {
        super(name, view, tags);
        this.type = type;
    }

    @Override
    public String CLIToString() {
        return super.getName() + " " + super.getView() + " " + super.getModifiers() + " " + type;
    }

    @Override
    public String GUIToString() {
        throw new UnsupportedOperationException("Unimplemented method 'GUIToString'");
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
