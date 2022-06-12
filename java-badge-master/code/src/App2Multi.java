import java.io.IOException;

public class App2Multi {
    private static StringBuilder builder = new StringBuilder();
    private static StringBuffer buffer = new StringBuffer();

    public static void main(String[] args) {
        // test the multi-threading performance for StringBuilder and StringBuffer
        testSeqMulti(builder);
        testSeqMulti(buffer);
    }

    // create a generic function for StringBuilder and StringBuffer
    private static <T extends Appendable & CharSequence> void testSeqMulti(T seq){
        // create a simple Runnable object: append 100 characters
        Runnable run = () -> {
            try {
                for (int i = 0; i < 100; i++) {
                    seq.append('a');
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        };

        // create 100 threads to run above Runnable object
        Thread[] td = new Thread[100];
        for (int i = 0; i < 100; i++) {
            td[i] = new Thread(run);
            td[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                td[i].join();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // count the number of characters in the seq, expectation is 10000
        System.out.printf("Length of %s: %d, expect: 10000\n",
                seq.getClass().getSimpleName(), seq.length());
    }

}
