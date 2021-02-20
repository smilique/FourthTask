package com.epam.training.tasks.fourth.parsers;

import com.epam.training.tasks.fourth.parsers.dom.DeviceDomParser;
import com.epam.training.tasks.fourth.parsers.jaxb.DeviceJaxbParser;
import com.epam.training.tasks.fourth.parsers.sax.DeviceSaxParser;


public class DeviceParserFactory {

    public DeviceParser createParser(ParserType type) {
        switch (type) {
            case SAX: {
                return new DeviceSaxParser();
            }
            case DOM: {
                return new DeviceDomParser();
            }
            case JAXB: {
                return new DeviceJaxbParser();
            }
            default: {
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
            }
        }
    }

}


