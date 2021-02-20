package com.epam.training.tasks.fourth.parsers;

import com.epam.training.tasks.fourth.parsers.dom.DeviceDomParser;
import com.epam.training.tasks.fourth.parsers.jaxb.DeviceJaxbParser;
import com.epam.training.tasks.fourth.parsers.sax.DeviceSaxParser;
import org.junit.Assert;
import org.junit.Test;

import static com.epam.training.tasks.fourth.parsers.ParserType.*;
import static org.junit.Assert.*;

public class DeviceParserFactoryTest {

    private final DeviceParserFactory factory = new DeviceParserFactory();

    @Test
    public void testDeviceParserFactoryShouldReturnDomParserWhenDomRequested() {
        //given
        Class<DeviceDomParser> expected = DeviceDomParser.class;
        //when
        DeviceParser parser = factory.createParser(DOM);
        Class<?> actual = parser.getClass();
        //then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testDeviceParserFactoryShouldReturnSaxParserWhenSaxRequested() {
        //given
        Class<DeviceSaxParser> expected = DeviceSaxParser.class;
        //when
        DeviceParser parser = factory.createParser(SAX);
        Class<?> actual = parser.getClass();
        //then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testDeviceParserFactoryShouldReturnJaxbParserWhenJaxbRequested() {
        //given
        Class<DeviceJaxbParser> expected = DeviceJaxbParser.class;
        //when
        DeviceParser parser = factory.createParser(JAXB);
        Class<?> actual = parser.getClass();
        //then
        Assert.assertEquals(expected,actual);
    }
}