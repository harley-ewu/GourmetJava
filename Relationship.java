public class Relationship {
    //Format = Class1aggregatesClass2
    private String name;
    private ClassBox from;
    private ClassBox to;
    //Aggregation, Composition, extension, etc
    private String type;
    //(0,1,*, etc)
    private int fromNum;
    private int toNum;

    public Relationship(final String name, final ClassBox to, final ClassBox from,
                        final String type, final int fromNum, final int toNum){
        if(name == null || type == null){
            throw new IllegalArgumentException("null String passed to Relationship object");
        }
        this.name = name;
        this.from = from;
        this.to = to;
        this.type = type;
        this.fromNum = fromNum;
        this.toNum  = toNum;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ClassBox getFrom() {
        return from;
    }

    public void setFrom(final ClassBox from) {
        this.from = from;
    }

    public ClassBox getTo() {
        return to;
    }

    public void setTo(final ClassBox to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public int getFromNum() {
        return fromNum;
    }

    public void setFromNum(final int fromNum) {
        this.fromNum = fromNum;
    }

    public int getToNum() {
        return toNum;
    }

    public void setToNum(final int toNum) {
        this.toNum = toNum;
    }
}
