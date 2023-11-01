package src.main.java;

import java.util.LinkedList;

public abstract class Attribute implements Comparable<Attribute> {

    enum AttributeType {
        METHOD,
        FIELD;
    }

    private String name;
    //public, private, protected
    private Visibility view;
    //Stuff like static
    private LinkedList<String> modifiers;
    //Return type if method, value type if value


    public Attribute(String name, Visibility view, LinkedList<String> tags) {
        if (name == null || name.isEmpty() || view == null || tags == null) {
            throw new IllegalArgumentException("Bad params at Attribute constructor");
        }
        this.name = name;
        this.view = view;
        this.modifiers = tags;
    }

    public void setName(String newName) {
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("Bad name at attribute setName");
        }
        this.name = newName;
    }

    public void setView(Visibility newView) {
        if (newView == null) {
            throw new IllegalArgumentException("Bad view at attribute setView");
        }
        this.view = newView;
    }

    public void setModifiers(LinkedList<String> newModifiers) {
        if (newModifiers == null) {
            throw new IllegalArgumentException("Bad modifiers at attribute setModifiers");
        }
        this.modifiers = newModifiers;
    }

    public String getName() {
        return this.name;
    }

    public Visibility getView() {
        return this.view;
    }

    public LinkedList<String> getModifiers() {
        return this.modifiers;
    }

    @Override
    public int compareTo(Attribute a) {
        return this.name.compareTo(a.name);
    }

    //compare by name if field, by name and params if method
    abstract boolean equalTo(Attribute another);
    //will have symbols for view
    abstract public String GUIToString();
    //view as word
    abstract public String CLIToString();
}
