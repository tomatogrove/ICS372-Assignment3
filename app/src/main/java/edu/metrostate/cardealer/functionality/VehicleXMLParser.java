package edu.metrostate.cardealer.functionality;


import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.metrostate.cardealer.inventory.Dealership;

public class VehicleXMLParser {

    private static final XmlMapper mapper = createMapper();

    public static List<Dealership> read(File file) {
        List<Dealership> dealers = new ArrayList<>();
        try {
            dealers = mapper.readValue(file, VehicleXMLWrapper.class).getValidDealers();

        } catch (IOException e) {
            e.printStackTrace();
            return dealers;
        }

        return dealers;
    }

    // from https://github.com/FasterXML/jackson-dataformat-xml/issues/219#issuecomment-286003056
    private static XmlMapper createMapper() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        return new XmlMapper(module);
    }
}
