package j;

import java.util.ArrayList;

public class Memento<T> {
    private final ArrayList<T> snap;
    private Memento(){
        this.snap = new ArrayList<>();
    }

    public Memento(final ArrayList<T> snap) {
        this.snap = snap;
    }

    public void add(final T obj){
        this.snap.add(obj);
    }

    public ArrayList<T> restore(){
        return this.snap;
    }

}
