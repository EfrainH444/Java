import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CharMapFromFile {
    public static void main(String[] args) {
        String filePath = "BIBLIA_COMPLETA.txt";
        File file = new File(filePath);
        Map<Character, Integer> charMap = new TreeMap<>();
        
        try (FileReader reader = new FileReader(file)) {
            int currentChar = reader.read();
            while (currentChar != -1) {
                char c = (char) currentChar;
                if (charMap.containsKey(c)) {
                    int count = charMap.get(c);
                    charMap.put(c, count + 1);
                } else {
                    charMap.put(c, 1);
                }
                currentChar = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
        
        System.out.println("Mapa de caracteres:");
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

