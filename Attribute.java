import java.util.LinkedList;

public class Attribute {
    private String name;
    //Private, Public, or Protected
    private String view;
    //Static, constructor, getter, etc
    private LinkedList<String> Tags;
    //return type if method, field type if field, capitalize if object
    private String type;
    //null/empty if field, only for method
    private LinkedList<String> parameters;

    public Attribute(String name, String view, LinkedList<String> tags, String type, LinkedList<String> parameters) {
        this.name = name;
        this.view = view;
        this.Tags = tags;
        this.type = type;
        this.parameters = parameters;
    }

// Getters //
    public String getName() {
        return name;
    }

    public String getView() {
        return view;
    }

    public LinkedList<String> getTags() {
        return Tags;
    }

    public String getType() {
        return type;
    }

    public LinkedList<String> getParameters() {
        return parameters;
    }

// Setters //
     public void setName(String name) {
        this.name = name;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setTags(LinkedList<String> tags) {
        this.Tags = tags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        String tempString = "";
        tempString = "Name: " + getName() + " View: " + getView() + " Tags: " 
                    + getTags() + " Type: " + getType() + " Parameters: " + getParameters();

        return tempString;
    }
}
