import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class ReadDataFromFile {


    public void readFile(String fileName) throws FileNotFoundException {

        SetInitialValue setInitialValue = new SetInitialValue();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        StringTokenizer token;
        Scanner odczyt = new Scanner(file);
        int i = -1;
        int j = 0;
        while (odczyt.hasNextLine()) {
            i++;
            token = new StringTokenizer(odczyt.nextLine(), " ");
            while (token.hasMoreElements()) {
                setInitialValue.setOneValue(j % 9, i, token.nextToken());
                j++;
            }
        }
    }
}
