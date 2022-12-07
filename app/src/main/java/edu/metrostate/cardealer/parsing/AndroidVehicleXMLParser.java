package edu.metrostate.cardealer.parsing;

import com.tickaroo.tikxml.TikXml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.metrostate.cardealer.inventory.Dealership;
import okio.BufferedSource;
import okio.Okio;

public class AndroidVehicleXMLParser {

    public static List<Dealership> read(File file) {
        List<Dealership> dealers = new ArrayList<>();

        TikXml tikXml = new TikXml.Builder().build();

        try {
            BufferedSource source = Okio.buffer(Okio.source(file));
            dealers = tikXml.read(source, VehicleXMLWrapper.class).getValidDealers();
        } catch (IOException e) {
            e.printStackTrace();
            return dealers;
        }

        return dealers;
    }
}
