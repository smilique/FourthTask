package com.epam.training.tasks.fourth.parsers;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private static final Logger LOGGER = Logger.getLogger(XmlValidator.class);

    private String schemaName;
    final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    public XmlValidator (String schemaName) {
        this.schemaName = schemaName;
    }

    boolean isValid (String fileName) {
        LOGGER.info("Validating " + fileName);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = schemaFactory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            LOGGER.info(fileName + " is valid.");
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.error(fileName + " is not valid because " + e.getMessage());
            return false;
        }

    }
}
