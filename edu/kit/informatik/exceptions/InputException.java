package edu.kit.informatik.exceptions;

import edu.kit.informatik.Constant;

public class InputException extends Exception {
    private static final long serialVersionUID = 0;

    /**
     * constructor
     * 
     * @param errorMessage the speciefied error message
     */
    public InputException(String errorMessage) {
        super(Constant.EXCEPTIONBEGINNING + errorMessage);
    }

    /**
     * constructor which contains a list of all commands as a error message
     */
    public InputException() {
        super("This is not a valid command. Please insert one of the following commands.");
    }
}