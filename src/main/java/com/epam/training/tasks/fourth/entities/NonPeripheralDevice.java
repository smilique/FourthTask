package com.epam.training.tasks.fourth.entities;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "nonPeripheralDevice", namespace = "urn:com.example")
@XmlAccessorType(XmlAccessType.FIELD)

public class NonPeripheralDevice extends Device {

    @XmlElement(name = "slot",required = true, namespace = "urn:com.example")
    private String slot;

    public NonPeripheralDevice() {
    }

    public NonPeripheralDevice(String id, boolean inStock, String type, String name, Double price, boolean backlight, String slot) {
        setId(id);
        setInStock(inStock);
        setType(type);
        setName(name);
        setPrice(price);
        setBacklight(backlight);
        setSlot(slot);

    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return super.toString() + ", slot='" + slot + '\'';
    }

}
