package j;

public interface Observable {

    void notifyObservers(int reason, String msg);

}
