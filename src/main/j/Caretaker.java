package j;

import java.util.ArrayList;

/**
 * Keeps a list of the most recent changes, for undo/redo<br>
 * A more technical explanation is available in the source code
 */
public class Caretaker<T extends gCloneable<T>> {

    /**
     * a tweaked full ascending stack that preserves the elements that are "popped" off
     */
    private final ArrayList<j.Memento<T>> stack;

    /*
        This explanation will not go into edge cases, just how it works

        stackPointer keeps track of the index of the "current state"
            - When the user "undos", the stack pointer is decremented and returns the element at that index
            - When the user "redos", the stack pointer is incremented and returns the element at that index
            - When the user makes a change, the stack pointer is incremented and the new change is placed at that index

        stackSize keeps track of the size of the "working stack" (the stack might be smaller than the array holding it)
        Explanation:
            If the user does multiple "undos" in a row, they should be able to do multiple "redos" to return to the most recent state
            If the user does multiple "undos" in a row and then makes a change, it does not make sense to "redo" to the most previous state in the previous line

        Example Scenario:
            1. The user makes multiple changes: let's call them C1, C2, C3, C4
                - This is the current stack:
                            (stack start)-> | C1 | C2 | C3 | C4 | <-(stack end)
                                               Current state ^^^
                - The latest change is C4 and the program is in C4

            2. The user "undos" changes C4 and C3. This puts the program into C2
                            (stack start)-> | C1 | C2 | C3 | C4 | <-(stack end)
                                    Current state ^^^
                - Note that the most valid state is still C4, which is the size of the entire stack (minus 1)

            3. The user makes a change while in C2, making C5 which overwrites C3. This is the stack now:
                            (stack start)-> | C1 | C2 | C5 | C4 | <-(stack end)
                                          Current state ^^^
                - The user cannot "redo" anything because they are on the most recent change
                - "redo" should only put the state into the most recent change, so we cannot "redo" from C5 into C4
                - Notice that the latest change is no longer the last element in the stack

            4. The user "undos" C5, putting the program back into C2. This is the stack:
                            (stack start)-> | C1 | C2 | C5 | C4 | <-(stack end)
                                    Current state ^^^
                - The user can "redo" up until the most recent change (C5)
                - Because the most recent change might not be the last element in the stack:
                    - We cannot allow "redos" to the end of the stack
                    - We need a different pointer which keeps track of the most recent change
     */

    /**
     * Keeps track of the current state of the program
     */
    private int stackPointer;

    /**
     *   stackSize keeps track of the most updated state that we are allowed to redo to
     */
    private int stackSize;

    /**
     * This class is a singleton so the c'tor is private
     */
    public Caretaker() {
        this.stackPointer = -1;
        this.stackSize = 0;
        this.stack = new ArrayList<>();
    }

    /*
        POSTCONDITIONS:
            In cases where "undos" are invalid, will return null:
                - If the stack is empty
                - If the stackPointer is at index 0 (the state of the program is already the oldest recorded state)
            In cases where "undos" are valid, will return the previously recorded state of the program:
                - The stack is not empty and the stackPointer is not 0 (the state of the program is not the oldest recorded state)
     */
    /**
     * returns a Memento Object containing the state of the program just before the most recent change
     * @return Memento Object with the most recent changes, or null if the undo is invalid
     */
    public Memento<T> undo() {
        if (this.stackSize == 0 || this.stackPointer == 0)
            return null;

        /*
            Full descending stack: stackPointer "points" to the element at the top of the stack (the most recent change)
                - decrement the stackPointer
                - get the element "pointed at" by the stackPointer
         */
        --this.stackPointer;
        return this.stack.get(this.stackPointer);
    }

    /*
        POSTCONDITIONS:
            In cases where "redos" are invalid, will return null:
                - If the stack is empty
                - If the stackPointer is at the last element (stackSize - 1)
                    - the state of the program is already the oldest recorded state
            In cases where "redos" are valid, will return the next most recently recorded state of the program:
                - The stack is not empty and the stackPointer is < (stackSize - 1)
                    - the state of the program is not the newest recorded state

     */

    /**
     * returns a Memento Object containing the state of the program before the most recent undo
     * @return Memento object containing the state of the program before the most recent undo, or null if the redo is invalid
     */
    public Memento<T> redo() {
        //CASE: Stack is empty or stackPointer is already at 0 (the state of the program is already the oldest recorded state)
        if (this.stackSize == 0 || this.stackPointer == (stackSize - 1))
            return null;

        /*
            Full descending stack: stackPointer "points" to the element at the top of the stack (the most recent change)
                - increment the stackPointer
                    - now stackPointer is at the space after the current state (the next most recently recorded state of the program)
                - return the element "pointed at" by the stackPointer
         */
        ++stackPointer;
        return this.stack.get(this.stackPointer);
    }

    /*
        POSTCONDITIONS:
            - The snapshot is added to the top of the stack
            - The stackSize is increased by 1
            - The stackPointer holds the index of the most recent change
     */
    /**
     * Adds a Memento object to the stack and updates the stack pointer and stack size accordingly
     * @param snapshot Memento Object to add to the stack
     */
    public void addChange(final Memento<T> snapshot) {
        if (snapshot == null)
            throw new IllegalArgumentException("null snapshot passed to Caretaker.updateChange");

        ++this.stackPointer;
        if (this.stackPointer == this.stack.size()) {
            this.stack.add(snapshot);
        } else {
            this.stack.set(stackPointer, snapshot);
        }
        this.stackSize = this.stackPointer + 1;
    }


    /**
     * Replaces the Memento object on the top of the stack<br>
     * This is mainly for GUI when boxes are moved, the moves should be preserved but they should not be added to the stack
     * @param snapshot Memento Object that will take place of the object on the top of the stack
     */
    public void updateChange(final Memento<T> snapshot){
        if (snapshot == null)
            throw new IllegalArgumentException("null snapshot passed to Caretaker.updateChange");

        this.stack.set(stackPointer, snapshot);

    }

}
