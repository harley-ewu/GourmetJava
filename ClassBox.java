import org.w3c.dom.Attr;

import java.util.LinkedList;
public class ClassBox {
    private enum ClassType {

        CLASS, INTERFACE, RECORD, ENUM, ANNOTATION;

    }
    private String name;
    //Possibly change to enum later?
    //Class, Interface, Enum, etc
    private ClassType type;
    //Way to sort fields first, methods last?
    private LinkedList<Attribute> attributes;
    private LinkedList<Relationship> relationships;

    public ClassBox(String name, int type) {
        if(name==null||name.isEmpty()||type<1||type>5) {
            throw new IllegalArgumentException("Bad params at ClassBox constructor");
        }
        else {
            this.name = name;
            this.attributes = new LinkedList<>();
            this.relationships = new LinkedList<>();
            this.type = ClassBox.ClassType.values()[type - 1];
        }
    }

    public static void printClassTypes(){
        ClassBox.ClassType[] types = ClassBox.ClassType.values();
        for(int i = 0; i < 5; ++i){
            System.out.println((i) + " - " + types[i].name());
        }
    }

    public void addAttribute(String name){
        attributes.add(new Attribute(name));
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
            a.setName(newName);
        }
    }

    public static void addRelationship(final ClassBox class1, final ClassBox class2, final int type){
        if(class1 == null || class2 == null || type<1||type>6){
            throw new IllegalArgumentException("Bad object passed to addRelationship");
        }
        Relationship newRel = new Relationship(class1,class2,type);
        class1.relationships.add(newRel);
        class2.relationships.add(newRel);
    }

    public void deleteRelationship(ClassBox otherClass){
        Relationship r = null;
        for (Relationship relationship : this.relationships) {
            if (relationship.getFrom().equals(otherClass)||relationship.getTo().equals(otherClass)) {
                r = relationship;
            }
        }
        if(r==null){
            System.out.println("Error, relationship not found"); //lol
        }
        else{
            this.relationships.remove(r);
            otherClass.relationships.remove(r);
        }
    }

    public void renameClass(String name){
        this.name  = name;
    }
    //For list class details
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Class: ").append(this.name).append("\n");
        s.append("Type: ").append(this.type).append("\n");
        s.append("Attributes: \n");
        for(Attribute a: this.attributes){
            s.append("\t").append(a);
        }
        s.append("Relationships: \n");
        for(Relationship r: this.relationships){
            s.append("\t").append(r);
        }
        return s.toString();
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type.name();
    }

    public LinkedList<Attribute> getAttributes() {
        return this.attributes;
    }

    public LinkedList<Relationship> getRelationships() {
        return this.relationships;
    }

}
