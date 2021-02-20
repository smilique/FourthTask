package com.epam.training.tasks.fourth.parsers;

public class ParserException extends Exception {


    public ParserException (String message, Throwable cause) {
        super(message, cause);
    }

    public ParserException(Enum currentEnum) {
        System.err.println(currentEnum.getDeclaringClass() + " " + currentEnum.name());
    }
}
