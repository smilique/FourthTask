package com.epam.training.tasks.fourth.entities;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "devices", namespace = "urn:com.example")
@XmlAccessorType(XmlAccessType.FIELD)
public class Devices {

    @XmlElementRef(name = "device", namespace = "urn:com.example",type = Device.class)

    private List<Device> devices = new ArrayList<>();

    public Devices() {
        super();
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices (List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    @Override
    public String toString() {
        return "Devices{" +
                "devices=" + devices +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Devices devices1 = (Devices) object;
        return Objects.equals(devices, devices1.devices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devices);
    }
}

