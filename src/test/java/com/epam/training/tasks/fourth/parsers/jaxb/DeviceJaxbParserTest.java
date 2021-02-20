package com.epam.training.tasks.fourth.parsers.jaxb;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.parsers.AbstractParserTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DeviceJaxbParserTest extends AbstractParserTest {

    @Test
    public void testUnMarshallShouldParseXmlCorrectly() {
        //given
        DeviceJaxbParser jaxbParser = new DeviceJaxbParser();
        jaxbParser.buildDevicesList(XML_PATH);
        //when
        List<Device> actual = jaxbParser.getParsedDevices();
        //then
        Assert.assertEquals(EXPECTED,actual);
    }
}