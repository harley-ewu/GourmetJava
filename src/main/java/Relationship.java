package src.main.java;

import java.util.Arrays;

public class Relationship {
    private enum RelationshipType {
        AGGREGATION("aggregates"),
        COMPOSITION("composes"),
        IMPLEMENTATION("implements"),
        REALIZATION("realizes");

        private final String verb;

        RelationshipType(final String verb){
            this.verb = verb;
        }

        @Override
        public String toString(){
            return this.verb;
        }

    }

    private final ClassBox otherClass;
    //Aggregation, Composition, extension, etc
    private final RelationshipType type;

    /**
     * @param otherClass A ClassBox object
     * @param type The relationship type, as a case-insensitive String<br>
     * Relationship types: Aggregation, Composition, Extension, Dependency, Implementation, Association
     * @throws IllegalArgumentException if any objects are null, or the enum type does not exist
     */
    public Relationship(final ClassBox otherClass, final String type){
        if(otherClass == null || type == null)
            throw new IllegalArgumentException("null object passed to Relationship object");

        this.otherClass = otherClass;
        this.type = RelationshipType.valueOf(type.strip().toUpperCase());
    }

    /**
     * @param otherClass A ClassBox object
     * @param type The relationship type, as an int. The ints can be printed with printRelationshipTypes()<br>
     * Relationship types: Aggregation, Composition, Extension, Dependency, Implementation, Association
     * @throws IllegalArgumentException if any objects are null, or the enum type does not exist
     */
    public Relationship(final ClassBox otherClass, final int type){
        if(otherClass == null || type < 1 || type > RelationshipType.values().length){
            throw new IllegalArgumentException("illegal param passed to Relationship object");
        }
        this.otherClass = otherClass;
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

    public static String[] listRelationshipTypes(){
        return Arrays.stream(RelationshipType.values()).map(Enum::name).toArray(String[]::new);
    }

    /**
     * @return a String in the format "[verb] [class name]"<br>
     * Ex: If class1 extends class2, this will only print out "extends class2"<br>
     * It is up to the calling class' object to print its own name
     */
    @Override
    public String toString(){
        return this.type.verb + " " + this.getOtherClass().getName();
    }

    //Getters and setters are self-explanatory
    public ClassBox getOtherClass() {
        return this.otherClass;
    }

    public String getOtherClassName(){
        return this.otherClass.getName();
    }

    public String getType() {
        return this.type.name();
    }

    //Returns ordinal value + 1
    public int getTypeOrdinal(){
        return this.type.ordinal() + 1;
    }

}
