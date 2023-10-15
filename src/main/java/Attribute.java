package src.main.java;

// import java.util.LinkedList;

public class Attribute {
    private String name;

    public Attribute(String name) {
        this.name = name;
    }

// Getters //
    public String getName() {
        return name;
    }

// Setters //
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        this.name = name;
    }

    public String toString(){
        String tempString = "";
        tempString = "Name: " + getName();
        return tempString;
    }
}
