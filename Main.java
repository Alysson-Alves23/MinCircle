import org.compiler.MinT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static String read(String fileName) {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }



    public static void main(String[] args){
        MinT compiler = new MinT(read("./mint.mt"));
        System.out.println(compiler.generateObject());
    }
}
