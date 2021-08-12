public class OffByOne implements CharacterComparator {
    static Palindrome palindrome = new Palindrome();
    @Override
    public boolean equalChars(char x, char y){
        int diff=x-y;
        return diff == 1 || diff == -1;
    }

    public boolean isPalindrome(String word){
        if(word.length()==0||word.length()==1){
            return true;
        }
        Deque<Character> d=palindrome.wordToDeque(word);
        return isPalindrome(d);

    }
    private boolean isPalindrome(Deque<Character> d){
        if(d.size()==0||d.size()==1){
            return true;
        }
        return equalChars(d.removeFirst(),d.removeLast())&&isPalindrome(d);
    }
}
