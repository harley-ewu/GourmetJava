package src.main.java;

import java.util.LinkedList;

public class Attribute implements Comparable<Attribute> {

    enum ViewType {
        PUBLIC,
        PRIVATE,
        PROTECTED;
    }
    enum AttributeType {
        METHOD,
        FIELD;
    }
    private String name;
    //method or value
    private AttributeType attributeType;
    //public, private, protected
    private ViewType view;
    //Stuff like static
    private LinkedList<String> modifiers;
    //Return type if method, value type if value
    private String type;
    //empty/null if value
    private LinkedList<String> params;

    public Attribute(String name, AttributeType mOV, ViewType view, LinkedList<String> tags, String type, LinkedList<String> params) {
        if(name==null||name.isEmpty()||mOV==null||view==null||type==null||type.isEmpty()){
            throw new IllegalArgumentException("Bad params at Attribute constructor");
        }
        this.name = name;
        this.attributeType = mOV;
        this.view = view;
        this.modifiers = tags;
        this.type = type;
        this.params = params;
    }

    public void setName(String newName) {
        if(newName==null||newName.isEmpty()){
            throw new IllegalArgumentException("Bad name at attribute setName");
        }
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public AttributeType getmOV() {
        return this.attributeType;
    }

    public ViewType getView() {
        return this.view;
    }

    public LinkedList<String> getModifiers() {
        return this.modifiers;
    }

    public String getType() {
        return this.type;
    }

    public LinkedList<String> getParams() {
        return this.params;
    }

    @Override
    public int compareTo(Attribute a){
        if(this.attributeType.equals(AttributeType.FIELD) && a.attributeType.equals(AttributeType.METHOD)){
            return 1;
        }
        else if(this.attributeType.equals(AttributeType.METHOD) && a.attributeType.equals(AttributeType.FIELD)){
            return -1;
        }
        return this.name.compareTo(a.name);
    }

    //Used so overloading okay
    public boolean equalTo(Attribute another){
        if(this.name.equalsIgnoreCase(another.name)){
            return this.params.equals(another.params);
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder n = new StringBuilder(this.view.toString() + " ");
        if(!(this.modifiers ==null||this.modifiers.isEmpty())){
            for (String s : this.modifiers) {
                n.append(s).append(" ");
            }
        }
        n.append(this.type).append(" ");
        n.append(this.name);
        if(this.attributeType.equals(AttributeType.FIELD)){
            return n.toString();
        }
        else{
            n.append("(");
            for(String a: this.params){
                n.append(a).append(" ");
            }
            n.append(")");
        }
        return n.toString();
    }
}
