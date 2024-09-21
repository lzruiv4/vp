package de.scopevisio.vp.backend.controller;

import de.scopevisio.vp.backend.data.model.RegionalFromCSV;
import de.scopevisio.vp.backend.service.RegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("regionals")
public class RegionalController {

    @Autowired
    private RegionalService regionalService;

    @GetMapping
    public ResponseEntity<List<RegionalFromCSV>> getAllRegionals() {
        return new ResponseEntity<>(regionalService.readRegionalFromCsv("src/main/resources/static/postcodes.csv"), HttpStatus.OK);
    }

}
