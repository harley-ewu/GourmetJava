package src.main.java;


public class Field extends Attribute{
    //type of field
    private final String type;
    public Field(String name, int view, String type) {
        super(name, view);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String CLIToString() {
        return this.getView().name().toLowerCase() + " " + this.getName();
    }

    @Override
    public String GUIToString() {
        return this.getView().getSymbol() + " " + this.getName();
    }


    boolean equalTo(Field other) {
        return this.getName().equals(other.getName()) && this.type.equals(other.type);
    }    

    /*
           returns a String in the format:
           [visibility symbol][field name] : [type]
     */
    @Override
    public String toString(){
        return super.toString() + " : " + this.type;
    }

}
