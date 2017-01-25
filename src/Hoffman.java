import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class Hoffman {
    public static final String IN_FILENAME = "text.txt";
    public static final String OUT_FILENAME = "frequency.huf";

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        Map<K,V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e ->result.put(e.getKey(),e.getValue()));

        return result;
    }

    public static void main(String[] args) throws IOException{
        File inFile = new File(IN_FILENAME);
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (String s = reader.readLine(); s != null; s = reader.readLine()){
            s.trim();
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))){
                    map.replace(s.charAt(i), map.get(s.charAt(i)) + 1);
                }
                else{
                    map.put(s.charAt(i), 1);
                }
            }
        }
        reader.close();

        File outFile = new File(OUT_FILENAME);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile), "Cp1251"));
        writer.write(map.size() + "\n");
        map = sortByValue(map);
        for (Map.Entry<Character, Integer> entry: map.entrySet()){
            if (entry.getValue() > 5)
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        writer.close();
    }
}
