package com.epam.training.tasks.fourth.parsers.dom;


import com.epam.training.tasks.fourth.entities.Device;
import com.epam.training.tasks.fourth.entities.NonPeripheralDevice;
import com.epam.training.tasks.fourth.entities.PeripheralDevice;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DevicesDomParser {

    private static final Logger LOGGER = Logger.getLogger(DevicesDomParser.class);

    private List<Device> devices;
    private DocumentBuilder docBuilder;

    public DevicesDomParser() {
        this.devices = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Invalid parser configuration" + e.getMessage(),e);
        }
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void buildListDevices(String filename) {
        Document doc = null;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();

            NodeList peripheralDevices = root.getElementsByTagName("peripheralDevice");
            for (int i = 0; i < peripheralDevices.getLength(); i++) {

                Element deviceElement = (Element) peripheralDevices.item(i);
                PeripheralDevice device = buildPeripheral(deviceElement);
                devices.add(device);
            }
            //simplify and shorten it
            NodeList nonPeripheralDevices = root.getElementsByTagName("nonPeripheralDevice");
            for (int i = 0; i < nonPeripheralDevices.getLength(); i++) {

                Element deviceElement = (Element) nonPeripheralDevices.item(i);
                NonPeripheralDevice device = buildNonPeripheral(deviceElement);
                devices.add(device);
            }

        } catch (IOException | SAXException e ) {
            LOGGER.error(e.getMessage() + e);
        }
    }

    private Device build (Element deviceElement, Device device) {

        device.setId(
                deviceElement.getAttribute("id"));
        device.setInStock(
                Boolean.parseBoolean(
                        deviceElement.getAttribute("in-stock")));
        device.setType(getElementTextContent(deviceElement, "type"));
        device.setName(getElementTextContent(deviceElement,"name"));
        device.setPrice(Double.parseDouble(getElementTextContent(deviceElement,"price")));
        device.setBacklight(Boolean.parseBoolean(getElementTextContent(deviceElement,"backlight")));

        return device;
    }

    private PeripheralDevice buildPeripheral(Element deviceElement) {
        PeripheralDevice device = new PeripheralDevice();
        device.setConnection(getElementTextContent(deviceElement,"connection"));

        return (PeripheralDevice) build(deviceElement, device);
    }

    private NonPeripheralDevice buildNonPeripheral (Element deviceElement) {
        NonPeripheralDevice device = new NonPeripheralDevice();
        device.setSlot(getElementTextContent(deviceElement, "slot"));

        return (NonPeripheralDevice) build(deviceElement,device);

    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String value = node.getTextContent();
        return value;
    }

}
