//package src.main.java;

import java.lang.reflect.Method;
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
    private final LinkedList<Relationship> relationships;

    private final LinkedList<Methods> methods;

    private final LinkedList<Field> fields;

    public ClassBox(String name, int type) {
        if(name==null||name.isEmpty()||type<1||type>5) {
            throw new IllegalArgumentException("Bad params at ClassBox constructor");
        }
        else {
            this.name = name;
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

    public void addMethod(String name, Visibility view, LinkedList<String> tags, String type, LinkedList<String> params){
        //call the constructor and add to list
        Methods newMethod = new Methods(name, view, tags, type, params);
        this.methods.add(newMethod);        
    }

    public void addField(String name, Visibility view, LinkedList<String> tags, String type){
        //call the constructor and add to list
        Field newField = new Field(name, view, tags, type);
        this.fields.add(newField);
    }

    public void deleteAttribute(Attribute a){
        if(a.getClass().getSimpleName().equals("Method")){
            this.methods.remove((Methods) a);
        }
        else{
            this.fields.remove((Field) a);
        }
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
        s.append("\tFields: \n");
        for(Field a: this.fields){
            s.append("\t\t").append(a).append("\n");
        }
        s.append("\tMethods: \n");
        for(Method a: this.methods){
            s.append("\t\t").append(a).append("\n");
        }
        s.append("\tRelationships: \n");
        for(Relationship r: this.relationships){
            s.append("\t\t").append(this.name).append(" ").append(r).append("\n");
        }
        return s.toString();
    }

    public String getName() {
        return this.name;
    }

    public LinkedList<Relationship> getRelationships() {
        return this.relationships;
    }

    public LinkedList<Method> getMethods() {
        return methods;
    }

    public LinkedList<Field> getFields() {
        return fields;
    }
}
