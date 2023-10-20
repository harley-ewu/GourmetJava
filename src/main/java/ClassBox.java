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
            System.out.println((i+1) + " - " + types[i].name());
        }
    }
    //public Attribute(String name, attType mOV, ViewType view, LinkedList<String> tags, String type, LinkedList<String> params) {
    public void addAttribute(String name, String view, LinkedList<String> tags, String type, LinkedList<String> params){
        Attribute.attType attType = null;
        view = view.toUpperCase(Locale.ROOT);
        if(params==null||params.isEmpty()){
            attType = Attribute.attType.VALUE;
        }
        else{
            attType = Attribute.attType.METHOD;
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
        Relationship newRel = new Relationship(toClass,type);
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

    public void listRelationships(){
        for (Relationship rel : this.relationships){
            System.out.println(this.name+" "+rel.toString());
        }
    }

    public void renameClass(String name){
        this.name  = name;
    }
    //For list class details
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
        //make 2 sublists, first is values, second is relationships, then sort the two based on name
        LinkedList<Attribute> valList = new LinkedList<>();
        LinkedList<Attribute> metList = new LinkedList<>();
        for(Attribute a: this.attributes){
            if(a.getmOV().equals(Attribute.attType.VALUE)){
                valList.add(a);
            }
            else{
                metList.add(a);
            }
        }
        Collections.sort(valList);
        Collections.sort(metList);
        LinkedList<Attribute> sortedList = new LinkedList<>();
        sortedList.addAll(valList);
        sortedList.addAll(metList);
        this.attributes = sortedList;
    }

    public String getName() {
        return this.name;
    }

    public LinkedList<Relationship> getRelationships() {
        return this.relationships;
    }

    public LinkedList<Attribute> getAttributes() {return this.attributes;}

}
