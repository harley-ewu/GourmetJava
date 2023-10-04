import org.w3c.dom.Attr;

import java.util.LinkedList;

public class ClassBox {
    private String name;
    //Possibly change to enum later?
    //Class, Interface, Enum, etc
    private String type;
    //Way to sort fields first, methods last?
    private LinkedList<Attribute> attributes;
    private LinkedList<Relationship> relationships;

    public ClassBox(String name, String type, LinkedList<Attribute> attributes, LinkedList<Relationship> relationships) {
        this.name = name;
        this.type = type;
        this.attributes = attributes;
        this.relationships = relationships;
    }

    public void addAttribute(String name, String view, LinkedList<String> tags, String returnType, LinkedList<String> params){
        attributes.add(new Attribute(name, view, tags, returnType, params));
    }

    public void deleteAttribute(String name){
        attributes.remove();
    }

    public void renameAttribute(String name){

    }

    public void addRelationship(String name, String class1, String class2, String type){

    }

    public void deleteRelationship(String name){

    }

    public void renameClass(String name){
        this.name  = name;
    }
    //For list class details
    public String toString(){
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LinkedList<Attribute> getAttributes() {
        return attributes;
    }

    public LinkedList<Relationship> getRelationships() {
        return relationships;
    }

}
