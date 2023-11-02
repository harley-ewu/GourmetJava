package src.main.java;

import java.util.LinkedList;

public class ClassBox {

    public boolean equals(final ClassBox cb) {
        return this.equals(cb.getName());
    }

    public boolean equals(final String name) {
        return this.getName().equals(name);
    }

    private enum ClassType {

        CLASS, INTERFACE, RECORD, ENUM, ANNOTATION;

    }

    private String name;
    //Possibly change to enum later?
    //Class, Interface, Enum, etc
    private final ClassType type;
    //Way to sort fields first, methods last?
    private final LinkedList<Relationship> parents = new LinkedList<>();

    private final LinkedList<Relationship> children = new LinkedList<>();

    private final LinkedList<Methods> methods = new LinkedList<>();

    private final LinkedList<Field> fields = new LinkedList<>();

    public ClassBox(String name, int type) {
        if (name == null || name.isEmpty() || type < 1 || type > ClassType.values().length)
            throw new IllegalArgumentException("Bad params at ClassBox constructor");

        this.name = name;
        this.type = ClassBox.ClassType.values()[type - 1];
    }

    public static void printClassTypes() {
        ClassBox.ClassType[] types = ClassBox.ClassType.values();
        for (int i = 0; i < 5; ++i) {
            System.out.println((i + 1) + " - " + types[i].name());
        }
    }

    public void addMethod(String name, Visibility view, String type, LinkedList<String> params){
        //call the constructor and add to list
        Methods newMethod = new Methods(name, view, type, params);
        this.methods.add(newMethod);        
    }

    public void addField(String name, Visibility view, String type){
        //call the constructor and add to list
        Field newField = new Field(name, view, type);
        this.fields.add(newField);
    }

    public void deleteAttribute(Attribute a) {
        if (a.getClass().getSimpleName().equals("Method")) {
            this.methods.remove((Methods) a);
        } else {
            this.fields.remove((Field) a);
        }
    }

    public boolean deleteField(String name) {
        //Find the field with the name
        //remove that field from the list
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getName().equals(name)) {
                fields.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMethod(String name/*, LinkedList<String> params*/) {
        for (int i = 0; i < fields.size(); i++) {
            if (methods.get(i).getName().equals(name)) {
                methods.remove(i);
                return true;
            }
        }
        return false;
    }

    public void renameAttribute(Attribute att, String newName) {
        att.setName(newName);
    }

    public boolean renameMethod(String methodName, String newMethodName) {
        for (int i = 0; i < methods.size(); i++) {
            if (methods.get(i).getName().equals(methodName)) {
                methods.get(i).setName(newMethodName);
                return true;
            }
        }
        return false;
    }

    public boolean renameField(String fieldName, String newFieldName) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getName().equals(fieldName)) {
                fields.get(i).setName(newFieldName);
                return true;
            }
        }
        return false;
    }


    public static Relationship findRelationship(final ClassBox cb1, final ClassBox cb2) {
        for (Relationship rel : cb1.parents) {
            if (rel.getOtherClass().equals(cb2)) {
                return rel;
            }
        }
        for (Relationship rel : cb1.children) {
            if (rel.getOtherClass().equals(cb2)) {
                return rel;
            }
        }
        return null;
    }

    //Needs to check for existing Relationship between ClassBox objects
    //Returns true if a relationship was added
    //ClassBox objects are guaranteed to not be null by the caller
    public static void addRelationship(final ClassBox parentClass, final ClassBox childClass, final int type) {
            parentClass.children.add(new Relationship(childClass,type));
            childClass.parents.add(new Relationship(parentClass,type));
    }

    public static void addRelationship(final ClassBox parentClass, final ClassBox childClass, final String type) {
        parentClass.children.add(new Relationship(childClass,type));
        childClass.parents.add(new Relationship(parentClass,type));
    }

    //returns true only if a Relationship was deleted
    //returns false if no Relationship was deleted or it did not exist
    public static boolean deleteRelationship(final ClassBox cb1, final ClassBox cb2) {
        for (Relationship rel : cb1.parents) {
            if (rel.getOtherClass().equals(cb2)) {
                cb1.parents.remove(rel);
                cb2.children.remove(rel);
                return true;
            }
        }
        for (Relationship rel : cb1.children) {
            if (rel.getOtherClass().equals(cb2)) {
                cb1.children.remove(rel);
                cb2.parents.remove(rel);
                return true;
            }
        }
        return false;
    }

    public String[] listRelationships() {
        String[] rels = new String[this.parents.size()];
        for (int i = 0; i < rels.length; ++i) {
            rels[i] = this.parents.get(i).toString();
        }
        return rels;
    }

    public void rename(final String name) {
        this.name = name;
    }
    //For list class details

    @Override
    public String toString() {
        return null;
    }


    public String getName() {
        return this.name;
    }

    public LinkedList<Methods> getMethods() {
        return methods;
    }

    public LinkedList<Field> getFields() {
        return fields;
    }
}
