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

    /**
     * Get information from csv file.
     *
     * @param path, where the csv file is.
     * @return RegionalsFromCSV as list
     * @throws IllegalArgumentException if no csv file will be read.
     */
    public List<RegionalFromCSV> readRegionalFromCsv(String path) {
        List<RegionalFromCSV> regionalFromCSVS = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {

            reader.readNext();

            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                regionalFromCSVS.add(new RegionalFromCSV(row[7], row[6]));
            }
        } catch (IOException | CsvException e) {
            throw new IllegalArgumentException("Read csv wrong");
        }
        return regionalFromCSVS;
    }

    /**
     * Get postal code and city as Map.
     *
     * @return a map, which postal code as key and city as value.
     */
    public Map<String, String> getPlzOrts() {
        return regions.stream()
                .collect(Collectors.toMap(
                    RegionalFromCSV::getPostleitzahl,
                    RegionalFromCSV::getOrt,
                    (old, neu) -> old
                ));
    }
}
