package src.main.java;

import java.util.Arrays;

public abstract class Attribute implements Comparable<Attribute>, Cloneable{

    @Override
    public Attribute clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

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

    public void setName(String newName) {
        if (newName == null || newName.isEmpty())
            throw new IllegalArgumentException("Bad string passed to Attribute.setName()");

        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public int getView() {
        return this.view.ordinal();
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
