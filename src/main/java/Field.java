package src.main.java;

import java.util.LinkedList;

public class Field extends Attribute{
    //type of field
    private String type;
    public Field(String name, Visibility view, LinkedList<String> tags, String type) {
        super(name, view, tags);
        this.type = type;
    }

}
