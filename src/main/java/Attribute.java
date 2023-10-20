package src.main.java;

import java.util.LinkedList;

public class Attribute implements Comparable<Attribute> {


    enum ViewType {
        PUBLIC,
        PRIVATE,
        PROTECTED;
    }
    enum attType{
        METHOD,
        VALUE;
    }
    private String name;
    //method or value
    private attType mOV;
    private ViewType view;
    //Stuff like static
    private LinkedList<String> tags;
    //Return type if method, value type if value
    private String type;
    //empty/null if value
    private LinkedList<String> params;

    public Attribute(String name, attType mOV, ViewType view, LinkedList<String> tags, String type, LinkedList<String> params) {
        if(name==null||name.isEmpty()||mOV==null||view==null||type==null||type.isEmpty()){
            throw new IllegalArgumentException("Bad params at Attribute constructor");
        }
        this.name = name;
        this.mOV = mOV;
        this.view = view;
        this.tags = tags;
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

    public attType getmOV() {
        return this.mOV;
    }

    public ViewType getView() {
        return this.view;
    }

    public LinkedList<String> getTags() {
        return this.tags;
    }

    public String getType() {
        return this.type;
    }

    public LinkedList<String> getParams() {
        return this.params;
    }

    @Override
    public int compareTo(Attribute a){
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
        if(!(this.tags==null||this.tags.isEmpty())){
            for (String s : this.tags) {
                n.append(s).append(" ");
            }
        }
        n.append(this.type).append(" ");
        n.append(this.name);
        if(this.mOV.equals(attType.VALUE)){
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
