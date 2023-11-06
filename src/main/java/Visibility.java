package src.main.java;


//The CLI will use the enum name and the GUI will use the enum symbol
public enum Visibility {
    PRIVATE("-"),
    PUBLIC("+"),
    PROTECTED("#");

    private final String symbol;

    private Visibility(final String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return this.symbol;
    }

    public String getVisibility() {
        return this.name();
    }

}
