package com.epam.training.tasks.fourth.parsers;

import com.epam.training.tasks.fourth.parsers.sax.DeviceSaxParser;


public class DeviceParserFactory {

    public DeviceParser createParser(ParserType type) {
        switch (type) {
            case SAX: {
                return new DeviceSaxParser();
            }
            case DOM: {
                //return new DevicesDomBuilder();
            }
            case JAXB: {
                //return new DevicesJaxbBuilder();
            }
            default: {
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
            }
        }
    }

}
//в валидаторе передать xds в конструктор (не в метод)

//для девайса можно сделать через билдер