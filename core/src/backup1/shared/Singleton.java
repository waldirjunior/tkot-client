package backup1.shared;

public class Singleton {
    private static Singleton instance = new Singleton();
    private SharedObject sharedObject;

    private Singleton() {
        sharedObject = new SharedObject();
    }

    public static Singleton getInstance() {
        return instance;
    }

    public SharedObject getSharedObject() {
        return sharedObject;
    }
}