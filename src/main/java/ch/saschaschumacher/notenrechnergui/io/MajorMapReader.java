package ch.saschaschumacher.notenrechnergui.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MajorMapReader {

    private final File majorMapFile;
    public MajorMapReader(String fileName) {
        majorMapFile = new File(fileName);
    }

    public Map<String,String> readMap() {
        Map<String,String> majors = new HashMap<>();
        try {
            FileInputStream fstream = new FileInputStream(this.majorMapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                majors.put(strLine.substring(0,2).trim(),strLine.substring(2).trim());
            }
            fstream.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        return majors;

    }
}
