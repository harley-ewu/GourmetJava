
public class Relationship {
    private enum RelationshipType {
        AGGREGATION("aggregates"),
        COMPOSITION("composes?"),
        EXTENSION("extends?"),
        IMPLEMENTATION("implements"),
        DEPENDENCY("depends on"),
        ASSOCIATION("associates?");

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
    private ClassBox to;
    //Aggregation, Composition, extension, etc
    private RelationshipType type;

    /**
     * @param to A ClassBox object
     * @param from A ClassBox object
     * @param type The relationship type, as a case-insensitive String<br>
     * Relationship types: Aggregation, Composition, Extension, Dependency, Implementation, Association
     * @throws IllegalArgumentException if any objects are null, or the enum type does not exist
     */
    public Relationship(final ClassBox to, final ClassBox from, final String type){
        if(to == null || from == null || type == null){
            throw new IllegalArgumentException("null object passed to Relationship object");
        }
        this.from = from;
        this.to = to;
        //add this relationship to both class' lists
        this.type = RelationshipType.valueOf(type.strip().toUpperCase());
    }

    //remove itself from the lists of the "to" and "from" ClassBoxes
    /* commented out to hide build errors
    //TODO Guarantee that, given ClassBox objects/names (must be 2), the relationship is deleted
    public void deleteRelationship(final ClassBox b1, final ClassBox b2){

        this.to.deleteRelationship(this);
        this.from.deleteRelationship(this);
    }
    */

    /*
    public void addRelationship(final ClassBox b1, final ClassBox b2){
        box.addRelationship(this);
    }

    public void replaceRelationship(final ClassBox b1, final ClassBox b2){

    }
    */

    /**
     * @return String in format "ClassBox to [relationship verb] ClassBox from"
     */
    @Override
    public String toString(){
        return this.to.toString() + " " + this.type.toString() + " " + this.from.toString();
    }

    /**
     * @param to A ClassBox object
     * @param from A ClassBox object
     * @throws IllegalArgumentException if either object is null
     */
    public void setToAndFrom(final ClassBox to, final ClassBox from){
        if(to == null || from == null){
            throw new IllegalArgumentException("to or from in Relationship.setToAndFrom is null");
        }
        this.to = to;
        this.from = from;
    }

    //Getters and setters are self-explanatory
    public ClassBox getFrom() {
        return from;
    }

    /**
     * @param from A ClassBox Object
     * @throws IllegalArgumentException if the param is null
     */
    public void setFrom(final ClassBox from) {
        if(from == null){
            throw new IllegalArgumentException("null from object in Relationship.setFrom");
        }
        this.from = from;
    }

    public ClassBox getTo() {
        return this.to;
    }

    /**
     * @param to A ClassBox object
     * @throws IllegalArgumentException if the object is null
     */
    public void setTo(final ClassBox to) {
        if(to == null){
        throw new IllegalArgumentException("null to object in Relationship.setFrom");
    }
        this.to = to;
    }

    public String getType() {
        return this.type.name();
    }

    /**
     * @param type The type of the new Relationship - must be a Relationship type
     * @throws IllegalArgumentException if the type String is null
     */
    public void setType(final String type) {
        if(type == null){
            throw new IllegalArgumentException("null type object in Relationship.setFrom");
        }
        this.type = RelationshipType.valueOf(type.strip().toUpperCase());
    }

}
