package com.epam.training.tasks.fourth.entities;


import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(name = "peripheralDevice", namespace = "urn:com.example")
@XmlAccessorType(XmlAccessType.FIELD)

public class PeripheralDevice extends Device {

    @XmlElement(name = "connection",required = true, namespace = "urn:com.example")
    private String connection;

    public PeripheralDevice() {

    }

    public PeripheralDevice(String id, boolean inStock, String type, String name, Double price, boolean backlight, String connection) {
        setId(id);
        setInStock(inStock);
        setType(type);
        setName(name);
        setPrice(price);
        setBacklight(backlight);
        setConnection(connection);

    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return  super.toString() + ", connection='" + connection + '\'';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        PeripheralDevice that = (PeripheralDevice) object;
        return connection.equals(that.connection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connection);
    }
}
