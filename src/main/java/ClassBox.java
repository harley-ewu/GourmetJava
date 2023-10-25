package src.main.java;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;

public class ClassBox {


    private enum ClassType {

        CLASS, INTERFACE, RECORD, ENUM, ANNOTATION;

    }
    private String name;
    //Possibly change to enum later?
    //Class, Interface, Enum, etc
    private final ClassType type;
    //Way to sort fields first, methods last?
    private LinkedList<Attribute> attributes;
    private final LinkedList<Relationship> relationships;

    private final LinkedList<Methods> methods;

    private final LinkedList<Fields> fields;

    public ClassBox(String name, int type) {
        if(name==null||name.isEmpty()||type<1||type>5) {
            throw new IllegalArgumentException("Bad params at ClassBox constructor");
        }
        else {
            this.name = name;
            this.attributes = new LinkedList<>();
            this.relationships = new LinkedList<>();
            this.type = ClassBox.ClassType.values()[type - 1];
            this.methods = new LinkedList<>();
            this.fields = new LinkedList<>();
        }
    }

    public static void printClassTypes(){
        ClassBox.ClassType[] types = ClassBox.ClassType.values();
        for(int i = 0; i < 5; ++i){
            System.out.println((i+1) + " - " + types[i].name());
        }
    }

    public void addAttribute(String name, String view, LinkedList<String> tags, String type, LinkedList<String> params){
        Attribute.AttributeType attType = null;
        view = view.toUpperCase(Locale.ROOT);
        if(params==null||params.isEmpty()){
            attType = Attribute.AttributeType.FIELD;
        }
        else{
            attType = Attribute.AttributeType.METHOD;
        }
        Attribute.ViewType viewT = Attribute.ViewType.valueOf(view);
        this.attributes.add(new Attribute(name, attType, viewT, tags, type, params));

    }

    public void deleteAttribute(Attribute a){
        this.attributes.remove(a);
    }

    public void renameAttribute(Attribute att, String newName){
        att.setName(newName);
    }

    public void addRelationship(final ClassBox toClass, final int type){
        Relationship newRel = new Relationship(toClass, type);
        this.relationships.add(newRel);
    }

    public void deleteRelationship(ClassBox otherClass){
        for (Relationship rel : this.relationships) {
            if (rel.getFrom().equals(otherClass)) {
                this.relationships.remove(rel);
                return;
            }
        }
        for (Relationship rel : otherClass.relationships) {
            if (rel.getFrom().equals(this)) {
                otherClass.relationships.remove(rel);
                return;
            }
        }
        throw new IllegalArgumentException("Relationship not found");
    }

    public String[] listRelationships(){
        String[] rels = new String[this.relationships.size()];
        for(int i = 0; i < rels.length; ++i){
            rels[i] = this.relationships.get(i).toString();
        }
        return rels;
    }

    public void renameClass(String name){
        this.name  = name;
    }
    //For list class details

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Class: ").append(this.name).append("\n");
        s.append("\tType: ").append(this.type).append("\n");
        s.append("\tAttributes: \n");
        for(Attribute a: this.attributes){
            s.append("\t\t").append(a).append("\n");
        }
        s.append("\tRelationships: \n");
        for(Relationship r: this.relationships){
            s.append("\t\t").append(this.name).append(" ").append(r).append("\n");
        }
        return s.toString();
    }

    public void sortAttributes(){
        Collections.sort(this.attributes);
    }

    public String getName() {
        return this.name;
    }

    public LinkedList<Relationship> getRelationships() {
        return this.relationships;
    }

    public LinkedList<Attribute> getAttributes() {return this.attributes;}

}
