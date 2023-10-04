public class Relationship {
    //Format = Class1aggregatesClass2
    private String name;
    private ClassBox from;
    private ClassBox to;
    //Aggregation, Composition, extension, etc
    private String type;
    //(0,1,*, etc)
    private String fromNum;
    private String toNum;

}
