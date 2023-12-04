package j;

import java.util.ArrayList;

public class Memento<T extends GCloneable<T>> {
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


    public static <T extends GCloneable<T>> Memento<T> createSnapshot(final ArrayList<T> l){
        ArrayList<T> list = new ArrayList<>();
        for(T o : l){
            list.add(o.clone());
        }
        return new Memento<>(list);
    }

    public static <T extends GCloneable<T>> ArrayList<T> restoreSnapshot(final Memento<T> m){
        ArrayList<T> list = new ArrayList<>();
        for(T o : m.snap)
            list.add(o.clone());
        return list;
    }

}
