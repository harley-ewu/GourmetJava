//package src.main.java;

//This class is used as a helper class for save/load methods in UI so that the info
// for creating relationships is stored in an object that gson can parse into json.
public class RelationshipBuilder {
    private int firstIndex;
    private int secondIndex;
    private int relationshipType;

    public RelationshipBuilder(int firstIndex, int secondIndex, int relationshipType) {
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
        this.relationshipType = relationshipType;
    }

    public int getFirstIndex() {
        return this.firstIndex;
    }
    public int getSecondIndex() {
        return this.secondIndex;
    }

    public int getRelationshipType() {
        return this.relationshipType;
    }
}
