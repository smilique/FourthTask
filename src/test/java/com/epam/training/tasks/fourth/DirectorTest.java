package com.epam.training.tasks.fourth;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.entities.NonPeripheralDevice;
import com.epam.training.tasks.fourth.entities.PeripheralDevice;
import com.epam.training.tasks.fourth.parsers.DeviceParser;
import com.epam.training.tasks.fourth.parsers.XmlValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


public class DirectorTest {

    private final String xmlValidPath = "./src/test/resources/testdevices.xml";
    private final String xsdValidPath = "./src/test/resources/testdevices.xsd";
    private final String xmlInvalidPath = "./this/is/invalid/path.xml";
    private final String xsdInvalidPath = "./this/is/invalid/path.xsd";
    private final List<Device> VALID_EXPECTED = Arrays.asList(
            new PeripheralDevice("a1111",true,"keyboard","Keyless Genesis",75.0,true,"bluetooth"),
            new PeripheralDevice("b2222", false, "webcam", "Blindman Web Cam", 250.0, false, "USB"),
            new NonPeripheralDevice("c3333", true, "motherboard", "MN1700 Motherboard", 740.0, true, "A1"),
            new NonPeripheralDevice("d4444", false, "processor", "Champion 34230X", 1400.0, false, "A1"));
    private final List<Device> INVALID_EXPECTED = new ArrayList<>();

    private XmlValidator validator = Mockito.mock(XmlValidator.class);
    private DeviceParser parser = Mockito.mock(DeviceParser.class);


    @Before
    public void before() {
        when(validator.isValid(xsdValidPath)).thenReturn(true);
        when(validator.isValid(xsdInvalidPath)).thenReturn(false);
    }

    @Test
    public void testDirectorShouldReturnListDevicesWhenParseRequestedWithCorrectParams() {
        //given
        when(parser.getParsedDevices()).thenReturn(VALID_EXPECTED);
        Director director = new Director(validator,parser);
        //when
        List<Device> actual = director.parseDevices(xmlValidPath, xsdValidPath);
        //then
        Assert.assertEquals(VALID_EXPECTED,actual);
    }

    @Test
    public void testDirectorShouldReturnEmptyListDevicesWhenParseRequestedWithIncorrectXmlPath() {
        //given
        when(parser.getParsedDevices()).thenReturn(INVALID_EXPECTED);
        Director director = new Director(validator,parser);
        //when
        List<Device> actual = director.parseDevices(xmlInvalidPath, xsdValidPath);
        //then
        Assert.assertEquals(INVALID_EXPECTED,actual);
    }

    @Test
    public void testDirectorShouldReturnEmptyListDevicesWhenParseRequestedWithIncorrectXsdPath() {
        //given
        when(parser.getParsedDevices()).thenReturn(VALID_EXPECTED);
        Director director = new Director(validator,parser);
        //when
        List<Device> actual = director.parseDevices(xmlValidPath, xsdInvalidPath);
        //then
        Assert.assertEquals(INVALID_EXPECTED,actual);
    }

    @Test
    public void testDirectorShouldReturnEmptyListDevicesWhenParseRequestedWithIncorrectParams() {
        //given
        when(parser.getParsedDevices()).thenReturn(INVALID_EXPECTED);
        Director director = new Director(validator,parser);
        //when
        List<Device> actual = director.parseDevices(xmlInvalidPath, xsdInvalidPath);
        //then
        Assert.assertEquals(INVALID_EXPECTED,actual);
    }

}