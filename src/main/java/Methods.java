package src.main.java;

import java.util.LinkedList;

public class Methods extends Attribute implements Cloneable{
    //parameter types
    private final LinkedList<String> paramTypes;
    //return type
    private final String returnType;

    public Methods(String name, int view, String type, LinkedList<String> params) {
        super(name, view);

        this.paramTypes = params;
        this.returnType = type;
    }

    @Override
    public Methods clone() {
        return (Methods) super.clone();
    }

    public Controller.STATUS_CODES deleteParam(String param) {
        for (int i = 0; i < paramTypes.size(); i++) {
            if (paramTypes.get(i).equals(param)) {
                paramTypes.remove(i);
                return Controller.STATUS_CODES.SUCCESS;
            }
        }
        return Controller.STATUS_CODES.OBJ_NOT_FOUND;
    }

  
    public Controller.STATUS_CODES renameParam(String oldParamName, String newParamName) {
        for (int i = 0; i < paramTypes.size(); i++) {
            if (paramTypes.get(i).equals(oldParamName)) {
                paramTypes.set(i, newParamName);
                return Controller.STATUS_CODES.SUCCESS;
            }
        }
        return Controller.STATUS_CODES.OBJ_NOT_FOUND;
  }
  
    public void addParam(String param) {
        this.paramTypes.add(param);
    }

    public LinkedList<String> getParamTypes() {
        return this.paramTypes;
    }

    /*
        returns a String in the format:
            [visibility symbol][method name] ([param types]) : [return type]
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(super.toString());
        ret.append("(");
        int size = this.paramTypes.size();
        for (int i = 0; i < size; ++i) {
            ret.append(this.paramTypes.get(i));
            if (i != size - 1) {
                ret.append(", ");
            }
        }
        ret.append(") : ").append(this.returnType);
        return ret.toString();
    }

}
