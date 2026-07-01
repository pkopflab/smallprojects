/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

public abstract class AuDHashTable {

    protected int capacity;

    public AuDHashTable(int capacity) {
        this.capacity = capacity;
    }

    abstract public void insert(Contact c);

    abstract public void remove(Contact c);

    abstract public Contact getContact(String email);

    // Diese Methode muss noch implementiert werden
    protected int hash(String s) {
        // Ersetzen Sie den Methodenkoerper durch die richtige Hashfunktion
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }
        return sum % capacity;
    }
}
