package com.epam.training.tasks.fourth.parsers.sax;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.entities.DeviceEnum;
import com.epam.training.tasks.fourth.entities.NonPeripheralDevice;
import com.epam.training.tasks.fourth.entities.PeripheralDevice;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import static com.epam.training.tasks.fourth.entities.DeviceEnum.*;

public class DeviceSaxHandler extends DefaultHandler {

    private static final Logger LOGGER = Logger.getLogger(DeviceSaxHandler.class);

    private List<Device> devices;
    private Device currentDevice = null;
    private DeviceEnum currentEnum = null;
    private EnumSet<DeviceEnum> withText;

    public DeviceSaxHandler() {
        devices = new ArrayList<>();
        withText = EnumSet.range(DEVICES,CONNECTION);

        LOGGER.info("Enum range " + DEVICES + " " + CONNECTION);
    }

    public List<Device> getDevices() {
        LOGGER.info("Returning list of Devices");

        return devices;
    }

    private PeripheralDevice parseAttributes(PeripheralDevice device, Attributes attributes) {

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
 //change
         if ("peripheralDevice".equals(localName)) {
            currentDevice = new PeripheralDevice();
            currentDevice.setId(attributes.getValue(0));
            LOGGER.info("New " + localName + " found!");
            if (attributes.getLength() == 2) {
                LOGGER.info("Second attribute " + attributes.getValue(1));
                String inStockAttribute = attributes.getValue(1);
                currentDevice.setInStock(
                        Boolean.parseBoolean(inStockAttribute));
            }
        } else if ("nonPeripheralDevice".equals(localName)) {
            currentDevice = new NonPeripheralDevice();
            currentDevice.setId(attributes.getValue(0));
            LOGGER.info("New " + localName + " found!");
            if (attributes.getLength() == 2) {
                LOGGER.info("Second attribute " + attributes.getValue(1));
                String inStockAttribute = attributes.getValue(1);
                currentDevice.setInStock(
                        Boolean.parseBoolean(inStockAttribute));
            }
        } else {
            DeviceEnum temporaryEnum = DeviceEnum.valueOf(localName.toUpperCase(Locale.ROOT));
            if (withText.contains(temporaryEnum)) {
                currentEnum = temporaryEnum;
            }

        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("nonPeripheralDevice".equals(localName) || "peripheralDevice".equals(localName)) {
            devices.add(currentDevice);
            LOGGER.info("New device " + currentDevice + " added");
        }
    }

    public void characters(char[] ch, int start, int length) {
        String element = new String(ch, start, length).trim();
        LOGGER.info("Parameter " + element + " found");
        if (currentEnum != null) {
            switch (currentEnum) {
                case TYPE: {
                    currentDevice.setType(element);
                    break;
                }
                case NAME: {
                    currentDevice.setName(element);
                    break;
                }
                case PRICE: {
                    currentDevice.setPrice(Double.parseDouble(element));
                    break;
                }
                case BACKLIGHT: {
                    currentDevice.setBacklight(Boolean.parseBoolean(element));
                    break;

                }
                case SLOT: {
                    if (currentDevice instanceof NonPeripheralDevice) {
                        ((NonPeripheralDevice) currentDevice).setSlot(element);
                    }
                    break;
                }

                case DEVICES:
                {
                    break;
                }

                case CONNECTION: {
                    if (currentDevice instanceof PeripheralDevice) {
                        ((PeripheralDevice) currentDevice).setConnection(element);
                    }
                    break;
                }

                default: {
                    //rewrite to own exception
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
                }
            }
        }
        currentEnum = null;
    }
}
