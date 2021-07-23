public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }

    private void resize(int capacity) {
        if (capacity <= 8) {
            return;
        }
        T[] a = (T[]) new Object[capacity];
        if (nextFirst == items.length - 1) {
            System.arraycopy(items, 0, a, 0, size);
        } else if (nextFirst + size < items.length) {
            System.arraycopy(items, nextFirst + 1, a, 0, size);
        } else {
            System.arraycopy(items, nextFirst + 1, a, 0, items.length - (nextFirst + 1));
            System.arraycopy(items, 0, a, items.length - (nextFirst + 1), nextLast);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items = a;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst == items.length - 1) {
            for (int i = 0; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            int book = 0;
            for (int i = nextFirst + 1; i <= nextFirst + size; i++) {
                if (i == nextLast) {
                    book = 1;
                }
                //到达队列末尾
                if (i == items.length) {
                    break;
                }
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j < nextLast; j++) {
                System.out.print(items[j] + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size <= Math.ceil(items.length / 2.0)) {
            resize((int) Math.ceil(items.length / 2.0));
        }
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        size -= 1;
        return items[nextFirst];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size <= Math.ceil(items.length / 2.0)) {
            resize((int) Math.ceil(items.length / 2.0));
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        size -= 1;
        return items[nextLast];
    }

    public T get(int index) {
        if (nextFirst + index + 1 < items.length) {
            return items[nextFirst + index + 1];
        } else if ((nextLast - (size - index)) >= 0) {
            return items[nextLast - (size - index)];
        } else {
            return items[index];
        }
    }

//    /* just for testing */
//    public static void main(String[] args) {
//        ArrayDeque<Integer> L = new ArrayDeque<>();
//        L.addLast(0);
//        L.addLast(1);
//        System.out.println(L.removeFirst());
//        System.out.println(L.removeFirst());
////        System.out.println(L.get(4));
//    }
}
