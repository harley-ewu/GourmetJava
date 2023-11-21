package j;


public class Field extends Attribute implements Cloneable{
    //type of field
    private final String type;
    public Field(String name, int view, String type) {
        super(name, view);
        this.type = type;
    }

    /*
           returns a String in the format:
           [visibility symbol][field name] : [type]
     */
    @Override
    public String toString(){
        return super.toString() + " : " + this.type;
    }

    @Override
    public Field clone() {
            return (Field) super.clone();
    }
}
