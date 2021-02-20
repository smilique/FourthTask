package com.epam.training.tasks.fourth;


import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.parsers.DeviceParser;
import com.epam.training.tasks.fourth.parsers.XmlValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Director {

    private static final Logger LOGGER = Logger.getLogger(Director.class);

    private XmlValidator validator;
    private DeviceParser parser;

    public Director(XmlValidator validator, DeviceParser parser) {
        this.parser = parser;
        this.validator = validator;
    }

    public List<Device> parseDevices(String filename, String schema) {
        LOGGER.info("Validating devices with " + schema + " schema");
        List<Device> devices = new ArrayList<>();
        if (validator.isValid(schema)) {
            LOGGER.info("Parsing devices with " + parser);
            devices = parser.getParsedDevices();
        }

        return devices;
    }

}
