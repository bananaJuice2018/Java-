import java.io.FileReader;

public class App6 {
    private static void handleException(Throwable th) {
        if (th instanceof Error) {
            System.out.println(th.getClass().getSimpleName() + " is an Error");
        }
        else if (th instanceof RuntimeException) {
            System.out.println(th.getClass().getSimpleName() + " is an unchecked exception");
        }
        else {
            System.out.println(th.getClass().getSimpleName() + " is a checked exception");
        }
    }
    public static void main(String[] args) {
        // an example of unchecked exception
        try {
            int a = Integer.parseInt("abc");
        }
        catch (Throwable t) {
            handleException(t);
        }

        // an example of checked exception
        try {
            FileReader reader = new FileReader("unreal.txt");
            reader.close();
        }
        catch (Throwable t) {
            handleException(t);
        }

        // an example of error
        try {
            int[] data = new int[1024*1024*1024];
        }
        catch (Throwable t) {
            handleException(t);
        }
    }
}
