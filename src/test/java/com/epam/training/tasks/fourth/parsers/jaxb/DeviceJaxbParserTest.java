package com.epam.training.tasks.fourth.parsers.jaxb;

import com.epam.training.tasks.fourth.entities.Device;
import org.junit.Test;

import java.util.List;

public class DeviceJaxbParserTest {

    @Test
    public void testUnMarshallShouldParseXmlCorrectly() {
        DeviceJaxbParser unMarshal = new DeviceJaxbParser();
        unMarshal.buildDevicesList("./src/test/resources/testdevices.xml");
        List<Device> actual = unMarshal.getParsedDevices();
        System.out.println(actual);

    }
}