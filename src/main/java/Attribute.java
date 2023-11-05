package src.main.java;

import java.util.Arrays;
import java.util.LinkedList;

public abstract class Attribute implements Comparable<Attribute> {

    private enum AttributeType {
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

    public Attribute(final String name, final int viewType) {
        if (name == null || name.isEmpty() || viewType < 1 || viewType > Visibility.values().length)
            throw new IllegalArgumentException("Bad params at Attribute constructor");

        this.name = name;
        this.view = Visibility.values()[viewType - 1];

    }

    public boolean setName(String newName) {
        if (newName == null || newName.isEmpty())
            return false;

        this.name = newName;
        return true;
    }

    public boolean setView(Visibility newView) {
        if (newView == null)
            return false;

        this.view = newView;
        return true;
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

    public static String[] listAttributeTypes() {
        return Arrays.stream(AttributeType.values()).map(Enum::name).toArray(String[]::new);
    }

    /*
    Returns the visibility symbol of the attribute and then the name
    Ex: "+myMethod"
     */
    @Override
    public String toString() {
        return this.view.getSymbol() + this.name;
    }

}
