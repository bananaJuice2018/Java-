import java.util.ArrayList;
import java.util.Stack;


public class ReverseStr {

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char tempo = s[left];  // tempo = h
            s[left] = s[right];   // s[left] = o at the moment
            s[right] = tempo;      // s[right] = h

            left++;
            right--;
        }

        System.out.println(s);
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        ReverseStr reverseStr = new ReverseStr();
        reverseStr.reverseString(s);
    }
}


