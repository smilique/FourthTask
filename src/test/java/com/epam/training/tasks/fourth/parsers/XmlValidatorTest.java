package com.epam.training.tasks.fourth.parsers;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    private final String schemaPath = "./src/test/resources/testdevices.xsd";
    private final XmlValidator validator = new XmlValidator(schemaPath);
    private final String correctXml = "./src/test/resources/testdevices.xml";
    private final String incorrectXml = "./src/test/resources/testincorrectdevices.xml";
    private final String incorrectPath = "./incorrect/path/to/xml/file.xml";

    @Test
    public void testXmlValidatorShouldReturnTrueWhenCorrectXmlFileGiven() {
        //given
        //when
        boolean actual = validator.isValid(correctXml);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testXmlValidatorShouldReturnFalseWhenIncorrectXmlFileGiven() {
        //given
        //when
        boolean actual = validator.isValid(incorrectXml);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testXmlValidatorShouldReturnFalseWhenIncorrectXmlFilePathGiven() {
        //given
        //when
        boolean actual = validator.isValid(incorrectPath);
        //then
        Assert.assertFalse(actual);
    }

}