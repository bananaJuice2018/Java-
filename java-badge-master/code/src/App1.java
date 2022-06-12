public class App1 {
    public static void main(String[] args) {
        // define a new StringBuilder object
        StringBuilder sb = new StringBuilder();

        // keep appending until error been caught
        try {
            while (true) {
                sb.append('a');
            }
        }
        catch (OutOfMemoryError e) {
            // check the size of the StringBuilder object
            long length = sb.length();
            long sizeBytes = length * 2;
            double sizeGB = length * 2.0 / 1073741824;
            System.out.println("Number of Characters in StringBuilder: " + length);
            System.out.println("Size of StringBuilder (Bytes): " + sizeBytes + "B");
            System.out.println("Size of StringBuilder (GB): " + sizeGB + "GB");
            e.printStackTrace();
        }
    }
}
