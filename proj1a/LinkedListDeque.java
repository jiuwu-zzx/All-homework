public class LinkedListDeque<T> {

    private class StuffNode {
        public T item;
        public StuffNode prev;
        public StuffNode next;

        public StuffNode(T item, StuffNode prev, StuffNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    private StuffNode sentF;
    private StuffNode sentB;
    private int size;

    public LinkedListDeque() {
        sentF = new StuffNode(null, null, sentB);
        sentB = new StuffNode(null, sentF, null);
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentF = new StuffNode(null, null, null);
        sentF.next = new StuffNode(item, sentF, sentB);
        sentB = new StuffNode(null, sentF.next, null);
        size = 1;
    }

    public void addFirst(T item) {
        sentF.next = new StuffNode(item, sentF, sentF.next);
        sentF.next.next.prev = sentF.next;
        size += 1;
    }

    public void addLast(T item) {
        sentB.prev = new StuffNode(item, sentB.prev, sentB);
        sentB.prev.prev.next = sentB.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode p = sentF.next;
        while (p.next != null) {
            System.out.print(p.item + " ");
            p=p.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);
        System.out.println("the size of the list:" + L.size());
        L.printDeque();
    }
}
