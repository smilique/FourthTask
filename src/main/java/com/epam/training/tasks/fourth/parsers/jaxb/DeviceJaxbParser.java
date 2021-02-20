package com.epam.training.tasks.fourth.parsers.jaxb;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.entities.Devices;
import com.epam.training.tasks.fourth.parsers.DeviceParser;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DeviceJaxbParser implements DeviceParser {

    public static final Logger LOGGER = Logger.getLogger(DeviceJaxbParser.class);

    private Devices devices = new Devices();

    public List<Device> getParsedDevices() {
        return devices.getDevices();
    }

    public void buildDevicesList(String filename) {
        FileReader reader = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Devices.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new FileReader(filename);
            devices = (Devices) unmarshaller.unmarshal(reader);

            } catch (JAXBException e) {
            LOGGER.error("JAXB Exception " + e.getMessage() + e);

            } catch (FileNotFoundException e) {
            LOGGER.error("File not found " + e.getMessage() + e);
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LOGGER.error("IO exception " + e.getMessage() + e);
            }
        }
    }
}
