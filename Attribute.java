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

    public String toString(){
        return "";
    }
}
