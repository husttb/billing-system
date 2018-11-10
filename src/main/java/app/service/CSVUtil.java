package app.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVUtil {

    private CSVUtil() {
    }

    public static Iterable<CSVRecord> getCSVRecords(String inputPath) throws IOException {
        Reader in = new FileReader(inputPath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

        return records;
    }

    public static CSVPrinter getCSVPrinter(String outputPath) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

        return csvPrinter;
    }
}
