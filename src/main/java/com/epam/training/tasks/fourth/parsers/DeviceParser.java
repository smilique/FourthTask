package com.epam.training.tasks.fourth.parsers;

import com.epam.training.tasks.fourth.entities.Device;

import java.util.List;

public interface DeviceParser {

    void buildDevicesList(String filename);

    public List<Device> getParsedDevices();
}
