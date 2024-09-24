package de.scopevisio.vp.backend.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import de.scopevisio.vp.backend.data.model.RegionalFromCSV;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegionalService {

    private final List<RegionalFromCSV> regions;

    public RegionalService() {
        this.regions = readRegionalFromCsv("src/main/resources/static/postcodes.csv");
    }

    public List<RegionalFromCSV> readRegionalFromCsv(String path) {
        List<RegionalFromCSV> regionalFromCSVS = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {

            reader.readNext();

            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                regionalFromCSVS.add(new RegionalFromCSV(row[2], row[0], row[7], row[6], row[8]));
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return regionalFromCSVS;
    }

    public Map<String, List<String>> getPlzOrts() {
        return regions.stream()
                .collect(Collectors.groupingBy(
                        RegionalFromCSV::getPostleitzahl,
                        Collectors.mapping(RegionalFromCSV::getOrt, Collectors.toList()))
                );
    }
}
