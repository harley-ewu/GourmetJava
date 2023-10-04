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
        Attribute a = null;
        for (Attribute attribute : this.attributes) {
            if (attribute.getName().equals(name)) {
                a = attribute;
            }
        }
        if(a==null){
            System.out.println("Error: Attribute not found");
        }
        else {
            this.attributes.remove(a);
        }
    }

    public void renameAttribute(String att, String newName){
        Attribute a = null;
        for (Attribute attribute : this.attributes) {
            if (attribute.getName().equals(att)) {
                a = attribute;
            }
        }
        if(a==null){
            System.out.println("Error: Attribute not found");
        }
        else {
            a.setName(name);
        }
    }

    public void addRelationship(String name, ClassBox class1, ClassBox class2, String type, String fromNum, String toNum){
        this.relationships.add(new Relationship(name, class1, class2, type, fromNum, toNum));
    }

    public void deleteRelationship(String name){
        Relationship r = null;
        for (Relationship relationship : this.relationships) {
            if (relationship.getName().equals(name)) {
                r = relationship;
            }
        }
        if(r==null){
            System.out.println("Error, relationship not found");
        }
        else{
            this.relationships.remove(r);
        }
    }

    public void renameClass(String name){
        this.name  = name;
    }
    //For list class details
    public String toString(){
        System.out.println("Class: "+this.name);
        System.out.println("Type: "+this.type);
        System.out.println("Attributes: ");
        for(Attribute a: this.attributes){
            System.out.println("\t"+a);
        }
        System.out.println("Relationships: ");
        for(Relationship r: this.relationships){
            System.out.println("\t"+r);
        }
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
