/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class AuDOpenHashTable extends AuDHashTable {

    private final LinkedList<Contact>[] table;

    public AuDOpenHashTable(int capacity) {
        super(capacity);
        table = new LinkedList[capacity];

        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<Contact>();
        }
    }

    @Override
    public void insert(Contact c) {
        int position = hash(c.getEmail());
        table[position].add(c);
    }

    @Override
    public void remove(Contact c) {
        int position = hash(c.getEmail());
        if(table[position].contains(c)) {
            table[position].remove(c);
        } else {
            throw new NoSuchElementException();
        }



    }

    @Override
    public Contact getContact(String email) {
        int position = hash(email);
        for(Contact contact : table[position]) {
            if(contact.getEmail().equals(email)) {
                return contact;
            }
        }
        throw new NoSuchElementException();
    }

    public void displayTable() {
        for(int i = 0; i < table.length; i++) {
            System.out.println("["+i+"]: "+table[i]);
        }
    }

}
