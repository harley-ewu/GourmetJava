package src.main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ClassBox implements Cloneable {

    public boolean equals(final ClassBox cb) {
        return this.equals(cb.getName());
    }

    public boolean equals(final String name) {
        return this.getName().equals(name);
    }

    private enum ClassType {

        CLASS, INTERFACE, RECORD, ENUMERATION, ANNOTATION;

    }



    private String name;
    //Possibly change to enum later?
    //Class, Interface, Enum, etc
    private final ClassType type;
    //Way to sort fields first, methods last?
    private LinkedList<Relationship> parents = new LinkedList<>();

    private LinkedList<Relationship> children = new LinkedList<>();

    private LinkedList<Methods> methods = new LinkedList<>();

    private LinkedList<Field> fields = new LinkedList<>();

    public String getType() {
        return this.type.name();
    }

    @Override
    public ClassBox clone() {
        try {
            // Deep copy the parents list
            LinkedList<Relationship> newParents = new LinkedList<>();


            ClassBox clone = (ClassBox) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private ClassBox(final String name, final ClassType type, final LinkedList<Relationship> parents,
                     final LinkedList<Relationship> children, final LinkedList<Methods> methods,
                     final LinkedList<Field> fields){
        this.name = name;
        this.type = type;
        this.parents = parents;
        this.children = children;
        this.methods = methods;
        this.fields = fields;
    }

    public ClassBox(String name, int type) {
        if (name == null || name.isEmpty() || type < 1 || type > ClassType.values().length)
            throw new IllegalArgumentException("Bad params at ClassBox constructor");

        this.name = name;
        this.type = ClassBox.ClassType.values()[type - 1];
    }


    //returns an array that contains the names of all constants in the ClassType enum
    //I shamelessly found the code online -David
    public static String[] listClassTypes() {
        return Arrays.stream(ClassType.values()).map(Enum::name).toArray(String[]::new);
    }


    public void addMethod(String name, int view, String type, LinkedList<String> params) {
        //call the constructor and add to list
        Methods newMethod = new Methods(name, view, type, params);
        this.methods.add(newMethod);
    }

    public void addField(String name, int view, String type) {
        //call the constructor and add to list
        Field newField = new Field(name, view, type);
        this.fields.add(newField);
    }


    // Adds a new param to a found method, returns false otherwise
    public Controller.STATUS_CODES addParam(String methodName, String newParamName) {
        Methods target = findMethod(methodName);
        if (target == null)
            return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        target.addParam(newParamName);
        return Controller.STATUS_CODES.SUCCESS;
    }


    public Controller.STATUS_CODES deleteField(String name) {
        //Find the field with the name
        //remove that field from the list
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getName().equals(name)) {
                fields.remove(i);
                return Controller.STATUS_CODES.SUCCESS;
            }
        }
        return Controller.STATUS_CODES.OBJ_NOT_FOUND;
    }

    public Controller.STATUS_CODES deleteMethod(String name/*, LinkedList<String> params*/) {
        for (int i = 0; i < methods.size(); i++) {
            if (methods.get(i).getName().equals(name)) {
                methods.remove(i);
                return Controller.STATUS_CODES.SUCCESS;
            }
        }
        return Controller.STATUS_CODES.OBJ_NOT_FOUND;
    }


    public Controller.STATUS_CODES renameParam(String methodName, String oldParamName, String newParamName) {
        for (int i = 0; i < fields.size(); i++) {
            if (methods.get(i).getName().equals(methodName)) {
                return methods.get(i).renameParam(oldParamName, newParamName);
            }
        }
        return Controller.STATUS_CODES.OBJ_NOT_FOUND;
    }

    public Controller.STATUS_CODES deleteParam(String methodName, String paramName) {
        Methods target = findMethod(methodName);
        if (target == null)
            return Controller.STATUS_CODES.OBJ_NOT_FOUND;

        return target.deleteParam(paramName);
    }


    public Controller.STATUS_CODES renameMethod(String methodName, String newMethodName) {
        for (Methods method : methods) {
            if (method.getName().equals(methodName)) {
                method.setName(newMethodName);
                return Controller.STATUS_CODES.SUCCESS;
            }
        }
        return Controller.STATUS_CODES.OBJ_NOT_FOUND;
    }

    public Controller.STATUS_CODES renameField(String fieldName, String newFieldName) {
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                field.setName(newFieldName);
                return Controller.STATUS_CODES.SUCCESS;
            }
        }
        return Controller.STATUS_CODES.OBJ_NOT_FOUND;
    }


    //finds a relationship between two ClassBoxes if it exists, or null if the relationship does not exist
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

    // Helper method that attempts to find a method within the methods list based on name
    // returns null if not found
    public Methods findMethod(String methodName) {
        for (Methods method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    //Needs to check for existing Relationship between ClassBox objects
    //ClassBox objects are guaranteed to not be null by the caller
    public static void addRelationship(final ClassBox parentClass, final ClassBox childClass, final int type) {
        parentClass.children.add(new Relationship(childClass, type));
        childClass.parents.add(new Relationship(parentClass, type));
    }

    /*
        Finds and deletes the relationship between the two ClassBox objects
        The caller (ModelDiagram) guarantees that the passed ClassBox objects are valid
        POST CONDITION: There is no relationship between the passed ClassBox objects
            (it does not care if the relationship already exists)
    */
    public static void deleteRelationship(final ClassBox cb1, final ClassBox cb2) {
        for (Relationship rel : cb1.parents) {
            if (rel.getOtherClass().equals(cb2)) {
                cb1.parents.remove(rel);
                cb2.children.remove(rel);
            }
        }
        for (Relationship rel : cb1.children) {
            if (rel.getOtherClass().equals(cb2)) {
                cb1.children.remove(rel);
                cb2.parents.remove(rel);
            }
        }
    }

    /*
    Deletes the passed relationship between the two ClassBox objects
    The caller (ModelDiagram) guarantees that the passed ClassBox objects are valid
    POST CONDITION: There is no relationship between the passed ClassBox objects
        (it does not care if the relationship already exists)
    Throws an exception if the relationship does not exist in a list (which it shouldn't)
*/
    public static void deleteRelationship(final ClassBox cb1, final ClassBox cb2, final Relationship rel) {
        try {
            cb1.parents.remove(rel);
            cb2.children.remove(rel);
        } catch (Exception ignored) {
        }
        cb1.children.remove(rel);
        cb2.parents.remove(rel);
    }

    //returns a list of ONLY the class names in the calling ClassBox's parents list
    public String[] listRelationships() {
        String[] relationships = new String[this.parents.size()];
        for (int i = 0; i < relationships.length; ++i) {
            relationships[i] = this.name + " " + this.parents.get(i).toString();
        }
        return relationships;
    }

    public String[] listMethods() {
        String[] methods = new String[this.methods.size()];
        for (int i = 0; i < this.methods.size(); ++i) {
            methods[i] = this.methods.get(i).toString();
        }
        return methods;
    }

    public String[] listFields() {
        String[] fields = new String[this.fields.size()];
        for (int i = 0; i < this.fields.size(); ++i) {
            fields[i] = this.fields.get(i).toString();
        }
        return fields;
    }

    public static String[] listVisibilityTypes() {
        return Arrays.stream(Visibility.values()).map(Enum::name).toArray(String[]::new);
    }

    public ArrayList<String[]> listRelationshipsSaveHelper() {
        ArrayList<String[]> list = new ArrayList<>();
        for (Relationship parent : this.parents) {
            list.add(new String[]{
                    parent.getOtherClassName(),
                    this.getName(),
                    String.valueOf(parent.getTypeOrdinal())
            });
        }
        return list;
    }

    public static String[] listRelationshipTypes() {
        return Relationship.listRelationshipTypes();
    }


    public void rename(final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "ClassBox toString() not implemented";
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

    public LinkedList<Relationship> getParents() {
        return parents;
    }

    public LinkedList<Relationship> getChildren() {
        return children;
    }
}
