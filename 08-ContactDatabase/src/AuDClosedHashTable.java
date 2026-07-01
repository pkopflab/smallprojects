/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

import java.util.NoSuchElementException;

public class AuDClosedHashTable extends AuDHashTable {

    private Contact[] table;
    private boolean[] deleted;
    private int counter;

    public AuDClosedHashTable(int capacity) {
        super(capacity);

        table = new Contact[capacity];
        deleted = new boolean[capacity];
        counter = 0;
    }

    @Override
    public void insert(Contact c) {
        if (!isFull()) {
            int rep = 0;
            int position = hash(c.getEmail());

            while (table[position] != null ) {
                if(deleted[position]) {
                    table[position] = c;
                    deleted[position] = false;
                    return;
                }
                rep++;
                position = hash(c.getEmail(), rep);
            }
            table[position] = c;
            counter++;
        } else {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public void remove(Contact c) {
        int position = getIndexOf(c.getEmail());
        deleted[position] = true;
        counter--;
    }

    @Override
    public Contact getContact(String email) {
        return table[getIndexOf(email)];
    }

    public boolean isFull() {
        return counter == capacity;
    }

    protected int hash(String s, int i) {
        int rest = i % 2;
        if (rest == 0) {
            return Math.floorMod((hash(s) - (i / 2) - 1), capacity);
        } else {
            return Math.floorMod((hash(s) - (i / 2)), capacity);
        }

    }

    public int getIndexOf(String email) {
        int position = hash(email);
        if(table[position] != null) {
            if (table[position].getEmail().equals(email)) {
                return position;
            } else {
                int rep = 0;
                boolean running = true;
                while (running) {
                    rep++;
                    position = hash(email, rep);
                    if(table[position] != null) {
                        if(table[position].getEmail().equals(email)) {
                            if(!deleted[position]) {
                                return position;
                            }
                        }
                    }
                    if(rep == capacity*2) {
                        running = false;
                    }
                }
            }
        }
        throw new NoSuchElementException();
    }

    public void displayTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.println("[" + i + "]: " + table[i]);
        }
    }
}
