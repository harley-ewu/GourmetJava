public class Relationship {
    private enum RelationshipType {
        AGGREGATION("aggregates"),
        COMPOSITION("composes"),
        EXTENSION("extends"),
        IMPLEMENTATION("implements"),
        DEPENDENCY("depends on"),
        ASSOCIATION("associates with");

        private final String verb;

        RelationshipType(final String verb){
            this.verb = verb;
        }

        @Override
        public String toString(){
            return this.verb;
        }

    }

    private ClassBox from;
    //Aggregation, Composition, extension, etc
    private RelationshipType type;

    /**
     * @param from A ClassBox object
     * @param type The relationship type, as a case-insensitive String<br>
     * Relationship types: Aggregation, Composition, Extension, Dependency, Implementation, Association
     * @throws IllegalArgumentException if any objects are null, or the enum type does not exist
     */
    public Relationship(final ClassBox from, final String type){
        if(from == null || type == null){
            throw new IllegalArgumentException("null object passed to Relationship object");
        }
        this.from = from;
        this.type = RelationshipType.valueOf(type.strip().toUpperCase());
    }

    /**
     * @param from A ClassBox object
     * @param type The relationship type, as an int. The ints can be printed with printRelationshipTypes()<br>
     * Relationship types: Aggregation, Composition, Extension, Dependency, Implementation, Association
     * @throws IllegalArgumentException if any objects are null, or the enum type does not exist
     */
    public Relationship(final ClassBox from, final int type){
        if(from == null || type < 1 || type > RelationshipType.values().length){
            throw new IllegalArgumentException("illegal param passed to Relationship object");
        }
        this.from = from;
        this.type = RelationshipType.values()[type - 1];
    }

    /**
     * Prints the relationships in the format "[num] - [relationship type]
     */
    public static void printRelationshipTypes(){
        RelationshipType[] relations = RelationshipType.values();
        for(int i = 0; i < relations.length; ++i){
            System.out.println((i + 1) + " - " + relations[i].name());
        }
    }

    /**
     * @return a String in the format "[verb] [class name]"<br>
     * Ex: If class1 extends class2, this will only print out "extends class2"<br>
     * It is up to the calling class' object to print its own name
     */
    @Override
    public String toString(){
        return this.type.verb + " " + this.getFrom().getName();
    }

    //Getters and setters are self-explanatory
    public ClassBox getFrom() {
        return this.from;
    }

    public String getType() {
        return this.type.name();
    }

    public int getTypeOrdinal(){
        return this.type.ordinal();
    }

}
