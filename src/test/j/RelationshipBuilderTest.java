import org.junit.Test;
import static org.junit.Assert.*;
import j.RelationshipBuilder;

public class RelationshipBuilderTest {

    @Test
    public void testConstructor() {
        RelationshipBuilder builder = new RelationshipBuilder(9, 6, 3, "parent");
        assertEquals(9, builder.getFirstIndex());
        assertEquals(6, builder.getSecondIndex());
        assertEquals(3, builder.getRelationshipType());
    }
    
}