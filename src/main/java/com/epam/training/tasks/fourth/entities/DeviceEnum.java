package com.epam.training.tasks.fourth.entities;

public enum DeviceEnum {
    DEVICES("devices"),
    PERIPHERAL_DEVICE("peripheralDevice"),
    NON_PERIPHERAL_DEVICE("nonPeripheralDevice"),
    TYPE("type"),
    NAME("name"),
    PRICE("price"),
    BACKLIGHT("backlight"),
    SLOT("slot"),
    CONNECTION("connection");

    private String value;
    private DeviceEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
