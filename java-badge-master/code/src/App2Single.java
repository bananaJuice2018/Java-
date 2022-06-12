public class App2Single {
    public static void main(String[] args) {
        int N = 77777777;
        long t;
        // test the speed of StringBuilder in single thread
        {
            StringBuilder sb = new StringBuilder();
            t = System.currentTimeMillis();
            for (int i = N; i > 0 ; i--) {
                sb.append('a');
            }
            System.out.printf("Time for appending %d characters to StringBuilder: %d ms\n",
                    N, System.currentTimeMillis() - t);
        }
        // test the speed of StringBuffer in single thread
        {
            StringBuffer sb = new StringBuffer();
            t = System.currentTimeMillis();
            for (int i = N; i --> 0 ;) {
                sb.append('a');
            }
            System.out.printf("Time for appending %d characters to StringBuffer: %d ms\n",
                    N, System.currentTimeMillis() - t);
        }
    }
}
