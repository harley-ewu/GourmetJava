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

    public Attribute(String name, Visibility view) {
        if (name == null || name.isEmpty() || view == null) {
            throw new IllegalArgumentException("Bad params at Attribute constructor");
        }
        this.name = name;
        this.view = view;
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

    public String getName() {
        return this.name;
    }

    public Visibility getView() {
        return this.view;
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
