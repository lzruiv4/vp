package de.scopevisio.vp.data.model;

public record RegionalFromCSV(
        String bundesland,
        String land,
        String ort,
        String postleitzahl,
        String bezirk
) {
}
