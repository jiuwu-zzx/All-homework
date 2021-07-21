public class LinkedListDeque<T> {

    private class StuffNode {
        private T item;
        private StuffNode prev;
        private StuffNode next;

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
        sentF = new StuffNode(null, null, null);
        sentB = new StuffNode(null, sentF, null);
        sentF.next = sentB;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentF = new StuffNode(null, null, null);
        sentF.next = new StuffNode(item, sentF, null);
        sentB = new StuffNode(null, sentF.next, null);
        sentF.next.next = sentB;
        size = 1;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentF = new StuffNode(null, null, sentB);
        sentB = new StuffNode(null, sentF, null);
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
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
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T first = sentF.next.item;
        sentF.next = sentF.next.next;
        sentF.next.prev = sentF;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T last = sentB.prev.item;
        sentB.prev = sentB.prev.prev;
        sentB.prev.next = sentB;
        size -= 1;
        return last;
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        }
        StuffNode p = sentF.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }
        return getRecursiveHelper(sentF.next, index);
    }

    private T getRecursiveHelper(StuffNode senti, int index) {
        if (index == 0) {
            return senti.item;
        }
        return getRecursiveHelper(senti.next, index - 1);
    }

}
