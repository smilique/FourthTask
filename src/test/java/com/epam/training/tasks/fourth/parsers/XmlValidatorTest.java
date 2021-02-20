package com.epam.training.tasks.fourth.parsers;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    private final String SCHEMA_PATH = "./src/test/resources/testdevices.xsd";
    private final XmlValidator VALIDATOR = new XmlValidator(SCHEMA_PATH);
    private final String CORRECT_XML = "./src/test/resources/testdevices.xml";
    private final String INCORRECT_XML = "./src/test/resources/testincorrectdevices.xml";
    private final String INCORRECT_XML_PATH = "./incorrect/path/to/xml/file.xml";

    @Test
    public void testXmlValidatorShouldReturnTrueWhenCorrectXmlFileGiven() {
        //given
        //when
        boolean actual = VALIDATOR.isValid(CORRECT_XML);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testXmlValidatorShouldReturnFalseWhenIncorrectXmlFileGiven() {
        //given
        //when
        boolean actual = VALIDATOR.isValid(INCORRECT_XML);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testXmlValidatorShouldReturnFalseWhenIncorrectXmlFilePathGiven() {
        //given
        //when
        boolean actual = VALIDATOR.isValid(INCORRECT_XML_PATH);
        //then
        Assert.assertFalse(actual);
    }

}