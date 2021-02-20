package com.epam.training.tasks.fourth.parsers.sax;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.entities.NonPeripheralDevice;
import com.epam.training.tasks.fourth.entities.PeripheralDevice;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DeviceSaxParserTest {



    @Test
    public void testDeviceSaxParserShouldReturnListDevicesWhenBothDeviceClassesUsed() {

        DeviceSaxParser saxParser = new DeviceSaxParser();
        saxParser.buildDevicesList("./src/test/resources/testdevices.xml");
        List<Device> expected = Arrays.asList(new PeripheralDevice("a1111",true,"keyboard","Keyless Genesis",75.0,true,"bluetooth"),
                new PeripheralDevice("b2222", false, "webcam", "Blindman Web Cam", 250.0, false, "USB"),
                new NonPeripheralDevice("c3333", true, "motherboard", "MN1700 Motherboard", 740.0, true, "A1"),
                new NonPeripheralDevice("d4444", false, "processor", "Champion 34230X", 1400.0, false, "A1"));

        List<Device> actual = saxParser.getParsedDevices();

        Assert.assertEquals(expected, actual);
    }


}