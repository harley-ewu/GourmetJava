package j;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class MethodsTest {

    @Test
    public void testToString() {
        // Created a new linked list
        LinkedList<String> params = new LinkedList<>();
        // Adding an int to be the first parameter
        params.add("int");
        // String is our second parameter
        params.add("String");
        // Making a new method and assigning params to the constructor parameter
        Methods meth = new Methods("printID", 2, "void", params);
        String expected = "+printID(int, String) : void";
        // This will make sure the format of the string is correct
        assertEquals(expected, meth.toString());
    }

}