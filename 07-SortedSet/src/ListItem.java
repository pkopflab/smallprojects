public class ListItem {

    private SortedSet.ListItem previous;
    private SortedSet.ListItem next;
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


}


