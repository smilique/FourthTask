package com.epam.training.tasks.fourth.parsers.sax;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.parsers.AbstractParserTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DeviceSaxParserTest extends AbstractParserTest {

    @Test
    public void testDeviceSaxParserShouldReturnListDevicesWhenParseXmlRequested() {
        //given
        DeviceSaxParser saxParser = new DeviceSaxParser();
        saxParser.buildDevicesList(xmlPath);
        //when
        List<Device> actual = saxParser.getParsedDevices();
        //then
        Assert.assertEquals(expected, actual);
    }


}