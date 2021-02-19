package com.epam.training.tasks.fourth.parsers.jaxb;

import com.epam.training.tasks.fourth.entities.Device;
import org.junit.Test;

import java.util.List;

public class DeviceJaxbParserTest {

    @Test
    public void testUnMarshallShouldParseXmlCorrectly() {
        DeviceJaxbParser unMarshal = new DeviceJaxbParser();
        List<Device> actual = unMarshal.process("./src/test/resources/testdevices.xml");

        System.out.println(actual);

    }
}