import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public List<String> fizzBuzz(int n) {

        List<String> output = new ArrayList();
        for (int num = 1; num <= n; num++) {
            if (num % 3 == 0 && num % 5 == 0)
                output.add("FizzBuzz");
            else if (num % 3 == 0)
                output.add("Fizz");
            else if (num % 5 == 0)
                output.add(" Buzz");
            else
                output.add(Integer.toString(num));
        }
        return output;
    }





    public static void main(String[] args) {


    }
}

