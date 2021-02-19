package com.epam.training.tasks.fourth.entities;


import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(name = "device", namespace = "urn:com.example")
@XmlSeeAlso({PeripheralDevice.class,NonPeripheralDevice.class})
@XmlAccessorType(XmlAccessType.FIELD)

public abstract class Device {
    @XmlElement(name = "type",required = true, namespace = "urn:com.example")
    private String type;
    @XmlElement(name = "price",required = true, namespace = "urn:com.example")
    private double price;
    @XmlElement(name = "backlight",required = true, namespace = "urn:com.example")
    private boolean backlight;
    @XmlAttribute(name = "in-stock",required = false)
    private boolean inStock;
    @XmlAttribute(name = "id", required = true)
    private String id;
    @XmlElement(name = "name", required = true, namespace = "urn:com.example")
    private String name;

    public Device() {
    }

    public void setType(String type){
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBacklight(boolean backlight) {
        this.backlight = backlight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }


    @Override
    public String toString() {
        return "Device: " +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", backlight=" + backlight;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Device device = (Device) object;
        return backlight == device.backlight && type.equals(device.type) && name.equals(device.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, price, backlight, inStock, id, name);
    }
}
