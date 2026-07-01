/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

import java.util.NoSuchElementException;

public class SortedSet implements OrderedSet {

    private ListItem head = null;
    private ListItem tail = null;

    public void clear() {
        head = null;
    }

    public int size() {
        int size = 0;
        for (ListItem i = head; i != null; i = i.next) {
            size++;
        }
        return size;
    }

    public boolean contains(int value) {
        for (ListItem i = head; i != null; i = i.next) {
            if (i.value == value) {
                return true;
            }
        }
        return false;
    }

    public int[] toArray() {
        int[] array = new int[size()];
        int position = 0;
        for (ListItem i = head; i != null; i = i.next) {
            array[position] = i.value;
            position++;
        }
        return array;
    }

    public int[] toReversedArray() {
        int[] array = new int[size()];
        int position = 0;
        for (ListItem i = tail; i != null; i = i.previous) {
            array[position] = i.value;
            position++;
        }
        return array;
    }

    public void add(int value) {
        ListItem toadd = new ListItem(value);
        int size = size();
        if (size == 0) {
            head = toadd;
            tail = head;
            return;
        }
        ListItem current = head;
        if (value <= current.value) {
            head.previous = toadd;
            toadd.next = head;
            head = head.previous;
            if (size == 1) {
                tail = head.next;
            }
            return;
        }

        for (int i = 0; i < size; i++) {
            if (current.value == value) {
                throw new ElementExistsException();
            }
            if (current.value >= value && current.previous.value <= value) {
                current.previous.next = toadd;
                current.previous.next.previous = current.previous;
                current.previous.next.next = current;
                current.previous = current.previous.next;
                return;
            }
            current = current.next;
        }
        tail.next = toadd;
        tail.next.previous = tail;
        tail = tail.next;
    }

    public void add(int[] array) {
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    public void remove(int value) {
        for (ListItem i = head; i != null; i = i.next) {
            if (i.value == value) {
                //first item "head"
                if (i.previous == null) {
                    head = i.next;
                    head.previous = null;
                    return;
                } else {
                    if (i.next != null) {
                        ListItem prev = i.previous;
                        ListItem next = i.next;
                        prev.next = next;
                        next.previous = prev;
                        //last element "tail"
                    } else {
                        i.previous.next = null;
                        tail = i.previous;
                    }
                    return;
                }
            }
        }
        throw new NoSuchElementException();
    }

    public OrderedSet clone() {
        SortedSet sortedSet = new SortedSet();
        int[] content = toArray();
        sortedSet.add(content);
        return sortedSet;
    }

    public OrderedSet getSetInBetween(int from, int to) {
        SortedSet sortedSet = new SortedSet();
        int length = to - from;
        ListItem fromListItem = head;

        for (int i = 0; i < from - 1; i++) {
            fromListItem = fromListItem.next;
        }
        sortedSet.add(fromListItem.value);


        ListItem toListItem = fromListItem;
        for (int i = 0; i < length; i++) {
            toListItem = toListItem.next;
            sortedSet.add(toListItem.value);
        }
        return sortedSet;
    }

    public OrderedSet intersect(OrderedSet set) {
        int[] array1 = toArray();
        SortedSet intersect = new SortedSet();

        for (int i = 0; i < array1.length; i++) {
            if (set.contains(array1[i])) {
                System.out.println(array1[i]);
                intersect.add(array1[i]);
            }
        }

        return intersect;
    }

    public OrderedSet unite(OrderedSet set) {
        SortedSet sortedSet = new SortedSet();
        int[] content = toArray();
        int[] content2 = set.toArray();
        sortedSet.add(content);
        sortedSet.add(content2);
        return sortedSet;
    }

    public OrderedSet subtract(OrderedSet set) {
        SortedSet sortedSet = new SortedSet();
        SortedSet intersect = (SortedSet) intersect(set);

        int[] intersectContent = intersect.toArray();
        int[] content = toArray();

        sortedSet.add(content);

        for (int i = 0; i < intersectContent.length; i++) {
            sortedSet.remove(intersectContent[i]);
        }

        return sortedSet;
    }

    @Override
    public String toString() {
        String result = "";
        for (ListItem i = head; i != null; i = i.next) {
            result = result + i.toString() + " ";
        }

        return "{ " + result + "}";
    }

    public class ListItem {

        private ListItem previous;
        private ListItem next;
        private final int value;

        public ListItem(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (next != null) {
                return "[" + value + "] -->";
            } else {
                return "[" + value + "]";
            }
        }

        public boolean equals(Object other) {
            if (other instanceof ListItem) {
                ListItem other2 = (ListItem) other;
                if (value == other2.value) {
                    return true;
                }
            }
            return false;
        }

    }

    public void printSet() {
        for (ListItem i = head; i != null; i = i.next) {
            System.out.print(i.toString());
        }
    }

    public static void main(String[] args) {
//        SortedSet sortedSet = new SortedSet();
//        sortedSet.add(0);
//        sortedSet.add(1);
//        sortedSet.add(2);
//        sortedSet.clear();
//        System.out.println(sortedSet.toString());

//        SortedSet sortedSet = new SortedSet();
//        sortedSet.add(0);
//        sortedSet.add(1);
//        sortedSet.add(2);
//        System.out.println(sortedSet.size());

//        SortedSet sortedSet = new SortedSet();
//        sortedSet.add(0);
//        sortedSet.add(1);
//        sortedSet.add(2);
//        System.out.println(sortedSet.contains(1));
//        System.out.println(sortedSet.contains(3));

//        SortedSet sortedSet = new SortedSet();
//        sortedSet.add(0);
//        sortedSet.add(1);
//        sortedSet.add(2);
//        System.out.print(Arrays.toString(sortedSet.toArray()));

//        SortedSet sortedSet = new SortedSet();
//        sortedSet.add(0);
//        sortedSet.add(1);
//        sortedSet.add(2);
//        System.out.print(Arrays.toString(sortedSet.toReversedArray()));

//        SortedSet sortedSet = new SortedSet();
//        sortedSet.add(2);
//        sortedSet.add(1);
//        sortedSet.add(3);
//        System.out.print(sortedSet.toString());


        //Add methode funktioniert nicht richtig
        //Hatte keine Ahnung wie ich die fixxen muss

        OrderedSet orderedSet = new SortedSet();
        OrderedSet orderedSet2 = new SortedSet();

        System.out.println(orderedSet.toString());
        System.out.println(orderedSet2.toString());

        orderedSet.add(1);
        orderedSet.add(6);
        orderedSet.add(4);
        orderedSet.add(2);
        orderedSet.add(7);
        orderedSet.add(5);
        orderedSet.add(12);

        orderedSet2.add(new int[]{5,23,22,7,9});

        System.out.println(orderedSet.toString());
        System.out.println(orderedSet2.toString());

        orderedSet2.remove(22);
        try {
            orderedSet2.remove(77);
        } catch (NoSuchElementException exception) {
            System.err.println("Zahl nicht in Menge enthalten");
        }

        OrderedSet orderedSet3 = orderedSet.getSetInBetween(2, 6);
        System.out.println(orderedSet3.toString());

        try {
            OrderedSet schnitt = orderedSet.intersect(orderedSet2);
            OrderedSet vereinigung = orderedSet.unite(orderedSet2);
            OrderedSet diff = orderedSet.subtract(orderedSet2);
        } catch (ElementExistsException exception) {
            System.err.println("Fehler 7h)");
        }

        OrderedSet orderedSet4 = new SortedSet();
        orderedSet4.add(new int[]{18, 19, 20});
        OrderedSet unit = orderedSet4.unite(orderedSet);


    }

}


