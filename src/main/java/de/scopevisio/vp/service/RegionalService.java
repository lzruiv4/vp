package de.scopevisio.vp.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import de.scopevisio.vp.data.model.RegionalFromCSV;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegionalService {

    public List<RegionalFromCSV> readRegionalFromCsv(String path) {
        List<RegionalFromCSV> regionalFromCSVS = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {

            reader.readNext();

            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                regionalFromCSVS.add(new RegionalFromCSV(row[2], row[4], row[5], row[6], row[7]));
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace(); // Maybe use logger
        }
        return regionalFromCSVS;
    }
}
