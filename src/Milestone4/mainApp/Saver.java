package mainApp;

import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Saver {
    private String filename = "results.csv";
    List<String[]> dataLines = new ArrayList<>();

    private String convertLineToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }

    public void addDataLine(String[] data) {
        dataLines.add(data);
    }

    public void saveCurrentData() {
        List<String> lines = getCSVLines();
        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            lines.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getCSVLines() {
        List<String> result = new ArrayList<>();
        for (String[] data : dataLines) {
            result.add(convertLineToCSV(data));
        }
        return result;
    }
}
