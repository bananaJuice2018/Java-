import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App5 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("lorem.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        fileReader.close();
        lines.sort(Collections.reverseOrder());

        FileWriter fileWriter = new FileWriter("lorem-sort-reverse.txt");
        for (String ln : lines) {
            fileWriter.write(ln);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }
}
