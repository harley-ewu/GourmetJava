package src.main.java;

import java.util.Arrays;

/**
 * Describes the relationship between 2 classes
 */
public class Relationship {
    /**
     * the type of relationship between the 2 classes
     */
    private enum RelationshipType {
        AGGREGATION("aggregates"),
        COMPOSITION("composes"),
        IMPLEMENTATION("implements"),
        REALIZATION("realizes");

        private final String verb;

        RelationshipType(final String verb) {
            this.verb = verb;
        }

        @Override
        public String toString() {
            return this.verb;
        }

    }

    /**
     * name of the other class (a Relationship object is owned by one class and points to another)
     */
    private final String otherClass;

    /**
     * Type of relationship
     */
    private final RelationshipType type;

    /**
     * @param otherClass A ClassBox object
     * @param type       The relationship type, as a case-insensitive String<br>
     *                   Relationship types: Aggregation, Composition, Extension, Dependency, Implementation, Association
     * @throws IllegalArgumentException if any objects are null, or the enum type does not exist
     */
    public Relationship(final ClassBox otherClass, final String type) {
        if (otherClass == null || type == null)
            throw new IllegalArgumentException("null object passed to Relationship object");

        this.otherClass = otherClass.getName();
        this.type = RelationshipType.valueOf(type.strip().toUpperCase());
    }

    /**
     * @param otherClass A ClassBox object
     * @param type       The relationship type, as an int. The ints can be printed with printRelationshipTypes()<br>
     *                   Relationship types: Aggregation, Composition, Extension, Dependency, Implementation, Association
     * @throws IllegalArgumentException if any objects are null, or the enum type does not exist
     */
    public Relationship(final ClassBox otherClass, final int type) {
        if (otherClass == null || type < 1 || type > RelationshipType.values().length)
            throw new IllegalArgumentException("illegal param passed to Relationship object");

        this.otherClass = otherClass.getName();
        this.type = RelationshipType.values()[type - 1];
    }

    public static String[] listRelationshipTypes() {
        return Arrays.stream(RelationshipType.values()).map(Enum::name).toArray(String[]::new);
    }

    /**
     * @return a String in the format "[verb] [class name]"<br>
     * Ex: If class1 extends class2, this will only print out "extends class2"<br>
     * It is up to the calling class' object to print its own name
     */
    @Override
    public String toString() {
        return this.type.verb + " " + this.getOtherClass();
    }

    //Getters and setters are self-explanatory
    public String getOtherClass() {
        return this.otherClass;
    }

    public String getType() {
        return this.type.name();
    }

    //Returns ordinal value + 1
    public int getTypeOrdinal() {
        return this.type.ordinal() + 1;
    }

}
