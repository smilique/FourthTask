package com.epam.training.tasks.fourth.parsers.sax;

import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.entities.DeviceEnum;
import com.epam.training.tasks.fourth.entities.NonPeripheralDevice;
import com.epam.training.tasks.fourth.entities.PeripheralDevice;
import com.epam.training.tasks.fourth.parsers.ParserException;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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
    }

    public List<Device> getDevices() {
        LOGGER.info("Returning list of Devices");

        return devices;
    }

    private void setAttributes(Device device, Attributes attributes) {
        LOGGER.info("Setting attributes to " + device);

        String id = attributes.getValue(0);
        device.setId(id);
        if (attributes.getLength() == 2) {
            String isInStock = attributes.getValue(1);
            boolean inStock = Boolean.parseBoolean(isInStock);
            device.setInStock(inStock);
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        switch (localName) {
            case "peripheralDevice": {
                LOGGER.info("New " + localName + " found!");

                currentDevice = new PeripheralDevice();
                setAttributes(currentDevice, attributes);
                break;
            }
            case "nonPeripheralDevice": {
                LOGGER.info("New " + localName + " found!");

                currentDevice = new NonPeripheralDevice();
                setAttributes(currentDevice, attributes);
                break;
            }
            default: {
                String currentValue = localName.toUpperCase(); //Locale.ROOT if needed
                DeviceEnum temporaryEnum = DeviceEnum.valueOf(currentValue);
                if (withText.contains(temporaryEnum)) {
                    currentEnum = temporaryEnum;
                }
                break;
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
                    double currentPrice = Double.parseDouble(element);
                    currentDevice.setPrice(currentPrice);
                    break;
                }
                case BACKLIGHT: {
                    boolean currentBacklight = Boolean.parseBoolean(element);
                    currentDevice.setBacklight(currentBacklight);
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
                    try {
                        throw new ParserException(currentEnum);
                    } catch (ParserException e) {
                        LOGGER.error(e.getMessage() + " " + e);
                    }
                }
            }
        }
        currentEnum = null;
    }
}
