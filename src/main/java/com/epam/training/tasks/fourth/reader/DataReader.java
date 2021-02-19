package com.epam.training.tasks.fourth.reader;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    private static final Logger LOGGER = Logger.getLogger(DataReader.class);

    public List<String> read(String filename) throws DataReaderException {

        LOGGER.info("DataReader reads data from: " + filename);

        BufferedReader reader = null;

        List<String> result = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filename));

            while (reader.ready()) {
                result.add(reader.readLine());
            }

        }
        catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            throw new DataReaderException("Fail ", e);
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                LOGGER.error("Reader close failed.",e);
            }

        }

        return result;

    }
}
