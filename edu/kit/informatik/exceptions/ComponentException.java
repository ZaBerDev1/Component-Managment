package edu.kit.informatik.exceptions;

import edu.kit.informatik.Constant;

public class ComponentException extends Exception {
    private static final long serialVersionUID = 0;

    /**
     * constructor
     * 
     * @param errorMessage the speciefied error message
     */
    public ComponentException(String errorMessage) {
        super(Constant.EXCEPTIONBEGINNING + errorMessage);
    }
}