package com.epam.training.tasks.fourth.parsers.sax;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.parsers.DeviceParser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class DeviceSaxParser implements DeviceParser {

    private static final Logger LOGGER = Logger.getLogger(DeviceSaxParser.class);

    private final DeviceSaxHandler handler;

    private List<Device> devices;
    private XMLReader reader;

    public DeviceSaxParser() {
        LOGGER.info("Parsing with SAX");
        handler = new DeviceSaxHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            System.out.println("SAX parser error " + e);
        }
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void buildDevicesList(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            LOGGER.error("SAX parsser error" + e);

        } catch (IOException e) {
            LOGGER.error("IO exception" + e);
        }

        devices = handler.getDevices();
    }
}
