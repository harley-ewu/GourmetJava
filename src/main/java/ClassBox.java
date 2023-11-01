package src.main.java;

import java.lang.reflect.Method;
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
    private final LinkedList<Relationship> relationships;

    private final LinkedList<Methods> methods;

    private final LinkedList<Field> fields;

    public ClassBox(String name, int type) {
        if (name == null || name.isEmpty() || type < 1 || type > ClassType.values().length)
            throw new IllegalArgumentException("Bad params at ClassBox constructor");

        this.name = name;
        this.relationships = new LinkedList<>();
        this.type = ClassBox.ClassType.values()[type - 1];
        this.methods = new LinkedList<>();
        this.fields = new LinkedList<>();
    }

    public static void printClassTypes() {
        ClassBox.ClassType[] types = ClassBox.ClassType.values();
        for (int i = 0; i < 5; ++i) {
            System.out.println((i + 1) + " - " + types[i].name());
        }
    }

    public void addMethod(String name, Visibility view, LinkedList<String> tags, String type, LinkedList<String> params) {
        //call the constructor and add to list
        Methods newMethod = new Methods(name, view, tags, type, params);
        this.methods.add(newMethod);
    }

    public void addField(String name, Visibility view, LinkedList<String> tags, String type) {
        //call the constructor and add to list
        Field newField = new Field(name, view, tags, type);
        this.fields.add(newField);
    }

    public void deleteAttribute(Attribute a) {
        if (a.getClass().getSimpleName().equals("Method")) {
            this.methods.remove((Methods) a);
        } else {
            this.fields.remove((Field) a);
        }
    }

    public void renameAttribute(Attribute att, String newName) {
        att.setName(newName);
    }


    public static Relationship findRelationship(final ClassBox cb1, final ClassBox cb2) {
        for (Relationship rel : cb1.relationships) {
            if (rel.getFrom().equals(cb2)) {
                return rel;
            }
        }
        for (Relationship rel : cb2.relationships) {
            if (rel.getFrom().equals(cb1)) {
                return rel;
            }
        }
        return null;
    }

    //Needs to check for existing Relationship between ClassBox objects
    //Returns true if a relationship was added
    //ClassBox objects are guaranteed to not be null by the caller
    public boolean addRelationship(final ClassBox toClass, final int type) {
        this.relationships.add(new Relationship(toClass, type));
        return true;
    }

    //returns true only if a Relationship was deleted
    //returns false if no Relationship was deleted or it did not exist
    public static boolean deleteRelationship(final ClassBox cb1, final ClassBox cb2) {
        for (Relationship rel : cb1.relationships) {
            if (rel.getFrom().equals(cb2)) {
                cb1.relationships.remove(rel);
                return true;
            }
        }
        for (Relationship rel : cb2.relationships) {
            if (rel.getFrom().equals(cb1)) {
                cb2.relationships.remove(rel);
                return true;
            }
        }
        return false;
    }

    public String[] listRelationships() {
        String[] rels = new String[this.relationships.size()];
        for (int i = 0; i < rels.length; ++i) {
            rels[i] = this.relationships.get(i).toString();
        }
        return rels;
    }

    public void rename(final String name) {
        this.name = name;
    }
    //For list class details

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Class: ").append(this.name).append("\n");
        s.append("\tType: ").append(this.type).append("\n");
        s.append("\tFields: \n");
        for (Field a : this.fields) {
            s.append("\t\t").append(a).append("\n");
        }
        s.append("\tMethods: \n");
        for (Methods a : this.methods) {
            s.append("\t\t").append(a).append("\n");
        }
        s.append("\tRelationships: \n");
        for (Relationship r : this.relationships) {
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

    public LinkedList<Methods> getMethods() {
        return methods;
    }

    public LinkedList<Field> getFields() {
        return fields;
    }
}
