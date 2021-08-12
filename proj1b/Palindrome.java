public class Palindrome {
    /**
     * return a Deque
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**
     * return a dummy value
     */
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d);

    }

    private boolean isPalindrome(Deque<Character> d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        return (d.removeFirst() == d.removeLast()) && isPalindrome(d);
    }

    /**
     * OffByOne
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d, cc);
    }

    private boolean isPalindrome(Deque<Character> d, CharacterComparator cc) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        return cc.equalChars(d.removeFirst(), d.removeLast()) && isPalindrome(d, cc);
    }
}
