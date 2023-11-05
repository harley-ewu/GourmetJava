package src.main.java;

import java.util.LinkedList;

public class Methods extends Attribute {
    //parameter types
    private LinkedList<String> paramTypes;
    //return type
    private final String returnType;

    public Methods(String name, int view, String type, LinkedList<String> params) {
        super(name, view);

        this.paramTypes = params;
        this.returnType = type;
    }

    public void deleteParam(String param) {
        for (int i = 0; i < paramTypes.size(); i++) {
            if (paramTypes.get(i).equals(param)) {
                paramTypes.remove(i);
            }
        }
    }

    public void setParamTypes(LinkedList<String> newParamTypes) {
        if (newParamTypes == null) {
            throw new IllegalArgumentException("Bad paramTypes at Methods setParamTypes");
        }
        this.paramTypes = newParamTypes;
    }

  
    public boolean renameParam(String oldParamName, String newParamName) {
        for (int i = 0; i < paramTypes.size(); i++) {
            if (paramTypes.get(i).equals(oldParamName)) {
                paramTypes.set(i, newParamName);
                return true;
            }
        }
        return false;
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
