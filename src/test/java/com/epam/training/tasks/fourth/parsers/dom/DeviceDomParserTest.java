package com.epam.training.tasks.fourth.parsers.dom;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.parsers.AbstractParserTest;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

public class DeviceDomParserTest extends AbstractParserTest {

    @Test
    public void testDevicesDomParserShouldReturnListDevicesWhenBothDeviceClassesUsed() {
        //given
        DeviceDomParser domParser = new DeviceDomParser();
        domParser.buildDevicesList(XML_PATH);
        //when
        List<Device> actual = domParser.getParsedDevices();
        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}